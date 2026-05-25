public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private boolean isIssued;

    // Constructor
    public Book(int bookId, String title, String author, String genre) {
        this.bookId   = bookId;
        this.title    = title;
        this.author   = author;
        this.genre    = genre;
        this.isIssued = false;  // default: available
    }

    // Getters and setters
    public int    getBookId()            { return bookId; }
    public String getTitle()             { return title; }
    public String getAuthor()            { return author; }
    public String getGenre()             { return genre; }
    public boolean isIssued()            { return isIssued; }
    public void   setIssued(boolean b)   { this.isIssued = b; }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s by %s (%s) | Status: %s",
            bookId, title, author, genre, isIssued ? "Issued" : "Available");
    }
}