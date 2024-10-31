package com.aranoua.referencias_bibliograficas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "afiliacao")
public class Afiliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sigla;
    @Column(nullable = false, unique = true)
    private String referencia;

    public Afiliacao(String nome, String sigla, String referencia){
        this.nome = nome;
        this.sigla = sigla;
        this.referencia = referencia;
    }
}
