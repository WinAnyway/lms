package pl.com.kubachmielowiec.model.clients;

import pl.com.kubachmielowiec.model.publications.Publication;

import java.util.List;

public interface LoansRepository {

    void put(Loan loan);

    List<Loan> getLoansFor(Long clientId);

    Loan get(Long publicationId, Long clientId);

    List<Loan> getActiveExpiredLoans();

    List<Publication> countLoans();
}
