package pl.com.kubachmielowiec.model;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class ISBN {

    String isbn;

    public ISBN() {
        this.isbn = UUID.randomUUID().toString();
    }

    public String getIsbn() {
        return isbn;
    }
}
