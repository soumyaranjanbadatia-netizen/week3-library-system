
package Library;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = FileHandler.loadLibrary();
        int choice;

        do {
            showMenu();
            choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addBook(library);
                    case 2 -> library.viewBooks();
                    case 3 -> searchBooks(library);
                    case 4 -> filterBooks(library);
                    case 5 -> removeBook(library);
                    case 6 -> registerMember(library);
                    case 7 -> library.viewMembers();
                    case 8 -> searchMembers(library);
                    case 9 -> borrowBook(library);
                    case 10 -> returnBook(library);
                    case 11 -> library.viewActiveLoans();
                    case 12 -> library.viewOverdueLoans();
                    case 13 -> library.showStatistics();
                    case 0 -> System.out.println(
                        "Exiting Library Management System..."
                    );
                    default -> System.out.println(
                        "Invalid choice. Enter a number from 0 to 13."
                    );
                }

            } catch (IllegalArgumentException |
                     IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println(
                    "Unexpected error: " + e.getMessage()
                );
            }

            // Save after every menu operation.
            try {
                FileHandler.saveLibrary(library);
            } catch (IOException e) {
                System.out.println(
                    "Warning: Could not save data: " + e.getMessage()
                );
            }

            System.out.println();

        } while (choice != 0);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("========== LIBRARY MANAGEMENT SYSTEM ==========");
        System.out.println("1.  Add book");
        System.out.println("2.  View all books");
        System.out.println("3.  Search books");
        System.out.println("4.  Filter books by availability");
        System.out.println("5.  Remove book");
        System.out.println("6.  Register member");
        System.out.println("7.  View members");
        System.out.println("8.  Search members");
        System.out.println("9.  Borrow book");
        System.out.println("10. Return book");
        System.out.println("11. View active loans");
        System.out.println("12. View overdue loans");
        System.out.println("13. Library statistics");
        System.out.println("0.  Exit");
        System.out.println("===============================================");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Please enter a whole number."
                );
            }
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private static void addBook(Library library) {
        String isbn = readText("ISBN: ");
        String title = readText("Title: ");
        String author = readText("Author: ");
        int year = readInt("Publication year: ");

        library.addBook(new Book(isbn, title, author, year));

        System.out.println("Book added successfully.");
    }

    private static void searchBooks(Library library) {
        String keyword = readText(
            "Search by ISBN, title, or author: "
        );

        List<Book> results = library.searchBooks(keyword);

        if (results.isEmpty()) {
            System.out.println("No matching books found.");
            return;
        }

        for (Book book : results) {
            book.displayDetails();
            System.out.println("------------------------------");
        }
    }

    private static void filterBooks(Library library) {
        System.out.println("1. Available books");
        System.out.println("2. Borrowed books");

        int filter = readInt("Choose filter: ");

        if (filter != 1 && filter != 2) {
            System.out.println("Invalid filter choice.");
            return;
        }

        List<Book> results =
            library.filterBooksByAvailability(filter == 1);

        if (results.isEmpty()) {
            System.out.println("No books match this filter.");
            return;
        }

        for (Book book : results) {
            book.displayDetails();
            System.out.println("------------------------------");
        }
    }

    private static void removeBook(Library library) {
        String isbn = readText("ISBN to remove: ");

        library.removeBook(isbn);

        System.out.println("Book removed successfully.");
    }

    private static void registerMember(Library library) {
        int id = readInt("Member ID: ");
        String name = readText("Member name: ");

        library.registerMember(new Member(id, name));

        System.out.println("Member registered successfully.");
    }

    private static void searchMembers(Library library) {
        String keyword = readText(
            "Search by member name or ID: "
        );

        library.searchMembers(keyword);
    }

    private static void borrowBook(Library library) {
        int memberId = readInt("Member ID: ");
        String isbn = readText("Book ISBN: ");

        Loan loan = library.borrowBook(memberId, isbn);

        System.out.println("Book borrowed successfully.");
        System.out.println("Due date: " + loan.getDueDate());
    }

    private static void returnBook(Library library) {
        int memberId = readInt("Member ID: ");
        String isbn = readText("Book ISBN: ");

        Loan loan = library.returnBook(memberId, isbn);

        System.out.println("Book returned successfully.");
        System.out.println(
            "Return date: " + loan.getReturnDate()
        );
    }
}
