import java.io.File;
import java.util.List;

public class Main {
    static File commandsFile = new File("src/textfl/Commands.txt");
    static File tasksFile = new File("src/textfl/Tasks.txt");
    static File wishesFile = new File("src/textfl/Wishes.txt");

    public static void main(String[] args) {
        File folder = new File("src/textfl");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        TaskManager taskManager = new TaskManager();
        WishManager wishManager = new WishManager();
        Child child = new Child();
        PointManager pointManager = new PointManager(child);

        CommandProcessor commandProcessor = new CommandProcessor(taskManager, wishManager, pointManager, child);

        List<String> commands = FileManager.readFile(commandsFile.getPath());

        for (String commandLine : commands) {
            commandProcessor.process(commandLine);
        }

        List<String> taskLines = taskManager.exportTasks();
        if (taskLines != null)
            FileManager.writeFile(tasksFile.getPath(), taskLines);

        List<String> wishLines = wishManager.exportWishes();
        if (wishLines != null)
            FileManager.writeFile(wishesFile.getPath(), wishLines);
    }

}
