package com.bonani.apiCrud.repository;

import com.bonani.apiCrud.model.ArtistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArtistRepository extends JpaRepository<ArtistModel, Long> {

    @Query(value = "SELECT * FROM artist ORDER BY name", nativeQuery = true)
    public List<ArtistModel> findAll();

    @Query(value = "SELECT * FROM artist WHERE upper(name) like upper(:name) ORDER BY name ", nativeQuery = true)
    public List<ArtistModel> findByName(@Param("name") String name);

}
