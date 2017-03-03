package pl.com.kubachmielowiec.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.model.*;

import java.util.Collection;

public class StandardLoaningProcess implements LoaningProcess {

    private PublicationRepository publicationRepository;
    private ClientRepository clientRepository;
    private RaportGenerator raportGenerator;
    private RankingGenerator rankingGenerator;
    private ClientReminder clientReminder;

    public StandardLoaningProcess(PublicationRepository publicationRepository, ClientRepository clientRepository,
                                  RaportGenerator raportGenerator, RankingGenerator rankingGenerator, ClientReminder clientReminder) {
        this.publicationRepository = publicationRepository;
        this.clientRepository = clientRepository;
        this.raportGenerator = raportGenerator;
        this.rankingGenerator = rankingGenerator;
        this.clientReminder = clientReminder;
    }

    @Override
    @Transactional
    public void loan(Long publicationId) {
        Publication publication = publicationRepository.get(publicationId);
        publication.loan();
    }

    @Override
    @Transactional
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
    @Transactional
    public Collection<Loan> getClientLoaningHistory(Long clientId) {
        Client client = clientRepository.get(clientId);
        return client.getLoans();
    }

    @Override
    public Ranking generateTheMostLoanedRanking() {
        return rankingGenerator.generateRanking();
    }
}
