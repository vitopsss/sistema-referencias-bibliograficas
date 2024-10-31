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
@Table(name = "revistas_cientificas")
public class RevistaCientifica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String ISSN;
    @OneToMany(mappedBy = "revista")
    private Set<Artigo> artigosPublicados = new HashSet<>();

    public RevistaCientifica(String nome, String ISSN) {
        this.nome = nome;
        this.ISSN = ISSN;
    }
}
