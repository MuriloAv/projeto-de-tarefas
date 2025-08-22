package com.example.demomvc.service;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demomvc.entity.Tarefa;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class TarefaTest {
@Autowired

	private TarefaServiceImpl ts;
@Test
@Order(1)
public void insere() {
	Tarefa tarefa = new Tarefa();
	tarefa.setNome("revisao");
	tarefa.setDataEntrega(LocalDate.of(2024, 06, 10));
	tarefa.setResponsavel("Murilo");
	
	ts.salvar(tarefa);
}
@Test
@Order(2)
	public void pesquisaPeloId() {
		Tarefa tarefa = ts.buscarPorId(2L);
				System.out.println(tarefa);
	}
	
	@Test
@Order(3)
	public void atualiza() {
		   Tarefa tarefa = ts.buscarPorId(2L);
		  tarefa.setNome("Review");
		   ts.editar(tarefa);
	   }
	@Test
	@Order(4)
	
	public void remove() {
		 Tarefa tarefa = ts.buscarPorId(2L);
		 
		   ts.excluir(tarefa.getId());
		 
		
	}

	
}

	