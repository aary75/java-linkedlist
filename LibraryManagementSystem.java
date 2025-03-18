import java.util.Scanner;

class Book {
    int bookID;
    String title;
    String author;
    String genre;
    boolean availability;
    Book next;
    Book prev;

    public Book(int bookID, String title, String author, String genre, boolean availability) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head;
    private Book tail;

    // Add a new book
    public void addBook(int bookID, String title, String author, String genre, boolean availability, int position) {
        Book newBook = new Book(bookID, title, author, genre, availability);

        if (head == null) {
            head = tail = newBook;
        } else if (position <= 0) {
            // Add at the beginning
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        } else {
            // Add at specific position or end
            Book current = head;
            int index = 0;

            while (current.next != null && index < position - 1) {
                current = current.next;
                index++;
            }

            newBook.next = current.next;
            newBook.prev = current;

            if (current.next != null) {
                current.next.prev = newBook;
            }
            current.next = newBook;

            if (newBook.next == null) {
                tail = newBook;  // Update tail if added at the end
            }
        }
        System.out.println("Book added successfully!");
    }

    // Remove book by ID
    public void removeBook(int bookID) {
        Book current = head;

        while (current != null) {
            if (current.bookID == bookID) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Book ID " + bookID + " removed.");
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID " + bookID + " not found.");
    }

    // Search for a book by title or author
    public void searchBook(String keyword) {
        Book current = head;
        boolean found = false;

        while (current != null) {
            if (current.title.equalsIgnoreCase(keyword) || current.author.equalsIgnoreCase(keyword)) {
                System.out.println("Book Found: [" + current.bookID + "] " + current.title + " by " + current.author +
                        " (Genre: " + current.genre + ", Available: " + (current.availability ? "Yes" : "No") + ")");
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No book found with the keyword: " + keyword);
        }
    }

    // Update book availability
    public void updateAvailability(int bookID, boolean availability) {
        Book current = head;

        while (current != null) {
            if (current.bookID == bookID) {
                current.availability = availability;
                System.out.println("Availability updated for Book ID " + bookID + ": " + (availability ? "Available" : "Not Available"));
                return;
            }
            current = current.next;
        }
        System.out.println("Book ID " + bookID + " not found.");
    }

    // Display books in forward order
    public void displayForward() {
        Book current = head;
        if (current == null) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBooks in Library (Forward):");

        while (current != null) {
            System.out.println("[" + current.bookID + "] " + current.title + " by " + current.author +
                    " | Genre: " + current.genre + " | Available: " + (current.availability ? "Yes" : "No"));
            current = current.next;
        }
    }

    // Display books in reverse order
    public void displayReverse() {
        Book current = tail;
        if (current == null) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nBooks in Library (Reverse):");

        while (current != null) {
            System.out.println("[" + current.bookID + "] " + current.title + " by " + current.author +
                    " | Genre: " + current.genre + " | Available: " + (current.availability ? "Yes" : "No"));
            current = current.prev;
        }
    }

    // Count total number of books
    public int countBooks() {
        int count = 0;
        Book current = head;

        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Update Availability");
            System.out.println("5. Display Books (Forward)");
            System.out.println("6. Display Books (Reverse)");
            System.out.println("7. Count Books");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();

                    System.out.print("Is Available (true/false): ");
                    boolean availability = scanner.nextBoolean();

                    System.out.print("Enter Position (0 for beginning, -1 for end): ");
                    int position = scanner.nextInt();

                    library.addBook(id, title, author, genre, availability, position);
                    break;

                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    int removeID = scanner.nextInt();
                    library.removeBook(removeID);
                    break;

                case 3:
                    System.out.print("Enter Title or Author to search: ");
                    String keyword = scanner.nextLine();
                    library.searchBook(keyword);
                    break;

                case 4:
                    System.out.print("Enter Book ID to update availability: ");
                    int updateID = scanner.nextInt();
                    System.out.print("Is Available (true/false): ");
                    boolean isAvailable = scanner.nextBoolean();
                    library.updateAvailability(updateID, isAvailable);
                    break;

                case 5:
                    library.displayForward();
                    break;

                case 6:
                    library.displayReverse();
                    break;

                case 7:
                    System.out.println("\nTotal Books: " + library.countBooks());
                    break;

                case 8:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}


// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 3
// Enter Title or Author to search: NCERT history
// No book found with the keyword: NCERT history

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 1
// Enter Book ID: 1
// Enter Title: NCERT History
// Enter Author: NCERT
// Enter Genre: 2
// Is Available (true/false): true
// Enter Position (0 for beginning, -1 for end): 0
// Book added successfully!

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 2
// Enter Book ID to remove: 8
// Book ID 8 not found.

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 3
// Enter Title or Author to search: NCERT History
// Book Found: [1] NCERT History by NCERT (Genre: 2, Available: Yes)

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 4
// Enter Book ID to update availability: 1
// Is Available (true/false): false
// Availability updated for Book ID 1: Not Available

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 7

// Total Books: 1

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 5

// Books in Library (Forward):
// [1] NCERT History by NCERT | Genre: 2 | Available: No

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 1
// Enter Book ID: 2
// Enter Title: Civil Science
// Enter Author: HD Verma
// Enter Genre: 3
// Is Available (true/false): true
// Enter Position (0 for beginning, -1 for end): 2
// Book added successfully!

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 6

// Books in Library (Reverse):
// [2] Civil Science by HD Verma | Genre: 3 | Available: Yes
// [1] NCERT History by NCERT | Genre: 2 | Available: No

// Library Management System Menu:
// 1. Add Book
// 2. Remove Book
// 3. Search Book
// 4. Update Availability
// 5. Display Books (Forward)
// 6. Display Books (Reverse)
// 7. Count Books
// 8. Exit
// Choose an option: 8
// Exiting Library Management System. Goodbye!
