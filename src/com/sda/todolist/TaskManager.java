package com.sda.todolist;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * This class is a part of "ToDoList" project.
 * This TaskManager provides different operation on the tasks list.
 *
 * @author Fatemeh Engqvist
 */

public class TaskManager {

    private ArrayList<Task> tasks;

    /**
     * Constructor
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * This method is used to add a new task to tasks list.
     *
     * @param
     */
    public void addTask(String title, LocalDate dueDate, String project, boolean isDone) {
        tasks.add(new Task(title, dueDate, project, isDone));
    }


    public boolean idExist(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to remove a task from tasks list.
     *
     * @param
     */

    public ArrayList<Task> removeTaskById(int id) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getId() == id) {
                tasks.remove(task);
                return tasks;
            }
        }
        return null;
    }

     public void updateTask(int id, ArrayList<String> newTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.get(i).setTitle(newTask.get(0));
                tasks.get(i).setDueDate(LocalDate.parse(newTask.get(1)));
                tasks.get(i).setProject(newTask.get(2));
                tasks.get(i).setDone(Boolean.parseBoolean(newTask.get(3)));
            }
        }
    }

    public void markAsDone(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDone(true);
            }
        }
    }

    /**
     * This method is used to return the tasks list (getter).
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> sortByDate() {
        ArrayList<Task> copiedTasks = tasks;
        return (ArrayList<Task>) copiedTasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }


    public ArrayList<Task> filterByProject(String project) {
        ArrayList<Task> copiedTasks = tasks;
        return (ArrayList<Task>) copiedTasks.stream()
                .filter(task -> task.getProject().contains(project))
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public int noOfDoneTasks() {
        int sum = 0;
        for (Task t : tasks) {
            if (t.isDone()) {
                sum++;
            }
        }
        return sum;
    }

    public int noOfNotDoneTasks() {
        int sum = 0;
        for (Task t : tasks) {
            if (!t.isDone()) {
                sum++;
            }
        }
        return sum;
    }
}