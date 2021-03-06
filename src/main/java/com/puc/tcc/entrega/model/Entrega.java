package com.puc.tcc.entrega.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.puc.tcc.entrega.enums.StatusDaEntrega;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Entrega implements Serializable{

	private static final long serialVersionUID = 2043008054923050692L;

	private String id;
	
	@NotNull
	private String codigoDaEntrega;

	private String codigoDeRastreio;

	@NotNull
	private String idCliente;
	
	@NotNull
	private String emailCliente;
	
	@NotNull()
	private String nomeDoCliente;
	
	@NotNull
	private String idFornecedor;
	
	@NotNull
	private String idCompra;
	
	@NotNull
	private String estimativaDeEntrega;
	
	@NotNull
	private StatusDaEntrega statusDaEntrega;
	
	@NotNull
	private String nomeDoProduto;
	
	@NotNull
	private int quantidade;
	
	@NotNull
	private String urlFornecedor;
	
	@NotNull
	private String modelo;
	
	@NotNull
	private String marca;
	
	@NotNull
	private Endereco endereco;
	
	@NotNull
	private List<HistoricoDeEntrega> historicoDeEntrega;
	
	public void addHistoricoDeEntrega(HistoricoDeEntrega historicoDeEntrega){
		this.historicoDeEntrega.add(historicoDeEntrega);
	}
	
	public Entrega update(Entrega entregaDoPedido, Entrega detailsEntregaDoPedido) {
		entregaDoPedido.setEstimativaDeEntrega(detailsEntregaDoPedido.getEstimativaDeEntrega());
		
		return entregaDoPedido;
	}
	
}