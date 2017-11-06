package magengine.mulplay;

import java.util.concurrent.CountDownLatch;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	
	private CountDownLatch pingcdl = new CountDownLatch(1);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String strmsg = (String) msg;
		switch(strmsg){
		case "ping":
			System.out.println("Client :getPing!");
			ctx.writeAndFlush(Unpooled.copiedBuffer("pong",CharsetUtil.UTF_8));
			pingcdl.countDown();
			break;
			default:
				ctx.fireChannelRead(strmsg);
		}
	}
	
	public CountDownLatch getPingcdl() {
		return pingcdl;
	}
}
