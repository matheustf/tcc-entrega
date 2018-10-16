package com.puc.tcc.entrega.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.puc.tcc.entrega.component.CorreiosComponent;
import com.puc.tcc.entrega.component.SendEmailComponent;
import com.puc.tcc.entrega.consts.Constants;
import com.puc.tcc.entrega.enums.StatusDaEntrega;
import com.puc.tcc.entrega.exceptions.DeliveryMaintainerException;
import com.puc.tcc.entrega.model.Entrega;
import com.puc.tcc.entrega.model.HistoricoDeEntrega;
import com.puc.tcc.entrega.repository.EntregaRepository;
import com.puc.tcc.entrega.utils.Util;

@Service
public class DeliveryMaintainerServiceImpl implements DeliveryMaintainerService {

	EntregaRepository entregaRepository;

	CorreiosComponent correiosComponent;

	SendEmailComponent sendEmailComponent;

	@Autowired
	public DeliveryMaintainerServiceImpl(EntregaRepository entregaRepository, CorreiosComponent correiosComponent,
			SendEmailComponent sendEmailComponent) {
		this.entregaRepository = entregaRepository;
		this.correiosComponent = correiosComponent;
		this.sendEmailComponent = sendEmailComponent;
	}

	private Entrega consultar(String codigoDeRastreio) throws DeliveryMaintainerException {

		Optional<Entrega> optional = entregaRepository.findByCodigoDeRastreio(codigoDeRastreio);

		return validarEntrega(optional);
	}

	@Override
	public void checarEntrega(String codigoDeRastreio, StatusDaEntrega statusDaEntrega)
			throws DeliveryMaintainerException {

		StatusDaEntrega statusEntregaCorreio = correiosComponent.postCorreio(codigoDeRastreio);

		if (statusEntregaCorreio != statusDaEntrega) {
			updateStatusEntrega(codigoDeRastreio, statusEntregaCorreio);
		}

	}

	private void updateStatusEntrega(String codigoDeRastreio, StatusDaEntrega statusDaEntrega)
			throws DeliveryMaintainerException {
		Entrega entrega = consultar(codigoDeRastreio);
		System.out.println("Atualizando Status da Entrega");
		// TODO Criar maquina de estado para validar caminho correto

		HistoricoDeEntrega historicoDeEntrega = HistoricoDeEntrega.builder().data(Util.dataNow())
				.statusDaEntrega(statusDaEntrega).build();

		entrega.addHistoricoDeEntrega(historicoDeEntrega);
		entrega.setStatusDaEntrega(statusDaEntrega);

		entregaRepository.save(entrega);
		sendEmailComponent.sendEmail(entrega);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	private Entrega validarEntrega(Optional<Entrega> optional) throws DeliveryMaintainerException {
		return Optional.ofNullable(optional).get()
				.orElseThrow(() -> new DeliveryMaintainerException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}
}
