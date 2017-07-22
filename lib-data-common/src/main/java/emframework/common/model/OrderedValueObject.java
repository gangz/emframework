package emframework.common.model;

import javax.persistence.MappedSuperclass;

/**
 * The actual "RESOURCE" created by users/customers.
 * They have meaningful impact on customer's business.
 * Usually, they are "ENTITY" in the DDD concept.
 * i.e. we need to track status of them: creation/modification/deletion.
 */
@MappedSuperclass
public abstract class OrderedValueObject extends ValueObject {
	private Byte sequence = Byte.MAX_VALUE;

	public Byte getSequence() {
		return sequence;
	}

	public void setSequence(Byte sequence) {
		this.sequence = sequence;
	}

}
