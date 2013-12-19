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
 * ��̨�����������
 * 
 * 
 * 
 */
public class FileMngAction extends Struts2Action {
	
	private static Logger log = LoggerFactory.getLogger(FileMngAction.class);

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// Ͷ���ļ�
	private Set<Map<String, Object>> tbwjFiles;
	// ��ȫ�ļ�
	private Set<Map<String, Object>> bqwjFiles;
	// �����ļ�
	private Set<Map<String, Object>> lpwjFiles;
	// ְҵ����
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
			throw new RuntimeException("���������ļ�·��δ����");
		}
		

//		base_path = "D:/Users/pengyuming/Workspaces/workspace_37_chinalife/mis/src/main/webapp/business/businessManage/downloadFileMng";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// Ͷ���ļ�
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
		
		// ��ȫ�ļ�
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
		// �����ļ�
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
		// ְҵ����
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
	 * �����ַ���
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryption(String string) {// ASCIIת��Ϊ�ַ���
		// ת���ת����ע��ת����������6���ַ�������ǰ��ӵ�̶����ַ�
		String s0 = "";
		try {
			s0 = URLEncoder.encode("�п�����̩��Ŀ��" + string, "GBK").replaceAll("%",
					"`");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// �������ַ����ĳ���
		int length = s0.length() / 4 + (s0.length() % 4 == 0 ? 0 : 1);
		// �ֽ��4�����ַ���
		String s1 = s0.substring(0, length);
		String s2 = s0.substring(length, length * 2);
		String s3 = s0.substring(length * 2, length * 3);
		String s4 = s0.substring(length * 3);
		// s4��~�ַ�
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
		// �������г��µ��ַ���
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
	 * �����ַ���
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
		// ��������"~"�ַ�,�����3��������ִ��3��
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		// ת���ת��
		s0 = s0.replaceAll("`", "%");
		try {
			s0 = URLDecoder.decode(s0, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		s0 = s0.replaceFirst("�п�����̩��Ŀ��", "");
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
			throw new RuntimeException("���������ļ�·��δ����");
		}
		
//		base_path = "D:/Users/pengyuming/Workspaces/workspace_37_chinalife/mis/src/main/webapp/business/businessManage/downloadFileMng";

		String name = decryption(down_filename);
		String path = base_path + "/" + fileType + "/" + name;
		InputStream i = new FileInputStream(path);
		
		log.info("�����ļ���" + path);
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
