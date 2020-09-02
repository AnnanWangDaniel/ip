public class Deadline extends Task {

    protected String by;

    public Deadline(String line) {
        super(line.substring(9,(line.indexOf("/")-1)));
        this.by = line.substring((line.indexOf("/")+4));
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }
    @Override
    public String toString(){
        return "[" + "D" + "]" + super.toString() + " (at: "+ by +")";
    }
} 