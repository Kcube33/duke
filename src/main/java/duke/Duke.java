package duke;

import duke.tasks.*;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<TaskList> storage = new ArrayList<>();


    public static void main(String[] args) {
        readFile.initialise(storage);
        ChatBot a = new ChatBot(storage);
    }
}
