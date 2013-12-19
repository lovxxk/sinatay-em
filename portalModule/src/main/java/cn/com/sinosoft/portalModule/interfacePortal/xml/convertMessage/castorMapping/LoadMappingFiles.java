package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

public class LoadMappingFiles {
	
	private static Map<String,Mapping> LOADMAPPINGFILES = null;
	
	private static final String MAPFILEDIR  =  getRealPath();
	
	private static final String[] MAPPINGTYPE = new String[] {"Req", "Res" , "FReq", "FRes"};
	
	public static Map<String,Mapping> getMappingResources() {
		if (LOADMAPPINGFILES == null) {
			LOADMAPPINGFILES = loadMappingFiles(MAPFILEDIR);
		}
		return LOADMAPPINGFILES;
	}
	
	public static URL getUrl(String fileMame){
		return LoadMappingFiles.class.getClassLoader().getResource("castorMappingFile/" + fileMame);
	}
	
	public static URL getUrl(String fileDir,String fileName){
		return LoadMappingFiles.class.getClassLoader().getResource(fileDir + "/" + fileName);
	}
	
	public static String getRealPath() {
		return LoadMappingFiles.getUrl("").getPath();
	}
	 
	public static Map<String,Mapping> loadMappingFiles(String dirPath){
		Map<String,Mapping> loadMappingFile = new HashMap<String,Mapping>();
		File file = new File(dirPath);
		if (file.exists()) {
			try {
				File parentFile = file.getParentFile();
				loadMappingFiles(loadMappingFile,parentFile);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (MappingException e) {
				e.printStackTrace();
			}
		} else {
		}
		return loadMappingFile;
		
	}
	
	public static void loadMappingFiles(Map<String,Mapping> loadMappingFile, File file) throws IOException, MappingException {
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f: files)  {
					loadMappingFiles(loadMappingFile,f);
				}
			} else {
				 Mapping mapping = new Mapping();
				 mapping.loadMapping(file.getAbsolutePath());
				 String fileName = file.getName();
				 loadMappingFile.put(fileName.substring(0, fileName.indexOf(".")), mapping);
			}
		}
		
	}
	
	/**
	 * 通过文件的URL加载Mapping文件
	 * @param url 文件URL
	 * @return
	 */
	public static Mapping loadMappingFile(URL url) {
		try {
			if (url != null) {
				Mapping mapping = new Mapping();
				mapping.loadMapping(url);
				return mapping;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 通过文件所在文件夹及文件名加载Mapping文件
	 * @param fileDir 文件夹
	 * @param fileName 文件名
	 * @return
	 */
	public static Mapping loadMappingFile(String fileDir,String fileName) {
		URL url = LoadMappingFiles.getUrl(fileDir, fileName);
		return LoadMappingFiles.loadMappingFile(url);
	}
	
	/**
	 * 
	 *  加载映射配置文件
	 * @param map 映射类
	 * @param transCode 交易代码
	 * 
	 */
	public static void loadMappingFiles(Map<String,Mapping> map, String transCode){
		try {
			for (int i = 0; i < MAPPINGTYPE.length; i++) {
				
				String filePath = transCode + "_" + MAPPINGTYPE[i] + "_" + "CastorMapping.xml";
				URL url = LoadMappingFiles.getUrl(filePath);
				if (url != null) {
					 Mapping mapping = new Mapping();
					 mapping.loadMapping(url);
					 map.put(filePath.substring(0, filePath.indexOf(".")), mapping);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		}
		
	}
}
