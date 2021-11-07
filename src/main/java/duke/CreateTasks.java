package duke;


import duke.tasks.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CreateTasks {

    private static String task;
    private static String by;

    public static void Todo(ArrayList<TaskList> all_tasks, String text){
        try{
            task = Parser.parseTodo(text);
            all_tasks.add(new Todo(task));
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

    public static void Deadline(ArrayList<TaskList> all_tasks, String text){
        try {

            all_tasks.add(new Deadline(text.substring(9, text.indexOf('/')),
                    text.substring(text.indexOf('/') + 4)));
            System.out.println("----------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(all_tasks.get(all_tasks.size()-1).printtask());
            System.out.println("Now you have " + all_tasks.size() + " tasks in the list.");
            System.out.println("----------------------------------------------------");
        } catch (IndexOutOfBoundsException e){
            System.out.println("----------------------------------------------------");
            System.out.println("☹ OOPS!!! The description of a deadline is incomplete.");
            System.out.println("----------------------------------------------------");
        }
    }

    public static void Event(ArrayList<TaskList> all_tasks, String text){
        try {
            all_tasks.add(new Events(text.substring(6, text.indexOf('/')),
                    text.substring(text.indexOf('/') + 4)));
            System.out.println("----------------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(all_tasks.get(all_tasks.size()-1).printtask());
            System.out.println("Now you have " + all_tasks.size() + " tasks in the list.");
            System.out.println("----------------------------------------------------");
        } catch (IndexOutOfBoundsException e){
            System.out.println("----------------------------------------------------");
            System.out.println("☹ OOPS!!! The description of a event is incomplete.");
            System.out.println("----------------------------------------------------");
        }
    }

    public static void delete(ArrayList<TaskList> all_tasks, Integer taskno){
        int temp = taskno-1;
        try {
            System.out.println(
                    "Noted. I've removed this task: \n" +
                            all_tasks.get(temp).printtask() +
                            "\nNow you have " + temp  + " tasks in the list.");
            all_tasks.remove(temp);
        } catch (IndexOutOfBoundsException e){
            System.out.println("Task Number Does not exist. Please try again");
        }

    }

}
