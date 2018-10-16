package com.puc.tcc.entrega.component;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puc.tcc.entrega.dtos.Email;
import com.puc.tcc.entrega.enums.TipoDeEmail;
import com.puc.tcc.entrega.model.Entrega;
import com.puc.tcc.entrega.rabbitmq.RabbitMQComponent;

@Component
public class SendEmailComponent {
	
	RabbitMQComponent rabbitMQComponent;
	
	@Autowired
	public SendEmailComponent(RabbitMQComponent rabbitMQComponent) {
		this.rabbitMQComponent = rabbitMQComponent;
	}
	
	public void sendEmail(Entrega entrega) {
		System.out.println("Send Email -> Cliente: " + entrega.getNomeDoCliente() + "Email: " + entrega.getEmailCliente());
		
		HashMap<String, String> contentReplace = new HashMap<>();
		contentReplace.put("**usuario**", entrega.getNomeDoCliente());
		contentReplace.put("**status**", entrega.getStatusDaEntrega().name());
		
		Email email = Email.builder()
				.emailDestinatario(entrega.getEmailCliente())
				.nomeDoUsuario(entrega.getNomeDoCliente())
				.tipoDeEmail(TipoDeEmail.ENTREGA.name())
				.contentReplace(contentReplace)
				.build();
		
		rabbitMQComponent.sendEmail(email);
	}

}
