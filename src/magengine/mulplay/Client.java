package magengine.mulplay;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
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
					.addLast(new ObjectEncoder())
//					.addLast("stringDecoder",new StringDecoder())
					.addLast("decoder",new ObjectDecoder(1024*100,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
					.addLast(clientHandler)
					.addLast(new InitHandler())
					.addLast(new PlayerVOHandler());
//					.addLast(new StringEncoder());
				}
			});
			ChannelFuture cf = boot.connect(this.host,this.port).sync();
			this.channel=cf.channel();
			cf.channel().closeFuture().addListener(future -> close());
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
