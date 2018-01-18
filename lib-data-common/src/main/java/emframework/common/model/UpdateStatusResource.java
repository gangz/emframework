package emframework.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import emframework.common.data.SessionDTO;

@MappedSuperclass
public class UpdateStatusResource extends Resource{
    private Long lastUpdateTime;
	@Column(length=60,nullable=false)
	private String lastUpdatorId=null;
	public void addUpdateMark(SessionDTO session){
		this.setLastUpdateTime(System.currentTimeMillis());
		this.setLastUpdatorId(session.getAccountId());
	}
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLastUpdatorId() {
		return lastUpdatorId;
	}
	public void setLastUpdatorId(String lastUpdatorId) {
		this.lastUpdatorId = lastUpdatorId;
	}	
}
