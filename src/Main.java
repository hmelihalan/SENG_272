import java.io.*;
import java.util.*;

public class Main {
    static File commandsFile = new File("src/textfl/Commands.txt");
    static File tasksFile = new File("src/textfl/Tasks.txt");

    public static void main(String[] args) {
        try {
            setupFiles();

            List<String> commands = readCommands();

            for (String command : commands) {
                processCommand(command);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setupFiles() throws IOException {
        if (!commandsFile.exists()) {
            commandsFile.getParentFile().mkdirs();
            commandsFile.createNewFile();
        }
        if (!tasksFile.exists()) {
            tasksFile.createNewFile();
        }
    }

    private static List<String> readCommands() throws IOException {
        List<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(commandsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    commands.add(line.trim());
                }
            }
        }
        return commands;
    }

    private static void processCommand(String command) throws IOException {
        if (command.startsWith("ADD_TASK")) {
            addTask(command);
        } else if (command.startsWith("LIST_ALL_TASKS")) {
            listAllTasks();
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    private static void addTask(String command) throws IOException {
        // Format: ADD_TASK1 T 101 "Math Homework" "Solve pages 10 to 20" 2025-03-01 15:00 10
        String[] parts = splitCommandArgs(command);
        if (parts.length < 8) {
            System.out.println("Invalid ADD_TASK format");
            return;
        }

        String assignedBy = parts[1];
        String taskId = parts[2];
        String title = parts[3];
        String description = parts[4];
        String date = parts[5];
        String time = parts[6];
        String points = parts[7];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tasksFile, true))) {
            writer.write(taskId + "," + assignedBy + "," + title + "," + description + "," + date + " " + time + "," + points);
            writer.newLine();
            System.out.println("Task " + taskId + " added.");
        }
    }

    private static void listAllTasks() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(tasksFile))) {
            String line;
            System.out.println("Tasks:");
            while ((line = reader.readLine()) != null) {
                System.out.println(" - " + line);
            }
        }
    }

    private static String[] splitCommandArgs(String command) {
        List<String> parts = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (char c : command.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
                continue;
            }
            if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    parts.add(current.toString());
                    current = new StringBuilder();
                }
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            parts.add(current.toString());
        }
        return parts.toArray(new String[0]);
    }
}