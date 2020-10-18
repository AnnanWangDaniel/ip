/**
 * The main function of the app.
 * Read in user commands and maitaion the program loop.
 */

import tasks.Task;
import UI.UI;
import TaskManager.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Duke {
    private static String line;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static File data = new File("duke_tasks_storage.txt");
    protected static FileOutputStream outputFile;
    protected static Scanner sc = new Scanner(System.in);
    public static FileInputStream inputFile;    
    public static boolean isFromStorage = true;

    static {
        try {
            outputFile = new FileOutputStream(data, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            inputFile = new FileInputStream(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static Scanner in = new Scanner(inputFile);

    /**
     * Read in user inputs and handle logistic.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        UI.greet();

        line = getInput();
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    UI.printTaskList(tasks);
                } else if (line.startsWith("done")) {
                    TaskManager.markTaskAsDone(line, tasks);
                } else if (line.startsWith("todo")) {
                    TaskManager.addTodo(line, tasks, isFromStorage);
                } else if (line.startsWith("deadline")) {
                    TaskManager.addDeadline(line, tasks, isFromStorage);
                } else if (line.startsWith("event")) {
                    TaskManager.addEvent(line, tasks, isFromStorage);
                } else if (line.startsWith("delete")) {
                    TaskManager.deleteTask(line, tasks);
                } else if (line.isEmpty()) {
                    UI.messageEmptyCommand();
                } else if (line.equals("save")) {
                    TaskManager.saveTasks(tasks, data);
                    UI.messageSaved();
                } else if (line.startsWith("find")) {
                    UI.messageFind(tasks, line);
                } else {
                    UI.messageInvalidCommand();
                }
            } catch (StringIndexOutOfBoundsException e) {
                UI.messageStringIndexOutOfBoundsExceptio();
            } catch (Exception e) {
                UI.printHorizontalLine();
                if (line.equals("todo")) {
                    System.out.println("  OOPS!!! The description of a todo cannot be empty.");
                } else if (line.equals("deadline")) {
                    System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                } else if (line.equals("event")) {
                    System.out.println("  OOPS!!! The description of a event cannot be empty.");
                } else {
                    System.out.println("  Whoops, something went wrong!");
                }
                UI.printHorizontalLine();
            }
            line = getInput();
        }
        TaskManager.saveTasks(tasks, data);
        UI.messageExit();
    }

    public static boolean isFirstOpen = true;

    /**
     * add get input from storage file or user input line.
     */ 
    public static String getInput() {
        if (isFirstOpen) {
            isFirstOpen = false;
            System.out.println("  Loading tasks from storage...\n");
        }
        if (in.hasNextLine()) {
            line = in.nextLine();
            if (line.isEmpty()) {
                if (in.hasNextLine()) {
                    line = in.nextLine();
                } else {
                    isFromStorage = false;
                    System.out.println("  All tasks loaded,");
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    line = sc.nextLine();
                }
            }
            if (line.substring(1, 2).equals("T")) {
                line = "todo " + line.substring(7);
            } else if (line.substring(1, 2).equals("D")) {
                line = "deadline " + line.substring(7, (line.indexOf("("))) + "/ by"
                        + line.substring(line.indexOf(":") + 2, line.indexOf(")"));
            } else if (line.substring(1, 2).equals("E")) {
                line = "event " + line.substring(7, (line.indexOf("("))) + "/ by"
                        + line.substring(line.indexOf(":") + 2, line.indexOf(")"));
            }
        } else {
            if (isFromStorage) {
                isFromStorage = false;
                System.out.println("  All tasks loaded,");
                System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                UI.printHorizontalLine();               
            }
            line = sc.nextLine();
        }
        return line;
    }
}