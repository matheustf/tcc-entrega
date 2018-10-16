package com.puc.tcc.entrega.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RobbitMqConfig {

	public static final String ROUTING_ENTREGA = "my.queue.entregas";
	
	public static final String ROUTING_EMAIL = "my.queue.email";

	@Bean
	Queue queueEntregas() {
		return new Queue(ROUTING_ENTREGA, true);
	}
	
	@Bean
	Queue queueEmail() {
		return new Queue(ROUTING_EMAIL, true);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("my_queue_exchange");
	}
	
	@Bean
	Binding bindingExchangeEntregas(Queue queueEntregas, TopicExchange exchange) {
		return BindingBuilder.bind(queueEntregas).to(exchange).with(ROUTING_ENTREGA);
	}
	
	@Bean
	Binding bindingExchangeEmail(Queue queueEmail, TopicExchange exchange) {
		return BindingBuilder.bind(queueEmail).to(exchange).with(ROUTING_EMAIL);
	}
	
	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}


}
