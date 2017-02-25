package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.model.SearchCommand;

public interface SearchProcess {

    PublicationSearchResults search(SearchCommand searchCommand);

}
