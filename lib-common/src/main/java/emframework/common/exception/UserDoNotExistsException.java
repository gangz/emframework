package emframework.common.exception;

public class UserDoNotExistsException extends ApplicationException {
	public UserDoNotExistsException(String userId){
		super("no such user " + userId);
	}
}
