<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-个人客户详细信息</title>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			个人客户详细信息
		</div>
	</div>
	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<table  class="table_style" align="center" width="650px">
		<tr>
			<td class="td_head" width="100px" nowrap>客户号：</td>
			<td class="td_body">${geUserPersonal.userID}</td>
			<td class="td_head" width="100px" nowrap>客户姓名：</td>
			<td class="td_body">${geUserPersonal.userName}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>客户等级：</td>
			<td class="td_body">${geUserPersonal.userLevel }</td>
			<td class="td_head" nowrap>性别：</td>
			<td class="td_body">${geUserPersonal.sex=="1"?"男":(geUserPersonal.sex=="2"?"女":"")}</td>	
		</tr>
		<tr>
			<td class="td_head" nowrap>证件类型：</td>
			<td class="td_body">${identifyTypeName }</td>
			<td class="td_head" nowrap>证件号码：</td>
			<td class="td_body">${geUserPersonal.identifyNumber}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>出生日期：</td>
			<td class="td_body"><fmt:formatDate value="${geUserPersonal.birthday}" dateStyle="long"/></td>
			<td class="td_head" nowrap>注册时间：</td>
			<td class="td_body"><fmt:formatDate value="${geUserPersonal.makeDate}" dateStyle="long"/></td>
		</tr>
		<tr>
			<td  class="td_head" nowrap>登录帐号：</td>				
			<td class="td_body">${geUserPersonal.userAccount}</td>
			<td class="td_head" nowrap>鹤卡号：</td>
			<td class="td_body">${geUserPersonal.piCardNo}</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				邮箱：
			</td>
			<td class="td_body">
				${geUserPersonal.email}
			</td>
			<td class="td_head" nowrap>
				手机号：
			</td>
			<td class="td_body">
				${geUserPersonal.mobilePhone}
			</td>
		</tr>

		<tr>
			<td class="td_head" nowrap>
				婚姻状况：
			</td>
			<td class="td_body">
				${marriageStatus }
			</td>
			<td class="td_head" nowrap>
				所在地区：
			</td>
			<td class="td_body">
				${city }
			</td>
		</tr>

		
		<tr>
			<td class="td_head" nowrap>
				所在行业：
			</td>
			<td class="td_body">
				${industry }
			</td>
			<td class="td_head" nowrap>
				收入状况：
			</td>
			<td class="td_body">
				${income }
			</td>
		</tr>
		
		<tr>
			<td class="td_head" nowrap>
				家庭电话：
			</td>
			<td class="td_body">
				${geUserPersonal.homePhone}
			</td>
			<td class="td_head" nowrap>
				办公电话：
			</td>
			<td class="td_body">
				${geUserPersonal.officePhone}
			</td>
		</tr>
		
		<tr>
		<td class="td_head" nowrap>
				证书识别码 ：
			</td>
			<td class="td_body">
				${geUserPersonal.ukey}
			</td>
			<td class="td_head" nowrap>
				邮编：
			</td>
			<td class="td_body">
				${geUserPersonal.zipCode}
			</td>
		</tr>

		<tr>
			<td class="td_head" nowrap>
				客户来源：
			</td>
			<td class="td_body">
				${userSource}
			</td>
			<td class="td_head" nowrap>
				健康状况：
			</td>
			<td class="td_body">
				${health}
			</td>
		</tr>

		<tr>
			<td class="td_head" nowrap>
				客户状态：
			</td>
			<td class="td_body">
				${geUserPersonal.status=="0"?"无效":(geUserPersonal.status=="1"?"有效":(geUserPersonal.status=="2"?"未开通":""))}
			</td>
			<td class="td_head" nowrap>
				积分：
			</td>
			<td class="td_body">
				${geUserPersonal.integral}
			</td>
		</tr>
		<tr>
			<td class="td_head" nowrap>
				联系地址：
			</td>
			<td class="td_body" colspan="3">
				${geUserPersonal.contactAddress}
			</td>
		</tr>
		<tr height="10px">
			<td colspan="4"></td>
		</tr>
		<tr>
			<td colspan="4">
				<table width=200 align="center">
					<tr>
						<td width="20%">&nbsp;</td>
						<c:if test="${buttonFlag eq '0'}">
							<acc:showView source="ROLE_B_PUSE_U">
								<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doEdit();" nowrap>编辑</td>
							</acc:showView>
							<acc:showView source="ROLE_B_PUSE_B">
								<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="doUnBindPolicy();" nowrap>保单解绑</td>
							</acc:showView>
						</c:if>
						<!-- 
						<acc:showView source="ROLE_B_PUSE_N">
						<td onclick="doUnBindCard();" class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'">服务卡解绑</td>
						</acc:showView>
						 -->
						<td class="btnBigOnBlur" onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" onclick="doClose();" nowrap>关闭</td>
						<td  width="40%">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
//关闭该页面
function doClose(){
	window.close();
}
//转到编辑页面
function doEdit(){
	window.location = "${ctx }/business/customerManage/personalUser/queryGeUserPersonalForUpdateOrShow.do?handle=update&geUserPersonal.userID=${geUserPersonal.userID}";
}
//转到保单解绑查询页面
function doUnBindPolicy(){
	window.open("${ctx}/business/customerManage/personalUser/unbound/proOrPen/index.jsp?userID=${geUserPersonal.userID}&userName=${geUserPersonal.userAccount}","保单解绑" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
//转到服务卡解绑页面
function doUnBindCard(){
	//window.location = "#";
}
</script>
</body>
</html>
