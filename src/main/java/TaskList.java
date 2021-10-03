public class TaskList {

    protected String task;
    protected Boolean isDone;

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

}
