package com.sda.todolist;

import java.util.ArrayList;
import com.sda.todolist.model.Task;

public class TaskPresenter {

    static void printTasks(ArrayList<Task> tasks, String header) {
        System.out.println();
        System.out.println("******************************* " + header + " *****************************");
        System.out.println();
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks to show.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(tasks.get(i));
            }
        }
        System.out.println();
        System.out.println("*******************************************************************************************");
    }

}
