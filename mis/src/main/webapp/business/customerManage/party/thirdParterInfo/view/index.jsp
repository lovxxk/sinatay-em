<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>电子商务后台管理系统-查询供应商信息</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/customerManage/party/thirdParterInfo/third.js"></script>
</head>
<body onload="toBreakWord();">
<input id="businessAreaId" value="<s:property value="geThirdParterInfo.businessArea"/>" type="hidden" />
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				查询供应商信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
<%//隐藏表格区域start %>
<%/* 
<table  id="tableInfoHiddenForUpdate" width="720px" style="display: none;">
				<tr>
					<td>
					<table border="1px red solid;" width="100%" style="border-collapse:collapse;">
					<tr><td colspan="4"><span id="count">联系人</span></td></tr>
					<tr>
					<td width="10%" style="text-align: left;">姓名1</td>
					<td width="40%"><input name="userName" type="text" />&nbsp;</td>
					<td width="10%" align="left">证件类型</td>
					<td width="40%">
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
					<td>证件号码</td>
					<td>
					<input type="text" name="identifyNumber" onkeyup="validateIDNO(this);" onfocus="validateIDNO(this);"/>
					</td>
					
					<td>性别</td>
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
					<td>出生日期</td>
					<td>
						<input type="text" name="birthday" readonly onclick="WdatePicker({maxDate:'%y-%M-%d'})" class="Wdate" />
					</td>
					<td>手机号码</td>
					<td width="400px"><input type="text" name="telePhone" maxlength="11"/></td>
					</tr>
					<tr>
					<td>电子邮箱</td>
					<td width="400px"><input type="text" name="email" /></td>
					</tr>
					<tr>
					<td>联系地址</td>
					<td colspan="4">
					    <textarea rows="3" cols="83" onchange="textAreaMaxLen(this);" maxlength="150" name="contactAddress"></textarea>
<!--                   	<input type="text" name="contactAddress" />-->
					</td>
					</tr>
					<tr>
					<td colspan='4'><input type='button'  value='删除' onclick='deleteTable(this);'/></td>
					</tr>
					
					</table>
					</td>
				</tr>
			</table>
 */%>
<%//隐藏表格区域end %>
	<table class="table_style" align="center" width="900px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	
	<tr style="display: none;">
		<td class="td_head"  nowrap>公司代码：</td>
		<td class="td_body">
			<s:property value="geThirdParterInfo.thirdParterID"/>
		</td>
	</tr>
	<tr>
		<td class="td_head"  nowrap>公司名称：<input type="hidden" name="geThirdParterInfo.thirdParterID" value="<s:property value="geThirdParterInfo.thirdParterID"/>"/></td>
		<td class="td_body" id="tharterName">
			<s:property value="geThirdParterInfo.thirdParterName"/>
			
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司电话：</td>
		<td class="td_body">
			<s:property value="geThirdParterInfo.companyPhone"/>			
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司地址：</td>
		<td class="td_body" id="address"  style="word-break : break-all; width: 500px;overflow:hidden">
			<s:property value="geThirdParterInfo.address"/>
		</td>
	</tr>
		<tr>
		<td class="td_head" nowrap>公司电子邮箱：</td>
		<td class="td_body">
			<s:property value="geThirdParterInfo.email"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>公司网址：</td>
		<td class="td_body" id="urlIndo">
			<%/*
			<select id="identifyType" name="geBlackList.identifyType" style="width:100px;" >
				<option value="">--请选择--</option>
				<c:forEach items="${idTypeList}" var="GeCode_idType" step="1" varStatus="status">
					<option value="${GeCode_idType.id.codeCode}" ${GeCode_idType.id.codeCode == geBlackList.identifyType?"selected":"" }>${GeCode_idType.codeCName}</option>
				</c:forEach>
			</select>
			*/ %>
			<s:property value="geThirdParterInfo.url"/>
		</td>
	</tr>
	
	<tr>
		<td class="td_head" nowrap>公司类型：</td>
		<td class="td_body">
		<s:iterator value="geCodeCompanyTypeList" var="geCodeCompanyType">
			<s:if test="#geCodeCompanyType.id.codeCode==geThirdParterInfo.companyType">
				<s:property value="#geCodeCompanyType.codeCName"/>
			</s:if>
		</s:iterator>
		</td>
	</tr>
	<%//业务领域 %>
	<%/*
	<tr>
		<td class="td_head" nowrap>业务领域：</td>
		<td class="td_body">
		<s:if test="geBusinessAreaTypeList!=null">
			<s:iterator value="geBusinessAreaTypeList" var="businessArea">
			<input type="checkbox" name="geThirdParterInfo.businessArea" value="<s:property value="#businessArea.id.codeCode"/>"><s:property value="#businessArea.codeCName"/>
			</s:iterator>
		</s:if>
		</td>
	</tr>
	*/ %>
	<%//机构地域 %>
	<tr>
		<td class="td_head" nowrap>机构地域：</td>
		<td class="td_body">
			<s:property value="geThirdParterInfo.deptName"/>
			<input type="hidden" id="authorityid" name="geThirdParterInfo.deptID" value="<s:property value="geThirdParterInfo.deptID"/>">
		</td>
		<td class="td_head" nowrap valign="top"></td>
		<td class="td_body">
 </table>
<div style="background-color:#E9F8FF;border:1px #E3E3E3 solid;border-bottom-style:none;width: 616px;line-height: 30px;text-align: left ;">
	<span style="padding-left: 10px" class="td_head">公司联系人</span>
</div>
			<div style="border:1px #E3E3E3 solid;overflow:inherit;width:500px">
			<s:if test="geThirdParterInfo.geThirdParterContacts!=null">
			<s:iterator value="geThirdParterInfo.geThirdParterContacts" var="geThirdParterContact" status="status" >
			<table  id="tableInfo" style="border:0px #E3E3E3 solid;width:500px;margin-left:20px; ">
				<tr id="${status.index}">
					<tr><td colspan="4"><span id="count" style="font-weight: bold;">联系人${status.index+1}</span></td></tr>
					<tr>
					<td width="10%" style="text-align: left;">姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
					<td width="40%"><s:property value="#geThirdParterContact.userName"/>&nbsp;</td>
					<td width="10%" align="left">证件类型：</td>
					<td width="40%">
						<s:iterator value="geCodeIdentifyTypeList" var="geCodeIdentifyType">
							<s:if test="#geThirdParterContact.identifyType==#geCodeIdentifyType.id.codeCode">
								<s:property value="#geCodeIdentifyType.codeCName"/>
							</s:if>
						</s:iterator>
					</td>
					</tr>
					
					<tr>
					<td>证件号码：</td>
					<td>
					<s:property value="#geThirdParterContact.identifyNumber"/>
					</td>
					
					<td>性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
					<td width="400px">
						<s:iterator value="geCodeSexList" var="geCodeSex">
							<s:if test="#geThirdParterContact.sex==#geCodeSex.id.codeCode">
								<s:property value="#geCodeSex.codeCName"/>
							</s:if>
						</s:iterator>
						
					</td>
					</tr>
					
					<tr>
					<td>出生日期：</td>
					<td>
						<s:property value="#geThirdParterContact.birthday"/>
					</td>
					<td>手机号码：</td>
					<td width="400px"><s:property value="#geThirdParterContact.telePhone"/></td>
					</tr>
					<tr>
					<td>电子邮箱：</td>
					<td width="400px"><s:property value="#geThirdParterContact.email"/></td>
					</tr>
					<tr>
					<td>联系地址：</td>
					<td id="ctAddress" colspan="4"><textarea rows="3" cols="83" readonly><s:property value="#geThirdParterContact.contactAddress"/></textarea></td>
					</tr>
				</table>
			</s:iterator>
			</s:if>
			</div>
		</td>
	</tr>
	<%/*
	<tr>
		<td class="td_head" nowrap>业务领域：</td>
		<td class="td_body">
			<select id="businessArea" name="geBlackList.businessArea" style="width:170px;">
				<option value="1" ${geBlackList.businessArea=="1"?"selected":"" }>集团</option>
				<option value="2" ${geBlackList.businessArea=="2"?"selected":"" }>财险</option>
				<option value="3" ${geBlackList.businessArea=="3"?"selected":"" }>寿险</option>
				<option value="4" ${geBlackList.businessArea=="4"?"selected":"" }>养老险</option>
				<option value="9" ${geBlackList.businessArea=="9"?"selected":"" }>其他</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>加入黑名单原因：</td>
		<td class="td_body">
			<textarea rows="8" cols="40" id="reason" name="geBlackList.reason">${geBlackList.reason}</textarea>
		</td>
	</tr>
	*/ %>
</div>
<div style="padding-top:10px;padding-bottom:20px;">
		<form id="operatorForm" method="post" enctype="multipart/form-data" action="" target="postTargetIframe">
			<table cellpadding="0" cellspacing="0" id="operatorTable" align="center">
				<tr>
					<td onclick="javascript:maximizeGrid(this);">
						&nbsp;
					</td>					
					<acc:showView source="ROLE_B_PDIR_U">
						<td onclick="doEdit('<s:property value="geThirdParterInfo.thirdParterID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" style="padding-right: 20px" >编辑</td>
					</acc:showView>
					<acc:showView source="ROLE_B_PDIR_U">
						<td onclick="doDelete('<s:property value="geThirdParterInfo.thirdParterID"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" style="padding-right: 20px">删除</td>
					</acc:showView>
					
					<td onclick="pageclose();"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">关闭</td>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;"></iframe>
	</div>
	<script type="text/javascript">
		//编辑
		function doEdit(idStr){
			location.href = "${ctx}/party/prepareUpdateThirdParterInfo.do?geThirdParterInfo.thirdParterID=" + idStr;
			
		}
		
		
		//删除
		function doDelete(idStr){
			if(confirm('您确认删除该供应商吗？')){
				location.href = "${ctx}/party/deleteThirdParterInfoAndThridParterContact.do?geThirdParterInfo.thirdParterID=" + idStr;
			}
		
		}
		//关闭
		function pageclose(){
			window.close();
			window.parent.opener.parent.frames[0].doSearch();
		}
		
		String.prototype.trim = function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
<script type="text/javascript">
function doClear(){
	document.getElementById("frmInput").reset();
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
</script>
</body>
</html>
