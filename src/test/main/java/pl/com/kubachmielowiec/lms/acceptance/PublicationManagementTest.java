package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.PublicationsManagement;
import pl.com.kubachmielowiec.model.commands.CreatePublicationCommand;
import pl.com.kubachmielowiec.model.commands.UpdatePublicationCommand;
import pl.com.kubachmielowiec.model.publications.Publication;
import pl.com.kubachmielowiec.model.publications.PublicationRepository;
import pl.com.kubachmielowiec.model.publications.Publisher;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class PublicationManagementTest {

    @Autowired
    PublicationsManagement publicationsManagement;

    @Autowired
    PublicationRepository publicationRepository;

    @Test
    public void shouldCreatePublication() {
        Long id = createPublication();

        Publication publication = publicationRepository.get(id);
        assertThat(publication.getTitle()).isEqualTo("Dżemik");
        assertThat(publication.getDescription()).isEqualTo("dobry");
    }

    @Test
    public void shouldDeletePublication() {
        //given
        Long id = createPublication();
        //when
        publicationsManagement.deletePublication(id);
        //then
        Publication publication = publicationRepository.get(id);
        assertThat(publication).isNull();
    }

    @Test
    public void shouldChangePublication() {
        //given
        Long id = createPublication();
        //when
        UpdatePublicationCommand cmd = new UpdatePublicationCommand();
        cmd.setId(id);
        cmd.setTitle("test");
        cmd.setIsbn("123");
        publicationsManagement.updatePublication(cmd);

        Publication publication = publicationRepository.get(id);
        assertThat(publication.getTitle()).isEqualTo("test");
        assertThat(publication.getIsbn()).isEqualTo("123");
    }

    @Test
    public void shouldGenerateCodesForPublication() {
        //given
        Long id = createPublication();
        //when
        publicationsManagement.addCopiesOf(id, 3);
        //then

    }

    private Long createPublication() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        cmd.setTitle("Dżemik");
        cmd.setDescription("dobry");
        cmd.setIsbn("asdasd");
        cmd.setPublished(Year.of(1993));
        cmd.setPublisher(new Publisher("Fabryka Słów"));
        return publicationsManagement.createPublication(cmd);
    }

}
