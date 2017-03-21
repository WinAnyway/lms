package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.GenresManagement;
import pl.com.kubachmielowiec.model.publications.Genre;
import pl.com.kubachmielowiec.model.publications.GenresRepository;

@Transactional
public class StandardGenresManagement implements GenresManagement{

    private GenresRepository genresRepository;

    public StandardGenresManagement(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Override
    public Long createGenre(String genreName) {
        Genre genre = new Genre(genreName);
        genresRepository.put(genre);
        return genre.getId();
    }

    @Override
    public void updateGenre(String newGenreName, Long genreId) {
        Genre genre = genresRepository.get(genreId);
        genre.update(newGenreName);
    }

    @Override
    public void deleteGenre(Long genreId) {
        genresRepository.remove(genreId);
    }

    @Override
    public Genre getGenre(Long genreId) {
        return genresRepository.get(genreId);
    }
}
