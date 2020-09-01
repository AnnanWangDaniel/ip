public class Request {
    private String title;
    private String description;
    private boolean isDone;

    public Request(String title, String description) {
        this.title = title;
        this.description = description;
        this.isDone = false;
    }

    public Request(String title) {
        this.title = title;
        this.description = "";
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public void print() {
        System.out.print("[" + (isDone ? "*" : " ") + "] " + title);
    }
}