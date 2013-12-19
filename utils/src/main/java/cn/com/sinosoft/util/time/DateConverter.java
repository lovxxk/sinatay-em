package cn.com.sinosoft.util.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import ognl.DefaultTypeConverter;

public class DateConverter extends DefaultTypeConverter {
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy-MM-dd HH"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy-MM"),
			new SimpleDateFormat("yyyy")
	}; // ֧��ת�������ڸ�ʽ

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Date.class) { // �������������ύʱ������String to Date��ת��
			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0];// ��ȡ���ڵ��ַ���
			for (DateFormat format : ACCEPT_DATE_FORMATS) {
				try {
					return format.parse(dateString);// ��������֧�ָ�ʽ������ת��
				} catch (Exception e) {
					continue;
				}
			}
			return null;
		} else if (toType == String.class) { // ����������������ʱ������Date to String������ת��
			Date date = (Date) value;
			return new SimpleDateFormat("yyyy-MM-dd").format(date);// ����ĸ�ʽ��yyyy-MM-dd
		}
		return null;
	}
}
