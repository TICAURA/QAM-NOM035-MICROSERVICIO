package com.aura.nom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public @Getter
@Setter
class CuestionarioXPersonaKey implements Serializable {
    @Column(name = "idColaborador")
    private int idColaborador;
    @Column(name = "idCuestionario")
    private int idCuestionario;

    public CuestionarioXPersonaKey(int idColaborador, int idCuestionario) {
        this.idColaborador = idColaborador;
        this.idCuestionario = idCuestionario;
    }

    public CuestionarioXPersonaKey() {
    }
}
