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
    private boolean active;

    public Loan(Long publicationId) {
        this.publicationId = publicationId;
        this.loanDate = LocalDate.now();
        this.active = true;
    }

    public boolean hasExpired() {
        return ChronoUnit.DAYS.between(loanDate, LocalDate.now()) > 31;
    }

    public boolean isActive(){
        return active;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void deactivate() {
        this.active = false;
    }
}
