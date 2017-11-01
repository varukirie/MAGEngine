package magengine.mulplay;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import javafx.scene.text.Text;
import magengine.element.impl.Player;
import magengine.game.GameSession;

public class InitAndPlayerHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		GameSession.getGameSession().setMulplayChannel(ctx.channel());
		ctx.channel().eventLoop().scheduleWithFixedDelay(()->{
//			System.out.println("server sended pxy! "+new Date());
			try {
				//TODO 
				Player player1 = Player.getPlayer1();
				String msg = "pxy:"+player1.getX()+"|"+player1.getY()+"|"+player1.getVelocityX()+"|"+player1.getVelocityY()+"\n";
				ctx.channel().writeAndFlush(msg).sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 500, 20, TimeUnit.MILLISECONDS);
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("channel　read call");
		String strmsg = (String) msg;
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
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		GameSession.getGameSession().setMulplayChannel(null);
	}
	
}
