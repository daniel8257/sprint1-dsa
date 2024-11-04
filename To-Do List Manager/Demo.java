import java.util.Scanner;

public class Demo {
    private static User[] users = new User[10]; // Array to store up to 10 users
    private static int userCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTo-Do List Manager");
            System.out.println("1. User Management");
            System.out.println("2. Task Management");
            System.out.println("3. Mark Tasks as Completed");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    userManagement(scanner);
                    break;
                case 2:
                    taskManagement(scanner);
                    break;
                case 3:
                    markTasksAsCompleted(scanner);
                    break;
                case 4:
                    viewTasks(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // User Management Menu
    private static void userManagement(Scanner scanner) {
        System.out.print("Enter user name to create: ");
        String name = scanner.nextLine();
        createUser(name);
    }

    // Task Management Menu
    private static void taskManagement(Scanner scanner) {
        System.out.print("Enter user name to add a task: ");
        String userName = scanner.nextLine();
        User user = findUser(userName);
        if (user == null) {
            System.out.println("User not found. Please add the user first.");
            return;
        }
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        user.getTaskList().addTask(description);
        System.out.println("Task added to " + userName + "'s to-do list.");
    }

    // Mark Tasks as Completed Menu
    private static void markTasksAsCompleted(Scanner scanner) {
        System.out.print("Enter user name to mark a task as completed: ");
        String userName = scanner.nextLine();
        User user = findUser(userName);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.print("Enter task number to mark as completed: ");
        int taskIndex = scanner.nextInt() - 1; // Task index (0-based)
        user.getTaskList().markTaskAsCompleted(taskIndex);
    }

    // View Tasks Menu
    private static void viewTasks(Scanner scanner) {
        System.out.print("Enter user name to view tasks: ");
        String userName = scanner.nextLine();
        User user = findUser(userName);
        if (user == null) {
            System.out.println("User not found.");
        } else {
            System.out.println(userName + "'s To-Do List:");
            user.getTaskList().printTasks();
        }
    }

    private static void createUser(String name) {
        if (userCount >= users.length) {
            System.out.println("User limit reached.");
            return;
        }
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                System.out.println("User already exists.");
                return;
            }
        }
        users[userCount++] = new User(name);
        System.out.println("User " + name + " created.");
    }

    private static User findUser(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        return null;
    }
}
