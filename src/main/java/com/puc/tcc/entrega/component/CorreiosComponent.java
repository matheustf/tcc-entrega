package com.puc.tcc.entrega.component;

import org.springframework.stereotype.Component;

import com.puc.tcc.entrega.enums.StatusDaEntrega;
import com.puc.tcc.entrega.exceptions.DeliveryMaintainerException;
import com.puc.tcc.entrega.mockcorreios.MockCorreios;
import com.puc.tcc.entrega.mockcorreios.MockCorreiosComponent;

@Component
public class CorreiosComponent {
	
	MockCorreiosComponent mock;
	
	public CorreiosComponent(MockCorreiosComponent mock) {
		this.mock = mock;
	}

	public void mockar(String codigo, String status) {
		mock.mockar(codigo, status);
	}

	public StatusDaEntrega postCorreio(String codigoDeRastreio) throws DeliveryMaintainerException {
		MockCorreios mockCorreios = mock.consultarMockCorreios(codigoDeRastreio);
		return StatusDaEntrega.valueOf(mockCorreios.getStatus());
	}
	
}
