import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//CommandProcessor processes inputs by parsing them and calling the methods of the relevant inputs to handle the commands
public class CommandProcessor {
    private final TaskManager taskManager;
    private final WishManager wishManager;
    private final PointManager pointManager;
    private final Child child;

    /**
     * Constructors.
     * @param taskManager TaskManager for managing tasks.
     * @param wishManager WishManager for managing wishes.
     * @param pointManager PointManager for tracking points.
     * @param child Child instance.
     */
    public CommandProcessor(TaskManager taskManager, WishManager wishManager, PointManager pointManager, Child child) {
        this.taskManager = taskManager;
        this.wishManager = wishManager;
        this.pointManager = pointManager;
        this.child = child;
    }

    /**
     * Processes the given command line and performs the corresponding operations.
     * Catches error situations using a try-catch block and provides appropriate error messages.
     * @param commandLine The command line to be processed.
     */
    public void process(String commandLine) {
        List<String> tokens = parseCommand(commandLine);
        if (tokens.isEmpty()) {
            System.out.println("Skipped empty command.");
            return;
        }
        String command = tokens.get(0).toUpperCase();
        try {
            switch (command) {
                case "ADD_TASK1":
                    if (tokens.size() >= 8) {
                        char c = tokens.get(1).charAt(0);
                        int id = Integer.parseInt(tokens.get(2));
                        String title = tokens.get(3);
                        String description = tokens.get(4);
                        LocalDate deadline = LocalDate.parse(tokens.get(5), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalTime taskTime = LocalTime.parse(tokens.get(6), DateTimeFormatter.ofPattern("HH:mm"));
                        int taskPoints = Integer.parseInt(tokens.get(7));
                        String assignedBy = changeassignedBy(c,id);
                        taskManager.addTask(assignedBy, id, taskPoints, deadline, taskTime, title, description);
                    } else {
                        System.out.println("Missing parameter for ADD_TASK1 command!");
                    }
                    break;

                case "ADD_TASK2":
                    if (tokens.size() >= 10) {
                        char c = tokens.get(1).charAt(0);
                        int id = Integer.parseInt(tokens.get(2));
                        String title = tokens.get(3);
                        String description = tokens.get(4);
                        LocalDate startDate = LocalDate.parse(tokens.get(5), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalTime startTime = LocalTime.parse(tokens.get(6), DateTimeFormatter.ofPattern("HH:mm"));
                        LocalDate endDate = LocalDate.parse(tokens.get(7), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalTime endTime = LocalTime.parse(tokens.get(8), DateTimeFormatter.ofPattern("HH:mm"));
                        int taskPoints = Integer.parseInt(tokens.get(9));
                        String assignedBy = changeassignedBy(c,id);
                        taskManager.addTaskTimed(assignedBy, id, taskPoints, startDate, startTime, endDate, endTime, title, description);
                    } else {
                        System.out.println("Missing parameter for ADD_TASK2 command!");
                    }
                    break;

                case "LIST_ALL_TASKS":
                    if (tokens.size() >= 2) {
                        String filter = tokens.get(1);
                        if (filter.equalsIgnoreCase("D"))
                            taskManager.listAllTasksDaily();
                        else if (filter.equalsIgnoreCase("W"))
                            taskManager.listAllTasksWeekly();
                        else
                            taskManager.listAllTasks();
                    } else {
                        taskManager.listAllTasks();
                    }
                    break;

                case "TASK_DONE":
                    if (tokens.size() >= 2) {
                        int taskID = Integer.parseInt(tokens.get(1));
                        taskManager.taskDone(taskID);
                    } else {
                        System.out.println("Missing parameter for TASK_DONE command!!");
                    }
                    break;



                case "TASK_CHECKED":
                    if (tokens.size() >= 3) {
                        int taskID = Integer.parseInt(tokens.get(1));
                        int rating = Integer.parseInt(tokens.get(2));

                        if (rating < 0 || rating > 5) {
                            System.out.println("Error: Rating must be between 0 and 5.");
                            return;
                        }
                        taskManager.taskChecked(taskID, rating, child);
                    } else {
                        System.out.println("Missing parameter for TASK_CHECKED command!");
                    }
                    break;


                case "ADD_WISH1":
                    if (tokens.size() >= 4) {
                        String wishIDStr = tokens.get(1);
                        int wishID = Integer.parseInt(wishIDStr.substring(1));
                        String title = tokens.get(2);
                        String description = tokens.get(3);
                        Wish wish = new Wish(wishID, 0, title, description, null, State.PENDING);
                        wishManager.addWish(wish);
                    } else {
                        System.out.println("Missing parameter for ADD_WISH1 command!");
                    }
                    break;

                case "ADD_WISH2":
                    if (tokens.size() >= 8) {
                        String wishIDStr = tokens.get(1);
                        int wishID = Integer.parseInt(wishIDStr.substring(1));
                        String title = tokens.get(2);
                        String description = tokens.get(3);
                        LocalDate wishDate = LocalDate.parse(tokens.get(4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        Wish wish = new Wish(wishID, 0, title, description, wishDate, State.PENDING);
                        wishManager.addWish(wish);
                    } else {
                        System.out.println("Missing parameter for ADD_WISH2 command!");
                    }
                    break;

                case "WISH_CHECKED":
                    if (tokens.size() >= 3) {
                        String wishIDStr = tokens.get(1);
                        int wishID = Integer.parseInt(wishIDStr.substring(1));
                        boolean approved = tokens.get(2).equalsIgnoreCase("APPROVED");
                        int level = 0;
                        if (approved && tokens.size() >= 4) {
                            level = Integer.parseInt(tokens.get(3));
                        }
                        wishManager.wishChecked(wishID, approved, level);
                    } else {
                        System.out.println("Missing parameter for WISH_CHECKED command!");
                    }
                    break;

                case "LIST_ALL_WISHES":
                    wishManager.listAllWishes();
                    break;

                case "PRINT_STATUS":
                    pointManager.printStatus();
                    break;

                case "PRINT_BUDGET":
                    pointManager.printPoints();
                    break;

                case "ADD_BUDGET_COIN":
                    if (tokens.size() >= 2) {
                        int coins = Integer.parseInt(tokens.get(1));
                        pointManager.addPoints(coins);
                        PointManager.updateLevel(child);
                    } else {
                        System.out.println("Missing parameter for ADD_BUDGET_COIN command!");
                    }
                    break;


                case "HELP":
                    System.out.println("""
                    Available Commands:
                    - ADD_TASK1 [T/F] [id] [title] [desc] [yyyy-MM-dd] [HH:mm] [points]
                    - ADD_TASK2 [T/F] [id] [title] [desc] [startDate] [startTime] [endDate] [endTime] [points]
                    - LIST_ALL_TASKS [D/W(optional)]
                    - TASK_DONE [id]
                    - TASK_CHECKED [id] [rating 0-5]
                    - ADD_WISH1 [W{id}] [title] [desc]
                    - ADD_WISH2 [W{id}] [title] [desc] [yyyy-MM-dd]
                    - WISH_CHECKED [W{id}] [APPROVED/REJECTED] [level]
                    - LIST_ALL_WISHES
                    - PRINT_STATUS
                    - PRINT_BUDGET
                    - ADD_BUDGET_COIN [amount]
                    """);

                    break;

                default:
                    System.out.println("Unrecognized command: " + command);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error while processing the command: " + e.getMessage());
        }
    }

    /**
     * Parses the given line by treating quoted expressions as a single token.
     * @param line The input line
     * @return A list of parsed tokens
     */

    public static List<String> parseCommand(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuote = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuote = !inQuote;
                continue;
            }
            if (c == ' ' && !inQuote) {
                if (!sb.isEmpty()) {
                    tokens.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        if (!sb.isEmpty()) {
            tokens.add(sb.toString());
        }
        return tokens;
    }
    private String changeassignedBy(char c,int id) {
        if (c == 'T')
            return "Teacher";
        else if (c == 'F')
            return "Parent";
        else {
            System.out.println("Task assigner invalid for Task "+id+", Assigner set to unknown");
            return "Unknown";
        }
    }
}
