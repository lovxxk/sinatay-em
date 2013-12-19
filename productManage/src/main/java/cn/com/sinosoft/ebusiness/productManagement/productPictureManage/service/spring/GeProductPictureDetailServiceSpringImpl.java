/**  
 * @Title: GeProductPictureDetailServiceSpringImpl.java
 * @Package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.spring
 * @Description: TODO	产品图片管理接口实现类
 *   
 * @date 2012-7-20 下午05:55:49
 * @version V1.0  
 */ 
package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPicture;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPictureDetail;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.facade.GeProductPictureDetailService;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.MarketingException;

public class GeProductPictureDetailServiceSpringImpl extends GenericDaoHibernate<GeProductPictureDetail, String>
		implements GeProductPictureDetailService {
	
	@LocusTrace(setDesc="获取最新的并且启用的产品图片")
	public Map<Integer,GeProductPicture> getProductPictures(){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addSql("createtime=(select  max(createtime) from ge_product_picture_detail where flag='1' group by flag )");
		GeProductPictureDetail geProductPictureDetail=super.findUnique(queryRule);
		Map<Integer,GeProductPicture> map=new HashMap<Integer,GeProductPicture>(); 
		if(geProductPictureDetail != null && geProductPictureDetail.getGeProductPictures() != null){
			for(int i=0;i<geProductPictureDetail.getGeProductPictures().size();i++){
				map.put(i, geProductPictureDetail.getGeProductPictures().get(i));
			}
		}
		return map;
	}
	
	@LocusTrace(setDesc="根据查询规则查询产品图片")
	public GeProductPictureDetail findByRule(QueryRule queryRule) {
		return super.findUnique(queryRule);
	}

	@LocusTrace(setDesc="根据主键查询产品图片")
	public GeProductPictureDetail findById(String id) {
		return super.get(id);
	}
	
	@LocusTrace(setDesc="修改产品图片")
	public void modify(GeProductPictureDetail geProductPictureDetail,String uploadPictureSerialNos) {
		
		//通过活动ID 来查询图片
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geProductPictureDetail", geProductPictureDetail);
		List<GeProductPicture> geProductPictureList = super.find(GeProductPicture.class, queryRule);
		try{
			// 一共就5张图片
			//要更新图片的List 是重新组装
			List<GeProductPicture> updatePicture = getGeProductPictureUpdate(uploadPictureSerialNos,geProductPictureDetail,geProductPictureList);
			geProductPictureDetail.setGeProductPictures(updatePicture);
			if(updatePicture!=null&&updatePicture.size()>0){
				for (GeProductPicture geProductPicture : updatePicture) {
					geProductPicture.setGeProductPictureDetail(geProductPictureDetail);
				}
			}
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************(update)Picture Error IOException="+sw.toString()+"**********************");
			//需要向market模块抛异常
			throw MarketingException.newInstanceMsg("(update)Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************(update)Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("(update)Picture Error Exception="+sw.toString());
			//需要向market模块抛异常
		}
		//删除子表GeActivityPicture 
		deleteGeProductPicture(geProductPictureList);
		super.save(geProductPictureDetail);
	}

	//为了更新方法而组织的上传图片
	private List<GeProductPicture> getGeProductPictureUpdate(String uploadPictureSerialNos,GeProductPictureDetail geProductPictureDetail,List<GeProductPicture> geProductPictureList) throws IOException, Exception{
		List<GeProductPicture> updatePicture = new ArrayList<GeProductPicture>();
		for(int i=1;i<=5;i++){
			//图片1
			if(uploadPictureSerialNos!=null
					&&!uploadPictureSerialNos.equals("")
					&&uploadPictureSerialNos.contains(i+"")){//一定有要上传的图片
				//开始上传文件
				logger.info("*****************addServiceActivities upload i="+i+ "**********************");
				String count = getUploadPictureSize(uploadPictureSerialNos,i);//得拿到List的序号
				File file = geProductPictureDetail.getUploadPicture().get(Integer.parseInt(count));//这个能够取到要上传的图片
				String attrPictureName = geProductPictureDetail.getDetailid()+"_"+i+"_"+geProductPictureDetail.getUploadPictureFileName().get(Integer.parseInt(count));//文件名字：eid+序号+文件名字
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geProductPictureDetail.getUploadImagePath()+java.io.File.separator+attrPictureName;//文件上传物理路径
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//要上传的图片的物理位置
				FileUtils.write(FileUtils.readBytes(file), wantUploadPictureFile);//两个参数一个是File类型，一个是图片上传位置
				logger.info("*****************picture upload success**********************");
				//组装List
				GeProductPicture geProductPictureUpdate = new GeProductPicture();
				geProductPictureUpdate.setGeProductPictureDetail(geProductPictureDetail);//活动代码
				geProductPictureUpdate.setSerialNo(String.valueOf(i));//图片序号
				String savePath  = geProductPictureDetail.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geProductPictureUpdate.setPictureurl(savePath);
				updatePicture.add(geProductPictureUpdate);
				
			}else{//没有要上传的图片 ，那么看看数据库里原来 是否存在
				GeProductPicture geProductPictureUpdate = getGeProductPictureForUpdate(geProductPictureList,i);
				if(geProductPictureUpdate!=null){//以前数据库里存在图片
					GeProductPicture geProductPictureNew  = new GeProductPicture();
					BeanUtils.copyProperties(geProductPictureUpdate, geProductPictureNew);
					geProductPictureNew.setPictureid(null);
					updatePicture.add(geProductPictureNew);
				}
			}// end if(uploadPictureSerialNos!=null
		}//end for
		return updatePicture;
	}
	
	private String getUploadPictureSize(String uploadPictureSerialNos ,int i){
		String count = "";
		String[] uploadSerialNos = uploadPictureSerialNos.split(",");
		for(int k=0;k<uploadSerialNos.length;k++){
			if(uploadSerialNos[k].equals(i+"")){//相等
				count = k+"";
			}
		}
		return count;
	}
	
	private GeProductPicture getGeProductPictureForUpdate(List<GeProductPicture> geProductPictureTempList,int i){
		if(geProductPictureTempList!=null&&geProductPictureTempList.size()>0){
			for (GeProductPicture geProductPictureTemp: geProductPictureTempList) {
				if(geProductPictureTemp.getSerialNo().equals(i+"")){//以前存在
					geProductPictureTemp.setPictureid(null);//自动生成的图片ID暂时设置为空
					return geProductPictureTemp;
				}
			}// end for 
		}// end if
		return null;
	}
	private void deleteGeProductPicture(List<GeProductPicture> geProductPictureList){
		super.deleteAll(geProductPictureList);
	}

	@LocusTrace(setDesc="删除单个产品图片")
	public void deleteById(String id) {
		super.deleteByPK(id);

	}

	@LocusTrace(setDesc="批量删除产品图片")
	public void deleteByList(List<GeProductPictureDetail> list){
		super.deleteAll(list);
	}
	
	@LocusTrace(setDesc="增加产品图片")
	public void insert(GeProductPictureDetail geProductPictureDetail) {
		//给图片表做设置   单独进行
		List<String> uploadPictureFileNames = geProductPictureDetail.getUploadPictureFileName();
		List<String> uploadSerialNoList= geProductPictureDetail.getUploadSerialNoList();
		if(uploadPictureFileNames!=null&&uploadPictureFileNames.size()>0){
			List<GeProductPicture> geProductPictureList = new ArrayList<GeProductPicture>();
			for(int i=0;i<uploadPictureFileNames.size();i++){
				String fileName = uploadPictureFileNames.get(i);
				GeProductPicture geProductPicture = new GeProductPicture();
				geProductPicture.setGeProductPictureDetail(geProductPictureDetail);
				geProductPicture.setSerialNo(uploadSerialNoList.get(i));
				geProductPictureList.add(geProductPicture);
			}
			geProductPictureDetail.setGeProductPictures(geProductPictureList);
		}
		super.save(geProductPictureDetail);
		
		//设置上传图片
		try {
			uploadPicture(geProductPictureDetail);//设置上传图片
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error IOException="+sw.toString()+"**********************");
			//需要向market模块抛异常
			throw MarketingException.newInstanceMsg("Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("Picture Error Exception="+sw.toString());
			//需要向market模块抛异常
		}//根据页面收集的上传图片的信息来决定图片表是否有信息来存储
	}
	
	private void  uploadPicture(GeProductPictureDetail geProductPictureDetail) throws IOException, Exception{
		if(geProductPictureDetail!=null
				&&geProductPictureDetail.getUploadPicture()!=null
				&&geProductPictureDetail.getUploadPicture().size()>0){//证明是有图片的
			
			List<GeProductPicture> geProductPictures = new ArrayList<GeProductPicture>();
			List<String> uploadSerialNoList= geProductPictureDetail.getUploadSerialNoList();
			//开始上传文件
			logger.info("*****************addServiceActivities upload**********************");
			List<File> uploadPicture = geProductPictureDetail.getUploadPicture();
			List<String> uploadPictureFileName = geProductPictureDetail.getUploadPictureFileName();
			for(int i =0;i<uploadPicture.size();i++){
				//上传图片的处理
				String attrPictureName = geProductPictureDetail.getDetailid()+"_"+uploadSerialNoList.get(i)+"_"+uploadPictureFileName.get(i);//文件名字：eid+序号+文件名字
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geProductPictureDetail.getUploadImagePath()+java.io.File.separator+attrPictureName;
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				//uploadImageDirectoryPaths.add(uploadImageDirectoryPath);//收集每张图片要上传的位置
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//要上传的图片的物理位置
				FileUtils.write(FileUtils.readBytes(geProductPictureDetail.getUploadPicture().get(i)), wantUploadPictureFile);//两个参数一个是File类型，一个是图片上传位置
				logger.info("*****************picture upload success**********************");
				
				//然后开始组装GeActivitiesProduct表的子表产品图片表
				String savePath  = geProductPictureDetail.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geProductPictureDetail.getGeProductPictures().get(i).setPictureurl(savePath);//图片的URL
				geProductPictureDetail.getGeProductPictures().get(i).setGeProductPictureDetail(geProductPictureDetail);
			}
		}
	}

	public Page findList(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
}
