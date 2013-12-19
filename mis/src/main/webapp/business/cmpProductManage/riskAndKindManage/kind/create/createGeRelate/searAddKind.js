
//全局
var idStr = "";
var count = 0;

//给全局赋值
function getId(obj) {
	try{
		obj.contentWindow.checkSingleRow();
		idStr = document.getElementById("idStr").value;
		count = document.getElementById("count").value;
		return true;
	} catch (e) {
		alert("请选择要操作的记录！");
		return false;
	}
}

//初始化页面数据
function init(){
//	var netSaleProductType = document.getElementById("netSaleProductType");
//	netSaleProductTypeChange(netSaleProductType);
	doSearch();//提交表单
 	recommendProductSrc();//已增加的产品
	$("#open_titleDIV").css("width",  document.body.scrollWidth);//宽度自适应
}
//根据下拉列表来显示表格的显示
function netSaleProductTypeChange(obj){
	var type = $(obj).val();
	if(type == "01"){//车
		$("#trtd13").html("险种代码：");//第1行  第3列 
		$("#trtd14").html("<input type='text' id='riskCode' name='riskCode' />");//第1行  第4列 
		$("#trtd21").html("险种名称：");//第2行 第1列
		$("#trtd22").html("<input id='riskCName' name='riskCName'/>");//第2行  第2列
		$("#trtd23").hide();//第2行  第3列
		$("#trtd24").hide();//第2行  第4列
	}else if(type == "02"){//非车
		$("#trtd13").html("产品代码：");//第1行  第3列 
		$("#trtd14").html("<input type='text' id='coreProductCode' name='coreProductCode' />");//第1行  第4列 
		$("#trtd21").html("产品名称：");//第2行 第1列
		$("#trtd22").html("<input id='coreProductSimpleName' name='coreProductSimpleName'/>");//第2行  第2列
		$("#trtd23").show();
		$("#trtd23").html("地<span style=\"letter-spacing:20px\">&nbsp;</span>区：");//第2行  第3列
		$("#trtd24").show();
		setAreaInnerHtml("ProductMain");//设置地区的HTML
	}else{//卡
		$("#trtd13").html("卡产品名称：");//第1行  第3列 
		$("#trtd14").html("<input name='cardProductName' type='text'/>");//第1行  第4列
		$("#trtd21").html("地<span style=\"letter-spacing:20px\">&nbsp;</span>区：");//第2行 第1列
		$("#trtd22").html("");//第2行  第2列
		$("#trtd23").hide();
		$("#trtd23").html("");//第2行  第3列
		$("#trtd24").hide();
		$("#trtd24").html("");
		setAreaInnerHtml("Card");//设置地区的HTML
	}
}

//设置地区的HTML
function setAreaInnerHtml(selectType){
	var isParentFlag = $("#isParentFlag").val();
	var parentAreaHtml = "";
	if(isParentFlag=="yes"){//父结点
		parentAreaHtml = parentAreaHtml+"<input type='text' id='authorityDepartmentManager' name='saleDeptSelect' />";  //地区输入域
		parentAreaHtml = parentAreaHtml+"<input type='hidden' id='authorityid' name='saleDeptSelectCode' />";//地区ID
		
		parentAreaHtml = parentAreaHtml+"<span  id='buttonId'>";//请选择按钮 start
		parentAreaHtml = parentAreaHtml+"<input type='button' value='请选择' onclick='alertTree();' >";
		parentAreaHtml = parentAreaHtml+"</span>";
	}else{//子结点
		var deptName = $("#deptName").val();
		var deptId = document.getElementsByName("saleDept").value;
		parentAreaHtml = parentAreaHtml+"<input type='text' id='authorityDepartmentManager' name='saleDeptSelect' value='"+deptName+"'/>";//地区输入域
		parentAreaHtml = parentAreaHtml+"<input type='hidden' id='authorityid' name='saleDeptSelectCode' value='"+deptId+"'/> ";
	}
	
	if(selectType=="ProductMain"){
		$("#trtd24").html(parentAreaHtml);
	}else{
		$("#trtd22").html(parentAreaHtml);
	}
	
}

//弹出一棵树
function alertTree(){
	var parentFilterId = document.getElementsByName("saleDept")[0].value; 
	window.open(contextRootPath+'/deptAuthority/DeptAuthIdQuery.do?&authorityid=ROLE_B_AAGA&type=2&parentFilterId='+parentFilterId,'机构' ,'top=100, left=500, width=400,height=500,toolbar=no');
}

//提交
function doSearch(){
		$("#recommendProductQueryForm").submit();
}
//已增加的产品
function recommendProductSrc(){
	var recommendProduct = document.getElementById("recommendProduct");
	recommendProduct.src = contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/create/createGeRelate/addKindUse.jsp";
//	recommendProduct.src = contextRootPath+"/business/cmpProductManage/riskAndKindManage/recommendKind.do?riskV=&idsValue=";
}
//清空
function doClean(){
	$("#cleanKindCode").attr("value","");
}

//增加方法
function addRecommendProduct(){
	getId(document.getElementById("noRecommendProduct"));//给全局赋值
	if (count > 0) {
		var noRecommendProductTrIdStrs = idStr.split(",");
		var trId = "";
		for (var i = 0; i < noRecommendProductTrIdStrs.length; i++) {
			var trs =  noRecommendProductTrIdStrs[i].split("@");
			var riskCode = trs[0];
			var kindCode = trs[1];
			var kindCName = trs[2];
			if(!containt(kindCode,'recommendProduct')){//如果不包含则往右侧添加
				var appendHTML = "";
				appendHTML = appendHTML+"<tr id='tr_" + kindCode + "' class='search_tr_selected' >";
				appendHTML = appendHTML+"<td class='td_border_body_top_none' nowrap>";
				appendHTML = appendHTML+"<input type='checkbox' name='checkChild' checked='checked' id='"+kindCode+"' onclick='checkSingleRow();' value='"+riskCode+"@"+kindCode+"@"+kindCName+"'/>";
				appendHTML = appendHTML+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' nowrap style='height: 28px'>"+riskCode+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' nowrap>"+kindCode+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' width='200px' nowrap>"+kindCName+"</td>";
				appendHTML = appendHTML+"</tr>";
				$("#recommendProduct").contents().find("#recommendProductTable").append(appendHTML);
				$("#noRecommendProduct").contents().find("#tr_"+kindCode).remove();//将左侧的那行删除掉//删除左面的
			}
		}
		
		//checkbox全选的操作
		if ($("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked") == "checked") {
			$("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked",false);
			$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",true);
		} 
		
	} else {
		alert("请选择要操作的记录！");
	}
}

//移除
function removeRecommendProduct(){
	getId(document.getElementById("recommendProduct"));
	if (count > 0) {
		var recommendProductTrIdStrs = idStr.split(",");
		var trId = "";
		for (var i = 0; i < recommendProductTrIdStrs.length; i++) {
			var trs =  recommendProductTrIdStrs[i].split("@");
			var riskCode = trs[0];
			var kindCode = trs[1];
			var kindCName = trs[2];
			
			//移除
			$("#recommendProduct").contents().find("#tr_"+kindCode).remove();
				if(!containt(kindCode,'noRecommendProduct')){//如果左侧中不包含该元素
					var appendHTML = "";
					appendHTML = appendHTML+"<tr id='tr_"+kindCode+"' class='search_tr_selected' >";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>";
					appendHTML = appendHTML+"<input type='Checkbox' name='checkChild' id='"+kindCode+"' checked='checked' onclick='checkSingleRow();' value='"+riskCode+"@"+kindCode+"@"+kindCName+"'>";
					appendHTML = appendHTML+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+riskCode+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+kindCode+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+kindCName+"</td>";
					appendHTML = appendHTML+"</tr>";
					$("#noRecommendProduct").contents().find("#noRecommendProductTable").append(appendHTML);
				}
		}
		
		if ($("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked") == "checked") {
			$("#recommendProduct").contents().find("#recommendProductTableCheckAll").attr("checked",false);
			$("#noRecommendProduct").contents().find("#noRecommendProductTableCheckAll").attr("checked",true);
		} 
		
	} else {
		alert("请选择要操作的记录！");
	}
}



//添加 按钮方法     提交
function saveRecommendProduct(){

    	var obj = document.getElementById("recommendProduct");
	    obj.contentWindow.getAddProductInfo();
		var kinds = document.getElementById("kinds").value.split(",");
	//	var riskCodes = document.getElementById("riskCodes").value.split(",");
	//	var mainCodes = document.getElementById("mainCodes").value.split(",");
		var productNames = document.getElementById("productNames").value.split(",");
		//往上一个页面回写
			var productCallBack = window.opener.document.getElementById("productCallBack");
			var productCallBacktwo = window.opener.document.getElementById("productCallBacktwo");
		
			var showInfo = window.opener.document.getElementById("showInfo");
			var appendHtml = "";
			var appendHtm2 = "";
			for(var i=0;i<kinds.length;i++){
				if(kinds[i]!=""&&kinds[i]!=undefined&&productNames[i]!=""&&productNames[i]!=undefined){
					if(i<=9){
						appendHtml = appendHtml+"<tr  class='resetNode'>";
						appendHtml = appendHtml+"<td>"+(i+1)+".</td>";
						appendHtml = appendHtml+"<td>"+"<input type='hidden'  name='coreProductCode' value='"+kinds[i]+"'>"+"</td>";
						appendHtml = appendHtml+"<td>"+productNames[i]+"<input type='hidden' value='"+kinds[i]+"' name='geKind.geKindRelateList["+i+"].id.relateKindCode'/></td>";
						appendHtml = appendHtml+"</tr>";
					} else {
						showInfo.style.display = "";
						appendHtm2 = appendHtm2+"<tr  class='resetNode' >";
						appendHtm2 = appendHtm2+"<td>"+(i+1)+".</td>";
						appendHtm2 = appendHtm2+"<td>"+productNames[i]+"<input type='hidden' value='"+kinds[i]+"' name='geKind.geKindRelateList["+i+"].id.relateKindCode'/></td>";
						appendHtm2 = appendHtm2+"</tr>";
					}
				}
			}
			$(productCallBack).html(appendHtml);
			$(productCallBacktwo).html(appendHtm2);
			window.close();
		
}

//右侧是否包含左面的eid
function containt(kindCode,iframeType){
	if(iframeType=="recommendProduct"){
		
		var value = $("#recommendProduct").contents().find("#tr_"+kindCode);
		if(value.length>0){
			$("#noRecommendProduct").contents().find("#tr_"+kindCode).remove();//将左侧的那行删除掉//删除左面的
			$("#recommendProduct").contents().find("#"+kindCode).attr("checked",true);//并且若右侧未选中,则将右侧选中
			return true;
		}else{
			return false;
		}
		
	}else{
		
		var value = $("#noRecommendProduct").contents().find("#tr_"+kindCode);
		if(value.length>0){
			$("#recommendProduct").contents().find("#tr_"+kindCode).remove();//
			$("#noRecommendProduct").contents().find("#"+kindCode).attr("checked",true);//并且若右侧未选中,则将右侧选中
			return true;
		}else{
			return false;
		}
		
	}
	
}

//展示更多产品信息
function showInfo(){
	var productInfo = document.getElementById("productInfo");
	var showText  = document.getElementById("showText");
	var showPicture = document.getElementById("showPicture");
	var hideText  = document.getElementById("hideText");
	var hidePicture  = document.getElementById("hidePicture");
	showText.style.display = "none";
	showPicture.style.display = "none";
	hideText.style.display ="";
	hidePicture.style.display ="";
	productInfo.style.display = "";
	
}
//收起更多产品信息
function hideInfo(){
	var productInfo = document.getElementById("productInfo");
	var hideText  = document.getElementById("hideText");
	var hidePicture  = document.getElementById("hidePicture");
	var showText  = document.getElementById("showText");
	var showPicture = document.getElementById("showPicture");
	productInfo.style.display = "none";
	hideText.style.display ="none";
	hidePicture.style.display ="none";
	showText.style.display = "";
	showPicture.style.display = "";
}

