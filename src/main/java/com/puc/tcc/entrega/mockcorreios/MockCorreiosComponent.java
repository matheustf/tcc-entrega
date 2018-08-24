package com.puc.tcc.entrega.mockcorreios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	

}
