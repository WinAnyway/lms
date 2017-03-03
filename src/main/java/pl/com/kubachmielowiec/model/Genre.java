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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        return name.equals(genre.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
