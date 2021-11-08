package duke;

import duke.tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.exception.timelineException;

/**
 * Reads the contents from the text file and populates the ArrayList
 */
public class readFile {

    /**
     * Searches the following path for the text tile.
     * @return selected file from hard disk
     */
    private static File getFile(){
        String home = System.getProperty("user.home") + "\\Documents\\duke.txt";
        File f = null;
        try {
            f = new File(home);
        } catch (NullPointerException e) {
            System.out.println("File not found");
        }
        return f;
    }

    /**
     * Takes the created file in {@code pFile} and reads the content inside. This method will
     * take each line and pass it #fileTasks to determine the type of task.
     * @param pFile the existing file that was created from the text file on hard disk
     * @param all_tasks ArrayList of tasks for Duke
     * @see readFile#fileTasks(ArrayList, String)
     */
    private static void PrintFile(File pFile, ArrayList<TaskList> all_tasks) {
        Scanner s;
        try {
            s = new Scanner(pFile);
            while (s.hasNext()) {
                String oneTask = s.nextLine();
                fileTasks(all_tasks,oneTask);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("File not found. New file will be created in Documents Folder " +
                    "under duke.txt");
        }
    }

    /**
     * Determines the type of task from {@code ot} and its details and adds it into ArrayList
     * @param all_tasks
     * @param ot String of text obtained from #PrintFile
     */
    private static void fileTasks(ArrayList<TaskList> all_tasks, String ot) {
        String[] expected = ot.split("\\|");
        for (int i = 0; i < 2; i++){
            expected[i] = expected[i].replaceAll("\\s","");
        }
        for (int i = 2; i < expected.length; i++){
            expected[i] = expected[i].trim();
        }
        try{
            if (expected[0].equals("T")){
                all_tasks.add(new Todo(expected[2]));
            } else if (expected[0].equals("E")){
                all_tasks.add(new Events(expected[2],Parser.parseFromFile(expected[3]),
                        Parser.parseFromFile(expected[4])));
            } else if (expected[0].equals("D")){
                all_tasks.add(new Deadline(expected[2],Parser.parseFromFile(expected[3])));
            }
        } catch (timelineException pe){
            System.out.println("Please check file entry " + ot);
            System.out.println("Format must be in: \"by/at/end yyyy-MM-dd HH:mm\"");
        }

        boolean D;
        if (expected[1].equals("1")){
            all_tasks.get(all_tasks.size()-1).isDone = true;
        }
    }

    /**
     * This method checks for an existing file in the hardtask. When the file is found, ArrayList
     * will be populated with the tasks from the text file. If not, it will prompt the user to
     * create a new lists of tasks
     * @param all_tasks ArrayList of tasks to be populated and used for Duke
     */
    public static void initialise(ArrayList<TaskList> all_tasks) {
        File newFile = getFile();
        if (newFile == null){
            System.out.println("Please proceed to create new list");
            return;
        }
        PrintFile(newFile, all_tasks);
    }
}
