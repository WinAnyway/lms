package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.application.dtos.LoanDto;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;

import java.util.List;

public class RaportGenerator {

    private LoansRepository loansRepository;

    public RaportGenerator(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    public LoanRaport generateExpiredRaport() {
        LoanRaport loanRaport = new LoanRaport();
        List<Loan> activeLoans = loansRepository.getActiveLoans();
        for(Loan loan : activeLoans) {
            if(loan.hasExpired()) {
                loanRaport.add(changeLoanToDto(loan));
            }
        }
        return loanRaport;
    }

    private LoanDto changeLoanToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setPublication(loan.getPublication().getTitle());
        dto.setClientId(loan.getClient().getId());
        return dto;
    }

}
