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
<title>创建机构邮箱配置</title>
</head>
<body>   
<div class="public_fun_title">
	新建机构邮箱<span id="des" style="cursor: pointer;font-size: 15px;"><img style="vertical-align:middle;color:#E9E7E8" src="${ctx}/global/images/help.png"/></span>
</div>
<form action="${ctx}/system/configManage/emailConfig/add.do" id="DeptMailform" method="post" target="myFrame">
<table align="center" width="500px" id="DeptMailTable">
<tr>
	<td height=10>&nbsp;</td>
</tr>
<tr>
  <td class="td_head" nowrap>机构：</td>
  <td class="td_body" width="100%">
     <div>
         <div style="width:450px;float:left;">
            <input type="text" id="authorityDepartmentManager" name="deptName" value=""  ondblclick="deptAuthIdQuery();" style="width:170px;" readonly>
            <input type="hidden" id="authorityid" name="geDeptMail.deptID" value=""/>
         	<input style="width:60px;" onclick="deptAuthIdQuery();" type="button" value="选择机构…" />
         </div>
     </div>
               
  </td> 
</tr>
<tr>
	<td class="td_head" width="120px" nowrap>适用功能模块：</td>
	<td class="td_body" width="100%">
	    <select id="functionID" name="geDeptMail.functionID" style="width:170px;">
			<s:if test="#request.sendMailTypeList!=null">
              <s:iterator value="#request.sendMailTypeList" var="code">
                 <option value="<s:property value="#code.id.codeCode"/>"><s:property value="#code.codeCName"/></option>
              </s:iterator>
            </s:if>
		</select>
	</td>
</tr>
<tr>
	<td class="td_head" width="120px" nowrap>邮箱：</td>
	<td class="td_body" width="100%">
	   <input id="mail" name="geDeptMail.mail" type="text" onkeyup="tt2hide();" onclick="checkMobile();" style="width:170px;" maxlength=50 class=required />
	</td>
</tr>
 <tr>
	<td class="td_head" width="120px" nowrap>手机：</td>
	<td class="td_body" width="100%">
	   <input id="mobile" name="geDeptMail.mobile" type="text" onkeyup="tt2hide();" onclick="checkMobile();" style="width:170px;" maxlength=11 class=required />
	   <span id="tt2" style="display:none" class="talentErrMsg" style="top: 70px; left: 475px;">电子邮箱或电话请至少选一种!</span>
	</td>
</tr> 
<tr>
	<td class="td_head" width="120px" nowrap>有效标志：</td>
	<td class="td_body" >
	   <select id="validInd" name="geDeptMail.validInd" style="width:170px;">
			<option value="1">有效</option>
			<option value="0">无效</option>
		</select>
	</td>
</tr>
<tr><td height="25px"></td></tr>
<tr>
	<td colspan=2>
		<table style="margin-left:130px">
		<tr>
			<td id="createButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" onclick="doCreate();" nowrap>创建 </td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
				onmouseout="this.className='btnBigOnBlur'" align=right onclick="doClear();" nowrap>重置</td>
		</tr>
		</table>
	</td>
</tr>
</table> 
<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
</form>
</body>
<script type="text/javascript">
tt.vf.req.add("geDeptMail.functionID","deptName");
tt.vf.email.add("geDeptMail.mail"); 
new tt.RV().set(new RegExp("^1[0-9]{10}$"),  
"手机格式不正确！").add("geDeptMail.mobile");
function doCreate(){
	if(tt.validate()){
		if(checkMobile()){
			document.getElementById("DeptMailform").submit();
		}
	}
}
function doClear(){
	document.getElementById("DeptMailform").reset();
}
function deptAuthIdQuery(){
	var authorityid = document.getElementById("authorityid").value;
	var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
	window.open('${ctx}/deptAuthority/selectDeptAuthIdForMailConfig.do?checkType=2&authorityid=ROLE_S_ECON_I&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}

function checkMobile(){
	if($("#mail").val()==""&&$("#mobile").val()==""){
		//alert("电子邮箱或电话请至少选一种");
		$("#tt2").show(); 
		return false;
	}else{
		return true;
	}
}
function tt2hide(){
	if($("#mail").val()!=""||$("#mobile").val()!=""){
		$("#tt2").hide();
	}
}
$(function(){
	//pop提示信息
	var ids = ['des'];
	// <img src="'+contextRootPath+'/global/images/form_success.gif" />
	var contents = ['说明：新建机构邮箱<br/>使用人群：系统配置人员 <br/>配置条件：用于维护各个业务部门的客服邮箱 <br/>注意事项：邮箱或手机可用。'];
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