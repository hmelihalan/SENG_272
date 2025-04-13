import java.time.LocalDate;
import java.util.HashMap;


public class TaskManager {
    HashMap<Integer, Task> tasks=new HashMap<>();


    void ADD_TASK(char assgined, int id, int taskpoints, LocalDate deadline,String title,String description) {
        Task task = new Task(assgined,taskpoints,deadline,title,description);

        tasks.put(task.task_ID,task);
    }
    public void LIST_ALL_TASKS(){

        for(Task task : tasks.values()) {
         task.printTask();
        }
    }
    void TASK_DONE(int taskID) {

        Task task = tasks.get(taskID);
        task.completed = true;

    }
    void TASK_CHECKED(int taskID,int taskrating) {

        Task task = tasks.get(taskID);
        task.task_Status = true;
        task.task_Rating = taskrating;

    }

}

