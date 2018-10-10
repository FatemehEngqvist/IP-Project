package com.sda.todolist.model;

import java.util.ArrayList;

/**
 * This Interface is a part of "ToDoList" project.
 *
 * @author Fatemeh Engqvist
 */
public interface TaskWriter {

    void saveTasks(ArrayList<Task> tasks);
}
