/**
 * Task Class
 * parent Class of all kinds of tasks
 */

package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Class.
     * @param description content of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    // add tick and cross Icons
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    // mark a task as done
    public Task completeTask() {
        this.isDone = true;
        return this;
    }

    // return the task description
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
