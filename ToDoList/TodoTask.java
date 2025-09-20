package ToDoList;
import java.time.LocalDate;

class TodoTask extends Task {

    public TodoTask(String title, Priority priority, LocalDate dueDate) {
        super(title, priority, dueDate);
    }
}
