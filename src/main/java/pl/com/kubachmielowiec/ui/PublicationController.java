package pl.com.kubachmielowiec.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.kubachmielowiec.application.PublicationCatalog;
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
}
