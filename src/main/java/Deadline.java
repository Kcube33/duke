public class Deadline extends TaskList{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String printtask(){
        return "[D]" + super.printtask() + " (by: " + by + ")";
    }
}
