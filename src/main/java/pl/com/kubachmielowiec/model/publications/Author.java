package pl.com.kubachmielowiec.model.publications;

import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;
import pl.com.kubachmielowiec.model.commands.UpdateAuthorCommand;

import javax.persistence.*;
import java.time.Year;

@Entity
public class Author {

    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;
    private String nationality;

    private Year birthDate;
    private Year deathDate;

    Author(){}

    public Author(CreateAuthorCommand cmd) {
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.nationality = cmd.getNationality();
        this.birthDate = cmd.getBirthDate();
        this.deathDate = cmd.getDeathDate();
    }

    public void update(UpdateAuthorCommand cmd) {
        this.firstName = cmd.getFirstName();
        this.lastName = cmd.getLastName();
        this.nationality = cmd.getNationality();
        this.birthDate = cmd.getBirthDate();
        this.deathDate = cmd.getDeathDate();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public Year getBirthDate() {
        return birthDate;
    }

    public Year getDeathDate() {
        return deathDate;
    }

    public Long getId() {
        return id;
    }

}
