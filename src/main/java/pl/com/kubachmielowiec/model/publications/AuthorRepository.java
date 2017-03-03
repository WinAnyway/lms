package pl.com.kubachmielowiec.model.publications;

public interface AuthorRepository {

    void put(Author author);

    Author get(Long authorId);

    void remove(Long authorId);
}
