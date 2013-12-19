<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<script src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="utf-8"></script>
<title>���������̨����ϵͳ-�༭����</title>
<style type="text/css">
span.talentErrMsg{
	padding-left:17px;
}
</style>
</head>
<body>
<<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�༭����
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/universal/updateUniversal.do" id="universalFm" method="post">
	<table class="table_style" align="center" style="width:500px" id="universalTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>��Ʒ���ƣ�</td>
		<td class="td_body" width="390px">
		<input type="hidden" id="serialNo" name="universal.serialNo" maxlength="32" value="${universal.serialNo}" style="width: 160px;"/>
		${universal.riskName}	
 		<input id="riskName" name="universal.riskName" maxlength="60" value="${universal.riskName}" style="display:none;width: 160px;"/>	
 		</td>
	</tr>
    <tr>
		<td class="td_head" width="120px" nowrap>�������ڣ�</td>
		<td class="td_body" width="390px" >
			<s:date name="universal.culDate" format="yyyy-MM-dd" />
            <input id="culDate" name="universal.culDate" type="text" value="${universal.culDate}" style="display:none;width:215px;""/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���������ʣ�</td>
		<td class="td_body" width="390px" >
           <input id="dateRate" name="universal.dateRate" type="text" value="${universal.dateRate}" style="width:100px;" maxlength=10 />%
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>�ۺ���������ʣ�</td>
		<td class="td_body" width="390px" >
           <input id="yearRate" name="universal.yearRate" type="text" value="${universal.yearRate}" style="width:100px;" maxlength=10/>%
		</td>
	</tr>
	<tr height=35><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			<tr>
			<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="goToView();" nowrap>����  </td>
              
				<td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="universalFmSubmit();" nowrap>�޸�  </td>
                <td id="updateButton" align=right class="btnBigOnBlur" onclick="javascript:doClear();" nowrap>����</td>
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//������һ�д���Ϊ3���ֶ�����ˡ��ǿա���֤��ͬʱ���Զ���3���ֶ��ϼ��Ϻ�ɫ�Ǻ�
	new tt.LV().set(0,10).add("universal.dateRate");
	new tt.LV().set(0,10).add("universal.yearRate");
//	tt.vf.num.add("riskCode","riskType");"^\\d+$"
	tt.vf.req.add("universal.dateRate");
	tt.vf.req.add("universal.yearRate");
	new tt.RV().set(new RegExp("^((\\d){1,2})(\\.(\\d){1,6})?$"), "��ȷ�����������").add("universal.dateRate");
	new tt.RV().set(new RegExp("^((\\d){1,2})(\\.(\\d){1,6})?$"), "��ȷ�����������").add("universal.yearRate");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.eid");
	//new tt.RV().set(new RegExp("^\\w{1,30}$"), "ֻ������A-Z,a-z,0-9,�ҳ���Ϊ1-30").add("geDirectory.productQuoteCode");
})
function universalFmSubmit(){
	if(tt.validate()){
	   document.getElementById("universalFm").submit();
	}
}

function doClear(){
	document.getElementById("universalFm").reset();
}

function goToView(){
	window.location.href="${ctx}/universal/viewUniversal.do?universal.serialNo="+'${universal.serialNo}';
	
}
</script>
</html>
