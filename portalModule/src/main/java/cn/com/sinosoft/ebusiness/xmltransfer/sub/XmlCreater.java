package cn.com.sinosoft.ebusiness.xmltransfer.sub;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.xmltransfer.service.facade.MaxNoService;
/**
 * ������������
 * @author zhuxuyang
 * 2013.08.29
 */
public class XmlCreater {
	//������Դ�������̳�ΪMALL
	private static String saleChannel = "W";
	private static String sellType = "20";
	private static String source = "MALL";
	
	private MaxNoService maxNoService;
	
	public MaxNoService getMaxNoService() {
		return maxNoService;
	}

	public void setMaxNoService(MaxNoService maxNoService) {
		this.maxNoService = maxNoService;
	}

	public XmlCreater(){};

	protected Document createXml(String functionFlag) {
		Document doc = null;
		try {
			doc = DocumentHelper.createDocument();
			Element tTranData = doc.addElement("TranData");
			Element tBaseInfo = tTranData.addElement("BaseInfo");

			Element tTransrDate = tBaseInfo.addElement("TransrDate");
			Element tTransrTime = tBaseInfo.addElement("TransrTime");
			Element tTellerNo = tBaseInfo.addElement("TellerNo");
			Element tTransrNo = tBaseInfo.addElement("TransrNo");
			Element tSaleChannel = tBaseInfo.addElement("SaleChannel");
			Element tSellType = tBaseInfo.addElement("SellType");
			Element tFunctionFlag = tBaseInfo.addElement("FunctionFlag");
			Element tSource = tBaseInfo.addElement("Source");
			
			//�Զ����ɽ�����ˮ��
			String maxNo = maxNoService.findMaxNo("MALL");
			int exZero = 9-maxNo.length();
			StringBuffer sb = new StringBuffer();
			sb.append("MAL").append(getCurrentDateNoGap());
			for(int i = 0; i < exZero; i++){
				sb.append("0");
			}
			sb.append(maxNo);
			String transrNo = sb.toString();
			
			tTransrDate.addText(getCurrentDateNoGap());
			tTransrTime.addText(getCurrentTimeNoGap());
			tTransrNo.addText(transrNo);
			tFunctionFlag.addText(functionFlag);
			
			tSaleChannel.addText(saleChannel);
			tSellType.addText(sellType);
			tSource.addText(source);

			Element tTranRequest = tTranData.addElement("TranRequest");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * �õ���ǰϵͳ���� author: YT
	 * 
	 * @return ��ǰ���ڵĸ�ʽ�ַ���,���ڸ�ʽΪ"yyyyMMdd"
	 */
	private static String getCurrentDateNoGap() {
		GregorianCalendar tGCalendar = new GregorianCalendar();
		StringBuffer tStringBuffer = new StringBuffer(10);
		int sYears = tGCalendar.get(Calendar.YEAR);
		tStringBuffer.append(sYears);
		int sMonths = tGCalendar.get(Calendar.MONTH) + 1;
		if (sMonths < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sMonths);
		int sDays = tGCalendar.get(Calendar.DAY_OF_MONTH);
		if (sDays < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sDays);
		String tString = tStringBuffer.toString();
		return tString;

	}
	
	/**
	 * �õ���ǰϵͳʱ�� author: YT
	 * 
	 * @return ��ǰʱ��ĸ�ʽ�ַ�����ʱ���ʽΪ"HH:mm:ss"
	 */
	public static String getCurrentTimeNoGap() {
		GregorianCalendar tGCalendar = new GregorianCalendar();
		StringBuffer tStringBuffer = new StringBuffer(8);
		int sHOUR = tGCalendar.get(Calendar.HOUR_OF_DAY);
		if (sHOUR < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sHOUR);
		int sMINUTE = tGCalendar.get(Calendar.MINUTE);
		tStringBuffer.append(":");
		if (sMINUTE < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sMINUTE);
		int sSECOND = tGCalendar.get(Calendar.SECOND);
		tStringBuffer.append(":");
		if (sSECOND < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sSECOND);
		String tString = tStringBuffer.toString();
		return tString;
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static long idx = -1;
	private static String oldTime = "20120721123022";

	/**
	 * ��ʱ�����ɶ�һ�޶����ļ�������������׺
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String createTellerNo() {
		idx++;
		String tOldTime = sdf.format(new Date());		
		if(!tOldTime.substring(0,14).equals(oldTime)){
			idx = 0;
			oldTime = tOldTime.substring(0,14);
		} else if(idx > 999){//һ�����ڵ��ö���999�εĳ���
			try {
				//ͣһ�룬�����ɽ�����
				Thread.sleep(1000);
				return createTellerNo();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String pattern = "000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		String str =  tOldTime + df.format(idx);
		return "MAL"+str;
	}


	
	public static void main(String[] args){
//		XmlCreater creater = new XmlCreater();
//		Document doc = creater.createXml("ST000001");
//		System.out.println(doc.asXML());
//		WSClientHelper ws = new WSClientHelper();
//		ws.saveDoc(doc, "D:\\testXML\\ebusiness\\demo.xml");
		for (int i = 0; i < 20000; i++) {
			System.out.println(createTellerNo());
		}
		
	}
}
