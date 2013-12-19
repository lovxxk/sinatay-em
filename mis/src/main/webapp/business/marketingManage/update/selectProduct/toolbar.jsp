<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/global/ui/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="${ctx}/global/css/mis_basic.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctx }/global/js/jquery-1.6.2.min.js"></script>
</head>
<body>
	<div id="toolbar_DIV">
		<table id="toolbar_main" cellpadding="0" cellspacing="0">
			<tr>
				<td onclick="javascript:maximizeGrid(this);">&nbsp;</td>
				<td onclick="getProduct();" onmouseover="changeTD_style(this);" onmouseout="changeTD_style(this);">关联到产品类型</td>
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
			top.document.getElementById("fraTop").rows='0,30,*,40,0';
			obj.title = "还原数据表";
		}else{//下移
			obj.className = "toolbar_up";
			top.document.getElementById("fraTop").rows=defaultRows;
			obj.title = "扩大数据表";
		}
	}
	
	function doSearch(){
		var fraSearchForm = top.frames[0];
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
		getId();
		var valArr = idStr.split("@");
		if (count == 1) {
			if (confirm("确定关联该产品?")) {
				cleanUpload();//清除上一次的所有upload
				var coreProductCodeOld =  top.opener.document.getElementById("coreProductCodeOld").value;
				top.opener.document.getElementById("coreProductCode").value = valArr[0];
				top.opener.document.getElementById("productName").value = valArr[1];
				if(valArr[2]!=""){
					createUploadPicture(valArr[2],valArr[0],coreProductCodeOld);//画出要上传的文本框
				}
			}
			top.close();
		} else if (count == 0) {
			alert("请选择要关联的产品！");
		} else {
			alert("只能对单条产品进行操作！");
		} 
	}
	//清除上传图片的轨迹
	function cleanUpload (){
		
		var productTable = top.opener.document.getElementById("productTable");
		$(productTable).find("tr").each(function(index, value){
			if($(value).attr("name")=="upload"||$(value).attr("name")=="showUploadPicture"){
				$(value).remove();
			}
		});
	}
	//重新写上传图片的轨迹
	function createUploadPicture(pictureCount,productCode,coreProductCode){
		if(pictureCount!=""&&parseInt(pictureCount)>0){
			var htmlUpload = "";
			//			
			if(productCode==coreProductCode){//得把showUploadPicture 显示出来
				for(var i=0;i<parseInt(pictureCount);i++){
					//上传图片展示
					var objShow = top.opener.document.getElementById("tableDelShowHidden");
					var objShow= $(objShow);
					
					//第一列
					objShow.find("td").each(function(index, value){
						if(index==0){
							$(value).html("上传图片"+(i+1))	;
						}
						if(index==1){
							var picUrl = top.opener.document.getElementsByName("pictureUrl")[i].value;
							$(value).html("<img src='${ctx}/"+picUrl+"'/>");
						}
					});
					htmlUpload = htmlUpload+objShow.html();
					//修改上传图片
					var objTemp = top.opener.document.getElementById("tableDelHidden");
					var obj = $(objTemp);
					//第一列
					obj.find("td").each(function(index, value){
						if(index==0){
							var temp  ="修改上传图片"+(i+1);
							$(value).html(temp)	
						}
					});
					htmlUpload = htmlUpload+obj.html();
				}//end for
			}else{
				for(var i=0;i<parseInt(pictureCount);i++){
					htmlUpload = htmlUpload+createUpload(i)
				}
			}// end if(productCode==coreProductCode){
				
			$(top.opener.document.getElementById("productType")).append(htmlUpload);
		}//end if(pictureCount!=""&&parseInt(pictureCount)>0){
	}
		
	function createUpload(i){
		var objTemp = top.opener.document.getElementById("tableDelHidden");
		var obj = $(objTemp);
		//第一列
		obj.find("td").each(function(index, value){
			if(index==0){
				var temp  ="上传图片"+(i+1);
				$(value).html(temp)	
			}
		});
		return obj.html();
	}
	//特殊处理end
</script>
</html>
