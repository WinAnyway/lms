package pl.com.kubachmielowiec.model;

import java.time.LocalDate;

public class Publication {
    String title;
    String description;
    Author author;
    ISBN isbn;
    LocalDate published;
    Publisher publisher;
    Genre genre;
    boolean available;
}
