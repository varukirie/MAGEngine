package magengine.mulplay;

import java.util.concurrent.CountDownLatch;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
	
	private CountDownLatch pingcdl = new CountDownLatch(1);
	
	
	public CountDownLatch getPingcdl() {
		return pingcdl;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String strmsg = (String) msg;
		switch(strmsg){
		case "ping":
			System.out.println("Client :getPing!");
			ctx.writeAndFlush("pong");
			pingcdl.countDown();
			break;
			default:
				ctx.fireChannelRead(strmsg);
		}
	}
}
