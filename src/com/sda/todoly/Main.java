package com.sda.todoly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.sda.todoly.MenuOperation.*;


/**
 * This class is a part of "ToDoList" project.
 * This main reads user input and choose different operations based on user preference.
 *
 * @author Fatemeh Engqvist
 */
public class Main {


    public static void main(String[] args) throws IOException {

        TaskReader taskReader = new TaskReader();
        ArrayList<Task> tasks = taskReader.loadTasks();
        TaskManager taskManager = new TaskManager(tasks);
        Scanner sc = new Scanner(System.in);
        displayWelcome(taskManager.noOfNotDoneTasks(), taskManager.noOfDoneTasks());

        boolean quit = false;
        while (!quit) {
            displayMainMenu();
            System.out.println("Enter your choice: ");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {

                // Show Task List ordered by date or filtered by project based on user choice
                case 1: {
                    displayTasksByDateOrProjectMenu();
                    System.out.println("Enter your choice: ");
                    int showOption = Integer.parseInt(sc.nextLine());
                    switch (showOption) {
                        case 1:
                            printTasks(taskManager.sortByDate());
                            break;
                        case 2:
                            System.out.println("Enter Project: ");
                            String project = sc.nextLine();
                            printTasks(taskManager.filterByProject(project));
                            break;
                        default:
                    }
                    break;
                }

                // Add new Task
                case 2: {
                    addTaskToTaskList(taskManager);
                    break;
                }

                // Edit task
                case 3:
                    displayEditTaskMenu();
                    System.out.println("Enter your choice: ");
                    int editOption = Integer.parseInt(sc.nextLine());

                    switch (editOption) {
                        case 1: // Update task
                            printTasks(updateTask(tasks));
                            break;


                        // Mark as done
                        case 2:
                            printTasks(markAsDone(tasks));
                            break;


                        // Remove task
                        case 3:
                            printTasks(removeTask(tasks));
                            break;
                    }
                    break;

                case 4:
                    TaskWriter taskWriter = new TaskWriter();
                    taskWriter.writeToFile(taskManager.getTasks());
                    System.out.println(">> End of operation.");
                    quit = true; //TODO save and quit
                    break;

                case 0:

                default:
                    System.out.println(">> Please choose an option between 1 to 4");
                    break;
            }


        }
    }

}