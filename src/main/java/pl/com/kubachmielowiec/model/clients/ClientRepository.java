package pl.com.kubachmielowiec.model.clients;

public interface ClientRepository {

    void put(Client client);

    Client get(Long clientId);

    void remove(Long clientId);
}
