package com.nh.p2p;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
/**
 * 广播式，
 * 服务端有消息是，会将消息发送给所有链接了当前endpoint的浏览器
 * @author pact
 *
 */
@Configuration
//开启使用STOMP协议来传输基于代理（messageBorker）的消息
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	/**
	 * 注册STOMP协议的endpoint，并映射的执行URL
	 * @param registry
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//注册一个STOMP的endpoint，指定使用SocketJS协议
		// 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
		registry.addEndpoint("/endpointWisely").withSockJS();
		
		registry.addEndpoint("/endpointChat").withSockJS();
	}
	/**
	 * 配置消息代理（MessageBroker）
	 */
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//广播式应该配置一个/topic消息代理,点对点增加一个/queue的消息代理
		registry.enableSimpleBroker("/queue");
	}
}

