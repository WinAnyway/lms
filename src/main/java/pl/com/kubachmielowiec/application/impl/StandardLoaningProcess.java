package pl.com.kubachmielowiec.application.impl;

import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.model.*;

import java.util.Collection;

public class StandardLoaningProcess implements LoaningProcess {

    private PublicationRepository publicationRepository;
    private ClientRepository clientRepository;
    private RaportGenerator raportGenerator;
    private RankingGenerator rankingGenerator;
    private ClientReminder clientReminder;

    @Override
    public void loan(Long publicationId) {
        Publication publication = publicationRepository.get(publicationId);
        publication.loan();
    }

    @Override
    public void giveBack(Long publicationId) {
        Publication publication = publicationRepository.get(publicationId);
        publication.giveBack();
    }

    @Override
    public LoanRaport generateExpiredReturnDateRaport() {
        return raportGenerator.generateExpiredRaport();
    }

    @Override
    public void remindClientAboutReturn(Long clientId) {
        clientReminder.remindAboutReturn(clientId);
    }

    @Override
    public Collection<Loan> getClientLoaningHistory(Long clientId) {
        Client client = clientRepository.get(clientId);
        return client.getLoans();
    }

    @Override
    public Ranking generateTheMostLoanedRanking() {
        return rankingGenerator.generateRanking();
    }
}
