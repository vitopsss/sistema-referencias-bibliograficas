package com.aranoua.referencias_bibliograficas.controller;

import com.aranoua.referencias_bibliograficas.dto.autor.AutorCreateDTO;
import com.aranoua.referencias_bibliograficas.dto.autor.AutorDTO;
import com.aranoua.referencias_bibliograficas.dto.autor.AutorSimplesDTO;
import com.aranoua.referencias_bibliograficas.service.AutorService;
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
@RequestMapping("/api/autor")
public class AutorController {
    @Autowired
    AutorService autorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AutorSimplesDTO>> list(){
        return ResponseEntity.ok().body(autorService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDTO> read(@PathVariable long id){
        return ResponseEntity.ok().body(autorService.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<AutorSimplesDTO>> create(@Valid @RequestBody AutorCreateDTO body){
        AutorSimplesDTO autor = autorService.create(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autor.id())
                .toUri();

        AutorController linkBuild = WebMvcLinkBuilder.methodOn(AutorController.class);

        Link list = WebMvcLinkBuilder.linkTo(linkBuild.list()).withRel("list");
        Link read = WebMvcLinkBuilder.linkTo(linkBuild.read(autor.id())).withRel("read");
        Link update = WebMvcLinkBuilder.linkTo(linkBuild.update(autor.id(), body)).withRel("update");
        Link delete = WebMvcLinkBuilder.linkTo(linkBuild.delete(autor.id())).withRel("delete");

        EntityModel<AutorSimplesDTO> modelAutor = EntityModel.of(autor)
                .add(list, read, update, delete);
        return ResponseEntity.created(uri).body(modelAutor);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDTO> update(@Valid @PathVariable long id, @RequestBody AutorCreateDTO body){
         return ResponseEntity.ok().body(autorService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
         autorService.delete(id);
         return ResponseEntity.ok().build();
    }
}
