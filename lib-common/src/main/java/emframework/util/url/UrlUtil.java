package emframework.util.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlUtil {
	public static String removeUrlParameters(String oldUrl) {
		int l = oldUrl.indexOf('?');
		if (l <= 0)
			return oldUrl;
		String url = oldUrl.substring(0, l);
		return url;
	}
	
	public static boolean isHostOnlyUrl(String url) {
		if (url==null) return true;
		if (url.trim().equals("")) return true;
		String regex = "(http://)?[0-9a-zA-Z-\\.]*(/)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		if (matcher.matches()) return true;
		return false;
	}
}
