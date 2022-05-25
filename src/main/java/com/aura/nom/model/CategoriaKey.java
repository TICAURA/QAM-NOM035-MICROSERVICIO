package com.aura.nom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public @Getter
@Setter
class CategoriaKey implements Serializable {
    @Column(name = "idCategoria")
    private int idCategoria;
    @Column(name = "idCuestionario")
    private int idCuestionario;

    public CategoriaKey(int idCategoria, int idCuestionario) {
        this.idCategoria = idCategoria;
        this.idCuestionario = idCuestionario;
    }

    public CategoriaKey() {
    }
}
