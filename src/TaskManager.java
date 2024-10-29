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

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasksList.values());
    }

    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTaskList.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epicTaskList.values());
    }

    public ArrayList<SubTask> getAllSubTasksByEpic(Epic epic) {
        return new ArrayList<>(epic.getSubTasks());
    }

    public void deleteAllTasks() {
        tasksList.clear();
    }

    public void deleteAllSubTasks() {
        for (SubTask subTask : subTaskList.values()) {
            for (Epic epic : epicTaskList.values()) {
                if (epic.getId() == subTask.getEpicId()) {
                    epic.getSubTasks().remove(subTask);
                    checkEpicStatus(epic);
                }
            }
        }
        subTaskList.clear();
    }

    public void deleteAllEpics() {
        for (Epic epic : epicTaskList.values()) {
            for (SubTask subTask : epic.getSubTasks()) {
                subTaskList.values().remove(subTask);
            }
        }
        epicTaskList.clear();
    }

    public Task getTaskById(int id) {
        for (Task task : tasksList.values()) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException("Task with id" + id + " not found");
    }

    public SubTask getSubTaskById(int id) {
        for (SubTask subTask : subTaskList.values()) {
            if (subTask.getId() == id) {
                return subTask;
            }
        }
        throw new IllegalArgumentException("SubTask with id " + id + " not found");
    }

    public Epic getEpicById(int id) {
        for (Epic epic : epicTaskList.values()) {
            if (epic.getId() == id) {
                return epic;
            }
        }
        throw new IllegalArgumentException("Epic with id " + id + " not found");
    }

    public void createTask(Task task) {
        task.setId(getCounter());
        tasksList.put(task.getId(), task);
    }

    public void createSubTask(SubTask subTask) {
        subTask.setId(getCounter());
        subTaskList.put(subTask.getId(), subTask);
        epicTaskList.get(subTask.getEpicId()).getSubTasks().add(subTask);
        checkEpicStatus(epicTaskList.get(subTask.getEpicId()));
    }

    public void createEpic(Epic epic) {
        epic.setId(getCounter());
        checkEpicStatus(epic);
        epicTaskList.put(epic.getId(), epic);
    }

    public void updateTask(Task task) {
        tasksList.put(task.getId(), task);
    }

    public void updateSubTask(SubTask subTask) {
        subTaskList.put(subTask.getId(), subTask);
        epicTaskList.get(subTask.getEpicId()).getSubTasks().remove(subTask);
        epicTaskList.get(subTask.getEpicId()).getSubTasks().add(subTask);
        checkEpicStatus(epicTaskList.get(subTask.getEpicId()));
    }

    public void updateEpic(Epic epic) {
        for (SubTask epicSubTask : epic.getSubTasks()) {
                /* Здесь делается ставка на то, что пользователь будет вводить валидный эпик
                Сабтаски, которых нет в subTaskList, будут добавляться в subTaskList. Если сабтаска уже есть, то ничего
                не происходит. Как раз метод equals очень помогает в сравнении айдишников.
                 */
            subTaskList.putIfAbsent(epicSubTask.getId(), epicSubTask);
        }
        checkEpicStatus(epic);
        epicTaskList.put(epic.getId(), epic);
    }

    public void deleteTaskById(int id) {
        Task task = getTaskById(id);
        tasksList.values().remove(task);
    }

    public void deleteSubTaskById(int id) {
        SubTask subTask = getSubTaskById(id);
        epicTaskList.get(subTask.getEpicId()).getSubTasks().remove(subTask);
        checkEpicStatus(epicTaskList.get(subTask.getEpicId()));
        subTaskList.values().remove(subTask);
    }

    public void deleteEpicById(int id) {
        Epic epic = getEpicById(id);
        for (SubTask subTask : epic.getSubTasks()) {
            subTaskList.values().remove(subTask);
        }
        epicTaskList.values().remove(epic);
    }

    private static void checkEpicStatus(Epic epic) {
        if (epic.getSubTasks().isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (epic.getSubTasks().stream().allMatch(task -> TaskStatus.DONE.equals(task.getTaskStatus()))) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }

    private static int getCounter() {
        return ++counter;
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
