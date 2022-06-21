package com.bonani.apiCrud.service;
import com.bonani.apiCrud.exception.NotFoundException;
import com.bonani.apiCrud.model.ArtistModel;
import com.bonani.apiCrud.repository.IArtistRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private IArtistRepository repository;

    public ArtistModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }

    public List<ArtistModel> findAll(){
        return repository.findAll();
    }

    public List<ArtistModel> findByName(String name){
        return repository.findByName(name);
    }

    public ArtistModel save(ArtistModel model){
        return repository.save(model);
    }

    public ArtistModel update(ArtistModel model){
        ArtistModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setName(model.getName());
        found.setGender(model.getGender());
        found.setCountry(model.getCountry());
        return repository.save(found);
    }

    public void delete(long id){
        ArtistModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }
}
