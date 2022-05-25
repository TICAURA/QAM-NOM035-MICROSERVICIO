package com.aura.nom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="NOM035_RESPUESTA")
public @Getter
@Setter
class Respuesta {

    @EmbeddedId
    private RespuestaKey id;

    /**
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCuestionario")
    @JoinColumn(name="idCuestionario", referencedColumnName="idCuestionario")
    @JsonBackReference(value="cuestionario-respuesta")
    private Cuestionario cuestionario;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCuestionario")
    @JoinColumns({
            @JoinColumn(name="idPregunta", referencedColumnName="idPregunta"),
            @JoinColumn(name="idCuestionario", referencedColumnName="idCuestionario")
    })
    @JsonBackReference(value="pregunta-respuesta")
    private Pregunta pregunta;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTipoRespuesta")
    @JoinColumn(name="idTipoRespuesta", referencedColumnName="idTipoRespuesta")
    @JsonBackReference(value="tipoRespuesta-respuesta")
    private TipoRespuesta tipoRespuesta;
     **/
    @Column(name="idTipoRespuesta")
    private int idTipoRespuesta;

}
