package com.alura.floresReyes.forohub.controller;

import com.alura.floresReyes.forohub.model.Curso;
import com.alura.floresReyes.forohub.model.Usuario;
import com.alura.floresReyes.forohub.service.CursoService;
import com.alura.floresReyes.forohub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.alura.floresReyes.forohub.model.Topico;
import com.alura.floresReyes.forohub.model.TopicoDTO;
import com.alura.floresReyes.forohub.service.TopicoService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> registrarTopico(@Valid @RequestBody TopicoDTO topicoDTO) {
        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());

        // Aquí deberías buscar el autor y el curso por sus IDs y setearlos en el tópico.
        // Asumiendo que tienes métodos para encontrar el autor y el curso.
        Usuario autor = usuarioService.findById(topicoDTO.getAutorId());
        Curso curso = cursoService.findById(topicoDTO.getCursoId());

        topico.setAutor(autor);
        topico.setCurso(curso);

        try {
            Topico nuevoTopico = topicoService.registrarTopico(topico);
            return ResponseEntity.ok(nuevoTopico);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        List<Topico> topicos = topicoService.obtenerTodosLosTopicos();
        return ResponseEntity.ok(topicos);
    }

    // Endpoint para obtener un tópico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obtenerTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para actualizar un tópico por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody Topico topicoActualizado) {
        Optional<Topico> optionalTopico = topicoService.obtenerTopicoPorId(id);
        if (optionalTopico.isPresent()) {
            Topico topicoExistente = optionalTopico.get();
            // Actualizar los campos del tópico existente con los nuevos datos
            topicoExistente.setTitulo(topicoActualizado.getTitulo());
            topicoExistente.setMensaje(topicoActualizado.getMensaje());
            topicoExistente.setAutor(topicoActualizado.getAutor());
            topicoExistente.setCurso(topicoActualizado.getCurso());

            // Guardar el tópico actualizado en la base de datos
            Topico topicoGuardado = topicoService.guardarTopico(topicoExistente);
            return ResponseEntity.ok(topicoGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Endpoint para eliminar un tópico por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoService.obtenerTopicoPorId(id);
        if (optionalTopico.isPresent()) {
            topicoService.eliminarTopicoPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}