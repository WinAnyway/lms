package pl.com.kubachmielowiec.model.publications;

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
}
