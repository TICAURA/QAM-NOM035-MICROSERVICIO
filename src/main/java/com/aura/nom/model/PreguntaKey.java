package com.aura.nom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public @Getter
@Setter
class PreguntaKey implements Serializable {

    @Column(name = "idPregunta")
    private int idPregunta;
    @Column(name = "idCuestionario")
    private int idCuestionario;

    public PreguntaKey(int idPregunta, int idCuestionario) {
        this.idPregunta = idPregunta;
        this.idCuestionario = idCuestionario;
    }

    public PreguntaKey() {
    }
}
