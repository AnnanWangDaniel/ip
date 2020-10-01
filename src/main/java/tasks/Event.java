package tasks;

public class Event extends Task{
	protected String period;

	public Event(String description, String period) {
		super(description);
		this.period = period;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + String.format(" (at: %s)",this.getPeriod());
	}
}
