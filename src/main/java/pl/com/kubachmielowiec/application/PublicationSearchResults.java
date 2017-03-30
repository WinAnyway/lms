package pl.com.kubachmielowiec.application;

import pl.com.kubachmielowiec.application.dtos.PublicationDto;

import java.util.List;

public class PublicationSearchResults {

    private List<PublicationDto> publications;
    private Long pagesCount;
    private Integer pageNumber;
    private Integer perPage;

    public List<PublicationDto> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationDto> publications) {
        this.publications = publications;
    }

    public Long getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Long pagesCount) {
        this.pagesCount = pagesCount;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
