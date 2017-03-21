package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.publications.Publication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
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
        CriteriaQuery<Publication> criteriaQuery = cb.createQuery(Publication.class);
        Root<Loan> loan = criteriaQuery.from(Loan.class);
        criteriaQuery.select(loan.get("publication"));
        loan.join("publication", JoinType.LEFT);
        criteriaQuery.groupBy(loan.get("publication"));
        criteriaQuery.orderBy(cb.desc(cb.count(loan.get("publication"))));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();*/
        Query query = entityManager.createQuery("SELECT p FROM Loan l left join l.publication p group by p.id order by count(p) desc");
        return query.getResultList();
    }

}
