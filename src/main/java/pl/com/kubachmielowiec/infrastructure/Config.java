package pl.com.kubachmielowiec.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.kubachmielowiec.application.*;
import pl.com.kubachmielowiec.application.impl.StandardLoaningProcess;
import pl.com.kubachmielowiec.application.management.AuthorsManagement;
import pl.com.kubachmielowiec.application.management.ClientsManagement;
import pl.com.kubachmielowiec.application.management.GenresManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardAuthorsManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardClientsManagement;
import pl.com.kubachmielowiec.application.management.impl.StandardGenresManagement;
import pl.com.kubachmielowiec.model.clients.ClientRepository;
import pl.com.kubachmielowiec.model.publications.AuthorRepository;
import pl.com.kubachmielowiec.model.publications.GenresRepository;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

@Configuration
public class Config {

    @Bean
    public LoaningProcess loaningProcess(PublicationRepository publicationRepository, ClientRepository clientRepository,
                                         RaportGenerator raportGenerator, RankingGenerator rankingGenerator, ClientReminder clientReminder) {
        return new StandardLoaningProcess(publicationRepository, clientRepository, raportGenerator, rankingGenerator, clientReminder);
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
