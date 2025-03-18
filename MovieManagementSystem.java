// import the util package for scanner class
import java.util.*;

public class MovieManagementSystem
{
 // Node class to represent in movie's details
     static class Node{
    String[] details; // Array to store student's information
    Node next;  // Pointer to the next node
    Node prev; // Pointer to the prev node

    // Constructor to initialize the node with the given details
    Node(String[] details){
        this.details = details;
        this.next = null;
        this.prev = null;
    }
  };

  // Linked list to manage movie details
  static class linkedlist{
  Scanner scanner = new Scanner(System.in);
  Node head = null;  // reference to first node
  Node tail = null;  // reference to last node
  
 // method to insert node at head
 void insertAtHead(String[] details){
    Node temp = new Node(details);  // create a new node with the details
    
    if(head== null){
        head = temp;   // if the list is empty, set head and tail to new node
        tail = temp;
    }
    else{
        temp.next = head; // otherwise, insert the new node before the head node
        head.prev = temp;
        head = temp;
    }
};

   // method to insert node at end
   void insertAtEnd(String[] details){
       Node temp = new Node(details);
       
       if(head == null){
           head = temp;
           tail = temp;
       }
       else{
           tail.next = temp;
           temp.prev = tail;
           tail = temp;
       }
   };

    // method to insert the details at specific position
    void insertAtPosition(String[] details, int position){
       Node temp = new Node(details);

       if(head == null) insertAtHead(details);   // if the list is empty, insert at the head
        else if(tail == null) insertAtEnd(details); // if only one node exists, insert at the end
        else{
           
           // otherwise, traverse to the desired postiona nd insert the node
           Node dummy = head;

           int index = 1;
           while(index < position){
                dummy  = dummy.next;
                index++;
            }

            // traverse the list to the postition before the insertion point
            temp.next = dummy.next;
            dummy.next.prev = temp;

            temp.prev = dummy;
            dummy.next = temp;
      }
};
    
     // delete the movie based on director
     void delete(String movieTitle){
     
         
         Node dummy = head;
         Node beforeDummy1 = null; // to track the previous node

         while(!(dummy.details[0]).equals(movieTitle)){
            beforeDummy1 = dummy;
            dummy = dummy.next;
            
         }

         beforeDummy1.next = dummy.next;
         dummy.next = null;  // remove the link from the target node 
         
       };

  // method to search movie record by its director
    void search(String director){
          

        Node dummy = head;

        while(!(dummy.details[1]).equals(director)){
            dummy = dummy.next;
            
        }
 
        // print the entire details of movie
        for(int i=0;i < (dummy.details).length; i++){
           System.out.print(dummy.details[i] + " ");
        }
        System.out.println();
    };

  // Display the every detail of all movies
    void print(){
    
    Node temp = head;
    while(temp != null){
        for(int i = 0; i < (temp.details).length;i++){
             System.out.print(temp.details[i] + " ");
        }
        System.out.println();
        temp = temp.next;
    }
       
    };

  // Method to update the rating of movie based on their movie title
    void update(String movieTitle){
        
        Node dummy = head;

        while(!(dummy.details[0]).equals(movieTitle)){
             dummy = dummy.next;
             
        }
        System.out.print("New Rating: ");  // getting new rating
        dummy.details[3] = String.valueOf(scanner.nextDouble()); // changing it
        scanner.nextLine();
};

   
}
   
	public static void main(String[] args) {
	   linkedlist li = new linkedlist(); // Creating the linked list object
           Scanner scanner = new Scanner(System.in);  // Scanner class to get new user's input

           System.out.print("Do you want to start the program(true/false): ");
           String space = scanner.nextLine();  // start the program
           int turn = 1;

           while(space.equals("true")){
                String[] details = new String[4]; // movie details

                System.out.print("Enter Movie title: ");
                String movieTitle = scanner.nextLine();

                System.out.print("Enter Director's name: ");
                String director = scanner.nextLine();

                System.out.print("Enter release year: ");
                String releaseYear = String.valueOf(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter movie's rating: ");
                String rating = String.valueOf(scanner.nextDouble());
                scanner.nextLine();
                 
                details[0] = movieTitle;
                details[1] = director;
                details[2] = releaseYear;
                details[3] = rating;

           
                // entering the movie deatais in linked list
                if(turn%2 == 0) li.insertAtEnd(details);
                else if(turn%3 == 0) li.insertAtHead(details);
                else li.insertAtPosition(details, turn);
               
                turn++;

                System.out.print("Do you want to add more students(true/false): ");
                space = scanner.nextLine();
            }
            
            // Printing the movie details
            li.print();

            // Deleting the movie record on its title
	    System.out.println("\nDeleting a movie by its title");
            li.delete(scanner.nextLine());
	    System.out.println("\nMovies After Deletion: ");
            
            // Printing after deletion
            li.print();

	    // Searching the movie record based on its director's name
            System.out.println("\nSearchin a movie by its director's name ");
            li.search(scanner.nextLine());
             System.out.println();
               
            // Updating the movie rating based on its title
            System.out.println("\nUpdating a movie's rating by its title");
            li.update(scanner.nextLine());
            System.out.println("\nMoviess after updation");

            // Printing the updated record
            li.print();
	}
}


// Do you want to start the program(true/false): true
// Enter Movie title: Singham
// Enter Director's name: Rohit Shetty
// Enter release year: 2006
// Enter movie's rating: 9.8
// Do you want to add more students(true/false): true
// Enter Movie title: Bhool Bhuliya
// Enter Director's name: Aneez Bazmee
// Enter release year: 2008
// Enter movie's rating: 9.7
// Do you want to add more students(true/false): true
// Enter Movie title: Happy New Year
// Enter Director's name: Farah Khan
// Enter release year: 2012
// Enter movie's rating: 8.5
// Do you want to add more students(true/false): false

// Happy New Year Farah Khan 2012 8.5
// Singham Rohit Shetty 2006 9.8
// Bhool Bhuliya Aneez Bazmee 2008 9.7

// Deleting a movie by its title
// Singham

// Movies After Deletion:
// Happy New Year Farah Khan 2012 8.5
// Bhool Bhuliya Aneez Bazmee 2008 9.7

// Searchin a movie by its director's name
// Farah Khan

// Happy New Year Farah Khan 2012 8.5

// Updating a movie's rating by its title
// Happy New Year
// New Rating: 9.0

// Moviess after updation
// Happy New Year Farah Khan 2012 9.0
// Bhool Bhuliya Aneez Bazmee 2008 9.7

