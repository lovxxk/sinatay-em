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

	private String message;//����ҳ�����ʾ��Ϣ
	private int flag;//ҳ����ʾѡ����
	
	private GeProductPictureDetailService geProductPictureDetailService;//�Զ������ӿ�
	private GeProductPictureDetail geProductPictureDetail;//�Զ���ʵ����
	private GeProductPicture geProductPicture;
	private static final String imagePath = "upload/images/imageP/";//�Զ������
	
	/**
	 * �����ϴ�ͼƬʱ�õ�start
	 * struts2�ļ��ϴ�ʱ��������������������
	 * List<File> test;
	 * List<String> testFileName;//��׺������FileName
	 * List<String> testPictureContentType;//��׺������PictureContentType
	 * ����struts2���ڲ���������ο�struts2�ĵ�
	 */
	private List<File> uploadPicture;
	private List<String> uploadPictureFileName;
	private List<String> uploadPictureContentType;
	//�����ϴ�ͼƬʱ�õ�end
	
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
	 * ���뵽��ѯ����ҳ��
	 * @return
	 */


	/**����ҳ����յ������ݽ��в�ѯ�������õ���ҳ��Ϣ*/
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
	
	/**����ҳ����յ���idStr(��һ������id��ɣ�֮����','�ָ�)������������Ӧ��ɾ������*/
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
			message = "ɾ���ɹ���";
		} catch (Exception e) {
			e.printStackTrace();
			message = "ɾ��ʧ�ܣ�";
		}
		return SUCCESS;
	}
	/**����ҳ����յ����������в�ѯ�������õ���Ӧ��ʵ������Ϣ��������ϸҳ��*/
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
	
	/**����ҳ����յ����������в�ѯ�������õ���Ӧ��ʵ������Ϣ�������޸�ҳ��*/
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

	/**����ҳ����յ������ݽ����޸Ĳ���*/
	@LocusTrace(setCode="GeProductPictureManageAction_updateGeProductPictureDetail")
	public String updateGeProductPictureDetail(){
		try {
//				if(uploadPictureContentType != null ){
//					for (String  fileType: uploadPictureContentType) {
//						if(StringUtils.isNotBlank(fileType)){
//							if(!fileType.equals("image/bmp") && !fileType.equals("image/pjpeg") && !fileType.equals("image/gif")){
//								flag = 2;
//								message = "�޸�ʧ�ܣ�ͼƬ��ʽ���ԣ�";
//								return SUCCESS;
//							}
//						}
//					}
//				}
				//��������ͼƬ������
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
				//ͼƬ����start
				geProductPictureDetail.setUploadPicture(uploadPicture);//����Ҫ�ϴ����ļ�
				geProductPictureDetail.setUploadPictureContentType(uploadPictureContentType);//����Ҫ�ϴ����ļ�����
				geProductPictureDetail.setUploadPictureFileName(uploadPictureFileName);//����Ҫ�ϴ����ļ����� 
				geProductPictureDetail.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
				geProductPictureDetail.setUploadSavePath(imagePath);
				String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
				//ͼƬ����end
				geProductPictureDetailService.modify(geProductPictureDetail,uploadPictureSerialNos);
				flag = 1;
				message = "�޸ĳɹ�";
		} catch (Exception e) {
			e.printStackTrace();
			message = "�޸�ʧ��";
		}	
		return SUCCESS;
	}
	
	/**����ҳ����յ������ݽ�����Ӳ���*/
	@LocusTrace(setCode="GeProductPictureManageAction_addGeProductPicture")
	public String addGeProductPicture(){
		try {
//			for (String  fileType: uploadPictureContentType) {
//				if(StringUtils.isNotBlank(fileType)){
//					if(!fileType.equals("image/bmp") && !fileType.equals("image/pjpeg") && !fileType.equals("image/gif")){
//						flag = 2;
//						message = "���ʧ�ܣ�ͼƬ��ʽ���ԣ�";
//						return SUCCESS;
//					}
//				}
//			}
//			
			//��ȡ�ļ���׺���Ĺ���
			//FileUtils.getFileNameExt(logoImgFileName)
			//��������ͼƬ������
			for(int i=0;i<uploadPictureFileName.size();i++){
				String pictureExtName = FileUtils.getFileNameExt(uploadPictureFileName.get(i));
				//System.out.println(pictureExtName);
				//UUID.randomUUID().toString();
				uploadPictureFileName.add(i, UUID.randomUUID().toString()+pictureExtName);
				uploadPictureFileName.remove(i+1);
			}

			String uploadPictureSerialNos = super.getRequest().getParameter("uploadPictureSerialNos");
			geProductPictureDetail.setUploadPicture(uploadPicture);//����Ҫ�ϴ����ļ�
			geProductPictureDetail.setUploadPictureContentType(uploadPictureContentType);//����Ҫ�ϴ����ļ�����
			geProductPictureDetail.setUploadPictureFileName(uploadPictureFileName);//����Ҫ�ϴ����ļ����� 
			geProductPictureDetail.setUploadSerialNoList(getUploadSerialNoList(uploadPictureSerialNos));//����Ҫ�ϴ����ļ����к�
			geProductPictureDetail.setUploadImagePath(super.getRequest().getRealPath("/upload/images/imageP/"));
			geProductPictureDetail.setUploadSavePath(imagePath);

			Date date=new Date();
			geProductPictureDetail.setCreateTime(date);
			
			geProductPictureDetailService.insert(geProductPictureDetail);
				flag = 1;
				message = "��ӳɹ�";
		}catch(Exception e){
			flag = 1;
			message = "���ʧ��";
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	//�õ�ÿ��Ҫ�ϴ���ͼƬ��Ӧ��ͼƬ���
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
	
	//����������ڸ���ҳ���ͼƬ��չʾ
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
