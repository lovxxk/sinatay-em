package cn.com.sinosoft.ebusiness.xmltransfer.service.spring;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.sinosoft.ebusiness.xmltransfer.service.facade.MaxNoService;

public class MaxNoServiceSpringImpl implements MaxNoService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String findMaxNo(String GroupNo) {
		String sql = "SELECT maxno FROM ge_maxno where groupno = '" + GroupNo + "' for update";
		String maxNo;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> tList = session.createSQLQuery(sql).list();
		Object o = tList.get(0);
		maxNo = (String)o;
		maxNo = "" + (Integer.parseInt(maxNo) + 1);
		
		sql = "UPDATE ge_maxno SET maxno = ? WHERE groupno = ?";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, maxNo);
		query.setParameter(1, GroupNo);
		query.executeUpdate();
		tx.commit();
		session.close();
		
		return maxNo;
	}
}
