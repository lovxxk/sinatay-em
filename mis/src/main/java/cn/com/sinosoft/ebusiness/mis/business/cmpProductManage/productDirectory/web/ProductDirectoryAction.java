package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productDirectory.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.FileUtils;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.JSONUtils;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.BizCommonService;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeId;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeInfo;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectoryAttributeRelate;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeProductCorrelation;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeneratorGeDirectoryEId;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryAttributeInfoService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryAttributeRelateService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeProductCorrelationService;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductMain;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.ProductManageService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.util.reflect.ConvertUtils;

/***
 *  
 * @version 1 created on 2011-9-16 上午10:42:41
 */
public class ProductDirectoryAction extends Struts2Action {

	private static final long serialVersionUID = -2156785553645263348L;

	private GeDirectoryAttributeInfoService geDirectoryAttributeInfoService;

	private GeDirectoryService geDirectoryService;

	private GeDirectoryAttributeRelateService geDirectoryAttributeRelateService;

	private ProductManageService productManageService;
	
	private GeDirectory geDirectory;
	
	private GeDirectoryAttributeInfo geDirectoryAttributeInfo;
	
	private GeDirectoryAttributeRelate geDirectoryAttributeRelate;
	
	private GeCodeService geCodeService;/**数据字典服务类*/
	
	private BizCommonService bizCommonService;
	
	/**产品推荐*/
	private GeProductCorrelationService geProductCorrelationService;
	
	public List<GeDirectory> productDirectoryList;

	private ArrayList<GeProductMain> productMains; // 根据业务领域取得产品信息

	private List<GeCode> businessAreaList;/**证件类型列表*/
	
	private final  static String BusinessArea = "BusinessArea";
	
	private final  static String jtStr = "1";//业务领域中集团对应的代码

	private File attrSmallPictureOne;
	
	private String attrSmallPictureOneFileName;

	private File attrSmallPictureTwo;
	
	private String attrSmallPictureTwoFileName;

	private File attrMiddlePictureOne;
	
	private String attrMiddlePictureOneFileName;

	private File attrMiddlePictureTwo;
	
	private String attrMiddlePictureTwoFileName;

	private File attrBigPictureOne;
	
	private String attrBigPictureOneFileName;

	private File attrBigPictureTwo;
	
	private String attrBigPictureTwoFileName;
	
	private File smallPictureOne;
	
	private String smallPictureOneFileName;

	private File smallPictureTwo;
	
	private String smallPictureTwoFileName;

	private File middlePictureOne;
	
	private String middlePictureOneFileName;

	private File middlePictureTwo;
	
	private String middlePictureTwoFileName;

	private File bigPictureOne;
	
	private String bigPictureOneFileName;

	private File bigPictureTwo;	
	
	private String bigPictureTwoFileName;
	
	private String nameCount;//用于营销活动
	
	private String xrule;//用于营销活动的 归责中的加购产品第几条规则
	
	private String yaddShopping;//用于营销活动归则中第几个加购产品

	private Page page;
	
	/**
	 * json格式化配置
	 */
	private JsonConfig jc ;
	private static final String imagePath = "upload/images/imageL/";
	
//	private static final String imageAbsolutePath = "D:/workspaces/sanSumSpace/mis/src/main/webapp/upload/images/imageL/";
	
//	private static final String imageOnlineAbsolutePath = "D:/chinalifeWorkSpace/online/src/main/webapp/upload/images/imageL/";
	
	
	private static final String imageAbsolutePath = getProjectLocalPath() + "/mis_war.ear/mis.war/upload/images/imageL/";
	
	/*	private static final String imageOnlineAbsolutePath = getProjectLocalPath() + "/online_war.ear/online.war/upload/images/imageL/";*/
	
	public GeDirectoryAttributeRelate getGeDirectoryAttributeRelate() {
		return geDirectoryAttributeRelate;
	}
	public void setGeDirectoryAttributeRelate(
			GeDirectoryAttributeRelate geDirectoryAttributeRelate) {
		this.geDirectoryAttributeRelate = geDirectoryAttributeRelate;
	}

	public GeDirectoryAttributeInfoService getGeDirectoryAttributeInfoService() {
		return geDirectoryAttributeInfoService;
	}

	public void setGeDirectoryAttributeInfoService(
			GeDirectoryAttributeInfoService geDirectoryAttributeInfoService) {
		this.geDirectoryAttributeInfoService = geDirectoryAttributeInfoService;
	}

	public GeDirectoryService getGeDirectoryService() {
		return geDirectoryService;
	}

	public void setGeDirectoryService(GeDirectoryService geDirectoryService) {
		this.geDirectoryService = geDirectoryService;
	}

	public GeDirectoryAttributeRelateService getGeDirectoryAttributeRelateService() {
		return geDirectoryAttributeRelateService;
	}

	public void setGeDirectoryAttributeRelateService(GeDirectoryAttributeRelateService geDirectoryAttributeRelateService) {
		this.geDirectoryAttributeRelateService = geDirectoryAttributeRelateService;
	}

	public ProductManageService getProductManageService() {
		return productManageService;
	}

	public void setProductManageService(ProductManageService productManageService) {
		this.productManageService = productManageService;
	}
	
	
	public GeProductCorrelationService getGeProductCorrelationService() {
		return geProductCorrelationService;
	}
	
	public void setGeProductCorrelationService(GeProductCorrelationService geProductCorrelationService) {
		this.geProductCorrelationService = geProductCorrelationService;
	}
	
	public GeDirectory getGeDirectory() {
		return geDirectory;
	}

	public void setGeDirectory(GeDirectory geDirectory) {
		this.geDirectory = geDirectory;
	}

	public GeDirectoryAttributeInfo getGeDirectoryAttributeInfo() {
		return geDirectoryAttributeInfo;
	}

	public void setGeDirectoryAttributeInfo(
			GeDirectoryAttributeInfo geDirectoryAttributeInfo) {
		this.geDirectoryAttributeInfo = geDirectoryAttributeInfo;
	}
	
	public BizCommonService getBizCommonService() {
		return bizCommonService;
	}
	
	public void setBizCommonService(BizCommonService bizCommonService) {
		this.bizCommonService = bizCommonService;
	}
	
	public List<GeDirectory> getProductDirectoryList() {
		return productDirectoryList;
	}

	public void setProductDirectoryList(List<GeDirectory> productDirectoryList) {
		this.productDirectoryList = productDirectoryList;
	}

	public ArrayList<GeProductMain> getProductMains() {
		return productMains;
	}

	public void setProductMains(ArrayList<GeProductMain> productMains) {
		this.productMains = productMains;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	
	public File getAttrPicture() {
		return attrSmallPictureOne;
	}

	public File getAttrSmallPictureOne() {
		return attrSmallPictureOne;
	}

	public void setAttrSmallPictureOne(File attrSmallPictureOne) {
		this.attrSmallPictureOne = attrSmallPictureOne;
	}

	public String getAttrSmallPictureOneFileName() {
		return attrSmallPictureOneFileName;
	}

	public void setAttrSmallPictureOneFileName(String attrSmallPictureOneFileName) {
		this.attrSmallPictureOneFileName = attrSmallPictureOneFileName;
	}

	public void setAttrPictureFileName(String attrSmallPictureOneFileName) {
		this.attrSmallPictureOneFileName = attrSmallPictureOneFileName;
	}

	public File getAttrSmallPictureTwo() {
		return attrSmallPictureTwo;
	}

	public void setAttrSmallPictureTwo(File attrSmallPictureTwo) {
		this.attrSmallPictureTwo = attrSmallPictureTwo;
	}

	public String getAttrSmallPictureTwoFileName() {
		return attrSmallPictureTwoFileName;
	}

	public void setAttrSmallPictureTwoFileName(String attrSmallPictureTwoFileName) {
		this.attrSmallPictureTwoFileName = attrSmallPictureTwoFileName;
	}

	public File getAttrMiddlePictureOne() {
		return attrMiddlePictureOne;
	}

	public void setAttrMiddlePictureOne(File attrMiddlePictureOne) {
		this.attrMiddlePictureOne = attrMiddlePictureOne;
	}

	public String getAttrMiddlePictureOneFileName() {
		return attrMiddlePictureOneFileName;
	}

	public void setAttrMiddlePictureOneFileName(String attrMiddlePictureOneFileName) {
		this.attrMiddlePictureOneFileName = attrMiddlePictureOneFileName;
	}

	public File getAttrMiddlePictureTwo() {
		return attrMiddlePictureTwo;
	}

	public void setAttrMiddlePictureTwo(File attrMiddlePictureTwo) {
		this.attrMiddlePictureTwo = attrMiddlePictureTwo;
	}

	public String getAttrMiddlePictureTwoFileName() {
		return attrMiddlePictureTwoFileName;
	}

	public void setAttrMiddlePictureTwoFileName(String attrMiddlePictureTwoFileName) {
		this.attrMiddlePictureTwoFileName = attrMiddlePictureTwoFileName;
	}

	public File getAttrBigPictureOne() {
		return attrBigPictureOne;
	}

	public void setAttrBigPictureOne(File attrBigPictureOne) {
		this.attrBigPictureOne = attrBigPictureOne;
	}

	public String getAttrBigPictureOneFileName() {
		return attrBigPictureOneFileName;
	}

	public void setAttrBigPictureOneFileName(String attrBigPictureOneFileName) {
		this.attrBigPictureOneFileName = attrBigPictureOneFileName;
	}

	public File getAttrBigPictureTwo() {
		return attrBigPictureTwo;
	}

	public void setAttrBigPictureTwo(File attrBigPictureTwo) {
		this.attrBigPictureTwo = attrBigPictureTwo;
	}

	public String getAttrBigPictureTwoFileName() {
		return attrBigPictureTwoFileName;
	}

	public void setAttrBigPictureTwoFileName(String attrBigPictureTwoFileName) {
		this.attrBigPictureTwoFileName = attrBigPictureTwoFileName;
	}
	
	public File getSmallPictureOne() {
		return smallPictureOne;
	}

	public void setSmallPictureOne(File smallPictureOne) {
		this.smallPictureOne = smallPictureOne;
	}

	public String getSmallPictureOneFileName() {
		return smallPictureOneFileName;
	}

	public void setSmallPictureOneFileName(String smallPictureOneFileName) {
		this.smallPictureOneFileName = smallPictureOneFileName;
	}

	public File getSmallPictureTwo() {
		return smallPictureTwo;
	}

	public void setSmallPictureTwo(File smallPictureTwo) {
		this.smallPictureTwo = smallPictureTwo;
	}

	public String getSmallPictureTwoFileName() {
		return smallPictureTwoFileName;
	}

	public void setSmallPictureTwoFileName(String smallPictureTwoFileName) {
		this.smallPictureTwoFileName = smallPictureTwoFileName;
	}

	public File getMiddlePictureOne() {
		return middlePictureOne;
	}

	public void setMiddlePictureOne(File middlePictureOne) {
		this.middlePictureOne = middlePictureOne;
	}

	public String getMiddlePictureOneFileName() {
		return middlePictureOneFileName;
	}

	public void setMiddlePictureOneFileName(String middlePictureOneFileName) {
		this.middlePictureOneFileName = middlePictureOneFileName;
	}

	public File getMiddlePictureTwo() {
		return middlePictureTwo;
	}

	public void setMiddlePictureTwo(File middlePictureTwo) {
		this.middlePictureTwo = middlePictureTwo;
	}

	public String getMiddlePictureTwoFileName() {
		return middlePictureTwoFileName;
	}

	public void setMiddlePictureTwoFileName(String middlePictureTwoFileName) {
		this.middlePictureTwoFileName = middlePictureTwoFileName;
	}

	public File getBigPictureOne() {
		return bigPictureOne;
	}

	public void setBigPictureOne(File bigPictureOne) {
		this.bigPictureOne = bigPictureOne;
	}

	public String getBigPictureOneFileName() {
		return bigPictureOneFileName;
	}

	public void setBigPictureOneFileName(String bigPictureOneFileName) {
		this.bigPictureOneFileName = bigPictureOneFileName;
	}

	public File getBigPictureTwo() {
		return bigPictureTwo;
	}

	public void setBigPictureTwo(File bigPictureTwo) {
		this.bigPictureTwo = bigPictureTwo;
	}

	public String getBigPictureTwoFileName() {
		return bigPictureTwoFileName;
	}

	public void setBigPictureTwoFileName(String bigPictureTwoFileName) {
		this.bigPictureTwoFileName = bigPictureTwoFileName;
	}

	public List<GeCode> getBusinessAreaList() {
		return businessAreaList;
	}

	public void setBusinessAreaList(List<GeCode> businessAreaList) {
		this.businessAreaList = businessAreaList;
	}
	
	public GeCodeService getGeCodeService() {
		return geCodeService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}
	
	public String getNameCount() {
		return nameCount;
	}
	
	public void setNameCount(String nameCount) {
		this.nameCount = nameCount;
	}
	public String getYaddShopping() {
		return yaddShopping;
	}
	public void setYaddShopping(String yaddShopping) {
		this.yaddShopping = yaddShopping;
	}
	public String getXrule() {
		return xrule;
	}
	public void setXrule(String xrule) {
		this.xrule = xrule;
	}
	/***
	 * 添加产品目录
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_addProductDirectory")
	public String addProductDirectory() {
		try {
			geDirectory.setEid(GeneratorGeDirectoryEId.generatorProductEId(geDirectory.getBusinessArea()));
			if(smallPictureOne != null){
				String smallPictureOnefileName = geDirectory.getEid() + "_SmallPictureOne" + FileUtils.getFileNameExt(smallPictureOneFileName);
				File smallPictureOnePictureFile = new File(imageAbsolutePath + smallPictureOnefileName);
//				File onlineSmallPictureOnePictureFile = new File(imageOnlineAbsolutePath + smallPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(smallPictureOne), smallPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(smallPictureOne), onlineSmallPictureOnePictureFile);
				geDirectory.setSmallPictureOne(imagePath + smallPictureOnefileName);
			}
			
			if(smallPictureTwo != null){
				String smallPictureTwofileName = geDirectory.getEid() + "_SmallPictureTwo" +  FileUtils.getFileNameExt(smallPictureTwoFileName);
				File smallPictureTwoPictureFile = new File(imageAbsolutePath + smallPictureTwofileName);
//				File onlineSmallPictureTwoPictureFile = new File(imageOnlineAbsolutePath + smallPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(smallPictureTwo), smallPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(smallPictureTwo), onlineSmallPictureTwoPictureFile);
				geDirectory.setSmallPictureTwo(imagePath + smallPictureTwofileName);
			}
			
			if(middlePictureOne != null){
				String middlePictureOnefileName = geDirectory.getEid() + "_MiddlePictureOne" +  FileUtils.getFileNameExt(middlePictureOneFileName);
				File middlePictureOnePictureFile = new File(imageAbsolutePath + middlePictureOnefileName);
//				File onlineMiddlePictureOnePictureFile = new File(imageOnlineAbsolutePath + middlePictureOnefileName);
				FileUtils.write(FileUtils.readBytes(middlePictureOne), middlePictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(middlePictureOne), onlineMiddlePictureOnePictureFile);
				geDirectory.setMiddlePictureOne(imagePath + middlePictureOnefileName);
			}
			
			if(middlePictureTwo != null){
				String middlePictureTwofileName = geDirectory.getEid() + "_MiddlePictureTwo" +  FileUtils.getFileNameExt(middlePictureTwoFileName);
				File middlePictureTwoPictureFile = new File(imageAbsolutePath + middlePictureTwofileName);
//				File onlineMiddlePictureTwoPictureFile = new File(imageOnlineAbsolutePath + middlePictureTwofileName);
				FileUtils.write(FileUtils.readBytes(middlePictureTwo), middlePictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(middlePictureTwo), onlineMiddlePictureTwoPictureFile);
				geDirectory.setMiddlePictureTwo(imagePath + middlePictureTwofileName);
			}
			
			if(bigPictureOne != null){
				String bigPictureOnefileName = geDirectory.getEid() + "_BigPictureOne" +  FileUtils.getFileNameExt(bigPictureOneFileName);
				File bigPictureOnePictureFile = new File(imageAbsolutePath + bigPictureOnefileName);
//				File onlineBigPictureOnePictureFile = new File(imageOnlineAbsolutePath + bigPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(bigPictureOne), bigPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(bigPictureOne), onlineBigPictureOnePictureFile);
				geDirectory.setBigPictureOne(imagePath + bigPictureOnefileName);
			}
			
			if(bigPictureTwo != null){
				String bigPictureTwofileName = geDirectory.getEid() + "_BigPictureTwo" +  FileUtils.getFileNameExt(bigPictureTwoFileName);
				File bigPictureTwoPictureFile = new File(imageAbsolutePath + bigPictureTwofileName);
//				File onlineBigPictureTwoPictureFile = new File(imageOnlineAbsolutePath + bigPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(bigPictureTwo), bigPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(bigPictureTwo), onlineBigPictureTwoPictureFile);
				geDirectory.setBigPictureTwo(imagePath + bigPictureTwofileName);
			}
			geDirectory.setCreateDate(new Date());
			geDirectory.setIsProductShelf("02");
			geDirectoryService.addGeDirectory(geDirectory);
			super.getRequest().setAttribute("addReslut", "success");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addReslut", "failure");
		} 
		
		/**查询数据字典的业务领域*/
		try {
			businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
			List<GeCode> saleChannelList = geCodeService.findAllByGeCodeType("SaleChannel");//销售渠道
			List<GeCode> acceptInsuranceList = geCodeService.findAllByGeCodeType("AcceptInsurance");//承保方式
			List<GeCode> productSectionList = geCodeService.findAllByGeCodeType("ProductSection");//产品栏目
			List<GeCode> itemTypeList = geCodeService.findAllByGeCodeType("ItemType");//标的类型
			List<GeCode> riskLimitList = geCodeService.findAllByGeCodeType("RiskLimit");//险种期限
			List<GeCode> productRecommendList = geCodeService.findAllByGeCodeType("ProductRecommend");//产品推荐评分
			
			Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_B_PDIR_I");//拿到该功能的所有机构权限
			
			for (int i = businessAreaList.size() -1; i >= 0; i--) {
				String baStr = businessAreaList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					businessAreaList.remove(i);
				}
			}
			HttpServletRequest request = super.getRequest();
			request.setAttribute("saleChannelList", saleChannelList);
			request.setAttribute("acceptInsuranceList", acceptInsuranceList);
			request.setAttribute("productSectionList", productSectionList);
			request.setAttribute("itemTypeList", itemTypeList);
			request.setAttribute("riskLimitList", riskLimitList);
			request.setAttribute("productRecommendList", productRecommendList);
		} catch (BizConfigCommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查询数据字典出现异常，异常信息："+e.getMsg());
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}


	/**
	 * 修改产品目录信息
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_updateGeDirectory")
	public String updateGeDirectory() {
		
		try {
			if(smallPictureOne != null){
				String smallPictureOnefileName = geDirectory.getEid() + "_SmallPictureOne" + FileUtils.getFileNameExt(smallPictureOneFileName);
				File smallPictureOnePictureFile = new File(imageAbsolutePath + smallPictureOnefileName);
//				File onlineSmallPictureOnePictureFile = new File(imageOnlineAbsolutePath + smallPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(smallPictureOne), smallPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(smallPictureOne), onlineSmallPictureOnePictureFile);
				geDirectory.setSmallPictureOne(imagePath + smallPictureOnefileName);
			}
			if(smallPictureTwo != null){
				String smallPictureTwofileName = geDirectory.getEid() + "_SmallPictureTwo" +  FileUtils.getFileNameExt(smallPictureTwoFileName);
				File smallPictureTwoPictureFile = new File(imageAbsolutePath + smallPictureTwofileName);
//				File onlineSmallPictureTwoPictureFile = new File(imageOnlineAbsolutePath + smallPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(smallPictureTwo), smallPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(smallPictureTwo), onlineSmallPictureTwoPictureFile);
				geDirectory.setSmallPictureTwo(imagePath + smallPictureTwofileName);
			}
			if(middlePictureOne != null){
				String middlePictureOnefileName = geDirectory.getEid() + "_MiddlePictureOne" +  FileUtils.getFileNameExt(middlePictureOneFileName);
				File middlePictureOnePictureFile = new File(imageAbsolutePath + middlePictureOnefileName);
//				File onlineMiddlePictureOnePictureFile = new File(imageOnlineAbsolutePath + middlePictureOnefileName);
				FileUtils.write(FileUtils.readBytes(middlePictureOne), middlePictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(middlePictureOne), onlineMiddlePictureOnePictureFile);
				geDirectory.setMiddlePictureOne(imagePath + middlePictureOnefileName);
			}
			if(middlePictureTwo != null){
				String middlePictureTwofileName = geDirectory.getEid() + "_MiddlePictureTwo" +  FileUtils.getFileNameExt(middlePictureTwoFileName);
				File middlePictureTwoPictureFile = new File(imageAbsolutePath + middlePictureTwofileName);
//				File onlineMiddlePictureTwoPictureFile = new File(imageOnlineAbsolutePath + middlePictureTwofileName);
				FileUtils.write(FileUtils.readBytes(middlePictureTwo), middlePictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(middlePictureTwo), onlineMiddlePictureTwoPictureFile);
				geDirectory.setMiddlePictureTwo(imagePath + middlePictureTwofileName);
			}
			if(bigPictureOne != null){
				String bigPictureOnefileName = geDirectory.getEid() + "_BigPictureOne" +  FileUtils.getFileNameExt(bigPictureOneFileName);
				File bigPictureOnePictureFile = new File(imageAbsolutePath + bigPictureOnefileName);
//				File onlineBigPictureOnePictureFile = new File(imageOnlineAbsolutePath + bigPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(bigPictureOne), bigPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(bigPictureOne), onlineBigPictureOnePictureFile);
				geDirectory.setBigPictureOne(imagePath + bigPictureOnefileName);
			}
			if(bigPictureTwo != null){
				String bigPictureTwofileName = geDirectory.getEid() + "_BigPictureTwo" +  FileUtils.getFileNameExt(bigPictureTwoFileName);
				File bigPictureTwoPictureFile = new File(imageAbsolutePath + bigPictureTwofileName);
//				File onlineBigPictureTwoPictureFile = new File(imageOnlineAbsolutePath + bigPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(bigPictureTwo), bigPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(bigPictureTwo), onlineBigPictureTwoPictureFile);
				geDirectory.setBigPictureTwo(imagePath + bigPictureTwofileName);
			}
			geDirectoryService.updateGeDirectory(geDirectory);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "操作失败！");
		}
		return SUCCESS;
	}

	/***
	 * 更新产品上架下架状态
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_updateProductShelf")
	public String updateProductShelf() {
		try {
			String eid = super.getRequest().getParameter("eid");
			String isProductShelf = super.getRequest().getParameter("isProductShelf");
			GeDirectory update = geDirectoryService.findGeDirectoryByEId(eid);
			String productShelf = "01".equals(isProductShelf) ? "上架" : "下架";
			if (isProductShelf.equals(update.getIsProductShelf())) {
				super.getRequest().setAttribute("flag", "0");
				super.getRequest().setAttribute("message", "该产品已经" + productShelf + ", 不能再进行" + productShelf + "操作！");
			} else {
				update.setIsProductShelf(isProductShelf);
				geDirectoryService.updateProductShelf(update);
				super.getRequest().setAttribute("flag", "1");
				super.getRequest().setAttribute("message", "操作成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "操作失败！");
		}
		return SUCCESS;
	}

	/***
	 * 删除产品目录
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_delProductDirectory")
	public String delProductDirectory() {

		try {
			String eId = super.getRequest().getParameter("eid");
			GeDirectory geDirectory = geDirectoryService.findGeDirectoryByEId(eId);
			if ("01".equals(geDirectory.getIsProductShelf())) {
				super.getRequest().setAttribute("flag", "0");
				super.getRequest().setAttribute("message", "上架产品不允许删除！");
			} else {
				geDirectoryService.deleteGeDirectory(eId);
				super.getRequest().setAttribute("flag", "1");
				super.getRequest().setAttribute("message", "删除成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "操作失败！");
		}
		return SUCCESS;
	}

	/***
	 * 添加产品目录属性
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_addGeDirectoryAttributeInfo")
	public String addGeDirectoryAttributeInfo() {
		try {
			if (attrSmallPictureOne != null) {
				String attrSmallPictureOnefileName = geDirectoryAttributeInfo.getAttrID() + "_AttrSmallPictureOne" + FileUtils.getFileNameExt(attrSmallPictureOneFileName);
				File attrSmallPictureOnePictureFile = new File(imageAbsolutePath + attrSmallPictureOnefileName);
//				File onlineAttrSmallPictureOnePictureFile = new File(imageOnlineAbsolutePath + attrSmallPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(attrSmallPictureOne), attrSmallPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(attrSmallPictureOne), onlineAttrSmallPictureOnePictureFile);
				geDirectoryAttributeInfo.setAttrSmallPictureOne(imagePath + attrSmallPictureOnefileName);
			}
			if (attrSmallPictureTwo != null) {
				String attrSmallPictureTwofileName = geDirectoryAttributeInfo.getAttrID() + "_AttrSmallPictureTwo" + FileUtils.getFileNameExt(attrSmallPictureTwoFileName);
				File attrSmallPictureTwoPictureFile = new File(imageAbsolutePath + attrSmallPictureTwofileName);
//				File onlineAttrSmallPictureTwoPictureFile = new File(imageOnlineAbsolutePath + attrSmallPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(attrSmallPictureTwo), attrSmallPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(attrSmallPictureTwo), onlineAttrSmallPictureTwoPictureFile);
				geDirectoryAttributeInfo.setAttrSmallPictureTwo(imagePath + attrSmallPictureTwofileName);
			}
			if (attrMiddlePictureOne != null) {
				String attrMiddlePictureOnefileName = geDirectoryAttributeInfo.getAttrID() + "_AttrMiddlePictureOne" + FileUtils.getFileNameExt(attrMiddlePictureOneFileName);
				File attrMiddlePictureOnePictureFile = new File(imageAbsolutePath + attrMiddlePictureOnefileName);
//				File onlineAttrMiddlePictureOnefileName = new File(imageOnlineAbsolutePath + attrMiddlePictureOnefileName);
				FileUtils.write(FileUtils.readBytes(attrMiddlePictureOne), attrMiddlePictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(attrMiddlePictureOne), onlineAttrMiddlePictureOnefileName);
				geDirectoryAttributeInfo.setAttrMiddlePictureOne(imagePath + attrMiddlePictureOnefileName);
			}
			if (attrMiddlePictureTwo != null) {
				String attrMiddlePictureTwofileName = geDirectoryAttributeInfo.getAttrID() + "_AttrMiddlePictureTwo" + FileUtils.getFileNameExt(attrMiddlePictureTwoFileName);
				File attrMiddlePictureTwoPictureFile = new File(imageAbsolutePath + attrMiddlePictureTwofileName);
//				File onlineAttrMiddlePictureTwoPictureFile = new File(imageOnlineAbsolutePath + attrMiddlePictureTwofileName);
				FileUtils.write(FileUtils.readBytes(attrMiddlePictureTwo), attrMiddlePictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(attrMiddlePictureTwo), onlineAttrMiddlePictureTwoPictureFile);
				geDirectoryAttributeInfo.setAttrMiddlePictureTwo(imagePath + attrMiddlePictureTwofileName);
			}
			if (attrBigPictureOne != null) {
				String attrBigPictureOnefileName = geDirectoryAttributeInfo.getAttrID() + "_AttrBigPictureOne" + FileUtils.getFileNameExt(attrBigPictureOneFileName);
				File attrBigPictureOnePictureFile = new File(imageAbsolutePath + attrBigPictureOnefileName);
//				File onlineAttrBigPictureOnePictureFile = new File(imageOnlineAbsolutePath + attrBigPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(attrBigPictureOne), attrBigPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(attrBigPictureOne), onlineAttrBigPictureOnePictureFile);
				geDirectoryAttributeInfo.setAttrBigPictureOne(imagePath + attrBigPictureOnefileName);
			}
			if (attrBigPictureTwo != null) {
				String attrBigPictureTwofileName = geDirectoryAttributeInfo.getAttrID() + "_AttrBigPictureTwo" + FileUtils.getFileNameExt(attrBigPictureTwoFileName);
				File attrBigPictureTwoPictureFile = new File(imageAbsolutePath + attrBigPictureTwofileName);
//				File onlineAttrBigPictureTwoPictureFile = new File(imageOnlineAbsolutePath + attrBigPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(attrBigPictureTwo), attrBigPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(attrBigPictureOne), onlineAttrBigPictureTwoPictureFile);
				geDirectoryAttributeInfo.setAttrBigPictureTwo(imagePath + attrBigPictureTwofileName);
			}
			geDirectoryAttributeInfoService.addGeDirectoryAttributeInfo(geDirectoryAttributeInfo);
			super.getRequest().setAttribute("attrId", geDirectoryAttributeInfo.getAttrID());
			super.getRequest().setAttribute("message", "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("message", "失败！");
		} finally {
			super.getRequest().setAttribute("parentAttrID", geDirectoryAttributeInfo.getGeDirectoryAttributeInfo().getAttrID());
		}
		return SUCCESS;
	}

	/**
	 * 修改产品目录属性
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@LocusTrace(setCode = "ProductDirectoryAction_updateGeDirectoryAttributeInfo")
	public String updateGeDirectoryAttributeInfo() {
		
		try {
			if(attrSmallPictureOne != null){
				String attrSmallPictureOnefileName = geDirectoryAttributeInfo.getAttrID() + "_AttrSmallPictureOne" + FileUtils.getFileNameExt(attrSmallPictureOneFileName);
				File attrSmallPictureOnePictureFile = new File(imageAbsolutePath + attrSmallPictureOnefileName);
//				File onlineAttrSmallPictureOnePictureFile = new File(imageOnlineAbsolutePath + attrSmallPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(attrSmallPictureOne), attrSmallPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(attrSmallPictureOne), onlineAttrSmallPictureOnePictureFile);
				geDirectoryAttributeInfo.setAttrSmallPictureOne(imagePath + attrSmallPictureOnefileName);
			}
			if(attrSmallPictureTwo != null){
				String attrSmallPictureTwofileName = geDirectoryAttributeInfo.getAttrID() + "_AttrSmallPictureTwo" + FileUtils.getFileNameExt(attrSmallPictureTwoFileName);
				File attrSmallPictureTwoPictureFile = new File(imageAbsolutePath + attrSmallPictureTwofileName);
//				File onlineAttrSmallPictureTwoPictureFile = new File(imageOnlineAbsolutePath + attrSmallPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(attrSmallPictureTwo), attrSmallPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(attrSmallPictureTwo), onlineAttrSmallPictureTwoPictureFile);
				geDirectoryAttributeInfo.setAttrSmallPictureTwo(imagePath + attrSmallPictureTwofileName);
			}
			if(attrMiddlePictureOne != null){
				String attrMiddlePictureOnefileName = geDirectoryAttributeInfo.getAttrID() + "_AttrMiddlePictureOne" + FileUtils.getFileNameExt(attrMiddlePictureOneFileName);
				File attrMiddlePictureOnePictureFile = new File(imageAbsolutePath + attrMiddlePictureOnefileName);
//				File onlineAttrMiddlePictureOnefileName = new File(imageOnlineAbsolutePath + attrMiddlePictureOnefileName);
				FileUtils.write(FileUtils.readBytes(attrMiddlePictureOne), attrMiddlePictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(attrMiddlePictureOne), onlineAttrMiddlePictureOnefileName);
				geDirectoryAttributeInfo.setAttrMiddlePictureOne(imagePath + attrMiddlePictureOnefileName);
			}
			if(attrMiddlePictureTwo != null){
				String attrMiddlePictureTwofileName = geDirectoryAttributeInfo.getAttrID() + "_AttrMiddlePictureTwo" + FileUtils.getFileNameExt(attrMiddlePictureTwoFileName);
				File attrMiddlePictureTwoPictureFile = new File(imageAbsolutePath + attrMiddlePictureTwofileName);
//				File onlineAttrMiddlePictureTwoPictureFile = new File(imageOnlineAbsolutePath + attrMiddlePictureTwofileName);
				FileUtils.write(FileUtils.readBytes(attrMiddlePictureTwo), attrMiddlePictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(attrMiddlePictureTwo), onlineAttrMiddlePictureTwoPictureFile);
				geDirectoryAttributeInfo.setAttrMiddlePictureTwo(imagePath + attrMiddlePictureTwofileName);
			}
			if(attrBigPictureOne != null){
				String attrBigPictureOnefileName = geDirectoryAttributeInfo.getAttrID() + "_AttrBigPictureOne" + FileUtils.getFileNameExt(attrBigPictureOneFileName);
				File attrBigPictureOnePictureFile = new File(imageAbsolutePath + attrBigPictureOnefileName);
//				File onlineAttrBigPictureOnePictureFile = new File(imageOnlineAbsolutePath + attrBigPictureOnefileName);
				FileUtils.write(FileUtils.readBytes(attrBigPictureOne), attrBigPictureOnePictureFile);
//				FileUtils.write(FileUtils.readBytes(attrBigPictureOne), onlineAttrBigPictureOnePictureFile);
				geDirectoryAttributeInfo.setAttrBigPictureOne(imagePath + attrBigPictureOnefileName);
			}
			if(attrBigPictureTwo != null){
				String attrBigPictureTwofileName = geDirectoryAttributeInfo.getAttrID() + "_AttrBigPictureTwo" + FileUtils.getFileNameExt(attrBigPictureTwoFileName);
				File attrBigPictureTwoPictureFile = new File(imageAbsolutePath + attrBigPictureTwofileName);
//				File onlineAttrBigPictureTwoPictureFile = new File(imageOnlineAbsolutePath + attrBigPictureTwofileName);
				FileUtils.write(FileUtils.readBytes(attrBigPictureTwo), attrBigPictureTwoPictureFile);
//				FileUtils.write(FileUtils.readBytes(attrBigPictureOne), onlineAttrBigPictureTwoPictureFile);
				geDirectoryAttributeInfo.setAttrBigPictureTwo(imagePath + attrBigPictureTwofileName);
			}
			geDirectoryAttributeInfoService.updateGeDirectoryAttributeInfo(geDirectoryAttributeInfo);
			super.getRequest().setAttribute("flag", "1");
			super.getRequest().setAttribute("message", "成功！");
		} catch (Exception e) {
			super.getRequest().setAttribute("flag", "0");
			super.getRequest().setAttribute("message", "失败！");
		} finally {
			super.getRequest().setAttribute("parentAttrID", geDirectoryAttributeInfo.getGeDirectoryAttributeInfo().getAttrID());
			super.getRequest().setAttribute("attrId", geDirectoryAttributeInfo.getAttrID());
		}
		return SUCCESS;
	}

	/**
	 * 根据产品目录属性ID查询产品目录属性信息
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findGeDirectoryAttributeInfoByAttrId")
	public String findGeDirectoryAttributeInfoByAttrId() {
		String attrID = super.getRequest().getParameter("attrId");
		geDirectoryAttributeInfo = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByAttrID(attrID);
		return SUCCESS;
	}
	
	/***
	 * 根据EId查询产品目录信息
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_isHaveGeDirectoryAttributeInfoByAttrId")
	public String isHaveGeDirectoryAttributeInfoByAttrId(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String attrId = super.getRequest().getParameter("attrId");
			GeDirectoryAttributeInfo geDirectoryAttributeInfo = geDirectoryAttributeInfoService.findGeDirectoryAttributeInfoByAttrID(attrId);
			
			if (geDirectoryAttributeInfo == null) {
				resultMap.put("resultFlag", "error");
			} else {
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
		
		return NONE;
	}
	
	/***
	 * 删除产品目录属性
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_delGeDirectoryAttribute")
	public String delGeDirectoryAttribute() {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String attrId = super.getRequest().getParameter("attrID");
			geDirectoryAttributeInfoService.deleteGeDirectoryByAttrId(attrId);
			resultMap.put("resultFlag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultMessage", "系统处理异常！");
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
		return NONE;
	}

	/***
	 * 添加产品目录属性关联
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_addProductDirectoryAttributeRelate")
	public String addProductDirectoryAttributeRelate() {
		Map<String, String> resultMap = new HashMap<String, String>();
		String eId = super.getRequest().getParameter("eId");
		String attributes = super.getRequest().getParameter("attributes");
		if (eId != null && attributes != null) {
			String[] attributeArray = attributes.split(",");
			geDirectoryAttributeRelateService.deleteGeDirectoryAttributeRelateByEid(eId);
			List<GeDirectoryAttributeRelate> directoryAttributeRelateList = new ArrayList<GeDirectoryAttributeRelate>();
			for (String attibute : attributeArray) {
				GeDirectoryAttributeRelate productDirectroyAttributeRelate = new GeDirectoryAttributeRelate();
				GeDirectoryAttributeInfo geDirectoryAttributeInfo = new GeDirectoryAttributeInfo();
				GeDirectory geDirectory = new GeDirectory();
				geDirectory.setEid(eId);
				geDirectoryAttributeInfo.setAttrID(attibute);
				productDirectroyAttributeRelate.setGeDirectory(geDirectory);
				productDirectroyAttributeRelate.setGeDirectoryAttributeInfo(geDirectoryAttributeInfo);
				directoryAttributeRelateList.add(productDirectroyAttributeRelate);
			}
			geDirectoryAttributeRelateService.addAllGeDirectoryAttributeRelate(directoryAttributeRelateList);
			resultMap.put("resultFlag", "success");
		} else {
			resultMap.put("resultFlag", "error");
		}

		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		renderText(jsonObject.toString());
		return NONE;
	}

	/***
	 * 获取网销产品信息
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findNetSalesProduct")
	public String findNetSalesProduct() {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String coreProductCode = super.getRequest().getParameter("coreProductCode");
			String coreProductSimpleName = super.getRequest().getParameter("coreProductSimpleName");
			String businessArea = super.getRequest().getParameter("businessArea");
			queryRule.addEqual("productStatus", "05");
			if (StringUtils.isNotBlank(coreProductCode)) {
				queryRule.addEqual("coreProductCode", coreProductCode);
			}

			if (StringUtils.isNotBlank(coreProductSimpleName)) {
				queryRule.addLike("coreProductSimpleName","%" + coreProductSimpleName.replaceAll("\\s", "%") + "%");
			}
			
			businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
			List<GeCodeId> idList = ConvertUtils.convertElementPropertyToList(businessAreaList, "id");
			List<String> codeList = ConvertUtils.convertElementPropertyToList(idList, "codeCode");
	    	Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_B_PDIR");//拿到该功能的所有机构权限
			
			for (int i = codeList.size() -1; i >= 0; i--) {
				String baStr = codeList.get(i);
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					codeList.remove(i);
				}
			}
			
			if (StringUtils.isNotBlank(businessArea) && codeList.contains(businessArea)) {
				queryRule.addEqual("businessArea", businessArea);
			}else{
				queryRule.addIn("businessArea", codeList);
			}
			pageNo = pageNo == 0 ? 1 : pageNo;
			pageSize = pageSize == 0 ? 20 : pageSize;
			
			if (codeList != null && codeList.size() >0) {
				page = productManageService.searchProduct(queryRule, pageNo, pageSize);
			}else {
				page = new Page(pageNo, 0, pageSize, null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询车产品
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findCarProduct")
	public String findCarProduct() {
		try {
			QueryRule queryRule = QueryRule.getInstance();
			String businessArea = super.getRequest().getParameter("businessArea");
			String riskCode = super.getRequest().getParameter("riskCode");
			String riskCName = super.getRequest().getParameter("riskCName");
			queryRule.addEqual("riskType", "11");
			queryRule.addEqual("validInd", "1");
			if (StringUtils.isNotBlank(riskCode)) {
				queryRule.addEqual("riskCode", riskCode);
			}
	
			if (StringUtils.isNotBlank(riskCName)) {
				queryRule.addLike("riskCName", "%" + riskCName.replaceAll("\\s", "%") + "%");
			}
			
			businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
			List<GeCodeId> idList = ConvertUtils.convertElementPropertyToList(businessAreaList, "id");
			List<String> codeList = ConvertUtils.convertElementPropertyToList(idList, "codeCode");
	    	Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_B_PDIR");//拿到该功能的所有机构权限
			
			for (int i = codeList.size() -1; i >= 0; i--) {
				String baStr = codeList.get(i);
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					codeList.remove(i);
				}
			}
			
			if (StringUtils.isNotBlank(businessArea) && codeList.contains(businessArea)) {
				queryRule.addEqual("businessArea", businessArea);
			}else{
				queryRule.addIn("businessArea", codeList);
			}
			
			pageNo = pageNo == 0 ? 1 : pageNo;
			pageSize = pageSize == 0 ? 20 : pageSize;
			if (codeList != null && codeList.size() >0) {
				page = bizCommonService.findGeRiskList(queryRule, pageNo, pageSize);
			}else {
				page = new Page(pageNo, 0, pageSize, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/***
	 * 根据EId查询产品目录信息
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findProductDirectoryByEId")
	public String findProductDirectoryByEId(){
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			String eId = super.getRequest().getParameter("eId");
			GeDirectory geDirectory = geDirectoryService.findGeDirectoryByEId(eId);
			
			if (geDirectory == null) {
				resultMap.put("resultFlag", "error");
			} else {
				resultMap.put("resultFlag", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}
		
		return NONE;
	}
	
	/**
	 * 根据电子商务产品EId查询该产品的绑定属性
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findGeDirectoryAttributeRelateByEId")
	public String findGeDirectoryAttributeRelateByEId() {
		String treeId = super.getRequest().getParameter("treeId");
		String eId = super.getRequest().getParameter("eId");
		String treeXml = geDirectoryAttributeRelateService.getProductAttributeXMLTree(treeId,eId);
		super.getRequest().setAttribute("treeXml", treeXml);
		return SUCCESS;
	}
	
	/**
	 * 根据电子商务产品EId查询该产品的绑定属性
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_directoryAttributeTree")
	public String directoryAttributeTree() {
		String treeId = super.getRequest().getParameter("treeId");
		String openTreeNode = super.getRequest().getParameter("openTreeNode");
		String attrId = super.getRequest().getParameter("attrId");
		String treeXml = geDirectoryAttributeInfoService.getAttributeXMLTree(treeId,openTreeNode);
		super.getRequest().setAttribute("treeXml", treeXml);
		super.getRequest().setAttribute("attrId", attrId);
		return SUCCESS;
	}
	
	/***
	 * 产品目录查询
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findProductDirectory")
	public String findProductDirectory() {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addDescOrder("createDate");
		if (StringUtils.isNotBlank(geDirectory.getEid())) {
			queryRule.addLike("eid", geDirectory.getEid() + "%");
		}
		if (StringUtils.isNotBlank(geDirectory.getCoreProductCode())) {
			queryRule.addLike("coreProductCode","%" + geDirectory.getCoreProductCode() + "%");
		}
		if (StringUtils.isNotBlank(geDirectory.getProductName())) {
			queryRule.addLike("productName","%" + geDirectory.getProductName().replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotBlank(geDirectory.getIsProductShelf())) {
			queryRule.addEqual("isProductShelf", geDirectory.getIsProductShelf());
		}
		if(StringUtils.isNotBlank(geDirectory.getIsNetSale())){
			queryRule.addEqual("isNetSale", geDirectory.getIsNetSale());
		}
		
		
		businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
		List<GeCode> saleChannelList = geCodeService.findAllByGeCodeType("SaleChannel");//销售渠道
		List<GeCode> productSectionList = geCodeService.findAllByGeCodeType("ProductSection");//产品栏目
			
		super.getRequest().setAttribute("saleChannelList", saleChannelList);
		super.getRequest().setAttribute("productSectionList", productSectionList);
		
		List<GeCodeId> idList = ConvertUtils.convertElementPropertyToList(businessAreaList, "id");
		List<String> codeList = ConvertUtils.convertElementPropertyToList(idList, "codeCode");
    	Map map = (Map) super.getSession().getAttribute("permission");

    	String marketingFlag = super.getRequest().getParameter("marketingFlag");
    	String authorityids = "";
    	if(marketingFlag!=null&&!marketingFlag.equals("")&&marketingFlag.equals("marketingFlag")){//是从营销活动过来的
    		authorityids = (String) map.get("ROLE_B_AAGA");//拿到该功能的所有机构权限
    	}else{//是从涛哥的的产品目录维护过来的
    		authorityids = (String) map.get("ROLE_B_PDIR_M");//拿到该功能的所有机构权限
    	}
    	
    	
		
		for (int i = codeList.size() -1; i >= 0; i--) {
			String baStr = codeList.get(i);
			if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
				continue;
			}else {
				codeList.remove(i);
			}
		}
		
		if (StringUtils.isNotBlank(geDirectory.getBusinessArea())  && codeList.contains(geDirectory.getBusinessArea())) {
			queryRule.addEqual("businessArea", geDirectory.getBusinessArea());
		}else{
			queryRule.addIn("businessArea", codeList);
		}
		
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 10 : pageSize;
		
		if (codeList != null && codeList.size() >0) {
			page = geDirectoryService.searchGeDirectory(queryRule, pageNo, pageSize);
		}else {
			page = new Page(pageNo, 0, pageSize, null);
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 根据电子商务产品ID查下产品目录基本信息
	 * 
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findGeDirectoryByEId")
	public String findGeDirectoryByEId() {
		try {
			geDirectory = geDirectoryService.findGeDirectoryByEId(geDirectory.getEid());
			super.getRequest().setAttribute("geDirectoryForUpdate", geDirectory);
			/**查询数据字典的业务领域*/
			businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
			List<GeCode> saleChannelList = geCodeService.findAllByGeCodeType("SaleChannel");//销售渠道
			List<GeCode> acceptInsuranceList = geCodeService.findAllByGeCodeType("AcceptInsurance");//承保方式
			List<GeCode> productSectionList = geCodeService.findAllByGeCodeType("ProductSection");//产品栏目
			List<GeCode> itemTypeList = geCodeService.findAllByGeCodeType("ItemType");//标的类型
			List<GeCode> riskLimitList = geCodeService.findAllByGeCodeType("RiskLimit");//险种期限
			List<GeCode> productRecommendList = geCodeService.findAllByGeCodeType("ProductRecommend");//产品推荐评分
			
			Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_B_PDIR_U");//拿到该功能的所有机构权限
			
			for (int i = businessAreaList.size() -1; i >= 0; i--) {
				String baStr = businessAreaList.get(i).getId().getCodeCode();
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				} else {
					businessAreaList.remove(i);
				}
			}
			
			HttpServletRequest request = super.getRequest();
			request.setAttribute("saleChannelList", saleChannelList);
			request.setAttribute("acceptInsuranceList", acceptInsuranceList);
			request.setAttribute("productSectionList", productSectionList);
			request.setAttribute("itemTypeList", itemTypeList);
			request.setAttribute("riskLimitList", riskLimitList);
			request.setAttribute("productRecommendList", productRecommendList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/***
	 * 产品推荐产品
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_addRecommendProduct")
	public String addRecommendProduct() {
		String productEId = super.getRequest().getParameter("productEId");
		String recommendProducts = super.getRequest().getParameter("recommendProducts");
		try {
			if (!":undefined".equals(productEId) && !":undefined".equals(recommendProducts)) {
				geProductCorrelationService.deleteGeProductCorrelationByProductEId(productEId);
				List<GeProductCorrelation> productCorrelationList = new ArrayList<GeProductCorrelation>();
				GeDirectory geDirectory = new GeDirectory();
				geDirectory.setEid(productEId);
				String[] products =  recommendProducts.split("\\|");
				for (String product:products) {
					GeDirectory recommendProduct = new GeDirectory();
					String[] productAttributes = product.split(":");
					recommendProduct.setEid(productAttributes[0]);
					GeProductCorrelation productCorrelation = new GeProductCorrelation();
					productCorrelation.setGeDirectoryByproduct(geDirectory);
					productCorrelation.setGeDirectoryByrecommendProduct(recommendProduct);
					productCorrelation.setCorreclation(Integer.valueOf(productAttributes[1]));
					productCorrelationList.add(productCorrelation);
				}
				geProductCorrelationService.addAllGeProductCorrelation(productCorrelationList);
				super.getRequest().setAttribute("resultFlag", "success");
				super.getRequest().setAttribute("message", "保存成功！");
			} else if (!":undefined".equals(productEId) && ":undefined".equals(recommendProducts)) {
				geProductCorrelationService.deleteGeProductCorrelationByProductEId(productEId);
				super.getRequest().setAttribute("resultFlag", "success");
				super.getRequest().setAttribute("message", "保存成功！");
			} else {
				super.getRequest().setAttribute("resultFlag", "failure");
				super.getRequest().setAttribute("message", "保存失败！");
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			super.getRequest().setAttribute("resultFlag", "failure");
			super.getRequest().setAttribute("message", "保存失败！");
		} finally {
			super.getRequest().setAttribute("eId", productEId);
		}
		return SUCCESS;
	}
	/***
	 * 查询产品已推荐的产品
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@LocusTrace(setCode = "ProductDirectoryAction_findRecommendProduct")
	public String findRecommendProduct() {
		List<GeCode> correlationList = null;
		try {
			String eId = super.getRequest().getParameter("eId");
			correlationList = geCodeService.findAllByGeCodeType("Correlation");
			List recommendProduct = geProductCorrelationService.findGeProductCorrelationByProductEId(eId);
			super.getRequest().setAttribute("recommendProduct", recommendProduct);
			if (recommendProduct == null) {
				super.getRequest().setAttribute("recommendProductSize", 0);
			} else {
				super.getRequest().setAttribute("recommendProductSize", recommendProduct.size());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.getRequest().setAttribute("correlationList", correlationList);
		return SUCCESS;
	}
	
	/***
	 * 查询产品未推荐的产品
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@LocusTrace(setCode = "ProductDirectoryAction_findNoRecommendProduct")
	public String findNoRecommendProduct() {
		
		try {
			String eId = super.getRequest().getParameter("eId");
			String noRecommendProductEId = super.getRequest().getParameter("noRecommendProductEId");
			String noRecommendProductCoreProductCode = super.getRequest().getParameter("noRecommendProductCoreProductCode");
			String businessArea = super.getRequest().getParameter("businessArea");
			boolean flag = false;//判断业务领域是否从权限里拿取
			if (StringUtils.isBlank(businessArea)) {
				flag = true;
			}
			
			businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
			List<GeCodeId> idList = ConvertUtils.convertElementPropertyToList(businessAreaList, "id");
			List<String> codeList = ConvertUtils.convertElementPropertyToList(idList, "codeCode");
	    	Map map = (Map) super.getSession().getAttribute("permission");
			String authorityids = (String) map.get("ROLE_B_PDIR_T");//拿到该功能的所有机构权限
			
			for (int i = codeList.size() -1; i >= 0; i--) {
				String baStr = codeList.get(i);
				if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
					continue;
				}else {
					codeList.remove(i);
				}
			}
			
			if ( flag || (!codeList.contains(businessArea))) {
				businessArea = "";
				if(codeList != null && codeList.size() > 0){
					for (int i = 0; i < codeList.size(); i++) {
						businessArea += "," + codeList.get(i);
					}
					businessArea = businessArea.substring(1);
				}
			}
			
			List noRecommendProduct = null;
			if(StringUtils.isNotBlank(businessArea)){
				if (StringUtils.isNotBlank(eId) && StringUtils.isBlank(noRecommendProductEId) && StringUtils.isBlank(noRecommendProductCoreProductCode) && flag) {
					noRecommendProduct = geDirectoryService.findNoRecommendProduct(eId, businessArea);
				}else if(StringUtils.isNotBlank(eId)){
					noRecommendProduct = geDirectoryService.findNoRecommendProduct(eId, noRecommendProductEId, businessArea, noRecommendProductCoreProductCode);
				}
			}
			
			super.getRequest().setAttribute("eId", eId);
			super.getRequest().setAttribute("noRecommendProduct", noRecommendProduct);
			super.getRequest().setAttribute("noRecommendProductSize", noRecommendProduct.size());
			
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		return SUCCESS;
	}
	
	/***
	 * 
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_findGeCode")
	public  String findGeCode() {
		String code = super.getRequest().getParameter("code");
		Map codeMap = geCodeService.findAllCodeAndName(code);
		return NONE;
	}
	
	/**
	 * 拼装response 用到的json字符串
	 * @param map
	 * @param jc
	 * @return
	 */
	private JSONObject getResponseJSONObject(Map<String, Object> map,JsonConfig jc){
		JSONObject jsonObject = new JSONObject();
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd"}) );
		if(jc==null){
			jc = getJc();
		}
		jsonObject.putAll(map,jc);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/**
	 * json格式化配置
	 */
	public JsonConfig getJc() {
		if(jc==null){
			jc = new JsonConfig();
			jc.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
		}
		return jc;
	}

	/**
	 * 获取项目物理路径
	 * 
	 * @return 项目路径
	 * @throws Exception
	 *             未找到路径
	 */
	public static String getProjectLocalPath() {
		try {
			String path = ProductDirectoryAction.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				if (path.lastIndexOf("/") > -1) {
					path = path.substring(0, path.lastIndexOf("/"));
				}
				String temp = "";
				if (StringUtils.isNotBlank(path) && path.length() >= 5)
					temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 进入新建页面
	 * @return
	 */
	@LocusTrace(setCode = "ProductDirectoryAction_getDataFromDic")
	public String getDataFromDic() throws Exception{
		/**查询数据字典的业务领域*/
		businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
		List<GeCode> saleChannelList = geCodeService.findAllByGeCodeType("SaleChannel");//销售渠道
		List<GeCode> acceptInsuranceList = geCodeService.findAllByGeCodeType("AcceptInsurance");//承保方式
		List<GeCode> productSectionList = geCodeService.findAllByGeCodeType("ProductSection");//产品栏目
		List<GeCode> itemTypeList = geCodeService.findAllByGeCodeType("ItemType");//标的类型
		List<GeCode> riskLimitList = geCodeService.findAllByGeCodeType("RiskLimit");//险种期限
		List<GeCode> productRecommendList = geCodeService.findAllByGeCodeType("ProductRecommend");//产品推荐评分
		
		Map map = (Map) super.getSession().getAttribute("permission");
		String authorityids = (String) map.get("ROLE_B_PDIR_I");//拿到该功能的所有机构权限
		
		for (int i = businessAreaList.size() -1; i >= 0; i--) {
			String baStr = businessAreaList.get(i).getId().getCodeCode();
			if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
				continue;
			} else {
				businessAreaList.remove(i);
			}
		}
		HttpServletRequest request = super.getRequest();
		request.setAttribute("saleChannelList", saleChannelList);
		request.setAttribute("acceptInsuranceList", acceptInsuranceList);
		request.setAttribute("productSectionList", productSectionList);
		request.setAttribute("itemTypeList", itemTypeList);
		request.setAttribute("riskLimitList", riskLimitList);
		request.setAttribute("productRecommendList", productRecommendList);
		
		
		return SUCCESS;
	}
	
	@LocusTrace(setCode = "ProductDirectoryAction_findBusinessArea")
    public void findBusinessArea(){
    	String perStr = super.getRequest().getParameter("perStr");
    	businessAreaList = geCodeService.findAllByGeCodeType(BusinessArea);
    	
    	Map map = (Map) super.getSession().getAttribute("permission");
    	if (StringUtils.isBlank(perStr)) {
			perStr = "ROLE_B_PDIR";//产品目录管理功能
		}
		String authorityids = (String) map.get(perStr);//拿到该功能的所有机构权限
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		for (int i = 0; i < businessAreaList.size(); i++) {
			String baStr = businessAreaList.get(i).getId().getCodeCode();
			if((!StringUtils.isBlank(baStr))&&(authorityids.startsWith(baStr)||authorityids.contains(","+baStr))){
				Map<String, String> map2 = new HashMap<String, String>();
				map2.put("code", baStr);
				map2.put("name", businessAreaList.get(i).getCodeCName());
				list.add(map2);
				continue;
			}
		}
		
		super.render(JsonBinder.buildNonNullBinder().toJson(list), "application/json;charset=utf-8");
    }
}
