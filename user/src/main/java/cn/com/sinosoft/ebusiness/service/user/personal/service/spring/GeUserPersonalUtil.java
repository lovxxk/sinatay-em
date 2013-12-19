package cn.com.sinosoft.ebusiness.service.user.personal.service.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.UserServiceException;

public class GeUserPersonalUtil {
	/**
	 * 通过反射设置字段的值
	 * 
	 * @param obj
	 * @param field
	 * @param value
	 * @throws Exception
	 */
	public static  void setColumnValue(Object obj, String fieldName, Object value)
			throws Exception {
		Field field = obj.getClass().getDeclaredField(fieldName);
		String methodName = "set"
				+ fieldName.replaceFirst("" + fieldName.charAt(0),
						("" + fieldName.charAt(0)).toUpperCase());
		String fieldType = field.getType().getSimpleName().toLowerCase();
		Method method = obj.getClass().getMethod(methodName, field.getType());

		if ("int".equals(fieldType) || "integer".equals(fieldType)) {
			method.invoke(obj, ((Integer) value).intValue());
		} else if ("short".equals(fieldType)) {
			method.invoke(obj, ((Short) value).shortValue());
		} else if ("byte".equals(fieldType)) {
			method.invoke(obj, ((Byte) value).byteValue());
		} else if ("long".equals(fieldType)) {
			method.invoke(obj, ((Long) value).longValue());
		} else if ("double".equals(fieldType)) {
			method.invoke(obj, ((Double) value).doubleValue());
		} else if ("float".equals(fieldType)) {
			method.invoke(obj, ((Float) value).floatValue());
		} else if ("boolean".equals(fieldType)) {
			method.invoke(obj, ((Boolean) value).booleanValue());
		} else if ("bigdecimal".equals(fieldType)) {
			method.invoke(obj, (BigDecimal)value);
		} else {
			method.invoke(obj, value);
		}
	}
	
	public static Map getGeUserPersonalMap(GeUserPersonal geUserPersonal){
		Map map = new HashMap();
		if(geUserPersonal.getUserID() != null){map.put("userID", geUserPersonal.getUserID());}
		if(geUserPersonal.getUserLevel() != null){map.put("userLevel", geUserPersonal.getUserLevel());}
		if(geUserPersonal.getUserAccount() != null){map.put("userAccount", geUserPersonal.getUserAccount());}
		if(geUserPersonal.getUserName() != null){map.put("userName", geUserPersonal.getUserName());}
		if(geUserPersonal.getPiCardNo() != null){map.put("piCardNo", geUserPersonal.getPiCardNo());}
		if(geUserPersonal.getPwd() != null){map.put("pwd", geUserPersonal.getPwd());}
		if(geUserPersonal.getPwd2() != null){map.put("pwd2", geUserPersonal.getPwd2());}
		if(geUserPersonal.getEmail() != null){map.put("email", geUserPersonal.getEmail());}
		if(geUserPersonal.getMobilePhone() != null){map.put("mobilePhone", geUserPersonal.getMobilePhone());}
		if(geUserPersonal.getIdentifyType() != null){map.put("identifyType", geUserPersonal.getIdentifyType());}
		if(geUserPersonal.getIdentifyNumber() != null){map.put("identifyNumber", geUserPersonal.getIdentifyNumber());}
		if(geUserPersonal.getSex() != null){map.put("sex", geUserPersonal.getSex());}
		if(geUserPersonal.getBirthday() != null){map.put("birthday", geUserPersonal.getBirthday());}
		if(geUserPersonal.getMarriageStatus() != null){map.put("marriageStatus", geUserPersonal.getMarriageStatus());}
		if(geUserPersonal.getAreaCode() != null){map.put("areaCode", geUserPersonal.getAreaCode());}
		if(geUserPersonal.getIndustry() != null){map.put("industry", geUserPersonal.getIndustry());}
		if(geUserPersonal.getIncome() != null){map.put("income", geUserPersonal.getIncome());}
		if(geUserPersonal.getHomePhone() != null){map.put("homePhone", geUserPersonal.getHomePhone());}
		if(geUserPersonal.getOfficePhone() != null){map.put("officePhone", geUserPersonal.getOfficePhone());}
		if(geUserPersonal.getProvinces() != null){map.put("provinces", geUserPersonal.getProvinces());}
		if(geUserPersonal.getCity() != null){map.put("city", geUserPersonal.getCity());}
		if(geUserPersonal.getArea() != null){map.put("area", geUserPersonal.getArea());}
		if(geUserPersonal.getContactAddress() != null){map.put("contactAddress", geUserPersonal.getContactAddress());}
		if(geUserPersonal.getZipCode() != null){map.put("zipCode", geUserPersonal.getZipCode());}
		if(geUserPersonal.getUserSource() != null){map.put("userSource", geUserPersonal.getUserSource());}
		if(geUserPersonal.getHealth() != null){map.put("health", geUserPersonal.getHealth());}
		if(geUserPersonal.getStatus() != null){map.put("status", geUserPersonal.getStatus());}
		if(geUserPersonal.getActiveCode() != null){map.put("activeCode", geUserPersonal.getActiveCode());}
		if(geUserPersonal.getUkey() != null){map.put("ukey", geUserPersonal.getUkey());}
		if(geUserPersonal.getCheckStatus() != null){map.put("checkStatus", geUserPersonal.getCheckStatus());}
		if(geUserPersonal.getIntegral() != null){map.put("integral", geUserPersonal.getIntegral());}
		if(geUserPersonal.getMakeDate() != null){map.put("makeDate", geUserPersonal.getMakeDate());}
		if(geUserPersonal.getFlag() != null){map.put("flag", geUserPersonal.getFlag());}
		if(geUserPersonal.getUserRank() != null){map.put("userRank", geUserPersonal.getUserRank());}
		if(geUserPersonal.getLoginTime() != null){map.put("loginTime", geUserPersonal.getLoginTime());}
		if(geUserPersonal.getLoginNum() != 0){map.put("loginNum", geUserPersonal.getLoginNum());}

		return map;
	}
	
	 /**
	 * 获取重置密码，同时包含数字和字母
	 * @return	初始密码
	 */
	public static String getInitPassword() {
		try {
			String datas = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
			String initPassword = "";
			Random rand = new Random();
			initPassword += datas.charAt(rand.nextInt(10));//保证包含数字
			initPassword += datas.charAt(rand.nextInt(52) + 10);//保证包含字母
			for(int i = 2; i < 6; i++){
				rand = new Random();
				initPassword += datas.charAt(rand.nextInt(62));
			}
			return initPassword;
		} catch (Exception e) {
			throw UserServiceException.newInstanceCode("024", e);
		}
	}
	
	/**生成语句*/
	public static void main(String[] args) {
		Map map = new HashMap();
		GeUserPersonal geUserPersonal = new GeUserPersonal();
		Field[] fieldArr = geUserPersonal.getClass().getDeclaredFields();
		for(int i = 0; i < fieldArr.length; i++){
			Field field = fieldArr[i];
			String fieldName = field.getName();
			String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1)+"()";
			String domainName = "geUserPersonal";
			String mapName = "map";
			System.out.println("if("+domainName+"."+methodName+" != null){"+mapName+".put(\""+fieldName+"\", "+domainName+"."+methodName+");}");
			
			
		}
	}
}
