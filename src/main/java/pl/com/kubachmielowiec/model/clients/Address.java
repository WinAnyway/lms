package pl.com.kubachmielowiec.model.clients;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    String country;
    String city;
    String street;
    String homeNumber;
    String postalCode;

    Address(){}

    public Address(String country, String city, String street, String homeNumber, String postalCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.homeNumber = homeNumber;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

}
