package emframework.services.security.authentications;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import emframework.common.data.SessionDTO;

public class UserAuthentication implements Authentication{
	private static final long serialVersionUID = 1L;
	private boolean isAuthenticated;
	private SessionDTO session;

	public UserAuthentication(SessionDTO session) {
		this.session = session;
	}

	@Override
	public String getName() {
		return "name";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set r = new HashSet<>();
		r.add(new UserAuthority(session.getRole()));
		return r;
	}

	@Override
	public Object getCredentials() {
		return new String("credentials");
	}

	@Override
	public Object getDetails() {
		return new String("detail");
	}

	@Override
	public Object getPrincipal() {
		return session;
	}

	@Override
	public boolean isAuthenticated() {
		return true;// isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}
}