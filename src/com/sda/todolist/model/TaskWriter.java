package com.sda.todolist.model;

import java.util.ArrayList;

public interface TaskWriter {

    void saveTasks(ArrayList<Task> tasks);
}
