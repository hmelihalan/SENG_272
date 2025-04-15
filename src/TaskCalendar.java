import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskCalendar {
    private List<Task> activityTasks = new ArrayList<>();

    // Adding tasks with only start and end date
    public void addTask(Task task) {
        if (task.isActivityTask) {
            activityTasks.add(task);
        }
    }

    // Adding daily tasks
    public List<Task> getTasksForDay(LocalDate day) {
        List<Task> tasksForDay = new ArrayList<>();
        for (Task task : activityTasks) {
            // If the task happens in the same day add to daily tasks
            if (task.startDate.equals(day)
                    || (day.isAfter(task.startDate) && day.isBefore(task.endDate))
                    || day.equals(task.endDate)) {
                tasksForDay.add(task);
            }
        }
        return tasksForDay;
    }

    // Adding weekly tasks
    public List<Task> getTasksForWeek(LocalDate startDay) {
        LocalDate endOfWeek = startDay.plusDays(6);
        List<Task> tasksForWeek = new ArrayList<>();
        for (Task task : activityTasks) {
            // If the task is weekly add to weekly tasks
            if (!(task.endDate.isBefore(startDay) || task.startDate.isAfter(endOfWeek))) {
                tasksForWeek.add(task);
            }
        }
        return tasksForWeek;
    }
}
