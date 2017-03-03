package pl.com.kubachmielowiec.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Publication {

    @Id
    @GeneratedValue
    Long id;

    @Embedded
    private PublicationCode publicationCode;

    String title;
    String description;

    @OneToMany
    Set<Author> authors;

    @Embedded
    ISBN isbn;

    @Temporal(TemporalType.TIMESTAMP)
    Date published;

    @Embedded
    Publisher publisher;

    @OneToMany
    Set<Genre> genres;

    boolean available;

    public void loan() {
        if (!available)
            throw new IllegalStateException(String.format("Publication %d is not available for loaning", id));
        this.available = false;
    }

    public void giveBack() {
        if (available)
            throw new IllegalStateException(String.format("Publication %d is already returned", id));
        this.available = true;
    }

    public void change(ChangePublicationCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.authors = cmd.getAuthors();
        this.isbn = cmd.getIsbn();
        this.published = cmd.getPublished();
        this.genres = cmd.getGenres();
    }

    public PublicationCode getPublicationCode() {
        return publicationCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public Date getPublished() {
        return published;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public boolean isAvailable() {
        return available;
    }
}
