package org.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import org.proto.image.ImageProto.Image;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();

        
	       // add decoders and encoders.
        pipeline.addLast ("frameDecoder", new ProtobufVarint32FrameDecoder ());
        pipeline.addLast ("protobufDecoder", new ProtobufDecoder (Image.getDefaultInstance ()));

        pipeline.addLast ("frameEncoder", new ProtobufVarint32LengthFieldPrepender ());
        pipeline.addLast ("protobufEncoder", new ProtobufEncoder ());


	        // and then business logic.
	        pipeline.addLast("handler", new ClientHandler()); // 
	}
}
