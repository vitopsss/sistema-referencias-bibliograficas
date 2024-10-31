package com.aranoua.referencias_bibliograficas.dto.revista_cientifica;

import com.aranoua.referencias_bibliograficas.dto.artigo.ArtigoDTO;
import com.aranoua.referencias_bibliograficas.model.Artigo;
import com.aranoua.referencias_bibliograficas.model.RevistaCientifica;

import java.util.Set;
import java.util.stream.Collectors;

public record RevistaDTO(long id, String nome, String ISSN, Set<ArtigoDTO> artigosPublicados){
    public static RevistaDTO buildDTO(RevistaCientifica revista){
        return new RevistaDTO(revista.getId(), revista.getNome(), revista.getISSN(), listarArtigosParaDTO(revista.getArtigosPublicados()));
    }

    private static Set<ArtigoDTO> listarArtigosParaDTO(Set<Artigo> artigos){
        return artigos.stream()
                .map(ArtigoDTO::buildDTO)
                .collect(Collectors.toSet());
    }
}
