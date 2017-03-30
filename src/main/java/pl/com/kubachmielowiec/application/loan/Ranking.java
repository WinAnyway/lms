package pl.com.kubachmielowiec.application.loan;

import pl.com.kubachmielowiec.model.publications.Publication;

import java.util.List;

public class Ranking {

    private List<Publication> publications;

    public Ranking(List<Publication> publications) {

        this.publications = publications;
    }

    public List<Publication> getLoansCounts() {
        return publications;
    }
}
