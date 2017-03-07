package pl.com.kubachmielowiec.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.PublicationCatalog;
import pl.com.kubachmielowiec.application.PublicationQuery;
import pl.com.kubachmielowiec.application.PublicationSearchResults;
import pl.com.kubachmielowiec.application.dtos.PublicationDto;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;

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
        return catalog.search(publicationQuery);
    }

    @GetMapping("/{publicationId}")
    public PublicationDto get(@PathVariable Long publicationId) {
        return catalog.get(publicationId);
    }
}
