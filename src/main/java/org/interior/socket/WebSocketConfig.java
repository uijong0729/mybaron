package org.interior.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
       
    	//client에서 SEND 요청을 처리한다. (
    	config.setApplicationDestinationPrefixes("/publish");
    	
    	//해당 경로로 SimpleBroker를 등록한다. SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 client에게 메시지를 전달하는 간단한 작업을 수행한다.
    	config.enableSimpleBroker("/subscribe");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	
    	//HandShake 
        registry.addEndpoint("/chattingLog").withSockJS();
    }

}