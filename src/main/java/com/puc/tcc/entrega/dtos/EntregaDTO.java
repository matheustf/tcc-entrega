package com.puc.tcc.entrega.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EntregaDTO {

	private String id;

	@NotNull()
	private String idCliente;
	
	@NotNull()
	private String emailCliente;
	
	@NotNull()
	private String nomeDoCliente;
	
	private String codigoDaEntrega;
	
	private String codigoDeRastreio;
	
	@NotNull()
	private String idFornecedor;
	
	@NotNull()
	private String idCompra;
	
	@NotNull()
	private String estimativaDeEntrega;
	
	private String statusDaEntrega;
	
	private String nomeDoProduto;
	
	private String modelo;
	
	private String marca;
	
	private int quantidade;
	
	private EnderecoDTO endereco;
	
	private List<HistoricoDeEntregaDTO> historicoDeEntrega;
	
	private String urlFornecedor;
	
	private String emailFornecedor;
	
}