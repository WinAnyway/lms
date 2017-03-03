package pl.com.kubachmielowiec.model;

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
