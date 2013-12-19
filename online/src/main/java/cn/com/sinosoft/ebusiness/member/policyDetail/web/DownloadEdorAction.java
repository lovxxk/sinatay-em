package cn.com.sinosoft.ebusiness.member.policyDetail.web;

import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.ebusiness.online.biz.dcenter.web.FileMngAction;

import com.f1j.ss.Document;
import com.f1j.ss.DocumentOpenCallback;
import com.f1j.ss.DocumentSaveCallback;
import com.f1j.ss.DocumentType;
import com.f1j.swing.engine.ss.JBook;

public class DownloadEdorAction extends Struts2Action {

	private static final long serialVersionUID = 6024976368251198502L;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// �ļ���
	private String fileName;
	// �ļ�·��
	private String url;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// ����һ������������Ϊһ���ͻ�����˵��һ���������������ڷ���������һ�� �����
	public InputStream getDownloadFile() throws Exception {

		url = FileMngAction.decryption(url);
		// ȡ���ļ���
//		String tt = getServletContext().getInitParameter("pdfilepath");
		
		String sql = "select codecorerelation from ge_code where codetype = 'pdvtsfilepath'";
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());
		
		String base_path = "";		
		if(list.size() != 0){
			base_path = list.get(0).get("codecorerelation").toString();
		}else{
			throw new RuntimeException("���������ļ�·��δ����");
		}
//		base_path = "D:/file4/MallVTSStore";		
		
		String vtsPath = base_path + url.substring(url.lastIndexOf("/"), url.length());
		String pdfPath = transVtsToPdf(vtsPath);		
		fileName = pdfPath.substring(pdfPath.lastIndexOf("/") + 1, pdfPath.length());
		FileInputStream is = new FileInputStream(pdfPath);
		return is;
	}

	public String transVtsToPdf(String vtsPath) {
		// vtsPath��û��д��Ȩ�ޣ�����Ҫ�½�һ���ٽ��ļ���
		String upPath = vtsPath.substring(0, vtsPath.lastIndexOf("/")-1);
		
		String sql = "select codecorerelation from ge_code where codetype = 'pdpdffilepath'";
		List<Object> objectList = new ArrayList<Object>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());

		if(list.size() != 0){
			upPath = list.get(0).get("codecorerelation").toString();
		}else{
			throw new RuntimeException("���������ļ�·��δ����");
		}
//		upPath = "D:/file4/pdpdffile/MallVtsStore";	

		File file = new File(upPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		//vtsPath.substring(0, vtsPath.length() - 3) + "pdf";	
		String pdfPath = upPath
				+ vtsPath.substring(vtsPath.lastIndexOf("/"),
						vtsPath.length() - 3) + "pdf";

		System.out.println("start trans " + vtsPath);
		try {
			Document doc = new Document(null, new File(vtsPath),
					new DocumentOpenCallback());
			JBook book = new JBook(doc);
			book.repaint();
			book.validate();

			try {
				doc.fileSaveAs(new File(pdfPath), DocumentType.PDF,
						new DocumentSaveCallback(book.getBookView()));
			} catch (Exception ex) {
				ex.printStackTrace();
				return "";
			} finally {
				doc.release();
			}
			book.destroy();
			book = null;
			System.out.println("ok trans " + pdfPath);
		} catch (Exception e) {
			throw new RuntimeException("����pdf�ļ�����");
		}
		return pdfPath;
	}
}
