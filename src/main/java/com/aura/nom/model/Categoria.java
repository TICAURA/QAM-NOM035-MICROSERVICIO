package com.aura.nom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="NOM035_CATEGORIA")
public @Getter @Setter class Categoria {

    @EmbeddedId
    private CategoriaKey id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "riesgoNulo")
    private int riesgoNulo;
    @Column(name = "riesgoBajo")
    private int riesgoBajo;
    @Column(name = "riesgoMedio")
    private int riesgoMedio;
    @Column(name = "riesgoAlto")
    private int riesgoAlto;
    @Column(name = "riesgoMuyAlto")
    private int riesgoMuyAlto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCuestionario")
    @JoinColumn(name="idCuestionario", referencedColumnName="idCuestionario")
    @JsonBackReference(value="cuestionario-categoria")
    private Cuestionario cuestionario;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categoria")
    @JsonBackReference(value="categoria-pregunta")
    Set<Pregunta> preguntas;
}
