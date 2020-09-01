import java.util.Scanner;

public class Duke {
    public static void printHorizonLine(){
        System.out.println(" ____________________________________________________________");
    }
    public static void printHello(){
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
    }
    public static void printGoodBye(){
        System.out.println("  Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        String input;
        printHorizonLine();
        printHello();
        printHorizonLine();
        System.out.println();
        while(true){
            input = k.nextLine();
            if(input.toLowerCase().equals("bye")){
                break;
            }
            printHorizonLine();
            System.out.println("  " + input);
            printHorizonLine();
            System.out.println();
        }
        printHorizonLine();
        printGoodBye();
        printHorizonLine();
    }
}