package com.sda.todoly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.sda.todoly.PrintMenu.*;
import static com.sda.todoly.MenuOperation.*;



/**
 * This class is a part of "ToDoList" project.
 * This main reads user input and choose different operations based on user preference.
 *
 *
 * @author Fatemeh Engqvist
 */
public class Main {


    public static void main(String[] args) throws IOException {

        TaskReader taskReader = new TaskReader();
        ArrayList<Task> tasks = taskReader.loadTasks();
        TaskManager taskManager = new TaskManager(tasks);
        Scanner sc = new Scanner(System.in);

        // TODO - count tasks

        welcome(taskManager.noOfNotDoneTasks(), taskManager.noOfDoneTasks());

        boolean quit = false;
        while(!quit) {
            mainMenu();
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {

                case 1: {
                    showTasksByDateOrProjectMenu();
                    displayTasks(taskManager);
                    break;
                }
                // Add new Task
                case 2: {
                    // TODO When you choose 2 and add a new task what will happen when you choose 1? Shall the new task be shown in the list?
                    creatTask(taskManager);
                    break;
                }

                // Edit task
                case 3:
                    editTaskMenu();
                    System.out.println(">> Choose a task number from the following list");
                    printTasks(taskManager.sortByDate());
                    System.out.printf(">> ");

                    printTasks(editTask(tasks));

                    break;
                case 4:
                    TaskWriter taskWriter = new TaskWriter();
                    taskWriter.writeToFile(taskManager.getTasks());
                    System.out.println(">> End of operation.");
                    quit = true; //TODO save and quit
                    break;
                default:
                    System.out.println(">> Please choose an option between 1 to 4");
                    break;
                }

        }
    }

}
