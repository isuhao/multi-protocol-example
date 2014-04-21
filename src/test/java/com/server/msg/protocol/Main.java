package com.server.msg.protocol;

import com.server.msg.protocol.Msg.Message;
import com.server.msg.protocol.MsgLogin.Login;
import com.server.msg.protocol.MsgLogin.Login.LoginRequest;

public class Main {

	public static void main(String[] args) {

		LoginRequest loginRequest = Login.LoginRequest.newBuilder()
				.setUserId("555").build();
		Login login = Message.newBuilder().getExtension(MsgLogin.login)
				.toBuilder().setLoginRequest(loginRequest).build();

		Message message = Message.newBuilder()
				.setExtension(MsgLogin.login, login).build();

		System.out.println(message.hasExtension(MsgLogin.login));
		System.out.println(message.getExtension(MsgLogin.login));

		System.out.println("===============================================");

		Login getLogin = message.getExtension(MsgLogin.login);
		String userId = getLogin.getLoginRequest().getUserId();
		System.out.println(userId);

	}

}
