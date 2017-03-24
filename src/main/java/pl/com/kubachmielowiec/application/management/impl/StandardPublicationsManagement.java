package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.*;

import java.util.LinkedList;
import java.util.List;

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
    public List<Barcode> addCopiesOf(Long publicationId, Integer numberOfCopies) {
        List<Barcode> barcodes = new LinkedList<>();
        Publication publication = publicationRepository.get(publicationId);
        createCopies(numberOfCopies, barcodes, publication);
        return barcodes;
    }

    private void createCopies(Integer numberOfCopies, List<Barcode> barcodes, Publication publication) {
        for(int i = 0; i < numberOfCopies; i++) {
            Copy copy = new Copy(publication);
            barcodes.add(copy.getBarcode());
            copyRepository.put(copy);
        }
    }

    @Override
    public void deleteCopy(Barcode barcode) {
        copyRepository.remove(barcode);
    }

}
