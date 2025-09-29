
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Library {
    private Map<String, Book> books;
    private Map<String, User> users;
    private String libraryName;
    private String address;

    // Constructor
    public Library(String libraryName, String address) {
        this.libraryName = libraryName;
        this.address = address;
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    // Method to add a book to the library
    public boolean addBook(Book book) {
        if (!books.containsKey(book.getIsbn())) {
            books.put(book.getIsbn(), book);
            System.out.println("Book '" + book.getTitle() + "' added to library successfully!");
            return true;
        } else {
            System.out.println("Book with ISBN " + book.getIsbn() + " already exists!");
            return false;
        }
    }

    // Method to remove a book from the library
    public boolean removeBook(String isbn) {
        Book book = books.get(isbn);
        if (book != null) {
            if (book.isAvailable()) {
                books.remove(isbn);
                System.out.println("Book '" + book.getTitle() + "' removed from library successfully!");
                return true;
            } else {
                System.out.println("Cannot remove book - it is currently borrowed!");
                return false;
            }
        } else {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }
    }

    // Method to register a new user
    public boolean registerUser(User user) {
        if (!users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
            System.out.println("User '" + user.getName() + "' registered successfully!");
            return true;
        } else {
            System.out.println("User with ID " + user.getUserId() + " already exists!");
            return false;
        }
    }

    // Method to issue a book to a user
    public boolean issueBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }

        if (user == null) {
            System.out.println("User with ID " + userId + " not found!");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Book '" + book.getTitle() + "' is not available!");
            return false;
        }

        if (!user.canBorrowMore()) {
            System.out.println("User '" + user.getName() + "' has reached maximum book limit!");
            return false;
        }

        // Issue the book
        book.setAvailable(false);
        book.setBorrowedBy(user.getName());
        book.setBorrowDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        user.addBorrowedBook(isbn);

        System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName() + " successfully!");
        return true;
    }

    // Method to return a book
    public boolean returnBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found!");
            return false;
        }

        if (user == null) {
            System.out.println("User with ID " + userId + " not found!");
            return false;
        }

        if (book.isAvailable()) {
            System.out.println("Book '" + book.getTitle() + "' is not currently borrowed!");
            return false;
        }

        if (!book.getBorrowedBy().equals(user.getName())) {
            System.out.println("This book was not borrowed by " + user.getName() + "!");
            return false;
        }

        // Return the book
        book.setAvailable(true);
        book.setBorrowedBy(null);
        book.setBorrowDate(null);
        user.removeBorrowedBook(isbn);

        System.out.println("Book '" + book.getTitle() + "' returned by " + user.getName() + " successfully!");
        return true;
    }

    // Method to search books by title
    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to search books by author
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to display all books
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library!");
            return;
        }

        System.out.println("\n=== All Books in " + libraryName + " ===");
        for (Book book : books.values()) {
            book.displayBookInfo();
        }
    }

    // Method to display available books
    public void displayAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        if (availableBooks.isEmpty()) {
            System.out.println("No books available for borrowing!");
            return;
        }

        System.out.println("\n=== Available Books ===");
        for (Book book : availableBooks) {
            book.displayBookInfo();
        }
    }

    // Method to display all users
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered!");
            return;
        }

        System.out.println("\n=== All Registered Users ===");
        for (User user : users.values()) {
            user.displayUserInfo();
        }
    }

    // Method to display library statistics
    public void displayLibraryStats() {
        int totalBooks = books.size();
        int availableBooks = 0;
        int borrowedBooks = 0;

        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks++;
            } else {
                borrowedBooks++;
            }
        }

        System.out.println("\n=== Library Statistics ===");
        System.out.println("Library Name: " + libraryName);
        System.out.println("Address: " + address);
        System.out.println("Total Books: " + totalBooks);
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Borrowed Books: " + borrowedBooks);
        System.out.println("Total Users: " + users.size());
        System.out.println("-".repeat(30));
    }

    // Getters
    public String getLibraryName() {
        return libraryName;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getTotalUsers() {
        return users.size();
    }
}
