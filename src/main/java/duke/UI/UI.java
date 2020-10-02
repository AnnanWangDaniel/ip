/**
 * The UI part of program.
 * Manages outputs contents to users.
 */
package UI;
import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;

public class UI {

    public static void printHorizontalLine() {
        System.out.println(" ____________________________________________________________");
    }

    public static void greet(){
        printHorizontalLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printHorizontalLine();
    }

    public static void printTaskList(ArrayList<Task> tasks){
        printHorizontalLine();
        System.out.println("  Here are the tasks in your list:");
        int i = 0;
        for (Task t : tasks) {
            i++;
            System.out.println("     " + i + ":" + t.toString());
        }
        printHorizontalLine();
    }

    public static void messageDone(ArrayList<Task> tasks, int taskCompletedId){
        printHorizontalLine();
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(taskCompletedId).toString());
        printHorizontalLine();
        System.out.println();
    }

    public static void messageTodo(ArrayList<Task> tasks, Todo todo){
        printHorizontalLine();
        System.out.println("  Got it. I've added this task:");
        System.out.println("   " + todo.toString());
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void messageDeadline(ArrayList<Task> tasks, Deadline ddl){
        printHorizontalLine();
        System.out.println("  Got it. I've added this task:");
        System.out.println("   " + ddl.toString());
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void messageEvent(ArrayList<Task> tasks, Event event){
        printHorizontalLine();
        System.out.println("  Got it. I've added this task:");                
        System.out.println("   " + event.toString());
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void messageDelete(ArrayList<Task> tasks, int taskdeletedId){
        printHorizontalLine();
        System.out.println("  Noted. I've removed this task:  ");
        System.out.println("   " + tasks.get(taskdeletedId).toString());
        tasks.remove(taskdeletedId);
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void messageEmptyCommand(){
        printHorizontalLine();
        System.out.println("  OOPS!!! The description of a task cannot be empty.");
        printHorizontalLine();
    }

    public static void messageSaved(){
        printHorizontalLine();
        System.out.println("  Tasks saved!");
        printHorizontalLine();
    }

    public static void messageFind(ArrayList<Task> tasks, String line){
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

    public static void messageStringIndexOutOfBoundsExceptio(){
        printHorizontalLine();
        System.out.println("  The task you input has missing fields!");
        printHorizontalLine();        
    }

    public static void messageSavingError(){
        printHorizontalLine();
        System.out.println("    Failed to save tasklist.");
        printHorizontalLine();        
    }

    public static void messageInvalidCommand(){
        printHorizontalLine();
        System.out.println("  OOPS!!! I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
        System.out.println();
    }

    public static void messageExit(){
        printHorizontalLine();
        System.out.println("  Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}