package com.puc.tcc.entrega.rabbitmq;

import com.puc.tcc.entrega.model.Entrega;

public interface RabbitMQComponent {

	void sendEntrega(Entrega entrega);
	
}
