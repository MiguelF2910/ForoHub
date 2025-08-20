package com.alura.floresReyes.forohub.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alura.floresReyes.forohub.model.Topico;
import com.alura.floresReyes.forohub.repository.TopicoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(Topico topico) {
        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            throw new IllegalArgumentException("El tópico con el mismo título y mensaje ya existe.");
        }
        return topicoRepository.save(topico);
    }
    @Transactional
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }
    @Transactional
    public Optional<Topico> obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }
    public Topico guardarTopico(Topico topico) {
        return topicoRepository.save(topico);
    }
    public void eliminarTopicoPorId(Long id) {
        topicoRepository.deleteById(id);
    }
}