package pl.com.kubachmielowiec.application.loan;

import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.publications.Barcode;

import java.util.Collection;

public interface LoaningProcess {

    void loan(Barcode barcode, Long clientId);

    void giveBack(Barcode barcode, Long clientId);

    LoanRaport generateExpiredReturnDateRaport();

    void remindClientsAboutReturnViaEmail();

    void remindClientsAboutReturnViaSms();

    Collection<Loan> getClientLoaningHistory(Long clientId);

    Ranking generateTheMostLoanedRanking();

}
