package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.dtos.ClientDto;
import pl.com.kubachmielowiec.application.dtos.LoanDto;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.model.clients.Client;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.UpdateClientCommand;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Transactional
public class StandardClientsManagement implements ClientsManagement{

    ClientRepository clientRepository;

    public StandardClientsManagement(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long createClient(CreateClientCommand cmd) {
        Client client = new Client(cmd);
        clientRepository.put(client);
        return client.getId();
    }

    @Override
    public void updateClient(UpdateClientCommand cmd) {
        Client client = clientRepository.get(cmd.getClientId());
        client.update(cmd);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.remove(clientId);
    }

    @Override
    public ClientDto getClient(Long clientId) {
        Client client = clientRepository.get(clientId);
        ClientDto dto = new ClientDto();
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setPesel(client.getPesel());
        dto.setIdNumber(client.getIdNumber());
        dto.setAddress(client.getAddress());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());
//        dto.setLoans(changeLoansToDtos(client.getLoans()));
        return dto;
    }

    private Set<LoanDto> changeLoansToDtos(Collection<Loan> loans) {
        Set<LoanDto> dtos = new HashSet<>();
        for(Loan loan : loans) {
            LoanDto dto = new LoanDto();
            dto.setPublication(loan.getPublication().getId());
            dto.setLoanDate(loan.getLoanDate());
            dtos.add(dto);
        }
        return dtos;
    }
}
