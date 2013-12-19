package cn.com.sinosoft.ebusiness.cms.service.spring;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.com.sinosoft.ebusiness.cms.domain.CmsChannel;
import cn.com.sinosoft.ebusiness.cms.domain.CmsChannelTemplet;
import cn.com.sinosoft.ebusiness.cms.domain.CmsDocument;
import cn.com.sinosoft.ebusiness.cms.domain.CmsTemplet;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSDistributeService;
import cn.com.sinosoft.ebusiness.cms.service.facade.CMSManageService;
import cn.com.sinosoft.ebusiness.cms.util.CmsLoader;
import cn.com.sinosoft.ebusiness.cms.util.FileGenerate;
import cn.com.sinosoft.ebusiness.cms.util.pojo.Channel;
import cn.com.sinosoft.ebusiness.cms.util.pojo.Document;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class CMSDistributeServiceSpringImpl extends GenericDaoHibernate<CmsChannel, String> implements CMSDistributeService{
	//ģ����·��
	public static String templetFolfer;
	//��������·��
	public static String webPath;
	//ģ������
	public static List templetTypeList;
	//�����������
	public static final String NOBIND = "��Ŀδѡ����ʽ";
	public static final String NOCHANNEL = "��Ŀ������";
	public static final String CODEEORROR = "ϵͳ�쳣";
	public static final String SUCCESS = "�����ɹ�";
	@Autowired
	private CMSManageService cmsManageService;//�ײ�ʵ����
	private Configuration freemarker_cfg = null;//freemarker�����
	private Map rootAll = new HashMap();//freemaker����
	//freemakerа���ת�壬��el���ʽ��${}�г�ͻ
	public static String ctx = "${ctx}";
	
	private static Logger loger = LoggerFactory.getLogger(CMSDistributeServiceSpringImpl.class);
	
	public void setCmsManageService(CMSManageService cmsManageService) {
		this.cmsManageService = cmsManageService;
	}
	
	static {
		templetFolfer = CmsLoader.getCmsProperties("templetFolfer");
		webPath = CmsLoader.getCmsProperties("webPath");
		String[] templetTypeArray = CmsLoader.templetTypeArray;
		templetTypeList = new ArrayList();
		for (int i = 0; i < templetTypeArray.length; i++) {
			templetTypeList.add(templetTypeArray[i]);
		}
		//����ת��
	}
	
	//������Ŀ������������Ŀ�²�������-�������1
	public String pubChannel(String channelID, List docList) {
		Integer chlID = Integer.valueOf(channelID);
		//��֤��Ŀ�Ƿ����
		CmsChannel chl = new CmsChannel();
		QueryRule q = QueryRule.getInstance();
		q.addEqual("channelID", chlID);
		List chlList = cmsManageService.searchCMS(chl, q);
		if(chlList == null || chlList.size() < 1) {
			return NOCHANNEL;
		}
		//����������Ŀ
		CmsChannel pubChl = (CmsChannel)chlList.get(0);
		//��ʼ��ģ�����
		this.initTempletContent(pubChl, docList);
		//��÷���·��
		String pubPath = this.getPubPath(pubChl);
		//�����Ŀ��ģ��
		CmsChannelTemplet bind = new CmsChannelTemplet();
		CmsTemplet templet = new CmsTemplet();
		QueryRule qct = QueryRule.getInstance();
		qct.addEqual("chlid", String.valueOf(chlID));
		List bindList = cmsManageService.searchCMS(bind, qct);
		if(bindList == null || bindList.size() < 1) {//δ�ҵ���
			return "NOBIND";
		}
		//����ÿһ����ģ��
		for (int i = 0; i < bindList.size(); i++) {
			bind = (CmsChannelTemplet) bindList.get(i);
			String templetID = bind.getTid();
			QueryRule qf = QueryRule.getInstance();
			qf.addEqual("tplID", Integer.valueOf(templetID));
			List tList = cmsManageService.searchCMS(templet, qf);
			if(tList == null || tList.size() < 1) {//δ�ҵ�ģ��
				return "NOBIND";
			}
			//��Ӧ��һ��ģ��
			templet = (CmsTemplet) tList.get(0);
			//����ģ��
			String tplType = templet.getTplType();
			int methodNum = templetTypeList.indexOf(tplType);
			Class cls = this.getClass();
			try {
				Method m  = cls.getDeclaredMethod("createPubFile" + methodNum, CmsTemplet.class, CmsChannel.class, String.class, List.class);
				m.invoke(this, templet, pubChl, pubPath, docList);
			} catch (Exception e) {
				loger.info("CMS������Ŀ����ʧ��:"+StringUtils.exception2String(e));
				return "";
			}
		}
		return SUCCESS;
	}
	
	//������Ŀid������Ŀ,������������-�������2
	public String pubChannel(String channelID) {
		List docList = this.getChlDoc(channelID);
		return this.pubChannel(channelID, docList);
	}
	
	//���ɷ�������ļ�-�����б�
	private void createPubFile0(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = "index.jsp";
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//���ɷ�������ļ�-������ϸ����Ҫ���¶��Ƶ�ǰ���£�
	private void createPubFile1(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		
		if(docList == null || docList.size() < 1) {
			rootAll.put("document", new Document());
			return;
		}
		
		for (int i = 0; i < docList.size(); i++) {
			CmsDocument doc = (CmsDocument) docList.get(i);
			String pubName = doc.getDocID() + ".jsp";
			Document docPojo = new Document();
			docPojo.setAuthor(doc.getDocAuthor());
			docPojo.setDocContent(doc.getDocContent());
			docPojo.setDocID(doc.getDocID() + "");
			docPojo.setDocName(doc.getDocTitle());
			docPojo.setPubTime(doc.getDocRelTime());
			rootAll.put("document", docPojo);
			this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
			
			//�����·�����ʶ��Ϊ�ѷ���
			doc.setDocStatus("1");
			cmsManageService.updateCMS(doc);
		}
	}
	
	//���ɷ�������ļ�-��ƪ����
	private void createPubFile2(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = "index.jsp";
		
		if(docList == null || docList.size() < 1) {
			rootAll.put("document", new Document());
			this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
			return;
		}
		
		CmsDocument doc = (CmsDocument) docList.get(0);
		Document docPojo = new Document();
		docPojo.setAuthor(doc.getDocAuthor());
		docPojo.setDocContent(doc.getDocContent());
		docPojo.setDocID(doc.getDocID() + "");
		docPojo.setDocName(doc.getDocTitle());
		docPojo.setPubTime(doc.getDocRelTime());
		rootAll.put("document", docPojo);
		
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
		
		//�����·�����ʶ��Ϊ�ѷ���
		doc.setDocStatus("1");
		cmsManageService.updateCMS(doc);
	}
	
	//���ɷ�������ļ�-���ⶨ��
	private void createPubFile3(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		//TODO
	}
	
	//���ɷ�������ļ�-���˵�
	private void createPubFile4(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		if(!"1".equals(pubChl.getParentID())) {//ֻ��2����Ŀ
			return;
		}
		String templetFileName = templet.getTplName();
		String pubName = "leftPanel.jsp";
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//���ɷ�������ļ�-��ת����Ŀ
	private void createPubFile5(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = "index.jsp";
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//���ɷ�������ļ�-����
	private void createPubFile6(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = templetFileName.replaceAll("ftl", "jsp");
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//���ɷ���·����������url�͸���Ŀurl��������"/news/group/health/"
	public String getPubPath(CmsChannel chl) {
		String chlURL = chl.getLinkUrl();
		String pID = chl.getParentID();
		QueryRule q = QueryRule.getInstance();
		CmsChannel c = new CmsChannel();
		q.addEqual("channelID", Integer.valueOf(pID));
		List l = cmsManageService.searchCMS(c, q);
		c = (CmsChannel) l.get(0);
		if(1 == c.getChannelID()) {
			return "/" + chl.getLinkUrl();
		} else {
			return this.getPubPath(c) + "/"  + chlURL;
		}
	}
	
	//�����Ŀ����������
	public List getChlDoc(String channelID) {
		CmsDocument doc = new CmsDocument();
		QueryRule q = QueryRule.getInstance();
		q.addEqual("docChannel", channelID);
		q.addDescOrder("docRelTime");
		return cmsManageService.searchCMS(doc, q);
	}
	
	//�����Ŀ�������޸�/�½�������
	public List getChlModifyDoc(String channelID) {
		CmsDocument doc = new CmsDocument();
		QueryRule q = QueryRule.getInstance();
		q.addEqual("docChannel", channelID);
		q.addNotEqual("docStatus", "1");
		return cmsManageService.searchCMS(doc, q);
//		return null;
	}
	
	//��װ���е�ģ������
	private void initTempletContent(CmsChannel pubChl, List docList) {
		//ת�����
		rootAll.put("ctx", ctx);
		//�׸�����Ŀ
		CmsChannel fc = new CmsChannel();
		QueryRule fq = QueryRule.getInstance();
		fq.addEqual("parentID", pubChl.getChannelID().toString());
		fq.addDescOrder("chnlOrder");
		List childList = cmsManageService.searchCMS(fc, fq);
		if(childList == null || childList.size() < 1) {
		} else {
			fc = (CmsChannel) childList.get(0);
			Channel fcPojo = new Channel();
//			fcPojo.setChannelPath(this.getPubPath(fc));
			fcPojo.setChannelUrl(fc.getLinkUrl());
			rootAll.put("firstChild", fcPojo);
		}
		
		
		//����
		List docPojoList = new ArrayList();
		for (int i = 0; i < docList.size(); i++) {
			Document docPojo = new Document();
			CmsDocument doc = (CmsDocument) docList.get(i);
			docPojo.setAuthor(doc.getDocAuthor());
			docPojo.setDocContent(doc.getDocContent());
			docPojo.setKeyWord(doc.getDocKeysWord());
			docPojo.setDocID(String.valueOf(doc.getDocID()));
			docPojo.setDocName(doc.getDocTitle());
			docPojo.setPubTime(doc.getDocRelTime());
			docPojoList.add(docPojo);
		}
		rootAll.put("documentList", docPojoList);
		
		//����Ŀ��Ϣ
		Channel channelPojo = new Channel();
		channelPojo.setChannelName(pubChl.getChnlDesName());
		channelPojo.setChannelUrl(pubChl.getLinkUrl());
		channelPojo.setChannelPath(this.getPubPath(pubChl));
		//����Ŀ��Ϣ
		String parentID = pubChl.getParentID();
		CmsChannel pChannel = this.findChannelByID(parentID);
		if("1".equals(pChannel.getParentID())) {//������Ŀ������Ŀ��һ����Ŀ��ͬ
			channelPojo.setParentName(pChannel.getChnlDesName());
			channelPojo.setParentPath(this.getPubPath(pChannel));
			channelPojo.setParentUrl(pChannel.getLinkUrl());
			channelPojo.setRootChannelName(pChannel.getChnlDesName());
			channelPojo.setRootChannelUrl(channelPojo.getParentPath());
			channelPojo.setRootUrl(pChannel.getLinkUrl());
		} else if(!"1".equals(parentID)) {//������Ŀ
			channelPojo.setParentName(pChannel.getChnlDesName());
			channelPojo.setParentPath(this.getPubPath(pChannel));
			channelPojo.setParentUrl(pChannel.getLinkUrl());
			String ppID = pChannel.getParentID();
			CmsChannel ppChannel = this.findChannelByID(ppID);
			channelPojo.setRootChannelName(ppChannel.getChnlDesName());
			channelPojo.setRootChannelUrl(this.getPubPath(ppChannel));
			channelPojo.setRootUrl(ppChannel.getLinkUrl());
		}
		rootAll.put("channel", channelPojo);
		
		//���˵�,ֻ��һ����ĿĬ�������˵�*����������ĿҲ���԰����˵�
		if("1".equals(pubChl.getParentID()) ||"1".equals(pChannel.getParentID()) ) {
			//�ҵ���һ����Ŀ�����ж�����Ŀ
			CmsChannel chl2 = new CmsChannel();
			QueryRule q = QueryRule.getInstance();
			q.addEqual("parentID", String.valueOf(pubChl.getChannelID()));
			q.addDescOrder("chnlOrder");
			List chl2List = cmsManageService.searchCMS(chl2, q);//��һ����Ŀ�����ж�����Ŀ
			if(chl2List == null || chl2List.size() < 1) {
			} else {
				List c2PojoList = new ArrayList();
				for (int i = 0; i < chl2List.size(); i++) {
					CmsChannel c2 = (CmsChannel) chl2List.get(i);
					Channel c2Pojo = new Channel();
					c2Pojo.setChannelName(c2.getChnlDesName());
					c2Pojo.setChannelUrl(c2.getLinkUrl());
					c2Pojo.setChannelPath(this.getPubPath(c2));
					CmsChannel c3 = new CmsChannel();
					QueryRule q2 = QueryRule.getInstance();
					q2.addEqual("parentID", String.valueOf(c2.getChannelID()));
					q2.addDescOrder("chnlOrder");
					List c3List = cmsManageService.searchCMS(c3, q2);
					if(c3List == null || c3List.size() < 1) {
						c2Pojo.setChild(false);
						c2PojoList.add(c2Pojo);
						continue;
					}
					for (int j = 0; j < c3List.size(); j++) {
						c2Pojo.setChild(true);
						Channel c3Pojo = new Channel();
						CmsChannel channel3 = (CmsChannel) c3List.get(j);
						c3Pojo.setChannelName(channel3.getChnlDesName());
						c3Pojo.setChannelUrl(channel3.getLinkUrl());
						c3Pojo.setChannelPath(this.getPubPath(channel3));
						List c2ChildList = c2Pojo.getChildList();
						if(c2ChildList == null || c2ChildList.size() < 1) {
							c2ChildList = new ArrayList();
							c2ChildList.add(c3Pojo);
							c2Pojo.setChildList(c2ChildList);
						} else {
							c2ChildList.add(c3Pojo);
						}
					}
					c2PojoList.add(c2Pojo);
				}
				rootAll.put("channelList", c2PojoList);
			
			}
		}
	}
	

	
	/**
     * ���ɾ�̬�ļ�.���ڳ����ڱ��������⣬����Ҫ�ڶ�Ӧ�ĸ��ط�����������
     * @param templateFileName ģ���ļ���,����"111.ftl"
     * @param temPath ģ���ļ���ʱ�洢·�� ������"/tpxw/view.ftl"
     * @param htmlFilePath Ҫ���ɵľ�̬�ļ���·��,��������еĸ�·��,���� "/tpxw/1/2005/4/"
     * @param htmlFileName Ҫ���ɵ��ļ���,���� "1.htm"
     */
   public boolean geneHtmlFile(String templateFileName, String temPath, String htmlFilePath,String htmlFileName)
   {
	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...");
        Map propMap = this.rootAll;//���ڴ���ģ�������Objectӳ��
       try
       {
    	   freemarker_cfg = getFreeMarkerCFG(temPath);
    	   Template t = freemarker_cfg.getTemplate(templateFileName, "GBK");
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...htmlFilePath==="+htmlFilePath);
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...htmlFileName==="+htmlFileName);
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...temPath==="+temPath);
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...templateFileName==="+templateFileName);
    	   File afile = new File(webPath + htmlFilePath);
    	   if(!afile.exists()) {
    		   afile.mkdirs();
    	   }
    	   afile = new File(webPath + htmlFilePath + "/" + htmlFileName);
//    	   if(!afile.exists()) {
//    		   afile.createNewFile();
//    	   }
//    	   System.out.println(((Document)rootAll.get("document")).getDocContent());
    	   Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile),"GBK"));
    	   t.process(propMap, out);
    	   
    	   FileGenerate fg = new FileGenerate();
    	   fg.setPathType(FileGenerate.RELATED_PATH);//����Ϊ���·��
    	   String rPath = "/web/" + htmlFilePath;//�����Ӧ�õ�·��
    	  
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...rPath: "+rPath);
    	   rPath.replaceAll("//", "/");
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...rPath.replaceAll: "+rPath);
    	   
    	   File f = new File(webPath + htmlFilePath + "/" + htmlFileName);
    	   
    	   loger.debug("Java��"+this.getClass().getName()+" ʱ�䣺"+DateUtils.getCurDateTime()+" geneHtmlFile()[���ɾ�̬�ļ�]...File: "+webPath + htmlFilePath + "/" + htmlFileName);
//			FileInputStream fis = new FileInputStream(webPath + htmlFilePath + "/" + htmlFileName);
//		   System.out.println(fis.available());
////		   fis.close();
    	   fg.mergeFileToFtp(rPath,f);
    	   
    	   
    	   
       }
       catch (TemplateException e)
       {
	         System.out.print("Error while processing FreeMarker template " + templateFileName);
	         loger.info("CMS���ɾ�̬�ļ�ʧ��:"+StringUtils.exception2String(e));
	         return false;
       }
       catch (IOException e)
       {
       	System.out.print("Error while generate Static Html File " + htmlFileName);
       	loger.info("CMS���ɾ�̬�ļ�ʧ��:"+StringUtils.exception2String(e));
         return false;
       }

       return true;
   }
	
   
   /**
    * ��ȡfreemarker������. freemarker����֧��classpath,Ŀ¼�ʹ�ServletContext��ȡ.��ǰ��Ŀ¼�л�ȡ
    * @param ģ��·��
    */
   protected Configuration getFreeMarkerCFG(String temPath)
  {
      if (null == freemarker_cfg)
      {
        freemarker_cfg = new Configuration();
        try{
	          File tmpDir=new File(temPath);
	          freemarker_cfg.setDirectoryForTemplateLoading(tmpDir);
        }catch(Exception e){
      	  loger.info("CMS��ȡfreemarker������ʧ��:"+StringUtils.exception2String(e));
      	  System.out.print("Error while find dir File ");
        }
      }
      return freemarker_cfg;
  }
  
  
   //����ID���channel
   private CmsChannel findChannelByID(String channelID) {
	   CmsChannel channel = new CmsChannel();
	   QueryRule q = QueryRule.getInstance();
	   q.addEqual("channelID", Integer.valueOf(channelID));
	   List rst = cmsManageService.searchCMS(channel, q);
	   if(rst == null || rst.size() < 1) {
		   return null;
	   }
	   return (CmsChannel) rst.get(0);
   }
  
   //����ID���channel
   private List findChannelListByID(String channelID) {
	   CmsChannel channel = new CmsChannel();
	   QueryRule q = QueryRule.getInstance();
	   q.addEqual("channelID", Integer.valueOf(channelID));
	   return cmsManageService.searchCMS(channel, q);
   }
  
  public static void main(String[] args) throws IOException {
	CMSDistributeServiceSpringImpl test = new CMSDistributeServiceSpringImpl();
//	test.rootAll.put("test", "123");
	List l = new ArrayList();
	Document d = new Document();
	d.setAuthor("aa");
	Document d1 = new Document();
	d1.setAuthor("bb");
	l.add(d);
	l.add(d1);
	test.rootAll.put("arr", l);
//	test.rootAll.put("test", d);
	
	test.geneHtmlFile("test.ftl", "D:/log/", "D:/log/html", "123.html");
//	  File afile = new File("d:/aaa/aaa.jsp");
//	   if(afile.exists()) {
//		   afile.createNewFile();
//	   }
//	   afile.mkdir();
	
  }
}
