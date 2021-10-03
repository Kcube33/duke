public class todo extends TaskList{

    public todo(String description) {
        super(description);
    }

    @Override
    public String printtask(){
        return "[T]" + super.printtask();
    }

}
