package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.model.clients.Loan;

import java.util.Collection;

public interface LoaningProcess {

    void loan(Long publicationId, Long clientId);

    void giveBack(Long publicationId, Long clientId);

    LoanRaport generateExpiredReturnDateRaport();

    void remindClientAboutReturnViaEmail(Long clientId);

    void remindClientAboutReturnViaSms(Long clientId);

    Collection<Loan> getClientLoaningHistory(Long clientId);

    Ranking generateTheMostLoanedRanking();

}
