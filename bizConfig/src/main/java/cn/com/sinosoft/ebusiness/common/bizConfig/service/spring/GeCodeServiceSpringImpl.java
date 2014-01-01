/**
 * 
 */
package cn.com.sinosoft.ebusiness.common.bizConfig.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCode;
import cn.com.sinosoft.ebusiness.common.bizConfig.domain.GeCodeId;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.log.LocusTrace;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.BizConfigCommonException;

/**
 * �����ֵ������
 *
 */
public class GeCodeServiceSpringImpl extends
		GenericDaoHibernate<GeCode, GeCodeId>  implements GeCodeService {

	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ��б�")
	public List<GeCode> findAll(QueryRule queryRule) throws BizConfigCommonException{
		List<GeCode> reList = new ArrayList<GeCode>();
		try {
			queryRule.addAscOrder("geCodeType");
			queryRule.addAscOrder("orderNo");
			reList = super.find(queryRule);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("��ѯ�����ֵ��б��쳣",e);
		}
		return reList;
	}
	
	@Override
	@LocusTrace(setDesc="���������ֵ����Ͳ�ѯ�����ֵ��б�")
	public List<GeCode> findAllByGeCodeType(String geCodeType)throws BizConfigCommonException{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", geCodeType);
		queryRule.addAscOrder("orderNo");
		return findAll(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="���������ֵ����Ͳ�ѯ��Ч�������ֵ��б�")
	public List<GeCode> findAllByGeCodeTypeAndValidInd(String geCodeType, String validInd)throws BizConfigCommonException{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", geCodeType);
		queryRule.addEqual("validInd", validInd);
		queryRule.addAscOrder("orderNo");
		return findAll(queryRule);
	}
	
	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ���뼰������")
	public Map<String, String> findAllCodeAndName(String geCodeType)throws BizConfigCommonException{
		String sql = "select d.CODECODE,d.CODECNAME from GE_CODE d where d.CODETYPE=?";
		List list = super.findBySql(sql,geCodeType);
		if (list == null || list.size() == 0) {
			return new HashMap<String, String>();
		}else{
			Map map = new HashMap();
			for(int i = 0; i < list.size(); i++){
				Object[] strs =  (Object[])list.get(i);
				map.put(strs[0], strs[1]);
			}
			return map;
		}
	}
	
	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ���뼰������")
	public List<String[]> findCodeAndNameByType(String geCodeType){
		String sql = "select d.CODECODE,d.CODECNAME from GE_CODE d where d.CODETYPE=?";
		return super.findBySql(sql,geCodeType);
	}
	
	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ�������")
	public String findCodeCName(String codeCode,String geCodeType)throws BizConfigCommonException{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", geCodeType);
		queryRule.addEqual("id.codeCode", codeCode);
		GeCode geCode = findGeCode(queryRule);
		if(geCode != null){
			return geCode.getCodeCName();
		}else{
			return "";
		}
	}
	
	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ�Ӣ����")
	public String findCodeEName(String codeCode,String geCodeType)throws BizConfigCommonException{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", geCodeType);
		queryRule.addEqual("id.codeCode", codeCode);
		GeCode geCode = findGeCode(queryRule);
		if(geCode != null){
			return geCode.getCodeEName();
		}else{
			return "";
		}
	}
	
	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ����")
	public String findCodeCode(String codeCName,String geCodeType)throws BizConfigCommonException{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", geCodeType);
		queryRule.addEqual("codeCName", codeCName);
		GeCode geCode = findGeCode(queryRule);
		if(geCode != null){
			return geCode.getId().getCodeCode();
		}else{
			return "";
		}
	}

	
	@Override
	@LocusTrace(setDesc="��ҳ��ѯ�����ֵ�")
	public Page findGeCode(QueryRule queryRule, int pageNo, int pageSize) throws BizConfigCommonException {
		Page page = null;
		try {
			queryRule.addAscOrder("geCodeType");
			queryRule.addAscOrder("orderNo");
			page = super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("��ҳ��ѯ�����ֵ��쳣", e);
		}
		return page;
	}

	@Override
	@LocusTrace(setDesc="��ѯ�����ֵ�")
	public GeCode findGeCode(QueryRule queryRule) throws BizConfigCommonException {
		GeCode geCode = null;
		try {
			geCode = super.findUnique(queryRule);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("��ѯ�����ֵ��쳣", e);
		}
		return geCode;
	}

	@Override
	@LocusTrace(setDesc="�޸������ֵ�")
	public boolean updateGeCode(GeCode geCode,String[] notUpdateColumns) throws BizConfigCommonException {
		if(notUpdateColumns == null)notUpdateColumns = new String[]{};
		try {
			System.out.println("geCode.getValidInd(): "+geCode.getValidInd());
			GeCode update = super.findUnique("id", geCode.getId());
			System.out.println("update.getValidInd(): "+update.getValidInd());
			update.setValidInd(geCode.getValidInd());
			BeanUtils.copyProperties(geCode, update, notUpdateColumns);
			super.update(update);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("�޸������ֵ��쳣", e);
		}
		return true;
	}

	@Override
	@LocusTrace(setDesc="���������ֵ�")
	public boolean save(GeCode geCode) throws BizConfigCommonException {
		try {
			super.save(geCode);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("���������ֵ��쳣", e);
		}
		return true;
	}

	@Override
	@LocusTrace(setDesc="������ɾ�������ֵ�")
	public boolean delete(GeCodeId id) throws BizConfigCommonException {
		try {
			super.deleteByPK(id);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("������ɾ�������ֵ��쳣", e);
		}
		return true;
	}
	
	@Override
	@LocusTrace(setDesc="����ɾ�������ֵ�")
	public boolean deleteAllGeCodeByIds(List<GeCodeId> ids) throws BizConfigCommonException {
		if (ids == null) {
		return false;
		}else{
			for (int i = 0; i < ids.size(); i++) {
				delete(ids.get(i));
			}
			return true;
		}
	}

	@Override
	@LocusTrace(setDesc="����ɾ�������ֵ�")
	public boolean deleteAllGeCode(List<GeCode> geCodeList) throws BizConfigCommonException  {
		try {
			super.deleteAll(geCodeList);
		} catch (Exception e) {
			throw BizConfigCommonException.newInstanceMsg("����ɾ�������ֵ��쳣", e);
		}
		
		return true;
	}
	
	@Override
	public String getCodeCoreRelation(String codeCode, String geCodeType) throws BizConfigCommonException{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id.codeType", geCodeType);
		queryRule.addEqual("id.codeCode", codeCode);
		GeCode geCode = findGeCode(queryRule);
		if(geCode!=null){
			return geCode.getCodeCoreRelation();
		}
		return "";
	}
}
