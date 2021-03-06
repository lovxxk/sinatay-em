<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>
<%@ taglib prefix="acc" uri="http://com.sinosoft.security/accessControll" %>
<%
	request.setCharacterEncoding("GBK");
	response.setHeader("Cache-Control", "No-Cache");//HTTP 1.1
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link type="text/css" rel="stylesheet" href="${ctx}/global/css/misBasic.css">
	<script src="${ctx}/global/js/jquery-1.6.2.min.js"></script>
</HEAD>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<td onclick="javascript:maximizeGrid(this);">
					&nbsp;
				</td>
				<td onclick="getProduct();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">关联加购产品</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="idStr" id="idStr">
	<input type="hidden" name="count" id="count">
	<input type="hidden" name="status" id="status">
</body>
<script language="javascript">
	//初始化toolbar样式
	var toolbarTable = document.getElementById("toolbar_main");
	var toolbarRow = toolbarTable.rows[0];
	toolbarRow.className = "toolbar_bg";
	var tds = toolbarRow.cells;
	for(var i = 0; i < tds.length; i++){
		if(i == 0){
			tds[i].className = "toolbar_up";
			tds[i].title = "扩大数据表";
		}else if(i % 2 == 0){
			tds[i].className = "toolbar_gap";
		}else{
			tds[i].className = "toolbar_normal";
		}
	}
	//改变toolbar样式
	function changeTD_style(obj){
		if(obj.className == "toolbar_normal"){
			obj.className = "toolbar_hover";
		}else{
			obj.className = "toolbar_normal";
		}
	}
	
	//公用区域start
	var defaultRows = top.document.getElementById("fraTop").rows;
	var idStr = "";
	var count = 0;
	var printID = "";
	var emailID = "";
	var receiver = "";
	var flag = "";
	var status = "";
	function maximizeGrid(obj){
		var className = obj.className;
		if(className == "toolbar_up"){//上移
			obj.className = "toolbar_down";
			top.frames[1].frames[2].document.getElementById("fraTop").rows='0,30,*,40,0';
			obj.title = "还原数据表";
		}else{//下移
			obj.className = "toolbar_up";
			top.frames[1].frames[2].document.getElementById("fraTop").rows=defaultRows;
			obj.title = "扩大数据表";
		}
	}
	
	function doSearch(){
		var fraSearchForm = top.frames[1].frames[2].frames[0];
		fraSearchForm.doSearch();
	}
	
	function getId(){
		try{
			top.frames[2].window.checkSingleRow();
			idStr = document.getElementById("idStr").value;
			count = document.getElementById("count").value;
			status = document.getElementById("status").value;
			return true;
		}catch (e) {
			alert("请选择要操作的记录！");
			return false;
		}
	}
	var top_ = (window.screen.availHeight-750)/2;
	if(top_ < 0){
		top_ = 0;
	}
	var left_ = (window.screen.availWidth-1020)/2;
	if(left_ < 2){
		left_ = 0;
	}
	var config = "width=1020,height=750,toolbar=no,statusbar=no";
	config += ",top="+top_ +",left="+left_;
	//公用区域end
	
	//特殊处理，随功能不同而不同
	//关联到目录
	function getProduct(){
		if(!getId()){
			return false;
		}
		var valArr = idStr.split(",");
		var eid = valArr[0];
		var name = valArr[1];
		var xrule =  valArr[2];
		var yaddShopping = valArr[3];
		if(isRepeat(valArr[0],valArr[2],valArr[3])){
			var TRObj = top.opener.document.getElementById(valArr[2]);
			var countTemp=0;
			var countCode = 0;
			$(TRObj).find("input").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){//codeName
					if(countTemp==parseInt(valArr[3])){
						$(value).attr("value",valArr[1]);

					}
					countTemp++;
				}
				if($(value).attr("typeProduct")=="addShoppingProductCode"){//code
					if(countCode==parseInt(valArr[3])){
						$(value).attr("value",valArr[0]);
					}
					countCode++;
				}
			});
			top.close();
		}//end if(isRepeat(valArr[0],valArr[2],valArr[3])){
		
		/*
		getId();
		var valArr = idStr.split(",");
		if (count == 1) {
			var area = valArr[0];
			if (confirm("确定关联该产品?")) {
				if(isRepeat(valArr[0],valArr[2],valArr[3])){
					var TRObj = top.opener.document.getElementById(valArr[2]);
					var countTemp=0;
					var countCode = 0;
					$(TRObj).find("input").each(function(index,value){
						
						if($(value).attr("typeProduct")=="addShopping"){//codeName
							if(countTemp==parseInt(valArr[3])){
								$(value).attr("value",valArr[1]);

							}
							countTemp++;
						}
						if($(value).attr("typeProduct")=="addShoppingProductCode"){//code
							if(countCode==parseInt(valArr[3])){
								$(value).attr("value",valArr[0]);
							}
							countCode++;
						}
					});
					top.close();
				}//end if(isRepeat(valArr[0],valArr[2],valArr[3])){
			}
			
		} else if (count == 0) {
			alert("请选择要关联的产品！");
		} 
			*/
		
	}
	function isRepeat(eid,xRule,yaddShopping){
		var isFlag = true;
		var tableInfoObj = top.opener.document.getElementById("tableInfo");
		$(tableInfoObj).find("tr").each(function(index,value){
			if($(value).attr("id")==xRule){
				$(value).find("input").each(function(indexInput,valueInput){
					if($(valueInput).attr("typeProduct")=="addShoppingProductCode"){
						if(valueInput.value==eid){
							alert("该产品已经选 中，请重新选择");
							isFlag=false;
						}
					}
				});
			}
			
		});
		return isFlag;
	}
	//特殊处理end
</script>
</HTML>
