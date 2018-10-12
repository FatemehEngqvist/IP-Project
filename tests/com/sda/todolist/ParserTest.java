package com.sda.todolist;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static com.sda.todolist.Parser.isInt;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

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
     * Test that the method isInt checks correctly if a string is integer or not.
     */
    void isIntTest() {
        assertTrue(isInt("0"));
        assertTrue(isInt("5"));
        assertTrue(isInt("-1"));
        assertTrue(isInt("1000"));
        assertFalse(isInt(""));
        assertFalse(isInt("int"));
        assertFalse(isInt("d"));
        assertFalse(isInt("0 d"));
        assertFalse(isInt("hHllo"));
        assertFalse(isInt("Hello 123"));
        assertFalse(isInt("Hello World"));
    }

}