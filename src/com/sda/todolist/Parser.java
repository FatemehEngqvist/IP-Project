package com.sda.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is a part of "ToDoList" project.
 * This Parser class reads the input from the user in the command line and parse it.
 *
 * @author Fatemeh Engqvist
 */

class Parser {

    /**
     * Checks if a String can be parsed to int
     * Else returns false.
     *
     * @param input a string
     * @return true if the String can be parsed to int
     */
    static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Displayes the welcome menu and the number of undone and done tasks.
     *
     * @param undoneTasks the number of tasks that are not done
     * @param doneTasks   the number of tasks that are done
     */
    static void displayWelcome(int undoneTasks, int doneTasks) {
        System.out.println();
        System.out.println("Welcome to ToDoList");
        System.out.println("You have " + undoneTasks + " tasks todo and " + doneTasks + " tasks are done!");
    }


    /**
     * Asks user to enter a title.
     * Returns the string format of the task title
     * @param sc the scanner that scans the data
     * @return title
     */
    static String getTitle(Scanner sc) {
        while (true) {
            System.out.print("Enter the title: ");
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
        }
    }

    /**
     * Asks the user to enter the project.
     * Returns the project name in String format.
     * @param sc the scanner that scans the data
     * @return project
     */
    static String getProject(Scanner sc) {
        while (true) {
            System.out.print("Enter the project: ");
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
        }
    }

    /**
     * Asks user to enter due date in yyyy-mm-dd format.
     * If the format is wrong asks again.
     * Returns the due date.
     * @param sc the scanner that scans the data
     * @return due date
     */
    static LocalDate getDate(Scanner sc) {
        System.out.print("Enter the due date in yyyy-mm-dd format: ");
        LocalDate dueDate;
        while (true) {
            try {
                dueDate = LocalDate.parse(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a correct format yyyy-mm-dd!");
            }
        }
        return dueDate;
    }

    /**
     * Asks the user to enter the status of the task.
     * Returns true if the entered status by user is done and false if the input from the user is undone
     * @param sc the scanner that scans the data
     * @return true if done and false if undone
     */
    static boolean getStatus(Scanner sc) {
        String statusString;
        while (true) {
            System.out.print("Enter the task status \"done\" or \"undone\": ");
            statusString = sc.nextLine();
            if (statusString.equals("done")) {
                return true;
            } else if (statusString.equals("undone")) {
                return false;
            }
        }
    }

    /**
     * Asks the user to enter the title, project, due date and status.
     * Returns the task in a list of string.
     * @param sc the scanner that scans the data
     * @return task
     */
    static ArrayList<String> taskString(Scanner sc) {

        ArrayList<String> updatedTask = new ArrayList<>();

        String title = getTitle(sc);
        String project = getProject(sc);
        LocalDate dueDate = getDate(sc);
        boolean status = getStatus(sc);

        updatedTask.add(title);
        updatedTask.add(project);
        updatedTask.add(dueDate.toString());
        updatedTask.add(String.valueOf(status));

        return updatedTask;
    }


    /**
     * Prints the user the options that are available to choose.
     * Asks user to enter an option number or 0 to go back to main menu.
     * If the entered number is not valid asks the user again.
     * Returns the number.
     *
     * @param sc the scanner that scans the data
     * @param options menu or submenu options
     * @return option number
     */
    static int getOption(Scanner sc, String... options) {
        int max = options.length;
        System.out.println();
        System.out.println("Pick an option (Or 0 to go back to the main menu): ");
        for (int i = 0; i < options.length; ++i) {
            System.out.println("  " + (i + 1) + ") " + options[i]);
        }
        while (true) {
            String inputString = sc.nextLine();
            if (isInt(inputString)) {
                int input = Integer.parseInt(inputString);
                if (input >= 0 && input <= max) {
                    return input;
                }
            }
            System.out.println("Enter a number between 1 and " + max + " or 0 to go back to the main menu");
        }
    }


}