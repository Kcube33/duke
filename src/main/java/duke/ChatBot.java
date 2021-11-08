package duke;

import duke.tasks.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class ChatBot {

    private static ArrayList<TaskList> all_tasks = new ArrayList<>();

    /** Prints out all tasks stored in the arraylist row by row
     * Utilises the method printtask from each individual task in the arraylist
     * @see TaskList#printtask()  Method to generate output for each different task
     * */
    public static void printem(){
        if(all_tasks.isEmpty()){
            System.out.println("Nothing to do");
        } else {
            for (int i = 0; i < all_tasks.size(); i++) {
                System.out.println(i+1 + ". " + all_tasks.get(i).printtask());
            }
        }
    }

    /**
     * Main method of Duke
     * This method intakes the input from the user and determines if it is valid command for Duke. It
     * only parses the first word of the input to avoid confusion.
     * When a valid input is detected, Duke will proceed to perform the function accordingly
     * At the end of every command, outstanding tasks will be saved to the hard disk
     * @param storage The list of tasks retrieved from the saved text file of outstanding tasks
     * @see Parser This class performs the various intake of user inputs
     * @see CreateTasks Class responsible to execute the creation and deletion of tasks
     * @see WriteToFile#startWriting(ArrayList) Takes the existing ArrayList and writes to file
     */
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
                    System.out.println("Please enter deadline of task in \"yyyy-MM-dd HH:mm\"");
                    String by = Parser.UIinput();
                    CreateTasks.Deadline(all_tasks,userIn, by);
                    break;
                case "event":
                    System.out.println("Please enter event date and time in \"yyyy-MM-dd HH:mm\"");
                    String at = Parser.UIinput();
                    System.out.println("Please enter estimated end time and date in \"yyyy-MM-dd HH:mm\"");
                    String end = Parser.UIinput();
                    CreateTasks.Event(all_tasks,userIn, at, end);
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
