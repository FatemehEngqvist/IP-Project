package com.sda.todoly;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuOperation {

    TaskManager taskManager = new TaskManager(new TaskReader().loadTasks());

    public MenuOperation() throws FileNotFoundException {
    }

    /**
     *
     * @return Task This returns the task object made of reading user input.
     */
     static void creatTask(TaskManager taskManager) {
        Scanner sc = new Scanner(System.in);
        System.out.print(">> Title: ");
        String title = sc.nextLine();
        LocalDate dueDate = null;
        while(true) {
            try {
                System.out.print(">> Due date (yyyy-mm-dd): ");
                LocalDate tempDueDate = LocalDate.parse(sc.nextLine());
                LocalDate today = LocalDate.now();
                if(tempDueDate.isAfter(today) || tempDueDate.isEqual(today)) {
                    dueDate = tempDueDate;
                    break;
                }
            } catch (DateTimeException ex) {
                System.out.println(">> Error with date format. The date format must be yyyy-mm-dd." );
            }
        }
        System.out.print(">> Project: ");
        String project = sc.nextLine();
        boolean isDone = false;

        //Task task = new Task(title, dueDate, project, isDone); // addtask skapa inte här
         taskManager.addTask(new Task(title, dueDate, project, isDone));
        //return task;
    }

    static void displayTasks(TaskManager taskManager) {
        Scanner sc = new Scanner(System.in);
        int i = Integer.parseInt(sc.nextLine());
        switch (i) {

            case 1:
                printTasks(taskManager.sortByDate());
                break;

            case 2:
                System.out.println(">> Project: ");
                String project = sc.nextLine();
                printTasks(taskManager.filterByProject(project));
                break;
        }
    }


    static void printTasks(ArrayList<Task> tasks) {
        if(tasks.isEmpty()) {
            System.out.println(">> There is no task.");
        }
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i));
        }
    }


    static ArrayList<Task> editTask(ArrayList<Task> tasks) {
        int length = tasks.size();
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {

            /* Update task */
            case 1: // TODO - update

            case 2: // TODO - mark as done
                break;
            case 3: // TODO - remove
                break;
        }
        return tasks;
    }
}
