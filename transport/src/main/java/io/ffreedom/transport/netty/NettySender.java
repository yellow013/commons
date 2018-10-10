package io.ffreedom.transport.netty;

import static io.ffreedom.common.log.LogSequence.sysMicrosecond;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.transport.base.role.Sender;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

public class NettySender implements Sender<byte[]> {

	private ChannelHandlerContext context;

	// private ByteBuf byteBuf;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public NettySender(ChannelHandlerContext context) {
		this.context = context;
		// this.byteBuf = byteBuf;
	}

	// public NettySender(ChannelHandlerContext context) {
	// this(context);
	// }

	@Override
	public boolean isConnected() {
		context.disconnect();
		return true;
	}

	@Override
	public boolean destroy() {
		// byteBuf.release();
		context.disconnect();
		context.close();
		return true;
	}

	@Override
	public String getName() {
		return "Sender->ContextHashCode:" + context.hashCode() + "&";// + byteBuf.capacity();
	}

	@Override
	public void sent(byte[] msg) {
		logger.debug(sysMicrosecond() + " call sender send -> data length : " + msg.length);
		ByteBuf byteBuf = context.alloc().buffer(msg.length);
		byteBuf.writeBytes(msg);
		ChannelFuture writeAndFlush = context.writeAndFlush(byteBuf.retain());
		writeAndFlush.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				logger.debug(sysMicrosecond() + " call sender send operation complete -> data length : "
						+ byteBuf.writerIndex());
				byteBuf.clear();
				byteBuf.release();
			}
		});
	}

}