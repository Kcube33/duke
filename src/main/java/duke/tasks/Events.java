package duke.tasks;

import java.time.LocalDateTime;

public class Events extends TaskList{


    public Events(String description, LocalDateTime by, LocalDateTime end) {
        super(description);
        this.by = by;
        this.type= "E";
        this.end = end;
    }

    @Override
    public String printtask(){
        return "[E]" + super.printtask() + " (at: " + getBy() + "" +
                " End: " + getEnd() +")";
    }

}
