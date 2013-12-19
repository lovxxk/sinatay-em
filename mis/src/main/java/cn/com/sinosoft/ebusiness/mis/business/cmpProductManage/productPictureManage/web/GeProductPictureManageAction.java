package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.productPictureManage.web;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.utils.FileUtils;
import ins.framework.utils.StringUtils;
import ins.framework.web.Struts2Action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPicture;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.domain.GeProductPictureDetail;
import cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.facade.GeProductPictureDetailService;

public class GeProductPictureManageAction extends Struts2Action {
	private static final long serialVersionUID = -3517814498044475227L;

	private String message;//返回页面的提示信息
	private int flag;//页面提示选择器
	
	private GeProductPictureDetailService geProductPictureDetailService;//自定义服务接口
	private GeProductPictureDetail geProductPictureDetail;//自定义实体类
	private GeProductPicture geProductPicture;
	private static final String imagePath = "upload/images/imageP/";//自定义变量
	
	/**
	 * 批量上传图片时用的start
	 * struts2文件上传时的三个参数命名规则是
	 * List<File> test;
	 * List<String> testFileName;//后缀必须是FileName
	 * List<String> testPictureContentType;//后缀必须是PictureContentType
	 * 这是struts2的内部规则详情参考struts2文档
	 */
	private List<File> uploadPicture;
	private List<String> uploadPictureFileName;
	private List<String> uploadPictureContentType;
	//批量上传图片时用的end
	
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

	public GeProductPictureDetailService getGeProductPictureDetailService() {
		return geProductPictureDetailService;
	}

	public void setGeProductPictureDetailService(
			GeProductPictureDetailService geProductPictureDetailService) {
		this.geProductPictureDetailService = geProductPictureDetailService;
	}

	public GeProductPictureDetail getGeProductPictureDetail() {
		return geProductPictureDetail;
	}

	public void setGeProductPictureDetail(
			GeProductPictureDetail geProductPictureDetail) {
		this.geProductPictureDetail = geProductPictureDetail;
	}
	
	public List<File> getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(List<File> uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	public List<String> getUploadPictureFileName() {
		return uploadPictureFileName;
	}

	public void setUploadPictureFileName(List<String> uploadPictureFileName) {
		this.uploadPictureFileName = uploadPictureFileName;
	}

	public List<String> getUploadPictureContentType() {
		return uploadPictureContentType;
	}

	public void setUploadPictureContentType(List<String> uploadPictureContentType) {
		this.uploadPictureContentType = uploadPictureContentType;
	}
	
	public GeProductPicture getGeProductPicture() {
		return geProductPicture;
	}

	public void setGeProductPicture(GeProductPicture geProductPicture) {
		this.geProductPicture = geProductPicture;
	}
	/**
	 * 进入到查询条件页面
	 * @return
	 */


	/**根据页面接收到的数据进行查询操作，得到分页信息*/
	@LocusTrace(setCode="GeProductPictureManageAction_queryGeProductPictureByDisPage")
	public String queryGeProductPictureByDisPage() throws Exception{
		String order=" 1=1 order by createTime desc";
		QueryRule queryRule = QueryRule.getInstance();
		if(StringUtils.isNotBlank(geProductPictureDetail.getPicturename())){
			queryRule.addSql("picturename like '%"+geProductPictureDetail.getPicturename()+"%'");
		}
		if(StringUtils.isNotBlank(geProductPictureDetail.getFlag())){
			queryRule.addSql("flag='"+geProductPictureDetail.getFlag()+"'");
		}
		
		queryRule.addSql(order);
		
		Page page = geProductPictureDetailService.findList(queryRule, pageNo, pageSize);
		
		super.getRequest().setAttribute("page", page);
		return SUCCESS;
	}
	
	/**根据页面接收到的idStr(由一个或多个id组成，之间用','分隔)参数，进行相应的删除操作*/
	@LocusTrace(setCode="GeProductPictureManageAction_deleteGeProductPictureDetail")
	public String deleteGeProductPictureDetail(){
		String idStr = super.getRequest().getParameter("idStr");
		String[] ids = idStr.split(",");
		try {
			if (ids.length==1) {
				geProductPictureDetailService.deleteById(idStr);
			}else{
				List<GeProductPictureDetail> list = new ArrayList<GeProductPictureDetail>();
				for (int i = 0; i < ids.length; i++) {
					idStr = ids[i];
					GeProductPictureDetail geProductPictureDetail2 = new GeProductPictureDetail();
					geProductPictureDetail2.setDetailid(idStr);
					list.add(geProductPictureDetail2);
				}
				geProductPictureDetailService.deleteByList(list);
			}
			flag = 1;
			message = "删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
			message = "删除失败！";
		}
		return SUCCESS;
	}
	/**根据页面接收到的主键进行查询操作，得到相应的实体类信息，返回详细页面*/
	@LocusTrace(setCode="GeProductPictureManageAction_queryGeProductPictureForShow")
	public String queryGeProductPictureForShow(){
		try {
			geProductPictureDetail = geProductPictureDetailService.findById(geProductPictureDetail.getDetailid());
			viePictureForUploadPage(geProductPictureDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**根据页面接收到的主键进行查询操作，得到相应的实体类信息，返回修改页面*/
	@LocusTrace(setCode="GeProductPictureManageAction_queryGeProductPictureDetailForUpdate")
	public String queryGeProductPictureDetailForUpdate(){
		try {
			geProductPictureDetail = geProductPictureDetailService.findById(geProductPictureDetail.getDetailid());
			viePictureForUploadPage(geProductPictureDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**根据页面接收到的数据进行修改操作*/
	@LocusTrace(setCode="GeProductPictureManageAction_updateGeProductPictureDetail")
	public String updateGeProductPictureDetail(){
		try {
//				if(uploadPictureContentType != null ){
//					for (String  fileType: uploadPictureContentType) {
//						if(StringUtils.isNotBlank(fileType)){
//							if(!fileType.equals("image/bmp") && !fileType.equals("image/pjpeg") && !fileType.equals("image/gif")){
//								flag = 2;
//								message = "修改失败，图片格式不对！";
//								return SUCCESS;
//							}
//						}
//					}
//				}
				//重新设置图片的名称
				if(null != uploadPictureFileName){
					for(int i=0;i<uploadPictureFileName.size();i++){
						String pictureExtName = FileUtils.getFileNameExt(uploadPictureFileName.get(i));
						//System.out.println(pictureExtName);
						//UUID.randomUUID().toString();
						uploadPictureFileName.add(i, UUID.randomUUID().toString()+pictureExtName);
						uploadPictureFileName.remove(i+1);
					}
				}
				geProductPictureDetail.setCreateTime(geProductPictureDetail.getCreateTime());
				//图片部分start
				geProductPictureDetail.setUploadPicture(uploadPicture);//真正要上传的文件
				geProductPictureDetail.setUploadPictureContentType(uploadPictureContentType);//真正要上传的文件类型
				geProductPictureDetail.setUploadPictureFileName(uploadPictureFileName);//真正要上传的文件名称 
				geProductPictureDetail.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
				geProductPictureDetail.setUploadSavePath(imagePath);
				String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
				//图片部分end
				geProductPictureDetailService.modify(geProductPictureDetail,uploadPictureSerialNos);
				flag = 1;
				message = "修改成功";
		} catch (Exception e) {
			e.printStackTrace();
			message = "修改失败";
		}	
		return SUCCESS;
	}
	
	/**根据页面接收到的数据进行添加操作*/
	@LocusTrace(setCode="GeProductPictureManageAction_addGeProductPicture")
	public String addGeProductPicture(){
		try {
//			for (String  fileType: uploadPictureContentType) {
//				if(StringUtils.isNotBlank(fileType)){
//					if(!fileType.equals("image/bmp") && !fileType.equals("image/pjpeg") && !fileType.equals("image/gif")){
//						flag = 2;
//						message = "添加失败，图片格式不对！";
//						return SUCCESS;
//					}
//				}
//			}
//			
			//获取文件后缀名的工具
			//FileUtils.getFileNameExt(logoImgFileName)
			//重新设置图片的名称
			for(int i=0;i<uploadPictureFileName.size();i++){
				String pictureExtName = FileUtils.getFileNameExt(uploadPictureFileName.get(i));
				//System.out.println(pictureExtName);
				//UUID.randomUUID().toString();
				uploadPictureFileName.add(i, UUID.randomUUID().toString()+pictureExtName);
				uploadPictureFileName.remove(i+1);
			}

			String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
			geProductPictureDetail.setUploadPicture(uploadPicture);//真正要上传的文件
			geProductPictureDetail.setUploadPictureContentType(uploadPictureContentType);//真正要上传的文件类型
			geProductPictureDetail.setUploadPictureFileName(uploadPictureFileName);//真正要上传的文件名称 
			geProductPictureDetail.setUploadSerialNoList(getUploadSerialNoList(uploadPictureSerialNos));//真正要上传的文件序列号
			geProductPictureDetail.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
			geProductPictureDetail.setUploadSavePath(imagePath);

			Date date=new Date();
			geProductPictureDetail.setCreateTime(date);
			
			geProductPictureDetailService.insert(geProductPictureDetail);
				flag = 1;
				message = "添加成功";
		}catch(Exception e){
			flag = 1;
			message = "添加失败";
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	//得到每个要上传的图片对应的图片序号
	private List<String> getUploadSerialNoList(String uploadPictureSerialNos){
		List<String> uploadPictureSerialNoList = new ArrayList<String>();
		if(uploadPictureSerialNos!=null&&!uploadPictureSerialNos.equals("")){
			String[] temps = uploadPictureSerialNos.split(",");
			if(temps!=null&&temps.length>0){
				for(int i=0;i<temps.length;i++){
					uploadPictureSerialNoList.add(temps[i]);
				}
			}
		}
		
		return uploadPictureSerialNoList;
	}
	
	//这个方法用于更新页面的图片的展示
	public void viePictureForUploadPage(GeProductPictureDetail geProductPictureDetail){
		List<GeProductPicture> geProductPictureList = geProductPictureDetail.getGeProductPictures();
		if(geProductPictureList!=null&&geProductPictureList.size()>0){
			java.util.Map<String, String> map = new HashMap<String, String>();
			for(int i=0;i<5;i++){
				map.put((i+1)+"", "no");
			}
			
			for (GeProductPicture  geProductPictureTemp : geProductPictureList) {
				map.put(geProductPictureTemp.getSerialNo(),"yes");
			}
			List<GeProductPicture> geProductPictureTempList= new ArrayList<GeProductPicture>();
			
			for(int i=1;i<=5;i++){
				if(map.get(i+"").equals("no")){
					GeProductPicture geProductPictureTemp = new GeProductPicture();
					geProductPictureTemp.setNooryes("no");
					geProductPictureTempList.add(geProductPictureTemp);
				}else{
					String serialNoTemp = i+"";
					for(GeProductPicture geProductPictureTemp:geProductPictureList){
						if(geProductPictureTemp.getSerialNo().equals(serialNoTemp)){
							geProductPictureTemp.setNooryes("yes");
							geProductPictureTempList.add(geProductPictureTemp);
						}
					}
				}
			}
			geProductPictureDetail.setGeProductPictures(geProductPictureTempList);
		}
	}
}
