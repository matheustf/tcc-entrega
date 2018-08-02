package com.puc.tcc.entrega.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Bloco {

	@Id
	private String id;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String nome;
	
	public Bloco update(Bloco bloco, Bloco blocoDetails) {
		bloco.setNome(blocoDetails.getNome());
		
		return bloco;
	}
	
}
