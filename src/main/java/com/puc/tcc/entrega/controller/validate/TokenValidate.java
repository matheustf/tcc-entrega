package com.puc.tcc.entrega.controller.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.puc.tcc.entrega.consts.Constants;
import com.puc.tcc.entrega.exceptions.EntregaException;
import com.puc.tcc.entrega.utils.Util;

@Component
public class TokenValidate {

	public void tokenValidate(String token) throws EntregaException {
		String idCadastro = Util.getPagameterToken(token, "idCadastro");

		if (StringUtils.isBlank(idCadastro)) {
			throw new EntregaException(HttpStatus.UNAUTHORIZED, Constants.UNAUTHORIZED);
		}

	}
	
	public void tokenSimpleValidate(String token) throws EntregaException {
		if (StringUtils.isBlank(token)) {
			throw new EntregaException(HttpStatus.UNAUTHORIZED, Constants.UNAUTHORIZED);
		}
	}

	public void tokenValidateCliente(String token, String idCliente) throws EntregaException {
		String idCadastro = Util.getPagameterToken(token, "idCadastro");

		if (!isTokenCorreto(idCadastro, idCliente)) {
			throw new EntregaException(HttpStatus.UNAUTHORIZED, Constants.UNAUTHORIZED);
		}

	}

	private boolean isTokenCorreto(String idCadastro, String idCliente) {
		return StringUtils.isNotBlank(idCadastro) && idCadastro.equals(idCliente);
	}

}
