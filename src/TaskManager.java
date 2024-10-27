import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasksList;
    private final HashMap<Integer, SubTask> subTaskList;
    private final HashMap<Integer, Epic> epicTaskList;

    private static int counter = 0;

    public TaskManager() {
        this.tasksList = new HashMap<>();
        this.subTaskList = new HashMap<>();
        this.epicTaskList = new HashMap<>();
    }

    public HashMap<Integer, Task> getTasksHashMap() {
        return tasksList;
    }

    public HashMap<Integer, SubTask> getSubTasksHashMap() {
        return subTaskList;
    }

    public HashMap<Integer, Epic> getEpicTasksHashMap() {
        return epicTaskList;
    }

    public static int getCounter() {
        return ++counter;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasksList.values());
    }

    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTaskList.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epicTaskList.values());
    }

    public void deleteAllTasks() {
        tasksList.clear();
    }

    public void deleteAllSubTasks() {
        subTaskList.clear();
    }

    public void deleteAllEpics() {
        epicTaskList.clear();
    }

    public Task getTaskById(int id) {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasksList.values());
        allTasks.addAll(subTaskList.values());
        allTasks.addAll(epicTaskList.values());
        for (Task task : allTasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException("Task with " + id + " not found");
    }

    public ArrayList<Task> getAllTasksArrayList() {
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(tasksList.values());
        allTasks.addAll(subTaskList.values());
        allTasks.addAll(epicTaskList.values());
        return allTasks;
    }

    public void createOrUpdateTask(Task task) {
        if (task.getClass() == Task.class) {
            tasksList.put(task.getId(), task);
        } else if (task.getClass() == SubTask.class) {
            subTaskList.put(task.getId(), (SubTask) task);
        } else if (task.getClass() == Epic.class) {
            epicTaskList.put(task.getId(), (Epic) task);
        }
    }

    public void deleteTaskById(int id) {
        Task task = getTaskById(id);
        if (task.getClass() == Task.class) {
            tasksList.values().remove(task);
        } else if (task.getClass() == SubTask.class) {
            subTaskList.values().remove((SubTask) task);
        } else if (task.getClass() == Epic.class) {
            epicTaskList.values().remove((Epic) task);
        }
    }

    public ArrayList<SubTask> getAllSubTasksFromEpic(Epic epic) {
        return epic.getSubTasks();
    }

    public void setTaskStatus(TaskStatus taskStatus, Task task) {
        task.setTaskStatus(taskStatus);
    }

    public static void checkEpicStatus(Epic epic) {
        if (epic.getSubTasks().isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (epic.getSubTasks().stream().allMatch(task -> TaskStatus.DONE.equals(task.getTaskStatus()))) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasksList=" + tasksList +
                ", subTaskList=" + subTaskList +
                ", epicTaskList=" + epicTaskList +
                '}';
    }
}
