package pl.com.kubachmielowiec.application.dtos;

import java.time.LocalDate;

public class LoanDto {
    private LocalDate loanDate;
    private String copy;
    private Long id;
    private Long clientId;

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getCopy() {
        return copy;
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
