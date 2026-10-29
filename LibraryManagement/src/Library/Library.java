
package Library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    // ---------------- BOOK MANAGEMENT ----------------

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException(
                "Book cannot be null."
            );
        }

        if (findByIsbn(book.getIsbn()) != null) {
            throw new IllegalArgumentException(
                "A book with this ISBN already exists."
            );
        }

        books.add(book);
    }

    public Book findByIsbn(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            return null;
        }

        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn.trim())) {
                return book;
            }
        }

        return null;
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books registered.");
            return;
        }

        for (Book book : books) {
            book.displayDetails();
            System.out.println("------------------------------");
        }
    }

    public List<Book> searchBooks(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                "Search keyword is required."
            );
        }

        String search = keyword.trim().toLowerCase(Locale.ROOT);
        List<Book> results = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase(Locale.ROOT).contains(search)
                    || book.getAuthor().toLowerCase(Locale.ROOT).contains(search)
                    || book.getIsbn().toLowerCase(Locale.ROOT).contains(search)) {
                results.add(book);
            }
        }

        return results;
    }

    public List<Book> filterBooksByAvailability(boolean available) {
        List<Book> results = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable() == available) {
                results.add(book);
            }
        }

        return results;
    }

    public void removeBook(String isbn) {
        Book book = findByIsbn(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException(
                "Cannot remove a borrowed book."
            );
        }

        books.remove(book);
    }

    // ---------------- MEMBER MANAGEMENT ----------------

    public void registerMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException(
                "Member cannot be null."
            );
        }

        if (findMember(member.getMemberId()) != null) {
            throw new IllegalArgumentException(
                "Member ID already exists."
            );
        }

        members.add(member);
    }

    public Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }

    public void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        for (Member member : members) {
            member.displayDetails();
            System.out.println("------------------------------");
        }
    }

    public void searchMembers(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                "Search keyword is required."
            );
        }

        String search = keyword.trim().toLowerCase(Locale.ROOT);
        boolean found = false;

        for (Member member : members) {
            if (member.getName().toLowerCase(Locale.ROOT).contains(search)
                    || String.valueOf(member.getMemberId()).contains(search)) {
                member.displayDetails();
                System.out.println("------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching members found.");
        }
    }

    // ---------------- BORROWING ----------------

    public Loan borrowBook(int memberId, String isbn) {
        Member member = findMember(memberId);
        Book book = findByIsbn(isbn);

        if (member == null) {
            throw new IllegalArgumentException("Member not found.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException(
                "Book is already borrowed."
            );
        }

        Loan loan = new Loan(book, member);
        book.setAvailable(false);
        loans.add(loan);

        return loan;
    }

    // ---------------- RETURNING ----------------

    public Loan returnBook(int memberId, String isbn) {
        Member member = findMember(memberId);
        Book book = findByIsbn(isbn);

        if (member == null) {
            throw new IllegalArgumentException("Member not found.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        for (Loan loan : loans) {
            if (loan.isActive()
                    && loan.getBook() == book
                    && loan.getMember() == member) {

                loan.markReturned();
                book.setAvailable(true);

                return loan;
            }
        }

        throw new IllegalStateException(
            "This member does not have this book on loan."
        );
    }

    // ---------------- LOAN REPORTS ----------------

    public void viewActiveLoans() {
        boolean found = false;

        for (Loan loan : loans) {
            if (loan.isActive()) {
                loan.displayDetails();
                System.out.println("------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No active loans.");
        }
    }

    public void viewOverdueLoans() {
        boolean found = false;

        for (Loan loan : loans) {
            if (loan.isOverdue()) {
                loan.displayDetails();
                System.out.println("------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue loans.");
        }
    }

    // ---------------- STATISTICS ----------------

    public void showStatistics() {
        int available = 0;
        int borrowed = 0;
        int activeLoans = 0;
        int overdue = 0;

        for (Book book : books) {
            if (book.isAvailable()) {
                available++;
            } else {
                borrowed++;
            }
        }

        for (Loan loan : loans) {
            if (loan.isActive()) {
                activeLoans++;

                if (loan.isOverdue()) {
                    overdue++;
                }
            }
        }

        System.out.println("\n===== LIBRARY STATISTICS =====");
        System.out.println("Total books: " + books.size());
        System.out.println("Available books: " + available);
        System.out.println("Borrowed books: " + borrowed);
        System.out.println("Registered members: " + members.size());
        System.out.println("Active loans: " + activeLoans);
        System.out.println("Overdue loans: " + overdue);
        System.out.println("==============================");
    }
}
