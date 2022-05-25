package com.aura.nom.repository;

import com.aura.nom.model.Respuesta;
import com.aura.nom.model.RespuestaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, RespuestaKey>
 {
  List<Respuesta> findByIdIdCuestionarioAndIdIdColaborador(int idCuestionario, int idColaborador);
  long countByIdIdCuestionarioAndIdIdColaborador(int idCuestionario, int idColaborador);
}
