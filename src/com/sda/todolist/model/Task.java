package com.sda.todolist.model;

import java.time.LocalDate;


public class Task implements Comparable<Task> {

    private String title;
    private LocalDate dueDate;
    private String project;
    private boolean isDone;
    private static int lastId = 0;
    private int id;

    public Task(String title, LocalDate dueDate, String project, boolean isDone) {
        lastId ++;
        id = lastId;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void updateTask(String title, LocalDate dueDate, String project, boolean isDone) {
        setTitle(title);
        setDueDate(dueDate);
        setProject(project);
        setDone(isDone);
    }

    public int compareTo(Task t) {
        return dueDate.compareTo(t.getDueDate());
    }

    @Override
    public String toString() {
        String status = "task is Not Done";
        if(isDone) {
            status = "task is Done";
        }
        return  "Task id: " + id + "  Title: " + title + "  Due date: " +
                dueDate + "  Project: " + project + "  Status: " + status ;
    }
}