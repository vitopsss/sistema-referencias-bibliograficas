package com.aranoua.referencias_bibliograficas.controller;

import com.aranoua.referencias_bibliograficas.dto.artigo.ArtigoCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.artigo.ArtigoDTO;
import com.aranoua.referencias_bibliograficas.service.ArtigoService;
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
@RequestMapping("/api/artigo")
public class ArtigoController {
    @Autowired
    ArtigoService artigoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ArtigoDTO>> list(){
        return ResponseEntity.ok().body(artigoService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtigoDTO> read(@PathVariable long id){
        return ResponseEntity.ok().body(artigoService.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ArtigoDTO>> create(@Valid @RequestBody ArtigoCreateDTO body){
        ArtigoDTO autor = artigoService.create(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autor.id())
                .toUri();

        ArtigoController linkBuild = WebMvcLinkBuilder.methodOn(ArtigoController.class);
        Link list = WebMvcLinkBuilder.linkTo(linkBuild.list()).withRel("list");
        Link read = WebMvcLinkBuilder.linkTo(linkBuild.read(autor.id())).withRel("read");
        Link update = WebMvcLinkBuilder.linkTo(linkBuild.update(autor.id(), body)).withRel("update");
        Link delete = WebMvcLinkBuilder.linkTo(linkBuild.delete(autor.id())).withRel("delete");

        EntityModel<ArtigoDTO> modelAutor = EntityModel.of(autor)
                .add(list, read, update, delete);
        return ResponseEntity.created(uri).body(modelAutor);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtigoDTO> update(@PathVariable long id,@Valid @RequestBody ArtigoCreateDTO body){
         return ResponseEntity.ok().body(artigoService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        artigoService.delete(id);
         return ResponseEntity.ok().build();
    }
}
