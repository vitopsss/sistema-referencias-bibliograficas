package com.aranoua.referencias_bibliograficas.repository;

import com.aranoua.referencias_bibliograficas.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeAndAfiliacao_Referencia(String nome, String referencia);
}
