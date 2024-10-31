package com.aranoua.referencias_bibliograficas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne(optional = false)
    @JoinColumn(name = "afiliacao_id", nullable = false)
    private Afiliacao afiliacao;

    @ManyToMany
    @JoinTable(
            name = "artigo_autor",
            joinColumns = @JoinColumn(name ="autor_id"),
            inverseJoinColumns = @JoinColumn(name = "artigo_id")
    )
    private Set<Artigo> artigos = new HashSet<>();

    public Autor(String nome, Afiliacao afiliacao) {
        this.nome = nome;
        this.afiliacao = afiliacao;
    }
}
