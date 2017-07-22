package emframework.common.exception;

public class UserNotLoggedInException extends ApplicationException {

	public UserNotLoggedInException(String token) {
		super("user have not logged in : token =" + token);
	}

	private static final long serialVersionUID = 1L;

}
