package com.aranoua.referencias_bibliograficas.repository;

import com.aranoua.referencias_bibliograficas.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
}
