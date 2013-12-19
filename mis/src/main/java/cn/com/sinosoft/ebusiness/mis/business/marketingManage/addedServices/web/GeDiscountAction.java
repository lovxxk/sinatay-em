package cn.com.sinosoft.ebusiness.mis.business.marketingManage.addedServices.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.FileUtils;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.marketing.domain.GeDiscountManage;
import cn.com.sinosoft.ebusiness.marketing.service.facade.GeDiscountManageService;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.domain.GeDirectory;
import cn.com.sinosoft.ebusiness.productManagement.productDirectory.service.facade.GeDirectoryService;

public class GeDiscountAction extends Struts2Action{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GeDiscountManage geDiscountManage;
	
	private GeDirectoryService  geDirectoryService;
	
	public GeDirectoryService getGeDirectoryService() {
		return geDirectoryService;
	}
	public void setGeDirectoryService(GeDirectoryService geDirectoryService) {
		this.geDirectoryService = geDirectoryService;
	}


	@Autowired
	private GeDiscountManageService geDiscountManageService;

	private Page page;
	
	private static final String imagePath = "upload/images/imageL/";
	
	private static final String imageAbsolutePath = "D:/chinalifeWorkSpace/mis/src/main/webapp/upload/images/imageL/";
	
//	private static final String imageAbsolutePath = getProjectLocalPath() + "/mis_war.ear/mis.war/upload/images/imageL/";
	
	private File pictureOne;
	
	private String pictureOneFileName;

	private File pictureTwo;
	
	private String pictureTwoFileName;

	private File pictureThree;
	
	private String pictureThreeFileName;

	private File pictureFour;
	
	private String pictureFourFileName;

	private File pictureFive;
	
	private String pictureFiveFileName;

	private File pictureSix;
	
	private String pictureSixFileName;
	
	private int flag;
	
	private String message;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public File getPictureOne() {
		return pictureOne;
	}
	public void setPictureOne(File pictureOne) {
		this.pictureOne = pictureOne;
	}
	public String getPictureOneFileName() {
		return pictureOneFileName;
	}
	public void setPictureOneFileName(String pictureOneFileName) {
		this.pictureOneFileName = pictureOneFileName;
	}
	public File getPictureTwo() {
		return pictureTwo;
	}
	public void setPictureTwo(File pictureTwo) {
		this.pictureTwo = pictureTwo;
	}
	public String getPictureTwoFileName() {
		return pictureTwoFileName;
	}
	public void setPictureTwoFileName(String pictureTwoFileName) {
		this.pictureTwoFileName = pictureTwoFileName;
	}
	public File getPictureThree() {
		return pictureThree;
	}
	public void setPictureThree(File pictureThree) {
		this.pictureThree = pictureThree;
	}
	public String getPictureThreeFileName() {
		return pictureThreeFileName;
	}
	public void setPictureThreeFileName(String pictureThreeFileName) {
		this.pictureThreeFileName = pictureThreeFileName;
	}
	public File getPictureFour() {
		return pictureFour;
	}
	public void setPictureFour(File pictureFour) {
		this.pictureFour = pictureFour;
	}
	public String getPictureFourFileName() {
		return pictureFourFileName;
	}
	public void setPictureFourFileName(String pictureFourFileName) {
		this.pictureFourFileName = pictureFourFileName;
	}
	public File getPictureFive() {
		return pictureFive;
	}
	public void setPictureFive(File pictureFive) {
		this.pictureFive = pictureFive;
	}
	public String getPictureFiveFileName() {
		return pictureFiveFileName;
	}
	public void setPictureFiveFileName(String pictureFiveFileName) {
		this.pictureFiveFileName = pictureFiveFileName;
	}
	public File getPictureSix() {
		return pictureSix;
	}
	public void setPictureSix(File pictureSix) {
		this.pictureSix = pictureSix;
	}
	public String getPictureSixFileName() {
		return pictureSixFileName;
	}
	
	public void setPictureSixFileName(String pictureSixFileName) {
		this.pictureSixFileName = pictureSixFileName;
	}
	
	public GeDiscountManage getGeDiscountManage() {
		return geDiscountManage;
	}
	public void setGeDiscountManage(GeDiscountManage geDiscountManage) {
		this.geDiscountManage = geDiscountManage;
	}
	
	/**增加折扣*/
	public String addGeDiscountManage(){
		
		try {
			
			if(pictureOne != null){
				String pictureOnefileName = geDiscountManage.getDiscountid() + "_PictureOne" + FileUtils.getFileNameExt(pictureOneFileName);
				File pictureOnePictureFile = new File(imageAbsolutePath + pictureOnefileName);
				FileUtils.write(FileUtils.readBytes(pictureOne), pictureOnePictureFile);
				geDiscountManage.setPictureone(imagePath + pictureOnefileName);
			}
			if(pictureTwo != null){
				String pictureTwofileName = geDiscountManage.getDiscountid() + "_PictureTwo" + FileUtils.getFileNameExt(pictureTwoFileName);
				File pictureTwoPictureFile = new File(imageAbsolutePath + pictureTwofileName);
				FileUtils.write(FileUtils.readBytes(pictureTwo), pictureTwoPictureFile);
				geDiscountManage.setPicturetwo(imagePath + pictureTwofileName);
			}
			if(pictureThree != null){
				String pictureThreefileName = geDiscountManage.getDiscountid() + "_PictureThree" + FileUtils.getFileNameExt(pictureThreeFileName);
				File pictureThreePictureFile = new File(imageAbsolutePath + pictureThreefileName);
				FileUtils.write(FileUtils.readBytes(pictureThree), pictureThreePictureFile);
				geDiscountManage.setPicturethree(imagePath + pictureThreefileName);
			}
			if(pictureFour != null){
				String pictureFourfileName = geDiscountManage.getDiscountid() + "_PictureFour" + FileUtils.getFileNameExt(pictureFourFileName);
				File pictureFourPictureFile = new File(imageAbsolutePath + pictureFourfileName);
				FileUtils.write(FileUtils.readBytes(pictureFour), pictureFourPictureFile);
				geDiscountManage.setPicturefour(imagePath + pictureFourfileName);
			}
			if(pictureFive != null){
				String pictureFivefileName = geDiscountManage.getDiscountid() + "_PictureFive" + FileUtils.getFileNameExt(pictureFiveFileName);
				File pictureFivePictureFile = new File(imageAbsolutePath + pictureFivefileName);
				FileUtils.write(FileUtils.readBytes(pictureFive), pictureFivePictureFile);
				geDiscountManage.setPicturefive(imagePath + pictureFivefileName);
			}
			if(pictureSix != null){
				String pictureSixfileName = geDiscountManage.getDiscountid() + "_PictureSix" + FileUtils.getFileNameExt(pictureSixFileName);
				File pictureSixPictureFile = new File(imageAbsolutePath + pictureSixfileName);
				FileUtils.write(FileUtils.readBytes(pictureOne), pictureSixPictureFile);
				geDiscountManage.setPicturesix(imagePath + pictureSixfileName);
			}

			geDiscountManageService.createGeDiscountManage(geDiscountManage);
			super.getRequest().setAttribute("addReslut", "success");
		} catch (IOException e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addReslut", "failure");
		} catch (Exception e) {
			e.printStackTrace();
			super.getRequest().setAttribute("addReslut", "failure");
		}
		return SUCCESS;
	}
	
	/**增加折扣*/
	public String delGeDiscountManage(){
		String[] ids=super.getRequest().getParameter("idStr").split(",");
		List<GeDiscountManage> list = new ArrayList<GeDiscountManage>();
		for(int i = 0;i<ids.length;i++){
			GeDiscountManage geDiscountManage1 = new GeDiscountManage();
			geDiscountManage1.setDiscountid(ids[i]);
			list.add(geDiscountManage1);
		}
		if(geDiscountManageService.deleteGeDiscountManage(list)){
			flag = 1;
			message = "删除成功！";
		}
		return SUCCESS;
	}
	
	/***
	 * 查询
	 * @return
	 */
	public String findGeDiscountManage() {
		QueryRule queryRule = QueryRule.getInstance();
		String discountid = geDiscountManage.getDiscountid();
		String eid = geDiscountManage.getEid();
		if (StringUtils.isNotBlank(discountid)) {
			queryRule.addLike("discountid", discountid.replaceAll("\\s", "%") + "%");
		}
		if (StringUtils.isNotBlank(geDiscountManage.getEid())) {
			queryRule.addLike("eid","%" + eid.replaceAll("\\s", "%") + "%");
		}
		queryRule.addAscOrder("discountstartdate");
		pageNo = pageNo == 0 ? 1 : pageNo;
		pageSize = pageSize == 0 ? 10 : pageSize;
		page = geDiscountManageService.searchGeDiscountManage(queryRule, pageNo, pageSize);
		return SUCCESS;
	}
	

	/***
	 * 查询
	 * @return
	 * @throws Exception 
	 */
	public String findGeDiscountManageForUpdate() throws Exception {
		geDiscountManage = geDiscountManageService.findGeDiscountManageByDiscountId(geDiscountManage.getDiscountid());
		GeDirectory geDirectory = geDirectoryService.findGeDirectoryByEId(geDiscountManage.getEid());
		super.getRequest().setAttribute("productName", geDirectory.getCoreProductSimpleName());
		return SUCCESS;
	}
	
	/***
	 * 查询
	 * @return
	 * @throws Exception 
	 */
	public String findGeDiscountManageByDiscountId() throws Exception {
		String discountid = super.getRequest().getParameter("discountid");
		GeDiscountManage geDiscountManage = geDiscountManageService.findGeDiscountManageByDiscountId(discountid);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			if (geDiscountManage == null) {
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
	 * 更新
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String updateGeDiscountManage() throws IOException, Exception {
		try {
			if(pictureOne != null){
				String pictureOnefileName = geDiscountManage.getDiscountid() + "_PictureOne" + FileUtils.getFileNameExt(pictureOneFileName);
				File pictureOnePictureFile = new File(imageAbsolutePath + pictureOnefileName);
				FileUtils.write(FileUtils.readBytes(pictureOne), pictureOnePictureFile);
				geDiscountManage.setPictureone(imagePath + pictureOnefileName);
			}
			if(pictureTwo != null){
				String pictureTwofileName = geDiscountManage.getDiscountid() + "_PictureTwo" + FileUtils.getFileNameExt(pictureTwoFileName);
				File pictureTwoPictureFile = new File(imageAbsolutePath + pictureTwofileName);
				FileUtils.write(FileUtils.readBytes(pictureTwo), pictureTwoPictureFile);
				geDiscountManage.setPicturetwo(imagePath + pictureTwofileName);
			}
			if(pictureThree != null){
				String pictureThreefileName = geDiscountManage.getDiscountid() + "_PictureThree" + FileUtils.getFileNameExt(pictureThreeFileName);
				File pictureThreePictureFile = new File(imageAbsolutePath + pictureThreefileName);
				FileUtils.write(FileUtils.readBytes(pictureThree), pictureThreePictureFile);
				geDiscountManage.setPicturethree(imagePath + pictureThreefileName);
			}
			if(pictureFour != null){
				String pictureFourfileName = geDiscountManage.getDiscountid() + "_PictureFour" + FileUtils.getFileNameExt(pictureFourFileName);
				File pictureFourPictureFile = new File(imageAbsolutePath + pictureFourfileName);
				FileUtils.write(FileUtils.readBytes(pictureFour), pictureFourPictureFile);
				geDiscountManage.setPicturefour(imagePath + pictureFourfileName);
			}
			if(pictureFive != null){
				String pictureFivefileName = geDiscountManage.getDiscountid() + "_PictureFive" + FileUtils.getFileNameExt(pictureFiveFileName);
				File pictureFivePictureFile = new File(imageAbsolutePath + pictureFivefileName);
				FileUtils.write(FileUtils.readBytes(pictureFive), pictureFivePictureFile);
				geDiscountManage.setPicturefive(imagePath + pictureFivefileName);
			}
			if(pictureSix != null){
				String pictureSixfileName = geDiscountManage.getDiscountid() + "_PictureSix" + FileUtils.getFileNameExt(pictureSixFileName);
				File pictureSixPictureFile = new File(imageAbsolutePath + pictureSixfileName);
				FileUtils.write(FileUtils.readBytes(pictureOne), pictureSixPictureFile);
				geDiscountManage.setPicturesix(imagePath + pictureSixfileName);
			}

			geDiscountManageService.updateGeDiscountManage(geDiscountManage);
			flag=1;
			message = "更新成功！";
		} catch (IOException e) {
			e.printStackTrace();
			message = "更新失败！";
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新失败！";
		}
		return SUCCESS;
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
			String path = GeDiscountAction.class.getResource("").getFile();
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
}
