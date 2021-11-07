package duke.tasks;

public class Todo extends TaskList{

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String printtask(){
        return "[T]" + super.printtask();
    }

}
