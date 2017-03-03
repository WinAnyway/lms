package pl.com.kubachmielowiec.model;

public interface ClientRepository {

    void put(Client client);

    Client get(Long clientId);
}
