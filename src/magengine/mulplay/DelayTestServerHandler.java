package magengine.mulplay;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class DelayTestServerHandler extends ChannelInboundHandlerAdapter {
	
	private CountDownLatch cdl = new CountDownLatch(1);
	private long timeRec=0;
	private long delay = 0;
	
	
	public long getDelay(){
		try {
			if(cdl.await(10000, TimeUnit.MILLISECONDS)){
				return delay;
			}else{
				throw new RuntimeException(this.getClass().getName()+" 同步超时  ");
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
			e.printStackTrace();
			throw new RuntimeException(this.getClass().getName()+" 同步被中断  ");
		}
		
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
		System.out.println("delay Handler added");
		ctx.writeAndFlush(Unpooled.copiedBuffer("ping",CharsetUtil.UTF_8)).sync();
		timeRec=System.currentTimeMillis();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String strmsg = (String) msg;
		if("pong".equals(strmsg)){
			delay=System.currentTimeMillis()-timeRec;
			cdl.countDown();
		}else{
			ctx.fireChannelRead(strmsg);
		}
	}
}
