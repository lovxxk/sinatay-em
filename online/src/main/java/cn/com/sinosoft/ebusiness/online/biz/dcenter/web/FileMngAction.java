package cn.com.sinosoft.ebusiness.online.biz.dcenter.web;

import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.online.biz.bq.web.PartReceiveAction;

/**
 * 后台服务网点管理
 * 
 * 
 * 
 */
public class FileMngAction extends Struts2Action {
	
	private static Logger log = LoggerFactory.getLogger(FileMngAction.class);

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 投保文件
	private Set<Map<String, Object>> tbwjFiles;
	// 保全文件
	private Set<Map<String, Object>> bqwjFiles;
	// 理赔文件
	private Set<Map<String, Object>> lpwjFiles;
	// 职业分类
	private Set<Map<String, Object>> zyflFiles;
	
	private String fileType;
	
	private String down_filename;
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDown_filename() {
		return down_filename;
	}

	public void setDown_filename(String down_filename) {
		this.down_filename = down_filename;
	}
	
	public Set<Map<String, Object>> getTbwjFiles() {
		return tbwjFiles;
	}

	public void setTbwjFiles(Set<Map<String, Object>> tbwjFiles) {
		this.tbwjFiles = tbwjFiles;
	}

	public Set<Map<String, Object>> getBqwjFiles() {
		return bqwjFiles;
	}

	public void setBqwjFiles(Set<Map<String, Object>> bqwjFiles) {
		this.bqwjFiles = bqwjFiles;
	}

	public Set<Map<String, Object>> getLpwjFiles() {
		return lpwjFiles;
	}

	public void setLpwjFiles(Set<Map<String, Object>> lpwjFiles) {
		this.lpwjFiles = lpwjFiles;
	}

	public Set<Map<String, Object>> getZyflFiles() {
		return zyflFiles;
	}

	public void setZyflFiles(Set<Map<String, Object>> zyflFiles) {
		this.zyflFiles = zyflFiles;
	}

	public String init() throws Exception {		
		
		String sql = "select codecorerelation from ge_code where codetype = 'DCenterPath'";
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());
		
		String base_path = "";		
		if(list.size() != 0){
			base_path = list.get(0).get("codecorerelation").toString();
		}else{
			throw new RuntimeException("下载中心文件路劲未配置");
		}
		

//		base_path = "D:/Users/pengyuming/Workspaces/workspace_37_chinalife/mis/src/main/webapp/business/businessManage/downloadFileMng";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 投保文件
		File dir = new File(base_path + "/TBWJ");
		File[] files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					return true;
				return false;
			}
		});
		tbwjFiles = new TreeSet<Map<String, Object>>(
				new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1,
							Map<String, Object> o2) {
						return (int) ((Long) o1.get("LastModified") - (Long) o2
								.get("LastModified"));
					}
				});
		if(files != null){
			for (int i = 0; i < files.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Name", files[i].getName());
				map.put("DName", encryption(files[i].getName()));
				map.put("Length", files[i].length());
				map.put("LastModified", files[i].lastModified());
				map.put("LastModifiedDate",sdf.format(new Date(files[i].lastModified())));
				tbwjFiles.add(map);			
			}
		}
		
		// 保全文件
		dir = new File(base_path + "/BQWJ");
		files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					return true;
				return false;
			}
		});
		bqwjFiles = new TreeSet<Map<String, Object>>(
				new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1,
							Map<String, Object> o2) {
						return (int) ((Long) o1.get("LastModified") - (Long) o2
								.get("LastModified"));
					}
				});
		if(files != null){
			for (int i = 0; i < files.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Name", files[i].getName());
				map.put("DName", encryption(files[i].getName()));
				map.put("Length", files[i].length());
				map.put("LastModified", files[i].lastModified());
				map.put("LastModifiedDate",sdf.format(new Date(files[i].lastModified())));
				bqwjFiles.add(map);	
			}
		}
		// 理赔文件
		dir = new File(base_path + "/LPWJ");
		files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					return true;
				return false;
			}
		});
		lpwjFiles = new TreeSet<Map<String, Object>>(
				new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1,
							Map<String, Object> o2) {
						return (int) ((Long) o1.get("LastModified") - (Long) o2
								.get("LastModified"));
					}
				});
		if(files != null){
			for (int i = 0; i < files.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Name", files[i].getName());
				map.put("DName", encryption(files[i].getName()));
				map.put("Length", files[i].length());
				map.put("LastModified", files[i].lastModified());
				map.put("LastModifiedDate",sdf.format(new Date(files[i].lastModified())));
				lpwjFiles.add(map);	
			}
		}
		// 职业分类
		dir = new File(base_path + "/ZYFL");
		files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile())
					return true;
				return false;
			}
		});
		zyflFiles = new TreeSet<Map<String, Object>>(
				new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1,
							Map<String, Object> o2) {
						return (int) ((Long) o1.get("LastModified") - (Long) o2
								.get("LastModified"));
					}
				});
		if(files != null){
			for (int i = 0; i < files.length; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("Name", files[i].getName());
				map.put("DName", encryption(files[i].getName()));
				map.put("Length", files[i].length());
				map.put("LastModified", files[i].lastModified());
				map.put("LastModifiedDate",sdf.format(new Date(files[i].lastModified())));
				zyflFiles.add(map);	
			}
		}
		return SUCCESS;
	}

	/**
	 * 加密字符串
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryption(String string) {// ASCII转换为字符串
		// 转义符转换，注意转换后不能少于6个字符，所以前面加点固定的字符
		String s0 = "";
		try {
			s0 = URLEncoder.encode("中科软信泰项目组" + string, "GBK").replaceAll("%",
					"`");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// 计算子字符串的长度
		int length = s0.length() / 4 + (s0.length() % 4 == 0 ? 0 : 1);
		// 分解成4个子字符串
		String s1 = s0.substring(0, length);
		String s2 = s0.substring(length, length * 2);
		String s3 = s0.substring(length * 2, length * 3);
		String s4 = s0.substring(length * 3);
		// s4补~字符
		switch (length - s4.length()) {
		case 1:
			s4 = s4 + "~";
			break;
		case 2:
			s4 = s4 + "~~";
			break;
		case 3:
			s4 = s4 + "~~~";
			break;
		default:
			break;
		}
		// 重新排列成新的字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(s1.charAt(i));
			sb.append(s2.charAt(i));
			sb.append(s3.charAt(i));
			sb.append(s4.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 解密字符串
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decryption(String string) {
		int length = string.length();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for (int i = 0; i < length;) {
			sb1.append(string.charAt(i));
			i++;
			sb2.append(string.charAt(i));
			i++;
			sb3.append(string.charAt(i));
			i++;
			sb4.append(string.charAt(i));
			i++;
		}
		String s0 = "" + sb1 + sb2 + sb3 + sb4;
		// 消除最后的"~"字符,最多有3个，所以执行3遍
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		// 转义符转换
		s0 = s0.replaceAll("`", "%");
		try {
			s0 = URLDecoder.decode(s0, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		s0 = s0.replaceFirst("中科软信泰项目组", "");
		return s0;
	}
	
	public InputStream getInputStream() throws Exception {

		String sql = "select codecorerelation from ge_code where codetype = 'DCenterPath'";
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());
		
		String base_path = "";		
		if(list.size() != 0){
			base_path = list.get(0).get("codecorerelation").toString();
		}else{
			throw new RuntimeException("下载中心文件路劲未配置");
		}
		
//		base_path = "D:/Users/pengyuming/Workspaces/workspace_37_chinalife/mis/src/main/webapp/business/businessManage/downloadFileMng";

		String name = decryption(down_filename);
		String path = base_path + "/" + fileType + "/" + name;
		InputStream i = new FileInputStream(path);
		
		log.info("下载文件：" + path);
		return i;  
    }

	public String getDownloadFileName() {
		String downFileName = decryption(down_filename);
		try {
			downFileName = java.net.URLEncoder.encode(downFileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		return downFileName;
	}
}
