package cn.com.sinosoft.enums;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EnumDataUtils {
	  /**
     * Public constructor. This class should not normally be instantiated.
     * @since 2.0
     */
    public EnumDataUtils() {
      super();
    }

    /**
     * <p>Gets an <code>Enum</code> object by class and name.</p>
     * 
     * @param enumClass  the class of the <code>Enum</code> to get
     * @param name  the name of the Enum to get, may be <code>null</code>
     * @return the enum object
     * @throws IllegalArgumentException if the enum class is <code>null</code>
     */
	public static EnumData getEnum(Class enumClass, String name) {
        return EnumData.getEnum(enumClass, name);
    }

    /**
     * <p>Gets a <code>ValuedEnum</code> object by class and value.</p>
     * 
     * @param enumClass  the class of the <code>Enum</code> to get
     * @param value  the value of the <code>Enum</code> to get
     * @return the enum object, or null if the enum does not exist
     * @throws IllegalArgumentException if the enum class is <code>null</code>
     */
    public static ValuedEnumData getEnum(Class enumClass, int value) {
        return (ValuedEnumData) ValuedEnumData.getEnum(enumClass, value);
    }
    
    /**
     * 
     * @param enumClass
     * @param value
     * @return
     */
    public static EnumDictionary getEnumDictionaryByCoreValue(Class enumClass ,String coreValue) {
    	return (EnumDictionary) EnumDictionary.getEnumByCoreValue(enumClass, coreValue);
    }
    
    /**
     * 
     * @param enumClass
     * @param value
     * @return
     */
    public static EnumDictionary getEnumDictionaryByMerchantValue(Class enumClass ,String merchantValue) {
    	return (EnumDictionary) EnumDictionary.getEnumByMerchantValue(enumClass, merchantValue);
    }
    /**
     * 
     * @param enumClass
     * @param value
     * @return
     */
    public static EnumDictionary getEnumDictionaryByValue(Class enumClass ,int value) {
    	return (EnumDictionary) EnumDictionary.getEnum(enumClass, value);
    }
    
    
    /**
     * <p>Gets the <code>Map</code> of <code>Enum</code> objects by
     * name using the <code>Enum</code> class.</p>
     *
     * <p>If the requested class has no enum objects an empty
     * <code>Map</code> is returned. The <code>Map</code> is unmodifiable.</p>
     * 
     * @param enumClass  the class of the <code>Enum</code> to get
     * @return the enum object Map
     * @throws IllegalArgumentException if the enum class is <code>null</code>
     * @throws IllegalArgumentException if the enum class is not a subclass
     *  of <code>Enum</code>
     */
    public static Map getEnumMap(Class enumClass) {
        return EnumData.getEnumMap(enumClass);
    }
    
    /***
     * 
     * @param enumClass
     * @return
     */
	public static List<String> getEnumNameList(Class enumClass) {
    	List<EnumData> enumList = EnumData.getEnumList(enumClass);
    	List<String> enumNameList = new ArrayList<String>();
    	for (EnumData enumData:enumList) {
    		enumNameList.add(enumData.getName());
    	}
		return enumNameList;
	}
    
    /**
     * <p>Gets the <code>List</code> of <code>Enum</code> objects using
     * the <code>Enum</code> class.</p>
     *
     * <p>The list is in the order that the objects were created
     * (source code order).</p>
     *
     * <p>If the requested class has no enum objects an empty
     * <code>List</code> is returned. The <code>List</code> is unmodifiable.</p>
     * 
     * @param enumClass  the class of the Enum to get
     * @return the enum object Map
     * @throws IllegalArgumentException if the enum class is <code>null</code>
     * @throws IllegalArgumentException if the enum class is not a subclass
     *  of <code>Enum</code>
     */
    public static List getEnumList(Class enumClass) {
        return EnumData.getEnumList(enumClass);
    }

    /**
     * <p>Gets an <code>Iterator</code> over the <code>Enum</code> objects
     * in an <code>Enum</code> class.</p>
     *
     * <p>The iterator is in the order that the objects were created
     * (source code order).</p>
     *
     * <p>If the requested class has no enum objects an empty
     * <code>Iterator</code> is returned. The <code>Iterator</code>
     * is unmodifiable.</p>
     * 
     * @param enumClass  the class of the <code>Enum</code> to get
     * @return an <code>Iterator</code> of the <code>Enum</code> objects
     * @throws IllegalArgumentException if the enum class is <code>null</code>
     * @throws IllegalArgumentException if the enum class is not a subclass of <code>Enum</code>
     */
    public static Iterator iterator(Class enumClass) {
        return EnumData.getEnumList(enumClass).iterator();
    }
    
}
