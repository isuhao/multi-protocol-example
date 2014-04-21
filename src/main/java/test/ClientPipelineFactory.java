package test;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

import com.google.protobuf.ExtensionRegistry;
import com.server.message.protocol.Msg;
import com.server.message.protocol.MsgLogin.LoginRequest;
import com.server.message.protocol.MsgLogin.LoginResponse;

/**
 * 
 */
public class ClientPipelineFactory implements ChannelPipelineFactory {

	Timer timer = new HashedWheelTimer();

	public ChannelPipeline getPipeline() throws Exception {
		ExtensionRegistry registry = ExtensionRegistry.newInstance();
		registry.add(LoginRequest.loginRequest);
		registry.add(LoginResponse.loginResponse);
		Msg.registerAllExtensions(registry);
		
		ChannelPipeline p = Channels.pipeline();
		p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
		p.addLast("protobufDecoder",
				new ProtobufDecoder(Msg.Message.getDefaultInstance(),registry));

		p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
		p.addLast("protobufEncoder", new ProtobufEncoder());
		p.addLast("handler", new ClientHandler());
		return p;

	}

}
