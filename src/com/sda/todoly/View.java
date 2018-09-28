

public class View {

    static void printWelcome() {
        System.out.println(">> Welcome to ToDoly \n"
                + ">> You have X tasks todo and Y tasks are done"); //TODO fix X and Y
    }

    static void printMainMenu() {
        System.out.println();
        System.out.println(">> Pick an option \n"
                + ">> (1) Show Task List (by date or project) \n"
                + ">> (2) Add New Task \n"
                + ">> (3) Edit Task (update, mark as done, remove \n"
                + ">> (4) Save and Quite \n"
                + ">> ");
    }

    static void printShowTasksMenu() {
        System.out.println(">> Pick an option \n"
                + ">> (1) Show Task List by date \n"
                + ">> (2) Show Task List by project \n"
                + ">> ");
    }

    static void printEditMenu() {
        System.out.println(">> Pick an option \n"
                + ">> (1) Update task \n"
                + ">> (2) Mark as done \n"
                + ">> (3) Remove task");
    }


}
