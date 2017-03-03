package pl.com.kubachmielowiec.model.clients;

import pl.com.kubachmielowiec.model.publications.Publication;

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

    public Publication getPublication() {
        return publication;
    }

    public Date getLoanDate() {
        return loanDate;
    }
}
