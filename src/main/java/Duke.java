import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        printHorizontalLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printHorizontalLine();
        line = sc.nextLine();
        while (!line.equals("bye")){
            try {
                if(line.equals("list")){
                    printHorizontalLine();
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("  " + (i+1) +":" + tasks.get(i));
                    }
                    printHorizontalLine();
                }else if(line.startsWith("done")){
                    printHorizontalLine();
                    int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+",""))-1;
                    tasks.get(taskNumberCompleted).completeTask();
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("   "+":"+tasks.get(taskNumberCompleted));
                    printHorizontalLine();
                    System.out.println();
                } else if(line.startsWith("todo")){
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");
                    tasks.add(new ToDo(line));
                    System.out.println("  " + new ToDo(line));
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if(line.startsWith("deadline")){
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");
                    tasks.add(new Deadline(line));
                    System.out.println("  " + new Deadline(line));
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                    printHorizontalLine();
                } else if(line.startsWith("event")){
                    printHorizontalLine();
                    System.out.println("  Got it. I've added this task:");
                    tasks.add(new Event(line));
                    System.out.println("  " + new Event(line));
                    System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
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
                System.out.println("Something went wrong!");
                printHorizontalLine();
            }
            line = sc.nextLine();
        }
        printHorizontalLine();
        System.out.println("  Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine () {
        System.out.println(" ____________________________________________________________");
    }
}