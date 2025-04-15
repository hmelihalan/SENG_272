import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    TaskCalendar calendar = new TaskCalendar();

    //Add tasks with only end date
    void addTask(String assignedBy, int id, int taskPoints, LocalDate deadline, LocalTime taskTime, String title, String description) {
        if (tasks.containsKey(id)) {
            System.out.println("Error: Task ID " + id + " already exists. Not added.");
            return;
        }
        Task task = new Task(id, assignedBy, taskPoints, deadline, taskTime, title, description);
        tasks.put(id, task);
        System.out.println("Task " + id + " added.");
    }

    // Add tasks with start and end date
    void addTaskTimed(String assignedBy, int id, int taskPoints, LocalDate startDate, LocalTime startTime,
                      LocalDate endDate, LocalTime endTime, String title, String description) {
        if (tasks.containsKey(id)) {
            System.out.println("Error: Task ID " + id + " already exists. Not added.");
            return;
        }

        if (startDate.isAfter(endDate)) {
            System.out.println("Error: Start date cannot be after end date. Task not added.");
            return;
        }
        if (startDate.equals(endDate) && startTime.isAfter(endTime)) {
            System.out.println("Error: For tasks on the same day, start time cannot be after end time. Task not added.");
            return;
        }
        Task task = new Task(id, assignedBy, taskPoints, startDate, startTime, endDate, endTime, title, description);
        tasks.put(id, task);
        calendar.addTask(task);
        System.out.println("Task " + id + " added.");
    }


    public void listAllTasks() {
        System.out.println("---- All Tasks ----");
        for (Task task : tasks.values()) {
            task.printTask();
        }
    }

    public void listAllTasksDaily() {
        System.out.println("---- Daily Tasks ----");
        LocalDate today = LocalDate.now();
        List<Task> tasksForDay = calendar.getTasksForDay(today);
        for (Task task : tasksForDay) {
            task.printTask();
        }
    }

    public void listAllTasksWeekly() {
        System.out.println("---- Weekly Tasks ----");
        LocalDate today = LocalDate.now();
        List<Task> tasksForWeek = calendar.getTasksForWeek(today);
        for (Task task : tasksForWeek) {
            task.printTask();
        }
    }

    // Marks as task done
    void taskDone(int taskID) {
        Task task = tasks.get(taskID);
        if (task != null) {
            if (task.state == State.COMPLETED) {
                System.out.println("Task " + taskID + " is already marked as COMPLETED.");
                return;
            }
            task.state = State.COMPLETED;
            System.out.println("Task " + taskID + " marked as COMPLETED");
        } else {
            System.out.println("Error: Task " + taskID + " not found.");
        }
    }




    //Checks task
    void taskChecked(int taskID, int taskRating, Child c) {
        Task task = tasks.get(taskID);
        if (task != null && task.state == State.COMPLETED) {
            task.state = State.APPROVED;
            task.task_Rating = taskRating;
            c.points += task.task_Point + taskRating;
            c.approvedTaskCount++;
            c.averagePnts = c.points / c.approvedTaskCount;
            PointManager.updateLevel(c);
            System.out.println("Task " + taskID + " approved with rating: " + taskRating);
        } else {
            System.out.println("Error: Task " + taskID + " either not found or not marked COMPLETED.");
        }
    }


    //Exports all tasks to tasks.txt
    public List<String> exportTasks() {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks.values()) {
            String line;
            if (task.isActivityTask) {
                line = task.task_ID + "," + task.assigned_By + "," +
                        "\"" + task.task_Title + "\"," +
                        "\"" + task.task_Description + "\"," +
                        (task.startDate != null ? task.startDate : "") + "," +
                        (task.startTime != null ? task.startTime : "") + "," +
                        (task.endDate != null ? task.endDate : "") + "," +
                        (task.endTime != null ? task.endTime : "") + "," +
                        task.task_Point + "," +
                        task.state + "," +
                        task.task_Rating;
            } else {
                String dateTimeStr = (task.task_Date != null ? task.task_Date.toString() : "") + "," +
                        (task.task_Time != null ? task.task_Time.toString() : "");
                line = task.task_ID + "," + task.assigned_By + "," +
                        "\"" + task.task_Title + "\"," +
                        "\"" + task.task_Description + "\"," +
                        dateTimeStr + "," +
                        task.task_Point + "," +
                        task.state + "," +
                        task.task_Rating;
            }
            lines.add(line);
        }
        return lines;
    }


}
