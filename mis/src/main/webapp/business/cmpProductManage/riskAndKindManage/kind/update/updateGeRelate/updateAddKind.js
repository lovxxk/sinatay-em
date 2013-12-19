
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

//清空
function doClean(){
	$("#cleanKindCode").attr("value","");
}

//初始化页面数据
function init(){
	doSearch();//提交表单
 	recommendProductSrc();//已增加的产品
	$("#open_titleDIV").css("width",  document.body.scrollWidth);//宽度自适应
}

//提交
function doSearch(){
		$("#recommendProductQueryForm").submit();
}
//已增加的产品
function recommendProductSrc(){
	var recommendProduct = document.getElementById("recommendProduct");
	var riskV = document.getElementById("riskV").value
	var idsValue = document.getElementById("idsValue").value;
	recommendProduct.src = contextRootPath+"/business/cmpProductManage/riskAndKindManage/recommendKind.do?riskV="+riskV+"&idsValue="+idsValue;
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
						appendHtml = appendHtml+"<td width=5px>"+(i+1)+".</td>";
						appendHtml = appendHtml+"<td>"+productNames[i]+"<input type='hidden' value='"+kinds[i]+"' name='geKind.geKindRelateList["+i+"].id.relateKindCode'/></td>";
						appendHtml = appendHtml+"</tr>";
					} else {
						showInfo.style.display = "";
						appendHtm2 = appendHtm2+"<tr  class='resetNode'>";
						appendHtm2 = appendHtm2+"<td width=5px>"+(i+1)+".</td>";
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

//--------------------------- 验证结束 ------------------------------------	

function doUpdate(){
   if( tt.validate()&& checkNum()){
	    //取得关系码 
	     var kindMain = $("#kindCodeV").val();
		 var risk = $("#riskCodeV").val();
	     var KindAddCodeLength  = $("input[name$='id.relateKindCode'][type=hidden]").length;
	     var hiddenkindCodeLength  = $("input[name='hiddenkindCode'][type=hidden]").length;
	     if(hiddenkindCodeLength==0 && KindAddCodeLength>0){
	    	for(var i=0;i<KindAddCodeLength;i++){
				 $("#kindCodeV").after("<input type='hidden' value='"+kindMain+"' name='geKind.geKindRelateList["+i+"].id.kindCode'>");
				 $("#riskCodeV").after("<input type='hidden' value='"+risk+"' name='geKind.geKindRelateList["+i+"].id.riskCode'>");
			 }
	    }else if(hiddenkindCodeLength==0&&KindAddCodeLength==0){
	    	 $("#kindDiv").hide();
			 $("#buttonShow").hide();
	    }else{
	    	$("#kindDiv").show();
			$("#buttonShow").show();
	    }
	   $("div:hidden").remove();
	   $("tr:hidden").remove();
	   var codeType = $("#codeType").val();
	   if(codeType=='0'){
		   var elements=$("input[id^='du'][id!='duty']");
			 if(elements.length<1){
				 alert("对不起您必须添加一个限额 ");
				 return;
			 }else{
				 document.getElementById("frmInput").submit();
			 }
		 }else{
			 document.getElementById("frmInput").submit();
		 }
		
    }
}
function doClear(){
	//document.getElementById("frmInput").reset();
	window.location.href = window.location.href;
}
//-------------------------- 隐藏与显示  -------------------------------------

//验证数字与1000的倍数
function checkMoney(field){
if(isNaN($(field).val())){
	alert("必须输入数字");
	$(field).attr("value","");
	return false ;
}
if($(field).val()%1000!=0 ){ 
	alert("必须输入为1000的倍数");
	$(field).attr("value","");
	return false ;
}
}


function createValuerange(){
var ttvf = tt.vf.req;//非空
// ttvf.rmId('newCar','carT','duty');
// tt.vf.req.addId('newCar','carT','duty');
var other = $("input[id^='other']");
var aother = $("input[id^='aother']");

var elements=$("input[id^='du'][id!='duty']");
var codeType = $("#codeType").val();
if(codeType=='0'){
	var k = 0 ;
	$("#createInput").css("display","none");
	$("#createInputT").css("display","none");
	$("#creatOther").css("display","none");
	$("#createSelect").css("display","");
	var divT = $("#addDiv");
	var valueRangeKind = '${geKind.valuerange}';   // 取得的值
	if(valueRangeKind.indexOf("@{")<0){
		$("#addDiv").html("");
		var tr = $(" <tr> </tr>").appendTo(divT);
		var td = $(" <td class='td_head' nowrap >责任：</td> ").appendTo(tr);
		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' value='' id='duty' maxlength='10' />  </td>  ").appendTo(tr);
       	var trD = $("<tr></tr>").appendTo(divT);
		var td = $(" <td class='td_head' nowrap >责任限额：</td> ").appendTo(trD);
   		var tdText = $(" <td class='td_body'>  <input type='text'  style='font-style:oblique' maxlength='7' name='geKind.valuerange' value=''  id='dua0'   onchange='checkMoney(this);' onblur='fillTexta(0);'  />  </td>  ").appendTo(trD);
	    var td = $(" <td class='td_head' nowrap style=padding-left:50px >限额描述：</td> ").appendTo(trD);
   	  	var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' readonly='readonly' value='' id='mia0' style='font-style:oblique'  />  </td>  ").appendTo(trD);
	    var tdButtonA = $("<td> <input type='button' value='增加限额'  onclick='insertMoney();checkKind();'/> </td>").appendTo(trD);
	}
    ttvf.rmId('newCar','carT');
    tt.vf.req.addId('duty');
    if(other.length>0){
		tt.vf.req.rmId('otherDuty');
	}
	for(var j=0;j<other.length;j++){
		tt.vf.req.rmId($(other[j]).attr("id"));
	}
	for(var k=0;k<aother.length;k++){
		tt.vf.req.rmId($(aother[k]).attr("id"));
	}
    checkKind();
    
}else if(codeType=='1'){
	$("#createSelect").css("display","none");
	$("#createInputT").css("display","none");
	$("#creatOther").css("display","none");
	$("#createInput").css("display","");
    $("#newCar").val("同新车购置价");
	ttvf.rmId('duty','carT');
	tt.vf.req.addId('newCar');
	for(var i=0;i<elements.length;i++){
		ttvf.rmId($(elements[i]).attr("id"));
	}
	if(other.length>0){
		tt.vf.req.rmId('otherDuty');
	}
	for(var j=0;j<other.length;j++){
		tt.vf.req.rmId($(other[j]).attr("id"));
	}
	for(var k=0;k<aother.length;k++){
		tt.vf.req.rmId($(aother[k]).attr("id"));
	}
}else if(codeType=='2'){
	$("#createSelect").css("display","none");
	$("#createInput").css("display","none");
	$("#creatOther").css("display","none");
	$("#createInputT").css("display","");
	$("#carT").val("同车辆实际价值");
	ttvf.rmId('newCar','duty');
	tt.vf.req.addId('carT');
	for(var i=0;i<elements.length;i++){
		ttvf.rmId($(elements[i]).attr("id"));
	}
	if(other.length>0){
		tt.vf.req.rmId('otherDuty');
	}
	for(var j=0;j<other.length;j++){
		tt.vf.req.rmId($(other[j]).attr("id"));
	}
	for(var k=0;k<aother.length;k++){
		tt.vf.req.rmId($(aother[k]).attr("id"));
	}
}else if(codeType=='3'){
	$("#createInput").css("display","none");
	$("#createInputT").css("display","none");
	$("#createSelect").css("display","none");
	$("#creatOther").css("display","block");
	var divT = $("#addOther");
	var valueRangeKind = '${geKind.valuerange}';   // 取得的值
	
	if(valueRangeKind.indexOf("@{")<0 && valueRangeKind!=''){
		$("#addOther").html("");
		var tr = $(" <tr> </tr>").appendTo(divT);
		var td = $(" <td class='td_head' nowrap >责任描述：</td> ").appendTo(tr);
		var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange' value='' id='otherDuty' maxlength='10' />  </td>  ").appendTo(tr);
	    var tdButtonA = $(" <td> <input type='button' value='增加' onclick='insertOther();'/> <td>").appendTo(tr);
	}
	tt.vf.req.rmId('newCar','duty','carT');
	for(var i=0;i<elements.length;i++){
		tt.vf.req.rmId($(elements[i]).attr("id"));
	}
}else if(codeType==''){
	$("#createSelect").css("display","none");
	$("#createInput").css("display","none");
	$("#createInputT").css("display","none");
	ttvf.rmId('newCar','duty','carT');
	for(var i=0;i<elements.length;i++){
		ttvf.rmId($(elements[i]).attr("id"));
	}
	
}
}
//刪除dom
function removeMoney(field){
var ttvf = tt.vf.req;//非空
var tableObj = $(field).parent().parent();
$(tableObj).find("input[maxlength=7]").each(function(){
	id = $(this).attr("id");
	ttvf.rmId($(this).attr("id"));
});
$(tableObj).empty(); 
}
//增加 险别验证 
function checkKind(){
	var elements=$("input[id^='du'][id!='duty']");
	for(var i=0;i<elements.length;i++){
		tt.vf.req.addId($(elements[i]).attr("id"));
	}
}

//删除 验证 
function removeCheck(i){
	var ttvf = tt.vf.req;//非空
	var elements=$("input[id^='du'][id!='duty']");
	ttvf.rmId($(elements[i]).attr("id"));
}

//添加dom
var index = 0 ;
function insertMoney(){
var dex = $("input[id^='du']").length ;
if(dex<7){
	var divD = $("#addDiv");
	var trT = $("<tr></tr>").appendTo(divD);
	var td = $(" <td class='td_head' nowrap >责任限额：</td> ").appendTo(trT);
	var tdText = $(" <td class='td_body'>  <input type='text' name='geKind.valuerange'style='font-style:oblique' maxlength='7' id='duc"+ index +"' value='' onchange='checkMoney(this);' onblur='fillTextc("+index+");'  />  </td>  ").appendTo(trT);
	var td = $(" <td class='td_head' nowrap style=padding-left:50px>限额描述：</td> ").appendTo(trT);
	var tdText = $(" <td class='td_body'>  <input type='text' id='mic"+ index +"' name='geKind.valuerange' style='font-style:oblique' readonly='readonly'   value='' />  </td>  ").appendTo(trT);
	var tdButtonD =  $("<td class='td_body' > <input type='button' onclick='removeMoney(this);' value='刪除' /> </td>").appendTo(trT);
	index++ ;
}else{
	alert("最多添加6个限额");
	return false ;
}
checkKind();
}

//填充数值
function fillText(i){
	var num = $("#du"+i).val();
	var numF = num.substring(0,1);
	if(num/10000<1){
		$("#mi"+i).attr("value",num/1000+"千");
	}else if(num/10000>=1&&num/10000<10){
		$("#mi"+i).attr("value",num/10000+"万");
	}else if(num/10000>=10&&num/10000<100){
		$("#mi"+i).attr("value",num/10000+"万");
	}else if(num/10000>=100&&num/10000<1000){
		$("#mi"+i).attr("value",num/10000+"万");
	}
}

function fillTextc(i){
	var num = $("#duc"+i).val();
	var numF = num.substring(0,1);
	if(num/10000<1){
		$("#mic"+i).attr("value",num/1000+"千");
	}else if(num/10000>=1&&num/10000<10){
		$("#mic"+i).attr("value",num/10000+"万");
	}else if(num/10000>=10&&num/10000<100){
		$("#mic"+i).attr("value",num/10000+"万");
	}else if(num/10000>=100&&num/10000<1000){
		$("#mic"+i).attr("value",num/10000+"万");
	}
}
function fillTexta(i){
	var num = $("#dua"+i).val();
	var numF = num.substring(0,1);
	if(num/10000<1){
		$("#mia"+i).attr("value",num/1000+"千");
	}else if(num/10000>=1&&num/10000<10){
		$("#mia"+i).attr("value",num/10000+"万");
	}else if(num/10000>=10&&num/10000<100){
		$("#mia"+i).attr("value",num/10000+"万");
	}else if(num/10000>=100&&num/10000<1000){
		$("#mia"+i).attr("value",num/10000+"万");
	}
}

function checkNum(){
var flag = true;
  var tempCode = [];
  $("input[id^='du'][id!='duty']").each(
    function(){
    	if(tempCode[$(this).attr('value')] == $(this).attr('value')&& $(this).attr('value') !=""){
    		alert("责任限额不能重复请检查");
    		flag = false;
    		return false;
    	}else{
    		tempCode[$(this).attr('value')] = $(this).attr('value');
    	}
    }
  )
  return flag;
}



//校验序号是否重复 
function checkOrderNum(){
     var flag = false ;
	 var riskCode = document.getElementById("riskCodeV").value;
	 var orderno = document.getElementById("orderNo").value;
	 var kindFlag = document.getElementById("kindFlag").value;
	 var kindCode = document.getElementById("kindCodeV").value;
	//做隐藏域与正常值的对比 如果值相同 就不校验 
	var flagkind = document.getElementById("flagkind").value;
	var noOrder = document.getElementById("noOrder").value;
	if(kindFlag==flagkind && orderno==noOrder ){
		flag= true ;
	}else{
	   $.ajax({
	  		  type: "POST",
	  		  async:false,
	  		  url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkOrderNum.do',
	  		  dataType: 'text',
	  		  data:{"geKind.id.riskCode":riskCode,"orderNo":orderno,"geKind.kindflag":kindFlag,"geKind.id.kindCode":kindCode},
	  		  success: function(backData){
	  			  if(backData == '02'){
	  			     alert("该序号已被占用");
	  			     flag = false ;
	  			  }else{
	  				  flag = true ;
	  			  }
	  		    }
	  		});
	 }
	   return flag ;
}

/**
* 点击编辑附加险取值过程 
*/
function updateKind(){
	var riskCode = $("#riskCodeV").val();
	var idsLength = document.getElementsByName("hiddenkindCode");
	var idsValue='';
	for(var i=0;i<idsLength.length;i++){
		idsValue += document.getElementsByName("hiddenkindCode")[i].value+"-";
	}
	window.open(contextRootPath+"/business/cmpProductManage/riskAndKindManage/kind/update/updateGeRelate/searchUpdateAddKind.jsp?riskCode="+riskCode+"&idsValue="+idsValue,"编辑附加险" );
}

/**
 * 判断是否隐藏增加附加险的层
 */
function checkMain(){
	var riskCode = $("#riskCodeV").val();
	var kindCode = $("#kindCodeV").val();
	var kindFlag = $("#kindFlag").val();
	var idsLength = document.getElementsByName("hiddenkindCode").length;
	if(riskCode != '' && kindCode != '' && kindFlag == '01'){
		$("#flagRelateShow").show();	
		if(idsLength>0){
				$("#kindDiv").show();
				$("#buttonShow").show();
				$("#flagRelate").val("1");
			}else{
				$("#flagRelate").val("0");
			}
	}else{
		$("#flagRelateShow").hide();
		$("#kindDiv").hide();
		$("#buttonShow").hide();
	}
}
function changeNoYes(){
	  var flagRelate = $("#flagRelate").val();
	  if(flagRelate=='0'){
		  $("#kindDiv").hide();
		  $("#buttonShow").hide();
	  }else{
		  $("#kindDiv").show();
		  $("#buttonShow").show();
	  }
}


function checkAddFlag(){
    var flag = true ;
    var riskCode = $("#riskCodeV").val();
	var kindCode = $("#kindCodeV").val();
	var kindFlag = $("#kindFlag").val();
	var flagRelate = $("#flagRelate").val();
	if(kindFlag=='02'){
		return true ;
	}
	if(flagRelate=='0'){
		$("#kindDiv").hide();
	    $("#buttonShow").hide();
		return true;
	}else if(flagRelate=='1'){
		$("#kindDiv").show();
	    $("#buttonShow").show();
		$.ajax({
			  type: "POST",
	  		  async:false,
	  		  url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkAddFlag.do',
	  		  dataType: 'text',
	  		  data:{"geKind.id.riskCode":riskCode,"geKind.id.kindCode":kindCode,"geKind.kindflag":'02'},
	  		  success: function(backData){
	  			  if(backData == '02'){
	  			     alert("该险种下暂未配置附加险，请先添加附加险");
	  			     $("#kindFlag").attr("value","02");
	  			     $("#kindDiv").hide();
	  				 $("#buttonShow").hide();
	  				 $("#flagRelateShow").hide();
	  				 $("#flagRelate").val(0);
	  			      flag = false ;
	  			  }else{
	  				  flag =  true ;
	  			  }
	  		    }
		});
	}
	return flag ;
}

function checkFlag(){
	var checkFlagValue=$("#flagkind").val();
	var kindCode = $("#kindCodeV").val();
	var riskCode = $("#riskCodeV").val();
	$.ajax({
		type:'post',
		async:false,
		dataType:'text',
		url: contextRootPath+'/business/cmpProductManage/riskAndKindManage/checkFlag.do',
		data:{"geKindRelate.id.riskCode":riskCode,"kindflag":checkFlagValue,"geKindRelate.id.kindCode":kindCode},
        success:function(backData){
        	if(backData == '02'){
 			     alert("对不起您不能修改该险别类型");
 			     $("#kindFlag").attr("value",checkFlagValue);
 			     if(checkFlagValue=='01'){
 			    	$("#kindDiv").show();
 					$("#buttonShow").show();
 			     }else if(checkFlagValue=='02'){
 			    	$("#kindDiv").hide();
 					$("#buttonShow").hide();
 			     }
 			     return false ;
 			  }else{
 				  return true ;
 			  }
 		    }
	});	
}
function checkOther(field){
	   var ttvf = tt.vf.req;//非空
	   var check =  $(field).parent().siblings("td").children("input");
	   var checkValue = $(field).val();
	    if(checkValue !=''){
	    	var id = check.attr('id');
	    	tt.vf.req.addId(id);
	    	tt.vf.req.addId("otherDuty");
	    }
}