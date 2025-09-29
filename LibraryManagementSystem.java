
import java.util.Scanner;
import java.util.List;

public class LibraryManagementSystem {
    private static Library library;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System!");
        System.out.print("Enter library name: ");
        String libraryName = scanner.nextLine();
        System.out.print("Enter library address: ");
        String address = scanner.nextLine();

        library = new Library(libraryName, address);

        // Add sample data
        initializeSampleData();

        // Main menu loop
        while (true) {
            displayMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addBookMenu();
                    break;
                case 2:
                    removeBookMenu();
                    break;
                case 3:
                    registerUserMenu();
                    break;
                case 4:
                    issueBookMenu();
                    break;
                case 5:
                    returnBookMenu();
                    break;
                case 6:
                    searchBooksMenu();
                    break;
                case 7:
                    library.displayAllBooks();
                    break;
                case 8:
                    library.displayAvailableBooks();
                    break;
                case 9:
                    library.displayAllUsers();
                    break;
                case 10:
                    library.displayLibraryStats();
                    break;
                case 11:
                    System.out.println("Thank you for using the Library Management System!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("          LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1.  Add Book");
        System.out.println("2.  Remove Book");
        System.out.println("3.  Register User");
        System.out.println("4.  Issue Book");
        System.out.println("5.  Return Book");
        System.out.println("6.  Search Books");
        System.out.println("7.  Display All Books");
        System.out.println("8.  Display Available Books");
        System.out.println("9.  Display All Users");
        System.out.println("10. Display Library Statistics");
        System.out.println("11. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-11): ");
    }

    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addBookMenu() {
        System.out.println("\n=== Add New Book ===");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine().trim();

        Book book = new Book(isbn, title, author, genre);
        library.addBook(book);
    }

    private static void removeBookMenu() {
        System.out.println("\n=== Remove Book ===");
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine().trim();
        library.removeBook(isbn);
    }

    private static void registerUserMenu() {
        System.out.println("\n=== Register New User ===");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();

        User user = new User(userId, name, email, phone);
        library.registerUser(user);
    }

    private static void issueBookMenu() {
        System.out.println("\n=== Issue Book ===");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        library.issueBook(isbn, userId);
    }

    private static void returnBookMenu() {
        System.out.println("\n=== Return Book ===");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();
        library.returnBook(isbn, userId);
    }

    private static void searchBooksMenu() {
        System.out.println("\n=== Search Books ===");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.print("Enter choice (1-2): ");

        int choice = getChoice();
        List<Book> results = null;

        switch (choice) {
            case 1:
                System.out.print("Enter title to search: ");
                String title = scanner.nextLine().trim();
                results = library.searchBooksByTitle(title);
                break;
            case 2:
                System.out.print("Enter author to search: ");
                String author = scanner.nextLine().trim();
                results = library.searchBooksByAuthor(author);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        if (results != null && !results.isEmpty()) {
            System.out.println("\n=== Search Results ===");
            for (Book book : results) {
                book.displayBookInfo();
            }
        } else {
            System.out.println("No books found matching your search criteria.");
        }
    }

    private static void initializeSampleData() {
        // Add sample books
        library.addBook(new Book("978-0-13-468599-1", "Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("978-0-201-61622-4", "The Pragmatic Programmer", "David Thomas", "Programming"));
        library.addBook(new Book("978-0-596-00732-0", "Head First Java", "Kathy Sierra", "Programming"));
        library.addBook(new Book("978-0-134-09441-6", "Effective Java", "Joshua Bloch", "Programming"));
        library.addBook(new Book("978-0-13-235088-4", "Introduction to Algorithms", "Thomas Cormen", "Computer Science"));

        // Add sample users
        library.registerUser(new User("USR001", "John Doe", "john.doe@email.com", "123-456-7890"));
        library.registerUser(new User("USR002", "Jane Smith", "jane.smith@email.com", "098-765-4321"));
        library.registerUser(new User("USR003", "Alice Johnson", "alice.johnson@email.com", "555-123-4567"));

        System.out.println("Sample data initialized successfully!");
    }
}
