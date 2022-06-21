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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistModel that = (ArtistModel) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(gender, that.gender) && Objects.equals(country, that.country);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, country);
    }
}
