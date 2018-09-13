package com.puc.tcc.entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaApplication.class, args);
	}

}
