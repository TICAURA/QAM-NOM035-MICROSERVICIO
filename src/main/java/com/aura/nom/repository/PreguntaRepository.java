package com.aura.nom.repository;

import com.aura.nom.model.Pregunta;
import com.aura.nom.model.PreguntaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, PreguntaKey>
 {
    List<Pregunta> findByIdIdCuestionario(int idCuestionario);
     long countByIdIdCuestionario(int idCuestionario);
}
