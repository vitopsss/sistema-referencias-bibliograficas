package com.aranoua.referencias_bibliograficas.dto.autor;

import com.aranoua.referencias_bibliograficas.model.Afiliacao;
import com.aranoua.referencias_bibliograficas.model.Autor;
import com.aranoua.referencias_bibliograficas.repository.AfiliacaoRepository;
import com.aranoua.referencias_bibliograficas.service.exception.ObjectNotFoundException;
import jakarta.validation.constraints.NotBlank;


public record AutorCreateDTO(
        @NotBlank(message = "nome não pode ser vazio.")
        String nome,
        @NotBlank(message = "referencia não pode ser vazio.")
        String referencia) {
    public Autor toAutorEntity(AfiliacaoRepository repository){
        Afiliacao afiliacao = repository.findByReferencia(referencia)
                .orElseThrow(() -> new ObjectNotFoundException("Afiliacao nao encontrada SIGLA:" + referencia));
        return new Autor(nome, afiliacao);
    }
}
