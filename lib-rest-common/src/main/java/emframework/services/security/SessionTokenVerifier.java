package emframework.services.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import emframework.common.exception.UserNotLoggedInException;
import emframework.services.logon.cache.SesssionManager;
import emframework.services.security.authentications.UserAuthentication;

@Component
public class SessionTokenVerifier {
	
    private static final Logger logger = LoggerFactory.getLogger(SessionTokenVerifier.class);
	@Autowired
	SesssionManager sessionManager;
    public boolean contains(String token) {
        try {
			return sessionManager.getSession(token)!=null;
		} catch (UserNotLoggedInException e) {
			return false;
		}
    }

    public Authentication retrieve(String token) {
        try {
			return (Authentication) new UserAuthentication(sessionManager.getSession(token));
		} catch (UserNotLoggedInException e) {
			return null;
		}
    }
}
