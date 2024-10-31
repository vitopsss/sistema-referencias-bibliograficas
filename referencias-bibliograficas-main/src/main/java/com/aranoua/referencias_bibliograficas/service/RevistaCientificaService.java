package com.aranoua.referencias_bibliograficas.service;

import com.aranoua.referencias_bibliograficas.dto.revista_cientifica.RevistaCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.revista_cientifica.RevistaDTO;
import com.aranoua.referencias_bibliograficas.dto.revista_cientifica.RevistaSimplesDTO;
import com.aranoua.referencias_bibliograficas.model.RevistaCientifica;
import com.aranoua.referencias_bibliograficas.repository.RevistaCientificaRepository;
import com.aranoua.referencias_bibliograficas.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RevistaCientificaService {
    @Autowired
    RevistaCientificaRepository revistaRepository;

    public Set<RevistaSimplesDTO> list(){
        return revistaRepository.findAll().stream()
                .map(RevistaSimplesDTO::buildDTO)
                .collect(Collectors.toSet());
    }

    public RevistaDTO read(long id){
        return RevistaDTO.buildDTO(encontrarRevista(id));
    }

    public RevistaSimplesDTO create(RevistaCreateDTO body){
        try {
            return RevistaSimplesDTO.buildDTO(
                    revistaRepository.save(body.toRevistaEntity())
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public RevistaDTO update(long id, RevistaCreateDTO body){
        try {
            RevistaCientifica revista = encontrarRevista(id);
            revista.setNome(body.nome());
            revista.setISSN(body.ISSN());
            return RevistaDTO.buildDTO(revistaRepository.save(revista));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        try {
            revistaRepository.delete(encontrarRevista(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private RevistaCientifica encontrarRevista(long id){
        return revistaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Revista não encontrado REVISTA_ID: " + id));
    }
}
