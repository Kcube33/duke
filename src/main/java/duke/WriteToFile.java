package duke;

import duke.tasks.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

/** Writes outstanding tasks to text file on hard disk */
public class WriteToFile {

    /**
     * This method locates if there is an existing text file on the hard disk. If there
     * is no such file, this method will create one.
     * Each outstanding task is written through {@link #addToFile(File, TaskList)}
     * @param all_tasks
     *
     */
    public static void startWriting(ArrayList<TaskList> all_tasks){
        String home = System.getProperty("user.home") + "\\Documents\\duke.txt";
        File f = null;
        try {
            f = new File(home);
            clearContents(f);
            for(TaskList task:all_tasks){
                addToFile(f,task);
            }
        } catch (NullPointerException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("No Existing File. New file will be created at " + home);
        }
    }

    private static void clearContents(File f) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsolutePath());
        fw.write("");
        fw.close();
    }

    /** append each new task to the text file */
    private static void addToFile(File f, TaskList oneTask) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
        Integer D = oneTask.isDone ? 1:0;
        String temp = " | " + D+ " | ";
        if (oneTask.getType().equals("T")){
            fw.write(oneTask.getType() + temp + oneTask.getTask() +  System.lineSeparator());
        } else if (oneTask.getType().equals("D")){
            fw.write(oneTask.getType() + temp + oneTask.getTask() + " | by: " + oneTask.getbyRAW() +
                    System.lineSeparator());
        } else if (oneTask.getType().equals("E")){
            fw.write(oneTask.getType() + temp + oneTask.getTask() + " | at: " + oneTask.getbyRAW() +
                     " | end: " + oneTask.getEndRAW() + System.lineSeparator());
        }
        fw.close();
    }
}
