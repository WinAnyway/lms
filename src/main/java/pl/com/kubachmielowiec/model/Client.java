package pl.com.kubachmielowiec.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
