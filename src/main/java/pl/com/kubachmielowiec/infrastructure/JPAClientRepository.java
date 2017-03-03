package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public void remove(Long clientId) {
        Query query = entityManager.createQuery("DELETE FROM Client c WHERE c.id = :id");
        query.setParameter("id", clientId);
        query.executeUpdate();
    }
}
