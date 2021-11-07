package duke;

import duke.tasks.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class WriteToFile {

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
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void clearContents(File f) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsolutePath());
        fw.write("");
        fw.close();
    }

    private static void addToFile(File f, TaskList oneTask) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
        Integer D = oneTask.isDone ? 1:0;
        String temp = " | " + D+ " | ";
        if (oneTask.getType().equals("T")){
            fw.write(oneTask.getType() + temp + oneTask.getTask() +  System.lineSeparator());
        } else{
            fw.write(oneTask.getType() + temp + oneTask.getTask() + " | " + oneTask.getBy() +
                    System.lineSeparator());
        }
        fw.close();
    }
}
