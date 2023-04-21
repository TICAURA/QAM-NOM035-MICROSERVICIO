package com.aura.nom.dto;

import lombok.Data;

public @Data class CuestionarioXPersonaDto {

    private int idCuestionario;

    private int typeSacOrJefe; //0 Sac 1 Jefe
    private int valueSacOrJefe; //0 SIN RESPONDER, 1 Sí, 2 no

}
