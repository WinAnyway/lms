package pl.com.kubachmielowiec.application.dtos;

import java.time.Year;
import java.util.Set;

public class PublicationDto {
    private String title;
    private String description;
    private Set<AuthorDto> authors;
    private String isbn;
    private Year published;
    private String publisher;
    private Set<String> genres;
    private boolean available;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setPublished(Year published) {
        this.published = published;
    }

    public Year getPublished() {
        return published;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}
