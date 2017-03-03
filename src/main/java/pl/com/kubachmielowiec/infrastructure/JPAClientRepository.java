package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.Client;
import pl.com.kubachmielowiec.model.ClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JPAClientRepository implements ClientRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void put(Client client) {
        entityManager.persist(client);
    }

    @Override
    public Client get(Long clientId) {
        return entityManager.find(Client.class, clientId);
    }
}
