package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GeneratorTransSerialNumber {

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmsssss");

	private static final DecimalFormat decimalFormat = new DecimalFormat("000");
	
	private static final Random random = new Random();

	public static synchronized String generatorTransSerialNumber() {
		return dateFormat.format(new Date())
				+ decimalFormat.format(random.nextInt(100));
	}
}
