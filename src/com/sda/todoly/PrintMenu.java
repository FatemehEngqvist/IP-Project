package com.sda.todoly;

public class PrintMenu {

    static void welcome(int undoneTasks, int doneTasks) {
        System.out.println(">> Welcome to ToDoly \n"
                + ">> You have " + undoneTasks  + " tasks todo and " + doneTasks + " tasks are done");
    }

    static void mainMenu() {
        System.out.println();
        System.out.println(">> Pick an option \n"
                + ">> (1) Show Task List (by date or project) \n"
                + ">> (2) Add New Task \n"
                + ">> (3) Edit Task (update, mark as done, remove \n"
                + ">> (4) Save and Quite");
        System.out.print(">> ");
    }

    static void showTasksByDateOrProjectMenu() {
        System.out.println(">> Pick an option \n"
                + ">> (1) Show Task List by date \n"
                + ">> (2) Show Task List by project");
        System.out.print(">> ");
    }

    static void editTaskMenu() {
        System.out.println(">> Pick an option \n"
                + ">> (1) Update task \n"
                + ">> (2) Mark as done \n"
                + ">> (3) Remove task");
    }


}
