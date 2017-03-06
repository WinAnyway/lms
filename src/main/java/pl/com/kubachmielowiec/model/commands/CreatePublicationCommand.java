package pl.com.kubachmielowiec.model.commands;

import pl.com.kubachmielowiec.model.publications.Author;
import pl.com.kubachmielowiec.model.publications.Genre;
import pl.com.kubachmielowiec.model.publications.Publisher;

import java.time.Year;
import java.util.Set;

public class CreatePublicationCommand {
    private String title;
    private String description;
    private Set<Author> authors;
    private String isbn;
    private Year published;
    private Publisher publisher;
    private Set<Genre> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Year getPublished() {
        return published;
    }

    public void setPublished(Year published) {
        this.published = published;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
