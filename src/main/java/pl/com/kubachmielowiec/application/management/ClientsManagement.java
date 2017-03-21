package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.UpdateClientCommand;

public interface ClientsManagement {

    Long createClient(CreateClientCommand cmd);

    void updateClient(UpdateClientCommand cmd);

    void deleteClient(Long clientId);

    Client getClient(Long clientId);

}
