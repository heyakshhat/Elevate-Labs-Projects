
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    private String borrowedBy;
    private String borrowDate;

    // Constructor
    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;
        this.borrowedBy = null;
        this.borrowDate = null;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    // Setters
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    // Method to display book information
    public void displayBookInfo() {
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        if (!isAvailable) {
            System.out.println("Borrowed by: " + borrowedBy);
            System.out.println("Borrow date: " + borrowDate);
        }
        System.out.println("-".repeat(30));
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + "'" +
                ", title='" + title + "'" +
                ", author='" + author + "'" +
                ", genre='" + genre + "'" +
                ", isAvailable=" + isAvailable +
                "}";
    }
}
