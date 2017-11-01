package magengine.mulplay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import magengine.element.BaseElementVO;
import magengine.element.Moveable;
import magengine.element.MulSync;
import magengine.game.MoveHandler;
import magengine.util.DI;

public class ServerMoveMapHandler extends ChannelInboundHandlerAdapter{

	public static double round2(double in){
		return (Math.round(in*10000)/10000.0);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().eventLoop().scheduleWithFixedDelay(()->{
			ArrayList<BaseElementVO> vos= new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			MoveHandler mh = (MoveHandler) DI.di().get("mh");
			Iterator<Entry<String, Moveable>> iter= mh.getWantMoveMap().entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, Moveable> entry = iter.next();
				Moveable m = entry.getValue();
				if(m instanceof MulSync){
					BaseElementVO vo = new BaseElementVO( round2(m.getX()), round2(m.getY()), round2(m.getVelocityX()), round2(m.getVelocityY()), entry.getKey(),m.getClass().getName());
					vos.add(vo);
				}
			}
			String msg;
			try {
				msg = mapper.writeValueAsString(vos);
				msg = "moveMap:"+msg+"\n";
				ctx.channel().writeAndFlush(msg).sync();
				System.out.println("movemap sended msg="+msg);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				Thread.interrupted();
				e.printStackTrace();
			}
		}, 500, 20, TimeUnit.MILLISECONDS);
		super.channelActive(ctx);
	}
}
