package com.aranoua.referencias_bibliograficas.dto.afiliacao;

import com.aranoua.referencias_bibliograficas.model.Afiliacao;
import jakarta.validation.constraints.NotBlank;

public record AfiliacaoCreateDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @NotBlank(message = "Sigla não pode ser vazia")
        String sigla,
        @NotBlank(message = "referencia não pode ser vazia")
        String referencia) {
    public Afiliacao toAfiliacaoEntity(){
        return new Afiliacao(nome, sigla, referencia);
    }
}
