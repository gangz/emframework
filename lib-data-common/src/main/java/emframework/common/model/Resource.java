package emframework.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import emframework.common.data.SessionDTO;

/**
 * The actual "RESOURCE" created by users/customers.
 * They have meaningful impact on customer's business.
 * Usually, they are "ENTITY" in the DDD concept.
 * i.e. we need to track status of them: creation/modification/deletion.
 */
@MappedSuperclass
public abstract class Resource extends GeneralResource {
	@Column(length=60,nullable=false)
	private String creatorId=null;
    private Long creationTime;


	public Resource(){}
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	public void addCreationMark(SessionDTO session) {
		this.setCreationTime(System.currentTimeMillis());
		this.setCreatorId(session.getAccountId());
		if (session.getAccountId()==null) session.setAccountId("unknown");
	}
	
}
