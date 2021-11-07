package duke.tasks;

public class Events extends TaskList{


    public Events(String description, String by) {
        super(description);
        this.by = by;
        this.type= "E";
    }

    @Override
    public String printtask(){
        return "[E]" + super.printtask() + " (at: " + by + ")";
    }

}
