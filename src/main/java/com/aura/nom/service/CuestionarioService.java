package com.aura.nom.service;

import com.aura.nom.dto.CuestionarioDto;
import com.aura.nom.dto.PreguntaDTO;
import com.aura.nom.dto.RespuestaDTO;
import com.aura.nom.model.*;
import com.aura.nom.repository.CuestionarioRepository;
import com.aura.nom.repository.PreguntaRepository;
import com.aura.nom.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuestionarioService {

    @Autowired
    private CuestionarioRepository cuestionarioRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;



    public List<CuestionarioDto> getCuestionarios(int idColaborador){


        List<CuestionarioDto> cuestionarioDtos = new ArrayList<>();
        List<Cuestionario> cuestionarioList = cuestionarioRepository.findAll();
        for (Cuestionario cuestionario:cuestionarioList) {
            CuestionarioDto cuestionarioDto = new CuestionarioDto();
            cuestionarioDto.setIdCuestionario(cuestionario.getIdCuestionario());
            cuestionarioDto.setNombre(cuestionario.getNombre());
            cuestionarioDto.setDescripcion(cuestionario.getDescripcion());
            cuestionarioDto.setTotal(preguntaRepository.countByIdIdCuestionario(cuestionario.getIdCuestionario()));
            cuestionarioDto.setRespondidas(respuestaRepository.countByIdIdCuestionarioAndIdIdColaborador(cuestionario.getIdCuestionario(),idColaborador));
            cuestionarioDtos.add(cuestionarioDto);
        }
        return cuestionarioDtos;
    }

    public List<PreguntaDTO> getPreguntas(int idCuestionario){
        List<Pregunta> preguntas = preguntaRepository.findByIdIdCuestionario(idCuestionario);
        List<PreguntaDTO> preguntaDTOS = new ArrayList<>();
        for (Pregunta pregunta: preguntas) {
            preguntaDTOS.add(PreguntaDTO.build(pregunta));
        }
        return preguntaDTOS;
    }

    public void postRespuestas(int idColaborador, List<RespuestaDTO> respuestaDTOS){
        List<Respuesta> respuestas = new ArrayList<>();
        for (RespuestaDTO respuestaDTO: respuestaDTOS) {
            Respuesta respuesta = new Respuesta();
            respuesta.setId(new RespuestaKey(respuestaDTO.getIdCuestionario(),idColaborador,respuestaDTO.getIdPregunta()));
            respuesta.setIdTipoRespuesta(respuestaDTO.getIdTipoPregunta());
            respuestas.add(respuesta);
        }


        respuestaRepository.saveAllAndFlush(respuestas);

    }

    public void postRespuesta(int idColaborador, RespuestaDTO respuestaDTO){

            Respuesta respuesta = new Respuesta();
            respuesta.setId(new RespuestaKey(respuestaDTO.getIdCuestionario(),idColaborador,respuestaDTO.getIdPregunta()));
            respuesta.setIdTipoRespuesta(respuestaDTO.getIdTipoPregunta());

        respuestaRepository.saveAndFlush(respuesta);

    }

    public List<RespuestaDTO> getRespuestas(int idColaborador, int idCuestionario){


        List<RespuestaDTO> respuestaDTOS = new ArrayList<>();
       List<Respuesta> respuestas = respuestaRepository.findByIdIdCuestionarioAndIdIdColaborador(idCuestionario,idColaborador);
        for (Respuesta res: respuestas) {
            RespuestaDTO respuestaDTO = new RespuestaDTO();
            respuestaDTO.setIdCuestionario(idCuestionario);
            respuestaDTO.setIdPregunta(res.getId().getIdPregunta());
            respuestaDTO.setIdTipoPregunta(res.getIdTipoRespuesta());
            respuestaDTOS.add(respuestaDTO);
        }
        return respuestaDTOS;

    }

}
