package pl.com.kubachmielowiec.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.publications.Barcode;
import pl.com.kubachmielowiec.model.publications.Copy;
import pl.com.kubachmielowiec.model.publications.CopyRepository;
import pl.com.kubachmielowiec.model.publications.Publication;

import java.util.Collection;

public class StandardLoaningProcess implements LoaningProcess {

    private CopyRepository copyRepository;
    private ClientRepository clientRepository;
    private LoansRepository loansRepository;
    private RaportGenerator raportGenerator;
    private RankingGenerator rankingGenerator;
    private ClientReminder clientReminder;

    public StandardLoaningProcess(CopyRepository copyRepository, ClientRepository clientRepository,
                                  LoansRepository loansRepository, RaportGenerator raportGenerator, RankingGenerator rankingGenerator, ClientReminder clientReminder) {
        this.copyRepository = copyRepository;
        this.clientRepository = clientRepository;
        this.loansRepository = loansRepository;
        this.raportGenerator = raportGenerator;
        this.rankingGenerator = rankingGenerator;
        this.clientReminder = clientReminder;
    }

    @Override
    @Transactional
    public void loan(Barcode barcode, Long clientId) {
        Copy copy = copyRepository.get(barcode);
        Publication publication = copy.getPublication();

        copy.loan();
        makeUnavailableIfNoCopies(publication);

        Client client = clientRepository.get(clientId);
        loansRepository.put(new Loan(copy, client));
    }

    private void makeUnavailableIfNoCopies(Publication publication) {
        if(copyRepository.getAvailableCopiesOf(publication).isEmpty()) {
            publication.makeUnavailable();
        }
    }

    @Override
    @Transactional
    public void giveBack(Barcode barcode, Long clientId) {
        Copy copy = copyRepository.get(barcode);
        copy.giveBack();
        Publication publication = copy.getPublication();

        if(!publication.isAvailable())
            publication.makeAvailable();

        Loan loan = loansRepository.getActiveLoan(barcode, clientId);
        loan.deactivate();
    }

    @Override
    public LoanRaport generateExpiredReturnDateRaport() {
        return raportGenerator.generateExpiredRaport();
    }

    @Override
    public void remindClientsAboutReturnViaEmail() {
        clientReminder.remindAboutReturnViaEmail();
    }

    @Override
    public void remindClientsAboutReturnViaSms() {
            clientReminder.remindAboutReturnViaSms();
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
