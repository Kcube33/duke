package duke;

import duke.tasks.*;
import java.util.Scanner;

public class Parser {

    private static String task;
    private static String by;

    public static String FirstWord(String text){
        if(text.contains(" ")){
            text= text.substring(0, text.indexOf(" "));
        }
        return text;
    }

    public static String UIinput(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    public static String parseTodo(String text){
        task = text.substring(5);
        return task;
    }

    // fix to date
    public static String parseDeadline(String text){
        task = text.substring(9, text.indexOf('/'));
        by = text.substring(text.indexOf('/') + 4);
        return task;
    }

    // fix to date
    public static String parseEvent(String text){
        task = text.substring(6, text.indexOf('/'));
        by = text.substring(text.indexOf('/') + 4);
        return task;
    }



}
