package com.bonani.apiCrud.controller;

import com.bonani.apiCrud.model.MusicModel;
import com.bonani.apiCrud.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/music/v1")
public class MusicController {
    @Autowired
    private MusicService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public MusicModel findById(@PathVariable long id){
        MusicModel model = service.findById(id);
        buildEntityLink(model);
        return model;
    }

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public CollectionModel<MusicModel> findAll(){
        CollectionModel<MusicModel> collectionModel =
                CollectionModel.of(service.findAll());
        buildCollectionLink(collectionModel);
        return collectionModel;
    }

    @GetMapping(value = "/find", produces = {"application/json", "application/xml"})
    public List<MusicModel> findByTitle(@RequestParam Optional<String> title,
                                       @RequestParam Optional<String> authorName){
        List<MusicModel> result = new ArrayList<>();
        if(title.isPresent()){
            result = service.findByTitle(title.get());
        }
        if(authorName.isPresent()){
            result = service.findByArtist(authorName.get());
        }
        return result;
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public MusicModel save(@RequestBody MusicModel model){
        return service.save(model);
    }

    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public MusicModel update(@RequestBody MusicModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildEntityLink(MusicModel model){
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(MusicController.class).findById(model.getId())
                ).withRel(IanaLinkRelations.SELF)
        );

        //..build links to relationships
        if(!model.getCategory().hasLinks()) {
            model.getCategory().add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder
                                    .methodOn(CategoryController.class)
                                    .findById(model.getCategory().getId())
                    ).withRel(IanaLinkRelations.SELF)
            );
        }

        if(!model.getArtist().hasLinks()) {
            model.getArtist().add(
                    WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder
                                    .methodOn(ArtistController.class)
                                    .findById(model.getArtist().getId())
                    ).withRel(IanaLinkRelations.SELF)
            );
        }

    }

    public void buildCollectionLink(
            CollectionModel<MusicModel> collectionModel){

        for (MusicModel music : collectionModel){
            buildEntityLink(music);
        }

        collectionModel.add(
                WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(
                                        MusicController.class
                                ).findAll())
                        .withRel(IanaLinkRelations.COLLECTION)
        );

    }
}
