// import the util package for scanner class
import java.util.*;

public class StudentRecordManagement
{
 // Node class to represent in student's record
  static class Node{
    String[] record; // Array to store student's information
    Node next;  // Pointer to the next node
    
    // Constructor to initialize the node with the given record
    Node(String[] record){
        this.record = record;
        this.next = null;
    }
  };

  // Linked list to manage student record
  static class linkedlist{
  Scanner scanner = new Scanner(System.in);
  Node head = null;  // reference to first node
  Node tail = null;  // reference to last node
  
 // method to insert node at head
 void insertAtHead(String[] record){
    Node temp = new Node(record);  // create a new node with the record
    
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
   void insertAtEnd(String[] record){
       Node temp = new Node(record);
       
       if(head == null){
           head = temp;
           tail = temp;
       }
       else{
           tail.next = temp;
           tail = temp;
       }
   };

    // method to insert the record at specific position
    void insertAtPosition(String[] record, int position){
       Node temp = new Node(record);

       if(head == null) insertAtHead(record);   // if the list is empty, insert at the head
        else if(tail == null) insertAtEnd(record); // if only one node exists, insert at the end
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
            dummy.next = temp;
      }
};
    
     // delete the student record based on roll number
     void delete(int rollNumber){
     
         
         Node dummy = head;
         Node beforeDummy1 = null; // to track the previous node

         while(!(dummy.record[0]).equals(String.valueOf(rollNumber))){
            beforeDummy1 = dummy;
            dummy = dummy.next;
            
         }

         beforeDummy1.next = dummy.next;
         dummy.next = null;  // remove the link from the target node 
         
       };

  // method to search student record by its roll number
    void search(int rollNumber){
          

        Node dummy = head;

        while(!(dummy.record[0]).equals(String.valueOf(rollNumber))){
            dummy = dummy.next;
            
        }
 
        // print the entire record
        for(int i=0;i < (dummy.record).length; i++){
           System.out.print(dummy.record[i] + " ");
        }
        System.out.println();
    };

  // Display the record of all students
    void print(){
    
    Node temp = head;
    while(temp != null){
        for(int i = 0; i < (temp.record).length;i++){
             System.out.print(temp.record[i] + " ");
        }
        System.out.println();
        temp = temp.next;
    }
       
    };

  // Method to update the grade of student based on their roll number
    void update(int rollNumber){
        
        Node dummy = head;

        while(!(dummy.record[0]).equals(String.valueOf(rollNumber))){
             dummy = dummy.next;
             
        }
        System.out.print("New Grade: ");  // getting new grade
        dummy.record[3] = scanner.next(); // changing it
};

   
}
   
	public static void main(String[] args) {
	   linkedlist li = new linkedlist(); // Creating the linked list object
           Scanner scanner = new Scanner(System.in);  // Scanner class to get new user's input

           System.out.print("Do you want to start the program(true/false): ");
           String space = scanner.next();  // start the program

           while(space.equals("true")){
                String[] record = new String[4]; // student record

                System.out.print("Enter Student's Roll number: ");
                String rollNUmber = String.valueOf(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter student's Name: ");
                String name = scanner.next();

                System.out.print("Enter student's age: ");
                String age = String.valueOf(scanner.nextInt());
                scanner.nextLine();

                System.out.print("Enter student's grade: ");
                String grade = scanner.next();
                 
                record[0] = rollNUmber;
                record[1] = name;
                record[2] = age;
                record[3] = grade;

                int turn = 1;
           
                // entering the student record in linked list
                if(turn%2 == 0) li.insertAtEnd(record);
                else if(turn%3 == 0) li.insertAtHead(record);
                else li.insertAtPosition(record, turn);
               
                turn++;

                System.out.print("Do you want to add more students(true/false): ");
                space = scanner.next();
            }
            
            // Printing the student record
            li.print();

            // Deleting the student record based on its roll number
	    System.out.println("Deleting a student by its roll numner");
            li.delete(scanner.nextInt());
	    System.out.println("Records After Deletion: ");
            
            // Printing after deletion
            li.print();

	    // Searching the student record based on its roll number
            System.out.println("Searchin a student by its roll number");
            li.search(scanner.nextInt());
            	 
               
            // Updating the stdent record based on its rollnumber
            System.out.println("Updating a student's grade by its roll number");
            li.update(scanner.nextInt());
            System.out.println("Records after updation");

            // Printing the updated record
            li.print();
	}
}

// Output:
// Do you want to start the program(true/false): true
// Enter Student's Roll number: 1
// Enter student's Name: Aaryan
// Enter student's age: 23
// Enter student's grade: A
// Do you want to add more students(true/false): true
// Enter Student's Roll number: 2
// Enter student's Name: Hitesh
// Enter student's age: 22
// Enter student's grade: A
// Do you want to add more students(true/false): true
// Enter Student's Roll number: 3
// Enter student's Name: Yash
// Enter student's age: 21
// Enter student's grade: B
// Do you want to add more students(true/false): false
// 1 Aaryan 23 A
// 3 Yash 21 B
// 2 Hitesh 22 A
// Deleting a student by its roll numner
// 2
// Records After Deletion:
// 1 Aaryan 23 A
// 3 Yash 21 B
// Searchin a student by its roll number
// 3
// 3 Yash 21 B
// Updating a student's grade by its roll number
// 3
// New Grade: A
// Records after updation
// 1 Aaryan 23 A
// 3 Yash 21 A

