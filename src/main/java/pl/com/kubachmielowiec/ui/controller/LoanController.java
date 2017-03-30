package pl.com.kubachmielowiec.ui.controller;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.loan.LoanRaport;
import pl.com.kubachmielowiec.application.loan.LoaningProcess;
import pl.com.kubachmielowiec.application.loan.Ranking;
import pl.com.kubachmielowiec.model.clients.Loan;
import pl.com.kubachmielowiec.model.publications.Barcode;

import java.util.Collection;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoaningProcess loaningProcess;

    public LoanController(LoaningProcess loaningProcess) {
        this.loaningProcess = loaningProcess;
    }

    @PostMapping("/loan/{barcode}/{clientId}")
    public void loan(@PathVariable String barcode, @PathVariable Long clientId) {
        loaningProcess.loan(new Barcode(barcode), clientId);
    }

    @PutMapping("/giveback/{barcode}/{clientId}")
    public void giveBack(@PathVariable String barcode, @PathVariable Long clientId) {
        loaningProcess.giveBack(new Barcode(barcode), clientId);
    }

    @PostMapping("/raport")
    public LoanRaport generateRaport() {
        return loaningProcess.generateExpiredReturnDateRaport();
    }

    @PostMapping("/ranking")
    public Ranking generateRanking() {
        return loaningProcess.generateTheMostLoanedRanking();
    }

    @PostMapping("/reminder/sms")
    public void remindClientsViaSms() {
        loaningProcess.remindClientsAboutReturnViaSms();
    }

    @PostMapping("/reminder/email")
    public void remindClientsViaEmail() {
        loaningProcess.remindClientsAboutReturnViaEmail();
    }

    @GetMapping("/history/{clientId}")
    public Collection<Loan> getCLientLoaningHistory(@PathVariable Long clientId) {
        return loaningProcess.getClientLoaningHistory(clientId);
    }
}
