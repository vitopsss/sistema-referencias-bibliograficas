package com.aranoua.referencias_bibliograficas.service;

import com.aranoua.referencias_bibliograficas.dto.artigo.ArtigoCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.artigo.ArtigoDTO;
import com.aranoua.referencias_bibliograficas.model.Artigo;
import com.aranoua.referencias_bibliograficas.repository.ArtigoRepository;
import com.aranoua.referencias_bibliograficas.repository.AutorRepository;
import com.aranoua.referencias_bibliograficas.repository.RevistaCientificaRepository;
import com.aranoua.referencias_bibliograficas.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtigoService {
    @Autowired
    RevistaCientificaRepository revistaRepository;
    @Autowired
    AutorRepository autorRepository;
    @Autowired
    ArtigoRepository artigoRepository;

    public Set<ArtigoDTO> list(){
        return artigoRepository.findAll().stream()
                .map(ArtigoDTO::buildDTO)
                .collect(Collectors.toSet());
    }

    public ArtigoDTO read(long id){
        return ArtigoDTO.buildDTO(encontrarArtigo(id));
    }

    public ArtigoDTO create(ArtigoCreateDTO body){
        try {
            return ArtigoDTO.buildDTO(
                    artigoRepository.save(body.toArtigoEntity(revistaRepository, autorRepository))
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ArtigoDTO update(long id, ArtigoCreateDTO body) {
        try {
            Artigo novoArtigo = body.artigoAtualizado(encontrarArtigo(id), revistaRepository, autorRepository);
            return ArtigoDTO.buildDTO(artigoRepository.save(novoArtigo));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        try {
            artigoRepository.delete(encontrarArtigo(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private Artigo encontrarArtigo(long id){
        return artigoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Artigo não encontrado ARTIGO_ID: " + id));
    }
}
