package pl.com.kubachmielowiec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    Long id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
