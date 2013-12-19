<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<title>电子商务后台管理系统-险种详细信息</title>
<style type="text/css">
	td {
		vertical-align:top;
		text-align:right;
	}
	#productDetail tr {
		height:85px;
	}
	#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
	}
	.addDutyTable_title{
	    color: #ffffff;
	    font-weight: bold;
	    height: 23px;
	    text-align:center;
	    background-color:#318f5b;
	 }
	 .addDutyTable_body{
	    color: #616161;
	    height: 23px;
	    text-align:left;
	    padding-left:5px;
	 }
</style>
</head>
<body>
	<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				险种详细信息
			</div>
		</div>
		<div class="open_title_gap1"></div>
	</div>
	<div style="padding-top:15px;">
		<table class="table_style" align="center" id="geFunctionSwitchTable">
			<tr>
				<td class="td_head">险种代码： </td>
				<td class="td_body" width="400px">${geRiskConfig.riskCode}</td>
			</tr>
			<tr>
				<td class="td_head">险种名称：</td>
				<td class="td_body">${geRiskConfig.riskName}</td>
			</tr>
			<tr>
				<td class="td_head">险种简称：</td>
				<td class="td_body">${geRiskConfig.riskSimpleName}</td>
			</tr>
			<tr>
				<td class="td_head" >核心险种代码：</td>
				<td class="td_body">${geRiskConfig.coreRiskCode}</td>
			</tr>
			<tr>
				<td class="td_head" nowrap>主附险标志：</td>
				<td class="td_body" valign="top">
					<c:if test="${geRiskConfig.subRiskFlag=='01'}">是</c:if>
					<c:if test="${geRiskConfig.subRiskFlag=='02'}">否</c:if>
				</td>
			</tr>
			<tr>
				<td class="td_head" >业务领域：</td>
				<td class="td_body">
					<c:forEach items="${businessAreaList}" var="GeCode_businessArea" step="1" varStatus="status">
						<c:if test="${geRiskConfig.businessArea == GeCode_businessArea.id.codeCode}">
							${GeCode_businessArea.codeCName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div class="frmCreate_kuang" style="margin-left:10px;width:630px;margin-top:15px;">
						<div class="frmCreate_kuang_header">险种责任<span id="comChecked" style="color:#FF9000;font-weight:bold;"></span></div>
						<div style="padding-left:3px; padding-top:15px; padding-bottom:15px;">
							<table border="0" cellspacing="0" cellpadding="0" id="addDutyTable" class="table_Show">
								<tr>
									<td class="search_head" width="100" valign="top" nowrap>责任代码</td>
									<td class="search_head" width="250" valign="top" nowrap>责任名称</td>
									<td class="search_head" width="250" valign="top" nowrap>责任简称</td>
								</tr>
								<c:forEach items="${geRiskConfig.geDutyConfigs}" var="geDutyConfig">
									<tr>
										<td class="search_body" width="100" valign="top">${geDutyConfig.dutyCode}</td>
										<td class="search_body" width="250" valign="top">${geDutyConfig.dutyName}</td>
										<td class="search_body" width="250" valign="top">${geDutyConfig.simpleName}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding-top:10px;padding-bottom:20px;">
		<form id="operatorForm" method="post" enctype="multipart/form-data" action="" target="postTargetIframe">
			<table cellpadding="0" cellspacing="0" align="center" id="operatorTable">
				<tr>
					<acc:showView source="ROLE_B_PDIR_U">
						<td onclick="doEdit('${geRiskConfig.riskCode}');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" style="padding-right: 5px;" nowrap>编辑</td>
					</acc:showView>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap >关闭</td>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;height:1px;"></iframe>
	</div>
	<input type="hidden" id="saleChannel" name="geRiskConfig.saleChannel"  value="${geRiskConfig.saleChannel}" />
	<script type="text/javascript">
		//编辑
		function doEdit(idStr){
			location.href = "${ctx}/productManage/findGeRiskConfigByRiskCode.do?riskCode=" + idStr;		
		}
		
	</script>
</body>
</html>
