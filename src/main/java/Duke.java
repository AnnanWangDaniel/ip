import java.util.Scanner;

public class Duke {
    public static RequestList list = new RequestList();

    public static void printHorizonLine(){
        System.out.println(" ____________________________________________________________");
    }
    public static void printHello(){
        printHorizonLine();
        System.out.println("  Hello! I'm Duke");
        System.out.println("  What can I do for you?");
        printHorizonLine();
    }
    public static void printGoodBye(){
        System.out.println("  Bye. Hope to see you again soon!");
    }

    public static void printRequestList(){
        for (int i = 0; i < list.getSize(); i++) {
            Request t = list.getRequest(i);
            System.out.println("  " + (i + 1) + ". " + t.getTitle());
        }
    }

    public static void addRequest(String title){
        list.addRequest(new Request(title));
    }

    public static void main(String[] args) {
        Scanner k = new Scanner(System.in);
        String input;
        printHello();
        while(true){
            input = k.nextLine();
            if(input.toLowerCase().equals("bye")){
                break;
            } else if(input.toLowerCase().equals("list")){
                printHorizonLine();
                printRequestList();
                printHorizonLine();
                continue;
            }else {
                addRequest(input);
                printHorizonLine();
                System.out.println("  Added : " + input);
                printHorizonLine();
                continue;
            }
        }
        printHorizonLine();
        printGoodBye();
        printHorizonLine();
    }
}