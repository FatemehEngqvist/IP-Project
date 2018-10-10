package com.sda.todolist.model;

import java.time.LocalDate;

/**
 * This class is a part of "ToDoList" project.
 * A single task which is created by TaskManager class.
 *
 * @author Fatemeh Engqvist
 */
public class Task implements Comparable<Task> {

    private String title;
    private LocalDate dueDate;
    private String project;
    private boolean isDone;
    private int id;


    /**
     * Constructor of the task takes title, due date, project, status and id number.
     */
    public Task(String title, LocalDate dueDate, String project, boolean isDone, int id) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.isDone = isDone;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getProject() {
        return project;
    }

    public boolean isDone() {
        return isDone;
    }

     void setTitle(String title) {
        this.title = title;
    }

     void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

     void setProject(String project) {
        this.project = project;
    }

     void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Compares this task with the specified task for order using due date.
     * Returns a negative, zero, or a positive integer as this.task due date is before, equal, after the specified task.
     * @param t task
     * @return  negative, zero, or a positive integer as this.task due date is before, equal, after the specified task.
     */
    @Override
    public int compareTo(Task t) {
        return dueDate.compareTo(t.getDueDate());
    }

    /**
     * Returns a task in String format.
     * @return task
     */
    @Override
    public String toString() {
        String status = "task is Not Done";
        if (isDone) {
            status = "task is Done";
        }
        return "Task id: " + id + "  Title: " + title + "  Due date: " +
                dueDate + "  Project: " + project + "  Status: " + status;
    }
}
