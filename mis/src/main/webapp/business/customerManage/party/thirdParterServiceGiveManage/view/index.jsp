<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=GBK">
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css"/>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/business/marketingManage/marketing.js"></script>
<title>查询商品赠送</title>
<style type="text/css">
	td {
		vertical-align:top;
	}
	#productDetail tr {
		height:85px;
	}
	#operatorTable td {
		vertical-align:middle;
		text-align:center;
		width:82px;
	}
</style>
</head>
<body >
<div id="open_titleDIV">
		<div class="open_title_c">
			<div class="open_title">
				查询商品赠送
			</div>
		</div>
		<div class="open_title_gap1"></div>
</div>
<div style="padding-top:15px;">
<table class="table_style" style="margin-left:120px" id="productTable">
	<tr>
		<td height=10>&nbsp;</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>投保单号：</td>
		<td class="td_body" width="380px">
			<s:property value="geThirdParterSerialNumber.proposalNo"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>用户名id：</td>
		<td class="td_body" width="380px">
			<s:property value="geThirdParterSerialNumber.userID"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>商品名称：</td>
		<td class="td_body" width="380px">
			<s:property value="geThirdParterSerialNumber.geThirdParterService.itemName"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>数量：</td>
		<td class="td_body" width="380px">
			<s:property value="geThirdParterSerialNumber.count"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>投保地区：</td>
		<td class="td_body" width="380px">
			<s:property value="geThirdParterSerialNumber.proposalAreaName"/>
		</td>
	</tr>
	<tr>
		<td class="td_head" width="120px" nowrap>是否赠送：</td>
		<td class="td_body" width="380px"><input type="hidden" id="flag" value="<s:property value="geThirdParterSerialNumber.validInd"/>">
				<s:if test="geThirdParterSerialNumber.validInd==1">
					已赠送
				</s:if>
				<s:if test="geThirdParterSerialNumber.validInd==0">
					未赠送
				</s:if>
		</td>
	</tr>
</table> 
</div>
<div style="padding-top:10px;padding-bottom:20px;">
		<form id="operatorForm" name="form1" method="post" enctype="multipart/form-data" action="" target="postTargetIframe">
			<table cellpadding="0" cellspacing="0" style="margin-right:150px" id="operatorTable">
				<tr>
					<td onclick="javascript:maximizeGrid(this);">
						&nbsp;
					</td>
					<s:if test="geThirdParterSerialNumber.validInd==0">
						<acc:showView source="ROLE_B_TPSG_M_Z">
							<td id="send" onclick="dosend('<s:property value="geThirdParterSerialNumber.searialNo"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
								onmouseout="this.className='btnBigOnBlur'">赠送</td>
						</acc:showView>
					</s:if>					
					
					<%//这个暂时还没有加上去呀  %>
					<%/*
					<acc:showView source="ROLE_B_PDIR_U">
					
					</acc:showView>
					*/ %>
					<%//ROLE_B_TPSG_M_B%>
					<s:if test="geThirdParterSerialNumber.validInd==1">
					<acc:showView source="ROLE_B_TPSG_M_B">
						<td id="back" onclick="doBack('<s:property value="geThirdParterSerialNumber.searialNo"/>');"  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
							onmouseout="this.className='btnBigOnBlur'" >回退</td>
					</acc:showView>
					</s:if>
				</tr>
			</table>
		</form>
		<iframe name="postTargetIframe" style="display:none;"></iframe>
	</div>
	<script type="text/javascript">
	//先判断商品状态。根据状态显示赠送或是回退
	$(document).ready(function(){
		  if($("#flag").val()==1){
			$("#back").show();
			$("#send").hide();
			
		  }else{
			  $("#send").show();
			  $("#back").hide();
		  }
	}); 

		//赠送
		function dosend(searialNo){
			location.href = "${ctx }/party/updateGeThirdParterSerialNumberValidInd.do?searialNo=" + searialNo;
			//location.href = "${ctx}/productDirectory/findGeDirectoryByEId.do?geDirectory.eid=" + idStr;		
		}
		
		
		//回退
		function doBack(searialNo){
			location.href = "${ctx }/party/updateGeThirdParterSerialNumberInValidInd.do?searialNo=" + searialNo;
			//location.href ="${ctx}/marketing/findAddGeAddServiceActivity.do";
		
		}
		
		String.prototype.trim = function(){
		    return this.replace(/(^\s*)|(\s*$)/g, "");
		}
	</script>
</body>
<script type="text/javascript">
function doUpdate(){
	window.close();
}
</script>
</html>