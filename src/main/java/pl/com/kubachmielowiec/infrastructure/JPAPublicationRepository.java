package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.Publication;
import pl.com.kubachmielowiec.model.PublicationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
