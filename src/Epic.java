import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    private final ArrayList<SubTask> subTasks;

    public Epic(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
        this.subTasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + this.getId() +
                ", taskStatus=" + this.getTaskStatus() +
                ", subTasks=" + this.getSubTasks() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getTaskStatus(), subTasks);
    }
}
