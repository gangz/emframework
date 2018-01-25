package emframework.util.url;

import static org.junit.Assert.*;

import org.junit.Test;

public class UrlUtilTest {

	@Test
	public void withAdditionalPath(){
		assertFalse(UrlUtil.isHostOnlyUrl("http://abc.ab-c/aaa"));
	}


	@Test
	public void onlyHost(){
		assertTrue(UrlUtil.isHostOnlyUrl("http://picture.cby100.cn/"));
	}

	@Test
	public void onlyHostWithoutProtocol(){
		assertTrue(UrlUtil.isHostOnlyUrl("abc.ab-c/"));
	}

	
}
