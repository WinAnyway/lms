package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.model.Genre;
import pl.com.kubachmielowiec.model.PublicationCode;

public interface Administration {

    void manageAuthors(Long authorId);

    void manageGenres(Genre genre);

    void manageClients(Long ClientId);

    void managePublications(Long publicationId);

    void manageCopies(PublicationCode publicationCode);

}
