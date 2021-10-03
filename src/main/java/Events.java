public class Events extends TaskList{

    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String printtask(){
        return "[E]" + super.printtask() + " (at: " + at + ")";
    }

}
