import java.util.ArrayList;

/**
 * This class is a part of "ToDoList" project.
 * This TaskEditor provides different operation on the tasks list.
 *
 *
 * @author Fatemeh Engqvist
 */

public class TaskEditor {

    private ArrayList<Task> tasks;

    /**
     * Constructor
     */
    public TaskEditor() {
        tasks = new ArrayList<>();
    }

    /**
     * This method is used to add a new task to tasks list.
     * @param Task
     */
    public void addNewTask(Task task) {
        tasks.add(task);
    }

    /**
     * This method is used to remove a task from tasks list.
     * @param Task
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * This method is used to return the tasks list (getter).
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }
}
