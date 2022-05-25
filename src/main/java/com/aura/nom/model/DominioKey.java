package com.aura.nom.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public @Getter
@Setter
class DominioKey implements Serializable {

    @Column(name = "idDominio")
    private int idDominio;
    @Column(name = "idCuestionario")
    private int idCuestionario;

    public DominioKey(int idDominio, int idCuestionario) {
        this.idDominio = idDominio;
        this.idCuestionario = idCuestionario;
    }

    public DominioKey() {
    }
}
