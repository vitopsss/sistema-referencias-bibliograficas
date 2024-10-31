package com.aranoua.referencias_bibliograficas.dto.artigo;


import com.aranoua.referencias_bibliograficas.dto.autor.AutorSimplesDTO;
import com.aranoua.referencias_bibliograficas.model.Artigo;
import com.aranoua.referencias_bibliograficas.model.Autor;

import java.util.Set;
import java.util.stream.Collectors;

public record ArtigoDTO(long id, String titulo, String ano_publicacao, String revista, Set<AutorSimplesDTO> autores) {
    public static ArtigoDTO buildDTO(Artigo artigo){
        return new ArtigoDTO(artigo.getId(), artigo.getTitulo(), artigo.getAnoPublicacao(), artigo.getRevista().getNome(), toAutorSimplesDTO(artigo.getAutores()) );
    }

    private static Set<AutorSimplesDTO> toAutorSimplesDTO(Set<Autor> autores){
        return autores.stream()
                .map(AutorSimplesDTO::buildDTO)
                .collect(Collectors.toSet());
    }
}
