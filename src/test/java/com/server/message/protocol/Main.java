package com.server.message.protocol;

import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import com.server.message.protocol.Msg.Message;
import com.server.message.protocol.Msg.Pos;
import com.server.message.protocol.MsgLogin.LoginRequest;
import com.server.message.protocol.MsgLogin.LoginResponse;
import com.server.message.protocol.MsgTest.Test;

public class Main {

	public static void main(String[] args) {
		GeneratedExtension<Message, Integer> bar = Test.bar;
		Message foo = Message.newBuilder().setExtension(bar, 1).build();
		System.out.println(foo.hasExtension(bar));
		System.out.println(foo.getExtension(bar));

		System.out.println("===============================================");

		LoginRequest request = LoginRequest.newBuilder().setUserId("ddd")
				.build();
		Message msg = Message.newBuilder().setMsgId(1000)
				.setExtension(LoginRequest.loginRequest, request).build();

		System.out.println(msg.hasExtension(LoginRequest.loginRequest));
		System.out.println(msg.getExtension(LoginRequest.loginRequest));

		System.out.println("===============================================");

		LoginResponse response = LoginResponse.newBuilder().setUserId("ddd")
				.setLevel(1).setModelId(1).setName("aa")
				.setPos(Pos.newBuilder().setCol(0).setRow(0).build())
				.setSceneId(2).build();
		Message resposeMsg = Message.newBuilder().setMsgId(1000)
				.setExtension(LoginResponse.loginResponse, response).build();

		System.out.println(resposeMsg.hasExtension(LoginResponse.loginResponse));
		System.out.println(resposeMsg.getExtension(LoginResponse.loginResponse));

		System.out.println("===============================================");

		int msgId = resposeMsg.getMsgId();
		LoginResponse extension = resposeMsg.getExtension(LoginResponse.loginResponse);
		System.out.println(msgId);
		System.out.println(extension);

	}
}
