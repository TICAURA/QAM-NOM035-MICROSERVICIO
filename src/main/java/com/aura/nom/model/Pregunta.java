package com.aura.nom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
 @Table(name="NOM035_PREGUNTA")
 public @Getter
 @Setter
 class Pregunta {

 @EmbeddedId
 private PreguntaKey id;
 @Column(name = "pregunta")
 private String pregunta;

 @Column(name = "valorDescendente")
 private boolean isDescendente; //Si es cierto, la respuesta siempre = 0 y nunca = 4, si es falso, siempre = 4 y nunca = 0
 @Column(name = "servicioAlCliente")
 private boolean servicioAlCliente;
 @Column(name = "jefe")
 private boolean jefe;

 @ManyToOne(fetch = FetchType.LAZY)
 @MapsId("idCuestionario")
 @JoinColumns({
 @JoinColumn(name="idCategoria", referencedColumnName="idCategoria"),
 @JoinColumn(name="idCuestionario", referencedColumnName="idCuestionario")
 })
 @JsonBackReference(value="categoria-pregunta")
 private Categoria categoria;



  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("idCuestionario")
  @JoinColumn(name="idCuestionario", referencedColumnName="idCuestionario")
  @JsonBackReference(value="cuestionario-pregunta")
  private Cuestionario cuestionario;

  /**
   *
   * @ManyToOne(fetch = FetchType.LAZY)
   *  @MapsId("idCuestionario")
   *  @JoinColumns({
   *  @JoinColumn(name="idDominio", referencedColumnName="idDominio"),
   *  @JoinColumn(name="idCuestionario", referencedColumnName="idCuestionario")
   *  })
   *  @JsonBackReference(value="dominio-pregunta")
   *  private Dominio dominio;
 @OneToMany(fetch = FetchType.LAZY,mappedBy = "pregunta")
 @JsonBackReference(value="pregunta-respuesta")
 Set<Respuesta> respuestas;**/

 }
