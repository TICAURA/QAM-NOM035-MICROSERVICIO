package com.aura.nom.controller;

import com.aura.nom.dto.CuestionarioDto;
import com.aura.nom.dto.CuestionarioXPersonaDto;
import com.aura.nom.dto.PreguntaDTO;
import com.aura.nom.dto.RespuestaDTO;
import com.aura.nom.exception.BusinessException;
import com.aura.nom.model.Cuestionario;
import com.aura.nom.model.CuestionarioXPersona;
import com.aura.nom.model.Pregunta;
import com.aura.nom.service.CuestionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuestionario")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CuestionarioController {
    @Autowired
    private CuestionarioService cuestionarioService;

    Logger logger = LoggerFactory.getLogger(CuestionarioController.class);

    @GetMapping("")
    public ResponseEntity<Object> getServicios(@RequestAttribute("idColaborador") int idColaborador){
        try {
            List<CuestionarioDto> cuestionarios = cuestionarioService.getCuestionarios(idColaborador);
            return new ResponseEntity<Object>(cuestionarios, HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al traer cuestionarios:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("update-sac-jefe")
    public ResponseEntity<Object> updateSacOrJefe(@RequestAttribute("idColaborador") int idColaborador, @RequestBody CuestionarioXPersonaDto cuestionarioXPersonaDto){
        try {
            cuestionarioService.updateSacOrJefe(cuestionarioXPersonaDto,idColaborador);
            return new ResponseEntity<Object>( HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al traer cuestionarios:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sac-jefe/{idCuestionario}")
    public ResponseEntity<Object> getSacOrJefe(@RequestAttribute("idColaborador") int idColaborador,@PathVariable("idCuestionario") int idCuestionario){
        try {
            CuestionarioXPersona cuestionarioXPersona = cuestionarioService.getCuestionarioXPersona(idColaborador,idCuestionario);
            return new ResponseEntity<Object>(cuestionarioXPersona, HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al buscar attributos sac o jefe:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/completar/{idCuestionario}")
    public ResponseEntity<Object> getCompletar(@RequestAttribute("idColaborador") int idColaborador,@PathVariable("idCuestionario") int idCuestionario){
        try {
            cuestionarioService.updateCompletarCuestionario(idCuestionario,idColaborador);
            return new ResponseEntity<Object>( HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al buscar attributos sac o jefe:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/preguntas/{idCuestionario}")
    public ResponseEntity<Object> getPreguntas(@PathVariable("idCuestionario") int idCuestionario){
        try {
            List<PreguntaDTO> preguntas = cuestionarioService.getPreguntas(idCuestionario);
            return new ResponseEntity<Object>(preguntas, HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al buscar preguntas:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/respuestas/{idCuestionario}")
    public ResponseEntity<Object> getRespuestas(@RequestAttribute("idColaborador") int idColaborador,@PathVariable("idCuestionario") int idCuestionario){
        try {
            List<RespuestaDTO> respuestaDTOS =cuestionarioService.getRespuestas(idColaborador,idCuestionario);
            return new ResponseEntity<Object>(respuestaDTOS, HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al guardar respuestas:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/respuestas")
    public ResponseEntity<Object> postRespuestas(@RequestAttribute("idColaborador") int idColaborador,@RequestBody List<RespuestaDTO> respuestaDTOS){
        try {
            cuestionarioService.postRespuestas(idColaborador, respuestaDTOS);
            return new ResponseEntity<Object>("{}", HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al guardar respuestas:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/respuesta")
    public ResponseEntity<Object> postRespuesta(@RequestAttribute("idColaborador") int idColaborador,@RequestBody RespuestaDTO respuestaDTO){
        try {
            cuestionarioService.postRespuesta(idColaborador, respuestaDTO);
            return new ResponseEntity<Object>("{}", HttpStatus.OK);
        }catch (Exception e){
            logger.error("error al guardar respuestas:"+e.getMessage(),e);
            return new ResponseEntity<Object>("{\"error\":\"Servicio no disponible.\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
