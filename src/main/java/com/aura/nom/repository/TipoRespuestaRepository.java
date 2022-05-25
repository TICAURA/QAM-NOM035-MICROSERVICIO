package com.aura.nom.repository;

import com.aura.nom.model.TipoRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRespuestaRepository extends JpaRepository<TipoRespuesta,Integer> {
}
