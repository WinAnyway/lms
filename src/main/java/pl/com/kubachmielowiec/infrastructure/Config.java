package pl.com.kubachmielowiec.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.application.impl.StandardLoaningProcess;
import pl.com.kubachmielowiec.application.management.AuthorsManagement;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.application.management.GenresManagement;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardAuthorsManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardClientsManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardGenresManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardPublicationsManagement;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.clients.LoansRepository;
import pl.com.kubachmielowiec.model.publications.AuthorRepository;
import pl.com.kubachmielowiec.model.publications.GenresRepository;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

@Configuration
public class Config {

    @Bean
    public LoaningProcess loaningProcess(PublicationRepository publicationRepository, ClientRepository clientRepository, LoansRepository loansRepository,
                                         RaportGenerator raportGenerator, RankingGenerator rankingGenerator, ClientReminder clientReminder) {
        return new StandardLoaningProcess(publicationRepository, clientRepository, loansRepository, raportGenerator, rankingGenerator, clientReminder);
    }



    @Bean
    public AuthorsManagement authorsManagement(AuthorRepository authorRepository){
        return new StandardAuthorsManagement(authorRepository);
    }

    @Bean
    public ClientsManagement clientsManagement(ClientRepository clientRepository) {
        return new StandardClientsManagement(clientRepository);
    }

    @Bean
    public GenresManagement genresManagement(GenresRepository genresRepository) {
        return new StandardGenresManagement(genresRepository);
    }

    @Bean
    PublicationsManagement publicationsManagement(PublicationRepository publicationRepository) {
        return new StandardPublicationsManagement(publicationRepository);
    }

    @Bean
    PublicationCatalog publicationCatalog() {
        return new JPAPublicationCatalog();
    }

    @Bean
    LoansRepository loansRepository() {
        return new JPALoansRepository();
    }

    @Bean
    public GenresRepository genresRepository() {
        return new JPAGenresRepository();
    }

    @Bean
    public AuthorRepository authorRepository() {
        return new JPAAuthorRepository();
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
    public RaportGenerator raportGenerator(LoansRepository loansRepository) {
        return new RaportGenerator(loansRepository);
    }

    @Bean
    public RankingGenerator rankingGenerator(LoansRepository loansRepository) {
        return new RankingGenerator(loansRepository);
    }

    @Bean
    public ClientReminder clientReminder(LoansRepository loansRepository, ClientRepository clientRepository) {
        return new ClientReminder(loansRepository, clientRepository);
    }
}
