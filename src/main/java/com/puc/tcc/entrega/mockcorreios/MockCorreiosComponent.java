package com.puc.tcc.entrega.mockcorreios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.puc.tcc.entrega.consts.Constants;
import com.puc.tcc.entrega.exceptions.DeliveryMaintainerException;

@Component
public class MockCorreiosComponent {

	MockCorreiosRepository mock;
	
	@Autowired
	public MockCorreiosComponent(MockCorreiosRepository mock) {
		this.mock = mock;
	}
	
	public void mockar(String codigo, String status) {
		MockCorreios mockCorreios = MockCorreios.builder().codigo(codigo).status(status).build();
	
		mock.save(mockCorreios);
	}
	
	public MockCorreios consultarMockCorreios(String codigo) throws DeliveryMaintainerException {
		
		Optional<MockCorreios> optional = mock.findByCodigo(codigo);
		
		return validarMock(optional);
	}
	
	
	private MockCorreios validarMock(Optional<MockCorreios> optional) throws DeliveryMaintainerException {
		return Optional.ofNullable(optional).get()
		.orElseThrow(() -> new DeliveryMaintainerException(HttpStatus.NOT_FOUND, Constants.ITEM_NOT_FOUND));
	}

}
