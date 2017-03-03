package pl.com.kubachmielowiec.application.impl;

import pl.com.kubachmielowiec.application.LoanRaport;
import pl.com.kubachmielowiec.application.LoaningProcess;
import pl.com.kubachmielowiec.application.Ranking;
import pl.com.kubachmielowiec.model.PublicationCode;

public class StandardLoaningProcess implements LoaningProcess {

    @Override
    public void loan(PublicationCode publicationCode) {

    }

    @Override
    public void getBack(PublicationCode publicationCode) {

    }

    @Override
    public LoanRaport generateExpiredReturnDateRaport() {
        return null;
    }

    @Override
    public void remindClientAboutReturn(Long clientId) {

    }

    @Override
    public void showClientLoaningHistory(Long clientId) {

    }

    @Override
    public Ranking generateTheMostLoanedRanking() {
        return null;
    }
}
