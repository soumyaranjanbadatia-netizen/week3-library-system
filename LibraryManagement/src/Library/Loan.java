
package Library;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Book book;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Book book, Member member) {
        if (book == null || member == null) {
            throw new IllegalArgumentException(
                "Book and member are required."
            );
        }

        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14);
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public void markReturned() {
        if (!isActive()) {
            throw new IllegalStateException(
                "This loan has already been returned."
            );
        }

        returnDate = LocalDate.now();
    }

    public boolean isOverdue() {
        return isActive() && LocalDate.now().isAfter(dueDate);
    }

    public void displayDetails() {
        System.out.println(
            "Book: " + book.getTitle()
            + " (ISBN: " + book.getIsbn() + ")"
        );

        System.out.println(
            "Member: " + member.getName()
            + " (ID: " + member.getMemberId() + ")"
        );

        System.out.println("Borrowed: " + borrowDate);
        System.out.println("Due date: " + dueDate);

        System.out.println(
            "Returned: "
            + (returnDate == null ? "Not returned" : returnDate)
        );

        System.out.println(
            "Overdue: " + (isOverdue() ? "Yes" : "No")
        );
    }
}
