package pl.com.kubachmielowiec.model.clients;

import pl.com.kubachmielowiec.model.publications.Publication;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PUBLICATION_ID")
    private Publication publication;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    //    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate loanDate;
    private boolean active;

    Loan() {
    }

    public Loan(Publication publication, Client client, LocalDate loanDate, boolean active) {
        this.publication = publication;
        this.client = client;
        this.loanDate = loanDate;
        this.active = active;
    }

    public Loan(Publication publication, Client client) {
        this.publication = publication;
        loanDate = LocalDate.now();
        this.client = client;
        this.loanDate = LocalDate.now();
        this.active = true;
    }

    public boolean hasExpired() {
        return ChronoUnit.DAYS.between(loanDate, LocalDate.now()) > 31;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Publication getPublication() {
        return publication;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }
}
