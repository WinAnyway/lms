package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JPALoansRepository implements LoansRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Loan loan) {
        entityManager.persist(loan);
    }

    @Override
    public List<Loan> getLoansFor(Long clientId) {
        Query query = entityManager.createQuery("FROM Loan l WHERE l.client.id = :id");
        query.setParameter("id", clientId);
        return query.getResultList();
    }

    @Override
    public Loan get(Long publicationId, Long clientId) {
        Query query = entityManager.createQuery("FROM Loan l WHERE l.publication.id = :pId AND l.client.id = :cId");
        query.setParameter("pId", publicationId);
        query.setParameter("cId", clientId);
        return (Loan) query.getSingleResult();
    }

    @Override
    public List<Loan> getActiveLoans() {
        Query query = entityManager.createQuery("FROM Loan l WHERE l.active = TRUE");
        return query.getResultList();
    }
}
