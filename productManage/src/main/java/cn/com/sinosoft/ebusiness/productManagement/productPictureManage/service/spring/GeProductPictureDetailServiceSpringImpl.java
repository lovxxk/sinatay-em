/**  
 * @Title: GeProductPictureDetailServiceSpringImpl.java
 * @Package cn.com.sinosoft.ebusiness.productManagement.productPictureManage.service.spring
 * @Description: TODO	��ƷͼƬ����ӿ�ʵ����
 *   
 * @date 2012-7-20 ����05:55:49
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
	
	@LocusTrace(setDesc="��ȡ���µĲ������õĲ�ƷͼƬ")
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
	
	@LocusTrace(setDesc="���ݲ�ѯ�����ѯ��ƷͼƬ")
	public GeProductPictureDetail findByRule(QueryRule queryRule) {
		return super.findUnique(queryRule);
	}

	@LocusTrace(setDesc="����������ѯ��ƷͼƬ")
	public GeProductPictureDetail findById(String id) {
		return super.get(id);
	}
	
	@LocusTrace(setDesc="�޸Ĳ�ƷͼƬ")
	public void modify(GeProductPictureDetail geProductPictureDetail,String uploadPictureSerialNos) {
		
		//ͨ���ID ����ѯͼƬ
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geProductPictureDetail", geProductPictureDetail);
		List<GeProductPicture> geProductPictureList = super.find(GeProductPicture.class, queryRule);
		try{
			// һ����5��ͼƬ
			//Ҫ����ͼƬ��List ��������װ
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
			//��Ҫ��marketģ�����쳣
			throw MarketingException.newInstanceMsg("(update)Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************(update)Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("(update)Picture Error Exception="+sw.toString());
			//��Ҫ��marketģ�����쳣
		}
		//ɾ���ӱ�GeActivityPicture 
		deleteGeProductPicture(geProductPictureList);
		super.save(geProductPictureDetail);
	}

	//Ϊ�˸��·�������֯���ϴ�ͼƬ
	private List<GeProductPicture> getGeProductPictureUpdate(String uploadPictureSerialNos,GeProductPictureDetail geProductPictureDetail,List<GeProductPicture> geProductPictureList) throws IOException, Exception{
		List<GeProductPicture> updatePicture = new ArrayList<GeProductPicture>();
		for(int i=1;i<=5;i++){
			//ͼƬ1
			if(uploadPictureSerialNos!=null
					&&!uploadPictureSerialNos.equals("")
					&&uploadPictureSerialNos.contains(i+"")){//һ����Ҫ�ϴ���ͼƬ
				//��ʼ�ϴ��ļ�
				logger.info("*****************addServiceActivities upload i="+i+ "**********************");
				String count = getUploadPictureSize(uploadPictureSerialNos,i);//���õ�List�����
				File file = geProductPictureDetail.getUploadPicture().get(Integer.parseInt(count));//����ܹ�ȡ��Ҫ�ϴ���ͼƬ
				String attrPictureName = geProductPictureDetail.getDetailid()+"_"+i+"_"+geProductPictureDetail.getUploadPictureFileName().get(Integer.parseInt(count));//�ļ����֣�eid+���+�ļ�����
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geProductPictureDetail.getUploadImagePath()+java.io.File.separator+attrPictureName;//�ļ��ϴ�����·��
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//Ҫ�ϴ���ͼƬ������λ��
				FileUtils.write(FileUtils.readBytes(file), wantUploadPictureFile);//��������һ����File���ͣ�һ����ͼƬ�ϴ�λ��
				logger.info("*****************picture upload success**********************");
				//��װList
				GeProductPicture geProductPictureUpdate = new GeProductPicture();
				geProductPictureUpdate.setGeProductPictureDetail(geProductPictureDetail);//�����
				geProductPictureUpdate.setSerialNo(String.valueOf(i));//ͼƬ���
				String savePath  = geProductPictureDetail.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geProductPictureUpdate.setPictureurl(savePath);
				updatePicture.add(geProductPictureUpdate);
				
			}else{//û��Ҫ�ϴ���ͼƬ ����ô�������ݿ���ԭ�� �Ƿ����
				GeProductPicture geProductPictureUpdate = getGeProductPictureForUpdate(geProductPictureList,i);
				if(geProductPictureUpdate!=null){//��ǰ���ݿ������ͼƬ
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
			if(uploadSerialNos[k].equals(i+"")){//���
				count = k+"";
			}
		}
		return count;
	}
	
	private GeProductPicture getGeProductPictureForUpdate(List<GeProductPicture> geProductPictureTempList,int i){
		if(geProductPictureTempList!=null&&geProductPictureTempList.size()>0){
			for (GeProductPicture geProductPictureTemp: geProductPictureTempList) {
				if(geProductPictureTemp.getSerialNo().equals(i+"")){//��ǰ����
					geProductPictureTemp.setPictureid(null);//�Զ����ɵ�ͼƬID��ʱ����Ϊ��
					return geProductPictureTemp;
				}
			}// end for 
		}// end if
		return null;
	}
	private void deleteGeProductPicture(List<GeProductPicture> geProductPictureList){
		super.deleteAll(geProductPictureList);
	}

	@LocusTrace(setDesc="ɾ��������ƷͼƬ")
	public void deleteById(String id) {
		super.deleteByPK(id);

	}

	@LocusTrace(setDesc="����ɾ����ƷͼƬ")
	public void deleteByList(List<GeProductPictureDetail> list){
		super.deleteAll(list);
	}
	
	@LocusTrace(setDesc="���Ӳ�ƷͼƬ")
	public void insert(GeProductPictureDetail geProductPictureDetail) {
		//��ͼƬ��������   ��������
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
		
		//�����ϴ�ͼƬ
		try {
			uploadPicture(geProductPictureDetail);//�����ϴ�ͼƬ
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error IOException="+sw.toString()+"**********************");
			//��Ҫ��marketģ�����쳣
			throw MarketingException.newInstanceMsg("Picture Error IOException="+sw.toString());
		} catch (Exception e) {				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.info("*****************Picture Error Exception="+sw.toString()+"**********************");
			throw MarketingException.newInstanceMsg("Picture Error Exception="+sw.toString());
			//��Ҫ��marketģ�����쳣
		}//����ҳ���ռ����ϴ�ͼƬ����Ϣ������ͼƬ���Ƿ�����Ϣ���洢
	}
	
	private void  uploadPicture(GeProductPictureDetail geProductPictureDetail) throws IOException, Exception{
		if(geProductPictureDetail!=null
				&&geProductPictureDetail.getUploadPicture()!=null
				&&geProductPictureDetail.getUploadPicture().size()>0){//֤������ͼƬ��
			
			List<GeProductPicture> geProductPictures = new ArrayList<GeProductPicture>();
			List<String> uploadSerialNoList= geProductPictureDetail.getUploadSerialNoList();
			//��ʼ�ϴ��ļ�
			logger.info("*****************addServiceActivities upload**********************");
			List<File> uploadPicture = geProductPictureDetail.getUploadPicture();
			List<String> uploadPictureFileName = geProductPictureDetail.getUploadPictureFileName();
			for(int i =0;i<uploadPicture.size();i++){
				//�ϴ�ͼƬ�Ĵ���
				String attrPictureName = geProductPictureDetail.getDetailid()+"_"+uploadSerialNoList.get(i)+"_"+uploadPictureFileName.get(i);//�ļ����֣�eid+���+�ļ�����
				logger.info("*****************attrPictureName="+attrPictureName+"**********************");
				String uploadImageDirectoryPath = geProductPictureDetail.getUploadImagePath()+java.io.File.separator+attrPictureName;
				logger.info("*****************attrPictureName upload Path="+uploadImageDirectoryPath+"**********************");
				//uploadImageDirectoryPaths.add(uploadImageDirectoryPath);//�ռ�ÿ��ͼƬҪ�ϴ���λ��
				File wantUploadPictureFile = new File(uploadImageDirectoryPath);//Ҫ�ϴ���ͼƬ������λ��
				FileUtils.write(FileUtils.readBytes(geProductPictureDetail.getUploadPicture().get(i)), wantUploadPictureFile);//��������һ����File���ͣ�һ����ͼƬ�ϴ�λ��
				logger.info("*****************picture upload success**********************");
				
				//Ȼ��ʼ��װGeActivitiesProduct����ӱ��ƷͼƬ��
				String savePath  = geProductPictureDetail.getUploadSavePath()+attrPictureName;
				logger.info("*****************want save picture path savePath="+savePath+"**********************");
				geProductPictureDetail.getGeProductPictures().get(i).setPictureurl(savePath);//ͼƬ��URL
				geProductPictureDetail.getGeProductPictures().get(i).setGeProductPictureDetail(geProductPictureDetail);
			}
		}
	}

	public Page findList(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}
}
