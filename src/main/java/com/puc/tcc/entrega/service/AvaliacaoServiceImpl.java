package com.puc.tcc.entrega.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.puc.tcc.entrega.consts.Constants;
import com.puc.tcc.entrega.dtos.AvaliacaoDTO;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.model.Avaliacao;
import com.puc.tcc.entrega.repository.AvaliacaoRepository;
import com.puc.tcc.entrega.utils.Util;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

	AvaliacaoRepository avaliacaoRepository;

	@Autowired
	public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
	}

	@Override
	public AvaliacaoDTO consultar(String id) throws EntregaException {
		
		Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
		Avaliacao avaliacao = validarAvaliacao(optional);
		
		AvaliacaoDTO avaliacaoDTO = modelMapper().map(avaliacao, AvaliacaoDTO.class);
		
		return avaliacaoDTO;
	}

	@Override
	public List<AvaliacaoDTO> buscarTodos() {

		List<Avaliacao> avaliacoes = (List<Avaliacao>) avaliacaoRepository.findAll();

		Type listType = new TypeToken<List<AvaliacaoDTO>>(){}.getType();
		List<AvaliacaoDTO> avaliacoesDTO = modelMapper().map(avaliacoes, listType);

		return avaliacoesDTO;
	}

	@Override
	public AvaliacaoDTO incluir(AvaliacaoDTO avaliacaoDTO) {
		Avaliacao avaliacao = modelMapper().map(avaliacaoDTO, Avaliacao.class);
		avaliacao.setDataDaAvaliacao(Util.dataNow());
		
		avaliacaoRepository.save(avaliacao);
		
		return modelMapper().map(avaliacao, AvaliacaoDTO.class);
	}

	@Override
	public AvaliacaoDTO atualizar(String id, AvaliacaoDTO avaliacaoDTODetails) throws EntregaException {
		
		Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
		Avaliacao avaliacao = validarAvaliacao(optional);
		
		Avaliacao avaliacaoDetails = modelMapper().map(avaliacaoDTODetails, Avaliacao.class);

		avaliacao = avaliacao.update(avaliacao, avaliacaoDetails);
		avaliacao.setDataDaAvaliacao(Util.dataNow());

		avaliacaoRepository.save(avaliacao);

		AvaliacaoDTO avaliacaoDTO = modelMapper().map(avaliacao, AvaliacaoDTO.class);

		return avaliacaoDTO;
	}

	@Override
	public ResponseEntity<AvaliacaoDTO> deletar(String id) throws EntregaException {
		
		Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
		validarAvaliacao(optional);
		
		avaliacaoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private Avaliacao validarAvaliacao(Optional<Avaliacao> optional) throws EntregaException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new EntregaException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
}
