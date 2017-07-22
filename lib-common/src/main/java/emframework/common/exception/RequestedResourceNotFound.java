package emframework.common.exception;

public class RequestedResourceNotFound extends ApplicationException {
	public RequestedResourceNotFound (String msg) {
		super("the requested resource " + msg+ " do not exists");
	}
}
