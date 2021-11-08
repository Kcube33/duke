package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Superclass for all types of tasks
 */
public class TaskList {

    protected String task;
    public Boolean isDone;
    protected String type = null;
    protected LocalDateTime by = null;
    protected LocalDateTime end = null;

    TaskList(String task){
        this.task = task;
        isDone = false;
    }

    /**
     * Sets the completion status to done
     */
    public void done(){
        if (this.isDone == true){
            System.out.println("_______________________\n This task has already been completed");
            System.out.println("[X] " + this.task + "\n_______________________");
        } else{
            this.isDone = true;
            System.out.println("_______________________\n Nice! I've marked this task as done:");
            System.out.println("[X] " + this.task + "\n_______________________");
        }

    }

    /** returns the completion status */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** returns the task description */
    public String getTask(){
        return this.task;
    }

    /** returns the type of task in short form */
    public String getType(){ return this.type;}

    /** returns the completion/happening date and time in 12-hour format */
    public String getBy(){
        return this.by.getDayOfWeek() + " " +
                this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
    }

    /** returns the supposed end date of task in 12-hour format*/
    public String getEnd(){
        return this.end.getDayOfWeek() + " " +
                this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
    }

    /** returns the completion/happening date and time in 24-hour format */
    public String getbyRAW(){
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /** returns the supposed end date of task in 24-hour format*/
    public String getEndRAW(){
        return this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /** returns the task description and completion status in String form */
    public String printtask(){
        return ("[" + getStatusIcon() + "] " + getTask());
    }

}
