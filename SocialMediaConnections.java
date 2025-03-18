import java.util.Scanner;

class User {
    int userId;
    String name;
    int age;
    User next;               // Next user in the list
    FriendNode friendList;   // Friend connections as a linked list

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.next = null;
        this.friendList = null;  // Initialize friend list as empty
    }
}

class FriendNode {
    int friendId;
    FriendNode next;

    public FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class FriendList {
    private User head;

    // Add a new user to the linked list
    public void addUser(int id, String name, int age) {
        User newUser = new User(id, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newUser;
        }
        System.out.println("User added: " + name);
    }

    // Find a user by ID
    private User findUser(int id) {
        User current = head;
        while (current != null) {
            if (current.userId == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Add a friend connection between two users
    public void addFriend(int id1, int id2) {
        User user1 = findUser(id1);
        User user2 = findUser(id2);

        if (user1 != null && user2 != null) {
            // Add id2 to user1's friend list
            if (!isFriend(user1, id2)) {
                FriendNode newFriend1 = new FriendNode(id2);
                newFriend1.next = user1.friendList;
                user1.friendList = newFriend1;
            }

            // Add id1 to user2's friend list
            if (!isFriend(user2, id1)) {
                FriendNode newFriend2 = new FriendNode(id1);
                newFriend2.next = user2.friendList;
                user2.friendList = newFriend2;
            }

            System.out.println("Friend connection added between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Remove a friend connection
    public void removeFriend(int id1, int id2) {
        User user1 = findUser(id1);
        User user2 = findUser(id2);

        if (user1 != null && user2 != null) {
            user1.friendList = removeFriendFromList(user1.friendList, id2);
            user2.friendList = removeFriendFromList(user2.friendList, id1);

            System.out.println("Friend connection removed between " + user1.name + " and " + user2.name);
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Helper method to remove a friend from the friend list
    private FriendNode removeFriendFromList(FriendNode head, int friendId) {
        if (head == null) return null;

        if (head.friendId == friendId) {
            return head.next;  // Remove the first node
        }

        FriendNode current = head;
        while (current.next != null) {
            if (current.next.friendId == friendId) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
        return head;
    }

    // Display all friends of a user
    public void displayFriends(int userId) {
        User user = findUser(userId);
        if (user != null) {
            System.out.print(user.name + "'s Friends: ");
            FriendNode current = user.friendList;
            if (current == null) {
                System.out.println("No friends.");
            } else {
                while (current != null) {
                    System.out.print(current.friendId + " ");
                    current = current.next;
                }
                System.out.println();
            }
        } else {
            System.out.println("User not found.");
        }
    }

    // Find mutual friends between two users
    public void mutualFriends(int id1, int id2) {
        User user1 = findUser(id1);
        User user2 = findUser(id2);

        if (user1 != null && user2 != null) {
            System.out.print("Mutual friends between " + user1.name + " and " + user2.name + ": ");
            
            FriendNode f1 = user1.friendList;
            boolean found = false;

            while (f1 != null) {
                if (isFriend(user2, f1.friendId)) {
                    System.out.print(f1.friendId + " ");
                    found = true;
                }
                f1 = f1.next;
            }

            if (!found) {
                System.out.println("No mutual friends.");
            } else {
                System.out.println();
            }
        } else {
            System.out.println("One or both users not found.");
        }
    }

    // Check if two users are friends
    private boolean isFriend(User user, int friendId) {
        FriendNode current = user.friendList;
        while (current != null) {
            if (current.friendId == friendId) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Search for a user by ID or name
    public void searchUser(String key) {
        User current = head;
        while (current != null) {
            if (String.valueOf(current.userId).equals(key) || current.name.equalsIgnoreCase(key)) {
                System.out.println("User Found: " + current.userId + ", " + current.name + ", " + current.age + " years old");
                return;
            }
            current = current.next;
        }
        System.out.println("User not found.");
    }

    // Count the number of friends for each user
    public void countFriends() {
        User current = head;
        while (current != null) {
            int count = 0;
            FriendNode friend = current.friendList;
            while (friend != null) {
                count++;
                friend = friend.next;
            }
            System.out.println(current.name + " has " + count + " friends.");
            current = current.next;
        }
    }
}

public class SocialMediaConnections {
    public static void main(String[] args) {
        FriendList fl = new FriendList();
        Scanner scanner = new Scanner(System.in);

        // Adding some users
        fl.addUser(1, "Alice", 25);
        fl.addUser(2, "Bob", 30);
        fl.addUser(3, "Charlie", 22);
        fl.addUser(4, "Diana", 28);

        // Adding friend connections
        fl.addFriend(1, 2);
        fl.addFriend(1, 3);
        fl.addFriend(2, 4);

        // Displaying friends
        fl.displayFriends(1);
        fl.displayFriends(2);

        // Removing a friend connection
        fl.removeFriend(1, 2);

        // Displaying mutual friends
        fl.mutualFriends(1, 2);

        // Searching for users by ID and Name
        fl.searchUser("3");
        fl.searchUser("Diana");

        // Counting friends
        fl.countFriends();

        scanner.close();
    }
}

// User added: Alice
// User added: Bob
// User added: Charlie
// User added: Diana
// Friend connection added between Alice and Bob
// Friend connection added between Alice and Charlie
// Friend connection added between Bob and Diana
// Alice's Friends: 3 2
// Bob's Friends: 4 1
// Friend connection removed between Alice and Bob
// Mutual friends between Alice and Bob: No mutual friends.
// User Found: 3, Charlie, 22 years old
// User Found: 4, Diana, 28 years old
// Alice has 1 friends.
// Bob has 1 friends.
// Charlie has 1 friends.
// Diana has 1 friends.

