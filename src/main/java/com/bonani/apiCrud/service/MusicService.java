package com.bonani.apiCrud.service;

import com.bonani.apiCrud.exception.NotFoundException;
import com.bonani.apiCrud.model.MusicModel;
import com.bonani.apiCrud.repository.IMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    private IMusicRepository repository;

    public List<MusicModel> findAll(){
        return repository.findAll();
    }

    public List<MusicModel> findByTitle(String title){
        return repository.findByTitleContainingIgnoreCase(title);
    }

    public List<MusicModel> findByArtist(String artistName){
        return repository.findByArtistName("%" + artistName + "%");
    }

    public MusicModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }

    public MusicModel save(MusicModel model){
        return repository.save(model);
    }

    public MusicModel update(MusicModel model){
        MusicModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setTitle(model.getTitle());
        found.setCategory(model.getCategory());
        found.setArtist(model.getArtist());
        return  repository.save(model);
    }

    public void delete(long id){
        MusicModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }
}
