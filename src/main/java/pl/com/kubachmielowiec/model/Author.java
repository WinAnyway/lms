package pl.com.kubachmielowiec.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Author {

    @Id
    @GeneratedValue
    Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deathDate;
}
