<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
	<link href="${ctx}/business/cmpProductManage/productDirectory/directory/recommendProduct/css/tableBorder.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/global/css/misBasic.css" rel="stylesheet" type="text/css">
</head>
<body topmargin="2" leftmargin="2">
	<table width="100%" class="table_Show" id="noRecommendProductTable">
	<tr>
		<td class="td_border_head" colspan="6">
			<span style="text-align:center;"><font size="5" ><b>附加险信息</b></font></span>
		</td>
	</tr>
	<tr>
		<td class="search_head" width="30" nowrap><input id="noRecommendProductTableCheckAll" type="checkbox" onclick="checkAll(this)"></td>
		<td class="search_head" nowrap>险种代码</td>
		<td class="search_head" nowrap>附加险代码</td>
		<td class="search_head" nowrap>附加险名称</td>
	</tr>

		<c:forEach items="${page.result}" var="geKind" begin="0" step="1" varStatus="status">
			<tr id="tr_${geKind.id.kindCode}" class="${status.index%2 == 0?'':'search_tr_ou'}">
				<td class="search_body_center" nowrap>
					<input type="Checkbox" name="checkChild"  onclick="checkSingleRow('${status.index}');"  id="${geKind.id.kindCode}" value="${geKind.id.riskCode}@${geKind.id.kindCode}@${geKind.kindCName}">
				</td>
				<td class="search_body_center" nowrap>${geKind.id.riskCode}</td>
				<td class="search_body_center" nowrap>${geKind.id.kindCode}</td>
				<td class="search_body_center" nowrap>${geKind.kindCName}</td>
			</tr>
		</c:forEach>	 
</table>
</body>
<script type="text/javascript">
function checkAll(obj){
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

function checkSingleRow(){
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
</script>
</html>
