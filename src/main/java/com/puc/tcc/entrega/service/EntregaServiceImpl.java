package com.puc.tcc.entrega.service;

import java.lang.reflect.Type;
import java.util.Arrays;
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
import com.puc.tcc.entrega.dtos.EntregaDTO;
import com.puc.tcc.entrega.enums.StatusDaEntrega;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.model.Entrega;
import com.puc.tcc.entrega.model.HistoricoDeEntrega;
import com.puc.tcc.entrega.repository.EntregaRepository;
import com.puc.tcc.entrega.utils.Util;

@Service
public class EntregaServiceImpl implements EntregaService {

	EntregaRepository entregaRepository;

	@Autowired
	public EntregaServiceImpl(EntregaRepository entregaRepository) {
		this.entregaRepository = entregaRepository;
	}

	@Override
	public EntregaDTO consultar(String id) throws EntregaException {
		
		Optional<Entrega> optional = entregaRepository.findById(id);
		Entrega entrega = validarEntrega(optional);
		
		EntregaDTO entregaDTO = modelMapper().map(entrega, EntregaDTO.class);
		
		return entregaDTO;
	}

	@Override
	public List<EntregaDTO> buscarTodos() {

		List<Entrega> entregas = (List<Entrega>) entregaRepository.findAll();

		Type listType = new TypeToken<List<EntregaDTO>>(){}.getType();
		List<EntregaDTO> entregasDTO = modelMapper().map(entregas, listType);

		return entregasDTO;
	}

	@Override
	public EntregaDTO incluir(EntregaDTO entregaDTO) {
		Entrega entrega = modelMapper().map(entregaDTO, Entrega.class);
		
		StatusDaEntrega statusDaEntrega = StatusDaEntrega.valueOf(entregaDTO.getStatusDaEntrega());
		
		HistoricoDeEntrega historico = HistoricoDeEntrega
				.builder()
				.data(Util.dataNow())
				.statusDaEntrega(statusDaEntrega)
				.build();

		entrega.setHistoricoDeEntrega(Arrays.asList(historico));
		entrega.setStatusDaEntrega(statusDaEntrega);
		
		entregaRepository.save(entrega);
		
		return modelMapper().map(entrega, EntregaDTO.class);
	}

	@Override
	public EntregaDTO atualizar(String id, EntregaDTO entregaDTODetails) throws EntregaException {
		
		Optional<Entrega> optional = entregaRepository.findById(id);
		Entrega entrega = validarEntrega(optional);
		
		Entrega entregaDetails = modelMapper().map(entregaDTODetails, Entrega.class);

		entrega = entrega.update(entrega, entregaDetails);

		entregaRepository.save(entrega);

		EntregaDTO entregaDTO = modelMapper().map(entrega, EntregaDTO.class);

		return entregaDTO;
	}

	@Override
	public ResponseEntity<EntregaDTO> deletar(String id) throws EntregaException {
		
		Optional<Entrega> optional = entregaRepository.findById(id);
		validarEntrega(optional);
		
		entregaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private Entrega validarEntrega(Optional<Entrega> optional) throws EntregaException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new EntregaException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
}
