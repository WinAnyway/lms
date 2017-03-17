package pl.com.kubachmielowiec.application.dtos;

import java.time.LocalDate;

public class LoanDto {
    private LocalDate loanDate;
    private Long publication;

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setPublication(Long publication) {
        this.publication = publication;
    }

    public Long getPublication() {
        return publication;
    }
}
