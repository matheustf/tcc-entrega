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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.entrega.controller.validate.TokenValidate;
import com.puc.tcc.entrega.dtos.AvaliacaoDTO;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.exceptions.GenericException;
import com.puc.tcc.entrega.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	private AvaliacaoService avaliacaoService;
	private TokenValidate tokenValidate;

	@Autowired
	public AvaliacaoController(AvaliacaoService avaliacaoService, TokenValidate tokenValidate) {
		this.avaliacaoService = avaliacaoService;
		this.tokenValidate = tokenValidate;
	}

	@GetMapping()
	public ResponseEntity<List<AvaliacaoDTO>> buscarTodos(@RequestHeader(value = "x-access-token") String token) throws GenericException {
		tokenValidate.tokenValidate(token);
		
		List<AvaliacaoDTO> listAvaliacaos = avaliacaoService.buscarTodos();

		return new ResponseEntity<List<AvaliacaoDTO>>(listAvaliacaos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> consultar(@PathVariable(value = "id") String idAvaliacao, @RequestHeader(value = "x-access-token") String token) throws EntregaException, GenericException {
		tokenValidate.tokenValidate(token);
		
		AvaliacaoDTO avaliacaoDTO = avaliacaoService.consultar(idAvaliacao);

		return new ResponseEntity<AvaliacaoDTO>(avaliacaoDTO, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<AvaliacaoDTO> incluir(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO, @RequestHeader(value = "x-access-token") String token) throws GenericException {

		tokenValidate.tokenValidateCliente(token, avaliacaoDTO.getIdCliente());
		
		AvaliacaoDTO responseAvaliacaoDTO = avaliacaoService.incluir(avaliacaoDTO);
		return new ResponseEntity<AvaliacaoDTO>(responseAvaliacaoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> atualizar(@PathVariable(value = "id") String id, @RequestBody @Valid AvaliacaoDTO avaliacaoDTODetails, @RequestHeader(value = "x-access-token") String token) throws EntregaException, GenericException {
		tokenValidate.tokenValidate(token);
		
		AvaliacaoDTO avaliacaoDTO = avaliacaoService.atualizar(id, avaliacaoDTODetails);

		return new ResponseEntity<AvaliacaoDTO>(avaliacaoDTO, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AvaliacaoDTO> deletar(@PathVariable(value = "id") String id, @RequestHeader(value = "x-access-token") String token) throws EntregaException, GenericException {
		tokenValidate.tokenValidate(token);
		
		ResponseEntity<AvaliacaoDTO> response = avaliacaoService.deletar(id);
		
		return response;
	}

}