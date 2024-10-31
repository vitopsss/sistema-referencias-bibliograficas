package com.aranoua.referencias_bibliograficas.service;

import com.aranoua.referencias_bibliograficas.dto.afiliacao.AfiliacaoCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.afiliacao.AfiliacaoDTO;
import com.aranoua.referencias_bibliograficas.model.Afiliacao;
import com.aranoua.referencias_bibliograficas.repository.AfiliacaoRepository;
import com.aranoua.referencias_bibliograficas.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AfiliacaoService {
    @Autowired
    AfiliacaoRepository afiliacaoRepository;

    public Set<AfiliacaoDTO> list(){
        return afiliacaoRepository.findAll()
                .stream()
                .map(AfiliacaoDTO::buildDTO)
                .collect(Collectors.toSet());
    }

    public AfiliacaoDTO read(long id){
        try{
            return AfiliacaoDTO.buildDTO(encontrarAfiliacao(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public AfiliacaoDTO create(AfiliacaoCreateDTO body){
        try{
            return AfiliacaoDTO.buildDTO(afiliacaoRepository.save(body.toAfiliacaoEntity()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public AfiliacaoDTO update(long id, AfiliacaoCreateDTO body){
        try{
            Afiliacao afiliacaoAtual = encontrarAfiliacao(id);
            afiliacaoAtual.setNome(body.nome());
            afiliacaoAtual.setSigla(body.sigla());
            afiliacaoAtual.setReferencia(body.referencia());
            return AfiliacaoDTO.buildDTO(afiliacaoRepository.save(afiliacaoAtual));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        try {
            Afiliacao afiliacao = encontrarAfiliacao(id);
            afiliacaoRepository.delete(afiliacao);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private Afiliacao encontrarAfiliacao(long id){
        return afiliacaoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Afiliacao nao encontrada ID:" + id));
    }
}
