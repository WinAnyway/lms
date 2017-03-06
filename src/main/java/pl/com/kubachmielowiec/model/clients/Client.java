package pl.com.kubachmielowiec.model.clients;

import pl.com.kubachmielowiec.model.commands.CreateClientCommand;
import pl.com.kubachmielowiec.model.commands.UpdateClientCommand;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Client {

    @Id
    @GeneratedValue
    Long id;

    String firstName;
    String lastName;

    @Embedded
    Address address;
    String pesel;
    String idNumber;
    String phoneNumber;
    String email;

    @Embedded
    private Collection<Loan> loans;

    public Client(CreateClientCommand cmd) {
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.address = cmd.getAddress();
        this.pesel = cmd.getPesel();
        this.idNumber = cmd.getIdNumber();
        this.phoneNumber = cmd.getPhoneNumber();
        this.email = cmd.getEmail();
        this.loans = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPesel() {
        return pesel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Loan> getLoans() {
        return loans;
    }

    public void update(UpdateClientCommand cmd) {
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.address = cmd.getAddress();
        this.pesel = cmd.getPesel();
        this.idNumber = cmd.getIdNumber();
        this.phoneNumber = cmd.getPhoneNumber();
        this.email = cmd.getEmail();
        this.loans = cmd.getLoans();
    }
}
