import java.time.LocalDate;


public class Task {

    private String title;
    private LocalDate dueDate;
    private String project;
    private boolean isDone;

    public Task(String title, LocalDate dueDate, String project, boolean isDone) {
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.isDone = isDone;
    }


    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getProject() {
        return project;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void updateTask(String title, LocalDate dueDate, String project, boolean isDone) {
        setTitle(title);
        setDueDate(dueDate);
        setProject(project);
        setDone(isDone);
    }

    public void MarkAsDone() {
        setDone(true);
    }



}
