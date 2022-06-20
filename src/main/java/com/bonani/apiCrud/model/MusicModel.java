package com.bonani.apiCrud.model;
import org.springframework.hateoas.RepresentationModel;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
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


}
