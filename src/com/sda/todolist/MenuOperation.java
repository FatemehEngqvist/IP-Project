package com.sda.todolist;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuOperation {

    static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    static void displayWelcome(int undoneTasks, int doneTasks) {
        System.out.println();
        System.out.println("Welcome to ToDoList");
        System.out.println("You have " + undoneTasks + " tasks todo and " + doneTasks + " tasks are done!");
    }


    // Parser
    public static String getString(Scanner sc, String string) {
        while (true) {
            System.out.print("Enter the " + string + ": ");
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
        }
    }

    // Parser
    public static LocalDate getDate(Scanner sc) {
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

    //Parser
    public static boolean getStatus(Scanner sc) {
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


    static ArrayList<String> taskString(Scanner sc) {

        ArrayList<String> updatedTask = new ArrayList<>();

        String title = getString(sc, "title");
        String project = getString(sc, "project");
        LocalDate dueDate = getDate(sc);
        boolean status = getStatus(sc);

        updatedTask.add(title);
        updatedTask.add(dueDate.toString());
        updatedTask.add(project);
        updatedTask.add(String.valueOf(status));

        return updatedTask;
    }


    // Parser returns what user want to do
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