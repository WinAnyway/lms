package pl.com.kubachmielowiec.model.publications;

public interface PublicationRepository {

    void put(Publication publication);

    Publication get(Long id);
}
