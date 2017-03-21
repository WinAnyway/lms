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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) return false;
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
        return lastName != null ? lastName.equals(author.lastName) : author.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
