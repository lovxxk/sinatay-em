<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>电子商务后台管理系统-编辑供应商信息</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/customerManage/party/thirdParterInfo/third.js"></script>
</head>
<body  onload="setUpdate();">
<input id="businessAreaId" value="<s:property value="geThirdParterInfo.businessArea"/>" type="hidden" />
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				编辑供应商信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
<%//隐藏表格区域start %>
<table  id="tableInfoHiddenForUpdate" width="750px" style="display: none;" border="0px" >
				<tr>
					<td>
					<table border="0px #E3E3E3 solid;" width="750px" style="border-collapse:collapse;margin-left:20px;">
					<tr><td colspan="4"><span id="count" style="font-weight: bold;">联系人</span></td></tr>
					<tr>
					<td width="6%" style="text-align: left;">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
					<td width="45%"><input name="userName" type="text" maxlength="10" />&nbsp;</td>
					<td width="5%" align="left">证件类型：</td>
					<td width="45%">
						<select name="identifyType" style="width:100px;">
							<s:if test="geCodeIdentifyTypeList!=null">
								<s:iterator value="geCodeIdentifyTypeList" var="geCodeIdentifyType">
								<s:if test="#geCodeIdentifyType.validInd==1">
								<option value="<s:property value="#geCodeIdentifyType.id.codeCode"/>"> <s:property value="#geCodeIdentifyType.codeCName"/> </option>
								</s:if>
								</s:iterator>							
							</s:if>
						</select>
					</td>
					</tr>
					
					<tr>
					<td>证件号码：</td>
					<td>
					<input type="text" name="identifyNumber" maxlength="25" onkeyup="validateIDNO(this);" onfocus="validateIDNO(this);"/>
					</td>
					
					<td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
					<td width="400px">
						<select name="sex" style="width:100px;">
						<s:if test="geCodeSexList!=null">
							<s:iterator value="geCodeSexList" var="geCodeSex">
							<option value="<s:property value="#geCodeSex.id.codeCode"/>"><s:property value="#geCodeSex.codeCName"/></option>
							</s:iterator>
						</s:if>
						</select>
					</td>
					</tr>
					
					<tr>
					<td>出生日期：</td>
					<td>
						<input type="text" name="birthday" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate" />
					</td>
					<td>手机号码：</td>
					<td width="400px"><input type="text" name="telePhone" maxlength="11"/></td>
					</tr>
					<tr>
					<td>电子邮箱：</td>
					<td width="400px"><input type="text" name="email" maxlength="30"/></td>
					</tr>
					<tr>
					<td>联系地址：</td>
					<td colspan="4">
						<textarea rows="3" cols="83" name="contactAddress" onkeyup="textAreaMaxLen(this);" maxlength="150"></textarea>
					</td>
					</tr>
					<tr>
					<td colspan='4'><input type='button'  value='删除' onclick='deleteTable(this);'/></td>
					</tr>
					
					</table>
					</td>
				</tr>
			</table>
<%//隐藏表格区域end %>

	<form id="frmInput" action="${ctx}/party/updateThirdParterInfoAndThirdParterContact.do" method="post" target="myFrame">
	<input type="hidden" value="<s:property value="geThirdParterInfo.createCode"/>" name="geThirdParterInfo.createCode">
	<input type="hidden" value="<s:date name="geThirdParterInfo.createDate" format="yyyy-MM-dd HH:mm:ss"/>" name="geThirdParterInfoCreateDate">
	
	<div class="table_style" align="center" style="display: inline;overflow: auto; ">
	<table class="table_style" align="center" width="700px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr style="display: none;">
		<td class="td_head" width="120px" nowrap>公司代码：</td>
		<td class="td_body">
			<input type="text" value="<s:property value="geThirdParterInfo.thirdParterID"/>" style="width:170px;" maxlength=80 disabled="disabled" >
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>公司名称：</td>
		<td class="td_body">
			<input type="text" id="thirdParterName" name="geThirdParterInfo.thirdParterName" value="<s:property value="geThirdParterInfo.thirdParterName"/>" style="width:240px;" maxlength=200 onkeyup="countThirdParterName()" onkeypress="countThirdParterName()" onblur="countThirdParterName();" onChange="countThirdParterName();">
			<input type="hidden" name="geThirdParterInfo.thirdParterID" value="<s:property value="geThirdParterInfo.thirdParterID"/>"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司网址：</td>
		<td class="td_body">
			<input type="text" id="infoUrl" name="geThirdParterInfo.url" value="<s:property value="geThirdParterInfo.url"/>" style="width:240px;" maxlength=200 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司电子邮箱：</td>
		<td class="td_body">
			<input  type="text" id="companyEmail" name="geThirdParterInfo.email" value="<s:property value="geThirdParterInfo.email"/>" style="width:240px;" maxlength=40 >
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司电话：</td>
		<td class="td_body">
			<input name="geThirdParterInfo.companyPhone" size="25" id="companyPhone" style="width:240px;" maxlength=13  value="<s:property value="geThirdParterInfo.companyPhone"/>">			
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司地址：</td>
		<td class="td_body">
			<textarea id="address" name="geThirdParterInfo.address"    rows="3" cols="36.5" onkeyup="countAddress()" onkeypress="countAddress()" onblur="countAddress();" onChange="countAddress();"><s:property value="geThirdParterInfo.address"/></textarea>
		</td>
	</tr>
	<%//机构地域 %>
		<tr>
		<td class="td_head" nowrap>业务：</td>
		<td class="td_body" >
		<s:iterator value="geBusinessAreaTypeList" var="geBusinessAreaType">
		<c:choose>
		<c:when test="${businessAreaDept=='1'}"><input type="Radio" id="actDeptId1" <c:if test="${(geBusinessAreaType.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geBusinessAreaType.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geBusinessAreaType.codeCName"/>&nbsp;</c:when>
		<c:when test="${businessAreaDept=='2'}"><input type="Radio" id="actDeptId2" <c:if test="${(geBusinessAreaType.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geBusinessAreaType.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geBusinessAreaType.codeCName"/>&nbsp;</c:when>
		<c:when test="${businessAreaDept=='3'}"><input type="Radio" id="actDeptId3" <c:if test="${(geBusinessAreaType.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geBusinessAreaType.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geBusinessAreaType.codeCName"/>&nbsp;</c:when>
		<c:when test="${businessAreaDept=='4'}"><input type="Radio" id="actDeptId4" <c:if test="${(geBusinessAreaType.id.codeCode) eq (businessAreaDept)}">checked</c:if> name="BusinessArea" value="<s:property value="#geBusinessAreaType.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geBusinessAreaType.codeCName"/>&nbsp;</c:when>
		<c:otherwise><input type="Radio" id="actDeptId5" checked name="BusinessArea" value="<s:property value="geBusinessAreaType.id.codeCode"/>"  onclick="selectProvince();"><s:property value="#geBusinessAreaType.codeCName"/>&nbsp;</c:otherwise>
		</c:choose>
		
		</s:iterator>
		
		
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>活动地域：</td>
		<td>
			<div style="float:left">
				<select id="mechanismArea" name="geThirdParterInfo.bu sinessArea" style="width:130px;" onchange="selectCity();">
				<option>全部区域</option>
				</select>
			</div>
			<div style="float:left;display: none;" id="suv">
				<select id="cityId"  name="geThirdParterInfo.deptID" >
					<option value="">--请选择--132</option>
				</select>
			</div>
			<div style="clear:both"></div>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司类型：</td>
		<td class="td_body">
			<select id="companyType" name="geThirdParterInfo.companyType" style="width:100px;" >
				<option value="">--请选择--</option>
				<s:if test="geCodeCompanyTypeList!=null">
					<s:iterator value="geCodeCompanyTypeList" var="geCodeCompanyType">
						<option value="<s:property value="#geCodeCompanyType.id.codeCode"/>" <s:if test="#geCodeCompanyType.id.codeCode==geThirdParterInfo.companyType">selected</s:if> ><s:property value="#geCodeCompanyType.codeCName"/></option>
					</s:iterator>
				</s:if>
			</select>
		</td>
	</tr>
	</table>
		<div style="background-color:#E9F8FF;border:1px #E3E3E3 solid;border-bottom-style:none;width: 800px;line-height: 30px;text-align: left ;">
			<span style="padding-left: 20px;" class="td_head">公司联系人</span>
		</div>
		<div style="border:1px #E3E3E3 solid;overflow:inherit;width:800px;" >
			<table  id="tableInfo"  border="0px #E3E3E3  solid;" width="750px">
			<s:if test="geThirdParterInfo.geThirdParterContacts!=null">
			<s:iterator value="geThirdParterInfo.geThirdParterContacts" var="geThirdParterContact" status="status" >
				<tr id="tableInfoHiddenForUpdate_tr${status.index}">
					<td>
					<table border="0px red solid;"  style="border-collapse:collapse;margin-left:20px;" >
					<tr><td colspan="4"><span id="count" style="font-weight: bold;">联系人${status.index+1}：</span></td></tr>
					<tr>
					<td width="6%" style="text-align: left;">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
					<td width="45%"><input id="userName${status.index}" name="userName" maxlength="10" type="text" value="<s:property value="#geThirdParterContact.userName"/>"/>&nbsp;</td>
					<td width="5%" align="left">证件类型：</td>
					<td width="45%">
						<select id="identifyType${status.index}" name="identifyType" style="width:100px;">
							<s:if test="geCodeIdentifyTypeList!=null">
								<s:iterator value="geCodeIdentifyTypeList" var="geCodeIdentifyType">
								<s:if test="#geCodeIdentifyType.validInd==1">
								<option value="<s:property value="#geCodeIdentifyType.id.codeCode"/>" <s:if test="#geThirdParterContact.identifyType==#geCodeIdentifyType.id.codeCode">selected</s:if>><s:property value="#geCodeIdentifyType.codeCName"/></option>
								</s:if>
								</s:iterator>							
							</s:if>
						</select>
					</td>
					</tr>
					
					<tr>
					<td>证件号码：</td>
					<td>
					<input id="identifyNumber${status.index}" type="text" maxlength="25" name="identifyNumber" value="<s:property value="#geThirdParterContact.identifyNumber"/>" onkeyup="validateIDNO(this);" onfocus="validateIDNO(this);"/>
					</td>
					
					<td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
					<td width="400px">
						<select id="sex${status.index}" name="sex" style="width:100px;">
						<s:if test="geCodeSexList!=null">
							<s:iterator value="geCodeSexList" var="geCodeSex">
							<option value="<s:property value="#geCodeSex.id.codeCode"/>" <s:if test="#geThirdParterContact.sex==#geCodeSex.id.codeCode">selected</s:if>><s:property value="#geCodeSex.codeCName"/></option>
							</s:iterator>
						</s:if>
						</select>
					</td>
					</tr>
					
					<tr>
					<td>出生日期：</td>
					<td>
						<input type="text" id="birthday${status.index}" name="birthday" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate" value="<s:property value="#geThirdParterContact.birthday"/>"/>
					</td>
					<td>手机号码：</td>
					<td width="400px"><input id="telePhone${status.index}" type="text" name="telePhone" value="<s:property value="#geThirdParterContact.telePhone"/>" maxlength="11"></td>
					</tr>
					<tr>
						<td>电子邮箱：</td>
						<td width="400px"><input id="email${status.index}" maxlength="30" type="text" name="email" value="<s:property value="#geThirdParterContact.email"/>"/></td>
					</tr>
					<tr>
						<td>联系地址：</td>
						<td colspan="4">
							<textarea rows="3" cols="81" id="contactAddress${status.index}" name="contactAddress" onkeyup="textAreaMaxLen(this);"><s:property value="#geThirdParterContact.contactAddress"/></textarea>
						</td>
					</tr>
					<tr>
					<td colspan='4'><input type='button'  value='删除' onclick='deleteTable(this);'/></td>
					</tr>
					</table>
					</td>
				</tr>
			</s:iterator>
			</s:if>
			</table>
			</div>
	<div style="border:0px #E3E3E3 solid;width: 723px;height:30px;padding-top:5px; text-align: right;">
		<div style="padding-right:58px"><input type="button" value="增加联系人" onclick="insertTableForUpdate();" ></div>
	</div>
	 <table align="center" id="operatorTable">
		<tr>
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:location.href ='${ctx}/party/viewCom.do?geThirdParterInfo.thirdParterID=<s:property value='geThirdParterInfo.thirdParterID'/>';">返回</td>
			<td id="updateButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doUpdate();" nowrap>修改</td>
			<td id="resetButton" class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
			<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
		</tr>
	</table>
	</div>
	<iframe id="myFrame" name="myFrame" style="display: none"></iframe>
	</form>
</div>
<script type="text/javascript">
function doClear(){
	location.href=location.href;
	//document.getElementById("frmInput").reset();
	//
	//设置页面的checkBox的选中
	//var businessAreas = document.getElementById("businessAreaId").value;
	//var businessAreaDoms = document.getElementsByName("geThirdParterInfo.businessArea");
	//var businessAreasTemp =businessAreas.split(",");
	//for(var i=0;i<businessAreasTemp.length;i++){
		//for(var j=0;j<businessAreaDoms.length;j++){
			//if(businessAreasTemp[i]==businessAreaDoms[j].value){
				//businessAreaDoms[j].checked=true;
			//}
		//}
	//}
}
//弹出一棵树
function alterTree(){
	var authorityid = document.getElementById("authorityid").value;
	var authorityDepartmentManager = document.getElementById("authorityDepartmentManager").value;
	window.open('${ctx}/marketing/selectDeptAuthId.do?operation=thirdPartterInfo&checkType=2&authorityid=ROLE_B_AAGA&type=2&receivedObj='+authorityid+'&receivedObjName='+authorityDepartmentManager,
			'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}
</script>
</body>
</html>