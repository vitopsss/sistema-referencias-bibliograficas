package com.aranoua.referencias_bibliograficas.dto.autor;

import com.aranoua.referencias_bibliograficas.dto.afiliacao.AfiliacaoDTO;
import com.aranoua.referencias_bibliograficas.model.Autor;


public record AutorSimplesDTO(long id, String nome, AfiliacaoDTO afiliacao) {
    public static AutorSimplesDTO buildDTO(Autor autor) {
        return new AutorSimplesDTO(autor.getId(), autor.getNome(), AfiliacaoDTO.buildDTO(autor.getAfiliacao()));
    }
}
