package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.LoaningProcess;
import pl.com.kubachmielowiec.application.Ranking;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Copy;
import pl.com.kubachmielowiec.model.publications.CopyRepository;
import pl.com.kubachmielowiec.model.publications.Publisher;

import java.time.Year;

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
    LoansRepository loansRepository;

    @Test
    public void shouldLoanACopy() {
        Copy copy = createCopy();
        Client client = createClient();

        loaningProcess.loan(copy.getBarcode(), client.getId());

        assertThat(copyRepository.get(copy.getBarcode()).isLoaned()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotLoanAlreadyLoanedCopy() {
        Copy copy = createCopy();
        Client client = createClient();

        loaningProcess.loan(copy.getBarcode(), client.getId());
        loaningProcess.loan(copy.getBarcode(), client.getId());
    }

    @Test
    public void shouldGiveBackACopy() {
        Copy copy = createCopy();
        Client client = createClient();
        loaningProcess.loan(copy.getBarcode(), client.getId());

        loaningProcess.giveBack(copy.getBarcode(), client.getId());

        assertThat(copyRepository.get(copy.getBarcode()).isLoaned()).isFalse();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotGiveBackNotLoanedCopy() {
        Copy copy = createCopy();
        Client client = createClient();

        loaningProcess.giveBack(copy.getBarcode(), client.getId());
    }

    /*@Test
    public void shouldGenerateExpiredReturnRaport() {
        Loan loan = createExpiredLoan();

        LoanRaport loanRaport = loaningProcess.generateExpiredReturnDateRaport();

        assertThat(loanRaport.getExpiredLoans().size()).isEqualTo(2);
        assertThat(loanRaport.getExpiredLoans().get(1).getCopy()).isEqualTo(loan.getCopy().getBarcode().getCode());
        assertThat(loanRaport.getExpiredLoans().get(1).getClientId()).isEqualTo(loan.getClient().getId());
    }

    private Loan createExpiredLoan() {
        Copy copy = createCopy();
        Client client = createClient();
        Loan loan = new Loan(copy, client, LocalDate.of(2000, 10, 1), true);
        loansRepository.put(loan);
        return loan;
    }*/

    @Test
    public void shouldGenerateTheMostLoanedRanking() {
        Copy c1 = createCopy();
        Copy c2 = createAnotherCopy();
        Client client = createClient();
        loaningProcess.loan(c1.getBarcode(), client.getId());
        loaningProcess.loan(c2.getBarcode(), client.getId());
        loaningProcess.giveBack(c1.getBarcode(), client.getId());
        loaningProcess.loan(c1.getBarcode(), client.getId());

        Ranking ranking = loaningProcess.generateTheMostLoanedRanking();

        assertThat(ranking.getLoansCounts().size()).isEqualTo(2);
        assertThat(ranking.getLoansCounts().get(0)).isEqualTo(c1.getPublication());
        assertThat(ranking.getLoansCounts().get(1)).isEqualTo(c2.getPublication());
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

    private Client createClient() {
        CreateClientCommand cmd = new CreateClientCommand();
        cmd.setId(1L);
        cmd.setFirstName("Kuba");
        cmd.setLastName("Buba");
        cmd.setEmail("kuba.buba@mail.com");
        cmd.setPhoneNumber("562323235");
        Client client = new Client(cmd);
        clientRepository.put(client);
        return client;
    }

}
