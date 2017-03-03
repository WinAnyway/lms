package pl.com.kubachmielowiec.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Publication publication;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;

}
