package com.puc.tcc.entrega.dtos;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AvaliacaoDTO {

	private String id;

	@NotNull()
	private String idCliente;
	
	private String codigoDaAvaliacao;
	
	@NotNull()
	private String idPedido;
	
	@NotNull()
	private int notaDeSatisfacao;
	
	@NotNull()
	private String titulo;

	@NotNull()
	private String descricao;
	
	private String dataDaAvaliacao;
	
}