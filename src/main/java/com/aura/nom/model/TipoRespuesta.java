package com.aura.nom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="NOM035_TIPO_RESPUESTA")
public @Getter @Setter class TipoRespuesta {
    @Id
    @GeneratedValue
    @Column(name = "idTipoRespuesta")
    private int idTipoRespuesta;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    /**
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "tipoRespuesta")
    @JsonBackReference(value="tipoRespuesta-respuesta")
    Set<Respuesta> respuestas;**/
}
