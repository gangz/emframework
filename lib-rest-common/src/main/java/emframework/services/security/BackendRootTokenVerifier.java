package emframework.services.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import emframework.services.security.authentications.RootAuthentication;

@Component
public class BackendRootTokenVerifier {
	@Value("${security.backend.root.token}")
	String backEndToken;
    private static final Logger logger = LoggerFactory.getLogger(BackendRootTokenVerifier.class);
    public boolean contains(String token) {
    	logger.info("logged in as ROOT!!!");
    	if (backEndToken==null) return false;
    	return token.equals(backEndToken);
    }

    public Authentication retrieve(String token) {
    	logger.info("queried roles as ROOT!!!");
    	if (contains(token))
    		return (Authentication) new RootAuthentication();
    	return null;
    }
}
