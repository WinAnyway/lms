package pl.com.kubachmielowiec.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.publications.Publication;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

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
    public void loan(Long publicationId, Long clientId) {
        Publication publication = publicationRepository.get(publicationId);
        publication.loan();
        Client client = clientRepository.get(clientId);
        client.loanAPublication(publicationId);
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
    public void remindClientAboutReturnViaEmail(Long clientId) {
        clientReminder.remindAboutReturnViaEmail(clientId);
    }

    @Override
    public void remindClientAboutReturnViaSms(Long clientId) {
        clientReminder.remindAboutReturnViaSms(clientId);
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
