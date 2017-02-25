package pl.com.kubachmielowiec.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Publication {

    @EmbeddedId
    private PublicationCode publicationCode;

    String title;
    String description;

    @OneToMany
    Set<Author> authors;

    @Embedded
    ISBN isbn;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDate published;

    @Embedded
    Publisher publisher;

    @OneToMany
    @Enumerated(EnumType.STRING)
    Set<Genre> genres;

    boolean available;
}
