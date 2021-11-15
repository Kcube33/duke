package duke;

import duke.tasks.*;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<TaskList> storage = new ArrayList<>();

    /**
     * Initialises duke
     * Searches for existing file on hard disk and loads the tasks into Duke if any
     */
    public static void main(String[] args) {
        readFile.initialise(storage);
        ChatBot.startDuke(storage);
    }
}
