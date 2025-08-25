package com.example.demomvc.service;

import com.example.demomvc.dao.TarefaRepository; // Import corrigido
import com.example.demomvc.entity.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Override
    @Transactional
    public void salvar(Tarefa tarefa) {
        repository.save(tarefa);
    }

    @Override
    @Transactional
    public void editar(Tarefa tarefa) {
        if (!repository.existsById(tarefa.getId())) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + tarefa.getId());
        }
        repository.save(tarefa);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Tarefa não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarefa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada com o ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarefa> buscaTodos() {
        return repository.findAll();
    }
}