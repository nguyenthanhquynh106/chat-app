package org.o7planning.springbootwebsocket.config;

import org.o7planning.springbootwebsocket.interceptor.HttpHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //Bật tính năng websocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private HttpHandshakeInterceptor handshakeInterceptor;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) { //Đăng ký một websocket endpoint mà các máy khách sẽ sử dụng để kết nối với máy chủ
		registry.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor); //SockJS được sử dụng để bật tùy chọn dự phòng cho các trình duyệt không hỗ trợ websocket
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}

}
