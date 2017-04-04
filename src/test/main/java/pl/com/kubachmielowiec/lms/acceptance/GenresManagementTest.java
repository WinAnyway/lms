package pl.com.kubachmielowiec.lms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.management.GenresManagement;
import pl.com.kubachmielowiec.model.publications.Genre;
import pl.com.kubachmielowiec.model.publications.GenresRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GenresManagementTest {

    @Autowired
    GenresManagement genresManagement;

    @Autowired
    GenresRepository genresRepository;

    @Test
    @Transactional
    public void shouldCreateGenre() {
        Long id = genresManagement.createGenre("Fantasy");

        assertThat(genresRepository.get(id).getName()).isEqualTo("Fantasy");
    }

    @Test
    public void shouldDeleteGenre() {
        Long id = genresManagement.createGenre("Fantasy");

        genresManagement.deleteGenre(id);

        assertThat(genresRepository.get(id)).isNull();
    }

    @Test
    @Transactional
    public void shouldUpdateGenre() {
        Long id = genresManagement.createGenre("Fantasy");

        genresManagement.updateGenre("Dramat", id);

        assertThat(genresRepository.get(id).getName()).isEqualTo("Dramat");
    }

    @Test
    @Transactional
    public void shouldShowGenre() {
        Long id = genresManagement.createGenre("Fantasy");

        Genre genre = genresManagement.getGenre(id);

        assertThat(genre.getName()).isEqualTo("Fantasy");
        assertThat(genre.getId()).isEqualTo(id);
    }
}
