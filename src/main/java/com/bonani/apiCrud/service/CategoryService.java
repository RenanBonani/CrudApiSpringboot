package com.bonani.apiCrud.service;

import com.bonani.apiCrud.exception.NotFoundException;
import com.bonani.apiCrud.model.CategoryModel;
import com.bonani.apiCrud.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private ICategoryRepository repository;
    public CategoryModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }
    public List<CategoryModel> findAll(){
        return repository.findAll();
    }
    public CategoryModel save(CategoryModel model){
        return repository.save(model);
    }
    public CategoryModel update(CategoryModel model){
        CategoryModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setName(model.getName());
        return repository.save(model);
    }
    public void delete(long id){
        CategoryModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }

}
