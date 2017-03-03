package pl.com.kubachmielowiec.application;

public interface LoaningProcess {

    void loan(Long id);

    void giveBack(Long id);

    LoanRaport generateExpiredReturnDateRaport();

    void remindClientAboutReturn(Long clientId);

    void showClientLoaningHistory(Long clientId);

    Ranking generateTheMostLoanedRanking();

}
