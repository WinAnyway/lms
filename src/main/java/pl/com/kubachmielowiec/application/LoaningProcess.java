package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.model.PublicationCode;

public interface LoaningProcess {

    void loan(PublicationCode publicationCode);

    void getBack(PublicationCode publicationCode);

    LoanRaport generateExpiredReturnDateRaport();

    void remindClientAboutReturn(Long clientId);

    void showClientLoaningHistory(Long clientId);

    Ranking generateTheMostLoanedRanking();

}
