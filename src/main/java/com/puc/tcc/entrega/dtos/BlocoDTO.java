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
public class BlocoDTO {

	private String id;

	@NotNull()
	private String nome;
	
}