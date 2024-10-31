package com.aranoua.referencias_bibliograficas.dto.artigo;


import com.aranoua.referencias_bibliograficas.model.Artigo;


public record ArtigoSimplesDTO(long id, String titulo, String ano_publicacao, String revista) {
    public static ArtigoSimplesDTO buildDTO(Artigo artigo){
        return new ArtigoSimplesDTO(artigo.getId(), artigo.getTitulo(), artigo.getAnoPublicacao(), artigo.getRevista().getNome() );
    }
}
