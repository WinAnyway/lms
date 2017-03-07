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
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JPAPublicationCatalog implements PublicationCatalog {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PublicationSearchResults search(PublicationQuery publicationQuery) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        PublicationSearchResults results = new PublicationSearchResults();
        List<PublicationDto> dtos = queryPublications(publicationQuery, criteriaBuilder);
        results.setPublications(dtos);
        return results;
    }

    private List<PublicationDto> queryPublications(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<Publication> criteriaQuery = criteriaBuilder.createQuery(Publication.class);
        Root<Publication> root = criteriaQuery.from(Publication.class);
        root.fetch("authors", JoinType.LEFT);
        root.fetch("genres", JoinType.LEFT);
        Set<Predicate> predicates = createPredicates(publicationQuery, criteriaBuilder, root);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Set<Predicate> createPredicates(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root) {
        Set<Predicate> predicates = new HashSet<>();
        addPhrasePredicate(publicationQuery, criteriaBuilder, root, predicates);
        addIsbnPredicate(publicationQuery, criteriaBuilder, root, predicates);
        addPublisherPredicate(publicationQuery, criteriaBuilder, root, predicates);
        addPublicationYearPredicate(publicationQuery, criteriaBuilder, root, predicates);
        addGenrePredicate(publicationQuery, criteriaBuilder, root, predicates);
        addAuthorPredicate(publicationQuery, criteriaBuilder, root, predicates);
        return predicates;
    }

    private void addAuthorPredicate(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root, Set<Predicate> predicates) {
        if (publicationQuery.getAuthor() != null) {
            //TODO
            predicates.add(criteriaBuilder.isMember(publicationQuery.getAuthor(), root.get("authors.firstName")));
        }
    }

    private void addGenrePredicate(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root, Set<Predicate> predicates) {
        if (publicationQuery.getGenre() != null) {
            predicates.add(criteriaBuilder.isMember(publicationQuery.getGenre(), root.get("genres")));
        }
    }

    private void addPublicationYearPredicate(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root, Set<Predicate> predicates) {
        if (publicationQuery.getPublicationYear() != null) {
            predicates.add(criteriaBuilder.equal(root.get("publicationYear"), publicationQuery.getPublicationYear()));
        }
    }

    private void addPublisherPredicate(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root, Set<Predicate> predicates) {
        if (publicationQuery.getPublisher() != null) {
            predicates.add(criteriaBuilder.equal(root.get("publisher.name"), publicationQuery.getPublisher()));
        }
    }

    private void addIsbnPredicate(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root, Set<Predicate> predicates) {
        if (publicationQuery.getIsbn() != null) {
            predicates.add(criteriaBuilder.equal(root.get("isbn"), publicationQuery.getIsbn()));
        }
    }

    private void addPhrasePredicate(PublicationQuery publicationQuery, CriteriaBuilder criteriaBuilder, Root<Publication> root, Set<Predicate> predicates) {
        if (publicationQuery.getPhrase() != null) {
            String likeExpression = "%" + publicationQuery.getPhrase() + "%";
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(root.get("title"), likeExpression),
                    criteriaBuilder.like(root.get("description"), likeExpression)));
        }
    }

    @Override
    public PublicationDto get(Long publicationId) {
        Publication publication = entityManager.find(Publication.class, publicationId);
        PublicationDto dto = createPublicationDto(publication);
        return dto;
    }

    private PublicationDto createPublicationDto(Publication publication) {
        PublicationDto dto = new PublicationDto();
        dto.setTitle(publication.getTitle());
        dto.setDescription(publication.getDescription());
        dto.setAuthors(changeAuthorsToDtos(publication.getAuthors()));
        dto.setIsbn(publication.getIsbn());
        dto.setPublished(publication.getPublicationYear());
        dto.setPublisher(publication.getPublisher().toString());
        dto.setGenres(changeGenresToStrings(publication.getGenres()));
        dto.setAvailable(publication.isAvailable());
        return dto;
    }

    private Set<String> changeGenresToStrings(Set<Genre> genres) {
        Set<String> genreStrings = new HashSet<>();
        for (Genre genre : genres)
            genreStrings.add(genre.getName());
        return genreStrings;
    }

    private Set<AuthorDto> changeAuthorsToDtos(Set<Author> authors) {
        Set<AuthorDto> dtos = new HashSet<>();
        for (Author author : authors) {
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
