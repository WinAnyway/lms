package pl.com.kubachmielowiec.model.clients;

import java.util.List;

public interface LoansRepository {

    void put(Loan loan);

    List<Loan> getLoansFor(Long clientId);

    Loan get(Long publicationId, Long clientId);
}
