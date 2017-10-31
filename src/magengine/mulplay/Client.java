package magengine.mulplay;

import java.io.Closeable;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Client implements Closeable{
	private int port;
	private String host;
	private NioEventLoopGroup group;
	private Channel channel;
	public Client(String host,int port){
		this.host=host;
		this.port = port;
	}
	
	public void start(){
		NioEventLoopGroup group = new NioEventLoopGroup();
		this.group = group;
		Bootstrap boot = new Bootstrap();
		try{
			boot.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new LineBasedFrameDecoder(1024))
					.addLast(new StringDecoder())
					.addLast(new StringEncoder())
					.addLast(new ClientHandler());
				}
			});
			ChannelFuture cf = boot.connect(this.host,this.port).sync();
			this.channel=cf.channel();
			cf.channel().closeFuture().addListener(new GenericFutureListener<Future<? super Void>>() {
				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					close();
				}
			});
		} catch (Exception e) {
			Thread.interrupted();
			e.printStackTrace();
			close();
		}		
	}
	
	public void close(){
		this.group.shutdownGracefully();
	}
	
	public Channel getChannel() {
		return channel;
	}
	public NioEventLoopGroup getGroup() {
		return group;
	}
}
