package com.puc.tcc.entrega.model;

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
public class Avaliacao {

	private String id;

	private String idCliente;
	
	private String idPedido;
	
	private int notaDeSatisfacao;
	
	private String titulo;

	private String descricao;
	
	private String dataDaAvaliacao;
	
	public Avaliacao update(Avaliacao avaliacao, Avaliacao detailsAvaliacao) {
		avaliacao.setTitulo(detailsAvaliacao.getTitulo());
		avaliacao.setNotaDeSatisfacao(avaliacao.getNotaDeSatisfacao());
		avaliacao.setTitulo(detailsAvaliacao.getDescricao());
		avaliacao.setDataDaAvaliacao(detailsAvaliacao.getDataDaAvaliacao());
		
		return avaliacao;
	}
	
}
