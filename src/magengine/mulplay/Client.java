package magengine.mulplay;

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
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Client extends Transport {
	private int port;
	private String host;
	private ClientHandler clientHandler ;
	
	
	public Client(String host,int port){
		this.host=host;
		this.port = port;
	}
	
	
	
	public void waitPing(){
		try {
			clientHandler.getPingcdl().await();
		} catch (InterruptedException e) {
			System.out.println("客户端等待服务器响应 状态:中断");
			e.printStackTrace();
			throw new RuntimeException("客户端等待服务器响应 状态:中断");
		}
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
					clientHandler = new ClientHandler();
					ch.pipeline()
					//in
					.addLast(new LineBasedFrameDecoder(1024))
//					.addLast(new JdkZlibDecoder())
					.addLast("stringDecoder",new StringDecoder())
					.addLast(new InitAndVOTransportHandler())
					.addLast(clientHandler)
					//out
//					.addLast(new JdkZlibEncoder())
					.addLast(new StringEncoder());
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
	

}
