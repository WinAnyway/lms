package pl.com.kubachmielowiec.model.clients;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.LoanRaport;
import pl.com.kubachmielowiec.application.LoaningProcess;
import pl.com.kubachmielowiec.application.Ranking;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoaningProcess loaningProcess;

    public LoanController(LoaningProcess loaningProcess) {
        this.loaningProcess = loaningProcess;
    }

    @PostMapping("/loan/{publicationId}/{clientId}")
    public void loan(@PathVariable Long publicationId, @PathVariable Long clientId) {
        loaningProcess.loan(publicationId, clientId);
    }

    @PutMapping("/giveback/{publicationId}/{clientId}")
    public void giveBack(@PathVariable Long publicationId, @PathVariable Long clientId) {
        loaningProcess.giveBack(publicationId, clientId);
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
