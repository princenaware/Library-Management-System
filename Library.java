import java.util.ArrayList;
import java.util.HashMap;

public class Library {

    // ArrayList for ordered display of all books
    private ArrayList<Book> bookList = new ArrayList<>();

    // HashMap for fast lookup: bookId → Book
    private HashMap<Integer, Book> bookMap = new HashMap<>();

    // HashMap for members: memberId → Member
    private HashMap<Integer, Member> memberMap = new HashMap<>();

    private int bookIdCounter   = 1;
    private int memberIdCounter = 1;

    // ─── Add Book ────────────────────────────────────────
    public void addBook(String title, String author, String genre) {
        Book book = new Book(bookIdCounter, title, author, genre);
        bookList.add(book);
        bookMap.put(bookIdCounter, book);
        bookIdCounter++;
        System.out.println("Book added: " + book);
    }

    // ─── Remove Book ──────────────────────────────────────
    public void removeBook(int bookId) throws LibraryException {
        Book book = bookMap.get(bookId);
        if (book == null)
            throw new LibraryException("Book ID " + bookId + " not found.");
        if (book.isIssued())
            throw new LibraryException("Cannot remove — book is currently issued.");
        bookList.remove(book);
        bookMap.remove(bookId);
        System.out.println("Book removed: " + book.getTitle());
    }

    // ─── Register Member ──────────────────────────────────
    public void registerMember(String name, String email) {
        Member member = new Member(memberIdCounter, name, email);
        memberMap.put(memberIdCounter, member);
        memberIdCounter++;
        System.out.println("Member registered: " + member);
    }

    // ─── Issue Book ───────────────────────────────────────
    public void issueBook(int bookId, int memberId) throws LibraryException {
        Book book = bookMap.get(bookId);
        if (book == null)
            throw new LibraryException("Book ID " + bookId + " not found.");
        if (book.isIssued())
            throw new LibraryException("Book is already issued.");

        Member member = memberMap.get(memberId);
        if (member == null)
            throw new LibraryException("Member ID " + memberId + " not found.");

        book.setIssued(true);
        member.borrowBook(bookId);
        System.out.println("Book \"" + book.getTitle() + "\" issued to " + member.getName());
    }

    // ─── Return Book ──────────────────────────────────────
    public void returnBook(int bookId, int memberId) throws LibraryException {
        Book book = bookMap.get(bookId);
        if (book == null)
            throw new LibraryException("Book ID " + bookId + " not found.");
        if (!book.isIssued())
            throw new LibraryException("This book was not issued.");

        Member member = memberMap.get(memberId);
        if (member == null)
            throw new LibraryException("Member ID " + memberId + " not found.");

        book.setIssued(false);
        member.returnBook(bookId);
        System.out.println("Book \"" + book.getTitle() + "\" returned by " + member.getName());
    }

    // ─── Search by Title (partial match) ─────────────────
    public void searchByTitle(String keyword) {
        System.out.println("Search results for \"" + keyword + "\":");
        boolean found = false;
        for (Book book : bookList) {               // enhanced for-loop
            if (book.getTitle().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                System.out.println("  " + book);
                found = true;
            }
        }
        if (!found) System.out.println("  No books found.");
    }

    // ─── Search by Author ────────────────────────────────
    public void searchByAuthor(String author) {
        System.out.println("Books by \"" + author + "\":");
        boolean found = false;
        for (Book book : bookList) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println("  " + book);
                found = true;
            }
        }
        if (!found) System.out.println("  No books found.");
    }

    // ─── Display All Books ───────────────────────────────
    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("=== All Books ===");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    // ─── Display All Members ─────────────────────────────
    public void displayAllMembers() {
        if (memberMap.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("=== All Members ===");
        for (Member member : memberMap.values()) {   // HashMap .values() loop
            System.out.println(member);
        }
    }
}