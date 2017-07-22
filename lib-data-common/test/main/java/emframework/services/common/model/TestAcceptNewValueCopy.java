package emframework.services.common.model;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestAcceptNewValueCopy {

	class Model extends GeneralResource{
		boolean isShared = true;
		
		@AcceptNewValue
		boolean autoUpdate = true;
	}
	@Test
	public void should_not_update_fields_without_annotation() throws IllegalArgumentException, IllegalAccessException {
		Model m1 = new Model();
		Model m2 = new Model();
		m2.isShared = false;
		assertEquals(true, m1.isShared);
	}
	
	@Test
	public void should_update_fields_with_annotation() throws IllegalArgumentException, IllegalAccessException {
		Model m1 = new Model();
		Model m2 = new Model();
		m1.autoUpdate = false;
		m2.updateWithNewValue(m1);
		assertEquals(false, m1.autoUpdate);
	}

}
