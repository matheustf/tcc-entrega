package com.puc.tcc.entrega.model;

import com.puc.tcc.entrega.enums.StatusDaEntrega;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HistoricoDeEntrega implements Comparable<HistoricoDeEntrega>{
	
	private StatusDaEntrega statusDaEntrega;
	
	private String data;

	@Override
	public int compareTo(HistoricoDeEntrega historico) {
		return - this.data.compareTo(historico.getData());
	}

}