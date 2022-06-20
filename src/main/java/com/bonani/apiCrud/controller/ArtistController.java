package com.bonani.apiCrud.controller;

import com.bonani.apiCrud.model.ArtistModel;
import com.bonani.apiCrud.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist/v1")
public class ArtistController {
    @Autowired
    ArtistService service;

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<ArtistModel> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/find/{name}", produces = {"application/json", "application/xml"})
    public List<ArtistModel> findByName(@PathVariable("name") String name){
        return service.findByName("%" + name + "%" );
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ArtistModel findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping(produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ArtistModel save(@RequestBody ArtistModel model){
        return service.save(model);
    }

    @PutMapping(produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ArtistModel update(@RequestBody ArtistModel model){
        return  service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
