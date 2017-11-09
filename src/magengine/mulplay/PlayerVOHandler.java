package magengine.mulplay;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import magengine.element.Moveable;
import magengine.element.impl.Player;
import magengine.game.MoveHandler;
import magengine.util.DI;

public class PlayerVOHandler extends SimpleChannelInboundHandler<PlayerVO> {

	public static final long transportInterval = 20;

	public static double round2(double in) {
		return (Math.round(in * 100) / 100.0);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().eventLoop().scheduleWithFixedDelay(() -> {
			PlayerVO vo = null;
			MoveHandler mh = (MoveHandler) DI.di().get("mh");
			Iterator<Entry<String, Moveable>> iter = mh.getWantMoveMap().entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Moveable> entry = iter.next();
				Moveable m = entry.getValue();
				if (m instanceof MulSync) {
					try {
						switch (entry.getKey()) {
						case "player1":
							vo = new PlayerVO(round2(m.getX()), round2(m.getY()), round2(m.getVelocityX()),
									round2(m.getVelocityY()), Player.getPlayer1().isShooting,
									Player.getPlayer1().getNoHurtMode());
							ctx.channel().writeAndFlush(vo).sync();
							break;
						default:
						}

					} catch (InterruptedException e) {
						Thread.interrupted();
						e.printStackTrace();
					}
				}
			}
		}, 500, transportInterval, TimeUnit.MILLISECONDS);
		super.channelActive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, PlayerVO msg) throws Exception {
//		System.out.println("serial get! " + msg);
		PlayerVO elem = msg;
		MoveHandler mh = (MoveHandler) DI.di().get("mh");
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
	}

}
