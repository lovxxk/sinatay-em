package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.riskAndKindManage.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.util.PropertyFilter;
/**
 * ����json�õ��Ĺ�����
 */
public class PropertyFilterImpl implements PropertyFilter {
	/**
	 * �����˿�ֵ��field����
	 */
	private static Set<String> fieldSet = new HashSet<String>();

	public static Set<String> getFieldSet() {
		return fieldSet;
	}

	public static void setFieldSet(Set<String> fieldSet) {
		PropertyFilterImpl.fieldSet = fieldSet;
	}

	static{
		fieldSet.add("test");
	}

	public boolean apply(Object arg0, String arg1, Object arg2) {
		if(fieldSet!=null&&fieldSet.contains(arg1)){
			return false;
		}
		if(arg2==null){
			return true;
		}
		if(arg2 instanceof List){
			if(((List<?>)arg2).isEmpty()){
				return true;
			}
		}
		return false;
	}

}
