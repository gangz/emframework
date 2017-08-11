package emframework.services.logon.cache;

import emframework.common.data.SessionDTO;
import emframework.common.exception.UserNotLoggedInException;

public interface SesssionManager {
	SessionDTO getSession(String token) throws UserNotLoggedInException;
	void removeByToken(String token);
	String generateUUID();
	void buildSession(String token, SessionDTO session, Long timeoutHours);
	
	void addKey(String randomNubSix);
	String getCode(String code);
}
