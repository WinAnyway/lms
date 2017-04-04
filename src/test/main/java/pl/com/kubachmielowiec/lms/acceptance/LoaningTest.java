package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.loan.*;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Copy;
import pl.com.kubachmielowiec.model.publications.CopyRepository;
import pl.com.kubachmielowiec.model.publications.Publisher;

import java.time.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class LoaningTest {

    @Autowired
    LoaningProcess loaningProcess;

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    PublicationsManagement publicationsManagement;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientsManagement clientsManagement;

    @Autowired
    LoansRepository loansRepository;

    @Test
    public void shouldLoanACopy() {
        Copy copy = createCopy();
        Client client = clientRepository.get(createClient());

        loaningProcess.loan(copy.getBarcode(), client.getId());

        assertThat(copyRepository.get(copy.getBarcode()).isLoaned()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotLoanAlreadyLoanedCopy() {
        Copy copy = createCopy();
        Client client = clientRepository.get(createClient());

        loaningProcess.loan(copy.getBarcode(), client.getId());
        loaningProcess.loan(copy.getBarcode(), client.getId());
    }

    @Test
    public void shouldGiveBackACopy() {
        Copy copy = createCopy();
        Client client = clientRepository.get(createClient());
        loaningProcess.loan(copy.getBarcode(), client.getId());

        loaningProcess.giveBack(copy.getBarcode(), client.getId());

        assertThat(copyRepository.get(copy.getBarcode()).isLoaned()).isFalse();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotGiveBackNotLoanedCopy() {
        Copy copy = createCopy();
        Client client = clientRepository.get(createClient());

        loaningProcess.giveBack(copy.getBarcode(), client.getId());
    }

    @Test
    public void shouldGenerateTheMostLoanedRanking() {
        Copy c1 = createCopy();
        Copy c2 = createAnotherCopy();
        Client client = clientRepository.get(createClient());
        loaningProcess.loan(c1.getBarcode(), client.getId());
        loaningProcess.loan(c2.getBarcode(), client.getId());
        loaningProcess.giveBack(c1.getBarcode(), client.getId());
        loaningProcess.loan(c1.getBarcode(), client.getId());

        Ranking ranking = loaningProcess.generateTheMostLoanedRanking();

        assertThat(ranking.getLoansCounts().size()).isEqualTo(2);
        assertThat(ranking.getLoansCounts().get(0)).isEqualTo(c1.getPublication());
        assertThat(ranking.getLoansCounts().get(1)).isEqualTo(c2.getPublication());
    }

    @Test
    public void shouldGenerateExpiredReturnRaport() {
        createExpiredLoan();

        LoanRaport raport = loaningProcess.generateExpiredReturnDateRaport();
        assertThat(raport.getExpiredLoans().size()).isEqualTo(1);
        assertThat(raport.getExpiredLoans().get(0).getLoanDate()).isEqualTo(LocalDate.of(2017, 1, 1));
    }

    @Test
    public void shouldGetClientLoaningHistory() {
        Long id = createClient();
        Copy copy = createCopy();
        Copy copy1 = createAnotherCopy();
        loaningProcess.loan(copy.getBarcode(), id);
        loaningProcess.loan(copy1.getBarcode(), id);

        List<Loan> loans = loaningProcess.getClientLoaningHistory(id);

        assertThat(loans.size()).isEqualTo(2);
        assertThat(loans.get(0).getClient().getId()).isEqualTo(id);
        assertThat(loans.get(0).getCopy()).isEqualTo(copy);
        assertThat(loans.get(1).getCopy()).isEqualTo(copy1);
    }

    private void createExpiredLoan() {
        Copy copy = createCopy();
        Client client = clientRepository.get(createClient());
        Clock clock = Clock.fixed(Instant.parse("2017-01-01T10:15:30.00Z"), ZoneId.systemDefault());
        loaningProcess = new StandardLoaningProcess(copyRepository, clientRepository, loansRepository, new RaportGenerator(loansRepository), new RankingGenerator(loansRepository), new ClientReminder(clientRepository), clock);
        loaningProcess.loan(copy.getBarcode(), client.getId());
    }

    private Copy createCopy() {
        return copyRepository.get(publicationsManagement.addCopiesOf(createPublication(), 1).get(0));
    }

    private Copy createAnotherCopy() {
        return copyRepository.get(publicationsManagement.addCopiesOf(createAnotherPublication(), 1).get(0));
    }

    private Long createPublication() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        cmd.setTitle("test");
        cmd.setDescription("test");
        cmd.setIsbn("asdasd");
        cmd.setPublished(Year.of(1993));
        cmd.setPublisher(new Publisher("Fabryka Słów"));
        return publicationsManagement.createPublication(cmd);
    }

    private Long createAnotherPublication() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        cmd.setTitle("test2");
        cmd.setDescription("tes2t");
        cmd.setIsbn("asdasd2");
        cmd.setPublished(Year.of(1995));
        cmd.setPublisher(new Publisher("Fabryka Słów2"));
        return publicationsManagement.createPublication(cmd);
    }

    private Long createClient() {
        CreateClientCommand cmd = new CreateClientCommand();
        cmd.setFirstName("Kuba");
        cmd.setLastName("Buba");
        cmd.setEmail("kuba.buba@mail.com");
        cmd.setPhoneNumber("562323235");
        return clientsManagement.createClient(cmd);
    }

}
