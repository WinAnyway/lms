package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Barcode;

import java.util.List;

public interface PublicationsManagement {

    Long createPublication(CreatePublicationCommand cmd);

    void updatePublication(UpdatePublicationCommand cmd);

    void deletePublication(Long publicationId);

    List<Barcode> addCopiesOf(Long publicationId, Integer numberOfCopies);

    void deleteCopy(Barcode barcode);

}
