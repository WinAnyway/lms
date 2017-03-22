package pl.com.kubachmielowiec.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.LoanRaport;
import pl.com.kubachmielowiec.application.LoaningProcess;
import pl.com.kubachmielowiec.application.Ranking;
import pl.com.kubachmielowiec.model.publications.Barcode;

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
}
