package com.sda.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import com.sda.todolist.model.TaskManager;
import com.sda.todolist.filehandler.*;
import static com.sda.todolist.Parser.*;
import static com.sda.todolist.model.TaskManager.printTasks;


/**
 * This class is a part of "ToDoList" project.
 * View class first loads all the tasks from the previous session (if exist)  to a task list, otherwise make an empty list.
 * Then asks the user to pick an options among show, add, edit or save and exit.
 * If necessary, modifies the task list based on the previous step.
 * To exit from the program the user should pick a "save and quit" option. If not saved the task list remains unchanged.
 *
 * @author Fatemeh Engqvist
 */
public class Main {


    /**
     *  Main method starts the program and shows the main menu.
     */
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(new FileTaskReader(), new FileTaskWriter());
        Scanner sc = new Scanner(System.in);
        displayWelcome(taskManager.noOfNotDoneTasks(), taskManager.noOfDoneTasks());

        boolean quit = false;
        while (!quit) {
            int input = getOption(sc, "Show Task List (by date or project)", "Add New Task", "Edit Task (update, mark as done, remove)", "Save and Quit");
            switch (input) {

                // Show Task List ordered by date or filtered by project
                case 1: {

                    //displayTaskMenu();
                    int showOption = getOption(sc, "Show Task List by date", "Show Task List by project");
                    showTaskList(showOption, taskManager, sc);
                    break;
                }

                // Add new Task
                case 2: {
                    createNewTask(sc, taskManager);
                    System.out.println("The task has been added to the task list successfully.");
                    break;
                }

                // Edit task
                case 3:
                    int editOption = getOption(sc, "Update task", "Mark as done", "Remove task");
                    editTask(editOption, taskManager, sc);
                    break;


                // Save and quit
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

    /**
     * Returns the id that the user enter if the id exist in the task list.
     * Returns -1 if the id does not exist or the user enters a non integer.
     *
     * @param sc scanner
     * @param taskManager contains task list
     * @return int id
     */
     private static int getId(Scanner sc, TaskManager taskManager) {
        System.out.println("Enter the task id from the above task list: ");
        String idString = sc.nextLine();
        if (isInt(idString)) {
            int id = Integer.parseInt(idString);
            if (taskManager.idExist(id)) {
                return id;
            }
        }
        return -1;
    }

    /**
     * Shows all tasks sorted by date or filtered by project.
     *
     * Ask the user to choose between 2 ways of showing the task list and then
     * prints the task list sorted by date or filtered by project based on what user will choose.
     * @param showOption  int that shows how the task list will to be shown
     * @param taskManager contains the task list
     * @param sc Scanner
     */
     private static void showTaskList(int showOption, TaskManager taskManager, Scanner sc) {
        switch (showOption) {
            case 1:
                printTasks(taskManager.sortByDate(), "  Task List Sorted By Date   ");
                break;

            case 2:
                System.out.println();
                String project = getProject(sc);
                printTasks(taskManager.filterByProject(project), "Task List Filtered By Project");
                break;
        }
    }


    /**
     * Create a new task and add it to the task list.
     *
     * First ask the user to enter the required fields for task: title, project, dueDate and status.
     * Then add the task to the task list
     * @param sc          scanner
     * @param taskManager contains task list
     */
     private static void createNewTask(Scanner sc, TaskManager taskManager) {
        String title = getTitle(sc);
        String project = getProject(sc);
        LocalDate dueDate = getDate(sc);
        boolean status = getStatus(sc);
        taskManager.addTask(title, project, dueDate, status);
    }


    /**
     * Update, mark as done or remove a task from a task list.
     *
     * Edit a task in a task list by first asking the user about which edit is wanted (update tas, mark as done and remove).
     * Then ask the user to enter the id of the task. If the id does not exist it will print the message that the task was not found.
     * @param option an option between update, mark as done or remove a task
     * @param taskManager taskManager
     * @param sc scanner
     */
     private static void editTask(int option, TaskManager taskManager, Scanner sc) {
        switch (option) {

            // Update task
            case 1:
                printTasks(taskManager.sortByDate(), "Task List Sorted By Date");
                int id = getId(sc, taskManager);
                if (id != -1) {
                    ArrayList<String> updatedTask = taskString(sc);
                    System.out.println();
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
                }break;
        }
    }
}