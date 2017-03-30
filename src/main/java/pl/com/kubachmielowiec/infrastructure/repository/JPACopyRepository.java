package pl.com.kubachmielowiec.infrastructure.repository;

import pl.com.kubachmielowiec.model.publications.Barcode;
import pl.com.kubachmielowiec.model.publications.Copy;
import pl.com.kubachmielowiec.model.publications.CopyRepository;
import pl.com.kubachmielowiec.model.publications.Publication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JPACopyRepository implements CopyRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Copy copy) {
        entityManager.persist(copy);
    }

    @Override
    public Copy get(Barcode barcode) {
        return entityManager.find(Copy.class, barcode);
    }

    @Override
    public List<Copy> getAvailableCopiesOf(Publication publication) {
        Query query = entityManager.createQuery("FROM Copy c WHERE c.publication = :publication AND c.loaned = false");
        query.setParameter("publication", publication);
        return query.getResultList();
    }

    @Override
    public void remove(Barcode barcode) {
        Query query = entityManager.createQuery("DELETE FROM Copy c WHERE c.barcode = :barcode");
        query.setParameter("barcode", barcode);
        query.executeUpdate();
    }
}
