package pl.com.kubachmielowiec.ui.controller;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.management.GenresManagement;
import pl.com.kubachmielowiec.model.publications.Genre;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenresManagement genresManagement;

    public GenreController(GenresManagement genresManagement) {
        this.genresManagement = genresManagement;
    }

    @PostMapping
    public Long create(@RequestBody String name) {
        return genresManagement.createGenre(name);
    }

    @GetMapping("/{genreId}")
    public Genre show(@PathVariable Long genreId) {
        return genresManagement.getGenre(genreId);
    }

    @PutMapping("/{genreId}")
    public void update(@PathVariable Long genreId, @RequestBody String newGenreName) {
        genresManagement.updateGenre(newGenreName, genreId);
    }

    @DeleteMapping("/{genreId}")
    public void destroy(@PathVariable Long genreId) {
        genresManagement.deleteGenre(genreId);
    }

}
