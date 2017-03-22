package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Barcode;

public interface PublicationsManagement {

    Long createPublication(CreatePublicationCommand cmd);

    void updatePublication(UpdatePublicationCommand cmd);

    void deletePublication(Long publicationId);

    void addCopiesOf(Long publicationId, Integer numberOfCopies);

    void deleteCopy(Barcode barcode);

}
