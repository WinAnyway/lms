package pl.com.kubachmielowiec.application.publicationsearch;

import pl.com.kubachmielowiec.application.dtos.PublicationDto;

public interface PublicationCatalog {

    PublicationSearchResults search(PublicationQuery publicationQuery);

    PublicationDto get(Long publicationId);

}
