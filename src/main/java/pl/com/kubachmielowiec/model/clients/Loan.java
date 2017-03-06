package pl.com.kubachmielowiec.model.clients;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class Loan {

    /*@Id
    @GeneratedValue
    private Long id;*/

    private String publicationName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;

    public String getPublicationName() {
        return publicationName;
    }

    public Date getLoanDate() {
        return loanDate;
    }
}
