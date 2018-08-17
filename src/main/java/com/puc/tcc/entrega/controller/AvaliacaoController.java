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

import com.puc.tcc.entrega.dtos.AvaliacaoDTO;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	private AvaliacaoService avaliacaoService;

	@Autowired
	public AvaliacaoController(AvaliacaoService avaliacaoService) {
		this.avaliacaoService = avaliacaoService;
	}

	@GetMapping()
	@RequestMapping("")
	public ResponseEntity<List<AvaliacaoDTO>> buscarTodos() {

		List<AvaliacaoDTO> listAvaliacaos = avaliacaoService.buscarTodos();

		return new ResponseEntity<List<AvaliacaoDTO>>(listAvaliacaos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> consultar(@PathVariable(value = "id") String idAvaliacao) throws EntregaException {

		AvaliacaoDTO avaliacaoDTO = avaliacaoService.consultar(idAvaliacao);

		return new ResponseEntity<AvaliacaoDTO>(avaliacaoDTO, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<AvaliacaoDTO> incluir(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {

		AvaliacaoDTO responseAvaliacaoDTO = avaliacaoService.incluir(avaliacaoDTO);
		return new ResponseEntity<AvaliacaoDTO>(responseAvaliacaoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> atualizar(@PathVariable(value = "id") String id, @RequestBody @Valid AvaliacaoDTO avaliacaoDTODetails) throws EntregaException {

		AvaliacaoDTO avaliacaoDTO = avaliacaoService.atualizar(id, avaliacaoDTODetails);

		return new ResponseEntity<AvaliacaoDTO>(avaliacaoDTO, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> deletar(@PathVariable(value = "id") String id) throws EntregaException {

		ResponseEntity<AvaliacaoDTO> response = avaliacaoService.deletar(id);
		
		return response;
	}

}