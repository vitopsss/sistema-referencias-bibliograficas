package com.aranoua.referencias_bibliograficas.controller;

import com.aranoua.referencias_bibliograficas.dto.revista_cientifica.RevistaCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.revista_cientifica.RevistaDTO;
import com.aranoua.referencias_bibliograficas.dto.revista_cientifica.RevistaSimplesDTO;
import com.aranoua.referencias_bibliograficas.service.RevistaCientificaService;
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
@RequestMapping("/api/revista")
public class RevistaCientificaController {
    @Autowired
    RevistaCientificaService revistaCientificaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<RevistaSimplesDTO>> list(){
        return ResponseEntity.ok().body(revistaCientificaService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RevistaDTO> read(@PathVariable long id){
        return ResponseEntity.ok().body(revistaCientificaService.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RevistaSimplesDTO>> create(@Valid @RequestBody RevistaCreateDTO body){
        RevistaSimplesDTO autor = revistaCientificaService.create(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autor.id())
                .toUri();

        RevistaCientificaController linkBuild = WebMvcLinkBuilder.methodOn(RevistaCientificaController.class);

        Link list = WebMvcLinkBuilder.linkTo(linkBuild.list()).withRel("list");
        Link read = WebMvcLinkBuilder.linkTo(linkBuild.read(autor.id())).withRel("read");
        Link update = WebMvcLinkBuilder.linkTo(linkBuild.update(autor.id(), body)).withRel("update");
        Link delete = WebMvcLinkBuilder.linkTo(linkBuild.delete(autor.id())).withRel("delete");

        EntityModel<RevistaSimplesDTO> modelAutor = EntityModel.of(autor)
                .add(list, read, update, delete);
        return ResponseEntity.created(uri).body(modelAutor);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RevistaDTO> update(@Valid @PathVariable long id, @RequestBody RevistaCreateDTO body){
         return ResponseEntity.ok().body(revistaCientificaService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        revistaCientificaService.delete(id);
         return ResponseEntity.ok().build();
    }
}
