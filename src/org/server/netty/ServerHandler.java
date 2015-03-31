package org.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;

import org.proto.image.ImageProto.Image;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends SimpleChannelInboundHandler<Image> {
	
   static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
 
	
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
    	System.out.println("Handler Added: " + ctx.channel().remoteAddress() );
    	channels.add(ctx.channel());
    }
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Image i)
			throws Exception {
		// TODO Auto-generated method stub
		byte[] bFile = i.getImgbits().toByteArray();
		FileOutputStream fileOuputStream = new FileOutputStream("PATH_TO_WHERE_YOU_WANT_TO_SAVE/"+i.getFilename()+".png"); 
		fileOuputStream.write(bFile);
		fileOuputStream.close();
		
	}
}
