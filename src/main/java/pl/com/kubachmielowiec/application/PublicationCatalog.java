package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.application.dtos.PublicationDto;

public interface PublicationCatalog {

    PublicationSearchResults search(PublicationQuery publicationQuery);

    PublicationDto get(Long publicationId);

}
