import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head;
    private Task tail;
    private Task current;  // Pointer to current task for iteration

    // Add a task at the beginning
    public void addTaskAtBeginning(int id, String name, int priority, String date) {
        Task newTask = new Task(id, name, priority, date);

        if (head == null) {
            head = tail = newTask;
            tail.next = head;  // Circular link
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;  // Circular link
        }

        System.out.println("Task added at the beginning: " + name);
    }

    // Add a task at the end
    public void addTaskAtEnd(int id, String name, int priority, String date) {
        Task newTask = new Task(id, name, priority, date);

        if (head == null) {
            head = tail = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;  // Circular link
        }

        System.out.println("Task added at the end: " + name);
    }

    // Add a task at a specific position
    public void addTaskAtPosition(int id, String name, int priority, String date, int position) {
        Task newTask = new Task(id, name, priority, date);

        if (position <= 1 || head == null) {
            addTaskAtBeginning(id, name, priority, date);
            return;
        }

        Task temp = head;
        int count = 1;

        while (count < position - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;  // Update tail if inserted at the end
            tail.next = head;  // Maintain circular link
        }

        System.out.println("Task added at position " + position + ": " + name);
    }

    // Remove a task by Task ID
    public void removeTaskById(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task current = head;
        Task prev = tail;

        do {
            if (current.taskId == id) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;  // Update tail if last node is removed
                        tail.next = head;  // Circular link
                    }
                }
                System.out.println("Task with ID " + id + " removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Task ID not found.");
    }

    // View the current task and move to the next task
    public void viewCurrentTask() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        if (current == null) {
            current = head;  // Start from the head
        }

        System.out.println("\nCurrent Task:");
        System.out.println("ID: " + current.taskId);
        System.out.println("Name: " + current.taskName);
        System.out.println("Priority: " + current.priority);
        System.out.println("Due Date: " + current.dueDate);

        current = current.next;  // Move to the next task (circular loop)
    }

    // Display all tasks in the circular list
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }

        Task temp = head;
        System.out.println("\nAll Tasks:");

        do {
            System.out.println("ID: " + temp.taskId + ", Name: " + temp.taskName +
                    ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    // Search for a task by Priority
    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }

        Task temp = head;
        boolean found = false;

        System.out.println("\nTasks with Priority " + priority + ":");

        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.taskId + ", Name: " + temp.taskName +
                        ", Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }
}

public class CircularTaskScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();

        while (true) {
            System.out.println("\nTask Scheduler Menu:");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Specific Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task (Next)");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search by Priority");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Due Date: ");
                    String date1 = scanner.nextLine();
                    scheduler.addTaskAtBeginning(id1, name1, priority1, date1);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name2 = scanner.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Due Date: ");
                    String date2 = scanner.nextLine();
                    scheduler.addTaskAtEnd(id2, name2, priority2, date2);
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int id3 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name3 = scanner.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority3 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Due Date: ");
                    String date3 = scanner.nextLine();
                    System.out.print("Enter Position: ");
                    int position = scanner.nextInt();
                    scheduler.addTaskAtPosition(id3, name3, priority3, date3, position);
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    int removeId = scanner.nextInt();
                    scheduler.removeTaskById(removeId);
                    break;

                case 5:
                    scheduler.viewCurrentTask();
                    break;

                case 6:
                    scheduler.displayAllTasks();
                    break;

                case 7:
                    System.out.print("Enter Priority to search: ");
                    int searchPriority = scanner.nextInt();
                    scheduler.searchTaskByPriority(searchPriority);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
