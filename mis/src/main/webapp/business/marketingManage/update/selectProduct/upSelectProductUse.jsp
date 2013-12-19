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
<body onload="update();">
	<center>
		<div>
			<table width="100%" cellspacing="0" id="recommendProductTable">
				<tr>
					<td class="td_border_head" colspan="6">
						<span style="text-align:center;"><font size="5" ><b>关联的产品</b></font></span>
					</td>
				</tr>
				<tr>
					<td class="td_border_head_top_none" width="30" nowrap>
						<input id="recommendProductTableCheckAll" type="checkbox" onclick="checkAll(this)">
					</td>
					<td class="td_border_head_left_top_none" nowrap>电子商务产品ID</td>
					<td class="td_border_head_left_top_none" nowrap>产品代码/险种代码</td>
					<td class="td_border_head_left_top_none" nowrap>产品名称</td>
				</tr>
				<c:forEach items="${geDirectorys}" var="geDirectory" begin="0" step="1" varStatus="status">
					<tr id="tr_${geDirectory.eid}" class="${status.index%2 == 0?'':'search_tr_ou'}">
						<td class="td_border_body_top_none" nowrap  style="height: 30px">
							<input type="checkbox" name="checkChild" checked="checked"" id="${geDirectory.eid}" onclick="checkSingleRow();" value="${geDirectory.eid}@${geDirectory.coreProductCode}@${geDirectory.productName}@${geDirectory.extra1}">
						</td>
						<td class="td_border_body_left_top_none" nowrap>${geDirectory.eid}</td>
						<td class="td_border_body_left_top_none" nowrap>${geDirectory.coreProductCode}&nbsp;</td>
						<td class="td_border_body_left_top_none" width="200px" nowrap>${geDirectory.productName}</td>
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
		function update(){
		 var eids = "${eids}";
		 alert(eids);
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
			var eids = "";
			var productNames ="";
			
			//取值
			var checkChilds =  document.getElementsByName("checkChild");
			if(checkChilds.length>0){
				for(var i=0;i<checkChilds.length;i++){
					var checkboxValues =  checkChilds[i].value.split("@");
					eids = eids+checkboxValues[0]+",";
					productNames = productNames+checkboxValues[2]+",";
				}
				
				eids = eids.substring(0,eids.length-1);
				productNames = productNames.substring(0,productNames.length-1);
				window.parent.document.getElementById("eids").value = eids;
				window.parent.document.getElementById("productNames").value = productNames;
				return "true";
			}else{
				alert("请选择要添加的产品");
				return "false";
			}
		} 
	</script>
</body>
</html>