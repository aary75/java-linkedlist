import java.util.Scanner;

class Process {
    int processId;
    int burstTime;
    int priority;
    Process next;

    public Process(int id, int burstTime, int priority) {
        this.processId = id;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head;
    private Process tail;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.head = null;
        this.tail = null;
        this.timeQuantum = timeQuantum;
    }

    // Add a new process at the end
    public void addProcess(int id, int burstTime, int priority) {
        Process newProcess = new Process(id, burstTime, priority);

        if (head == null) {
            head = tail = newProcess;
            tail.next = head;  // Circular link
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head;  // Circular link
        }
        System.out.println("Process added: ID = " + id + ", Burst Time = " + burstTime + ", Priority = " + priority);
    }

    // Remove a process by ID
    public void removeProcess(int id) {
        if (head == null) {
            System.out.println("No processes to remove.");
            return;
        }

        Process current = head;
        Process prev = tail;

        do {
            if (current.processId == id) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                        tail.next = head;
                    }
                }
                System.out.println("Process " + id + " removed after execution.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    // Simulate Round Robin Scheduling
    public void simulateScheduling() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process current = head;
        int totalProcesses = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int currentTime = 0;

        // Counting total processes
        Process temp = head;
        do {
            totalProcesses++;
            temp = temp.next;
        } while (temp != head);

        // Arrays to hold waiting times and turnaround times
        int[] waitingTime = new int[totalProcesses];
        int[] turnaroundTime = new int[totalProcesses];
        int[] burstTime = new int[totalProcesses];
        int[] processIds = new int[totalProcesses];

        int index = 0;
        temp = head;
        do {
            processIds[index] = temp.processId;
            burstTime[index] = temp.burstTime;
            index++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("\nSimulating Round Robin Scheduling...");
        boolean allCompleted = false;

        while (!allCompleted) {
            allCompleted = true;

            for (int i = 0; i < totalProcesses; i++) {
                if (burstTime[i] > 0) {
                    allCompleted = false;

                    // Execute the process for the time quantum or remaining burst time
                    int execTime = Math.min(burstTime[i], timeQuantum);
                    burstTime[i] -= execTime;
                    currentTime += execTime;

                    // If the process is completed, calculate turnaround and waiting time
                    if (burstTime[i] == 0) {
                        turnaroundTime[i] = currentTime;
                        waitingTime[i] = turnaroundTime[i] - temp.burstTime;
                    }

                    // Display round status
                    System.out.println("Executing Process ID: " + processIds[i] +
                            ", Remaining Burst Time: " + burstTime[i]);
                }
            }
        }

        // Calculate average waiting and turnaround times
        for (int i = 0; i < totalProcesses; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        double avgWaitingTime = (double) totalWaitingTime / totalProcesses;
        double avgTurnaroundTime = (double) totalTurnaroundTime / totalProcesses;

        // Display final results
        System.out.println("\nProcess Execution Completed.");
        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    // Display all processes in the circular queue
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process temp = head;
        System.out.println("\nProcesses in the queue:");

        do {
            System.out.println("ID: " + temp.processId + ", Burst Time: " + temp.burstTime +
                    ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

public class CircularRoundRobin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter time quantum: ");
        int timeQuantum = scanner.nextInt();
        RoundRobinScheduler scheduler = new RoundRobinScheduler(timeQuantum);

        while (true) {
            System.out.println("\nRound Robin Scheduler Menu:");
            System.out.println("1. Add Process");
            System.out.println("2. Remove Process");
            System.out.println("3. Simulate Scheduling");
            System.out.println("4. Display Processes");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter Burst Time: ");
                    int burstTime = scanner.nextInt();
                    System.out.print("Enter Priority: ");
                    int priority = scanner.nextInt();
                    scheduler.addProcess(id, burstTime, priority);
                    break;

                case 2:
                    System.out.print("Enter Process ID to remove: ");
                    int removeId = scanner.nextInt();
                    scheduler.removeProcess(removeId);
                    break;

                case 3:
                    scheduler.simulateScheduling();
                    break;

                case 4:
                    scheduler.displayProcesses();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
