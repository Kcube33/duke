package duke;

import duke.tasks.*;

import java.util.ArrayList;
import java.util.Locale;


public class ChatBot {

    private static ArrayList<TaskList> all_tasks = new ArrayList<>();
    static final String logo =    " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    /** Prints out all tasks stored in the arraylist row by row
     * Utilises the method printtask from each individual task in the arraylist
     * @see TaskList#printtask()  Method to generate output for each different task
     * */
    public static void printTasks(){
        if(all_tasks.isEmpty()){
            System.out.println("There is nothing to do in your list");
        } else {
            for (int i = 0; i < all_tasks.size(); i++) {
                System.out.println(i+1 + ". " + all_tasks.get(i).printtask());
            }
        }
    }

    public static void helpList(){
        System.out.println("bye -> Ends Duke");
        System.out.println("list -> List your outstanding tasks");
        System.out.println("done -> Select a task you have completed to mark it done");
        System.out.println("delete -> Select a task you wish to delete");
        System.out.println("todo taskdescription -> Enter a todo task with the description");
        System.out.println("deadline taskdescription -> Enter a deadline task with the description");
        System.out.println("event taskdescription -> Enter an event task with the description");
        System.out.println("find keyword -> Find tasks associated with keyword");
        System.out.println("clear -> Clear all your tasks");
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
    public static void startDuke(ArrayList<TaskList> storage){
        all_tasks = storage;
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you today?\n" +
                "Use !help to see a list of commands");
        String userIn;
        String first;
        // create enum for list of accepted words
        do {
            System.out.println("Please enter your command: ");
            userIn = Parser.UIinput();
            first = Parser.FirstWord(userIn).toLowerCase(Locale.ROOT);
            switch (first) {
                case "list":
                    printTasks();
                    break;
                case "done":
                    System.out.println("Which task number have u completed?");
                    printTasks();
                    Integer taskNo = Integer.parseInt(Parser.UIinput()) -1;
                    all_tasks.get(taskNo).done();
                    break;
                case "delete":
                    CreateTasks.delete(all_tasks);
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
                case "find":
                    CheckTasks.findTasks(all_tasks ,Parser.parseFind(userIn));
                    break;
                case "clear":
                    CreateTasks.clearTasks(all_tasks);
                    break;
                case "!help":
                    helpList();
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
