package com.aranoua.referencias_bibliograficas.controller;

import com.aranoua.referencias_bibliograficas.dto.afiliacao.AfiliacaoCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.afiliacao.AfiliacaoDTO;
import com.aranoua.referencias_bibliograficas.service.AfiliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@Controller
@RequestMapping("/api/afiliacao")
public class AfiliacaoController {
    @Autowired
    AfiliacaoService afiliacaoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AfiliacaoDTO>> list(){
        return ResponseEntity.ok().body(afiliacaoService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AfiliacaoDTO> read(@PathVariable long id){
        return ResponseEntity.ok().body(afiliacaoService.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AfiliacaoDTO>> create(@Valid @RequestBody AfiliacaoCreateDTO body){
        AfiliacaoDTO autor = afiliacaoService.create(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autor.id())
                .toUri();

        AfiliacaoController linkBuild = WebMvcLinkBuilder.methodOn(AfiliacaoController.class);

        Link list = WebMvcLinkBuilder.linkTo(linkBuild.list()).withRel("list");
        Link read = WebMvcLinkBuilder.linkTo(linkBuild.read(autor.id())).withRel("read");
        Link update = WebMvcLinkBuilder.linkTo(linkBuild.update(autor.id(), body)).withRel("update");
        Link delete = WebMvcLinkBuilder.linkTo(linkBuild.delete(autor.id())).withRel("delete");

        EntityModel<AfiliacaoDTO> modelAutor = EntityModel.of(autor)
                .add(list, read, update, delete);
        return ResponseEntity.created(uri).body(modelAutor);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AfiliacaoDTO> update(@Valid @PathVariable long id, @RequestBody AfiliacaoCreateDTO body){
         return ResponseEntity.ok().body(afiliacaoService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        afiliacaoService.delete(id);
         return ResponseEntity.ok().build();
    }
}
