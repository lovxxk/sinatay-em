package cn.com.sinosoft.ebusiness.web.action.cms;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.cms.domain.CmsChannel;
import cn.com.sinosoft.ebusiness.cms.domain.CmsDocument;
import cn.com.sinosoft.ebusiness.cms.domain.CmsEnclosure;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.ebusiness.cms.util.CmsLoader;
import cn.com.sinosoft.ebusiness.cms.util.FileGenerate;
import cn.com.sinosoft.util.string.StringUtils;

public class ArticleManageAction extends Struts2Action{
	private static final long serialVersionUID = -2707456426084182818L;
	public static String systemPath = CmsLoader.getCmsProperties("systemPath");
	public static String tmpFilePath = CmsLoader.getCmsProperties("tmpFilePath");
	public static String targetAppContextPath = CmsLoader.getCmsProperties("targetAppContextPath");
	
	@Autowired
	private CMSManageService cmsManageService;
	
	private CmsDocument document;
	
	private CmsChannel channel;
	
	private CmsEnclosure enclosure;
	
	private String message;
	
	private String fileName;
	private File doc;
	private String contentType;
	private File upload;
	private String innerPath;
	List<CmsEnclosure> cmsEncosures = new ArrayList<CmsEnclosure>(0);
	
	private static Logger loger = LoggerFactory.getLogger(ArticleManageAction.class);
	
	public List<CmsEnclosure> getCmsEncosures() {
		return cmsEncosures;
	}

	public void setCmsEncosures(List<CmsEnclosure> cmsEncosures) {
		this.cmsEncosures = cmsEncosures;
	}

	public String getInnerPath() {
		return innerPath;
	}

	public void setInnerPath(String innerPath) {
		this.innerPath = innerPath;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public CmsDocument getDocument() {
		return document;
	}

	public void setDocument(CmsDocument document) {
		this.document = document;
	}

	public CmsEnclosure getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(CmsEnclosure enclosure) {
		this.enclosure = enclosure;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CmsChannel getChannel() {
		return channel;
	}

	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}

	public void setCmsManageService(CMSManageService cmsManageService) {
		this.cmsManageService = cmsManageService;
	}
	
	public void setDoc(File file) {
		this.doc = file;
	}

	public void setDocFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setDocContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getCmsChannelByChannelId(){
		String channelId = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID").trim();
		try {
			List channelList = cmsManageService.findChannelByChannelID(Integer.parseInt(channelId));
			channel = (CmsChannel)channelList.get(0);
			super.getRequest().setAttribute("channel", channel);
			
			cmsEncosures = new ArrayList<CmsEnclosure>(0);
		} catch (Exception e) {
			loger.info("cms根据栏目id查找栏目失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}

//	public String saveImage() throws FileUploadException, IOException {
//		System.out.println("testForSaveImage");
//		ServletRequest req = super.getRequest();
//		ServletResponse res = super.getResponse();
//		PrintWriter pw = res.getWriter();
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//		Date today = new Date();
//		try {
//			Upload upload =new Upload();
//			enclosure =new CmsEnclosure();
//			upload.uploadfile(req);
//
//			String tmpDocId=upload.get("tmpDocId").toString();
//			String attNum=upload.get("docType").toString();
//			int fileCount=upload.getCount();
//			System.out.println("fileCount="+fileCount);
//			try{
//				for(int i=0;i<fileCount;i++){
//					String fileName=upload.getfileName(i);
//					String DescName=upload.get("FileName"+(i+1)).toString();
//					if(DescName!=null&&!DescName.equals("")){
//						String nowtime = df.format(today);
//						String docfilName=tmpDocId+"_"+nowtime+"_"+fileName;//用文章ID+生成的时间(唯一标识)+上附件名称作为AttName字段的值
//						upload.setfileName(docfilName,i);
//						upload.saveAs("/mis/cms/global/cms/examples/media/" ,i);
//						enclosure.setAttName(docfilName);
//						enclosure.setAttDocID(tmpDocId);
//						enclosure.setAttDesc(DescName);
//						enclosure.setAttPath("/mis/cms/global/cms/examples/media/" + docfilName);
//						enclosure.setAttType(attNum);
//						cmsManageService.createCMS(enclosure);
//					}
//				}
//				
//				
//				super.getRequest().getRequestDispatcher("/servlet/FileUpload").forward(req, res);
//			
//			
//				pw.write("<script language=\"javascript\">window.parent.submitContent();</script>");
//			}catch(Exception ex){
//				pw.write("上传出错:"+ex.getMessage());
//				message = "上传出错:"+ex.getMessage();
//			}
//
//		} catch (Exception e) {
//			System.out.println("*****"+e);
//		}
//		return NONE;
//		
//	}
	
	public String saveImage() throws IOException{
		ServletResponse res = super.getResponse();
		PrintWriter pw = res.getWriter();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date today = new Date();
		String nowtime = df.format(today);
		String targetDirectory = this.getServletContext().getRealPath("/WEB-INF/tempFiles");
		String tmpDocId = super.getRequest().getParameter("tmpDocId") == null ? "" : super.getRequest().getParameter("tmpDocId");
		String targetName = fileName;
		if(!targetName.equals("")&& targetName!=null){
			targetName = tmpDocId +"_" + nowtime + "_" + targetName.substring(targetName.lastIndexOf("/")+1);
			try {
				File target = new File(targetDirectory, targetName);
				
				enclosure = new CmsEnclosure();
				FileUtils.copyFile(doc, target);
				enclosure.setAttName(targetName);
				enclosure.setAttDocID(tmpDocId);
	//			enclosure.setAttDesc(DescName);
				enclosure.setAttPath(targetDirectory);
				enclosure.setAttType("2");
				cmsManageService.createCMS(enclosure);
				
			} catch (IOException e) {
				loger.info("cms保存图片失败:"+StringUtils.exception2String(e));
			}
		}
		pw.write("<script language=\"javascript\">window.parent.submitContent();</script>");
		return NONE;
	}
	
	public String saveEdit() throws FileUploadException, IOException {
		ServletRequest req = super.getRequest();

		String tmpDocId = req.getParameter("tmpDocId")==null?"":req.getParameter("tmpDocId").trim();
		String docTitle = req.getParameter("docTitle")==null?"":req.getParameter("docTitle").trim();
		String docChannel = req.getParameter("docChannel")==null?"":req.getParameter("docChannel").trim();
		String docAbs = req.getParameter("docAbs")==null?"":req.getParameter("docAbs").trim();
		String docType = req.getParameter("docType")==null?"":req.getParameter("docType").trim();
		String docLink = req.getParameter("docLink")==null?"":req.getParameter("docLink").trim();
		String docSource = req.getParameter("docSource")==null?"":req.getParameter("docSource").trim();
		String docKeysword = req.getParameter("docKeysword")==null?"":req.getParameter("docKeysword").trim();
		String docreltime = req.getParameter("docreltime")==null?"":req.getParameter("docreltime").trim();
		String elm1 = req.getParameter("elm1")==null?"&nbsp;":req.getParameter("elm1").trim()==""?"&nbsp;":req.getParameter("elm1").trim();

		//改变附件路径为发布后的应用名
		elm1 = elm1.replaceAll(this.getRequest().getContextPath() + tmpFilePath, "/" + targetAppContextPath + tmpFilePath );

		
		
		document = new CmsDocument();
		enclosure =new CmsEnclosure();
		
		document.setDocChannel(docChannel);
		document.setDocVersion("docversion");
		document.setDocOrder(new BigDecimal(0));
		document.setDocType(docType);
		document.setDocTitle(docTitle);
		document.setDocSource(docSource);
		document.setDocStatus("0");
		document.setDocContent(elm1);
		document.setDocHtmlCon(elm1);
		document.setDocABS(docAbs);
		document.setDocKeysWord(docKeysword);
		document.setDocAuthor("docauthor");
		document.setHitsCount("hitscount");
		document.setAcctachpic("acctachpic");
		document.setDocLink(docLink);
		document.setTempk(tmpDocId);
		document.setDocRelTime(docreltime);
		
		try{
			cmsManageService.createCMS(document);
			
//			document = new CmsDocument();
//			QueryRule qRule = QueryRule.getInstance();
//			qRule.addEqual("tempk", tmpDocId);
//			List<Object> docList = cmsManageService.searchCMS(document, qRule);
//			String docID = "";
//			for(int i = 0; i<docList.size(); i++){
//				docID = ((CmsDocument)docList.get(i)).getDocID() + "";
//			}
//			enclosure = new CmsEnclosure();
//			QueryRule qRule1 = QueryRule.getInstance();
//			qRule1.addEqual("attDocID", tmpDocId);
//			List<Object> encList = cmsManageService.searchCMS(enclosure, qRule1);
//			StringBuffer sb = new StringBuffer();
//			for(int j=0; j<encList.size(); j++){
//				if(j==encList.size()-1){
//					sb.append(((CmsEnclosure)encList.get(j)).getAttID());
//				}else{
//					sb.append(((CmsEnclosure)encList.get(j)).getAttID()+",");
//				}
//			}
//			String AttIdArry = sb.toString();
//			if(AttIdArry != null && !AttIdArry.equals("")){
//				String[] AttId=AttIdArry.split(",");
//				for(int i=0;i<AttId.length;i++){
//					System.out.println("AttId["+i+"]="+AttId[i]);
//					QueryRule queryRule = QueryRule.getInstance();
//					enclosure = new CmsEnclosure();
//					queryRule.addEqual("attID", Integer.parseInt(AttId[i]));
//					enclosure = (CmsEnclosure)cmsManageService.searchCMS(enclosure, queryRule).get(0);
//					enclosure.setAttDocID(docID);
//					cmsManageService.updateCMS(enclosure);
//				}
//			}
			
			if(cmsEncosures.size() > 0) {
				for (int i = 0; i < cmsEncosures.size(); i++) {
					CmsEnclosure en = cmsEncosures.get(i);
					en.setAttDocID(String.valueOf(document.getDocID()));
					cmsManageService.createCMS(en);
				}
			}
			
			
			
			message = "createDone";
			super.getRequest().setAttribute("message", message);
		}catch(Exception ex){
			message = "createFailed";
//			super.getRequest().setAttribute("message", message);
			loger.info("cms编辑文章失败:"+StringUtils.exception2String(ex));
//			try {
//				QueryRule queryRule = QueryRule.getInstance();
//				queryRule.addEqual("attDocID", tmpDocId);
//				enclosure = new CmsEnclosure();
//				List<Object> encList = cmsManageService.searchCMS(enclosure, queryRule);
//				for(int i = 0; i<encList.size(); i++){
//					cmsManageService.deleteCMS((CmsEnclosure)encList.get(i));
//				}
//			} catch (Exception e) {
//				loger.info("失败:"+StringUtils.exception2String(e));
//			}
		}
		
		return SUCCESS;
	}
	
	public String getCmsDocument(){
		String docChnl = super.getRequest().getParameter("nodeID") == null ? "" : super.getRequest().getParameter("nodeID");
		String docType = super.getRequest().getParameter("docType") == null ? "" : super.getRequest().getParameter("docType");
		String docTitle = super.getRequest().getParameter("docTitle") == null ? "" : super.getRequest().getParameter("docTitle");
		String docKeyWord = super.getRequest().getParameter("docKey") == null ? "" : super.getRequest().getParameter("docKey");
		
		String docIDs = cmsManageService.findDocIDByDocChnlAndDocTypeAndDocTitleAndDocKeyWord(docChnl, docType, docTitle, docKeyWord);
		System.out.println("docIDs="+docIDs);
		List<Object> docList = new ArrayList<Object>();
		if(!"".equals(docIDs)){
			String[] strID = docIDs.split(",");
			for(int i = 0; i<strID.length; i++){
				docList.add(((CmsDocument)cmsManageService.findDocByDocID(Integer.parseInt(strID[i])).get(0)));
			}
			super.getRequest().setAttribute("docList", docList);
		}
		super.getRequest().setAttribute("docIDs", docIDs);
		
		return SUCCESS;
	}
	
	public String deleteArticle(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		String[] idStr = ID.split(",");
		try {
			for(int i=0; i<idStr.length; i++){
				cmsManageService.deleteEnc(idStr[i]);
				cmsManageService.deleteDocByDocID(idStr[i]);
			}
			message = "deleteDone";
			super.getRequest().setAttribute("message", message);
		} catch (Exception e) {
			message = "deleteFailed";
			super.getRequest().setAttribute("message", message);
			loger.info("cms删除文章失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String frmUpdate(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", Integer.valueOf(ID));
		document = (CmsDocument)cmsManageService.searchCMS(document, queryRule).get(0);
		String docTitle = document.getDocTitle();
		String docKeyWord = document.getDocKeysWord();
		String docRelTime = document.getDocRelTime();
		
		channel = new CmsChannel();
		channel = (CmsChannel)cmsManageService.findChannelByChannelID(Integer.valueOf(document.getDocChannel())).get(0);
		String channelDesName = channel.getChnlDesName();
		super.getRequest().setAttribute("ID", ID);
		super.getRequest().setAttribute("docTitle", docTitle);
		super.getRequest().setAttribute("docKeyWord", docKeyWord);
		super.getRequest().setAttribute("docRelTime", docRelTime);
		super.getRequest().setAttribute("channelDesName", channelDesName);
		
		cmsEncosures = new ArrayList<CmsEnclosure>(0);
		
		return SUCCESS;
	}
	
	public String updateDocContent(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		String tag = super.getRequest().getParameter("tag") == null ? "" : super.getRequest().getParameter("tag");
		document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", Integer.valueOf(ID));
		document = (CmsDocument)cmsManageService.searchCMS(document, queryRule).get(0);
		String docContent = "";
		if("update".equals(tag))
			docContent = document.getDocContent();
		super.getRequest().setAttribute("docContent", docContent);
		
		return SUCCESS;
	}
	
	public String updateEdit(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		String docTitle = super.getRequest().getParameter("docTitle") == null ? "" : super.getRequest().getParameter("docTitle");
		String docKeyWord = super.getRequest().getParameter("docKeysword") == null ? "" : super.getRequest().getParameter("docKeysword");
		String docRelTime = super.getRequest().getParameter("docreltime") == null ? "" : super.getRequest().getParameter("docreltime");
		String elm1 = super.getRequest().getParameter("elm1")==null?"&nbsp;":super.getRequest().getParameter("elm1").trim()==""?"&nbsp;":super.getRequest().getParameter("elm1").trim();
		document = new CmsDocument();
		
		//改变附件路径为发布后的应用名
		elm1 = elm1.replaceAll(this.getRequest().getContextPath() + tmpFilePath, "/" + targetAppContextPath + tmpFilePath );
		System.out.println(elm1);
		
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", Integer.valueOf(ID));
		document = (CmsDocument)cmsManageService.searchCMS(document, queryRule).get(0);
		document.setDocTitle(docTitle);
		document.setDocKeysWord(docKeyWord);
		document.setDocRelTime(docRelTime);
		document.setDocContent(elm1);
		try {
			cmsManageService.updateCMS(document);
			message = "updateDone";
			super.getRequest().setAttribute("message", message);
			
			if(cmsEncosures.size() > 0) {
				for (int i = 0; i < cmsEncosures.size(); i++) {
					CmsEnclosure en = cmsEncosures.get(i);
					en.setAttDocID(String.valueOf(document.getDocID()));
					cmsManageService.createCMS(en);
				}
			}
			
		} catch (RuntimeException e) {
			message = "updateFailed";
			super.getRequest().setAttribute("message", message);
			loger.info("cms更新编辑文字失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	public String frmUpdateLink(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", Integer.valueOf(ID));
		document = (CmsDocument)cmsManageService.searchCMS(document, queryRule).get(0);
		String docTitle = document.getDocTitle();
		String docLink = document.getDocLink();
		String docKeyWord = document.getDocKeysWord();
		String docRelTime = document.getDocRelTime();
		
		channel = new CmsChannel();
		channel = (CmsChannel)cmsManageService.findChannelByChannelID(Integer.valueOf(document.getDocChannel())).get(0);
		String channelDesName = channel.getChnlDesName();
		super.getRequest().setAttribute("ID", ID);
		super.getRequest().setAttribute("docTitle", docTitle);
		super.getRequest().setAttribute("docLink", docLink);
		super.getRequest().setAttribute("docKeyWord", docKeyWord);
		super.getRequest().setAttribute("docRelTime", docRelTime);
		super.getRequest().setAttribute("channelDesName", channelDesName);
		return SUCCESS;
	}
	
	public String updateLink(){
		String ID = super.getRequest().getParameter("ID") == null ? "" : super.getRequest().getParameter("ID");
		String docTitle = super.getRequest().getParameter("docTitle") == null ? "" : super.getRequest().getParameter("docTitle");
		String docKeyWord = super.getRequest().getParameter("docKeysword") == null ? "" : super.getRequest().getParameter("docKeysword");
		String docRelTime = super.getRequest().getParameter("docreltime") == null ? "" : super.getRequest().getParameter("docreltime");
		String docLink = super.getRequest().getParameter("docLink")== null ? "" : super.getRequest().getParameter("docLink");
		document = new CmsDocument();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("docID", Integer.valueOf(ID));
		document = (CmsDocument)cmsManageService.searchCMS(document, queryRule).get(0);
		document.setDocTitle(docTitle);
		document.setDocKeysWord(docKeyWord);
		document.setDocRelTime(docRelTime);
		document.setDocLink(docLink);
		try {
			cmsManageService.updateCMS(document);
			message = "updateDone";
			super.getRequest().setAttribute("message", message);
		} catch (RuntimeException e) {
			message = "updateFailed";
			super.getRequest().setAttribute("message", message);
			loger.info("cms更新链接失败:"+StringUtils.exception2String(e));
		}
		return SUCCESS;
	}
	
	//上传图片
	//传递图片参数
	public String updateImg() {
		try {
			if (upload != null) {
				String time = System.currentTimeMillis() + "";
				String random = String.valueOf(new Random().nextInt(1000));
				fileName = this.getRequest().getParameter("fileName");
				String stux = fileName.substring(fileName.lastIndexOf("."), fileName.length());
				fileName = random + "_" + time + stux;//文件名
				String path = systemPath + tmpFilePath + fileName;//文件全路径
				byte[] uploadByte = ins.framework.utils.FileUtils.readBytes(upload);
				
				ins.framework.utils.FileUtils.write(uploadByte, path);
				innerPath = tmpFilePath + fileName;
				
				CmsEnclosure cmsEncosure = new CmsEnclosure();
				cmsEncosure.setAttName(fileName);
				cmsEncosure.setAttPath(systemPath + tmpFilePath);
				cmsEncosures.add(cmsEncosure);
				
				FileGenerate fg = new FileGenerate();
				fg.setPathType(FileGenerate.RELATED_PATH);//设置为相对路径
	    	    String rPath = tmpFilePath;//相对于应用的路径
	    	    fg.mergeFileToFtp(rPath, path);
			}
			upload = null;
		} catch (Exception e) {
			loger.info("cms上传图片失败:"+StringUtils.exception2String(e));
		}
		return this.getRequest().getParameter("type");
	}
	
}