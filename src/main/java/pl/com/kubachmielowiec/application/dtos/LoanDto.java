package pl.com.kubachmielowiec.application.dtos;

import java.time.LocalDate;

public class LoanDto {
    private LocalDate loanDate;
    private String publication;
    private Long id;
    private Long clientId;

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPublication() {
        return publication;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }
}
