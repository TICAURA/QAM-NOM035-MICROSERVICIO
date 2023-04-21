package com.aura.nom.dto;

import lombok.Data;

public @Data class CuestionarioDto {
    private int idCuestionario;
    private String nombre;
    private String descripcion;
    private long total;
    private long respondidas;
    private boolean completo;
}
