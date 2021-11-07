package duke.tasks;

public class Deadline extends TaskList{


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String printtask(){
        return "[D]" + super.printtask() + " (by: " + by + ")";
    }
}
