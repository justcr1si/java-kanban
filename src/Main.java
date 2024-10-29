public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Epic epic = new Epic("Ужин", "Приготовить ужин", TaskStatus.NEW);
        taskManager.createEpic(epic);
        SubTask subTask = new SubTask("Рыба", "Взять рыбу", TaskStatus.IN_PROGRESS, epic.getId());
        taskManager.createSubTask(subTask);
        Epic epic1 = new Epic("Новый эпик", "Эпик", TaskStatus.NEW);
        epic1.setId(1);
        taskManager.updateEpic(epic1);
        taskManager.updateSubTask(subTask);
        SubTask subTask1 = new SubTask("Тест", "Тест", TaskStatus.DONE, epic1.getId());
        taskManager.createSubTask(subTask1);
//        taskManager.deleteAllEpics();
        System.out.println(taskManager.getAllSubTasks());
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubTasksByEpic(epic1));
    }
}
