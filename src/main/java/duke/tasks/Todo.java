/**
 * class todo a sub-class of class Task
 */

package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
