package cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GeneratorGeDirectoryEId {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	private static final DecimalFormat decimalFormat = new DecimalFormat("000");
	
	private static final Random random = new Random();
	
	public static synchronized String generatorProductEId(final String businessArea) {
		return "G" + businessArea + dateFormat.format(new Date()) + decimalFormat.format(random.nextInt(100));
	}
	
}
