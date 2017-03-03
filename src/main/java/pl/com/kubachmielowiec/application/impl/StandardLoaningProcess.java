package pl.com.kubachmielowiec.application.impl;

import pl.com.kubachmielowiec.application.LoanRaport;
import pl.com.kubachmielowiec.application.LoaningProcess;
import pl.com.kubachmielowiec.application.Ranking;
import pl.com.kubachmielowiec.application.RaportGenerator;
import pl.com.kubachmielowiec.model.Publication;
import pl.com.kubachmielowiec.model.PublicationRepository;

public class StandardLoaningProcess implements LoaningProcess {

    private PublicationRepository publicationRepository;
    private RaportGenerator raportGenerator;

    @Override
    public void loan(Long id) {
        Publication publication = publicationRepository.get(id);
        publication.loan();
    }

    @Override
    public void giveBack(Long id) {
        Publication publication = publicationRepository.get(id);
        publication.giveBack();
    }

    @Override
    public LoanRaport generateExpiredReturnDateRaport() {
        return raportGenerator.generateExpiredRaport();
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
