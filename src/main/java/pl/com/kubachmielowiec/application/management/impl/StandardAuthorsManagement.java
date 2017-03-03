package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.dtos.AuthorDto;
import pl.com.kubachmielowiec.application.management.AuthorsManagement;
import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;
import pl.com.kubachmielowiec.model.commands.UpdateAuthorCommand;
import pl.com.kubachmielowiec.model.publications.Author;
import pl.com.kubachmielowiec.model.publications.AuthorRepository;

public class StandardAuthorsManagement implements AuthorsManagement{

    AuthorRepository authorRepository;

    public StandardAuthorsManagement(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Long createAuthor(CreateAuthorCommand cmd) {
        Author author = new Author(cmd);
        authorRepository.put(author);
        return author.getId();
    }

    @Override
    @Transactional
    public void updateAuthor(UpdateAuthorCommand cmd) {
        Author author = authorRepository.get(cmd.getAuthorId());
        author.update(cmd);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        authorRepository.remove(authorId);
    }

    @Override
    public AuthorDto getAuthor(Long authorId) {
        return null;
    }
}
