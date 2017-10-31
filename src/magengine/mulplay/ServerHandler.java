package magengine.mulplay;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import magengine.element.impl.Player;

public class ServerHandler extends SimpleChannelInboundHandler<String>{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		ctx.channel().eventLoop().scheduleWithFixedDelay(()->{
//			System.out.println("server sended pxy! "+new Date());
			try {
				//TODO 
				Player player1 = Player.getPlayer1();
				ctx.channel().writeAndFlush(Unpooled.copiedBuffer("pxy:"+player1.getX()+"|"+player1.getY()+"|"+player1.getVelocityX()+"|"+player1.getVelocityY()+"\n", CharsetUtil.UTF_8)).sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 500, 20, TimeUnit.MILLISECONDS);
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String strmsg = msg;
//		System.out.println("read call"+new Date());
		if(strmsg.indexOf("pxy:")==0){
//			System.out.println("received! pxy "+new Date());
			String[] pxy=strmsg.substring(4).split("\\|");
			double px = Double.parseDouble(pxy[0]);
			double py = Double.parseDouble(pxy[1]);
			double pvx = Double.parseDouble(pxy[2]);
			double pvy = Double.parseDouble(pxy[3]);
			Player player2 = Player.getPlayer2();
			player2.setX(px);
			player2.setY(py);
			player2.setVelocityX(pvx);
			player2.setVelocityY(pvy);
		}else{
			ctx.fireChannelRead(msg);
		}
	}
}
