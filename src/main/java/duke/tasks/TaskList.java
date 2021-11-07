package duke.tasks;

public class TaskList {

    protected String task;
    public Boolean isDone;
    protected String type = null;
    protected String by = null;

    TaskList(String task){
        this.task = task;
        isDone = false;
    }

    public void done(){
        this.isDone = true;
        System.out.println("_______________________\n Nice! I've marked this task as done:");
        System.out.println("[X] " + this.task + "\n_______________________");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTask(){
        return this.task;
    }

    public String getType(){ return this.type;}

    public String getBy(){ return this.by;}

    public String printtask(){
        return ("[" + getStatusIcon() + "] " + getTask());
    }

}
