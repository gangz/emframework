package emframework.common.model;

import javax.persistence.MappedSuperclass;

/**
 * The actual "RESOURCE" created by users/customers.
 * They have meaningful impact on customer's business.
 * Usually, they are "ENTITY" in the DDD concept.
 * i.e. we need to track status of them: creation/modification/deletion.
 */
@MappedSuperclass
public abstract class OrderedResource extends Resource {
	private Integer sequence = 0;

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
