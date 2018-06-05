package com.nh.p2p;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

	//通过SimpMessagingTemplate向浏览器发送消息
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	//priciple包含当前用户信息
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) {
		//如果发送人是wyf，则发送给wisely
		if (principal.getName().equals("wyf")) {
			//向用户发送消息，第一个参数是接受消息的用户，第二个是浏览器订阅的地址，第三个是消息本身
			messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications", principal.getName()+"-send:" + msg);
		}else {
			messagingTemplate.convertAndSendToUser("wyf", "/queue/notifications", principal.getName() +"-send:"+msg);
		}
	}
	
}
