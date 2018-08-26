package com.puc.tcc.entrega.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.puc.tcc.entrega.dtos.DespacheDTO;
import com.puc.tcc.entrega.dtos.EntregaDTO;
import com.puc.tcc.entrega.exceptions.EntregaException;

public interface EntregaService {
	
	EntregaDTO consultar(String id) throws EntregaException;
	
	EntregaDTO incluir(EntregaDTO entregaDTO);
	
	EntregaDTO atualizar(String id, EntregaDTO entregaDTODetails) throws EntregaException;
	
	ResponseEntity<EntregaDTO> deletar(String id) throws EntregaException;

	List<EntregaDTO> buscarTodos();

	void incluirList(List<EntregaDTO> entregasDTO);

	EntregaDTO consultarPorIdCompra(String idCompra) throws EntregaException;

	EntregaDTO despacharProduto(String codigoDaEntrega, DespacheDTO despache) throws EntregaException;

}
