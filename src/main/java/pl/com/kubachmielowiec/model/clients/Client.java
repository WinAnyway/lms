package pl.com.kubachmielowiec.model.clients;

import javax.persistence.*;
import java.util.Collection;

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
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Loan> loans;

    public Collection<Loan> getLoans() {
        return loans;
    }

}
