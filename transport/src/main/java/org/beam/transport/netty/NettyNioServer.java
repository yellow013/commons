package org.beam.transport.netty;

import org.apache.logging.log4j.Logger;
import org.beam.common.callback.Callback;
import org.beam.common.log.CommonLoggerFactory;
import org.beam.transport.base.role.Receiver;
import org.beam.transport.netty.config.NettyConfigurator;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyNioServer implements Receiver {

	private String tag;
	private NettyConfigurator configurator;

	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ServerBootstrap serverBootstrap;

	private Callback<byte[]> callback;
	
	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	public NettyNioServer(String tag, NettyConfigurator configurator, Callback<byte[]> callback) {
		this.tag = tag;
		this.configurator = configurator;
		this.callback = callback;
		init();
	}

	public void init() {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					public void initChannel(SocketChannel socketChannel) throws Exception {
						socketChannel.pipeline().addLast(new NioSocketHandler(callback));

					}
				}).option(ChannelOption.SO_BACKLOG, 256).childOption(ChannelOption.SO_KEEPALIVE, true);
		logger.info(tag + "-ServerBootStrap.bind->" + configurator.port());
	}

	@Override
	public void receive() {
		try {
			ChannelFuture channelFuture = serverBootstrap.bind(configurator.host(), configurator.port()).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			destroy();
		}
	}

	@Override
	public void destroy() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	@Override
	public String getReceiverName() {
		return tag;
	}

	private class NioSocketHandler extends ChannelInboundHandlerAdapter {

		private Callback<byte[]> callback;

		public NioSocketHandler(Callback<byte[]> callback) {
			this.callback = callback;
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
			ByteBuf byteBuf = (ByteBuf) msg;
			byte[] bytes = new byte[byteBuf.readableBytes()];
			byteBuf.retain().readBytes(bytes);
			callback.onEvent(bytes);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			cause.printStackTrace();
			ctx.close();
		}
	}

	public static void main(String[] args) throws Exception {

		NettyConfigurator configurator = NettyConfigurator.builder().setHost("127.0.0.1").setPort(9500).build();

		NettyNioServer nettyNioServer = new NettyNioServer("LocalTest", configurator, (byte[] bytes) -> {
			System.out.println(new String(bytes));
		});

		nettyNioServer.receive();

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}