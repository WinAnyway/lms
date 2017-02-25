package pl.com.kubachmielowiec.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Author {

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate birthDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate deathDate;
}
