package com.puc.tcc.entrega.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.entrega.dtos.EntregaDTO;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private EntregaService entregaService;

	@Autowired
	public EntregaController(EntregaService entregaService) {
		this.entregaService = entregaService;
	}
	
	@GetMapping()
	public ResponseEntity<List<EntregaDTO>> buscarTodos() {

		List<EntregaDTO> listEntregas = entregaService.buscarTodos();

		return new ResponseEntity<List<EntregaDTO>>(listEntregas, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<EntregaDTO> incluir(@RequestBody @Valid EntregaDTO entregaDTO) {

		EntregaDTO responseEntregaDTO = entregaService.incluir(entregaDTO);
		return new ResponseEntity<EntregaDTO>(responseEntregaDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EntregaDTO> atualizar(@PathVariable(value = "id") String id, @RequestBody @Valid EntregaDTO entregaDTODetails) throws EntregaException {

		EntregaDTO entregaDTO = entregaService.atualizar(id, entregaDTODetails);

		return new ResponseEntity<EntregaDTO>(entregaDTO, HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<EntregaDTO> consultar(@PathVariable(value = "id") String idEntrega) throws EntregaException {

		EntregaDTO entregaDTO = entregaService.consultar(idEntrega);

		return new ResponseEntity<EntregaDTO>(entregaDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EntregaDTO> deletar(@PathVariable(value = "id") String id) throws EntregaException {

		ResponseEntity<EntregaDTO> response = entregaService.deletar(id);
		
		return response;
	}

}