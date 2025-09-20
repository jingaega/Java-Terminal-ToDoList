package ToDoList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private static final TaskManager manager = new TaskManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    toggleTaskStatus();
                    break;
                case "3":
                    removeTask();
                    break;
                case "4":
                    System.out.println("Exiting application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== To-Do List ==========");
        // Use the new getSortedTasks() method to display the list
        List<Task> sortedTasks = manager.getSortedTasks();
        if (sortedTasks.isEmpty()) {
            System.out.println("No tasks yet. Add one!");
        } else {
            for (int i = 0; i < sortedTasks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + sortedTasks.get(i));
            }
        }
        System.out.println("================================");
        System.out.println("Options:");
        System.out.println("  1. Add a new task");
        System.out.println("  2. Mark a task as done/undone");
        System.out.println("  3. Remove a task");
        System.out.println("  4. Exit");
        System.out.println("================================");
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        Priority priority = null;
        while (priority == null) {
            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            try {
                priority = Priority.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please try again.");
            }
        }

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (yyyy-MM-dd): ");
            try {
                dueDate = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }

        manager.addTask(new TodoTask(title, priority, dueDate));
        System.out.println("Task added successfully.");
    }

    private static void toggleTaskStatus() {
        System.out.print("Enter the number of the task to toggle: ");
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            List<Task> sortedTasks = manager.getSortedTasks(); // Get the currently displayed list
            if (taskNumber > 0 && taskNumber <= sortedTasks.size()) {
                Task taskToToggle = sortedTasks.get(taskNumber - 1);
                taskToToggle.toggleDone();
                System.out.println("Task status updated.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void removeTask() {
        System.out.print("Enter the number of the task to remove: ");
         try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            List<Task> sortedTasks = manager.getSortedTasks(); // Get the currently displayed list
            if (taskNumber > 0 && taskNumber <= sortedTasks.size()) {
                Task taskToRemove = sortedTasks.get(taskNumber - 1);
                manager.removeTask(taskToRemove);
                System.out.println("Task removed successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
}
