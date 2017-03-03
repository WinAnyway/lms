package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.model.clients.Loan;

import java.util.Collection;

public interface LoaningProcess {

    void loan(Long id);

    void giveBack(Long id);

    LoanRaport generateExpiredReturnDateRaport();

    void remindClientAboutReturn(Long clientId);

    Collection<Loan> getClientLoaningHistory(Long clientId);

    Ranking generateTheMostLoanedRanking();

}
