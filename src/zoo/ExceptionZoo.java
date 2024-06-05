package zoo;

public class ExceptionZoo extends Exception {

	private static final long serialVersionUID = 1L;
	private String theIssueTitle;
	private String theIssueDescription;

	public ExceptionZoo(String theIssueTitle, String theIssueDescription) {
		this.theIssueTitle = theIssueTitle;
		this.theIssueDescription = theIssueDescription;
	}

	public ExceptionZoo(String theIssueTitle) {
		this(theIssueTitle, "Put The Right Input");
	}

	public String getTheIssueTitle() {
		return theIssueTitle;
	}

	public String getTheIssueDescription() {
		return theIssueDescription;
	}
}
