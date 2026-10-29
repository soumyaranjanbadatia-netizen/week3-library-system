
package Library;

import java.io.Serializable;
import java.time.Year;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private boolean available;

    public Book(String isbn, String title, String author, int year) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN is required.");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author is required.");
        }

        if (year <= 0 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid publication year.");
        }

        this.isbn = isbn.trim();
        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    // Only classes in this package can change availability.
    void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayDetails() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println(
            "Status: " + (available ? "Available" : "Borrowed")
        );
    }
}
