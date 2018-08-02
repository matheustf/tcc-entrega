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
import com.puc.tcc.entrega.dtos.BlocoDTO;
import com.puc.tcc.entrega.exceptions.VendaException;
import com.puc.tcc.entrega.model.Bloco;
import com.puc.tcc.entrega.repository.BlocoRepository;

@Service
public class BlocoServiceImpl implements BlocoService {

	BlocoRepository blocoRepository;

	@Autowired
	public BlocoServiceImpl(BlocoRepository blocoRepository) {
		this.blocoRepository = blocoRepository;
	}

	@Override
	public BlocoDTO consultar(String id) throws VendaException {
		
		Optional<Bloco> optional = blocoRepository.findById(id);
		Bloco bloco = validarBloco(optional);
		
		BlocoDTO blocoDTO = modelMapper().map(bloco, BlocoDTO.class);
		
		return blocoDTO;
	}

	@Override
	public List<BlocoDTO> buscarTodos() {

		List<Bloco> blocos = (List<Bloco>) blocoRepository.findAll();

		Type listType = new TypeToken<List<BlocoDTO>>(){}.getType();
		List<BlocoDTO> blocosDTO = modelMapper().map(blocos, listType);

		return blocosDTO;
	}

	@Override
	public BlocoDTO incluir(BlocoDTO blocoDTO) {
		Bloco bloco = modelMapper().map(blocoDTO, Bloco.class);

		blocoRepository.save(bloco);
		
		return modelMapper().map(bloco, BlocoDTO.class);
	}

	@Override
	public BlocoDTO atualizar(String id, BlocoDTO blocoDTODetails) throws VendaException {
		
		Optional<Bloco> optional = blocoRepository.findById(id);
		Bloco bloco = validarBloco(optional);
		
		Bloco blocoDetails = modelMapper().map(blocoDTODetails, Bloco.class);

		bloco = bloco.update(bloco, blocoDetails);

		blocoRepository.save(bloco);

		BlocoDTO blocoDTO = modelMapper().map(bloco, BlocoDTO.class);

		return blocoDTO;
	}

	@Override
	public ResponseEntity<BlocoDTO> deletar(String id) throws VendaException {
		
		Optional<Bloco> optional = blocoRepository.findById(id);
		validarBloco(optional);
		
		blocoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private Bloco validarBloco(Optional<Bloco> optional) throws VendaException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new VendaException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
}
