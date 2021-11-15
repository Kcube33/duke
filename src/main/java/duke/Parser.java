package duke;

import duke.tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import duke.exception.timelineException;

/**
 * This class is parses the user input into acceptable commands to be accepted by Duke
 */
public class Parser {

    private static String task;
    private static String by;

    /**
     * Extracts the first word of from {@code text} to be run as a command for Duke
     * @param text Raw User input
     * @return a command for Duke
     */
    public static String FirstWord(String text){
        if(text.contains(" ")){
            text= text.substring(0, text.indexOf(" "));
        }
        return text;
    }

    /**
     * Intake the user's raw input
     * @return
     */
    public static String UIinput(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    /**
     * Parses the todo task description from {@code text}
     * @param text Raw user input
     * @return Task description for todo
     */
    public static String parseTodo(String text){
        task = text.substring(5);
        return task;
    }

    /**
     * Parses the deadline task description from {@code text}
     * @param text Raw user input
     * @return Task description for deadline
     */
    public static String parseDeadline(String text){
        task = text.substring(9);
        return task;
    }

    /**
     * Parses the Event task description from {@code text}
     * @param text Raw user input
     * @return Task description for event
     */
    public static String parseEvent(String text){
        task = text.substring(6);
        return task;
    }

    /**
     * Parse the keyword from {@code text} to find from list of tasks
     * @param text
     * @return keyword
     */
    public static String parseFind(String text){
        task = text.substring(5);
        return task;
    }

    /**
     * Returns a date and time in a 24-hour format
     * The date and time must be indicated in string form in {@code text} the text
     * parameter is supposed to be entered in the below stated format
     * @param text the date and time in String format
     * @return date and time in proper date/time format
     */
    public static LocalDateTime parseDateTime(String text) throws timelineException{
        LocalDateTime date1 = null;
        try{
            text = text.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date1 = LocalDateTime.parse(text,formatter);
        } catch (DateTimeParseException pe){
            throw new timelineException("Fail");
        }
        return date1;
    }

    /**
     * Similar to method #parseDateTime. This method is exclusively used to parse the date and
     * time and the saved text file when loading up Duke.
     * @param text The date and time in String format parsed from text file
     * @return date1 date and time in proper date/time format
     * @throws timelineException Exception where the identifier/separator cannot be found
     * in the text file
     */
    public static LocalDateTime parseFromFile(String text) throws timelineException{
        String type;
        if (text.contains("by")){
            type = "by";
        } else if (text.contains("at")){
            type = "at";
        } else if (text.contains("end")){
            type = "end";
        } else {
            throw new timelineException("Unidentified dateline");
        }
        LocalDateTime date1 = null;
        try{
            by = text.substring(text.indexOf(type) + 4);
            by = by.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date1 = LocalDateTime.parse(by,formatter);
        } catch (DateTimeParseException pe){
            System.out.println("Please try again in format: yyyy-MM-dd HH:mm");
        }
        return date1;
    }

}
