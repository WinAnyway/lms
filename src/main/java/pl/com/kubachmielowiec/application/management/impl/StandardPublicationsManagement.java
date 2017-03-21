package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Publication;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;

@Transactional
public class StandardPublicationsManagement implements PublicationsManagement{

    private PublicationRepository publicationRepository;

    public StandardPublicationsManagement(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
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
    public void generateCodesFor(Long publicationId, Long numberOfCopies) {

    }

}
