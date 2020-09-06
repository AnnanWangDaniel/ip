public class Deadline extends Task {
    public static int DEADLINE_LENGTH = 9;
    public static int BY_LENGTH = 4;
    protected String by;

    public Deadline(String line) {
        super(line.substring(DEADLINE_LENGTH,(line.indexOf("/")-1)));
        this.by = line.substring((line.indexOf("/") + BY_LENGTH));
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