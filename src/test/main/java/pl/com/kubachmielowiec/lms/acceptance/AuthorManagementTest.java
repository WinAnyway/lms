package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.dtos.AuthorDto;
import pl.com.kubachmielowiec.application.management.AuthorsManagement;
import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;
import pl.com.kubachmielowiec.model.commands.UpdateAuthorCommand;
import pl.com.kubachmielowiec.model.publications.Author;
import pl.com.kubachmielowiec.model.publications.AuthorRepository;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorManagementTest {

    @Autowired
    AuthorsManagement authorsManagement;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    @Transactional
    public void shouldCreateAuthor() {
        Long id = createAuthor();

        Author author = authorRepository.get(id);

        assertThat(author.getId()).isEqualTo(id);
        assertThat(author.getFirstName()).isEqualTo("Janek");
        assertThat(author.getLastName()).isEqualTo("Dzbanek");
        assertThat(author.getBirthDate()).isEqualTo(Year.of(1993));
        assertThat(author.getDeathDate()).isNull();
        assertThat(author.getNationality()).isEqualTo("Polska");
    }

    @Test
    public void shouldDeleteAuthor() {
        Long id = createAuthor();

        authorsManagement.deleteAuthor(id);

        assertThat(authorRepository.get(id)).isNull();
    }

    @Test
    @Transactional
    public void shouldUpdateAuthor() {
        Long id = createAuthor();

        UpdateAuthorCommand cmd = new UpdateAuthorCommand();
        cmd.setAuthorId(id);
        cmd.setFirstName("Kuba");
        cmd.setLastName("Buba");
        authorsManagement.updateAuthor(cmd);

        Author author = authorRepository.get(id);
        assertThat(author.getId()).isEqualTo(id);
        assertThat(author.getFirstName()).isEqualTo("Kuba");
        assertThat(author.getLastName()).isEqualTo("Buba");
    }

    @Test
    @Transactional
    public void shouldGetAuthorDto() {
        Long id = createAuthor();

        AuthorDto author = authorsManagement.getAuthor(id);

        assertThat(author.getFirstName()).isEqualTo("Janek");
        assertThat(author.getLastName()).isEqualTo("Dzbanek");
        assertThat(author.getBirthDate()).isEqualTo(Year.of(1993));
        assertThat(author.getDeathDate()).isNull();
        assertThat(author.getNationality()).isEqualTo("Polska");
    }

    private Long createAuthor() {
        CreateAuthorCommand cmd = new CreateAuthorCommand();
        cmd.setFirstName("Janek");
        cmd.setLastName("Dzbanek");
        cmd.setBirthDate(Year.of(1993));
        cmd.setDeathDate(null);
        cmd.setNationality("Polska");
        return authorsManagement.createAuthor(cmd);
    }

}
