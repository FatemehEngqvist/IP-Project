package com.sda.todolist.filehandler;

import java.io.IOException;
import com.sda.todolist.model.TaskReader;
import com.sda.todolist.model.Task;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is a part of "ToDoList" project.
 * This FileTaskReader reads from the file and loads the tasks to a task list.
 *
 * @author Fatemeh Engqvist
 */
public class FileTaskReader implements TaskReader {

    /**
     * Load tasks saved in tasks.txt file to a list.
     * Returns the task list.
     * @return task list
     */
    public ArrayList<Task> loadTasks() {
        int id;
        ArrayList<Task> tasksToShow = new ArrayList<>();

        try {

            File file = new File("tasks.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            Task currentTask;

            while ((line = bufferedReader.readLine()) != null) {

                id = Integer.parseInt(line);
                line = bufferedReader.readLine();
                String title = line;
                line = bufferedReader.readLine();
                LocalDate dueDate = LocalDate.parse(line);
                line = bufferedReader.readLine();
                String project = line;
                line = bufferedReader.readLine();
                boolean isDone = Boolean.valueOf(line);


                currentTask = new Task(title, dueDate, project, isDone, id);
                tasksToShow.add(currentTask);
            }
            fileReader.close();


        }

        catch (IOException ex) {
            //System.out.println(ex.toString());
            //System.out.println("Could not find the file tasks.txt.");
            //System.out.println("The tasks.txt file will be created");
        }
        return tasksToShow;
    }
}
