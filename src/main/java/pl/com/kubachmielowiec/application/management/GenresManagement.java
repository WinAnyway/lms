package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.model.publications.Genre;

public interface GenresManagement {

    Long createGenre(String genreName);

    void updateGenre(String newGenreName, Long genreId);

    void deleteGenre(Long genreId);

    Genre getGenre(Long genreId);
}
