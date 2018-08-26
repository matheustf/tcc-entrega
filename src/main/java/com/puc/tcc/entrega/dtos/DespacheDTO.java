package com.puc.tcc.entrega.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DespacheDTO {

	private String id;

	private String codigoDeRastreio;
	
}