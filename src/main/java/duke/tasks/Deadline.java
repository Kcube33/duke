package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends TaskList{


    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String printtask(){
        return "[D]" + super.printtask() + " (by: " + getBy() + ")";
    }
}
