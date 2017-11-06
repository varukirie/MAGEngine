package magengine.mulplay;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import magengine.element.Moveable;
import magengine.element.MulSync;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.MoveHandler;
import magengine.util.DI;

public class InitAndVOTransportHandler extends ChannelInboundHandlerAdapter {

	public static final long transportInterval = 20;

	public static double round2(double in) {
		return (Math.round(in * 100) / 100.0);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		GameSession.getGameSession().setMulplayChannel(ctx.channel());
		ctx.channel().eventLoop().scheduleWithFixedDelay(() -> {
			ObjectMapper mapper = new ObjectMapper();
			MoveHandler mh = (MoveHandler) DI.di().get("mh");
			Iterator<Entry<String, Moveable>> iter = mh.getWantMoveMap().entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Moveable> entry = iter.next();
				Moveable m = entry.getValue();
				if (m instanceof MulSync) {
					String msg = null;
					try {
						switch (entry.getKey()) {
						case "player1":
							PlayerVO vo = new PlayerVO(round2(m.getX()), round2(m.getY()), round2(m.getVelocityX()),
									round2(m.getVelocityY()), Player.getPlayer1().isShooting,
									Player.getPlayer1().getNoHurtMode());

							msg = "PlayerVO#" + mapper.writeValueAsString(vo) + "\n";
							break;
						default:
						}
					} catch (JsonProcessingException e1) {
						e1.printStackTrace();
					}
					if (msg != null) {
						msg = "vo$" + msg;
						// System.out.println("movemap sended msg="+msg);
						try {
							ctx.channel().writeAndFlush(msg).sync();
						} catch (InterruptedException e) {
							Thread.interrupted();
							e.printStackTrace();
						}
					}
				}
			}
		}, 500, transportInterval, TimeUnit.MILLISECONDS);
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// System.out.println("channel read call");
		String strmsg = (String) msg;
		if (strmsg.indexOf("vo$") == 0) {
			ObjectMapper mapper = new ObjectMapper();
			MoveHandler mh = (MoveHandler) DI.di().get("mh");
			System.out.println(strmsg);
			System.out.println("length=" + strmsg.length());
			String vomsg = strmsg.substring(3);
			String[] vostr = vomsg.split("#");
			String indicator = vostr[0];
			String vobody = vostr[1];
			switch (indicator) {
			case "PlayerVO":
				PlayerVO elem = mapper.readValue(vobody, PlayerVO.class);
				Moveable m = mh.getWantMoveMap().get("player2");
				if (m != null) {
					m.setX(elem.getX());
					m.setY(elem.getY());
					m.setVelocityX(elem.getVx());
					m.setVelocityY(elem.getVy());
					Player player2 = (Player) m;
					player2.isShooting = elem.getShooting();
					player2.setNoHurtMode(elem.getNoHurtMode());
				} else {
					System.err.println("不存在的element keyName = " + "player2");
				}
				break;
			default:
				System.out.println("mulplay:不支持这种VO \n indicator=" + indicator);
				break;
			}
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		GameSession.getGameSession().setMulplayChannel(null);
	}

}
