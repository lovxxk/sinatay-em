package cn.com.sinosoft.util.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Spring��֧�����ݿ����,������ƺ�����ע���JUnit4 ���ɲ��Ի���,���Springԭ�������ָ���.
 *   
 * ������Ҫ����applicationContext�ļ���λ��, ��:
 * @ContextConfiguration(locations = { "/applicationContext-test.xml" })
 * 
 */
public abstract class SpringTxTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	protected DataSource dataSource;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
}
