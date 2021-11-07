package duke;

import duke.tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile {

    public static File getFile(){
        String home = System.getProperty("user.home") + "\\Documents\\duke.txt";
        File f = null;
        try {
            f = new File(home);
        } catch (NullPointerException e) {
            System.out.println("File not found");
        }
        return f;
    }

    public static void PrintFile(File pFile, ArrayList<TaskList> all_tasks) {
        Scanner s;
        try {
            s = new Scanner(pFile);
            while (s.hasNext()) {
                String oneTask = s.nextLine();
                fileTasks(all_tasks,oneTask);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("File not found" + e);
        }
    }

    public static void fileTasks(ArrayList<TaskList> all_tasks, String ot) {
        String[] expected = ot.split("\\|");
        for (int i = 0; i < 2; i++){
            expected[i] = expected[i].replaceAll("\\s","");
        }
        for (int i = 2; i < expected.length; i++){
            expected[i] = expected[i].trim();
        }
        if (expected[0].equals("T")){
            all_tasks.add(new Todo(expected[2]));
        } else if (expected[0].equals("E")){
            all_tasks.add(new Events(expected[2],expected[3]));
        } else {
            all_tasks.add(new Deadline(expected[2],expected[3]));
        }
        boolean D;
        if (expected[1].equals("1")){
            all_tasks.get(all_tasks.size()-1).isDone = true;
        }
    }

    public static void initialise(ArrayList<TaskList> all_tasks) {
        File newFile = getFile();
        if (newFile == null){
            System.out.println("Please proceed to create new list");
            return;
        }
        PrintFile(newFile, all_tasks);
    }
}
