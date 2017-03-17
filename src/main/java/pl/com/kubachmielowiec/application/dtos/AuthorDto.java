package pl.com.kubachmielowiec.application.dtos;

import java.time.Year;

public class AuthorDto {

    private String firstName;
    private String lastName;
    private String nationality;
    private Year birthDate;
    private Year deathDate;

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

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setBirthDate(Year birthDate) {
        this.birthDate = birthDate;
    }

    public Year getBirthDate() {
        return birthDate;
    }

    public void setDeathDate(Year deathDate) {
        this.deathDate = deathDate;
    }

    public Year getDeathDate() {
        return deathDate;
    }
}
