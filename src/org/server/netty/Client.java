package org.server.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.proto.image.ImageProto;

import com.google.protobuf.ByteString;

public class Client {
	
	private static final int CLIENT_PORT = 5997;
	
	
	public void run() throws Exception {    	
		System.out.println("WELCOME TO CLIENT");
		EventLoopGroup group = new NioEventLoopGroup(); // 1
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ClientInitializer()); //2
			
			Channel ch; 
			// Start the connection attempt.
			ch = b.connect("localhost",CLIENT_PORT).sync().channel(); //3

			ChannelFuture lastWriteFuture;
			System.out.println("Sending image...");


			File f = new File("PATH_TO_FILE_THAT_YOU_WANT_TO_SEND");
			InputStream inputStream=new FileInputStream(f);
            ImageProto.Image i = ImageProto.Image.newBuilder().setImgbits(ByteString.readFrom(inputStream)).setFilename("Photo").build();
            lastWriteFuture = ch.writeAndFlush(i);
			lastWriteFuture.channel().closeFuture().sync();
		}
		finally{
			group.shutdownGracefully();
		}
 }
	public static void main(String[] args) throws Exception {
		new Client().run(); // there are better ways to do it. 
	}

}
