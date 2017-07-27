package emframework.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * The actual "RESOURCE" created by users/customers.
 * They have meaningful impact on customer's business.
 * Usually, they are "ENTITY" in the DDD concept.
 * i.e. we need to track status of them: creation/modification/deletion.
 */
@MappedSuperclass
public abstract class OrderedValueObject extends ValueObject {
	@Column(nullable=false)
	private Integer sequence = Integer.MAX_VALUE;

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
