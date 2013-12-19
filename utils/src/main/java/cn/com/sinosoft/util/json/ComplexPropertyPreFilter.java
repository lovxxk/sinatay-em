package cn.com.sinosoft.util.json;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
        
/** 
 * @author ������2o13 <br> 
 * @Comment �� fastjson ������͵�����ѡ�������(���Կ�㼶) <br> 
 */
public class ComplexPropertyPreFilter implements PropertyPreFilter { 
            
    private Map<Class<?>, String[]> includes = new HashMap(); 
    private Map<Class<?>, String[]> excludes = new HashMap(); 
            
    static { 
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask(); 
    } 
            
    public ComplexPropertyPreFilter() { 
                
    } 
            
    public ComplexPropertyPreFilter(Map<Class<?>, String[]> includes) { 
        super(); 
        this.includes = includes; 
    } 
            
    public boolean apply(JSONSerializer serializer, Object source, String name) { 
                
        //����Ϊ�ա�ֱ�ӷ��� 
        if (source == null) { 
            return true; 
        } 
                
        // ��ȡ��ǰ��Ҫ���л��Ķ��������� 
        Class<?> clazz = source.getClass(); 
                
        // �������еĶ���Ѱ����Ҫ���˵Ķ��󣬿�����߲��Ҳ㼶 
        // �ҵ�����Ҫ�����л������� 
        for (Map.Entry<Class<?>, String[]> item : this.excludes.entrySet()) { 
            // isAssignableFrom()�������ж����ͼ��Ƿ��м̳й�ϵ 
            if (item.getKey().isAssignableFrom(clazz)) { 
                String[] strs = item.getValue(); 
                        
                // �������� �� name ֵ�������л� 
                if (isHave(strs, name)) { 
                    return false; 
                } 
            } 
        } 
                
        // ��Ҫ���еĶ��󼯺�Ϊ�� ��ʾ ȫ����Ҫ���л� 
        if (this.includes.isEmpty()) { 
            return true; 
        } 
                
        // ��Ҫ���еĶ��� 
        // �ҵ�����Ҫ�����л������� 
        for (Map.Entry<Class<?>, String[]> item : this.includes.entrySet()) { 
            // isAssignableFrom()�������ж����ͼ��Ƿ��м̳й�ϵ 
            if (item.getKey().isAssignableFrom(clazz)) { 
                String[] strs = item.getValue(); 
                // �������� �� name ֵ�������л� 
                if (isHave(strs, name)) { 
                    return true; 
                } 
            } 
        } 
                
        return false; 
    } 
            
    /* 
     * �˷�����������������һ����Ҫ���ҵ��ַ������飬�ڶ�����Ҫ���ҵ��ַ����ַ��� 
     */
    public static boolean isHave(String[] strs, String s) { 
                
        for (int i = 0; i < strs.length; i++) { 
            // ѭ�������ַ��������е�ÿ���ַ������Ƿ�������в��ҵ����� 
            if (strs[i].equals(s)) { 
                // ���ҵ��˾ͷ����棬���ڼ�����ѯ 
                return true; 
            } 
        } 
                
        // û�ҵ�����false 
        return false; 
    } 
            
    public Map<Class<?>, String[]> getIncludes() { 
        return includes; 
    } 
            
    public void setIncludes(Map<Class<?>, String[]> includes) { 
        this.includes = includes; 
    } 
            
    public Map<Class<?>, String[]> getExcludes() { 
        return excludes; 
    } 
            
    public void setExcludes(Map<Class<?>, String[]> excludes) { 
        this.excludes = excludes; 
    } 
            
    public static void main(String[] args) { 
        // use instanceOf�������ж϶����Ƿ������ʵ�� 
        // use isAssignableFrom()�������ж����ͼ��Ƿ��м̳й�ϵ 
        // use isInstance()�������ж϶����Ƿ������ʵ�� 
                
        Class<?> intClass = Integer.class; 
                
        // Create various objects. 
        String str = "Hello"; 
        Date date = new Date(); 
        Integer i = new Integer(10); 
                
        // Is str an instance of class Integer? 
        boolean check1 = intClass.isInstance(str); 
        System.out.println("str is an Integer? " + check1); 
                
        // Is date an instance of class Integer? 
        boolean check2 = intClass.isInstance(date); 
        System.out.println("date is an Integer? " + check2); 
                
        // Is i an instance of class Integer? 
        boolean check3 = intClass.isInstance(i); 
        System.out.println("i is an Integer? " + check3); 
                
    }
}