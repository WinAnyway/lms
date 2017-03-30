package pl.com.kubachmielowiec.application.loan;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.model.clients.LoansRepository;

@Transactional
public class RankingGenerator {

    private LoansRepository loansRepository;

    public RankingGenerator(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    public Ranking generateRanking() {
        return new Ranking(loansRepository.countLoans());
    }
}
