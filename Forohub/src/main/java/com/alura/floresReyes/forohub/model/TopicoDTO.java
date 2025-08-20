package com.alura.floresReyes.forohub.model;

import javax.validation.constraints.*;
import javax.validation.constraints.NotNull;

public class TopicoDTO {

    @NotEmpty(message = "El título es obligatorio")
    private String titulo;

    @NotEmpty(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El autor es obligatorio")
    private Long autorId;

    @NotNull(message = "El curso es obligatorio")
    private Long cursoId;

    // Getters y setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
