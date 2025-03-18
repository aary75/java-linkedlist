import java.util.Scanner;

class TextState {
    String content;
    TextState next;
    TextState prev;

    public TextState(String content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }
}

class TextEditor {
    private TextState current;
    private int maxHistorySize;
    private int historySize;

    public TextEditor(int maxHistorySize) {
        this.maxHistorySize = maxHistorySize;
        this.historySize = 0;

        // Initial empty state
        current = new TextState("");
    }

    // Add new text state (saving history)
    public void addState(String newText) {
        TextState newState = new TextState(newText);

        // Connect the new state with current
        newState.prev = current;
        if (current != null) {
            current.next = newState;
        }

        current = newState;
        historySize++;

        // Trim history if it exceeds the limit
        if (historySize > maxHistorySize) {
            trimHistory();
        }

        System.out.println("New state added: " + current.content);
    }

    // Undo action
    public void undo() {
        if (current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.content);
        } else {
            System.out.println("No more undo operations available.");
        }
    }

    // Redo action
    public void redo() {
        if (current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.content);
        } else {
            System.out.println("No more redo operations available.");
        }
    }

    // Display the current state of the text
    public void displayCurrentState() {
        System.out.println("\nCurrent State: " + current.content);
    }

    // Trim history if it exceeds the maximum size
    private void trimHistory() {
        TextState temp = current;

        // Move back to the first node
        while (temp.prev != null) {
            temp = temp.prev;
        }

        // Remove extra nodes from the beginning
        while (historySize > maxHistorySize) {
            if (temp.next != null) {
                temp = temp.next;
                temp.prev = null;  // Remove oldest state
                historySize--;
            }
        }
    }
}

public class TextEditorUndoRedo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor(10);  // Max history size = 10

        while (true) {
            System.out.println("\nText Editor Menu:");
            System.out.println("1. Type new text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current State");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    String newText = scanner.nextLine();
                    editor.addState(newText);
                    break;

                case 2:
                    editor.undo();
                    break;

                case 3:
                    editor.redo();
                    break;

                case 4:
                    editor.displayCurrentState();
                    break;

                case 5:
                    System.out.println("Exiting Text Editor. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}


// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Choose an option: 1
// Enter new text: Hai
// New state added: Hai

// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Choose an option: 4

// Current State: Hai

// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Choose an option: 1
// Enter new text: Hello
// New state added: Hello

// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Choose an option: 4

// Current State: Hello

// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
// Choose an option: 2
// Undo: Hai

// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5.  Exit
// Choose an option: 4

// Current State: Hai

// Text Editor Menu:
// 1. Type new text
// 2. Undo
// 3. Redo
// 4. Display Current State
// 5. Exit
//Choose an option: 5
// Exiting Text Editor. Goodbye!
