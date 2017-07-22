package emframework.util.date;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.format.number.NumberStyleFormatter;

public class TestDateUtil {
	private int orderSeq;

	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dd = sdf.format(new Date());
		DecimalFormat df = new DecimalFormat("0000");
		System.out.println(generateOrderNumber());
	}

	private String generateOrderNumber() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String no = sdf.format(new Date());
		DecimalFormat df = new DecimalFormat("0000");
		no +=df.format(this.orderSeq++);
		this.orderSeq%=10000;
		no+=UUID.randomUUID().toString().substring(0,5).toUpperCase();
		return no;
	}
}
