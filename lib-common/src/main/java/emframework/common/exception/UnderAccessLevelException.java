package emframework.common.exception;

public class UnderAccessLevelException extends ApplicationException {
	public UnderAccessLevelException(){
		super("user do not have enough right to access the resource");
	}
}
