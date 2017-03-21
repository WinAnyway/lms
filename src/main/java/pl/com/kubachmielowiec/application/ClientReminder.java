package pl.com.kubachmielowiec.application;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;

import java.util.List;

@Transactional
public class ClientReminder {

    ClientRepository clientRepository;

    public ClientReminder(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void remindAboutReturnViaSms() {
        List<Client> clients = clientRepository.getClientsWithExpiredLoans();
        for (Client client : clients)
            sendSmsTo(client.getPhoneNumber());
    }

    private void sendSmsTo(String phoneNumber) {
        Logger.getLogger(ClientReminder.class).info(String.format("Sending sms reminder to %s", phoneNumber));
    }

    public void remindAboutReturnViaEmail() {
        List<Client> clients = clientRepository.getClientsWithExpiredLoans();
        for(Client client : clients)
            sendEmailTo(client.getEmail());
    }

    private void sendEmailTo(String email) {
        Logger.getLogger(ClientReminder.class).info(String.format("Sending email reminder to %s", email));
    }
}
