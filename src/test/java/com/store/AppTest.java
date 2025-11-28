package com.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Модульные тесты для системы управления задачами
 */
class AppTest {
    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
        app.clearAllTasks();
    }

    @Test
    void testAddTask() {
        Task task = app.addTask("Learn Java");

        assertNotNull(task);
        assertEquals("Learn Java", task.getDescription());
        assertEquals(1, task.getId());
        assertFalse(task.isCompleted());
    }

    @Test
    void testCompleteTask() {
        app.addTask("Learn Java");
        boolean result = app.completeTask(1);

        assertTrue(result);

        List<Task> tasks = app.getAllTasks();
        assertTrue(tasks.get(0).isCompleted());
    }

    @Test
    void testCompleteNonExistentTask() {
        boolean result = app.completeTask(999);

        assertFalse(result);
    }

    @Test
    void testDeleteTask() {
        app.addTask("Learn Java");
        boolean result = app.deleteTask(1);

        assertTrue(result);
        assertTrue(app.getAllTasks().isEmpty());
    }

    @Test
    void testGetAllTasks() {
        app.addTask("Task 1");
        app.addTask("Task 2");

        List<Task> tasks = app.getAllTasks();

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getDescription());
        assertEquals("Task 2", tasks.get(1).getDescription());
    }

    @Test
    void testTaskEquality() {
        Task task1 = new Task(1, "Same task");
        Task task2 = new Task(1, "Same task");
        Task task3 = new Task(2, "Different task");

        assertEquals(task1, task2);
        assertNotEquals(task1, task3);
    }

    @Test
    void testTaskToString() {
        Task task = new Task(1, "Test task");
        String expectedTodo = "#1 [TODO] Test task";
        assertEquals(expectedTodo, task.toString());

        task.setCompleted(true);
        String expectedDone = "#1 [DONE] Test task";
        assertEquals(expectedDone, task.toString());
    }

    @Test
    void testClearCommand() {
        app.addTask("Test task");
        app.clearAllTasks();

        assertTrue(app.getAllTasks().isEmpty());
    }
}
