package com.bonani.apiCrud.model;
import org.springframework.hateoas.RepresentationModel;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music")
public class MusicModel extends RepresentationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistModel artist;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicModel musicModel = (MusicModel) o;
        return id == musicModel.id && Objects.equals(title, musicModel.title) && Objects.equals(artist, musicModel.artist) && Objects.equals(category, musicModel.category);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, category);
    }
}
