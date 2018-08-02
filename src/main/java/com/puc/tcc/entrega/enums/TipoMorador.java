package com.puc.tcc.entrega.enums;

public enum TipoMorador {

	DONO, LOCATARIO, RESIDENTE;

	private String descricao;

	private TipoMorador() {
	}
	
	private TipoMorador(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return this.descricao;
	}
}
