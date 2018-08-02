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
public class EntregaDoPedidoDTO {

	private String id;

	private String idCliente;
	
	private String idPedido;
	
	private String dataDeEntrega;
	
	private StatusPedido statusDoPedido;
	
}