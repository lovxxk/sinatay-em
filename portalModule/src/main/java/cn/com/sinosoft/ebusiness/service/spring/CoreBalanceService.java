package cn.com.sinosoft.ebusiness.service.spring;

import java.util.ArrayList;
import java.util.List;

import ins.framework.dao.GenericDaoHibernate;

public class CoreBalanceService extends GenericDaoHibernate{
	
	public List findCoreBalanceDate(String source,String start,String end){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select lc.signdate as TransDate, lc.proposalcontno as Prtno, lc.prem as Prem, lc.appflag as AppFlag")
		.append("from lccontsource lcs, lccont lc where lcs.proposalcontno = lc.proposalcontno and lcs.source = ?")
		.append("and lc.signdate between to_date(?, 'yyyyMMdd') and to_date(?, 'yyyyMMdd')");
		List resultList = new ArrayList();
		resultList = super.findBySql(sql.toString(), new Object[]{source,start,end});
		return resultList;
	}
}
