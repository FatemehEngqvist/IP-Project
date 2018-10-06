package com.sda.todolist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class is a part of "ToDoList" project.
 * This main reads user input and choose different operations based on user preference.
 *
 * @author Fatemeh Engqvist
 */
public class Main {
    static MenuOperation p = new MenuOperation();

    public static void main(String[] args) throws IOException {

        TaskReader taskReader = new TaskReader();
        ArrayList<Task> tasks = taskReader.loadTasks();
        TaskManager taskManager = new TaskManager(tasks);
        Scanner sc = new Scanner(System.in);


        p.displayWelcome(taskManager.noOfNotDoneTasks(), taskManager.noOfDoneTasks());

        boolean quit = false;
        while (!quit) {
            p.displayMainMenu();
            String inputString = sc.nextLine();
            if (p.isInt(inputString)) {
                int input = Integer.parseInt(inputString);
                switch (input) {

                    // Show Task List ordered by date or filtered by project
                    case 1: {

                        //displayTaskMenu();
                        int showOption = p.getOption(sc, "Show Task List by date", "Show Task List by project");
                        switch (showOption) {
                            case 1:
                                p.printTasksByDate(taskManager);
                                break;

                            case 2:
                                System.out.println();
                                System.out.println("Enter Project: ");
                                String project = sc.nextLine();
                                p.printTasksBypProject(taskManager);
                                break;
                        }

                        break;
                    }

                    // Add new Task
                    case 2: {
                        p.addTaskToTaskList(sc,taskManager);
                        System.out.println("The task has been added to the task list successfully.");
                        break;
                    }

                    // Edit task
                    case 3:
                        int editOption = p.getOption(sc, "Update task", "Mark as done", "Remove task");
                        switch (editOption) {

                            // Update task
                            case 1:
                                p.printTasksByDate(taskManager);
                                int id = p.getId(sc, taskManager);
                                if (id != -1) {
                                    ArrayList<String> updatedTask = p.taskString(sc);
                                    taskManager.updateTask(id, updatedTask);
                                    System.out.println("The task has been updated successfully.");
                                } else {
                                    System.out.println("No task was found with this id.");
                                }
                                break;

                            // Mark as done
                            case 2:
                                p.printTasksByDate(taskManager);
                                id = p.getId(sc, taskManager);
                                if (id != -1) {
                                    taskManager.markAsDone(id);
                                    System.out.println("The task has been marked as done successfully.");
                                } else {
                                    System.out.println("No task was found with this id.");
                                }
                                break;

                            // Remove task
                            case 3:
                                p.printTasksByDate(taskManager);
                                id = p.getId(sc, taskManager);
                                if (id != -1) {
                                    taskManager.removeTaskById(id);
                                    System.out.println("The task has been removed successfully.");
                                } else {
                                    System.out.println("No task was found with this id.");
                                }
                                break;
                        }
                        break;

                    case 4:
                        TaskWriter taskWriter = new TaskWriter();
                        taskWriter.writeToFile(tasks);
                        System.out.println("Saving... and Exit.");
                        quit = true;
                        break;

                    default:
                        System.out.println("Please enter a number between 1 to 4!");
                        break;
                }
            }
        }
    }
}