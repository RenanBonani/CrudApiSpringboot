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
@Table(name = "artist")
public class ArtistModel extends RepresentationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column(length = 1)
    private String gender;

    @Column(length = 40)
    private String country;

}
