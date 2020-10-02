/**
 * The main function of the app.
 * Read in user commands and maitaion the program loop.
 */
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;
import UI.UI;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Duke {
    private static String line;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static File data = new File("data/duke.txt");
    protected static FileOutputStream outputFile;
    protected static Scanner sc = new Scanner(System.in);
    public static FileInputStream inputFile;

    static {
        try {
            outputFile = new FileOutputStream(data,true);
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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        UI.greet();
        
        line = getInput();
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    UI.printTaskList(tasks);
                } 
                else if (line.startsWith("done")) {
                    int taskCompletedId = Integer.parseInt(line.replaceAll("\\D+", "")) - 1;//get the changed task ID
                    tasks.set(taskCompletedId, tasks.get(taskCompletedId).completeTask());
                    UI.messageDone(tasks, taskCompletedId);
                } 
                else if (line.startsWith("todo")) {
                    Todo todo = new Todo(line);
                    tasks.add(todo);
                    UI.messageTodo(tasks, todo);
                } else if (line.startsWith("deadline")) {
                    String descriptionOfDeadline;
                    String by;
                    descriptionOfDeadline = line.substring(9, (line.indexOf("/") - 1));
                    by = line.substring((line.indexOf("/") + 4));
                    Deadline ddl = new Deadline(descriptionOfDeadline, by);
                    tasks.add(ddl);
                    UI.messageDeadline(tasks, ddl);
                } else if (line.startsWith("event")) {
                    String descriptionOfEvent;
                    String at;
                    descriptionOfEvent = line.substring(6, (line.indexOf("/") - 1));
                    at = line.substring((line.indexOf("/") + 4));
                    Event event = new Event(descriptionOfEvent, at);
                    tasks.add(event);
                    UI.messageEvent(tasks, event);
                } else if (line.startsWith("delete")){
                    int taskdeletedId = Integer.parseInt(line.replaceAll("\\D+", "")) - 1;
                    UI.messageDelete(tasks, taskdeletedId);
                } else if (line.isEmpty()) {
                    UI.messageEmptyCommand();
                } else if (line.equals("save")) {
                    saveTasks(tasks, data);
                    UI.messageSaved();
                } else if (line.startsWith("find")){
                    UI.messageFind(tasks, line);
                }
                else {
                    UI.messageInvalidCommand();
                }
            } catch (StringIndexOutOfBoundsException e) {
                UI.messageStringIndexOutOfBoundsExceptio();
            } catch (Exception e) {
                UI.printHorizontalLine();
                if(line.equals("todo")) {
                    System.out.println("  OOPS!!! The description of a todo cannot be empty.");
                } else if(line.equals("deadline")) {
                    System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                } else if(line.equals("event")) {
                    System.out.println("  OOPS!!! The description of a event cannot be empty.");
                } else {
                    System.out.println("  Whoops, something went wrong!");
                }
                UI.printHorizontalLine();
            }
            line = getInput();
        }
        saveTasks(tasks, data);
        UI.messageExit();
    }

    //write tasks to the output file
    public static void saveTasks(ArrayList<Task> tasks, File data) {
        try {
            FileWriter writer = new FileWriter(data,false); //to not overwrite the file, set to true.
            writer.write("\n");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toString() + "\n");
            }
            writer.close(); 
        }
        catch (IOException e) {
            UI.messageSavingError();
        }
    }

    //add get input from storage file or user input line
    public static String getInput(){
        if(in.hasNextLine()){
            line = in.nextLine();
            if (line.isEmpty()){
                if(in.hasNextLine()){
                    line = in.nextLine();
                }
                else {
                    line = sc.nextLine();
                }
            }
            if(line.substring(1,2).equals("T")){
                line = "todo " + line.substring(7);
            }
            else if(line.substring(1,2).equals("D")){
                line = "deadline " + line.substring(7,(line.indexOf("(") )) + "/ by" + line.substring(line.indexOf(":")+2,line.indexOf(")"));
            }
            else if(line.substring(1,2).equals("E")){
                line = "event " + line.substring(7,(line.indexOf("(") )) + "/ by" + line.substring(line.indexOf(":")+2,line.indexOf(")"));
            }
        }
        else {
            line = sc.nextLine();
        }
        return line;
    }
}