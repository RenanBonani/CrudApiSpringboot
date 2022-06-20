package com.bonani.apiCrud.repository;

import com.bonani.apiCrud.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICategoryRepository extends JpaRepository<CategoryModel, Long> {
    @Query(value = "SELECT * FROM category order by name", nativeQuery = true)
    public List<CategoryModel> findAll();
}
