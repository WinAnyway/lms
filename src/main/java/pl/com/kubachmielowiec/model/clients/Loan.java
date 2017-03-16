package pl.com.kubachmielowiec.model.clients;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Embeddable
public class Loan {

    /*@Id
    @GeneratedValue
    private Long id;*/

    private Long publicationId;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate loanDate;

    public Loan(Long publicationId) {
        this.publicationId = publicationId;
        loanDate = LocalDate.now();
    }

    public boolean hasExpired() {
        return ChronoUnit.DAYS.between(loanDate, LocalDate.now()) > 31;
    }

    public Long getPublicationName() {
        return publicationId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }
}
