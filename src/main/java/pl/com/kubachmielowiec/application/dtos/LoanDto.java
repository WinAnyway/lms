package pl.com.kubachmielowiec.application.dtos;

import java.util.Date;

public class LoanDto {
    private Date loanDate;
    private String publication;

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPublication() {
        return publication;
    }
}
