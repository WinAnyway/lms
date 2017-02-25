package pl.com.kubachmielowiec.model;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Publication {

    String title;
    String description;

    Set<Author> authors;

    ISBN isbn;

    LocalDate published;

    Publisher publisher;

    Set<Genre> genres;

    boolean available;
}
