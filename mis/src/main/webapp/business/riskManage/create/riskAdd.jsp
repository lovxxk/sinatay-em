<%@ page language="java" contentType="text/html;charset=GBK" isELIgnored="false"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
<title>�½�����</title>
<style type="text/css">
span.talentErrMsg{
	padding-left:17px;
}
</style>
</head>
<body>
<div class="public_fun_title">�½�����<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span></div>

	<form action="${ctx}/risk/addGeRisk.do" id="riskForm" method="post" target="myFrame">
	<table class="table_style" align="center"  id="geRiskTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>

    <tr>
      <td class="td_head" width="120px" nowrap>���ִ��룺</td>
      <td class="td_body" width="380px">
         <input id="riskCode" name="geRisk.riskCode" type="text" value="" style="width:240px;" maxlength=4>
      </td>
   </tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�������ƣ�</td>
		<td class="td_body" width="380px" >
            <input id="riskCName" name="geRisk.riskCName" type="text" value="" style="width:240px;" maxlength=400/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>Ӣ�����ƣ�</td>
		<td class="td_body" width="380px" >
           <input id="riskEName" name="geRisk.riskEName" type="text" value="" style="width:240px;" maxlength=400/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�������ƣ�</td>
		<td class="td_body" width="380px" >
           <input id="riskTName" name="geRisk.riskTName" type="text" value="" style="width:240px;" maxlength=400 />
		</td>
	</tr>
		<tr>
		<td class="td_head" width="120px" nowrap>ҵ������</td>
		<td class="td_body" width="380px">
			<select id="businessArea" name="geRisk.businessArea"  style="width:170px;" >
				<option value="">--��ѡ��--</option>
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<option value="${bussArea.id.codeCode}">${bussArea.codeCName}</option>
				</c:forEach>
             </select>  
		</td>
	</tr>
    <tr>
         <td class="td_head" width="120px" nowrap>�������</td>
         <td class="td_body" width="380px">
          <select id="riskType" name="geRisk.riskType" style="width:170px;">
				<option value="">--��ѡ��--</option>
				<option value="01">��ͳ��</option>
				<option value="02">�ֺ�</option>
				<option value="03">Ͷ��</option>
				<option value="04">����</option>
				<option value="05">����</option>
				<option value="13">������</option>
             </select>  
         </td>
    </tr>
   
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=4>
			<table width=64 align="center">
			<tr>
				<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="riskAddSubmit()"  nowrap>
                                                              ����
                </td>
                <td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>����</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�\
	new tt.LV().set(0,4).add("geRisk.riskCode");
	new tt.LV().set(0,100).add("geRisk.riskCName");
	new tt.LV().set(0,100).add("geRisk.riskEName");
	new tt.LV().set(0,100).add("geRisk.riskTName");
//	tt.vf.num.add("riskCode","riskType");"^\\d+$"
	tt.vf.req.add("geRisk.riskCode");
	tt.vf.req.add("geRisk.riskCName");
	tt.vf.req.add("geRisk.businessArea");
	tt.vf.req.add("geRisk.riskType");
	var testCName = new tt.RV().set(new RegExp("^[\u4E00-\u9FA5]*$"), "�Բ���ֻ�����뺺�֣�");
	testCName.add("geRisk.riskCName");
	var testEName = new tt.RV().set(new RegExp("^[A-Za-z]+$"), "�Բ���ֻ������Ӣ����ĸ��");
	testEName.add("geRisk.riskEName");
	var testCode = new tt.RV().set(new RegExp("^\\d+$"), "�Բ���ֻ������Ǹ�����");
	testCode.add("geRisk.riskCode");
	//testCode.add("geRisk.riskType");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.eid");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.productQuoteCode");

	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['˵�����½����֡�<br/>ʹ����Ⱥ�����ղ�Ʒ����������Ա��<br/>������������������ǿ�ƻ�绰Ӫ���ա�<br/>ע�����'];
    	for ( var i = 0 ; i < 10 ; i++ ){
			$('#' + ids[i]).qtip({ 
				content:contents[i], 
				position: { 
					corner: { 
					tooltip: 'topMiddle',
					target: 'bottomMiddle'
					} ,
					adjust: { 
						screen: true 
						}
				}, 
				 style: {
				border: { 
					width: 1,
					radius: 1,
					color: '#00739f'
					},
					width:300,
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true,//����������Ƿ����
					padding :10
				}
			});
    	}

})
function riskAddSubmit(){
	if(tt.validate()){
	   document.getElementById("riskForm").submit();
	}
}
//�������
function doClear(){
	document.getElementById("riskForm").reset();
}
</script>

	
</html>