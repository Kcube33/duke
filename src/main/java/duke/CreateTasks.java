package duke;


import duke.tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import duke.exception.*;

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
            text = Parser.parseTodo(text);
            if (CheckTasks.gotDup(all_tasks,text, "T")){
                return;
            }
            all_tasks.add(new Todo(text));
            printTaskDes(all_tasks.get(all_tasks.size()-1).printtask(),all_tasks.size());
        } catch (IndexOutOfBoundsException e){
            printError("todo");
        }
    }

    /**
     * Creates a deadline task and adds it to ArrayList
     * @param all_tasks
     * @param text User input of the task description
     */
    public static void Deadline(ArrayList<TaskList> all_tasks, String text){
        try {
            text = Parser.parseDeadline(text);
            if (CheckTasks.gotDup(all_tasks,text, "D")){
                return;
            }
            System.out.println("Please enter deadline of task in \"yyyy-MM-dd HH:mm\"");
            LocalDateTime by = Parser.parseDateTime(Parser.UIinput());
            all_tasks.add(new Deadline(text,
                    by));
            printTaskDes(all_tasks.get(all_tasks.size()-1).printtask(),all_tasks.size());
        } catch (IndexOutOfBoundsException e){
            printError("deadline");
        } catch (DateTimeParseException pe){
            System.out.println("Please try again in format: yyyy-MM-dd HH:mm");
        } catch (timelineException e){
            System.out.println("Unable to recognise date format!");
        }
    }

    /**
     * Creates an Event task and adds it to ArrayList
     * @param all_tasks
     * @param text User input of the task description
     */
    public static void Event(ArrayList<TaskList> all_tasks, String text){
        try {
            text = Parser.parseEvent(text);
            if (CheckTasks.gotDup(all_tasks,text, "E")){
                return;
            }
            System.out.println("Please enter event date and time in \"yyyy-MM-dd HH:mm\"");
            LocalDateTime at = Parser.parseDateTime(Parser.UIinput());
            System.out.println("Please enter estimated end time and date in \"yyyy-MM-dd HH:mm\"");
            LocalDateTime end = Parser.parseDateTime(Parser.UIinput());
            all_tasks.add(new Events(text,
                    at,end));
            printTaskDes(all_tasks.get(all_tasks.size()-1).printtask(),all_tasks.size());
        } catch (IndexOutOfBoundsException e){
            printError("event");
        } catch (DateTimeParseException pe){
            System.out.println("Please try again in format: yyyy-MM-dd HH:mm");
        } catch (timelineException e){
            System.out.println("Unable to recognise date format!");
        }
    }

    /**
     * Removes a task from ArrayList according to user
     * ArrayList must contain tasks to delete
     * task number must not be zero
     * @param all_tasks
     */
    public static void delete(ArrayList<TaskList> all_tasks){
        if (all_tasks.isEmpty()){
            System.out.println("There are no existing tasks in your list");
            return;
        }
        try {
            assert !all_tasks.isEmpty();
            ChatBot.printTasks();
            System.out.println("Which task number do you want to delete?\n" +
                               "_______________________________________");
            int taskNo = Integer.parseInt(Parser.UIinput()) -1;
            assert !(taskNo == 0);
            System.out.println(
                    "Noted. I've removed this task: \n" +
                            all_tasks.get(taskNo).printtask() +
                            "\nNow you have " + (all_tasks.size()-1)  + " tasks in the list.");
            all_tasks.remove(taskNo);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task Number Does not exist. Please try again!");
        } catch (NumberFormatException e){
            System.out.println("Please enter a valid number!");
        } catch (NullPointerException e){
            System.out.println("There is not task at this number!");
        }
    }

    /**
     * @param all_tasks ArrayList of tasks
     */
    public static void clearTasks(ArrayList<TaskList> all_tasks){
        System.out.println("Are you sure to clear your list? Enter y to confirm");
        String result = Parser.UIinput();
        if (result.equals("y")){
            all_tasks.clear();
            System.out.println("You currently have 0 tasks.");
        }
    }

    private static void printTaskDes(String task, Integer size){
        System.out.println("----------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("----------------------------------------------------");
    }

    private static void printError(String type){
        System.out.println("----------------------------------------------------");
        System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        System.out.println("----------------------------------------------------");
    }

}
