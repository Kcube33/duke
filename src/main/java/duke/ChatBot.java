package duke;

import duke.tasks.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class ChatBot {

    private static ArrayList<TaskList> all_tasks = new ArrayList<>();

    public static void printem(){
        if(all_tasks.isEmpty()){
            System.out.println("Nothing to do");
        } else {
            for (int i = 0; i < all_tasks.size(); i++) {
                System.out.println(i+1 + ". " + all_tasks.get(i).printtask());
            }
        }
    }

    public ChatBot(ArrayList<TaskList> storage){
        all_tasks = storage;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String userIn;
        String first;
        // create enum for list of accepted words
        do {
            userIn = Parser.UIinput();
            first = Parser.FirstWord(userIn);
            switch (first) {
                case "list":
                    printem();
                    break;
                case "":
                    System.out.println("Please enter something");
                    break;
                case "done":
                    System.out.println("Which task number have u completed?");
                    printem();
                    //add in exception
                    Integer taskno = Integer.parseInt(Parser.UIinput()) -1;
                    all_tasks.get(taskno).done();
                    break;
                case "delete":
                    System.out.println("Which task number do you want to delete?");
                    printem();
                    // add in exception
                    Integer task = Integer.parseInt(Parser.UIinput());
                    CreateTasks.delete(all_tasks, task);
                    break;
                case "todo":
                    CreateTasks.Todo(all_tasks, userIn);
                    break;
                case "deadline":
                    CreateTasks.Deadline(all_tasks,userIn);
                    break;
                case "event":
                    CreateTasks.Event(all_tasks,userIn);
                    break;
                case "bye":
                    break;
                default:
                    System.out.println(
                            "____________________________________________________________\n" +
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "____________________________________________________________");
                    break;
            }
            WriteToFile.startWriting(all_tasks);
        } while (!"bye".equals(first));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
