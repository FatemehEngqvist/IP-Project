package com.sda.todolist;

import com.sda.todolist.filehandler.FileTaskReader;
import com.sda.todolist.filehandler.FileTaskWriter;
import com.sda.todolist.model.TaskManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static com.sda.todolist.MenuOperation.*;
import static com.sda.todolist.TaskPresenter.*;

/**
 * This class is a part of "ToDoList" project.
 * This main reads user input and choose different operations based on user preference.
 *
 * @author Fatemeh Engqvist
 */
public class Main {

    /**
     * @return Task This returns the task object made of reading user input.
     */
    static void addTaskToTaskList(Scanner sc, TaskManager taskManager) {
        String title = getString(sc, "title");
        String project = getString(sc, "project");
        LocalDate dueDate = getDate(sc);
        boolean status = getStatus(sc);

        taskManager.addTask(title, dueDate, project, status);
    }

    static int getId(Scanner sc, TaskManager taskManager) {
        System.out.println("Enter the task id from the above task list: ");
        String idString = sc.nextLine();
        if (isInt(idString)) {
            int id = Integer.parseInt(idString);
            if(taskManager.idExist(id)) {
                return id;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        TaskManager taskManager = new TaskManager(new FileTaskReader(), new FileTaskWriter());
        Scanner sc = new Scanner(System.in);


        displayWelcome(taskManager.noOfNotDoneTasks(), taskManager.noOfDoneTasks());

        boolean quit = false;
        while (!quit) {
            int input = getOption(sc, "Show Task List (by date or project)", "Add New Task", "Edit Task (update, mark as done, remove", "Save and Quit");
            switch (input) {

                // Show Task List ordered by date or filtered by project
                case 1: {

                    //displayTaskMenu();
                    int showOption = getOption(sc, "Show Task List by date", "Show Task List by project");
                    switch (showOption) {
                        case 1:
                            printTasks(taskManager.sortByDate(), "  Task List Sorted By Date   ");
                            break;

                        case 2:
                            System.out.println();
                            String project = getString(sc, "project");
                            printTasks(taskManager.filterByProject(project), "Task List Filtered By Project");
                            break;
                    }

                    break;
                }

                // Add new Task
                case 2: {
                    addTaskToTaskList(sc, taskManager);
                    System.out.println("The task has been added to the task list successfully.");
                    break;
                }

                // Edit task
                case 3:
                    int editOption = getOption(sc, "Update task", "Mark as done", "Remove task");
                    switch (editOption) {

                        // Update task
                        case 1:
                            printTasks(taskManager.sortByDate(), "Task List Sorted By Date");
                            int id = getId(sc, taskManager);
                            if (id != -1) {
                                ArrayList<String> updatedTask = taskString(sc);
                                taskManager.updateTask(id, updatedTask);
                                System.out.println("The task has been updated successfully.");
                            } else {
                                System.out.println("No task was found with this id.");
                            }
                            break;

                        // Mark as done
                        case 2:
                            printTasks(taskManager.sortByDate(), "Task List Sorted By Date");
                            id = getId(sc, taskManager);
                            if (id != -1) {
                                taskManager.markAsDone(id);
                                System.out.println("The task has been marked as done successfully.");
                            } else {
                                System.out.println("No task was found with this id.");
                            }
                            break;

                        // Remove task
                        case 3:
                            printTasks(taskManager.sortByDate(), "Task List Sorted By Date");
                            id = getId(sc, taskManager);
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
                    taskManager.saveTasks();
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