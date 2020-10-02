import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;

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
        
        printHorizontalLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printHorizontalLine();
        
        line = getInput();
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    printHorizontalLine();
                    System.out.println("  Here are the tasks in your list:");
                    int i = 0;
                    for (Task t : tasks) {
                        i++;
                        System.out.println("     " + i + ":" + t.toString());
                    }
                    printHorizontalLine();
                } else if (line.startsWith("done")) {
                    printHorizontalLine();
                    int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+", "")) - 1;
                    tasks.set(taskNumberCompleted, tasks.get(taskNumberCompleted).completeTask());
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNumberCompleted).toString());
                    printHorizontalLine();
                    System.out.println();
                } else if (line.startsWith("todo")) {
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");
                    Todo todo = new Todo(line);
                    tasks.add(todo);
                    System.out.println("   " + todo.toString());
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if (line.startsWith("deadline")) {
                    String descriptionOfDeadline;
                    String by;
                    descriptionOfDeadline = line.substring(9, (line.indexOf("/") - 1));
                    by = line.substring((line.indexOf("/") + 4));
                    Deadline ddl = new Deadline(descriptionOfDeadline, by);
                    tasks.add(ddl);
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");
                    System.out.println("   " + ddl.toString());
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if (line.startsWith("event")) {
                    String descriptionOfevent;
                    String at;
                    descriptionOfevent = line.substring(6, (line.indexOf("/") - 1));
                    at = line.substring((line.indexOf("/") + 4));
                    Event event = new Event(descriptionOfevent, at);
                    tasks.add(event);
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");                
                    System.out.println("   " + event.toString());
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if (line.startsWith("delete")){
                    printHorizontalLine();
                    System.out.println("  Noted. I've removed this task:  ");
                    int taskNumberdeleted = Integer.parseInt(line.replaceAll("\\D+", "")) - 1;
                    System.out.println("   " + tasks.get(taskNumberdeleted).toString());
                    tasks.remove(taskNumberdeleted);
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if (line.isEmpty()) {
                    printHorizontalLine();
                    System.out.println("  ☹ OOPS!!! The description of a task cannot be empty.");
                    printHorizontalLine();
                } else if (line.equals("save")) {
                    printHorizontalLine();
                    saveTasks(tasks, data);
                    System.out.println("  Tasks saved!");
                    printHorizontalLine();
                } else if (line.startsWith("find")){
                    printHorizontalLine();
                    System.out.println("Here are the matching tasks in your list:\n");
                    String keyword = line.substring(4);
                    int count = 0;
                    for(Task t : tasks){
                        if(t.toString().contains(keyword)){
                            count ++;
                            System.out.println("   " + count +": " + t);
                        }
                    }
                    printHorizontalLine();
                }
                else {
                    printHorizontalLine();
                    System.out.println("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printHorizontalLine();
                    System.out.println();
                }
            } catch (StringIndexOutOfBoundsException e) {
                printHorizontalLine();
                System.out.println("  The task you input has missing fields!");
                printHorizontalLine();
            } catch (Exception e) {
                printHorizontalLine();
                if(line.equals("todo")) {
                    System.out.println("  OOPS!!! The description of a todo cannot be empty.");
                } else if(line.equals("deadline")) {
                    System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                } else if(line.equals("event")) {
                    System.out.println("  OOPS!!! The description of a event cannot be empty.");
                } else {
                    System.out.println("  Whoops, something went wrong!");
                }
                printHorizontalLine();
            }
            line = getInput();
        }
        
        saveTasks(tasks, data);
        printHorizontalLine();
        System.out.println("  Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println(" ____________________________________________________________");
    }

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
            printHorizontalLine();
            System.out.println("    Failed to save tasklist.");
            printHorizontalLine();
        }
    }

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
