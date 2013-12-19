package cn.com.sinosoft.ebusiness.payment.service.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class CurrentDatePropertyEdit implements FactoryBean<Date>{

	@Override
	public Date getObject() throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH");
		
		Date date2 = sdf2.parse(sdf2.format(date));
		if (date.getTime() <= new Date(date2.getTime() + 30*60*1000).getTime()) {
			date = new Date(date2.getTime() + 30*60*1000);
		} else {
			date = new Date(date2.getTime() + 60*60*1000);
		}
		return date;
	}

	@Override
	public Class<?> getObjectType() {
		return Date.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


}
