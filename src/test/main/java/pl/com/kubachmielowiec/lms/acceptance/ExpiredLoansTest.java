package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.loan.LoanRaport;
import pl.com.kubachmielowiec.application.loan.LoaningProcess;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Copy;
import pl.com.kubachmielowiec.model.publications.CopyRepository;
import pl.com.kubachmielowiec.model.publications.Publisher;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ExpiredLoansTest {

    @Autowired
    LoaningProcess loaningProcess;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoansRepository loansRepository;

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    PublicationsManagement publicationsManagement;

    @Autowired
    ClientsManagement clientsManagement;

    @Test
    public void shouldGenerateExpiredReturnRaport() {
        createExpiredLoan();

        LoanRaport raport = loaningProcess.generateExpiredReturnDateRaport();
        assertThat(raport.getExpiredLoans().size()).isEqualTo(1);
        assertThat(raport.getExpiredLoans().get(0).getLoanDate()).isEqualTo(LocalDate.of(2017, 1, 1));
    }

    private void createExpiredLoan() {
        Copy copy = createCopy();
        Client client = clientRepository.get(createClient());
        loaningProcess.loan(copy.getBarcode(), client.getId());
    }

    private Copy createCopy() {
        return copyRepository.get(publicationsManagement.addCopiesOf(createPublication(), 1).get(0));
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

    private Long createClient() {
        CreateClientCommand cmd = new CreateClientCommand();
        cmd.setFirstName("Kuba");
        cmd.setLastName("Buba");
        cmd.setEmail("kuba.buba@mail.com");
        cmd.setPhoneNumber("562323235");
        return clientsManagement.createClient(cmd);
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public Clock clock() {
            return Clock.fixed(Instant.parse("2017-01-01T10:15:30.00Z"), ZoneId.systemDefault());
        }

    }
}


