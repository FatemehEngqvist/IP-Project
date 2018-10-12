package com.sda.todolist.model;

import org.junit.jupiter.api.*;
import com.sda.todolist.filehandler.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is a part of "ToDoList" project.
 * This TaskManagerTest class tests all methods in TaskManager class.
 *
 * @author Fatemeh Engqvist
 */

class TaskManagerTest {

    private TaskManager emptyTaskManager;
    private TaskManager nonEmptyTaskManager;


    @BeforeEach
    void setUp() {
        TaskReader tr = new FileTaskReader();
        TaskWriter tw = new FileTaskWriter();
        emptyTaskManager = new TaskManager(tr, tw);
        nonEmptyTaskManager = new TaskManager(tr, tw);

        nonEmptyTaskManager.addTask("Wash",  "Home", LocalDate.parse("2020-10-10"), false);
        nonEmptyTaskManager.addTask("Clean",  "Home", LocalDate.parse("2018-08-08"), false);
        nonEmptyTaskManager.addTask("Run",  "Me", LocalDate.parse("2019-09-09"), false);
        nonEmptyTaskManager.addTask("Sleep", "Me",  LocalDate.parse("2017-07-07"), false);

    }

    @AfterEach
    void tearDown() {
        //System.out.close();
        //System.setOut(originalOut);
    }

    /**
     * Test that a task is correctly added to the empty task manager.
     */
    @Test
    void testAddOneTaskToEmpty() {
        String title = "title1";
        LocalDate dueDate = LocalDate.parse("2018-12-12");
        String project = "project1";
        boolean status = false;

        System.out.println(status);

        emptyTaskManager.addTask(title, project, dueDate, status);
        assertTrue(emptyTaskManager.idExist(1));
        assertEquals(getTaskString(title, dueDate, project, status, 1), emptyTaskManager.getTaskString(1));
    }

    /**
     * Test that two tasks are correctly added to the empty task manager.
     */
    @Test
    void testAddTwoTasksToEmpty() {

        String title = "title1";
        LocalDate dueDate = LocalDate.parse("2018-12-12");
        String project = "project1";
        boolean status = false;

        emptyTaskManager.addTask(title, project, dueDate, status);
        assertTrue(emptyTaskManager.idExist(1));
        assertEquals(getTaskString(title, dueDate, project, status, 1), emptyTaskManager.getTaskString(1));

        String title2 = "title2";
        LocalDate dueDate2 = LocalDate.parse("2018-12-13");
        String project2 = "project2";
        boolean status2 = true;

        emptyTaskManager.addTask(title2, project2, dueDate2, status2);
        assertTrue(emptyTaskManager.idExist(2));
        assertEquals(getTaskString(title2, dueDate2, project2, status2, 2), emptyTaskManager.getTaskString(2));
    }


    /**
     * Test that one task is coorectly added to a non-empty task manager.
     */
    @Test
    void testAddOneTaskToNonEmpty() {
        nonEmptyTaskManager.addTask("Wash", "Home", LocalDate.parse("2011-11-11"), true);

        String title = "title1";
        LocalDate dueDate = LocalDate.parse("2018-12-12");
        String project = "project1";
        boolean status = false;

        nonEmptyTaskManager.addTask(title, project, dueDate, status);
        assertTrue(nonEmptyTaskManager.idExist(2));
        assertEquals(getTaskString(title, dueDate, project, status, 6), nonEmptyTaskManager.getTaskString(6));
    }

    /**
     * Test that two tasks are correctly added to a non-empty task manager.
     */
    @Test
    void testAddTwoTasksToNonEmpty() {
        String title = "Water flowers";
        LocalDate dueDate = LocalDate.parse("2018-12-12");
        String project = "Home";
        boolean status = false;

        nonEmptyTaskManager.addTask(title, project, dueDate, status);
        assertTrue(nonEmptyTaskManager.idExist(2));
        assertEquals(getTaskString(title, dueDate, project, status, 5), nonEmptyTaskManager.getTaskString(5));
    }

    /**
     * Test that id s in an empty task manager are valid.
     */
    @Test
    void testIdExistEmptyList() {
        assertFalse(emptyTaskManager.idExist(1));
        assertFalse(emptyTaskManager.idExist(2));
        assertFalse(emptyTaskManager.idExist(3));
        assertFalse(emptyTaskManager.idExist(4));
        assertFalse(emptyTaskManager.idExist(5));
        assertFalse(emptyTaskManager.idExist(-1));
        assertFalse(emptyTaskManager.idExist(0));
    }

    /**
     * Test that id s in a non-empty task manager are valid.
     */
    @Test
    void testIdExistNonEmptyList() {
        assertTrue(nonEmptyTaskManager.idExist(1));
        assertTrue(nonEmptyTaskManager.idExist(2));
        assertTrue(nonEmptyTaskManager.idExist(3));
        assertTrue(nonEmptyTaskManager.idExist(4));

        assertFalse(nonEmptyTaskManager.idExist(5));
        assertFalse(nonEmptyTaskManager.idExist(-1));
        assertFalse(nonEmptyTaskManager.idExist(0));
    }


    /**
     * Returns a task in string format.
     *
     * @param title   task title
     * @param dueDate task due date
     * @param project task project
     * @param status  task
     * @param id
     * @return task in string format
     */
    private String getTaskString(String title, LocalDate dueDate, String project, boolean status, int id) {
        String statusString = "task is Not Done";
        if (status) {
            statusString = "task is Done";
        }
        return "Task id: " + id + "  Title: " + title + "  Due date: " +
                dueDate + "  Project: " + project + "  Status: " + statusString;
    }

    /**
     * Test that it is impossible to remove a task from an empty task manager.
     */
    @Test
    void testRemoveTaskByIdEmpty() {
        assertFalse(emptyTaskManager.removeTaskById(0));

        assertFalse(emptyTaskManager.removeTaskById(1));
        assertFalse(emptyTaskManager.removeTaskById(2));
        assertFalse(emptyTaskManager.removeTaskById(3));
        assertFalse(emptyTaskManager.removeTaskById(4));

        assertFalse(emptyTaskManager.removeTaskById(5));
        assertFalse(emptyTaskManager.removeTaskById(100));
        assertFalse(emptyTaskManager.removeTaskById(-1));
    }

    /**
     * Test that it is possible to remove a task from a non-empty task manager.
     */
    @Test
    void testRemoveTaskByIdNonEmpty() {

        assertTrue(nonEmptyTaskManager.removeTaskById(1));
        assertTrue(nonEmptyTaskManager.removeTaskById(2));
        assertTrue(nonEmptyTaskManager.removeTaskById(3));
        assertTrue(nonEmptyTaskManager.removeTaskById(4));

        // The task manager should be empty now.
        assertEquals(nonEmptyTaskManager.getTasks().size(), 0);
        assertFalse(nonEmptyTaskManager.removeTaskById(1));
        assertFalse(nonEmptyTaskManager.idExist(1));


        assertFalse(nonEmptyTaskManager.removeTaskById(-1));
        assertFalse(nonEmptyTaskManager.removeTaskById(0));
        assertFalse(nonEmptyTaskManager.removeTaskById(15));

    }

    /**
     * Test that the task is updated correctly.
     */
    @Test
    void testUpdateTask() {
        ArrayList<String> updatedTask= new ArrayList<>();
        updatedTask.add("updatedTitle");
        updatedTask.add("updatedProject");
        updatedTask.add("2018-10-18");
        updatedTask.add("false");

        // id = 0 which is index 0 in the list
        nonEmptyTaskManager.updateTask(1, updatedTask);

        assertEquals("updatedTitle", nonEmptyTaskManager.getTasks().get(0).getTitle());
        assertEquals("updatedProject", nonEmptyTaskManager.getTasks().get(0).getProject());
        assertEquals(LocalDate.parse("2018-10-18"), nonEmptyTaskManager.getTasks().get(0).getDueDate());
        assertEquals(Boolean.valueOf("false"), nonEmptyTaskManager.getTasks().get(0).getStatus());

        assertFalse(nonEmptyTaskManager.updateTask(5, updatedTask));

    }

    /**
     * Test that the task is marked as done correctly.
     */
    @Test
    void testMarkAsDone() {
        // index 3 is id 4
        assertFalse(nonEmptyTaskManager.getTasks().get(3).getStatus());
        nonEmptyTaskManager.markAsDone(4);
        assertTrue(nonEmptyTaskManager.getTasks().get(3).getStatus());
    }

    /**
     * Test that task manager's task list is sorted by date correctly.
     */
    @Test
    void testSortByDate() {
        List<Task> nonSortedTasks = nonEmptyTaskManager.getTasks();
        assertFalse(nonSortedTasks.get(0).getDueDate().isBefore(nonSortedTasks.get(1).getDueDate()));
        assertFalse(nonSortedTasks.get(2).getDueDate().isBefore(nonSortedTasks.get(3).getDueDate()));

        List<Task> sortedByDateTasks = nonEmptyTaskManager.sortByDate();
        assertTrue(sortedByDateTasks.get(0).getDueDate().isBefore(nonEmptyTaskManager.getTasks().get(1).getDueDate()));
        assertTrue(sortedByDateTasks.get(1).getDueDate().isBefore(nonEmptyTaskManager.getTasks().get(2).getDueDate()));
    }

    /**
     * Test that the task list is filtered by project correctly.
     */
    @Test
    void testFilterByProject() {
        List<Task> nonFilteredTaskList= nonEmptyTaskManager.getTasks();
        assertEquals(4, nonFilteredTaskList.size());

        List<Task> filteredTaskList= nonEmptyTaskManager.filterByProject("Me");
        assertEquals(2, filteredTaskList.size());
        assertEquals("Me", filteredTaskList.get(0).getProject());
        assertEquals("Me", filteredTaskList.get(1).getProject());
    }

    /**
     * Test the correct max id.
     */
    @Test
    void testMaxId() {
        assertEquals(0, emptyTaskManager.getMaxId());
        assertEquals(4, nonEmptyTaskManager.getMaxId());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2018-12-12"), false);
        assertEquals(5, nonEmptyTaskManager.getMaxId());
    }

    /**
     * Test that number of done tasks are correct.
     */
    @Test
    void testNoOfDoneTasks() {
        assertEquals(0, nonEmptyTaskManager.noOfDoneTasks());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2018-12-12"), true);
        assertEquals(1, nonEmptyTaskManager.noOfDoneTasks());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2018-12-12"), false);
        assertEquals(1, nonEmptyTaskManager.noOfDoneTasks());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2019-12-12"), true);
        assertEquals(2, nonEmptyTaskManager.noOfDoneTasks());
    }


    /**
     * Test that number of NOT done tasks are correct.
     */
    @Test
    void testNoOfNotDoneTasks() {
        assertEquals(4, nonEmptyTaskManager.noOfNotDoneTasks());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2018-12-12"), false);
        assertEquals(5, nonEmptyTaskManager.noOfNotDoneTasks());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2018-12-12"), true);
        assertEquals(5, nonEmptyTaskManager.noOfNotDoneTasks());
        nonEmptyTaskManager.addTask("newT", "newP", LocalDate.parse("2019-12-12"), false);
        assertEquals(6, nonEmptyTaskManager.noOfNotDoneTasks());
    }





}