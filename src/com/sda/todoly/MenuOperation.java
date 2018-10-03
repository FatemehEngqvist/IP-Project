package com.sda.todoly;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuOperation {



    public MenuOperation() throws FileNotFoundException {
    }

    static void displayWelcome(int undoneTasks, int doneTasks) {
        System.out.println();
        System.out.println("Welcome to ToDoly.");
        System.out.println("You have " + undoneTasks + " tasks todo and " + doneTasks + " tasks are done!");
    }

    static void displayMainMenu() {
        System.out.println();
        System.out.println("Pick an option: ");
        System.out.println(" (1) Show Task List (by date or project)");
        System.out.println(" (2) Add New Task");
        System.out.println(" (3) Edit Task (update, mark as done, remove");
        System.out.println(" (4) Save and Quite");
        System.out.println();
    }

    static void displayTasksByDateOrProjectMenu() {
        System.out.println();
        System.out.println("Pick an option: ");
        System.out.println("(1) Show Task List by date");
        System.out.println("(2) Show Task List by project");
        System.out.println();
    }

    static void displayEditTaskMenu() {
        System.out.println();
        System.out.println("Pick an option: ");
        System.out.println("(1) Update task");
        System.out.println("(2) Mark as done");
        System.out.println("(3) Remove task");
        System.out.println();
    }


    /**
     * @return Task This returns the task object made of reading user input.
     */
    static void addTaskToTaskList(TaskManager taskManager) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter title: ");
        System.out.println();
        String title = sc.nextLine();
        System.out.print("Enter due date with the format yyyy-mm-dd: ");
        LocalDate dueDate;
        while (true) {
            try {
                dueDate = LocalDate.parse(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter the correct format date yyyy-mm-dd!");
            }
        }

        System.out.println("Enter project: ");
        System.out.println();
        String project = sc.nextLine();
        boolean isDone = false;
        taskManager.addTask(title, dueDate, project, isDone);
    }


    static void printTasks(ArrayList<Task> tasks) {
        System.out.println();
        if (tasks.isEmpty()) {
            System.out.println("There are no task to show.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }
    }


    static ArrayList<Task> updateTask(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        printTasks(tasks);
        System.out.println();
        System.out.println("Enter the id of the task you want to edit: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Task t : tasks) {
            if (t.getId() == id) {
                System.out.println("Enter the new title: ");
                System.out.println();
                String title = sc.nextLine();
                t.setTitle(title);

                System.out.print("Enter due date with the format yyyy-mm-dd: ");
                LocalDate dueDate;
                while (true) {
                    try {
                        dueDate = LocalDate.parse(sc.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Please enter the correct format date yyyy-mm-dd!");
                    }
                }

                System.out.println("Enter the new project: ");
                System.out.println();
                t.setTitle(sc.nextLine());
            }
        }
        return tasks;
    }

    static ArrayList<Task> markAsDone(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        printTasks(tasks);
        System.out.println();
        System.out.println("Enter the id of the task you want to mark as done: ");
        int id = sc.nextInt();
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDone(true);
            }
        }
        return tasks;
    }

    static ArrayList<Task> removeTask(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        printTasks(tasks);
        System.out.println();
        System.out.println("Enter the id of the task you want to remove: ");
        int id = sc.nextInt();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(tasks.get(i));
            }
        }
        return tasks;
    }


}
