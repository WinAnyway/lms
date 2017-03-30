package pl.com.kubachmielowiec.ui.controller;

import org.springframework.web.bind.annotation.*;
import pl.com.kubachmielowiec.application.dtos.AuthorDto;
import pl.com.kubachmielowiec.application.management.AuthorsManagement;
import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;
import pl.com.kubachmielowiec.model.commands.UpdateAuthorCommand;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorsManagement authorsManagement;

    public AuthorController(AuthorsManagement authorsManagement) {
        this.authorsManagement = authorsManagement;
    }

    @PostMapping
    public Long create(@RequestBody CreateAuthorCommand cmd) {
        return authorsManagement.createAuthor(cmd);
    }

    @GetMapping("/{authorId}")
    public AuthorDto show(@PathVariable Long authorId) {
        return authorsManagement.getAuthor(authorId);
    }

    @PutMapping("/{authorId}")
    public void update(@PathVariable Long authorId, @RequestBody UpdateAuthorCommand cmd) {
        cmd.setAuthorId(authorId);
        authorsManagement.updateAuthor(cmd);
    }

    @DeleteMapping("/{authorId}")
    public void destroy(@PathVariable Long authorId) {
        authorsManagement.deleteAuthor(authorId);
    }
}
