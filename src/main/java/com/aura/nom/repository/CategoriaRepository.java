package com.aura.nom.repository;

import com.aura.nom.model.Categoria;
import com.aura.nom.model.CategoriaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, CategoriaKey> {
}
