package pl.com.kubachmielowiec.model.publications;

import javax.persistence.Embeddable;

@Embeddable
public class Publisher {

    String name;

    Publisher(){}

    Publisher(String publisherName) {
        this.name = publisherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
