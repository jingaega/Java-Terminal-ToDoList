package ToDoList;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks); // Return a copy to protect original list
    }
    
    public List<Task> getSortedTasks() {
        List<Task> sortedList = new ArrayList<>(tasks);
        
        sortedList.sort(Comparator.comparing(Task::isDone)
            .thenComparing(Task::getPriority) 
            .thenComparing(Task::getDueDate));
            
        return sortedList;
    }
}