package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
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
    public Loan get(Long publicationId, Long clientId) {
        Query query = entityManager.createQuery("FROM Loan l WHERE l.publication.id = :pId AND l.client.id = :cId");
        query.setParameter("pId", publicationId);
        query.setParameter("cId", clientId);
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
        /*CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> criteriaQuery = cb.createQuery(Loan.class);
        Root<Loan> loan = criteriaQuery.from(Loan.class);

        Subquery<Publication> subquery = criteriaQuery.subquery(Publication.class);
        Root<Loan> subLoan = subquery.correlate(loan);
        Join<Loan, Publication> publication = subLoan.join("publication");
        subquery.select(publication);
        subquery.groupBy(publication);
        subquery.orderBy(cb.count(publication.get("id")));
        criteriaQuery.where(cb.exists(subquery));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();*/
        Query query = entityManager.createQuery("SELECT p FROM Loan l left join l.publication p group by p.id order by count(p) desc");
        return query.getResultList();
    }

}
