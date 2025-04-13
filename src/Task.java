import java.time.LocalDate;

public class Task {
    int task_ID;
    int task_Point;
    int task_Rating;
    String task_Title;
    String task_Description;
    char assigned_By;
    char task_Type; //Indicates the task is daily or weekly
    boolean completed;
    boolean task_Status;
    LocalDate task_Date;



    public Task(char assigned_By, int task_Point,LocalDate deadline,String title,String description) {
        this.assigned_By = assigned_By;
        this.task_Point = task_Point;
        this.task_Title = title;
        this.task_Date = deadline;
        this.task_Description = description;

    }
    public void printTask() {
        System.out.println(assigned_By + "\t" + task_Point + "\t" + task_Title + "\t" + task_Description);
    }

}
