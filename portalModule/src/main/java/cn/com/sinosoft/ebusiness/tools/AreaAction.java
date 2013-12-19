package cn.com.sinosoft.ebusiness.tools;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.tools.domain.Area;

import ins.framework.web.Struts2Action;

public class AreaAction extends Struts2Action {
	@Autowired
	private SessionFactory sessionFactory;
	
	private String upCode;
	private List<Area> listArea;
	
	public String initArea(){
		String sql;
		if(upCode == null || "".equals(upCode)){
			sql = "select placecode,placename from GE_AREA_ADDRESS where placetype='01'";
		}else{
			sql = "select placecode,placename from GE_AREA_ADDRESS where upplacename = '" + upCode + "'";
		}
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> tList = session.createSQLQuery(sql).list();
		tx.commit();
		session.close();
		
		listArea = new ArrayList<Area>();
		Area area;
		for(int i = 0; i < tList.size(); i++){
			area = new Area();
			area.setCode((String)(tList.get(i)[0]));
			area.setName((String)(tList.get(i)[1]));
			listArea.add(area);
		}
		
		return SUCCESS;
	}

	public String getUpCode() {
		return upCode;
	}

	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}

	public List<Area> getListArea() {
		return listArea;
	}

	public void setListArea(List<Area> listArea) {
		this.listArea = listArea;
	}
}
