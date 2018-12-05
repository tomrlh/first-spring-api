package socialbooksapi.services.exceptions;

public class ExistingAuthorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExistingAuthorException (String message) {
		super(message);
	}
	
	public ExistingAuthorException (String message, Throwable reason) {
		super(message, reason);
	}
}
