package org.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.proto.image.ImageProto.Image;

/**
 * Handles a client-side channel.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Image> {

    private static final Logger logger = Logger.getLogger(
            ClientHandler.class.getName());

        

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.", cause);
        ctx.close();
    }

//    @Override
//	protected void channelRead0(ChannelHandlerContext ctx, Person p)
//			throws Exception {
//    	Person res = Person.newBuilder().setEmail(p.getEmail() ).setName(p.getName()).setId(p.getId()).build();
//        System.out.println("[SERVER]: " + res.getEmail());
////        ctx.write(res);
//	}
//    
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Image arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}