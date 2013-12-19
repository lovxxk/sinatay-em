package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class NodeType extends EnumDictionary {

	/**
	 * 节点类型
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final NodeType ELEMENT_NODE = new NodeType("ELEMENT_NODE", "元素节点", 0);
	
	public static final NodeType START_ELEMENT = new NodeType("START_ELEMENT", "元素开始节点", 1); 
	
	public static final NodeType END_ELEMENT = new NodeType("END_ELEMENT ", "元素结束节点", 2);
	
	public static final NodeType PROCESSING_INSTRUCTION = new NodeType("PROCESSING_INSTRUCTION ", "处理指令", 3);
	
	public static final NodeType TEXT_NODE = new NodeType("TEXT_NODE ", "文本节点", 4);
	
	public static final NodeType COMMENT_NODE = new NodeType("COMMENT_NODE ", "注释节点", 5);

	public static final NodeType SPACE_NODE = new NodeType("SPACE_NODE ", "空白节点", 6);
	
	public static final NodeType WHITE_SPACE_HANDLING = new NodeType("WHITE_SPACE_HANDLING ", "空白处理", 61);
	
	public static final NodeType START_DOCUMENT = new NodeType("START_DOCUMENT ", "文档开始", 7);
	
	public static final NodeType END_DOCUMENT = new NodeType("END_DOCUMENT ", "文档结束", 8);
	
	public static final NodeType ENTITY_REFERENCE_NODE = new NodeType("ENTITY_REFERENCE_NODE ", "实体引用节点", 9);
	
	public static final NodeType ATTRIBUTE = new NodeType("ATTRIBUTE ", "属性节点", 10);
	
	public static final NodeType DTD_NODE = new NodeType("DTD_NODE ", "DTD节点", 11);
	
	public static final NodeType CDATA_SECTION_NODE = new NodeType("CDATA_SECTION_NODE ", "CDATA文本节点", 12);
	
	public static final NodeType NAMESPACE = new NodeType("NAMESPACE ", "命名空间", 13);
	
	public static final NodeType NOTATION_DECLARATION = new NodeType("NOTATION_DECLARATION ", "注释声明", 14);
	
	public static final NodeType ENTITY_DECLARATION = new NodeType("ENTITY_DECLARATION ", "实体声明", 15);
	
	public NodeType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
