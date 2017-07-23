package emframework.services.security;

import com.google.common.base.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import emframework.services.security.authentications.AnonymousAuthentication;

public class AuthenticationFilter extends GenericFilterBean {

	private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = asHttp(request);
		HttpServletResponse httpResponse = asHttp(response);
		Optional<String> token = Optional.fromNullable(httpRequest.getParameter("token"));
		String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
		try {
			if (resourcePath.contains("/accounts/login") || resourcePath.contains("/public/"))
				processLoginAuth(resourcePath);
			else if (token.isPresent()) {
				processTokenAuthentication(token);
			}
			logger.debug("AuthenticationFilter is passing request down the filter chain");
			chain.doFilter(request, response);
		} catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
			SecurityContextHolder.clearContext();
			logger.error("Internal authentication service exception", internalAuthenticationServiceException);
			httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (AuthenticationException authenticationException) {
			SecurityContextHolder.clearContext();
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
		}
	}

	private HttpServletRequest asHttp(ServletRequest request) {
		return (HttpServletRequest) request;
	}

	private HttpServletResponse asHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}

	private void processLoginAuth(String resourcePath) {
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());
	}

	private void processTokenAuthentication(Optional<String> token) {
		Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
	}

	private Authentication tryToAuthenticateWithToken(Optional<String> token) {
		PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token,
				null);
		return tryToAuthenticate(requestAuthentication);
	}

	private Authentication tryToAuthenticate(Authentication requestAuthentication) {
		Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			throw new InternalAuthenticationServiceException(
					"Unable to authenticate Domain User for provided credentials");
		}
		logger.debug("User successfully authenticated");
		return responseAuthentication;
	}
}
