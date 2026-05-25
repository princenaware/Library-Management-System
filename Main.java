import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Pre-load some sample data
        library.addBook("The Alchemist",   "Paulo Coelho",    "Fiction");
        library.addBook("Clean Code",      "Robert Martin",   "Technology");
        library.addBook("Atomic Habits",   "James Clear",     "Self-Help");
        library.addBook("1984",            "George Orwell",   "Dystopian");
        library.registerMember("Aarav Shah",   "aarav@email.com");
        library.registerMember("Priya Mehta",  "priya@email.com");

        boolean running = true;

        while (running) {   // main menu loop
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║   Library Management System  ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. View all books           ║");
            System.out.println("║  2. Add a book               ║");
            System.out.println("║  3. Remove a book            ║");
            System.out.println("║  4. Search by title          ║");
            System.out.println("║  5. Search by author         ║");
            System.out.println("║  6. Issue a book             ║");
            System.out.println("║  7. Return a book            ║");
            System.out.println("║  8. View all members         ║");
            System.out.println("║  9. Register new member      ║");
            System.out.println("║  0. Exit                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {       // switch-case on menu choice
                    case 1 -> library.displayAllBooks();
                    case 2 -> {
                        System.out.print("Title: ");   String title  = scanner.nextLine();
                        System.out.print("Author: ");  String author = scanner.nextLine();
                        System.out.print("Genre: ");   String genre  = scanner.nextLine();
                        library.addBook(title, author, genre);
                    }
                    case 3 -> {
                        System.out.print("Book ID to remove: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        library.removeBook(id);
                    }
                    case 4 -> {
                        System.out.print("Search title keyword: ");
                        library.searchByTitle(scanner.nextLine());
                    }
                    case 5 -> {
                        System.out.print("Author name: ");
                        library.searchByAuthor(scanner.nextLine());
                    }
                    case 6 -> {
                        System.out.print("Book ID: ");
                        int bId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Member ID: ");
                        int mId = Integer.parseInt(scanner.nextLine());
                        library.issueBook(bId, mId);
                    }
                    case 7 -> {
                        System.out.print("Book ID: ");
                        int bId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Member ID: ");
                        int mId = Integer.parseInt(scanner.nextLine());
                        library.returnBook(bId, mId);
                    }
                    case 8 -> library.displayAllMembers();
                    case 9 -> {
                        System.out.print("Name: ");  String name  = scanner.nextLine();
                        System.out.print("Email: "); String email = scanner.nextLine();
                        library.registerMember(name, email);
                    }
                    case 0 -> {
                        System.out.println("Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());   // custom exception caught here
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        scanner.close();
    }
}