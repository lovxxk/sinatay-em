<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<c:set var="geFunctionSwitch" value="${requestScope.geFunctionSwitchForUpdate}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="UTF-8"></script>
<title>���������̨����ϵͳ-�༭�������ù���</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
 <script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			�༭��������
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form action="${ctx}/system/configManage/SMSConfig/update.do" id="SMSfm" method="post" target="myFrame">
	<table class="table_style" align="center" width="550px" id="geSmsConfigTable">
	<tr>
		<td class="td_head" width="120px" nowrap>�������ƣ�</td>
		<td class="td_body" width="530px">
		    <input  name="geSmsConfig.creater" type="hidden" value="${geSmsConfig.creater}"/>
		    <input  name="geSmsConfig.createTime" type="hidden" value="${geSmsConfig.createTime}"/>
			<input id="smsId" name="geSmsConfig.smsId" type="hidden" value="${geSmsConfig.smsId}"/>
			<input id="smsName" name="geSmsConfig.smsName" type="text" style="width:150px;" value="${geSmsConfig.smsName}" maxlength=30 >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>���ù��ܣ�</td>
		<td class="td_body" width="530px" >
            <input  name="geSmsConfig.functionID" type="hidden" style="width:170px;" value="${geSmsConfig.functionID}"/ >
			${geSmsConfig.sendSmsName}
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>��Ч��־��</td>
		<td class="td_body" width="530px">
		   <select id="validInd" name="geSmsConfig.validInd" style="width:150px;">
			<option value="1" 
			<c:if test="${geSmsConfig.validInd == '1'}">selected</c:if>
			>��Ч</option>
			<option value="0" 
			<c:if test="${geSmsConfig.validInd == '0'}">selected</c:if>
			}>��Ч</option>
		</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap style="height: auto;">�������ݣ�</td>
		<td class="td_body" width="350px" style="height: auto;">
			<textarea maxlength="200" id="smsContent" name="geSmsConfig.smsContent" onkeyup="textAreaMaxLen(this);" style="width:200px;height:50px;overflow: visible;word-wrap:break-word;word-break:break-all;" >${geSmsConfig.smsContent}</textarea>
		</td>
	</tr>
		<tr>
		 <td class="td_head" colspan="2"><font color="#FF0000">��������Ϊ��������#Ϊռλ������ �����磺�𾴵�#������Ͷ����#����#���ڣ��뾡��֧��</font></td>
		</tr>
	<tr><td height="25px"></td></tr>
	<tr>
		<td colspan=2>
			<table align="center">
			<tr>
			 <td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                  onmouseout="this.className='btnBigOnBlur'" 
                  onclick="javascript:location.href ='${ctx}/system/configManage/SMSConfig/viewSms.do?geSmsConfig.smsId=${geSmsConfig.smsId}'"  nowrap>����</td>
                <acc:showView source="ROLE_S_SMSC_U">
      				<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
      					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doSubmit();" nowrap>�޸�</td>
                    <td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
                     onmouseout="this.className='btnBigOnBlur'" onclick="javascript:doRest();" nowrap>����</td>
                </acc:showView>
              	<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>�ر�</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
tt.vf.req.add("geSmsConfig.smsName", "geSmsConfig.smsContent");
// var lengthLv =  new tt.LV().set(1,200);
// lengthLv.add("geSmsConfig.smsContent");
function doSubmit(){
	if(tt.validate()){
	   document.getElementById("SMSfm").submit();
	}
}
function doRest(){
	document.getElementById("SMSfm").reset();
}
function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("�������200����");
	    	return false ;
	    }
}

$(document).ready(function() {
	//pop��ʾ��Ϣ
	var ids = ['smsContent'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['������Ϊ��������#Ϊռλ������' ];
		
    	for ( var i = 0 ; i < 10 ; i++ ){
			$('#' + ids[i]).qtip({ 
				content:contents[i], 
				position: { 
					corner: { 
						 target: 'rightMiddle',   
				         tooltip: 'leftMiddle'  
					} 
				},
				 style: { 
				border: { 
					width: 1,
					radius: 1,
					color: '#00739f'
					},
					width:100,
					padding: 10, 
					textAlign: 'center',
					background: '#e0f2ff', 
					tip:true//����������Ƿ����
					//name: 'green' 
				} 
			});
    	}
    	
    	
});
</script>
</body>
</html>
