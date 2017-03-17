package pl.com.kubachmielowiec.model.commands;

import java.time.Year;

public class CreateAuthorCommand {
    private String firstName;
    private String lastName;
    private String nationality;
    private Year birthDate;
    private Year deathDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Year getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Year birthDate) {
        this.birthDate = birthDate;
    }

    public Year getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Year deathDate) {
        this.deathDate = deathDate;
    }
}
