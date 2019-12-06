package com.supero.desafio.java.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.supero.desafio.java.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
