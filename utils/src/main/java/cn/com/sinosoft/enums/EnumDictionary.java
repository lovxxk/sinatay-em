package cn.com.sinosoft.enums;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.com.sinosoft.lang.ClassUtils;
import cn.com.sinosoft.util.string.StringUtils;

/**
 * POJO��DataDictionary
 */
public class EnumDictionary extends EnumData {

	private static final long serialVersionUID = 1L;


	/** ����ö������ */
	private String dataCode;
	
	/** ������������ */
	private String dataName;

	/** ��������Ӣ������ */
	private String dataENName;
	
	/** ������������ */
	private Integer dataType;
	
	/** ������������ֵ */
	private Integer value;

	/** �����ַ�������ֵ */
	private String stringValue;

	/** �������ݵȼ� */
	private Integer dataLevel;

	/** ������ʾ˳�� */
	private Integer seqIndex;
	
	/** ���Լ�� */
	private Date dateValue;
	
	/** ������С��������ֵ */
	private Integer minIntValue;

	/** ���������������ֵ */
	private Integer maxIntValue;

	/** ������С��������ֵ */
	private Date minDateValue;

	/** ���������������ֵ */
	private Date maxDateValue;

	/** ���Բ���Ա */
	private String operatorID;
	
	/** ���Ժ���ֵ */
	private String coreValue;
	
	/** �����̼�ֵ */
	private String merchantValue;
	
	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	public EnumDictionary() {
	}
	
	public EnumDictionary(String enumName, Integer value) {
		super(enumName);
		this.dataCode = enumName;
		this.value = value;
	}
	
	public EnumDictionary(String enumName, String dataName, Integer value) {
		super(enumName);
		this.dataName = dataName;
		this.dataCode = enumName;
		this.value = value;
	}
	
	public EnumDictionary(String enumName, String dataName, String dataENName,
			Integer value) {
		super(enumName);
		this.dataName = dataName;
		this.dataENName = dataENName;
		this.dataCode = enumName;
		this.value = value;
	}
	
	public EnumDictionary(String enumName, String dataName,
			Integer value, String coreValue) {
		super(enumName);
		this.dataCode = enumName;
		this.dataName = dataName;
		this.value = value;
		this.coreValue = coreValue;
	}
	
	public EnumDictionary(String enumName, String dataName,
			String dataENName, Integer value, String coreValue) {
		super(enumName);
		this.dataCode = enumName;
		this.dataName = dataName;
		this.dataENName = dataENName;
		this.value = value;
		this.coreValue = coreValue;
	}

	public EnumDictionary(String enumName, String dataName,
			Integer value, String coreValue, String merchantValue) {
		super(enumName);
		this.dataCode = enumName;
		this.dataName = dataName;
		this.value = value;
		this.coreValue = coreValue;
		this.merchantValue = merchantValue;
	}
	
	
	public EnumDictionary(String name, String dataCode, String dataName,
			String dataENName, Integer value, String coreValue,
			String merchantValue) {
		super(name);
		this.dataCode = dataCode;
		this.dataName = dataName;
		this.dataENName = dataENName;
		this.value = value;
		this.coreValue = coreValue;
		this.merchantValue = merchantValue;
	}
	
	public EnumDictionary(String name, String dataCode, String dataName,
			Integer value, String coreValue, String merchantValue) {
		super(name);
		this.dataCode = dataCode;
		this.dataName = dataName;
		this.value = value;
		this.coreValue = coreValue;
		this.merchantValue = merchantValue;
	}

	@SuppressWarnings("rawtypes")
	public static EnumData getEnum(Class enumClass, int value) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        }
        List list = EnumData.getEnumList(enumClass);
        for (Iterator it = list.iterator(); it.hasNext();) {
        	EnumDictionary enumeration = (EnumDictionary) it.next();
            if (enumeration.getValue() == value) {
                return enumeration;
            }
        }
        return null;
    }
	 
	@SuppressWarnings("rawtypes")
	public static EnumData getEnumByCoreValue(Class enumClass, String coreValue) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        }
        List list = EnumData.getEnumList(enumClass);
        for (Iterator it = list.iterator(); it.hasNext();) {
        	EnumDictionary enumeration = (EnumDictionary) it.next();
        	if (StringUtils.isNotBlank(coreValue) && StringUtils.isNotBlank(enumeration.getCoreValue())) {
        		 if (coreValue.equalsIgnoreCase(enumeration.getCoreValue())) {
                     return enumeration;
                 }
        	}
           
        }
        return null;
    }
	
	@SuppressWarnings("rawtypes")
	public static EnumData getEnumByMerchantValue(Class enumClass, String merchantValue) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        }
        List list = EnumData.getEnumList(enumClass);
        for (Iterator it = list.iterator(); it.hasNext();) {
        	EnumDictionary enumeration = (EnumDictionary) it.next();
        	if (StringUtils.isNotBlank(merchantValue) && StringUtils.isNotBlank(enumeration.getMerchantValue())) {
        		 if (merchantValue.equalsIgnoreCase(enumeration.getMerchantValue())) {
                     return enumeration;
                 }
        	}
           
        }
        return null;
    }
	
	public int compareTo(Object other) {
        if (other == this) {
            return 0;
        }
        if (other.getClass() != this.getClass()) {
            if (other.getClass().getName().equals(this.getClass().getName())) {
                return value - getValueInOtherClassLoader(other);
            }
            throw new ClassCastException(
                    "Different enum class '" + ClassUtils.getShortClassName(other.getClass()) + "'");
        }
        return value - ((EnumDictionary) other).value;
    }
	
	private int getValueInOtherClassLoader(Object other) {
        try {
        	Class<?>[] parameterTypes = {};
        	Object[] invokeObj = {};
            Method mth = other.getClass().getMethod("getValue", parameterTypes);
            Integer value = (Integer) mth.invoke(other, invokeObj);
            return value.intValue();
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        throw new IllegalStateException("This should not happen");
    }
	
	 public String toString() {
	        if (iToString == null) {
	            String shortName = ClassUtils.getShortClassName(getEnumClass());
	            iToString = shortName + "[" + getName() + "=" + getValue() + "]";
	        }
	        return iToString;
    }
	 
	/**

	/**
	 * �����������͵�getter����
	 */

	public Integer getDataType() {
		return this.dataType;
	}

	/**
	 * �����������͵�setter����
	 */
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	/**
	 * �����������Ƶ�getter����
	 */
	public String getDataName() {
		return this.dataName;
	}

	/**
	 * �����������Ƶ�setter����
	 */
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	/**
	 * ��������Ӣ�����Ƶ�getter����
	 */
	public String getDataENName() {
		return this.dataENName;
	}

	/**
	 * ��������Ӣ�����Ƶ�setter����
	 */
	public void setDataENName(String dataENName) {
		this.dataENName = dataENName;
	}

	/**
	 * ����ö�����Ƶ�getter����
	 */

	public String getDataCode() {
		return this.dataCode;
	}

	/**
	 * ����ö�����Ƶ�setter����
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * ������������ֵ��getter����
	 */
	public Integer getValue() {
		return this.value;
	}

	/**
	 * ������������ֵ��setter����
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * �����ַ�������ֵ��getter����
	 */
	public String getStringValue() {
		return this.stringValue;
	}

	/**
	 * �����ַ�������ֵ��setter����
	 */
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	/**
	 * �������ݵȼ���getter����
	 */
	public Integer getDataLevel() {
		return this.dataLevel;
	}

	/**
	 * �������ݵȼ���setter����
	 */
	public void setDataLevel(Integer dataLevel) {
		this.dataLevel = dataLevel;
	}

	/**
	 * ������ʾ˳���getter����
	 */
	public Integer getSeqIndex() {
		return this.seqIndex;
	}

	/**
	 * ������ʾ˳���setter����
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}
	
	/**
	 * ���Լ���getter����
	 */
	public Date getDateValue() {
		return dateValue;
	}
	
	/**
	 * ���Լ���setter����
	 */
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	/**
	 * ������С��������ֵ��getter����
	 */
	public Integer getMinIntValue() {
		return this.minIntValue;
	}

	/**
	 * ������С��������ֵ��setter����
	 */
	public void setMinIntValue(Integer minIntValue) {
		this.minIntValue = minIntValue;
	}

	/**
	 * ���������������ֵ��getter����
	 */
	public Integer getMaxIntValue() {
		return this.maxIntValue;
	}

	/**
	 * ���������������ֵ��setter����
	 */
	public void setMaxIntValue(Integer maxIntValue) {
		this.maxIntValue = maxIntValue;
	}

	/**
	 * ������С��������ֵ��getter����
	 */
	public Date getMinDateValue() {
		return this.minDateValue;
	}

	/**
	 * ������С��������ֵ��setter����
	 */
	public void setMinDateValue(Date minDateValue) {
		this.minDateValue = minDateValue;
	}

	/**
	 * ���������������ֵ��getter����
	 */
	public Date getMaxDateValue() {
		return this.maxDateValue;
	}

	/**
	 * ���������������ֵ��setter����
	 */
	public void setMaxDateValue(Date maxDateValue) {
		this.maxDateValue = maxDateValue;
	}
	
	/** 
	 * ���Ժ���ֵ��getter����
	 */
	public String getCoreValue() {
		return coreValue;
	}
	
	/** 
	 * ���Ժ���ֵ��setter����
	 */
	public void setCoreValue(String coreValue) {
		this.coreValue = coreValue;
	}

	/** 
	 * �����̼�ֵ��setter����
	 */
	public String getMerchantValue() {
		return merchantValue;
	}
	
	/** 
	 * �����̼�ֵ��getter����
	 */
	public void setMerchantValue(String merchantValue) {
		this.merchantValue = merchantValue;
	}

	

	/**
	 * ���Բ���Ա��getter����
	 */
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ���Ա��setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * ���Դ���ʱ���getter����
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
