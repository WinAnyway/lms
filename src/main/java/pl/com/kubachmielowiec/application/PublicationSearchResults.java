package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.application.dtos.PublicationDto;

import java.util.List;

public class PublicationSearchResults {

    List<PublicationDto> publications;

    public List<PublicationDto> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationDto> publications) {
        this.publications = publications;
    }
}
