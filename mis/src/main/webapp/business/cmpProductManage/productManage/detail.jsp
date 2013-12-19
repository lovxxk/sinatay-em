<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>详细信息</title>
	<link href="/mis/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/mis/global/js/jquery-1.6.2.min.js"></script>

	<link type="text/css" rel="stylesheet" href="/mis/global/js/validate/css/validate.css" />
	<script type="text/javascript" src="/mis/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
</head>


<body>
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			详细信息
		</div>
	</div>

	<div class="open_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form name="sub" action="/mis/productManage/subDetail.do" method="post">
			<input type="hidden" name="geProductMain.coreProductCode" value="${geProductMain.coreProductCode}"/>
			<input type="hidden" name="type" id="in_type"/>
			<input type="hidden" name="taskID" value="${taskID}"/>
			<input type="hidden" name="workFlowID" value="${workFlowID}"/>
	
	<table class="table_Show" align="center" width="650px" border="0">
	
	<c:if test="${geProductMain.productStatus =='06'}">
	<tr>
		<td class="td_head" width="70px">业务领域：</td>
		<td class="td_body" colspan="3">
			<input type="radio" name="newBusinessArea" value="2" ${geProductMain.businessArea == '2'?"checked":""}>寿险
			<input type="radio" name="newBusinessArea" value="3" ${geProductMain.businessArea == '3'?"checked":""}>财险
			<input type="radio" name="newBusinessArea" value="4" ${geProductMain.businessArea == '4'?"checked":""}>养老险
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td class="td_head" width="100px">产品代码：</td>
		<td class="td_body" width="255px">${geProductMain.coreProductCode}</td>
		<td class="td_head" width="70px">产品名称：</td>
		<td class="td_body">${geProductMain.productName}</td>
	</tr>
	<tr>

		<td class="td_head">提交时间：</td>
		<td class="td_body"> <fmt:formatDate value="${geProductMain.createDate}" pattern="yyyy-MM-dd"/> </td>
		<td class="td_head">产品状态：</td>
		<td class="td_body">
			<c:forEach items="${code}" var="code" varStatus="stas">
					<c:if test="${code.id.codeCode eq geProductMain.productStatus}">${code.codeCName}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td class="td_head"  width="70px">销售流程：</td>

		<td class="td_body" colspan="3">${geProductMain.productFlow}</td>
	</tr>
	<tr>
		<td class="td_head"  width="70px">产品简介：</td>
		<td class="td_body" colspan="3">${geProductMain.coreProductSimpleName}</td>
	</tr>
	
	
	<tr height="10px"><td colspan="4">&nbsp;</td></tr>
	
	<!-- 
	<c:if test="${'workflow' == type}">
		<c:if test="${geProductMain.productStatus=='03' || geProductMain.productStatus=='04'}">
		<tr>
			<td colspan="4" class="frmCreate_kuang" valign="top">
			<div class="frmCreate_kuang_header">
				处理意见
			</div>
			<div style="padding-left:3px;">
				<textarea id="suggestion" name="suggestion" class="textarea_disabled"></textarea>
	    			<span id="suggestion_msg"></span>
			</div>
			</td>
		</tr>
		</c:if>
	</c:if>
	 -->

	<tr height="10px"><td></td></tr> 
</table>
</form>



<table class="table_Show" width="650px" align="center" border="0">

<c:if test="${geProductMain.productStatus=='06'}">
   <tr height="5%">
   		<td height="40px"  colspan="6" align="left">

	   		<table  border="0" width=100% align="center">
   				<tr height="10px">
					<td width="75px" align="left" nowrap>操作提示：</td>
					<td  align="left" colspan=3>点击“重新分发”按钮，将投保意向信息状态更改为“处理中”，并将该记录分发到指定的业务领域。</td>		
				</tr>
				<tr height="10px">
					<td nowrap></td>
					<td  align="left"  colspan=3>点击“关闭”按钮，关闭当前对话框。</td>

				</tr>
			</table>
   		</td>
   	</tr>
</c:if>
   	
   	<tr height="80">
   		<td  colspan="6" align="center">
	   		<table  align="center">			
				<!-- 如果查询处理中过来的，才可以做处理、回退、放弃操作
				非处理中查询过来的，如果是回退可以做重新分发-->
				<tr>
				
				<c:choose>
				<c:when test="${'workflow' == type && (geProductMain.productStatus=='03' || geProductMain.productStatus=='04') }">
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="changeValue(5);" nowrap>回退</td>
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="changeValue(2);" nowrap>放弃处理</td>
						
						<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="window.close();" nowrap>关闭</td>
				</c:when>
				<c:otherwise>
					<c:if test="${geProposalIntention.status =='06' }">
							<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'" onclick="changeValue(3);" nowrap>重新分发</td>
					</c:if>
					
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" onmouseout="this.className='btnBigOnBlur'"  onclick="window.close();" nowrap>关闭</td>
				</c:otherwise>
				</c:choose>
				
				
				</tr>
			</table>
   		</td>
   	</tr>
	</table>
</div>
</body>
<script type="text/javascript">
var suggestion =new tt.Field("放弃处理时，处理意见","","suggestion").setMsgId("suggestion_msg");
//非空验证
tt.vf.req.add(suggestion);

function changeValue(op){
	document.getElementById("in_type").value=op;
	// 放弃必须填写原因
	if(op==2){
		if(tt.validate()){
			document.sub.submit();
		}
	}else if(op==3){
		var buss;
		if($("input:checked").val()==2){
			buss = "寿险";
		}else if($("input:checked").val()==3){
			buss = "财险";
		}else{
			buss = "养老险"
		}
		if(confirm('您确定将该任务重新分发到--'+buss+'领域?')){
			document.sub.submit();
		}
	}else{
		document.sub.submit();
	}
}

</script>
</html>