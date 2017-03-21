package pl.com.kubachmielowiec.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.publications.Publication;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

import java.util.Collection;

public class StandardLoaningProcess implements LoaningProcess {

    private PublicationRepository publicationRepository;
    private ClientRepository clientRepository;
    private LoansRepository loansRepository;
    private RaportGenerator raportGenerator;
    private RankingGenerator rankingGenerator;
    private ClientReminder clientReminder;

    public StandardLoaningProcess(PublicationRepository publicationRepository, ClientRepository clientRepository,
                                  LoansRepository loansRepository, RaportGenerator raportGenerator, RankingGenerator rankingGenerator, ClientReminder clientReminder) {
        this.publicationRepository = publicationRepository;
        this.clientRepository = clientRepository;
        this.loansRepository = loansRepository;
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
        loansRepository.put(new Loan(publication, client));
    }

    @Override
    @Transactional
    public void giveBack(Long publicationId, Long clientId) {
        Publication publication = publicationRepository.get(publicationId);
        publication.giveBack();
        Loan loan = loansRepository.get(publicationId, clientId);
        loan.deactivate();
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
        return loansRepository.getLoansFor(clientId);
    }

    @Override
    public Ranking generateTheMostLoanedRanking() {
        return rankingGenerator.generateRanking();
    }
}
