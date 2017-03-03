package pl.com.kubachmielowiec.model.clients;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
/*
    @Id
    @GeneratedValue
    Long id;*/

    String country;
    String city;
    String street;
    String homeNumber;
    String postalCode;
}
