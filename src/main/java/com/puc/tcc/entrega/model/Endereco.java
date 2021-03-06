package com.puc.tcc.entrega.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Endereco {

	private Long id;

	private String cep;

	private String logradouro;

	private String numero;

	private String bairro;

	private String complemento;

	private String cidade;

	private String estado;

	private String pais;
	
	private String destinatario;
}
