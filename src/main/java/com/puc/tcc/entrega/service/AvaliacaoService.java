package com.puc.tcc.entrega.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.puc.tcc.entrega.dtos.AvaliacaoDTO;
import com.puc.tcc.entrega.exceptions.EntregaException;

public interface AvaliacaoService {
	
	AvaliacaoDTO consultar(String id) throws EntregaException;
	
	AvaliacaoDTO incluir(AvaliacaoDTO avaliacaoDTO);
	
	AvaliacaoDTO atualizar(String id, AvaliacaoDTO avaliacaoDTODetails) throws EntregaException;
	
	ResponseEntity<AvaliacaoDTO> deletar(String id) throws EntregaException;

	List<AvaliacaoDTO> buscarTodos();

}
