package com.supero.desafio.java.api.model;

public enum StatusTarefa {
	
	ABERTO("Em Aberto"),
	ANDAMENTO("Em Andamento"),
	CONCLUIDO("Concluido"),
	EXCLUIDO("Excluido");
	
	private String value;
	
	private StatusTarefa(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
