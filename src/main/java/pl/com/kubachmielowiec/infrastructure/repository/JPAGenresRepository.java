package pl.com.kubachmielowiec.infrastructure.repository;

import pl.com.kubachmielowiec.model.publications.Genre;
import pl.com.kubachmielowiec.model.publications.GenresRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPAGenresRepository implements GenresRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void put(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public void remove(Long genreId) {
        Query query = entityManager.createQuery("DELETE FROM Genre g WHERE g.id = :id");
        query.setParameter("id", genreId);
        query.executeUpdate();
    }

    @Override
    public Genre get(Long genreId) {
        return entityManager.find(Genre.class, genreId);
    }
}
