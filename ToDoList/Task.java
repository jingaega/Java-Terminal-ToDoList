package ToDoList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class Task {

    private String title;
    private boolean isDone;
    private Priority priority;
    private LocalDate dueDate;

    /**
     * Constructor for the Task class.
     * @param title The title of the task.
     * @param priority The priority level of the task.
     * @param dueDate The date the task is due.
     */
    public Task(String title, Priority priority, LocalDate dueDate) {
        this.setTitle(title);
        this.setPriority(priority);
        this.setDueDate(dueDate);
        this.isDone = false;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            System.out.println("Error: Task title cannot be empty.");
            this.title = "Untitled Task"; // Default value
        }
    }

    public boolean isDone() { return isDone; }

    public Priority getPriority() { return priority; }
    
    public void setPriority(Priority priority) { this.priority = priority; }
    
    public LocalDate getDueDate() { return dueDate; }
    
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
        return String.format("%s %s (Priority: %s, Due: %s)",
                (isDone ? "[X]" : "[ ]"),
                title,
                priority,
                dueDate.format(formatter));
    }
}