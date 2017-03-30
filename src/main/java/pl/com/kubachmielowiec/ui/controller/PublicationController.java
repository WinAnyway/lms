package pl.com.kubachmielowiec.ui.controller;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.publicationsearch.PublicationCatalog;
import pl.com.kubachmielowiec.application.publicationsearch.PublicationQuery;
import pl.com.kubachmielowiec.application.publicationsearch.PublicationSearchResults;
import pl.com.kubachmielowiec.application.dtos.PublicationDto;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Barcode;

@RestController
@RequestMapping("/publications")
public class PublicationController {

    private PublicationsManagement publicationsManagement;
    private PublicationCatalog catalog;

    public PublicationController(PublicationsManagement publicationsManagement, PublicationCatalog catalog) {
        this.publicationsManagement = publicationsManagement;
        this.catalog = catalog;
    }

    @PostMapping
    public Long create(@RequestBody CreatePublicationCommand cmd) {
        return publicationsManagement.createPublication(cmd);
    }

    @GetMapping
    public PublicationSearchResults search(PublicationQuery publicationQuery) {
        PublicationSearchResults results = catalog.search(publicationQuery);
        System.out.println(results.getPublications().size());
        return results;
    }

    @GetMapping("/{publicationId}")
    public PublicationDto show(@PathVariable Long publicationId) {
        return catalog.get(publicationId);
    }

    @PutMapping("/{publicationId}")
    public void update(@PathVariable Long publicationId, @RequestBody UpdatePublicationCommand cmd) {
        cmd.setId(publicationId);
        publicationsManagement.updatePublication(cmd);
    }

    @DeleteMapping("/{publicationId}")
    public void destroy(@PathVariable Long publicationId) {
        publicationsManagement.deletePublication(publicationId);
    }

    @PostMapping("/copies/{publicationId}/{numberOfCopies}")
    public void addCopies(@PathVariable Long publicationId, @PathVariable Integer numberOfCopies) {
        publicationsManagement.addCopiesOf(publicationId, numberOfCopies);
    }

    @DeleteMapping("/copies/{barcode}")
    public void deleteCopy(@PathVariable String barcode) {
        publicationsManagement.deleteCopy(new Barcode(barcode));
    }
}
