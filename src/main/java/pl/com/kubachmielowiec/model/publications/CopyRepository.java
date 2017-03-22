package pl.com.kubachmielowiec.model.publications;

import java.util.List;

public interface CopyRepository {

    void put(Copy copy);

    Copy get(Barcode barcode);

    List<Copy> getAvailableCopiesOf(Publication publication);

    void remove(Barcode barcode);

}
