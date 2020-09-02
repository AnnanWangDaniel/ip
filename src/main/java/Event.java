public class Event extends Task{
    protected String at;

    public Event(String line) {
        super(line.substring(6,(line.indexOf("/")-1)));
        this.at = line.substring((line.indexOf("/")+4));
    }

    public void setBy(String by) {
        this.at = by;
    }

    public String getBy() {
        return at;
    }
    @Override
    public String toString(){
        return "[" + "D" + "]" + super.toString() + " (at: "+ at +")";
    }
}