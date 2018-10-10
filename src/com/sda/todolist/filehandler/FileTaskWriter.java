package com.sda.todolist.filehandler;

import com.sda.todolist.model.TaskWriter;
import com.sda.todolist.model.Task;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is a part of "ToDoList" project.
 * The FileTaskWriter class saves the task list to tasks.txt file.
 *
 * @author Fatemeh Engqvist
 */
public class FileTaskWriter implements TaskWriter {

    /**
     * Save the task list to tasks.txt file.
     * @param tasks task list
     */
    public void saveTasks(ArrayList<Task> tasks ) {

        FileWriter fileWriter = null;

        try {

             fileWriter = new FileWriter("tasks.txt");

             for(Task task: tasks) {
                 fileWriter.write(task.getId() + "\n");
                 fileWriter.write(task.getTitle() + "\n");
                fileWriter.write(task.getDueDate().toString() + "\n");
                fileWriter.write(task.getProject() + "\n");
                fileWriter.write(String.valueOf(task.isDone()) + "\n");
             }

            fileWriter.flush();

        } catch (IOException e) {
            System.out.println("Error writing to a file.");
        } finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        }
    }
}
