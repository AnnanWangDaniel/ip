package tasks;

import Commands;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTotalTasks = 0;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        numTotalTasks++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public static int getNumTotalTasks() {
        return numTotalTasks;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }

    public static boolean parseTask(String taskInString, ArrayList<Task> tasks) {
        char type = taskInString.charAt(1);
        char status = taskInString.charAt(4);
        taskInString = taskInString.substring(6);
        Task buffer;
        String[] splitCommands;
        String description;
        String time;

        switch (type) {
        case 'T':
            buffer = new Todo(taskInString);
            break;
        case 'E':
            splitCommands = taskInString.split("\\(at: ");
            description = splitCommands[0].strip();
            time = splitCommands[1].substring(0, splitCommands[1].length()-1).strip();
            buffer = new Event(description, time);
            break;
        case 'D':
            splitCommands = taskInString.split("\\(by: ");
            description = splitCommands[0].strip();
            time = splitCommands[1].substring(0, splitCommands[1].length()-1).strip();
            buffer = new Deadline(splitCommands[0], splitCommands[1]);
            break;
        default:
            System.out.println("Error: Wrong input file syntax.");
            return false;
        }
        if (status == '\u2713') {
            buffer.setDone();
        }
        tasks.add(buffer);
        return true;
    }
}