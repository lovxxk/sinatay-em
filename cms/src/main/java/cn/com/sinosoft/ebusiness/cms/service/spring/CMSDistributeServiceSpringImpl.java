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
	//模板存放路径
	public static String templetFolfer;
	//发布工程路径
	public static String webPath;
	//模板种类
	public static List templetTypeList;
	//发布结果代码
	public static final String NOBIND = "栏目未选择样式";
	public static final String NOCHANNEL = "栏目不存在";
	public static final String CODEEORROR = "系统异常";
	public static final String SUCCESS = "发布成功";
	@Autowired
	private CMSManageService cmsManageService;//底层实现类
	private Configuration freemarker_cfg = null;//freemarker入口类
	private Map rootAll = new HashMap();//freemaker参数
	//freemaker邪恶的转义，与el表达式的${}有冲突
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
		//加入转义
	}
	
	//根据栏目发布，发布栏目下部分文章-发布入口1
	public String pubChannel(String channelID, List docList) {
		Integer chlID = Integer.valueOf(channelID);
		//验证栏目是否存在
		CmsChannel chl = new CmsChannel();
		QueryRule q = QueryRule.getInstance();
		q.addEqual("channelID", chlID);
		List chlList = cmsManageService.searchCMS(chl, q);
		if(chlList == null || chlList.size() < 1) {
			return NOCHANNEL;
		}
		//待发布的栏目
		CmsChannel pubChl = (CmsChannel)chlList.get(0);
		//初始化模板参数
		this.initTempletContent(pubChl, docList);
		//获得发布路径
		String pubPath = this.getPubPath(pubChl);
		//获得栏目绑定模板
		CmsChannelTemplet bind = new CmsChannelTemplet();
		CmsTemplet templet = new CmsTemplet();
		QueryRule qct = QueryRule.getInstance();
		qct.addEqual("chlid", String.valueOf(chlID));
		List bindList = cmsManageService.searchCMS(bind, qct);
		if(bindList == null || bindList.size() < 1) {//未找到绑定
			return "NOBIND";
		}
		//发布每一个绑定模板
		for (int i = 0; i < bindList.size(); i++) {
			bind = (CmsChannelTemplet) bindList.get(i);
			String templetID = bind.getTid();
			QueryRule qf = QueryRule.getInstance();
			qf.addEqual("tplID", Integer.valueOf(templetID));
			List tList = cmsManageService.searchCMS(templet, qf);
			if(tList == null || tList.size() < 1) {//未找到模板
				return "NOBIND";
			}
			//对应的一个模板
			templet = (CmsTemplet) tList.get(0);
			//发布模板
			String tplType = templet.getTplType();
			int methodNum = templetTypeList.indexOf(tplType);
			Class cls = this.getClass();
			try {
				Method m  = cls.getDeclaredMethod("createPubFile" + methodNum, CmsTemplet.class, CmsChannel.class, String.class, List.class);
				m.invoke(this, templet, pubChl, pubPath, docList);
			} catch (Exception e) {
				loger.info("CMS根据栏目发布失败:"+StringUtils.exception2String(e));
				return "";
			}
		}
		return SUCCESS;
	}
	
	//根据栏目id发布栏目,发布所有内容-发布入口2
	public String pubChannel(String channelID) {
		List docList = this.getChlDoc(channelID);
		return this.pubChannel(channelID, docList);
	}
	
	//生成发布后的文件-文章列表
	private void createPubFile0(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = "index.jsp";
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//生成发布后的文件-文章明细（需要重新定制当前文章）
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
			
			//将文章发布标识置为已发布
			doc.setDocStatus("1");
			cmsManageService.updateCMS(doc);
		}
	}
	
	//生成发布后的文件-单篇文章
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
		
		//将文章发布标识置为已发布
		doc.setDocStatus("1");
		cmsManageService.updateCMS(doc);
	}
	
	//生成发布后的文件-特殊定制
	private void createPubFile3(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		//TODO
	}
	
	//生成发布后的文件-左侧菜单
	private void createPubFile4(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		if(!"1".equals(pubChl.getParentID())) {//只有2级栏目
			return;
		}
		String templetFileName = templet.getTplName();
		String pubName = "leftPanel.jsp";
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//生成发布后的文件-跳转子栏目
	private void createPubFile5(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = "index.jsp";
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//生成发布后的文件-包含
	private void createPubFile6(CmsTemplet templet, CmsChannel pubChl, String pubPath, List docList) {
		String templetFileName = templet.getTplName();
		String pubName = templetFileName.replaceAll("ftl", "jsp");
		this.geneHtmlFile(templetFileName, templetFolfer, pubPath, pubName);
	}
	
	//生成发布路径，根据其url和父栏目url迭代，如"/news/group/health/"
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
	
	//获得栏目下所有文章
	public List getChlDoc(String channelID) {
		CmsDocument doc = new CmsDocument();
		QueryRule q = QueryRule.getInstance();
		q.addEqual("docChannel", channelID);
		q.addDescOrder("docRelTime");
		return cmsManageService.searchCMS(doc, q);
	}
	
	//获得栏目下所有修改/新建的文章
	public List getChlModifyDoc(String channelID) {
		CmsDocument doc = new CmsDocument();
		QueryRule q = QueryRule.getInstance();
		q.addEqual("docChannel", channelID);
		q.addNotEqual("docStatus", "1");
		return cmsManageService.searchCMS(doc, q);
//		return null;
	}
	
	//组装所有的模板内容
	private void initTempletContent(CmsChannel pubChl, List docList) {
		//转义变量
		rootAll.put("ctx", ctx);
		//首个子栏目
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
		
		
		//文章
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
		
		//本栏目信息
		Channel channelPojo = new Channel();
		channelPojo.setChannelName(pubChl.getChnlDesName());
		channelPojo.setChannelUrl(pubChl.getLinkUrl());
		channelPojo.setChannelPath(this.getPubPath(pubChl));
		//父栏目信息
		String parentID = pubChl.getParentID();
		CmsChannel pChannel = this.findChannelByID(parentID);
		if("1".equals(pChannel.getParentID())) {//二级栏目，父栏目和一级栏目相同
			channelPojo.setParentName(pChannel.getChnlDesName());
			channelPojo.setParentPath(this.getPubPath(pChannel));
			channelPojo.setParentUrl(pChannel.getLinkUrl());
			channelPojo.setRootChannelName(pChannel.getChnlDesName());
			channelPojo.setRootChannelUrl(channelPojo.getParentPath());
			channelPojo.setRootUrl(pChannel.getLinkUrl());
		} else if(!"1".equals(parentID)) {//三级栏目
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
		
		//左侧菜单,只有一级栏目默认有左侧菜单*新增二级栏目也可以绑定左侧菜单
		if("1".equals(pubChl.getParentID()) ||"1".equals(pChannel.getParentID()) ) {
			//找到该一级栏目下所有二级栏目
			CmsChannel chl2 = new CmsChannel();
			QueryRule q = QueryRule.getInstance();
			q.addEqual("parentID", String.valueOf(pubChl.getChannelID()));
			q.addDescOrder("chnlOrder");
			List chl2List = cmsManageService.searchCMS(chl2, q);//该一级栏目下所有二级栏目
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
     * 生成静态文件.现在除了在本地生成外，还需要在对应的负载服务器上生成
     * @param templateFileName 模板文件名,例如"111.ftl"
     * @param temPath 模板文件临时存储路径 ，例如"/tpxw/view.ftl"
     * @param htmlFilePath 要生成的静态文件的路径,相对设置中的根路径,例如 "/tpxw/1/2005/4/"
     * @param htmlFileName 要生成的文件名,例如 "1.htm"
     */
   public boolean geneHtmlFile(String templateFileName, String temPath, String htmlFilePath,String htmlFileName)
   {
	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...");
        Map propMap = this.rootAll;//用于处理模板的属性Object映射
       try
       {
    	   freemarker_cfg = getFreeMarkerCFG(temPath);
    	   Template t = freemarker_cfg.getTemplate(templateFileName, "GBK");
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...htmlFilePath==="+htmlFilePath);
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...htmlFileName==="+htmlFileName);
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...temPath==="+temPath);
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...templateFileName==="+templateFileName);
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
    	   fg.setPathType(FileGenerate.RELATED_PATH);//设置为相对路径
    	   String rPath = "/web/" + htmlFilePath;//相对于应用的路径
    	  
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...rPath: "+rPath);
    	   rPath.replaceAll("//", "/");
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...rPath.replaceAll: "+rPath);
    	   
    	   File f = new File(webPath + htmlFilePath + "/" + htmlFileName);
    	   
    	   loger.debug("Java："+this.getClass().getName()+" 时间："+DateUtils.getCurDateTime()+" geneHtmlFile()[生成静态文件]...File: "+webPath + htmlFilePath + "/" + htmlFileName);
//			FileInputStream fis = new FileInputStream(webPath + htmlFilePath + "/" + htmlFileName);
//		   System.out.println(fis.available());
////		   fis.close();
    	   fg.mergeFileToFtp(rPath,f);
    	   
    	   
    	   
       }
       catch (TemplateException e)
       {
	         System.out.print("Error while processing FreeMarker template " + templateFileName);
	         loger.info("CMS生成静态文件失败:"+StringUtils.exception2String(e));
	         return false;
       }
       catch (IOException e)
       {
       	System.out.print("Error while generate Static Html File " + htmlFileName);
       	loger.info("CMS生成静态文件失败:"+StringUtils.exception2String(e));
         return false;
       }

       return true;
   }
	
   
   /**
    * 获取freemarker的配置. freemarker本身支持classpath,目录和从ServletContext获取.当前从目录中获取
    * @param 模板路径
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
      	  loger.info("CMS获取freemarker的配置失败:"+StringUtils.exception2String(e));
      	  System.out.print("Error while find dir File ");
        }
      }
      return freemarker_cfg;
  }
  
  
   //根据ID获得channel
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
  
   //根据ID获得channel
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
