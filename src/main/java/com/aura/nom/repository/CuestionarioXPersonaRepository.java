package com.aura.nom.repository;

import com.aura.nom.model.Cuestionario;
import com.aura.nom.model.CuestionarioXPersona;
import com.aura.nom.model.CuestionarioXPersonaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuestionarioXPersonaRepository extends JpaRepository<CuestionarioXPersona, CuestionarioXPersonaKey> {
}
