package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

/**
 * RetData节点DTO
 *
 */
public class RetData implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 返回标志 CD25 返回标志 0：失败 1：成功 */
	private String flag;
	
	/** 返回信息 Flag为0：错误信息 */
	private String desc;

	
	/**
	 * 属性 flag 的 getter 方法
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 属性 flag 的 setter 方法
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 属性 desc 的 getter 方法
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 属性 desc 的 setter 方法
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
