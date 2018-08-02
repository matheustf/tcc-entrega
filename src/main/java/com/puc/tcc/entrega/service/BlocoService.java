package com.puc.tcc.entrega.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.puc.tcc.entrega.dtos.BlocoDTO;
import com.puc.tcc.entrega.exceptions.VendaException;

public interface BlocoService {
	
	BlocoDTO consultar(String id) throws VendaException;
	
	BlocoDTO incluir(BlocoDTO blocoDTO);
	
	BlocoDTO atualizar(String id, BlocoDTO blocoDTODetails) throws VendaException;
	
	ResponseEntity<BlocoDTO> deletar(String id) throws VendaException;

	List<BlocoDTO> buscarTodos();

}
