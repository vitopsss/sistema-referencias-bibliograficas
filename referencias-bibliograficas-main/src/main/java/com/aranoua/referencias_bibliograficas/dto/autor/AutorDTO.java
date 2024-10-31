package com.aranoua.referencias_bibliograficas.dto.autor;

import com.aranoua.referencias_bibliograficas.dto.afiliacao.AfiliacaoDTO;
import com.aranoua.referencias_bibliograficas.dto.artigo.ArtigoSimplesDTO;
import com.aranoua.referencias_bibliograficas.model.Artigo;
import com.aranoua.referencias_bibliograficas.model.Autor;

import java.util.Set;
import java.util.stream.Collectors;

public record AutorDTO(long id, String nome, AfiliacaoDTO afiliacao, Set<ArtigoSimplesDTO> artigos) {
    public static AutorDTO buildDTO(Autor autor) {
        return new AutorDTO(autor.getId(), autor.getNome(), AfiliacaoDTO.buildDTO(autor.getAfiliacao()), listarArtigosParaDTO(autor.getArtigos()));
    }

    private static Set<ArtigoSimplesDTO> listarArtigosParaDTO(Set<Artigo> artigos){
        return artigos.stream()
                .map(ArtigoSimplesDTO::buildDTO)
                .collect(Collectors.toSet());
    }
}
