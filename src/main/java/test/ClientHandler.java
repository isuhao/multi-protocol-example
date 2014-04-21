package test;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UnknownFieldSet;
import com.server.message.protocol.Msg.Message;
import com.server.message.protocol.MsgLogin.LoginRequest;
import com.server.message.protocol.MsgLogin.LoginResponse;

/**
 * 
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(ClientHandler.class);

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		Channel c = e.getChannel();
		LoginRequest request = LoginRequest.newBuilder().setUserId("ddd")
				.build();
		Message msg = Message.newBuilder().setMsgId(1000)
				.setExtension(LoginRequest.loginRequest, request).build();
		System.out.println("客户端发送的消息:" + msg);
		c.write(msg);

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws InvalidProtocolBufferException {

		Message msg = (Message) e.getMessage();
//		ExtensionRegistry registry = ExtensionRegistry.newInstance();
//		registry.add(LoginResponse.loginResponse);
//		UnknownFieldSet unknownFields = msg.getUnknownFields();
//		Message parseFrom = Message.parseFrom(unknownFields.toByteString(), registry);
		System.out.println("客户端收到的消息:" + msg);

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.warn("Unexpected exception from downstream.", e.getCause());
		e.getChannel().close();
	}

}
