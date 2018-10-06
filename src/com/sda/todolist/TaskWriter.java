package com.sda.todolist;

import com.sda.todolist.Task;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is a part of "ToDoList" project.
 *
 * This main reads user input and choose different operations based on user preference.
 */

public class TaskWriter {

    public void writeToFile(ArrayList<Task> tasks ) { // TODO - skapa ta en lista av Task

        //if the file exists
        FileWriter fileWriter = null;

        try {

             fileWriter = new FileWriter("tasks.txt");

             for(Task task: tasks) {
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
