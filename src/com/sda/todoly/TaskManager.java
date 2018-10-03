package com.sda.todoly;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * This class is a part of "ToDoList" project.
 * This TaskManager provides different operation on the tasks list.
 *
 *
 * @author Fatemeh Engqvist
 */

public class TaskManager {

    private ArrayList<Task> tasks;
    private int nextTaskID;

    /**
     * Constructor
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
        nextTaskID = 1;
    }

    /**
     * This method is used to add a new task to tasks list.
     * @param
     */
    public void addTask(String title, LocalDate dueDate, String project, boolean isDone) {
        tasks.add(new Task(title, dueDate, project, isDone));
    }

    /**
     * This method is used to remove a task from tasks list.
     * @param task
     */

    // TODO - check which removeTask method to hold
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void removeTask(int id) {
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext()) {
            Task task = it.next();
            if(task.getId() == id) {
                tasks.remove(task);
            }
        }
    }

    /**
     * This method is used to return the tasks list (getter).
     */
    ArrayList<Task> getTasks() {
        return tasks;
    }

    int getNumTasks() {
        return tasks.size();
    }

    ArrayList<Task> sortByDate() {

        return (ArrayList<Task>) tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    ArrayList<Task> filterByProject(String project) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getProject().contains(project))
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
    }

    public int noOfDoneTasks() {
        int sum = 0;
        for(Task t : tasks) {
            if(t.isDone()) {
                sum++;
            }
        }
        return sum;
    }

    public int noOfNotDoneTasks() {
        int sum = 0;
        for(Task t : tasks) {
            if(!t.isDone()) {
                sum++;
            }
        }
        return sum;
    }



}