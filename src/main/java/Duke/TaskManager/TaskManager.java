package TaskManager;

import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;
import UI.UI;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class TaskManager {

    /**
     * mark the selected task in task list as done.
     */
    public static void markTaskAsDone(String input, ArrayList<Task> tasks) {
        int taskCompletedId = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
        tasks.set(taskCompletedId, tasks.get(taskCompletedId).completeTask());
        UI.messageDone(tasks, taskCompletedId);
    }

    /**
     * add a todo task to the task list.
     */
    public static void addTodo(String input, ArrayList<Task> tasks, boolean isFromStorage) {
        Todo todo = new Todo(input);
        tasks.add(todo);
        if (!isFromStorage) {
            UI.messageTodo(tasks, todo);
        }
    }

    /**
     * add a deadline task to the task list.
     */
    public static void addDeadline(String input, ArrayList<Task> tasks, boolean isFromStorage) {
        String descriptionOfDeadline;
        String by;
        descriptionOfDeadline = input.substring(9, (input.indexOf("/") - 1));
        by = input.substring((input.indexOf("/") + 4));
        Deadline ddl = new Deadline(descriptionOfDeadline, by);
        tasks.add(ddl);
        if (!isFromStorage) {
            UI.messageDeadline(tasks, ddl);
        }
    }

    /**
     * add a event task to the task list.
     */
    public static void addEvent(String input, ArrayList<Task> tasks, boolean isFromStorage) {
        String descriptionOfEvent;
        String at;
        descriptionOfEvent = input.substring(6, (input.indexOf("/") - 1));
        at = input.substring((input.indexOf("/") + 4));
        Event event = new Event(descriptionOfEvent, at);
        tasks.add(event);
        if (!isFromStorage) {
            UI.messageEvent(tasks, event);
        }
    }

    /**
     * delete the selected task from the task list.
     */
    public static void deleteTask(String input, ArrayList<Task> tasks) {
        int taskdeletedId = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
        UI.messageDelete(tasks, taskdeletedId);
    }

    /**
     * write tasks to the output file.
     */
    public static void saveTasks(ArrayList<Task> tasks, File data) {
        try {
            // to not overwrite the file, set to true.
            FileWriter writer = new FileWriter(data, false);
            writer.write("\n");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            UI.messageSavingError();
        }
    }
}