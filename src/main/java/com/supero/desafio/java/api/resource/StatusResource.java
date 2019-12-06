package com.supero.desafio.java.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supero.desafio.java.api.model.StatusModel;

@RestController
@RequestMapping("/status")
public class StatusResource {
	
	@GetMapping
	public List<StatusModel> listar(){
		return StatusModel.getListaStatus();
	}

}
