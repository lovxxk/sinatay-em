<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/business/cmpProductManage/productDirectory/directory/recommendProduct/css/tableBorder.css" rel="stylesheet" type="text/css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
	<center>
		<div>
			<table width="100%" cellspacing="0" id="recommendProductTable">
				<tr>
					<td class="td_border_head" colspan="6" style="height: 28px">
						<span style="text-align:center;"><font size="5" ><b>关联的附加险</b></font></span>
					</td>
				</tr>
				<tr>
					<td class="td_border_head_top_none" width="30" nowrap  style="height: 28px">
						<input id="recommendProductTableCheckAll" type="checkbox" onclick="checkAll(this)">
					</td>
					<td class="td_border_head_left_top_none" nowrap>险种代码</td>
					<td class="td_border_head_left_top_none" nowrap>附加险代码</td>
					<td class="td_border_head_left_top_none" nowrap>附加险名称</td>
				</tr>
				<c:forEach items="${kinds}" var="kinds" begin="0" step="1" varStatus="status">
					<tr id="tr_${kinds.id.kindCode}" class="${status.index%2 == 0?'':'search_tr_ou'}">
						<td class="td_border_body_top_none" nowrap>
							<input type="checkbox" name="checkChild" checked="checked" id="${kinds.id.kindCode}" onclick="checkSingleRow();" value="${kinds.id.riskCode}@${kinds.id.kindCode}@${kinds.kindCName}">
						</td>
						<td class="td_border_body_left_top_none" nowrap style='height: 30px'>
						${kinds.id.riskCode}
						</td>
						<td class="td_border_body_left_top_none" nowrap>
						${kinds.id.kindCode}&nbsp;
						</td>
						<td class="td_border_body_left_top_none" width="200px" nowrap>
						${kinds.kindCName}
						</td>
					</tr>
				</c:forEach>
				
				
			</table>
		</div>
	</center>
	<script type="text/javascript">
		function checkAll(obj) {
			var checkArray = document.getElementsByName("checkChild");
			var tr_selected;
			for (var i = 0; i < checkArray.length; i++) {
				tr_selected = document.getElementById("tr_" + checkArray[i].id);
				if (obj.checked) {
					tr_selected.className = "search_tr_selected";
				} else {
					if(i%2 == 0){
						tr_selected.className = "";
					}else{
						tr_selected.className = "search_tr_ou";
					}
				}
				checkArray[i].checked = obj.checked;
			}
			checkSingleRow();
		}
		
		function checkSingleRow() {
			var idStr = "";
			var count = 0;
			var checkArray = document.getElementsByName("checkChild");
			var tr_selected;
			var value_checked;
			for (var i = 0; i < checkArray.length; i++){
				tr_selected = document.getElementById("tr_" + checkArray[i].id);
				if(checkArray[i].checked){
					tr_selected.className = "search_tr_selected";
					value_checked = checkArray[i].value;
					if(idStr == ""){
						idStr = value_checked;
					}else {
						idStr = idStr + "," + value_checked;
					}
					count++;
				}else{
					if(i%2 == 0){
						tr_selected.className = "";
					}else{
						tr_selected.className = "search_tr_ou";
					}
				}
			}
			window.parent.document.getElementById("idStr").value = idStr;
			window.parent.document.getElementById("count").value = count;
		}
		//得到国购产品的值
		function getAddProductInfo(){
			var kinds = "";
			var riskCodes = "" ;
			var productNames ="";
			
			//取值
			var checkChilds =  document.getElementsByName("checkChild");
			if(checkChilds.length>0){
				
				for(var i=0;i<checkChilds.length;i++){
					var checkboxValues =  checkChilds[i].value.split("@");
					kinds = kinds+checkboxValues[1]+",";
					riskCodes = riskCodes+checkboxValues[0]+",";
					productNames = productNames+checkboxValues[2]+",";
				}
				
				kinds = kinds.substring(0,kinds.length-1);
				productNames = productNames.substring(0,productNames.length-1);
				window.parent.document.getElementById("kinds").value = kinds;
				window.parent.document.getElementById("productNames").value = productNames;
				window.parent.document.getElementById("riskCodes").value = riskCodes;
			}else{
				alert("请选择要添加的产品");
			}
		} 
	</script>
</body>
</html>