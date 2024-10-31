package com.aranoua.referencias_bibliograficas.dto.afiliacao;

import com.aranoua.referencias_bibliograficas.model.Afiliacao;

public record AfiliacaoDTO(long id, String nome, String sigla, String referencia) {
    public static AfiliacaoDTO buildDTO(Afiliacao afiliacao){
        return new AfiliacaoDTO(afiliacao.getId(), afiliacao.getNome(),afiliacao.getSigla(), afiliacao.getReferencia());
    }
}
