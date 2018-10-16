package com.puc.tcc.entrega.component;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.puc.tcc.entrega.model.Entrega;

@Component
public class SendNotificationComponent {

	public void sendNotification(Entrega entrega) {
		try {

			System.out.println("<SendNotification> Fornecedor: " + entrega.getIdFornecedor() + "  URL: "
					+ entrega.getUrlFornecedor());
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Entrega> request = new HttpEntity<>(entrega);
			restTemplate.postForObject(entrega.getUrlFornecedor(), request, String.class);
		} catch (HttpClientErrorException e) {
			System.out.println("Erro: Lambda fora do ar");
		}
	}

}
