package emframework.common.data;

import java.io.Serializable;


public class SessionDTO implements Serializable {
	private static final long serialVersionUID = -2437186301461673321L;
	private String token = null;
	private String userId = null;
	private String role = null;

	
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
	
}
