package pl.com.kubachmielowiec.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.dtos.ClientDto;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.UpdateClientCommand;

@RestController
@RequestMapping("/clients")
public class ClientController {

    ClientsManagement clientsManagement;

    public ClientController(ClientsManagement clientsManagement) {
        this.clientsManagement = clientsManagement;
    }

    @PostMapping
    public Long create(@RequestBody CreateClientCommand cmd) {
        return clientsManagement.createClient(cmd);
    }

    @GetMapping("/{clientId}")
    public ClientDto show(@PathVariable Long clientId) {
        return clientsManagement.getClient(clientId);
    }

    @PutMapping("/{clientId}")
    public void update(@PathVariable Long clientId, @RequestBody UpdateClientCommand cmd) {
        cmd.setClientId(clientId);
        clientsManagement.updateClient(cmd);
    }

    @DeleteMapping("/{clientId}")
    public void destroy(@PathVariable Long clientId) {
        clientsManagement.deleteClient(clientId);
    }
}
