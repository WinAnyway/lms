package pl.com.kubachmielowiec.lms.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Publication;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PublicationTest {

    @Test
    public void shouldNotBeAvailableAfterLoan() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        Publication publication = new Publication(cmd);

        publication.loan();

        assertThat(publication.isAvailable()).isFalse();
    }

    @Test
    public void shouldBeAvailableAfterGivingBack() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        Publication publication = new Publication(cmd);
        publication.loan();

        publication.giveBack();

        assertThat(publication.isAvailable()).isTrue();
    }

    @Test
    public void shouldBeChangedAfterChange() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        cmd.setTitle("dzieci z bullebryn");
        cmd.setDescription("lektura z liceum");
        Publication publication = new Publication(cmd);

        UpdatePublicationCommand updatePublicationCommand = new UpdatePublicationCommand();
        updatePublicationCommand.setTitle("Dzieci z Bullerbyn");
        updatePublicationCommand.setDescription("Lektura z podstawowki");
        publication.change(updatePublicationCommand);

        assertThat(publication.getTitle()).isEqualTo("Dzieci z Bullerbyn");
        assertThat(publication.getDescription()).isEqualTo("Lektura z podstawowki");
    }

}
