package pl.com.kubachmielowiec.model.clients;

import pl.com.kubachmielowiec.model.publications.Copy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COPY_BARCODE")
    private Copy copy;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    private LocalDate loanDate;
    private boolean active;

    Loan() {
    }

    public Loan(Copy copy, Client client, LocalDate loanDate, boolean active) {
        this.copy = copy;
        this.client = client;
        this.loanDate = loanDate;
        this.active = active;
    }

    public Loan(Copy copy, Client client) {
        this.copy = copy;
        loanDate = LocalDate.now();
        this.client = client;
        this.loanDate = LocalDate.now();
        this.active = true;
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

    public Copy getCopy() {
        return copy;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (!id.equals(loan.id)) return false;
        if (!copy.equals(loan.copy)) return false;
        return client.equals(loan.client);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + copy.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }
}
