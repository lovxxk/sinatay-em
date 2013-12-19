package test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Properties;

import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsureInformBook;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.util.encode.JsonBinder;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Class<?> clz = QuoteInsureInformBook.class;
		Field[] fields = clz.getDeclaredFields();
		// System.out.println(fields.length);

		StringBuilder str = new StringBuilder();
		str.append("var JSONObj = \r");
		str.append("\t{\r");
		for (Field field : fields) {
			str.append("\t\t" + field.getName() + ":\"\",\r");
//			 System.out.println(field.getName()+", "+field.getGenericType().toString());
		}
		Method[] methods = Risk.class.getMethods();
		for(Method method : methods){
//			System.out.println(method.getName());
			if(method.getName().substring(0,3).equals("set")){
//				System.out.println("quoteInsureInformBook."+method.getName()+"();");
			}
		}
		String newStr = str.toString().substring(0, str.toString().lastIndexOf(","));
		newStr += "\r\t};";
		 System.out.println(newStr);

		JsonBinder jsonBinder = JsonBinder.buildNonNullBinder();
		jsonBinder.setDateFormat("yyyy-MM-dd");
		InsureFlowDto insureFlowDto = new InsureFlowDto();
		insureFlowDto.setAgeInterval("1-60");
		// System.out.println(jsonBinder.toJson(insureFlowDto));
	}

	/**
	 * 
	 * java反射bean的get方法
	 * 
	 * 
	 * 
	 * @param objectClass
	 * 
	 * @param fieldName
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static Method getGetMethod(Class objectClass, String fieldName) {

		StringBuffer sb = new StringBuffer();

		sb.append("get");

		sb.append(fieldName.substring(0, 1).toUpperCase());

		sb.append(fieldName.substring(1));

		try {

			return objectClass.getMethod(sb.toString());

		} catch (Exception e) {

		}

		return null;

	}

	/**
	 * 
	 * java反射bean的set方法
	 * 
	 * 
	 * 
	 * @param objectClass
	 * 
	 * @param fieldName
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static Method getSetMethod(Class objectClass, String fieldName) {

		try {

			Class[] parameterTypes = new Class[1];

			Field field = objectClass.getDeclaredField(fieldName);

			parameterTypes[0] = field.getType();

			StringBuffer sb = new StringBuffer();

			sb.append("set");

			sb.append(fieldName.substring(0, 1).toUpperCase());

			sb.append(fieldName.substring(1));

			Method method = objectClass.getMethod(sb.toString(), parameterTypes);

			return method;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	/**
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath(final String dir) {
		try {
			String path = Test.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}

			Properties props = System.getProperties(); // 获得系统属性集
			String osName = props.getProperty("os.name"); // 操作系统名称
			if (osName.startsWith("Windows")) {
				path = props.getProperty("user.dir").replace("\\", "/") + "/src/main/webapp";
			} else {
				path += dir;
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
