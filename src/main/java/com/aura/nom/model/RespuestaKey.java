package com.aura.nom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public @Getter @Setter class RespuestaKey implements Serializable {

    @Column(name = "idCuestionario")
    private int idCuestionario;
    @Column(name = "idColaborador")
    private int idColaborador;
    @Column(name = "idPregunta")
    private int idPregunta;

    public RespuestaKey() {
    }

    public RespuestaKey(int idCuestionario, int idColaborador, int idPregunta) {
        this.idCuestionario = idCuestionario;
        this.idColaborador = idColaborador;
        this.idPregunta = idPregunta;
    }
}
