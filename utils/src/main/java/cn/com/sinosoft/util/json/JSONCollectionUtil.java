package cn.com.sinosoft.util.json;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class JSONCollectionUtil {
	
	public static String listmTOjson(List<Map<String, Object>> list) throws Exception{
	    JSONArray json_arr = new JSONArray();
	    for (Map<String, Object> map : list) {
	        JSONObject json_obj = new JSONObject();
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            json_obj.put(key,value); 
	        } 
	        json_arr.put(json_obj); 
	    }
	    return json_arr.toString();
	}
}
