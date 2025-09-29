
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private List<String> borrowedBooks;
    private int maxBooksAllowed;

    // Constructor
    public User(String userId, String name, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = new ArrayList<>();
        this.maxBooksAllowed = 3; // Default limit
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMaxBooksAllowed(int maxBooksAllowed) {
        this.maxBooksAllowed = maxBooksAllowed;
    }

    // Method to add a book to user's borrowed list
    public boolean addBorrowedBook(String isbn) {
        if (borrowedBooks.size() < maxBooksAllowed) {
            borrowedBooks.add(isbn);
            return true;
        }
        return false;
    }

    // Method to remove a book from user's borrowed list
    public boolean removeBorrowedBook(String isbn) {
        return borrowedBooks.remove(isbn);
    }

    // Method to check if user can borrow more books
    public boolean canBorrowMore() {
        return borrowedBooks.size() < maxBooksAllowed;
    }

    // Method to display user information
    public void displayUserInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Books borrowed: " + borrowedBooks.size() + "/" + maxBooksAllowed);
        if (!borrowedBooks.isEmpty()) {
            System.out.println("Borrowed books: " + borrowedBooks);
        }
        System.out.println("-".repeat(30));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + "'" +
                ", name='" + name + "'" +
                ", email='" + email + "'" +
                ", borrowedBooks=" + borrowedBooks.size() +
                "}";
    }
}
