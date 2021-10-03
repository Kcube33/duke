import java.util.HashMap;
import java.util.Scanner;

public class ChatBot {
    private static HashMap<Integer, TaskList> all_tasks = new HashMap<>();
    private static int counter = 1;

    public static void addem(String text){
        if (text.startsWith("todo")){
            all_tasks.put(counter, new todo(text.substring(5)));
            counter++;
        } else if (text.startsWith("deadline")){
            all_tasks.put(counter, new Deadline(text.substring(9, text.indexOf('/')),
                    text.substring(text.indexOf('/')+4)));
            counter++;
        } else if(text.startsWith("event")){
            all_tasks.put(counter, new Events(text.substring(9, text.indexOf('/')),
                    text.substring(text.indexOf('/')+4)));
            counter++;
        } else {
            System.out.println("Try Again");
        }
        // debugging with try/catch
        // do not add same task
    }

    public static void printem(){
        if(all_tasks.isEmpty()){
            System.out.println("Nothing to do");
        } else {
            for (int i = 1; i < counter; i++) {
                System.out.println(i + ". " + all_tasks.get(i).printtask());
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
                    all_tasks.get(taskno).done();
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
