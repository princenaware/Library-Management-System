import java.util.ArrayList;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private ArrayList<Integer> borrowedBookIds;  // stores book IDs

    public Member(int memberId, String name, String email) {
        this.memberId        = memberId;
        this.name            = name;
        this.email           = email;
        this.borrowedBookIds = new ArrayList<>();
    }

    public int       getMemberId()    { return memberId; }
    public String    getName()        { return name; }
    public String    getEmail()       { return email; }
    public ArrayList<Integer> getBorrowedBookIds() { return borrowedBookIds; }

    public void borrowBook(int bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(int bookId) {
        borrowedBookIds.remove(Integer.valueOf(bookId));
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s (%s) | Borrowed: %s",
            memberId, name, email, borrowedBookIds);
    }
}