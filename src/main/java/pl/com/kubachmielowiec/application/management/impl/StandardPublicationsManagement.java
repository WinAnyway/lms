package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.*;

@Transactional
public class StandardPublicationsManagement implements PublicationsManagement{

    private PublicationRepository publicationRepository;
    private CopyRepository copyRepository;

    public StandardPublicationsManagement(PublicationRepository publicationRepository, CopyRepository copyRepository) {
        this.publicationRepository = publicationRepository;
        this.copyRepository = copyRepository;
    }

    @Override
    public Long createPublication(CreatePublicationCommand cmd) {
        Publication publication = new Publication(cmd);
        publicationRepository.put(publication);
        return publication.getId();
    }

    @Override
    public void updatePublication(UpdatePublicationCommand cmd) {
        Publication publication = publicationRepository.get(cmd.getId());
        publication.change(cmd);
    }

    @Override
    public void deletePublication(Long publicationId) {
        publicationRepository.remove(publicationId);
    }

    @Override
    public void addCopiesOf(Long publicationId, Integer numberOfCopies) {
        for(int i = 0; i < numberOfCopies; i++) {
            Publication publication = publicationRepository.get(publicationId);
            Copy copy = new Copy(publication);
            copyRepository.put(copy);
        }
    }

    @Override
    public void deleteCopy(Barcode barcode) {
        copyRepository.remove(barcode);
    }

}
