package pl.com.kubachmielowiec.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.application.impl.StandardLoaningProcess;
import pl.com.kubachmielowiec.model.ClientRepository;
import pl.com.kubachmielowiec.model.PublicationRepository;

@Configuration
public class Config {

    @Bean
    public LoaningProcess loaningProcess(PublicationRepository publicationRepository, ClientRepository clientRepository,
                                         RaportGenerator raportGenerator, RankingGenerator rankingGenerator, ClientReminder clientReminder) {
        return new StandardLoaningProcess(publicationRepository, clientRepository, raportGenerator, rankingGenerator, clientReminder);
    }

    @Bean
    public PublicationRepository publicationRepository(){
        return new JPAPublicationRepository();
    }

    @Bean
    public ClientRepository clientRepository(){
        return new JPAClientRepository();
    }

    @Bean
    public RaportGenerator raportGenerator() {
        return new RaportGenerator();
    }

    @Bean
    public RankingGenerator rankingGenerator() {
        return new RankingGenerator();
    }

    @Bean
    public ClientReminder clientReminder() {
        return new ClientReminder();
    }
}
