package test;

import cn.com.sinosoft.portalModule.interfacePortal.client.servlet.ServletClient;

public class XmlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String transrNo = "20130916175000055020";
		String url = "http://10.20.3.147:9001/SIP/URLServer";
//		url = "http://10.20.3.97:7001/xintaisug/servlet/TestPayServlet";
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>"+
					"<TranData><BaseInfo><TransrDate>20130916</TransrDate><TransrTime>13:08</TransrTime><TellerNo></TellerNo><TransrNo>"+transrNo+"</TransrNo><SaleChannel>W</SaleChannel><SellType>20</SellType><FunctionFlag>ST000022</FunctionFlag><Source>WEB</Source></BaseInfo><TranRequest><LCCont><ProposalContNo></ProposalContNo><PolApplyDate>20130916</PolApplyDate><AccName></AccName><AccBankCode></AccBankCode><AccProvince></AccProvince><AccCity></AccCity><AccType></AccType><BankAccNo></BankAccNo><SecBankCode></SecBankCode><SecBankAccNo></SecBankAccNo><SecAccName></SecAccName><SecAccProvince></SecAccProvince><SecAccCity></SecAccCity><SecAccType></SecAccType><PayMode></PayMode><ExPayMode></ExPayMode><GetPolMode></GetPolMode><ManageCom></ManageCom><Password></Password><SpecContent></SpecContent><PrtNo></PrtNo><TempFeeNo></TempFeeNo><AgentCode></AgentCode><AgentGroup></AgentGroup><AgentName></AgentName><AgentCom></AgentCom><AgentComName></AgentComName><DisputedFlag></DisputedFlag><ACName></ACName><LCAppnt><AppntName>¡ı≥ø</AppntName><AppntSex>1</AppntSex><AppntBirthday>19860523</AppntBirthday><AppntIDType>0</AppntIDType><AppntIDNo>370921198605231516</AppntIDNo><IDValidFlag></IDValidFlag><IDStartDate></IDStartDate><IDEndDate></IDEndDate><AppntPhone></AppntPhone><AppntOfficePhone></AppntOfficePhone><AppntMobile>15800760896</AppntMobile><Household></Household><Marriage></Marriage><Province></Province><City></City><County></County><HomeAddress></HomeAddress><HomeZipCode></HomeZipCode><MailAddress></MailAddress><MailZipCode></MailZipCode><GrpName></GrpName><Email>lovxxk@126.com</Email><JobType></JobType><JobCode></JobCode><Nationality></Nationality><InCome></InCome><RelaToInsured></RelaToInsured><TellInfos><TellInfoCount>1</TellInfoCount><TellInfo><TellVersion></TellVersion><TellCode></TellCode><TellContent></TellContent><TellRemark></TellRemark></TellInfo></TellInfos></LCAppnt><LCInsureds><LCInsuredCount>1</LCInsuredCount><LCInsured><Name>¡ı≥ø</Name><Sex>1</Sex><Birthday>19860523</Birthday><IDType>0</IDType><IDNo>370921198605231516</IDNo><IDValidFlag></IDValidFlag><IDStartDate></IDStartDate><IDEndDate></IDEndDate><HomePhone></HomePhone><OfficePhone></OfficePhone><Mobile>15800760896</Mobile><Household></Household><Marriage></Marriage><Province></Province><City></City><County></County><HomeAddress></HomeAddress><HomeZipCode></HomeZipCode><MailAddress></MailAddress><MailZipCode></MailZipCode><GrpName></GrpName><Email>lovxxk@126.com</Email><JobType></JobType><JobCode></JobCode><Nationality></Nationality><InCome></InCome><RelaToMain></RelaToMain><RelaToAppnt></RelaToAppnt><TellInfos><TellInfoCount>1</TellInfoCount><TellInfo><TellVersion></TellVersion><TellCode></TellCode><TellContent></TellContent><TellRemark></TellRemark></TellInfo></TellInfos><Risks><RiskCount>1</RiskCount><Risk><RiskCode>00136200</RiskCode><MainRiskCode>00136200</MainRiskCode><RiskType></RiskType><RiskName></RiskName><Amnt>10</Amnt><Rate></Rate><CValiDate></CValiDate><Rank></Rank><Prem>1.4</Prem><Mult>1</Mult><PayIntv></PayIntv><CostIntv></CostIntv><CostDate></CostDate><Years>30</Years><SpecContent></SpecContent><PayEndYearFlag></PayEndYearFlag><PayEndYear></PayEndYear><GetYearFlag></GetYearFlag><GetYear></GetYear><InsuYearFlag></InsuYearFlag><InsuYear></InsuYear><GetIntv></GetIntv><GetBankCode></GetBankCode><GetBankAccNo></GetBankAccNo><GetAccName></GetAccName><GetAccProvince></GetAccProvince><GetAccCity></GetAccCity><GetAccType></GetAccType><AutoPayFlag></AutoPayFlag><BonusPayMode></BonusPayMode><SubFlag>1</SubFlag><BonusGetMode></BonusGetMode><AutoRNewFlag></AutoRNewFlag><HealthFlag></HealthFlag><FullBonusGetMode></FullBonusGetMode><FirstRate></FirstRate><SureRate></SureRate><Accounts><AccountCount>1</AccountCount><Account><AccNo></AccNo><AccMoney></AccMoney><AccRate></AccRate></Account></Accounts><LCBnfs><LCBnfCount>1</LCBnfCount><LCBnf><BnfType></BnfType><BnfGrade></BnfGrade><Name></Name><Sex></Sex><Birthday></Birthday><IDType></IDType><IDNo></IDNo><IDValidFlag></IDValidFlag><IDStartDate></IDStartDate><IDEndDate></IDEndDate><RelationToInsured></RelationToInsured><BnfLot></BnfLot></LCBnf></LCBnfs></Risk></Risks></LCInsured></LCInsureds></LCCont></TranRequest></TranData>";
		try {
			String resXML = ServletClient.sendRequestXML(url, xml, "GBK");
			System.out.println(resXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
