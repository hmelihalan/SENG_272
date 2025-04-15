import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    int task_ID;
    int task_Point;
    int task_Rating;
    String task_Title;
    String task_Description;
    String assigned_By;
    LocalDate task_Date; // Task date for only deadline
    LocalTime task_Time;
    State state;

    // Task dates for daily and weekly tasks
    boolean isActivityTask;
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;

    // Constructor: with deadline (ADD_TASK1)
    public Task(int task_ID, String assigned_By, int task_Point, LocalDate deadline, LocalTime taskTime, String title, String description) {
        this.task_ID = task_ID;
        this.assigned_By = assigned_By;
        this.task_Point = task_Point;
        this.task_Title = title;
        this.task_Date = deadline;
        this.task_Time = taskTime;
        this.task_Description = description;
        this.state = State.PENDING;
        this.isActivityTask = false;
    }





    // Constructor: with time span
    public Task(int task_ID, String assigned_By, int task_Point, LocalDate startDate, LocalTime startTime,
                LocalDate endDate, LocalTime endTime, String title, String description) {
        this.task_ID = task_ID;
        this.assigned_By = assigned_By;
        this.task_Point = task_Point;
        this.task_Title = title;
        this.task_Description = description;
        this.state = State.PENDING;
        this.isActivityTask = true;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }


    public void printTask() {
        System.out.print("Task ID: " + task_ID +
                " | Assigned By: " + assigned_By +
                " | Title: " + task_Title +
                " | Description: " + task_Description);
        if (isActivityTask) {
            System.out.print(" | Activity: " + startDate + " " + startTime + " to " + endDate + " " + endTime);
        } else {
            System.out.print(" | Deadline: " + task_Date + " " + task_Time);
        }
        System.out.println(" | Points: " + task_Point +
                " | State: " + state +
                " | Rating: " + task_Rating);
    }


}
