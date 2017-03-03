package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.publications.Author;
import pl.com.kubachmielowiec.model.publications.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPAAuthorRepository implements AuthorRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Author author) {
        entityManager.persist(author);
    }

    @Override
    public Author get(Long authorId) {
        return entityManager.find(Author.class, authorId);
    }

    @Override
    public void remove(Long authorId) {
        Query query = entityManager.createQuery("DELETE FROM Author a WHERE a.id = :id");
        query.setParameter("id", authorId);
        query.executeUpdate();
    }
}
