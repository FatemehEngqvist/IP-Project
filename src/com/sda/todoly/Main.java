import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.DateTimeException;

/**
 * This class is a part of "ToDoList" project.
 * This main reads user input and choose different operations based on user preference.
 *
 *
 * @author Fatemeh Engqvist
 */
public class Main {


    // sortedTasks.stream().forEach(task -> System.out.println(task));
    /**
     *
     * @return Task This returns the task object made of reading user input.
     */
     static Task createTaskMenu() {
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
                 System.out.println("Error with date format. The date format must be yyyy-mm-dd." );
             }
         }
         System.out.print(">> Project: ");
         String project = sc.nextLine();
         boolean isDone = false;
         Task task = new Task(title, dueDate, project, isDone);
         return task;
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
             System.out.println("There is no task.");
         }
         for(int i = 0; i < tasks.size(); i++) {
             System.out.println("Task " + (i + 1) + ":" + tasks.get(i));
         }
    }


    static void editTasks(ArrayList<Task> tasks) {
        View.printEditMenu();
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {

            /* Update task */
            case 1: // TODO choose from taskManager.tasks and update the desired task
                break;
            case 2: // TODO choose from taskManager.tasks and mark as done the desired task
                break;
            case 3: // TODO choose from taskManager.tasks and remove the desired task
                break;
        }
    }

    public static void main(String[] args) throws IOException {


        TaskReader taskReader = new TaskReader();
        ArrayList<Task> tasks = taskReader.loadTasks();

        TaskManager taskManager = new TaskManager(tasks);
        TaskWriter taskWriter = new TaskWriter(taskManager);

        View.printWelcome();

        boolean quiting = false;
        while(!quiting) {
            View.printMainMenu();
            Scanner sc = new Scanner(System.in);
            int option = Integer.parseInt(sc.nextLine());
            if (option > 0 && option < 5) {
                switch (option) {

                    // Use taskReader to show all the tasks saved on input file
                    case 1: {
                        View.printShowTasksMenu();
                        displayTasks(taskManager);

                        break;
                    }
                    // Add new Task
                    case 2: {
                        // TODO When you choose 2 and add a new task what will happen when you choose 1? Shall the new task be shown in the list?
                        taskManager.addTask(createTaskMenu());
                        taskWriter.writeToFile();
                        break;
                    }

                    // Edit task
                    case 3:
                        View.printEditMenu();

                        break;
                    case 4:
                        System.out.println("End of operation.");
                        quiting = true; //TODO save and quit
                }
            } else {
                System.out.println("Please choose an option between 1 to 4");
            }
        }
    }

}
