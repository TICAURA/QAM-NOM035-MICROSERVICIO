package com.aura.nom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="NOM035_CUESTIONARIO")
public @Getter @Setter class Cuestionario {
    @Id
    @GeneratedValue
    @Column(name = "idCuestionario")
    private int idCuestionario;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cuestionario")
    @JsonBackReference(value="cuestionario-categoria")
    Set<Categoria> categorias;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cuestionario")
    @JsonBackReference(value="cuestionario-dominio")
    Set<Dominio> dominios;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cuestionario")
    @JsonBackReference(value="cuestionario-pregunta")
    Set<Pregunta> preguntas;
    /**
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cuestionario")
    @JsonBackReference(value="cuestionario-respuesta")
    Set<Respuesta> respuestas;**/



}
