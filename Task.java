import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Task implements Comparable<Task> {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String priority;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        try {
            this.startTime = LocalTime.parse(startTime);
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format.");
        }
        this.priority = priority;
        if (this.startTime.isAfter(this.endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean overlapsWith(Task other) {
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }

    @Override
    public int compareTo(Task other) {
        return this.startTime.compareTo(other.startTime);
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s [%s]", startTime, endTime, description, priority);
    }
}
