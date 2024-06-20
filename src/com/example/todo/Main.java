package com.example.todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getUserChoice();
            handleChoice(choice);
        }
    }

    private static void printMenu() {
        System.out.println("\nTo-Do List Menu:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Показать все задачи");
        System.out.println("4. Редактировать задачу");
        System.out.println("5. Сохранить задачи");
        System.out.println("6. Загрузить задачи");
        System.out.println("7. Выход");
    }

    private static int getUserChoice() {
        System.out.print("Выберите пункт меню: ");
        return scanner.nextInt();
    }

    private static void handleChoice(int choice) {
        scanner.nextLine(); // Consume newline left-over
        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                deleteTask();
                break;
            case 3:
                showTasks();
                break;
            case 4:
                editTask();
                break;
            case 5:
                saveTasks();
                break;
            case 6:
                loadTasks();
                break;
            case 7:
                System.out.println("Выход из программы...");
                System.exit(0);
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private static void addTask() {
        System.out.print("Введите описание задачи: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Задача добавлена.");
    }

    private static void deleteTask() {
        showTasks();
        System.out.print("Введите номер задачи для удаления: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("Задача удалена.");
        } else {
            System.out.println("Неверный номер задачи.");
        }
    }

    private static void showTasks() {
        System.out.println("\nСписок задач:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void editTask() {
        showTasks();
        System.out.print("Введите номер задачи для редактирования: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            System.out.print("Введите новое описание задачи: ");
            String newTask = scanner.nextLine();
            tasks.set(taskNumber - 1, newTask);
            System.out.println("Задача обновлена.");
        } else {
            System.out.println("Неверный номер задачи.");
        }
    }

    private static void saveTasks() {
        System.out.print("Введите имя файла для сохранения задач: ");
        String fileName = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("Задачи сохранены в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        System.out.print("Введите имя файла для загрузки задач: ");
        String fileName = scanner.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            tasks.clear();
            String task;
            while ((task = reader.readLine()) != null) {
                tasks.add(task);
            }
            System.out.println("Задачи загружены из файла " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }
    }
}
