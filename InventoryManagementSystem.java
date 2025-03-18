// import the util package for scanner class
import java.util.*;

public class InventoryManagementSystem
{
  static int totalValue = 0;
 // Node class to represent in item's details
  static class Node{
    String[] details; // Array to store item's information
    Node next;  // Pointer to the next node
    
    // Constructor to initialize the node with the given details
    Node(String[] details){
        this.details = details;
        this.next = null;
    }
  };

  // Linked list to manage item details
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
           tail = temp;
       }
   };

    // method to insert the details at specific position
    void insertAtPosition(String[] details, int position){
       Node temp = new Node(details);

       if(head == null) insertAtHead(details);   // if the list is empty, insert at the head
        else if(tail == null) insertAtEnd(details); // if only one node exists, insert at the end
        else{
           
           // otherwise, traverse to the desired position and insert the node
           Node dummy = head;

           int index = 1;
           while(index < position){
                dummy  = dummy.next;
                index++;
            }

            // traverse the list to the position before the insertion point
            temp.next = dummy.next;
            dummy.next = temp;
      }
};
    
     // delete the item details based on itemID
     void delete(int itemId){
     
         
         Node dummy = head;
         Node beforeDummy1 = null; // to track the previous node

         while(!(dummy.details[0]).equals(String.valueOf(itemId))){
            beforeDummy1 = dummy;
            dummy = dummy.next;
            
         }
          
         if(beforeDummy1 == null)head = dummy.next;
         else beforeDummy1.next = dummy.next;

         dummy.next = null;  // remove the link from the target node 
         
       };

  // method to search item details by its itemId
    void search(int itemId){
          

        Node dummy = head;

        while(!(dummy.details[0]).equals(String.valueOf(itemId))){
            dummy = dummy.next;
            
        }
 
        // print the entire details
        for(int i=0;i < (dummy.details).length; i++){
           System.out.print(dummy.details[i] + " ");
        }
        System.out.println();
    };

  // Display the details of all items
    void print(){
    
    Node temp = head;
    while(temp != null){
        for(int i = 0; i < (temp.details).length;i++){
             System.out.print(temp.details[i] + " ");
        }
        
        int quantity = Integer.parseInt(temp.details[2]);
        int price = Integer.parseInt(temp.details[3]);

        totalValue += (quantity * price); // sum of all items with their prices

        System.out.println();
        temp = temp.next;
    }
       
    };

    

  // Method to update the quantity of item based on their itemId
    void update(int itemId){
        
        Node dummy = head;

        while(!(dummy.details[0]).equals(String.valueOf(itemId))){
             dummy = dummy.next;
             
        }
        System.out.print("New Quanity: ");  // getting new Quantity
        dummy.details[2] = scanner.next(); // changing it
};

  // Sort the list based on its price
   void sortList(){
    
        Node current = head;

        while (current != null){
            Node index = current.next;
            Node min = current;

            // Compare the prices
            while(index != null){
               int res = (index.details[3]).compareTo(min.details[3]);
               if(res < 0) min = index;
             
               index = index.next;
            }
          
            // Swapping the nodes
            String[] temp = current.details;
            current.details = min.details;
            min.details = temp;

            current = current.next;
       }
}}; 
   
	public static void main(String[] args) {
	   linkedlist li = new linkedlist(); // Creating the linked list object
           Scanner scanner = new Scanner(System.in);  // Scanner class to get new user's input

           System.out.print("Do you want to start the program(true/false): ");
           String space = scanner.next();  // start the program

           while(space.equals("true")){
                String[] details = new String[4]; // student record

                System.out.print("Enter Item's Id: ");
                String id = String.valueOf(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter item's Name: ");
                String name = scanner.next();

                System.out.print("Enter item's Quantity: ");
                String quantity = String.valueOf(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter item's price: ");
                String price = String.valueOf(scanner.nextInt());
                scanner.nextLine();
 
                details[0] = id;
                details[1] = name;
                details[2] = quantity;
                details[3] = price;

                int turn = 1;
           
                // entering the student details in linked list
                if(turn%2 == 0) li.insertAtEnd(details);
                else if(turn%3 == 0) li.insertAtHead(details);
                else li.insertAtPosition(details, turn);
               
                turn++;

                System.out.print("Do you want to add more students(true/false): ");
                space = scanner.next();
            }
            
            // Printing the item details
            System.out.println();
            li.print();

            // Deleting the item details based on its id
	    System.out.println("\nDeleting a item by its id");
            li.delete(scanner.nextInt());

            
	    System.out.println("\nItems After Deletion: ");
            
            // Printing after deletion
            li.print();

	    // Searching the items details based on its id
            System.out.println("\nSearchin a item by its ID");
            li.search(scanner.nextInt());
            	 
               
            // Updating the stdent record based on its rollnumber
            System.out.println("\nUpdating a item's quantity by its item id");
            li.update(scanner.nextInt());
            System.out.println();
            System.out.println("\nDetails after updation");

            // Printing the updated details
            li.print();

            // Find the total price of all items
            System.out.println("\nSum of price of all items " + totalValue);

            // Sorting the list
            li.sortList();

            // Sorted list
            System.out.println("\nSorted List based on their price");
            li.print();
	}
}


// Do you want to start the program(true/false): true
// Enter Item's Id: 1
// Enter item's Name: Oil
// Enter item's Quantity: 1
// Enter item's price: 200
// Do you want to add more students(true/false): true
// Enter Item's Id: 2
// Enter item's Name: Shampoo
// Enter item's Quantity: 2
// Enter item's price: 150
// Do you want to add more students(true/false): true
// Enter Item's Id: 3
// Enter item's Name: Soap
// Enter item's Quantity: 3
// Enter item's price: 100
// Do you want to add more students(true/false): false

// 1 Oil 1 200
// 3 Soap 3 100
// 2 Shampoo 2 150

// Deleting a item by its id
// 1

// Items After Deletion:
// 3 Soap 3 100
// 2 Shampoo 2 150

// Searchin a item by its ID
// 2
// 2 Shampoo 2 150

// Updating a item's quantity by its item id
// 3
// New Quanity: 5


// Details after updation
// 3 Soap 5 100
// 2 Shampoo 2 150

// Sum of price of all items 2200

// Sorted List based on their price
// 3 Soap 5 100
// 2 Shampoo 2 150
