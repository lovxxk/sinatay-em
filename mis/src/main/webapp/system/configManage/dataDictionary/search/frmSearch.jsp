<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<!-- 提示框开始 -->
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/global/js/lib/thickboxS.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/thickboxS.css" />
	<script type="text/javascript" src="${ctx}/global/js/jquery.qtip-1.0.0-rc3.js"></script>
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("pageNo").value = 1;
			document.getElementById("frmInput").submit();
		};
	</script>
	<title>电子商务管理系统-数据字典查询</title>
</head>
<body topmargin="0" leftmargin="0">
	<div class="public_fun_title">数据字典维护</div>
	<div class="table_content">
		<form id="frmInput" name="frmInput" method="post" action="${ctx}/business/businessManage/dataDictionary/queryGeCodeTypePageForListSon.do" target="fraSearchList">
			<table align="center" width="500px">
				<tr>
					<td class="td_head" width="80px" nowrap>
						代码类型：
					</td>
					<td class="td_body">
						<input type="text" style="width:170px" id="codeType" name="geCodeType.codeType" maxlength="25">
					</td>
					<td class="td_head" width="80px" nowrap>
						简体描述：
					</td>
					<td class="td_body">
						<input type="text" style="width:170px" id="codeTypeCDesc" name="geCodeType.codeTypeCDesc" maxlength="25">
					</td>
					
				</tr>
				<tr>
					<td class="td_head" width="80px" nowrap>
						业务领域：
					</td>
					<td class="td_body">
						<select id="businessArea" name="geCodeType.businessArea"  style="width:170px;" >
				<option value="">--全部--</option>
				<c:forEach items="${bussList}" var="bussArea" step="1" varStatus="status">
					<option value="${bussArea.id.codeCode}">${bussArea.codeCName}</option>
				</c:forEach>
			</select>
					</td>
					<td class="td_head" width="80px"  nowrap>
						是否有效：
					</td>
					<td class="td_body">
						<select id="status" name="geCodeType.validInd"  style="width:170px;" >
							<option value="">全部</option>
							<option value="0">无效</option>
							<option value="1">有效</option>
						</select>
					</td>
				</tr>
				<tr height="60px">
					<td colspan="4" align="center">
						<table>
							<tr>
								<td nowrap>
									<input type="hidden" name="pageNo" id="pageNo" value="1">
									<input type="hidden" name="pageSize" id="pageSize" value="20">
								</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:doSearch();" nowrap>查询</td>
								<td class="btnBigOnBlur"  onmouseover="this.className='btnBigOnFocus'" 
									onmouseout="this.className='btnBigOnBlur'"  onclick="javascript:doClear();" nowrap>清空</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//pop提示信息
	var ids = ['codeCode'];
	var contents = ['数据字典码值，该码值类型不与其它码值重复'];
		
    	for ( var i = 0 ; i < 1 ; i++ ){
			$('#' + ids[i]).qtip({ 
				content:contents[i], 
				position: { 
					corner: { 
					tooltip: 'leftMiddle', 
					target: 'rightMiddle'
					} 
				}, 
				 style: { 
				border: { 
					width: 2,
					radius: 2,
					color: '#00739f'
					},
					width:100,
					padding: 10, 
					textAlign: 'left',
					background: '#e0f2ff', 
					tip:true//控制左侧尖角是否出现
					//name: 'green' 
				} 
			}); 
    	}
    	$(".public_fun_title").html('<span  style="font-size: 15px;">'+$(".public_fun_title").html()+'<img id="des" src="/mis/global/images/help.png" border="0" style="cursor: pointer;vertical-align:middle;"/></span>');
    	var ids1 = ['des'];
    	var contents1 = ['说明：维护某数据字典类型下的字典数据<br/>使用人群：数据字典维护人员<br/>配置条件：待创建的数据字典的数据字典类型必须是已有的数据字典类型<br/>注意事项：列表显示的是数据字典类型信息列表，点击链接弹出新页面显示该类型下的数据字典列表，显示序号是同类型数据字典列表展示时的排序字段'];
    		
        	for ( var i = 0 ; i < ids1.length ; i++ ){
    			$('#' + ids1[i]).qtip({ 
    				content:contents1[i], 
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
    							width:450,
    							textAlign: 'left',
    							background: '#e0f2ff', 
    							tip:true,//控制左侧尖角是否出现
    							padding :10
    						}
    					});
    			
        	}
});
	function doSearch(){
		window.parent.fraToolbar.document.getElementById("idStr").value = "";
		window.parent.fraToolbar.document.getElementById("count").value = "";
		document.getElementById("pageNo").value = 1;
		document.getElementById("frmInput").submit();
	}
	
	function doClear(){
		document.getElementById("frmInput").reset();
	}
</script>
</html>
