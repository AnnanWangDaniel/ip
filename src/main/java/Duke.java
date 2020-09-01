import java.util.*;

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
            if(line.equals("list")){
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < tasks.size(); i++) {

                    System.out.println((i+1)+":"+"["+tasks.get(i).getStatusIcon()+"] "+tasks.get(i).description);
                }
                System.out.println(HORIZONTAL_LINE);
            }else if(line.startsWith("done")){
                System.out.println(HORIZONTAL_LINE);
                int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+",""))-1;
                tasks.set(taskNumberCompleted,tasks.get(taskNumberCompleted).completeTask());
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + "["+tasks.get(taskNumberCompleted).getStatusIcon()+"] "+tasks.get(taskNumberCompleted).description);
                System.out.println(HORIZONTAL_LINE);
                System.out.println();
            } else{
                System.out.println(HORIZONTAL_LINE);
                System.out.println("  added :" + line);
                tasks.add(new Task(line));
                System.out.println(HORIZONTAL_LINE);
                System.out.println();
            }
            line = sc.nextLine();
        }
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}