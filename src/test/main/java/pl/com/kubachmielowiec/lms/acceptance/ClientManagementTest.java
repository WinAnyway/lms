package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.model.clients.Address;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.UpdateClientCommand;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClientManagementTest {

    @Autowired
    ClientsManagement clientsManagement;

    @Autowired
    ClientRepository clientRepository;

    @Test
    @Transactional
    public void shouldCreateClient() {
        Long id = createClient();

        Client client = clientRepository.get(id);
        assertThat(client.getId()).isEqualTo(id);
        assertThat(client.getFirstName()).isEqualTo("Kuba");
        assertThat(client.getLastName()).isEqualTo("Buba");
        assertThat(client.getAddress().getCountry()).isEqualTo("Polska");
        assertThat(client.getAddress().getCity()).isEqualTo("Lublin");
        assertThat(client.getEmail()).isEqualTo("kubabuba@mail.com");
    }

    @Test
    public void shouldDeleteClient() {
        Long id = createClient();

        clientsManagement.deleteClient(id);

        assertThat(clientRepository.get(id)).isNull();
    }

    @Test
    @Transactional
    public void shouldUpdateClient() {
        Long id = createClient();

        UpdateClientCommand cmd = new UpdateClientCommand();
        cmd.setClientId(id);
        cmd.setFirstName("Janek");
        cmd.setLastName("Dzbanek");
        clientsManagement.updateClient(cmd);

        Client client = clientRepository.get(id);
        assertThat(client.getFirstName()).isEqualTo("Janek");
        assertThat(client.getLastName()).isEqualTo("Dzbanek");
    }

    @Test
    @Transactional
    public void shouldShowClient() {
        Long id = createClient();

        Client client = clientsManagement.getClient(id);

        assertThat(client.getFirstName()).isEqualTo("Kuba");
        assertThat(client.getLastName()).isEqualTo("Buba");
    }

    private Long createClient() {
        CreateClientCommand cmd = new CreateClientCommand();
        cmd.setFirstName("Kuba");
        cmd.setLastName("Buba");
        cmd.setEmail("kubabuba@mail.com");
        cmd.setPhoneNumber("234982349");
        cmd.setAddress(new Address("Polska", "Lublin", "Testowa", "1", "20-123"));
        cmd.setIdNumber("AUH234234");
        cmd.setPesel("123123123");
        return clientsManagement.createClient(cmd);
    }
}
