package com.puc.tcc.entrega.dtos;

import com.puc.tcc.entrega.enums.StatusDaEntrega;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HistoricoDeEntregaDTO {
	
	private StatusDaEntrega statusDaEntrega;
	
	private String data;

}
