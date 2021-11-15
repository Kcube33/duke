package duke;

import duke.tasks.TaskList;
import duke.tasks.TaskList.*;

import java.util.ArrayList;
import java.util.Locale;

public class CheckTasks {

    /**
     * Check for duplicate todo tasks
     * @param all_tasks
     * @param text
     * @return
     */
    public static boolean gotDup (ArrayList<TaskList> all_tasks, String text, String type){
        text = text.toUpperCase(Locale.ROOT);
        String des;
        for (TaskList task:all_tasks){
            des = task.getTask().toUpperCase(Locale.ROOT);
            if (task.getType().equals(type) && des.equals(text)){
                printDup(task.printtask());
                System.out.println("Are you sure you want to add again?\n" +
                        "Enter y to confirm");
                String result = Parser.UIinput();
                if (!result.equals("y")){
                    return true;
                }
            }
        }
        return false;
    }

    private static void printDup(String task){
        assert !task.trim().equals("");
        System.out.println("This task already exists: ");
        System.out.println(task);
    }

    public static void findTasks(ArrayList<TaskList> all_tasks, String word){
        int temp = 1;
        System.out.println( "--------------------------------------------\n" +
                "Here are the matching tasks in your list:");
        for (TaskList task:all_tasks){
            if (task.getTask().contains(word)){
                 System.out.println(temp + ". " + task.printtask()); ;
                 temp++;
            }
        }
        if (temp == 1){
            System.out.println("No tasks found matching keyword: " + word);
        }
    }
}
