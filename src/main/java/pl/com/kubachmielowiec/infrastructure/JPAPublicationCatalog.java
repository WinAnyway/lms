package pl.com.kubachmielowiec.infrastructure;

import pl.com.kubachmielowiec.application.PublicationCatalog;
import pl.com.kubachmielowiec.application.PublicationQuery;
import pl.com.kubachmielowiec.application.PublicationSearchResults;
import pl.com.kubachmielowiec.application.dtos.AuthorDto;
import pl.com.kubachmielowiec.application.dtos.PublicationDto;
import pl.com.kubachmielowiec.model.publications.Author;
import pl.com.kubachmielowiec.model.publications.Genre;
import pl.com.kubachmielowiec.model.publications.Publication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

public class JPAPublicationCatalog implements PublicationCatalog{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PublicationSearchResults search(PublicationQuery publicationQuery) {
        return null;
    }

    @Override
    public PublicationDto get(Long publicationId) {
        Publication publication = entityManager.find(Publication.class, publicationId);

        PublicationDto dto = new PublicationDto();
        dto.setTitle(publication.getTitle());
        dto.setDescription(publication.getDescription());
        dto.setAuthors(changeAuthorsToDtos(publication.getAuthors()));
        dto.setIsbn(publication.getIsbn());
        dto.setPublished(publication.getPublished());
        dto.setPublisher(publication.getPublisher().toString());
        dto.setGenres(changeGenresToStrings(publication.getGenres()));
        dto.setAvailable(publication.isAvailable());

        return dto;
    }

    private Set<String> changeGenresToStrings(Set<Genre> genres) {
        Set<String> genreStrings = new HashSet<>();
        for(Genre genre : genres)
            genreStrings.add(genre.getName());
        return genreStrings;
    }

    private Set<AuthorDto> changeAuthorsToDtos(Set<Author> authors) {
        Set<AuthorDto> dtos = new HashSet<>();
        for(Author author : authors) {
            AuthorDto dto = new AuthorDto();
            dto.setFirstName(author.getFirstName());
            dto.setLastName(author.getLastName());
            dto.setNationality(author.getNationality());
            dto.setBirthDate(author.getBirthDate());
            dto.setDeathDate(author.getDeathDate());
            dtos.add(dto);
        }
        return dtos;
    }
}
