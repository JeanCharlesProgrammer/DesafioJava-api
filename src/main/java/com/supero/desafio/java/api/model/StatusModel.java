package com.supero.desafio.java.api.model;

import java.util.ArrayList;
import java.util.List;

public class StatusModel {
	
	private String id;
	private String descricao;

	public StatusModel(String id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	
	public static ArrayList<StatusModel> getListaStatus(){
		StatusTarefa[] status = StatusTarefa.values();
		List<StatusModel> listStatus = new ArrayList<StatusModel>();
		
		for (int i = 0; i < status.length; i++)
			listStatus.add(new StatusModel(status[i].toString(), status[i].getValue()));
		
		
		
		return (ArrayList<StatusModel>) listStatus;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
