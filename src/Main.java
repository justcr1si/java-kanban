public class Main {
    public static void main(String[] args) {
        Epic epic = new Epic("Проверка", "Проверка", TaskStatus.IN_PROGRESS);
        TaskManager taskManager = new TaskManager();
        epic.addSubTask(new SubTask("Проверка", "Проверка", TaskStatus.DONE, epic.getId()));
        epic.addSubTask(new SubTask("Проверка", "Проверка", TaskStatus.DONE, epic.getId()));
        taskManager.createOrUpdateTask(epic);
        taskManager.createOrUpdateTask(epic.getSubTasks().get(0));
        taskManager.createOrUpdateTask(epic.getSubTasks().get(1));
        System.out.println(taskManager.getAllTasksArrayList());
        taskManager.deleteTaskById(2);
        System.out.println(((Epic) taskManager.getTaskById(1)).getSubTasks());
    }
}
