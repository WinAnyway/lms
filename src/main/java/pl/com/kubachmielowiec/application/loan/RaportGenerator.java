package pl.com.kubachmielowiec.application.loan;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.dtos.LoanDto;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.clients.LoansRepository;

import java.util.List;

@Transactional
public class RaportGenerator {

    private LoansRepository loansRepository;

    public RaportGenerator(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    public LoanRaport generateExpiredRaport() {
        LoanRaport loanRaport = new LoanRaport();
        List<Loan> activeExpiredLoans = loansRepository.getActiveExpiredLoans();
        addPositionsToRaport(loanRaport, activeExpiredLoans);
        return loanRaport;
    }

    private void addPositionsToRaport(LoanRaport loanRaport, List<Loan> activeExpiredLoans) {
        for (Loan loan : activeExpiredLoans)
            loanRaport.add(changeLoanToDto(loan));
    }

    private LoanDto changeLoanToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setCopy(loan.getCopy().getBarcode().getCode());
        dto.setClientId(loan.getClient().getId());
        return dto;
    }

}
