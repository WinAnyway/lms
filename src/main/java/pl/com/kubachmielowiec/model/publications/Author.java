package pl.com.kubachmielowiec.model.publications;

import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Author {

    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;
    private String nationality;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deathDate;

    public Author(CreateAuthorCommand cmd) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public Long getId() {
        return id;
    }
}
