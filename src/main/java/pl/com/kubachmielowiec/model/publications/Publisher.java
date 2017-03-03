package pl.com.kubachmielowiec.model.publications;

import javax.persistence.Embeddable;

@Embeddable
public class Publisher {

    String name;

    @Override
    public String toString() {
        return name;
    }
}
