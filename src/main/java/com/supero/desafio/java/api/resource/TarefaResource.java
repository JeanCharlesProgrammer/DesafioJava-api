package com.supero.desafio.java.api.resource;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.supero.desafio.java.api.model.StatusTarefa;
import com.supero.desafio.java.api.model.Tarefa;
import com.supero.desafio.java.api.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaResource {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping
	public List<Tarefa> listar(@PathParam("status") String status){
			return tarefaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
		
		tarefa.setDataCriacao(LocalDate.now());
		
		if(tarefa.getStatus() == StatusTarefa.EXCLUIDO)
			tarefa.setDataRemocao(LocalDate.now());
		
		if(tarefa.getStatus() == StatusTarefa.CONCLUIDO)
			tarefa.setDataConclusao(LocalDate.now());
		
		Tarefa tarefaSalva = tarefaRepository.save(tarefa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(tarefaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(tarefaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Tarefa> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(codigo);
		return tarefa.isPresent() ? ResponseEntity.ok(tarefa.get()) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long codigo,@Valid @RequestBody Tarefa tarefa ) {
		Optional<Tarefa> tarefaOpcional = tarefaRepository.findById(codigo);
		
		if(tarefaOpcional.isPresent()) {
			Tarefa tarefaSalva = tarefaOpcional.get();
			BeanUtils.copyProperties(tarefa, tarefaSalva, "codigo");
			tarefaSalva.setDataEdicao(LocalDate.now());
			
			if(tarefaSalva.getStatus() == StatusTarefa.EXCLUIDO) {
				tarefaSalva.setDataRemocao(LocalDate.now());
			} else if(tarefaSalva.getDataRemocao() != null) {
				tarefaSalva.setDataRemocao(null);
			}
			
			if(tarefaSalva.getStatus() == StatusTarefa.CONCLUIDO) {
				tarefaSalva.setDataConclusao(LocalDate.now());
			} else if(tarefaSalva.getDataConclusao() != null) {
				tarefaSalva.setDataConclusao(null);
			}
			
			tarefaRepository.save(tarefaSalva);
			
			return ResponseEntity.ok(tarefaSalva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Tarefa> deletarTarefa(@PathVariable Long codigo){
		Optional<Tarefa> tarefaOpcional = tarefaRepository.findById(codigo);
		
		if(tarefaOpcional.isPresent()) {
			Tarefa tarefaSalva = tarefaOpcional.get();
			tarefaSalva.setDataRemocao(LocalDate.now());
			tarefaSalva.setStatus(StatusTarefa.EXCLUIDO);
			
			tarefaRepository.save(tarefaSalva);
			
			return ResponseEntity.ok(tarefaSalva);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
