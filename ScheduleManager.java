import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public boolean addTask(Task task) {
        for (Task existingTask : tasks) {
            if (task.overlapsWith(existingTask)) {
                System.out.println("Error: Task conflicts with existing task \"" + existingTask.getDescription() + "\".");
                return false;
            }
        }
        tasks.add(task);
        Collections.sort(tasks);
        System.out.println("Task added successfully. No conflicts.");
        return true;
    }

    public boolean removeTask(String description) {
        Task toRemove = null;
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                toRemove = task;
                break;
            }
        }
        if (toRemove != null) {
            tasks.remove(toRemove);
            System.out.println("Task removed successfully.");
            return true;
        } else {
            System.out.println("Error: Task not found.");
            return false;
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}
