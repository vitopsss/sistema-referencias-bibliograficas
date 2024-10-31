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
@Table(name = "artigos")
public class Artigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false, name = "ano_publicacao")
    private String anoPublicacao;
    @ManyToOne
    private RevistaCientifica revista;
    @ManyToMany(mappedBy = "artigos")
    private Set<Autor> autores = new HashSet<>();

    public Artigo(String titulo, String anoPublicacao, RevistaCientifica revista, Set<Autor> autores){
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.revista = revista;
        this.autores = autores;
    }
}
