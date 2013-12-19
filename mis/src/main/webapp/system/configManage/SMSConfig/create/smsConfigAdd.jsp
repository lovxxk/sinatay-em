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
<title>创建短信配置</title>
</head>
<body>
<div class="public_fun_title">
新建短信配置<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>
<form action="${ctx}/system/configManage/SMSConfig/add.do" id="SMSform" method="post" target="myFrame">
<table align="center" width="500px" id="SMSTable">
<tr>
	<td class="td_head" nowrap width="120px">短信名称：</td>
	<td class="td_body" width="510px">
		<input id="smsName" name="geSmsConfig.smsName" type="text" style="width:150px;" maxlength=30 class=required>
	</td>
</tr>
<tr>
	<td class="td_head" nowrap width="120px">适用功能：</td>
	<td class="td_body" width="510px">
	    <select id="functionID" name="geSmsConfig.functionID" style="width:150px;">
			<s:if test="#request.SendSmsTypeList!=null">
              <s:iterator value="#request.SendSmsTypeList" var="code">
                 <option value="<s:property value="#code.id.codeCode"/>"><s:property value="#code.codeCName"/></option>
              </s:iterator>
            </s:if>
		</select>
	</td>
</tr>
<tr>
	<td class="td_head" nowrap width="120px">有效标志：</td>
	<td class="td_body" width="510px">
		<select id="validInd" name="geSmsConfig.validInd" style="width:150px;">
			<option value="1">有效</option>
			<option value="0">无效</option>
		</select>
	</td>
</tr>
<tr>
	<td class="td_head" width="120px" nowrap style="height: auto;">短信内容：</td>
	<td class="td_body" width="350px" style="height: auto;"  nowrap>
	   <textarea maxlength="200" onkeyup="textAreaMaxLen(this);" id="smsContent" name="geSmsConfig.smsContent"  rows="5" cols="31"  class=required></textarea>
	</td>
	
</tr>
<tr height="25px"><td></td></tr>
<tr>
	<td colspan=2>
		<table align="center">
		<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript"><!--
tt.vf.req.add("geSmsConfig.smsName", "geSmsConfig.smsContent");
//var lengthLv =  new tt.LV().set(1,200);
//lengthLv.add("geSmsConfig.smsContent");
function doCreate(){
	if(tt.validate()){
	   document.getElementById("SMSform").submit();
	}
}
function doClear(){
	document.getElementById("SMSform").reset();
}
function textAreaMaxLen(field){
	 var iMaxLen = parseInt(field.getAttribute('maxlength'));
	    var iCurLen = field.value.length;
	    if ( field.getAttribute && iCurLen > iMaxLen ){
	    	field.value = field.value.substring(0, iMaxLen);
	    	alert("最多输入200个字");
	    	return false ;
	    }
}
$(function(){
	//pop提示信息
	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建短信配置<br/>使用人群：短信配置人员<br/>配置条件：适用功能要准确应用 。<br/>注意事项：短息内容#为占位符，将来程序会替换成文字。--例如：您好！#的投保单#已于#付款成功，金额共计￥#元,请进行后续业务操作 '];
		
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
					tip:true,//控制左侧尖角是否出现
					padding :10
				}
			});
    	}
	
});
</script>
</html>