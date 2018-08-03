package com.puc.tcc.entrega.model;

import java.util.List;

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
public class Entrega {

	private String id;

	private String idCliente;
	
	private String idFornecedor;
	
	private String idPedido;
	
	private String estimativaDeEntrega;
	
	private StatusDaEntrega statusDaEntrega;
	
	private List<HistoricoDeEntrega> historicoDeEntrega;
	
	public Entrega update(Entrega entregaDoPedido, Entrega detailsEntregaDoPedido) {
		entregaDoPedido.setEstimativaDeEntrega(detailsEntregaDoPedido.getEstimativaDeEntrega());
		
		return entregaDoPedido;
	}
	
}