package com.sda.todolist.model;

import org.junit.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class is a part of "ToDoList" project.
 * This TaskTest class tests methods in Task class.
 *
 * @author Fatemeh Engqvist
 */
class TaskTest {

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {

    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {

    }

    @Test
    /**
     * Test that a task item is correctly initialised (title, due date, project, status and id).
     */
    public void testInit() {
        Task task = new Task("Do the dishes", "Home", LocalDate.parse("2018-10-12"), false, 1);
        assertEquals("Do the dishes", task.getTitle());
        assertEquals(LocalDate.parse("2018-10-12"), task.getDueDate());
        assertEquals("Home", task.getProject());
        assertFalse(task.getStatus());

    }

}