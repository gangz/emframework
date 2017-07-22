package emframework.services.logon.cache;

import emframework.common.data.SessionDTO;
import emframework.common.exception.UserNotLoggedInException;

public interface SesssionManager {
	SessionDTO getSession(String token) throws UserNotLoggedInException;
	SessionDTO buildSession(String token, Object u, Long timeoutHours);
	void removeByToken(String token);
	String generateUUID();
}
