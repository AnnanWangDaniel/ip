import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        FileInputStream inputFile = new FileInputStream("data/duke_input.txt");
        Scanner scFile =new Scanner(inputFile);
        FileWriter writer = new FileWriter("data/duke.txt");
        printHorizontalLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printHorizontalLine();
        if (scFile.hasNextLine()){
            line = scFile.nextLine();
        }
        else {
            line = sc.nextLine();
        }
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    printHorizontalLine();
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ":" + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
                    }
                    printHorizontalLine();
                } else if (line.startsWith("done")) {
                    printHorizontalLine();
                    int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+", "")) - 1;
                    tasks.set(taskNumberCompleted, tasks.get(taskNumberCompleted).completeTask());
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(" " + ":" + "[" + tasks.get(taskNumberCompleted).getStatusIcon() + "] " + tasks.get(taskNumberCompleted).getDescription());
                    printHorizontalLine();
                    System.out.println();
                } else if (line.startsWith("todo")) {
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");
                    Todo todo = new Todo(line);
                    tasks.add(todo);
                    System.out.println("  " + todo.toString());
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
                    System.out.println("  " + ddl.toString());
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
                    System.out.println("  " + event.toString());
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if (line.startsWith("delete")){
                    printHorizontalLine();
                    System.out.println("Noted. I've removed this task:  ");
                    int taskNumberdeleted = Integer.parseInt(line.replaceAll("\\D+", "")) - 1;
                    System.out.println(" " + "[" + tasks.get(taskNumberdeleted).getStatusIcon() + "] " + tasks.get(taskNumberdeleted).getDescription());
                    tasks.remove(taskNumberdeleted);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if (line.isEmpty()) {
                    printHorizontalLine();
                    System.out.println("  ☹ OOPS!!! The description of a task cannot be empty.");
                    printHorizontalLine();
                } else {
                    printHorizontalLine();
                    System.out.println("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    printHorizontalLine();
                    System.out.println();
                }
            } catch (StringIndexOutOfBoundsException e) {
                printHorizontalLine();
                System.out.println("The task you input has missing fields!");
                printHorizontalLine();
            } catch (Exception e) {
                printHorizontalLine();
                if(line.equals("todo")) {
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                } else if(line.equals("deadline")) {
                    System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                } else if(line.equals("event")) {
                    System.out.println(" OOPS!!! The description of a event cannot be empty.");
                } else {
                    System.out.println("Whoops, something went wrong!");
                }
                printHorizontalLine();
            }
            if (scFile.hasNextLine()){
                line = scFile.nextLine();
            }
            else {
                line = sc.nextLine();
            }
        }
        try {
            for (int i = 0; i < tasks.size(); i++) {
                if(tasks.get(i) instanceof Deadline){
                    writer.write("deadline " +tasks.get(i).getDescription());
                }
                if(tasks.get(i) instanceof Event){
                    writer.write("event " +tasks.get(i).getDescription());
                }
                if(tasks.get(i) instanceof Todo){
                    writer.write("todo " +tasks.get(i).getDescription());
                }
            }
        }
        catch (IOException e) {
            printHorizontalLine();
            System.out.println("Failed to save tasklist.");
            printHorizontalLine();
        }
        printHorizontalLine();
        System.out.println("  Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println(" ____________________________________________________________");
    }
}
