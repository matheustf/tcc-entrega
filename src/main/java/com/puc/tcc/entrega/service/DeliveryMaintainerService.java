package com.puc.tcc.entrega.service;

import com.puc.tcc.entrega.enums.StatusDaEntrega;
import com.puc.tcc.entrega.exceptions.DeliveryMaintainerException;

public interface DeliveryMaintainerService {
	
	void checarEntrega(String codigoDeRastreio, StatusDaEntrega statusDaEntrega) throws DeliveryMaintainerException;
	
}
