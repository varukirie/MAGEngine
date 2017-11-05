package magengine.mulplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import magengine.element.BaseElementVO;
import magengine.element.Moveable;
import magengine.element.MulSync;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.MoveHandler;
import magengine.util.DI;

public class InitAndSyncBaseElementHandler extends ChannelInboundHandlerAdapter{

	public static final long transportInterval = 20;
	
	public static double round2(double in){
		return (Math.round(in*10000)/10000.0);
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		GameSession.getGameSession().setMulplayChannel(ctx.channel());
		ctx.channel().eventLoop().scheduleWithFixedDelay(()->{
			ArrayList<BaseElementVO> vos= new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			MoveHandler mh = (MoveHandler) DI.di().get("mh");
			Iterator<Entry<String, Moveable>> iter= mh.getWantMoveMap().entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, Moveable> entry = iter.next();
				Moveable m = entry.getValue();
				Predicate<Entry<String, Moveable>> check = (e)->{
					switch(e.getKey()){
					case "player1":
						return true;
					case "player2":
						return false;
					}
					return false;
				};
				if(m instanceof MulSync && check.test(entry)){
					BaseElementVO vo = new BaseElementVO( round2(m.getX()), round2(m.getY()), round2(m.getVelocityX()), round2(m.getVelocityY()), entry.getKey(),m.getClass().getName(),Player.getPlayer1().isShooting,Player.getPlayer1().getNoHurtMode());
					vos.add(vo);
				}
			}
			String msg;
			try {
				msg = mapper.writeValueAsString(vos);
				msg = "moveMap:"+msg+"\n";
				ctx.channel().writeAndFlush(msg).sync();
//				System.out.println("movemap sended msg="+msg);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				Thread.interrupted();
				e.printStackTrace();
			}
		}, 500, transportInterval, TimeUnit.MILLISECONDS);
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("channel　read call");
		String strmsg = (String) msg;
		if(strmsg.indexOf("moveMap:")==0){
			ObjectMapper mapper = new ObjectMapper();
			List<BaseElementVO> vos = mapper.readValue(strmsg.substring(8), new TypeReference<List<BaseElementVO>>() { }); 
			MoveHandler mh = (MoveHandler) DI.di().get("mh");
			vos.forEach((elem)->{
				if(elem.getKeyName().equals("player1")){
					elem.setKeyName("player2");
				}
				Moveable m=mh.getWantMoveMap().get(elem.getKeyName());
				if(m!=null){
					m.setX(elem.getX());
					m.setY(elem.getY());
					m.setVelocityX(elem.getVx());
					m.setVelocityY(elem.getVy());
					if("player2".equals(elem.getKeyName())){
						Player player2 = (Player) m;
						player2.isShooting=elem.getShooting();
						player2.setNoHurtMode(elem.getNoHurtMode());
					}
//					System.out.println(m);
				}else{
					System.err.println("moveMap传输:wantMoveMap:不存在的element keyName = "+elem.getKeyName());
				}
			});
//			System.out.println(vos);
		}else{
			ctx.fireChannelRead(msg);
		}		
	}
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		GameSession.getGameSession().setMulplayChannel(null);
	}
	
}
