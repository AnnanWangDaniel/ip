import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        final String HORIZONTAL_LINE = " ____________________________________________________________";
        System.out.println(HORIZONTAL_LINE);
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        line = sc.nextLine();
        while (!line.equals("bye")){
            try {
                if(line.equals("list")){
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("  " + (i+1) +":" + tasks.get(i));
                    }
                    System.out.println(HORIZONTAL_LINE);
                }else if(line.startsWith("done")){
                    System.out.println(HORIZONTAL_LINE);
                    int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+",""))-1;
                    tasks.get(taskNumberCompleted).completeTask();
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("   "+":"+tasks.get(taskNumberCompleted));
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println();
                } else if(line.startsWith("todo")){
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("  Got it. I've added this task:");
                    tasks.add(new ToDo(line));
                    System.out.println("  " + new ToDo(line));
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if(line.startsWith("deadline")){
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("  Got it. I've added this task:");
                    tasks.add(new Deadline(line));
                    System.out.println("  " + new Deadline(line));
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if(line.startsWith("event")){
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("  Got it. I've added this task:");
                    tasks.add(new Event(line));
                    System.out.println("  " + new Event(line));
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (line.isEmpty()) {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("  ☹ OOPS!!! The description of a task cannot be empty.");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println();
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("The task you input has missing fields!");
                System.out.println(HORIZONTAL_LINE);
            } catch (Exception e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Something went wrong!");
                System.out.println(HORIZONTAL_LINE);
            }
            line = sc.nextLine();
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}