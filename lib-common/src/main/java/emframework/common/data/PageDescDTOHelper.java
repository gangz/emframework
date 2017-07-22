package emframework.common.data;

public class PageDescDTOHelper {
	public static void fillPage(PageDescDTO d, long l, Integer offset, Integer limit) {
		Integer ll = new Long(l).intValue();
		d.setCount(ll);
		d.setOffset(offset);
		d.setLimit(limit);
	}
}
