package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.application.dtos.PublicationDto;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;

public interface PublicationsManagement {

    Long createPublication(CreatePublicationCommand cmd);

    void updatePublication(UpdatePublicationCommand cmd);

    void deletePublication();

    PublicationDto getPublication(Long publicationId);

}
