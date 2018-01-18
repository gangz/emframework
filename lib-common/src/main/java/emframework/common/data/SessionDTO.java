package emframework.common.data;

import java.io.Serializable;


public class SessionDTO implements Serializable {
	private static final long serialVersionUID = -2437186301461673321L;
	private String token = null;
	private String userId = null;
	private String role = null;
	private String accountId = null;
	private String[] roles = new String[0];
	public SessionDTO() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getUserId() {
		return userId;
	}

	public void setId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SessionDTO [token=" + token + ", userId=" + userId + ", role=" + role + "]";
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
