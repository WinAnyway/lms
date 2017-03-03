package pl.com.kubachmielowiec.application.management;

import pl.com.kubachmielowiec.application.dtos.AuthorDto;
import pl.com.kubachmielowiec.model.commands.CreateAuthorCommand;
import pl.com.kubachmielowiec.model.commands.UpdateAuthorCommand;

public interface AuthorsManagement {

    Long createAuthor(CreateAuthorCommand cmd);

    void updateAuthor(UpdateAuthorCommand cmd);

    void deleteAuthor(Long authorId);

    AuthorDto getAuthor(Long authorId);

}
