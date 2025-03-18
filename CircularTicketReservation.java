import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketId, String customerName, String movieName, String seatNumber) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.next = null;
    }
}

class TicketReservation {
    private Ticket head;
    private Ticket tail;
    private int totalTickets;

    public TicketReservation() {
        this.head = null;
        this.tail = null;
        this.totalTickets = 0;
    }

    // Add a new ticket at the end
    public void addTicket(int id, String customer, String movie, String seat) {
        Ticket newTicket = new Ticket(id, customer, movie, seat);

        if (head == null) {
            head = tail = newTicket;
            tail.next = head;  // Circular link
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;  // Circular link
        }

        totalTickets++;
        System.out.println("Ticket booked successfully: " +
                "\nTicket ID: " + id +
                "\nCustomer: " + customer +
                "\nMovie: " + movie +
                "\nSeat: " + seat +
                "\nBooking Time: " + newTicket.bookingTime);
    }

    // Remove a ticket by Ticket ID
    public void removeTicket(int id) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        Ticket previous = tail;

        do {
            if (current.ticketId == id) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                }
                totalTickets--;
                System.out.println("Ticket ID " + id + " has been removed.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket ID " + id + " not found.");
    }

    // Display all tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        System.out.println("\nCurrent Booked Tickets:");
        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketId +
                    "\nCustomer: " + current.customerName +
                    "\nMovie: " + current.movieName +
                    "\nSeat: " + current.seatNumber +
                    "\nBooking Time: " + current.bookingTime + "\n");
            current = current.next;
        } while (current != head);

        System.out.println("Total Tickets: " + totalTickets);
    }

    // Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String searchKey) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        boolean found = false;
        Ticket current = head;

        System.out.println("\nSearch Results:");
        do {
            if (current.customerName.equalsIgnoreCase(searchKey) ||
                current.movieName.equalsIgnoreCase(searchKey)) {

                System.out.println("Ticket ID: " + current.ticketId +
                        "\nCustomer: " + current.customerName +
                        "\nMovie: " + current.movieName +
                        "\nSeat: " + current.seatNumber +
                        "\nBooking Time: " + current.bookingTime + "\n");
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No tickets found for: " + searchKey);
        }
    }

    // Display total number of booked tickets
    public void totalBookedTickets() {
        System.out.println("Total Booked Tickets: " + totalTickets);
    }
}

public class CircularTicketReservation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketReservation reservation = new TicketReservation();

        while (true) {
            System.out.println("\nOnline Ticket Reservation System");
            System.out.println("1. Add Ticket");
            System.out.println("2. Remove Ticket");
            System.out.println("3. Display All Tickets");
            System.out.println("4. Search Ticket (by Customer/Movie)");
            System.out.println("5. Total Booked Tickets");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customer = scanner.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movie = scanner.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seat = scanner.nextLine();
                    reservation.addTicket(id, customer, movie, seat);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    int removeId = scanner.nextInt();
                    reservation.removeTicket(removeId);
                    break;

                case 3:
                    reservation.displayTickets();
                    break;

                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String searchKey = scanner.nextLine();
                    reservation.searchTicket(searchKey);
                    break;

                case 5:
                    reservation.totalBookedTickets();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
