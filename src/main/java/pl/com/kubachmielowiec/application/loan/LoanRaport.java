package pl.com.kubachmielowiec.application.loan;

import pl.com.kubachmielowiec.application.dtos.LoanDto;

import java.util.LinkedList;
import java.util.List;

public class LoanRaport {

    private List<LoanDto> expiredLoans;

    public LoanRaport() {
        expiredLoans = new LinkedList<>();
    }

    public void add(LoanDto loanDto) {
        expiredLoans.add(loanDto);
    }

    public List<LoanDto> getExpiredLoans() {
        return expiredLoans;
    }
}
