package pl.com.kubachmielowiec.model.publications;

import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;

import javax.persistence.*;
import java.time.Year;
import java.util.Set;

@Entity
public class Publication {

    @Id
    @GeneratedValue
    Long id;

    String title;
    String description;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Author> authors;

    String isbn;

    Year publicationYear;

    @Embedded
    Publisher publisher;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Genre> genres;

    boolean available;

    Publication() {
    }

    public void activate() {
        this.available = true;
    }

    public void deactivate() {
        this.available = false;
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


    public void change(UpdatePublicationCommand cmd) {
        this.title = cmd.getTitle();
        this.description = cmd.getDescription();
        this.authors = cmd.getAuthors();
        this.isbn = cmd.getIsbn();
        this.publicationYear = cmd.getPublished();
        this.publisher = cmd.getPublisher();
        this.genres = cmd.getGenres();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publication that = (Publication) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        return isbn.equals(that.isbn);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }
}
