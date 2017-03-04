package pl.com.kubachmielowiec.model.publications;

public interface GenresRepository {

    void put(Genre genre);

    void remove(Long genreId);

    Genre get(Long genreId);
}
