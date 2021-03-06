package pl.com.kubachmielowiec.application.management.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.kubachmielowiec.application.dtos.AuthorDto;
import pl.com.kubachmielowiec.application.management.AuthorsManagement;
import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;
import pl.com.kubachmielowiec.model.commands.UpdateAuthorCommand;
import pl.com.kubachmielowiec.model.publications.Author;
import pl.com.kubachmielowiec.model.publications.AuthorRepository;

@Transactional
public class StandardAuthorsManagement implements AuthorsManagement{

    AuthorRepository authorRepository;

    public StandardAuthorsManagement(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Long createAuthor(CreateAuthorCommand cmd) {
        Author author = new Author(cmd);
        authorRepository.put(author);
        return author.getId();
    }

    @Override
    public void updateAuthor(UpdateAuthorCommand cmd) {
        Author author = authorRepository.get(cmd.getAuthorId());
        author.update(cmd);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.remove(authorId);
    }

    @Override
    public AuthorDto getAuthor(Long authorId) {
        Author author = authorRepository.get(authorId);
        return createAuthorDto(author);
    }

    private AuthorDto createAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setNationality(author.getNationality());
        authorDto.setBirthDate(author.getBirthDate());
        authorDto.setDeathDate(author.getDeathDate());
        return authorDto;
    }
}
