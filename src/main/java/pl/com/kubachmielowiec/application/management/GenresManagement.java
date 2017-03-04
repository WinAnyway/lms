package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.application.dtos.GenreDto;

public interface GenresManagement {

    Long createGenre(String genreName);

    void updateGenre(String newGenreName, Long genreId);

    void deleteGenre(Long genreId);

    GenreDto getGenre(Long genreId);
}
