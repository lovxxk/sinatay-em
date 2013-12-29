package cn.com.sinosoft.portalModule.transport.sinatay.insvalidator;

/**
 * @author sizhou
 * 所有的具体规则校验以及整体校验类都实现此接口。返回校验上下文
 *
 */
public interface InsuranceValidator{
	public ValidationContext validate(Object ctx) throws Exception;
}