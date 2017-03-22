package pl.com.kubachmielowiec.model.clients;

import pl.com.kubachmielowiec.model.publications.Barcode;
import pl.com.kubachmielowiec.model.publications.Publication;

import java.util.List;

public interface LoansRepository {

    void put(Loan loan);

    List<Loan> getLoansFor(Long clientId);

    Loan getActiveLoan(Barcode barcode, Long clientId);

    List<Loan> getActiveExpiredLoans();

    List<Publication> countLoans();
}
