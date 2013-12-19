<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
<title>电子商务后台管理系统-查看险别关系信息</title>
<script type="text/javascript" src="${ctx}/global/js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/global/js/validate/css/validate.css" />
<script type="text/javascript" src="${ctx}/global/js/validate/talent-validate-all-min.js" charset="utf-8"></script>
<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body onload="pageValidate();">
<div id="open_titleDIV">
	<div class="open_title_c">
		<div class="open_title">
			查看险别关系信息
		</div>
	</div>
	<div class="header_title_gap1"></div>
</div>
<div class="table_content">
	<div style="clear: both;"></div>
	<form  action="${ctx }/business/cmpProductManage/riskAndKindManage/kindRelatef.do" id="frmInput" method="post"  target="kindIframe" >
	<table align="center" style="width:550px;line-height:25px;"  id="productTable">
	<tr >
		<td class="td_head" width="" nowrap>险种代码：</td>
		<td class="td_body">
			<input type="text" name="geKindRelate.id.riskCode" value="<s:property value="geKindRelate.id.riskCode"/>" style="width:170px;" maxlength=80 ondblclick="alertNationality();" readonly="readonly" >(双击域选择)
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>主险险别代码：</td>
		<td class="td_body" >
			<input type="text" name="geKindRelate.id.kindCode" value="<s:property value="geKindRelate.id.kindCode"/>" id="kindCodeMain" style="width:170px;" ondblclick="alertKindCodeMain();" readonly="readonly" maxlength="6">(双击域选择)
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>附加险代码：</td>
		<td class="td_body" >
			<input type="text" name="geKindRelate.id.relateKindCode" value="<s:property value="geKindRelate.id.relateKindCode"/>"  id="kindCodeAd" style="width:170px;" ondblclick="alertKindCodeAddition();" readonly="readonly" maxlength="6">(双击域选择)
		</td>
	</tr>
	<tr>
	<td class="td_head"  nowrap>业务领域：</td>
		<td class="td_body" >
		 <s:select cssStyle="width:170px" name="geKindRelate.businessArea" list="#{'':'请选择','1':'集团','2':'寿险','3':'财险','4':'养老险'}"></s:select>
		</td>
	</tr>
	<tr>
		<td class="td_head" nowrap>标识：</td>
		<td class="td_body" >
		    <input type="hidden" id="view"  value="${view}"/>
			<input type="text" name="geKindRelate.flag" value="<s:property value="geKindRelate.flag"/>" style="width:170px;" maxlength="3">
		</td>
	</tr>
	<!-- -----------------------    隐藏与显示   结束 ------------------------------------- -->
	
	<tr height=25><td></td></tr> 
	<tr>
		<td colspan=2>
			<table width=200 align="center">
			
			<tr>
			    <c:choose>
					 <c:when test="${!(view eq 'view') }">
					  <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
		                        onmouseout="this.className='btnBigOnBlur'" onclick="submitKindRelate();" nowrap>保存</td>
					  <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
		                        onmouseout="this.className='btnBigOnBlur'" onclick="doClear();" nowrap>重置</td>
		             </c:when>
		             <c:otherwise>       
			                 <acc:showView source="ROLE_RISKKINDRELAT_U">
			                   <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
			                        onmouseout="this.className='btnBigOnBlur'" onclick="doUpate('<s:property value="geKindRelate.id.riskCode"/>','<s:property value="geKindRelate.id.kindCode"/>','<s:property value="geKindRelate.id.relateKindCode"/>');" nowrap>编辑</td>
			                  </acc:showView>
			                   <acc:showView source="ROLE_RISKKINDRELAT_D">
			                    <td  class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
			                        onmouseout="this.className='btnBigOnBlur'" onclick="doDelete();" nowrap>删除</td>
			                  </acc:showView>
		               </c:otherwise>     
	               </c:choose>
					<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
					onmouseout="this.className='btnBigOnBlur'" onclick="javascript:window.close();" nowrap>关闭</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	<iframe  name="kindIframe" style="display: none"></iframe>
	</form>
</div>
</body>
<script type="text/javascript">
$(function(){
	var view = document.getElementById("view").value;
	if(view=='view'){
		   $('input[type!="button"],textarea').each(function(){
			   $(this).attr('disabled','disabled');
		   });
		   $("select").each(function(){
			   $(this).attr('disabled','disabled');
		   });
	}
	
});

function pageValidate(){
	tt.vf.req.add("geKindRelate.id.riskCode","geKindRelate.id.relateKindCode","geKindRelate.id.kindCode"); 
}
//清空输入
function doClear(){
	document.getElementById("frmInput").reset();
}
//处理险种代码双击域
function alertNationality(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/searchRiscode/index.jsp","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
//处理险别主险代码双击域
function alertKindCodeMain(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/riskKindRelate/create/searchKindCode/index.jsp?validInd=1","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}
//处理险别附加险代码双击域
function alertKindCodeAddition(){
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/riskKindRelate/create/searchKindCode/index.jsp?validInd=0","险种代码" ,"top=100, left=100, width=900,height=600,toolbar=no");
}

function doUpate(riskCode,kindCode,kindRelateCode){
	window.location.href= contextRootPath + '/business/cmpProductManage/riskAndKindManage/viewKindRelate.do?geKindRelate.id.riskCode='+riskCode+'&geKindRelate.id.kindCode='+kindCode+'&geKindRelate.id.relateKindCode='+kindRelateCode;
}

function submitKindRelate(){
	$("#frmInput").submit();
	
}
</script>
</html>
