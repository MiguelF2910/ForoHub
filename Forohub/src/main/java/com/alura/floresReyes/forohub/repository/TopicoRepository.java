package com.alura.floresReyes.forohub.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.floresReyes.forohub.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

}