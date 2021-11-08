package duke;


import duke.tasks.*;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import duke.exception.timelineException;

/**
 * Handles the creation of different tasks and adding to the ArrayList
 * Also handles the deletion of tasks from the ArrayList
 */
public class CreateTasks {

    private static String task;
    private static String by;

    /**
     * Creates a todo task and adds it the ArrayList
     * @param all_tasks
     * @param text User input of the task description
     * @see Parser
     */
    public static void Todo(ArrayList<TaskList> all_tasks, String text){
        try{
            all_tasks.add(new Todo(Parser.parseTodo(text)));
            System.out.println("----------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(all_tasks.get(all_tasks.size()-1).printtask());
            System.out.println("Now you have " + all_tasks.size() + " tasks in the list.");
            System.out.println("----------------------------------------------------");
        } catch (IndexOutOfBoundsException e){
            System.out.println("----------------------------------------------------");
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println("----------------------------------------------------");
        }
    }

    /**
     * Creates a deadline task and adds it to ArrayList
     * @param all_tasks
     * @param text User input of the task description
     * @param by User input for which the task is to be completed by
     */
    public static void Deadline(ArrayList<TaskList> all_tasks, String text, String by){
        try {
            all_tasks.add(new Deadline(Parser.parseDeadline(text),
                    Parser.parseDateTime(by)));
//            all_tasks.add(new Deadline(text.substring(9, text.indexOf('/')),
//                    text.substring(text.indexOf('/') + 4)));
            System.out.println("----------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(all_tasks.get(all_tasks.size()-1).printtask());
            System.out.println("Now you have " + all_tasks.size() + " tasks in the list.");
            System.out.println("----------------------------------------------------");
        } catch (IndexOutOfBoundsException e){
            System.out.println("----------------------------------------------------");
            System.out.println("☹ OOPS!!! The description of a deadline is incomplete.");
            System.out.println("----------------------------------------------------");
        } catch (DateTimeParseException pe){
            System.out.println("Please try again in format: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Creates an Event task and adds it to ArrayList
     * @param all_tasks
     * @param text User input of the task description
     * @param at User input of when the task is supposed to take place
     * @param end User input of when the task is supposed to end
     */
    public static void Event(ArrayList<TaskList> all_tasks, String text, String at, String end){
        try {
            all_tasks.add(new Events(Parser.parseEvent(text),
                    Parser.parseDateTime(at),Parser.parseDateTime(end)));
            System.out.println("----------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(all_tasks.get(all_tasks.size()-1).printtask());
            System.out.println("Now you have " + all_tasks.size() + " tasks in the list.");
            System.out.println("----------------------------------------------------");
        } catch (IndexOutOfBoundsException e){
            System.out.println("----------------------------------------------------");
            System.out.println("☹ OOPS!!! The description of a event is incomplete.");
            System.out.println("----------------------------------------------------");
        } catch (DateTimeParseException pe){
            System.out.println("Please try again in format: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Removes a task from ArrayList according to user
     * @param all_tasks
     * @param taskno User input of which task to remove
     */
    public static void delete(ArrayList<TaskList> all_tasks, Integer taskno){
        int temp = taskno-1;
        try {
            System.out.println(
                    "Noted. I've removed this task: \n" +
                            all_tasks.get(temp).printtask() +
                            "\nNow you have " + (all_tasks.size()-1)  + " tasks in the list.");
            all_tasks.remove(temp);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task Number Does not exist. Please try again");
        }

    }

}
