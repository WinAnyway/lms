package pl.com.kubachmielowiec.model;

public interface PublicationRepository {

    void put(Publication publication);

    Publication get(Long id);
}
