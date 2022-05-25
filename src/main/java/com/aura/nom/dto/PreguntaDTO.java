package com.aura.nom.dto;

import com.aura.nom.model.Pregunta;
import lombok.Data;

public @Data class PreguntaDTO {

    private int idPregunta;
    private int idCuestionario;

    private String pregunta;
    private boolean isDescendente; //Si es cierto, la respuesta siempre = 0 y nunca = 4, si es falso, siempre = 4 y nunca = 0
    private boolean servicioAlCliente;
    private boolean jefe;


    public static PreguntaDTO build(Pregunta pregunta){
        PreguntaDTO preguntaDTO = new PreguntaDTO();
        preguntaDTO.setIdPregunta(pregunta.getId().getIdPregunta());
        preguntaDTO.setIdCuestionario(pregunta.getId().getIdCuestionario());
        preguntaDTO.setPregunta(pregunta.getPregunta());
        preguntaDTO.setDescendente(pregunta.isDescendente());
        preguntaDTO.setServicioAlCliente(pregunta.isServicioAlCliente());
        preguntaDTO.setJefe(pregunta.isJefe());
        return preguntaDTO;
    }
}
