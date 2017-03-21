package pl.com.kubachmielowiec.model.clients;

import java.util.List;

public interface ClientRepository {

    void put(Client client);

    Client get(Long clientId);

    void remove(Long clientId);

    List<Client> getClientsWithExpiredLoans();
}
