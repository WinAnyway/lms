package pl.com.kubachmielowiec.model.publications;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.Year;
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
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "PUBLICATION_AUTHORS",
            joinColumns = @JoinColumn(name = "PUBLICATION_ID")
    )
    Set<Author> authors;

    String isbn;

    //    @Temporal(TemporalType.TIMESTAMP)
    Year publicationYear;

    @Embedded
    Publisher publisher;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "PUBLICATION_GENRES",
            joinColumns = @JoinColumn(name = "PUBLICATION_ID")
    )
    Set<Genre> genres;

    boolean available;

    Publication() {
    }

    public Publication(CreatePublicationCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.authors = cmd.getAuthors();
        this.isbn = cmd.getIsbn();
        this.publicationYear = cmd.getPublished();
        this.publisher = cmd.getPublisher();
        this.genres = cmd.getGenres();
        this.available = true;
    }

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

    public void change(UpdatePublicationCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.authors = cmd.getAuthors();
        this.isbn = cmd.getIsbn();
        this.publicationYear = cmd.getPublished();
        this.publisher = cmd.getPublisher();
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

    public String getIsbn() {
        return isbn;
    }

    public Year getPublicationYear() {
        return publicationYear;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
