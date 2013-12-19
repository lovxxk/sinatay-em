
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
	var netSaleProductType = document.getElementById("netSaleProductType");
	netSaleProductTypeChange(netSaleProductType);
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
		parentAreaHtml = parentAreaHtml+"<input type='text' id='authorityDepartmentManager' name='saleDeptSelect' value='"+deptName+"' />";//地区输入域
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
	var type = $("#netSaleProductType").val();
	if(type == "01"){//车 查geRisk表中再查产品目录表  
		$("#recommendProductQueryForm").attr("action", contextRootPath+"/marketing/findGeRisk.do");
		$("#recommendProductQueryForm").submit();
	} else if (type == "02"){//非车  
		$("#recommendProductQueryForm").attr("action", contextRootPath+"/marketing/findGeProductMain.do");
		$("#recommendProductQueryForm").submit();
	} else {//卡
		$("#recommendProductQueryForm").attr("action", contextRootPath+"/marketing/findGeCardProduct.do");
		$("#recommendProductQueryForm").submit();
	}
}
//已增加的产品
function recommendProductSrc(){
	var recommendProduct = document.getElementById("recommendProduct");
	recommendProduct.src = contextRootPath+"/marketing/findGeActivitiesProduct.do?eids="+eids;
}
//清空
function doClean(){
	var isParentFlag = $("#isParentFlag").val();
	if(isParentFlag=="yes"){
		$(':input')   
		 .not(':button, :submit, :reset, :hidden')   
		 .val('')   
		 .removeAttr('checked')   
		 .removeAttr('selected');
	}else{
		var authorityDepartmentManager = $("#authorityDepartmentManager").val();
		var authorityid  = $("#authorityid").val();
		$(':input')   
		 .not(':button, :submit, :reset, :hidden')   
		 .val('')   
		 .removeAttr('checked')   
		 .removeAttr('selected');
		$("#authorityDepartmentManager").attr("value",authorityDepartmentManager);
		$("#authorityid").attr("value",authorityid);
	}
	
}

//增加方法
function addRecommendProduct(){
	getId(document.getElementById("noRecommendProduct"));//给全局赋值
	if (count > 0) {
		
		
		var noRecommendProductTrIdStrs = idStr.split(",");
		var trId = "";
		for (var i = 0; i < noRecommendProductTrIdStrs.length; i++) {
			var trs =  noRecommendProductTrIdStrs[i].split("@");
			var eid = trs[0];
			var coreProductCode = trs[1];
			var productName = trs[2];
			var selectType = getSelectType();
			
			if(!containt(eid,'recommendProduct')){//如果不包含则往右侧添加
				var appendHTML = "";
				appendHTML = appendHTML+"<tr id='tr_" + eid + "' class='search_tr_selected' >";
				appendHTML = appendHTML+"<td class='td_border_body_top_none' nowrap>";
				appendHTML = appendHTML+"<input type='checkbox' name='checkChild' checked='checked' id='"+eid+"' onclick='checkSingleRow();' value='"+eid+"@"+coreProductCode+"@"+productName+"@"+selectType+"'/>";
				appendHTML = appendHTML+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' nowrap style='height: 28px'>"+eid+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' nowrap>"+coreProductCode+"</td>";
				appendHTML = appendHTML+"<td class='td_border_body_left_top_none' width='200px' nowrap>"+productName+"</td>";
				appendHTML = appendHTML+"</tr>";
				$("#recommendProduct").contents().find("#recommendProductTable").append(appendHTML);
				$("#noRecommendProduct").contents().find("#tr_"+eid).remove();//将左侧的那行删除掉//删除左面的
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
			var eid = trs[0];
			var coreProductCode = trs[1];
			var productName = trs[2];
			var reSelectType = trs[3];
			var noSelectType = getSelectType();
			
			//移除
			$("#recommendProduct").contents().find("#tr_"+eid).remove();
			if(reSelectType==noSelectType){//将右侧中的元素移到左侧
				if(!containt(eid,'noRecommendProduct')){//如果左侧中不包含该元素
					var appendHTML = "";
					appendHTML = appendHTML+"<tr id='tr_"+eid+"' class='search_tr_selected' >";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>";
					appendHTML = appendHTML+"<input type='Checkbox' name='checkChild' id='"+eid+"' checked='checked' onclick='checkSingleRow();' value='"+eid+"@"+coreProductCode+"@"+productName+"'>";
					appendHTML = appendHTML+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+eid+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+coreProductCode+"</td>";
					appendHTML = appendHTML+"<td class='search_body_center' nowrap>"+productName+"</td>";
					appendHTML = appendHTML+"</tr>";
					$("#noRecommendProduct").contents().find("#noRecommendProductTable").append(appendHTML);
				}
				
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

//获得类型
function getSelectType(){
	var selectType =$("#netSaleProductType").val();
	if(selectType=="01"){//车
		return "Car";
	}else if(selectType=="02"){//非车
		return "ProductMain";
	}else{
		return "Card";
	}
}




//添加 按钮方法
function saveRecommendProduct(){
	(function($){
	    $.getUrlParam = function(name)
	    {
	        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if (r!=null) return unescape(r[2]); return null; 
	    }
	})(jQuery);
	var obj = document.getElementById("recommendProduct");
		obj.contentWindow.getAddProductInfo();
		var eids = document.getElementById("eids").value.split(",");
		var productNames = document.getElementById("productNames").value.split(",");
		//往上一个页面回写
		
		if($.getUrlParam('operation')!=null&&$.getUrlParam('operation')!=undefined&&$.getUrlParam('operation')==1){
			var xRule = $.getUrlParam('xRule');
			var yAddShopping = $.getUrlParam('yAddShopping');
			var TRObj = top.opener.document.getElementById(xRule);
			var productCallBack = window.opener.document.getElementById("addshoppingProductCallBack"+xRule+""+yAddShopping);
			var appendHtml = "";
			for(var i=0;i<eids.length;i++){
				if(eids[i]!=""&&eids[i]!=undefined&&productNames[i]!=""&&productNames[i]!=undefined){
						appendHtml = appendHtml+"<tr  class='addResetNode' id ='addResetNode"+i+"'>";
						appendHtml = appendHtml+"<td>"+(i+1)+".</td>";
						appendHtml = appendHtml+"<td name='addShoppingProduct'>"+productNames[i]+"<input type='hidden' value='"+eids[i]+"' typeProduct='addShoppingProductCode' /></td>";
						appendHtml = appendHtml+"</tr>";
				}
			}
			$(productCallBack).html(appendHtml);
			window.close();
		}else{
			var discount = $.getUrlParam('discount');
			var productCallBack = window.opener.document.getElementById("productCallBack");
			var productCallBacktwo = window.opener.document.getElementById("productCallBacktwo");
		
			var showInfo = window.opener.document.getElementById("showInfo");
			var appendHtml = "";
			var appendHtm2 = "";
			for(var i=0;i<eids.length;i++){
				if(eids[i]!=""&&eids[i]!=undefined&&productNames[i]!=""&&productNames[i]!=undefined){
					if(i<=9){
						appendHtml = appendHtml+"<tr  class='resetNode' id ='resetNode"+i+"'>";
						appendHtml = appendHtml+"<td>"+(i+1)+".</td>";
						appendHtml = appendHtml+"<td>"+"<input type='hidden'  name='coreProductCode' value='"+eids[i]+"'>"+"</td>";
						if(discount=="4"){
							appendHtml = appendHtml+"<td>"+productNames[i]+"<input type='hidden' value='"+eids[i]+"' name='geAddServiceActivity.geActivitiesProducts["+i+"].eid'/></td>";
							appendHtml = appendHtml+"<td align='right' class='clazz'><span style=\"font-weight: bold;padding-left: 200px\"> 折扣ID(选填)：</span><input  class=\"activityPatternDiscountID\" id=\"activityDiscountID"+i+"\" name=\"geAddServiceActivity.geActivitiesProducts["+i+"].discountID\" style=\"width: 170px;\" onkeyup=\"value=value.replace(/[\\\W]/g,'')\" type=\"text\" maxLength=\"100\"/></td>";
						}else{
							appendHtml = appendHtml+"<td>"+productNames[i]+"<input type='hidden' value='"+eids[i]+"' name='geAddServiceActivity.geActivitiesProducts["+i+"].eid'/></td>";
						}
						appendHtml = appendHtml+"</tr>";
					} else {
						showInfo.style.display = "";
						appendHtm2 = appendHtm2+"<tr  class='resetNode' id ='resetNode"+i+"'>";
						appendHtm2 = appendHtm2+"<td>"+(i+1)+".</td>";
						if(discount=="4"){
							appendHtm2 = appendHtm2+"<td>"+productNames[i]+"<input type='hidden' value='"+eids[i]+"' name='geAddServiceActivity.geActivitiesProducts["+i+"].eid'/></td>";
							appendHtm2 = appendHtm2+"<td align='right' class='clazz'><span style=\"font-weight: bold;padding-left: 190px\"> 折扣ID(选填)：</span><input class=\"activityPatternDiscountID\" id=\"activityDiscountID"+i+"\" name=\"geAddServiceActivity.geActivitiesProducts["+i+"].discountID\" style=\"width: 170px;\" onkeyup=\"value=value.replace(/[\\\W]/g,'')\" type=\"text\" maxLength=\"100\"/></td>";
						}else{
							appendHtm2 = appendHtm2+"<td>"+productNames[i]+"<input type='hidden' value='"+eids[i]+"' name='geAddServiceActivity.geActivitiesProducts["+i+"].eid'/></td>";
						}
						appendHtm2 = appendHtm2+"</tr>";
					}
				}
			}
			$(productCallBack).html(appendHtml);
			$(productCallBacktwo).html(appendHtm2);
			window.close();
		}
}

//右侧是否包含左面的eid
function containt(eid,iframeType){
	if(iframeType=="recommendProduct"){
		
		var value = $("#recommendProduct").contents().find("#tr_"+eid);
		if(value.length>0){
			$("#noRecommendProduct").contents().find("#tr_"+eid).remove();//将左侧的那行删除掉//删除左面的
			$("#recommendProduct").contents().find("#"+eid).attr("checked",true);//并且若右侧未选中,则将右侧选中
			return true;
		}else{
			return false;
		}
		
	}else{
		
		var value = $("#noRecommendProduct").contents().find("#tr_"+eid);
		if(value.length>0){
			$("#recommendProduct").contents().find("#tr_"+eid).remove();//
			$("#noRecommendProduct").contents().find("#"+eid).attr("checked",true);//并且若右侧未选中,则将右侧选中
			return true;
		}else{
			return false;
		}
		
	}
	
}

