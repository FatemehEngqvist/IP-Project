package com.sda.todolist.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * This class is a part of "ToDoList" project.
 * TaskManager class builds a task list and loads the saved tasks (if exists) into the list.
 * It modifies the task list.
 *
 * @author Fatemeh Engqvist
 */
public class TaskManager {

    private ArrayList<Task> tasks;
    private TaskWriter taskWriter;
    private int id;


    /**
     * Construct a task manager.
     * Loads previous saved tasks to the tasks array list.
     * Assign the task writer.
     */
    public TaskManager(TaskReader taskReader, TaskWriter taskWriter) {
        this.tasks = taskReader.loadTasks();
        this.id = getMaxId();
        this.taskWriter = taskWriter;
    }

    public void saveTasks() {
        taskWriter.saveTasks(tasks);
    }

    /**
     * Adds a new task to tasks list.
     *
     * @param title   task's title
     * @param dueDate task's due date
     * @param project task's project category
     * @param isDone  task's status
     */
    public void addTask(String title, LocalDate dueDate, String project, boolean isDone) {
        id += 1;
        tasks.add(new Task(title, dueDate, project, isDone, id));
    }

    /**
     * Checks if a task with this id exists in the task list.
     * @param id task id
     * @return true if there is a task with the id, false otherwise
     */
    public boolean idExist(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a task from tasks list by task id.
     * @param id task id
     */
    public void removeTaskById(int id) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getId() == id) {
                tasks.remove(task);
            }
        }
    }

    /**
     * Updates all fields of a task from the task list by the given task id and new update details.
     * @param id task id
     * @param newTask array list of the updated version of task {title, dueDate, project, status}
     */
    public void updateTask(int id, ArrayList<String> newTask) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setTitle(newTask.get(0));
                t.setProject(newTask.get(2));
                t.setDueDate(LocalDate.parse(newTask.get(1)));
                t.setDone(Boolean.parseBoolean(newTask.get(3)));
            }
        }
    }

    /**
     * Marks a task from the task list done by task id
     * @param id task id
     */
    public void markAsDone(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDone(true);
            }
        }
    }


    /**
     * Sort the task list by due date
     * @return the sorted task list by due date
     */
    public List<Task> sortByDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    /**
     * Filters the task list by project name
     * @param project task project category
     * @return filtered task list by project name
     */
    public List<Task> filterByProject(String project) {
        return tasks.stream()
                .filter(task -> task.getProject().contains(project))
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    /**
     * Returns the maximum task id in the task list
     * @return maximum id
     */
     private int getMaxId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0);
    }

    /**
     * Returns the number of tasks that are marked done in the task list
     * @return number of done tasks
     */
    public int noOfDoneTasks() {
        int sum = 0;
        for (Task t : tasks) {
            if (t.isDone()) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Returns the number of tasks that are marked not done in the task list
     * @return number of undone tasks
     */
    public int noOfNotDoneTasks() {
        int sum = 0;
        for (Task t : tasks) {
            if (!t.isDone()) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Returns a string of a task from the task list specified by id.
     * @param id task id
     * @return the task specified by id in the string format
     */
    String getTaskString(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t.toString();
            }
        }
        return "";
    }

    /**
     * Prints the task list by the header on top
     * @param tasks task list
     * @param header the title of the task list (e.g sorted or filtered)
     */
    public static void printTasks(List<Task> tasks, String header) {
        System.out.println();
        System.out.println("******************************* " + header + " *****************************");
        System.out.println();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks to show.");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
        System.out.println();
    }

}
