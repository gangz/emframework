package emframework.services.security.authentications;

import org.springframework.security.core.GrantedAuthority;

class UserAuthority implements GrantedAuthority{
	private String role;
	public UserAuthority(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}
}