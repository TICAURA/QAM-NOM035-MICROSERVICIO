package com.aura.nom.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="nom035_cuestionarioxcolaborador")
public @Data class CuestionarioXPersona {
    @EmbeddedId
    private CuestionarioXPersonaKey id;

    @Column(name="completo")
    private boolean completo;

    @Column(name="es_Jefe")
    private int jefe;
    @Column(name="es_sac")
    private int sac;

    @Column(name="activo")
    private boolean activo;

    @Column(name="fecha_creacion")
    private Date fechaCreacion;
}
