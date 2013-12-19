package cn.com.sinosoft.portalModule.enumUtil;

import cn.com.sinosoft.enums.EnumDictionary;


public class NodeType extends EnumDictionary {

	/**
	 * �ڵ�����
	 */
	private static final long serialVersionUID = 9212211777359168627L;
	
	public static final NodeType ELEMENT_NODE = new NodeType("ELEMENT_NODE", "Ԫ�ؽڵ�", 0);
	
	public static final NodeType START_ELEMENT = new NodeType("START_ELEMENT", "Ԫ�ؿ�ʼ�ڵ�", 1); 
	
	public static final NodeType END_ELEMENT = new NodeType("END_ELEMENT ", "Ԫ�ؽ����ڵ�", 2);
	
	public static final NodeType PROCESSING_INSTRUCTION = new NodeType("PROCESSING_INSTRUCTION ", "����ָ��", 3);
	
	public static final NodeType TEXT_NODE = new NodeType("TEXT_NODE ", "�ı��ڵ�", 4);
	
	public static final NodeType COMMENT_NODE = new NodeType("COMMENT_NODE ", "ע�ͽڵ�", 5);

	public static final NodeType SPACE_NODE = new NodeType("SPACE_NODE ", "�հ׽ڵ�", 6);
	
	public static final NodeType WHITE_SPACE_HANDLING = new NodeType("WHITE_SPACE_HANDLING ", "�հ״���", 61);
	
	public static final NodeType START_DOCUMENT = new NodeType("START_DOCUMENT ", "�ĵ���ʼ", 7);
	
	public static final NodeType END_DOCUMENT = new NodeType("END_DOCUMENT ", "�ĵ�����", 8);
	
	public static final NodeType ENTITY_REFERENCE_NODE = new NodeType("ENTITY_REFERENCE_NODE ", "ʵ�����ýڵ�", 9);
	
	public static final NodeType ATTRIBUTE = new NodeType("ATTRIBUTE ", "���Խڵ�", 10);
	
	public static final NodeType DTD_NODE = new NodeType("DTD_NODE ", "DTD�ڵ�", 11);
	
	public static final NodeType CDATA_SECTION_NODE = new NodeType("CDATA_SECTION_NODE ", "CDATA�ı��ڵ�", 12);
	
	public static final NodeType NAMESPACE = new NodeType("NAMESPACE ", "�����ռ�", 13);
	
	public static final NodeType NOTATION_DECLARATION = new NodeType("NOTATION_DECLARATION ", "ע������", 14);
	
	public static final NodeType ENTITY_DECLARATION = new NodeType("ENTITY_DECLARATION ", "ʵ������", 15);
	
	public NodeType(String enumName, String dataName, Integer value) {
		super(enumName, dataName, value);
	}
}
