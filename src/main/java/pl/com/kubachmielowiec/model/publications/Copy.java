package pl.com.kubachmielowiec.model.publications;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Copy {

    @ManyToOne
    Publication publication;
    @EmbeddedId
    Barcode barcode;

    boolean loaned;

    Copy(){}

    public Copy(Publication publication) {
        this.publication = publication;
        this.barcode = new Barcode();
    }

    public void loan() {
        if (loaned)
            throw new IllegalStateException(String.format("Publication %s is not available for loaning", barcode.getCode()));
        this.loaned = true;
    }

    public void giveBack() {
        if (!loaned)
            throw new IllegalStateException(String.format("Copy %s is already returned", barcode.getCode()));
        this.loaned = false;
    }

    public Publication getPublication() {
        return publication;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public boolean isLoaned() {
        return loaned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Copy copy = (Copy) o;

        return barcode.equals(copy.barcode);

    }

    @Override
    public int hashCode() {
        return barcode.hashCode();
    }
}
