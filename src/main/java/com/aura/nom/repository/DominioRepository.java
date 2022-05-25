package com.aura.nom.repository;

import com.aura.nom.model.Dominio;
import com.aura.nom.model.DominioKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DominioRepository extends JpaRepository<Dominio, DominioKey> {
}
