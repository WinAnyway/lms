package pl.com.kubachmielowiec.infrastructure.repository;

import pl.com.kubachmielowiec.model.publications.Publication;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class JPAPublicationRepository implements PublicationRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Publication publication) {
        entityManager.persist(publication);
    }

    @Override
    public Publication get(Long id) {
        return entityManager.find(Publication.class, id);
    }

    @Override
    public void remove(Long publicationId) {
        Query query = entityManager.createQuery("DELETE FROM Publication p WHERE p.id = :id");
        query.setParameter("id", publicationId);
        query.executeUpdate();
    }
}
