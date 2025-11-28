package com.store;
// TEST: This is a test comment
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Простая система управления задачами (To-Do List)
 * ИНТЕРАКТИВНАЯ ВЕРСИЯ
 */
public class App {
    private List<Task> tasks = new ArrayList<>();
    private int taskCounter = 1;

    public static void main(String[] args) {
        App app = new App();

        if (args.length == 0) {
            // Интерактивный режим
            app.runInteractiveMode();
        } else {
            // Режим командной строки (для тестов)
            app.executeCommand(args);
        }
    }

    /**
     * Интерактивный режим
     */
    private void runInteractiveMode() {
        initializeSampleTasks();
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎯 To-Do List Manager (Interactive Mode)");
        System.out.println("Type 'help' for commands, 'exit' to quit");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();
            String argument = parts.length > 1 ? parts[1] : "";

            executeInteractiveCommand(command, argument);
        }

        scanner.close();
        System.out.println("👋 Goodbye!");
    }

    /**
     * Выполнение команды в интерактивном режиме
     */
    private void executeInteractiveCommand(String command, String argument) {
        switch (command) {
            case "add":
                if (!argument.isEmpty()) {
                    addTask(argument);
                } else {
                    System.out.println("Error: Please provide task description");
                }
                break;

            case "complete":
                if (!argument.isEmpty()) {
                    try {
                        completeTask(Integer.parseInt(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please provide valid task ID");
                    }
                } else {
                    System.out.println("Error: Please provide task ID");
                }
                break;

            case "delete":
                if (!argument.isEmpty()) {
                    try {
                        deleteTask(Integer.parseInt(argument));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please provide valid task ID");
                    }
                } else {
                    System.out.println("Error: Please provide task ID");
                }
                break;

            case "list":
                listTasks();
                break;

            case "stats":
                showStats();
                break;

            case "clear":
                clearAllTasks();
                System.out.println("🗑️ All tasks cleared!");
                break;

            case "reset":
                clearAllTasks();
                initializeSampleTasks();
                System.out.println("🔄 Tasks reset to initial state!");
                break;

            case "help":
                showHelp();
                break;

            default:
                System.out.println("Unknown command: '" + command + "'");
                System.out.println("Type 'help' for available commands");
        }
    }

    /**
     * Режим командной строки (для тестов и CI/CD)
     */
    private void executeCommand(String[] args) {
        initializeSampleTasks();
        String command = args[0].toLowerCase();

        switch (command) {
            case "add":
                if (args.length >= 2) {
                    addTask(args[1]);
                } else {
                    System.out.println("Error: Please provide task description");
                }
                break;

            case "complete":
                if (args.length >= 2) {
                    try {
                        completeTask(Integer.parseInt(args[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please provide valid task ID");
                    }
                } else {
                    System.out.println("Error: Please provide task ID");
                }
                break;

            case "list":
                listTasks();
                break;

            case "delete":
                if (args.length >= 2) {
                    try {
                        deleteTask(Integer.parseInt(args[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please provide valid task ID");
                    }
                } else {
                    System.out.println("Error: Please provide task ID");
                }
                break;

            case "stats":
                showStats();
                break;

            case "clear":
                clearAllTasks();
                System.out.println("🗑️ All tasks cleared!");
                break;

            case "reset":
                clearAllTasks();
                initializeSampleTasks();
                System.out.println("🔄 Tasks reset to initial state!");
                break;

            default:
                System.out.println("Unknown command: " + command);
                showHelp();
        }
    }

    /**
     * Инициализация тестовых задач
     */
    private void initializeSampleTasks() {
        if (tasks.isEmpty()) {
            addTaskWithoutMessage("Learn Java programming");
            addTaskWithoutMessage("Build CI/CD pipeline with Jenkins");
            addTaskWithoutMessage("Write unit tests");

            // Первую задачу помечаем как выполненную для демонстрации
            Task firstTask = findTask(1);
            if (firstTask != null) {
                firstTask.setCompleted(true);
            }
        }
    }

    /**
     * Добавить новую задачу (без сообщения - для инициализации)
     */
    private void addTaskWithoutMessage(String description) {
        Task task = new Task(taskCounter++, description);
        tasks.add(task);
    }

    /**
     * Добавить новую задачу
     */
    public Task addTask(String description) {
        Task task = new Task(taskCounter++, description);
        tasks.add(task);
        System.out.println("✅ Added task: " + task);
        return task;
    }

    /**
     * Отметить задачу как выполненную
     */
    public boolean completeTask(int taskId) {
        Task task = findTask(taskId);
        if (task != null && !task.isCompleted()) {
            task.setCompleted(true);
            System.out.println("🎉 Completed task: " + task);
            return true;
        } else if (task != null) {
            System.out.println("ℹ️ Task already completed: " + task);
            return false;
        } else {
            System.out.println("❌ Task not found with ID: " + taskId);
            return false;
        }
    }

    /**
     * Удалить задачу
     */
    public boolean deleteTask(int taskId) {
        Task task = findTask(taskId);
        if (task != null) {
            tasks.remove(task);
            System.out.println("🗑️ Deleted task: " + task);
            return true;
        } else {
            System.out.println("❌ Task not found with ID: " + taskId);
            return false;
        }
    }

    /**
     * Показать все задачи
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found. Add some tasks!");
            return;
        }

        System.out.println("📋 Your Tasks:");
        for (Task task : tasks) {
            String status = task.isCompleted() ? "✅" : "⏳";
            System.out.println(status + " " + task);
        }
    }

    /**
     * Показать статистику
     */
    public void showStats() {
        int total = tasks.size();
        int completed = (int) tasks.stream().filter(Task::isCompleted).count();
        int pending = total - completed;

        System.out.println("📊 Task Statistics:");
        System.out.println("Total tasks: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);

        if (total > 0) {
            double progress = (double) completed / total * 100;
            System.out.println("Progress: " + String.format("%.1f", progress) + "%");
        }
    }

    /**
     * Найти задачу по ID
     */
    private Task findTask(int taskId) {
        return tasks.stream()
                .filter(task -> task.getId() == taskId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Показать справку по командам
     */
    private void showHelp() {
        System.out.println("🎯 Available Commands:");
        System.out.println("  add <description>    - Add new task");
        System.out.println("  complete <id>        - Mark task as completed");
        System.out.println("  delete <id>          - Delete task");
        System.out.println("  list                 - Show all tasks");
        System.out.println("  stats                - Show statistics");
        System.out.println("  clear                - Clear all tasks");
        System.out.println("  reset                - Reset to initial tasks");
        System.out.println("  help                 - Show this help");
        System.out.println("  exit                 - Exit the application");
    }

    /**
     * Получить все задачи (для тестов)
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Очистить все задачи
     */
    public void clearAllTasks() {
        tasks.clear();
        taskCounter = 1;
    }

    public static String getVersion() {
        return "1.0.0-PR-TEST";
    }
}