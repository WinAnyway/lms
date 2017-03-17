package pl.com.kubachmielowiec.application.dtos;

import pl.com.kubachmielowiec.model.clients.Address;

public class ClientDto {
    private String firstName;
    private String lastName;
    private String pesel;
    private String idNumber;
    private Address address;
    private String email;
    private String phoneNumber;
//    private Set<LoanDto> loans;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

//    public void setLoans(Set<LoanDto> loans) {
//        this.loans = loans;
//    }
//
//    public Set<LoanDto> getLoans() {
//        return loans;
//    }
}
