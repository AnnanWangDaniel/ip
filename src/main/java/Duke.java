import ImportExportFileHandler;
import exceptions.*;
import tasks.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SEPARATOR = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int inputStoredIndex = 0;
    enum TaskType {
        TODO, DEADLINE, EVENT, TASK;
    }
    private static ImportExportFileHandler fileHandler = new ImportExportFileHandler(tasks);

    public static void main(String[] args) {
        printLogo();
        greet();
        echo();
    }

    public static void printLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet(){
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";

        System.out.println(SEPARATOR);
        System.out.println(greeting);
        System.out.println(SEPARATOR);
    }

    public static void echo(){
        Scanner scanner = new Scanner(System.in);
        String userInputSentence;

        while (true) {
            userInputSentence = scanner.nextLine();
            executeCommand(userInputSentence);
        }
    }

    private static void executeCommand(String userInputSentence) {
        boolean isEqualToTERMIATION_SIGNAL = userInputSentence.toLowerCase().equals(Commands.TERMINATION_SIGNAL);
        boolean isEqualToLIST_PRINT_COMMAND = userInputSentence.toLowerCase().equals(Commands.LIST_PRINT_COMMAND);
        boolean isEqualToSET_TASK_DONE_COMMAND = userInputSentence.length() >= Commands.SET_TASK_DONE_COMMAND.length() &&
                    userInputSentence.substring(0, Commands.SET_TASK_DONE_COMMAND.length()).equals(Commands.SET_TASK_DONE_COMMAND);
        boolean isEqualToDELETE_COMMAND = userInputSentence.startsWith(Commands.DELETE_COMMAND);

        if (isEqualToTERMIATION_SIGNAL){
            exit();
        } else if (isEqualToLIST_PRINT_COMMAND) {
            printTaskList();
        } else if (isEqualToSET_TASK_DONE_COMMAND) {
            String[] splitCommand = userInputSentence.split(" ");
            Integer setTaskId = Integer.valueOf(splitCommand[1]);

            setTaskDone(setTaskId - 1); // -1 because array's index starts from 0
        } else if (isEqualToDELETE_COMMAND) {
            DeleteTask(userInputSentence);
        } else {
            try {
                executeTaskAddingCommand(userInputSentence);
            } catch (EmptyContentForTodoCommandException e) {
                System.out.println(SEPARATOR);
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println(SEPARATOR);
            } catch (UnknownCommandException e) {
                System.out.println(SEPARATOR);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(SEPARATOR);
            }
        }
    }

    private static void DeleteTask(String userInputSentence) {
        int taskId = Integer.parseInt(userInputSentence.split(" ")[1]);
        try {
            Task task = tasks.get(taskId-1);
            tasks.remove(taskId - 1);

            System.out.println(SEPARATOR);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            System.out.println(SEPARATOR);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("There is no task with the index %d", taskId);
        }

        fileHandler.UpdateFile(tasks);
    }

    private static void executeTaskAddingCommand(String userInputSentence) throws EmptyContentForTodoCommandException, UnknownCommandException{
        boolean isAddTodo = userInputSentence.length() >= Commands.ADD_TODO_COMMAND.length() &&
                userInputSentence.substring(0, Commands.ADD_TODO_COMMAND.length()).equals(Commands.ADD_TODO_COMMAND);
        boolean isAddDeadline = userInputSentence.length() >= Commands.ADD_DEADLINE_COMMAND.length() &&
                userInputSentence.substring(0, Commands.ADD_DEADLINE_COMMAND.length()).equals(Commands.ADD_DEADLINE_COMMAND);
        boolean isAddEvent = userInputSentence.length() >= Commands.ADD_EVENT_COMMAND.length() &&
                userInputSentence.substring(0, Commands.ADD_EVENT_COMMAND.length()).equals(Commands.ADD_EVENT_COMMAND);

        TaskType taskTypeToAdd = TaskType.TASK;
        if (isAddTodo) {
            taskTypeToAdd = TaskType.TODO;
        } else if (isAddDeadline) {
            taskTypeToAdd = TaskType.DEADLINE;
        } else if (isAddEvent) {
            taskTypeToAdd = TaskType.EVENT;
        } else {
            throw new UnknownCommandException();
        }
        try {
            switch (taskTypeToAdd) {
                case TODO: {
                    String description = userInputSentence.substring(Commands.ADD_TODO_COMMAND.length());
                    if (description == null || description.equals("")) {
                        throw new EmptyContentForTodoCommandException();
                    }
                    tasks.add(new Todo(description));
                    printSuccessfulAddedMessage();
                    inputStoredIndex++;
                    break;
                }
                case DEADLINE: {
                    String[] splitCommands = userInputSentence.substring(Commands.ADD_DEADLINE_COMMAND.length()).split("/by ");
                    tasks.add(new Deadline(splitCommands[0], splitCommands[1]));
                    printSuccessfulAddedMessage();
                    inputStoredIndex++;
                    break;
                }
                case EVENT: {
                    String[] splitCommands = userInputSentence.substring(Commands.ADD_EVENT_COMMAND.length()).split("/at ");
                    tasks.add(new Event(splitCommands[0], splitCommands[1]));
                    printSuccessfulAddedMessage();
                    inputStoredIndex++;
                    break;
                }
                default: {
                    /* If the user doesn't specify the task type to add, the reminder will be given. */
                    System.out.println(SEPARATOR);
                    System.out.println("Please specify the task type.");
                    System.out.println(SEPARATOR);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(SEPARATOR);
            System.out.println("Wrong syntax for commands.");
            System.out.println(SEPARATOR);
        }

        fileHandler.UpdateFile(tasks);
    }

    private static void setTaskDone(Integer setTaskId) {
        try {
            Task targetTask = tasks.get(setTaskId);
            targetTask.setDone();

            System.out.println(SEPARATOR);
            System.out.printf("Nice! I've marked this task as done: \n[%s] %s\n",
                    targetTask.getStatusIcon(),targetTask.getDescription());
            System.out.println(SEPARATOR);
        }
        catch (NullPointerException e) {
            System.out.printf("duke.tasks.Task %d does not exist.\n", setTaskId);
        }

        fileHandler.UpdateFile(tasks);
    }

    private static void printSuccessfulAddedMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(inputStoredIndex));
        System.out.printf("Now you have %d tasks in the list.\n", Task.getNumTotalTasks());
        System.out.println(SEPARATOR);
    }

    public static void sayGoodbye(){
        String goodbyeWords = "Bye. Hope to see you again soon!";

        System.out.println(SEPARATOR);
        System.out.println(goodbyeWords);
        System.out.println(SEPARATOR);
    }

    protected static void exit() {
        sayGoodbye();
        System.exit(0);
    }

    protected static void printTaskList() {
        int taskId = 1;

        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");

        for (Task task: tasks) {
            if (task==null) {
                break;
            } else {
                System.out.printf("%d.%s\n", taskId, task);
                taskId++;
            }
        }

        System.out.println(SEPARATOR);
    }
}
