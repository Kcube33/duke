import java.util.Scanner;

public class ChatBot {
    private static TaskList[] all_lists = new TaskList[100];
    private static int counter = 0;

    public static void addem(String text){
        all_lists[counter] = new TaskList(text);
        counter++;
    }

    public static void printem(){
        if(counter == 0){
            System.out.println("Nothing to do");
        } else {
            for (int i = 0; i < counter; i++) {
                System.out.println(i + 1 + ".[" + all_lists[i].getStatusIcon() + "] " +
                        all_lists[i].getTask());
            }
        }
    }

    public void startbot(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
//        System.out.println(!"bye".equals(line));
        while (!"bye".equals(line)) {
            switch (line) {
                case "list":
                    printem();
                    break;
                case "":
                    System.out.println("Please enter something");
                    break;
                case "done":
                    System.out.println("Which task number have u completed?");
                    printem();
                    in = new Scanner(System.in);
                    Integer taskno = Integer.parseInt(in.nextLine());
                    all_lists[taskno - 1].done();
                case "bye":
                    break;
                default:
                    addem(line);
                    break;
            }
            in = new Scanner(System.in);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
