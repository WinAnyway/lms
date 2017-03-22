package pl.com.kubachmielowiec.model.publications;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class Barcode implements Serializable{

    String code;

    public Barcode() {
        code = UUID.randomUUID().toString();
    }

    public Barcode(String barcode) {
        this.code = barcode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barcode barcode = (Barcode) o;

        return code.equals(barcode.code);

    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
