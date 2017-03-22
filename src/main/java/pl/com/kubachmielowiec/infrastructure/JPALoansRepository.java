package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.publications.Barcode;
import pl.com.kubachmielowiec.model.publications.Publication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
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
    public Loan getActiveLoan(Barcode barcode, Long clientId) {
        Query query = entityManager.createQuery("FROM Loan l WHERE l.copy.barcode = :barcode AND l.client.id = :clientId AND l.active = true");
        query.setParameter("barcode", barcode);
        query.setParameter("clientId", clientId);
        return (Loan) query.getSingleResult();
    }

    @Override
    public List<Loan> getActiveExpiredLoans() {
        Query query = entityManager.createQuery("FROM Loan l WHERE l.active = TRUE AND l.loanDate < :monthAgo");
        query.setParameter("monthAgo", LocalDate.now().minusMonths(1L));
        return query.getResultList();
    }

    @Override
    public List<Publication> countLoans() {
        Query query = entityManager.createQuery("SELECT p FROM Loan l left join l.copy c left join c.publication p group by p.id order by count(p) desc");
        return query.getResultList();
    }

}
