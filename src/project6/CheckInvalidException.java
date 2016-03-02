package project6;

@SuppressWarnings("serial")
public class CheckInvalidException extends Exception {
	String offending_class;
	
	public CheckInvalidException(String className) {
		offending_class = className;
	}
	
	public CheckInvalidException() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Still in check. " + offending_class;
	}
}
