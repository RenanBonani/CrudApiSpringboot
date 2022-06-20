package com.bonani.apiCrud.repository;

import com.bonani.apiCrud.model.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMusicRepository extends JpaRepository<MusicModel, Long> {
    @Query(value = "SELECT * FROM music ORDER BY title", nativeQuery = true)
    public List<MusicModel> findAll();

    public List<MusicModel> findByTitleContainingIgnoreCase(String title);

    @Query(value = "SELECT b.* from music b, artist a WHERE b.artist_id = a.id AND " +
            " UPPER(a.name) LIKE UPPER(:artistName) ORDER BY title", nativeQuery = true)
    public List<MusicModel> findByArtistName(@Param("artistName") String artistName);
}
