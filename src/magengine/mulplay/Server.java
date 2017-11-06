package magengine.mulplay;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Server extends Transport {

	private int port;
	private NioEventLoopGroup bossLoop;
	private NioEventLoopGroup workerLoop;

	public Server(int port) {
		this.port = port;
	}

	public long getDelay() {
		DelayTestServerHandler handler = new DelayTestServerHandler();
		while (this.channel == null) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("服务器等待连接中断");
				e.printStackTrace();
				throw new RuntimeException("服务器等待连接中断");
			}
		}
		this.channel.pipeline().addAfter("decoder", "delayHandler", handler);
		return handler.getDelay();
	}

	public void start() {
		ServerBootstrap boot = new ServerBootstrap();
		NioEventLoopGroup bossLoop = new NioEventLoopGroup(2);
		this.bossLoop = bossLoop;
		setGroup(bossLoop);
		NioEventLoopGroup workerLoop = new NioEventLoopGroup();
		this.workerLoop = workerLoop;
		try {
			boot.channel(NioServerSocketChannel.class).childOption(ChannelOption.TCP_NODELAY, true)
					.group(bossLoop, workerLoop).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							setChannel(ch);
							ch.pipeline()
									// in
									// .addLast("stringDecoder",new
									// StringDecoder())
									.addLast(new ObjectEncoder())
									.addLast("decoder",new ObjectDecoder(1024*100,ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
									.addLast(new InitHandler())
									.addLast(new PlayerVOHandler());
									
						}
					});
			ChannelFuture cf = boot.bind(port).sync();
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

	public void close() {
		this.bossLoop.shutdownGracefully();
		this.workerLoop.shutdownGracefully();

	}

	public NioEventLoopGroup getBossLoop() {
		return bossLoop;
	}
}
