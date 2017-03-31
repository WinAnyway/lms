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
import pl.com.kubachmielowiec.model.publications.*;

import java.time.Year;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublicationManagementTest {

    @Autowired
    PublicationsManagement publicationsManagement;

    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    CopyRepository copyRepository;

    @Test
    @Transactional
    public void shouldCreatePublication() {
        Long id = createPublication();

        Publication publication = publicationRepository.get(id);
        assertThat(publication.getTitle()).isEqualTo("tescik");
        assertThat(publication.getDescription()).isEqualTo("tescik");
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
    @Transactional
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
    @Transactional
    public void shouldCreateCopiesOfPublication() {
        //given
        Long id = createPublication();
        //when
        publicationsManagement.addCopiesOf(id, 2);
        //then
        Publication publication = publicationRepository.get(id);
        List<Copy> copies = copyRepository.getAvailableCopiesOf(publication);

        assertThat(copies.size()).isEqualTo(2);
        assertThat(copies.get(0).getPublication()).isEqualTo(publication);
        assertThat(copies.get(1).getPublication()).isEqualTo(publication);
    }

    @Test
    public void shouldDeleteCopyOfPublication() {
        Long id = createPublication();
        publicationsManagement.addCopiesOf(id, 2);
        Publication publication = publicationRepository.get(id);
        List<Copy> copies = copyRepository.getAvailableCopiesOf(publication);
        //when
        publicationsManagement.deleteCopy(copies.get(0).getBarcode());
        copies = copyRepository.getAvailableCopiesOf(publication);
        //then
        assertThat(copies.size()).isEqualTo(1);
        assertThat(copies.get(0).getPublication()).isEqualTo(publication);
    }



    private Long createPublication() {
        CreatePublicationCommand cmd = new CreatePublicationCommand();
        cmd.setTitle("tescik");
        cmd.setDescription("tescik");
        cmd.setIsbn("asdasd");
        cmd.setPublished(Year.of(1993));
        cmd.setPublisher(new Publisher("Fabryka Słów"));
        return publicationsManagement.createPublication(cmd);
    }

}
