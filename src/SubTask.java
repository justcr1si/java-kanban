import java.util.Objects;

public class SubTask extends Task {
    private final int epicId;

    public SubTask(String name, String description, TaskStatus taskStatus, int epicId) {
        super(name, description, taskStatus);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + this.getId() +
                ", taskStatus=" + this.getTaskStatus() +
                ", epicId=" + this.getEpicId() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getDescription(), getTaskStatus(), epicId);
    }
}
