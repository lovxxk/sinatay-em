package cn.com.sinosoft.test.tools.springTest;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Spring��֧������ע���JUnit4 ���ɲ��Ի���, ���Springԭ�������ָ���.
 * 
 * ������Ҫ����applicationContext�ļ���λ��,��:
 * @ContextConfiguration(locations = { "/applicationContext-test.xml" })
 * 
 */
public abstract class SpringContextTestCase extends AbstractJUnit4SpringContextTests {
}
