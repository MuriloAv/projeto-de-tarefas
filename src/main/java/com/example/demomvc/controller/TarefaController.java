package com.example.demomvc.controller;

import com.example.demomvc.entity.Tarefa;
import com.example.demomvc.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController // MUDANÇA IMPORTANTE: de @Controller para @RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa) {
        service.salvar(tarefa);
        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTodas() {
        List<Tarefa> tarefas = service.buscaTodos();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable("id") Long id) {
        try {
            Tarefa tarefa = service.buscarPorId(id);
            return ResponseEntity.ok(tarefa);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Tarefa tarefa) {
        tarefa.setId(id);
        try {
            service.editar(tarefa);
            return ResponseEntity.ok(tarefa);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}