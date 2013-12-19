var calendarImg = "/NetSaleProduct/mis/global/js/My97DatePicker/skin/datePicker.gif";
var insureRuleTable = document.getElementById("insureRuleTable");
var insRuleTable = document.getElementById("insRuleTable");
var productTable = document.getElementById("productTable");
var rateInfoTable = document.getElementById("rateInfoTable");
var version = getExplorerVersion();
/**
*比较两个日期范围（日期格式yyyy-MM-dd）
*用date1和date2比较
*如果date1在date2范围内则返回true，否则返回false
*/
function compareDate(date1,date2){
	var date1Arr = date1.split("-");
	var date2Arr = date2.split("-");
	if(date1Arr.length!=3&&date2Arr.length!=3)return;
	if(date1Arr[0]-date2Arr[0]<0){
		return false;
	}else if(date1Arr[0]-date2Arr[0]==0&&date1Arr[1]-date2Arr[1]<0){
		return false;
	}else if(date1Arr[0]-date2Arr[0]==0&&date1Arr[1]-date2Arr[1]==0&&date1Arr[2]-date2Arr[2]<0){
		return false;	
	}
	return true;
}

/**
*获取日历图片HTML样式
*@param id 
*/
function getCalendarImgHTML(id){
	return "&nbsp;<img onclick=\"WdatePicker({el:'" + id + "',minDate:'%y-%M-{%d+1}'})\" src='" + calendarImg + "' style='width:16;height:22;cursor:pointer;' align='absmiddle'>";
}

/**
 *	检查文件是否为图片 
 * @param ext
 * @return
 */
function checkIsImage(ext){
    if (!ext.match(/jpg|gif|png|bmp/i)) {
        return false;
    }
    return true;
}

/**
*标签头动态显示
*@param index 坐标
*/
function changeTitleStyle(index){
	(document.parentWindow || document.defaultView).parent.document.getElementById("actived").value = "1";
	var array = (document.parentWindow || document.defaultView).parent.document.getElementsByTagName("a");
	var arraySrc = new Array(array.length);
	arraySrc[0] = "basicInfo.jsp";
	arraySrc[1] = "extraInfo.jsp";
	arraySrc[2] = "legalNotices.jsp";
	arraySrc[3] = "notice.jsp";
	arraySrc[4] = "insureRule.jsp";
	arraySrc[5] = "configItem.jsp";
	arraySrc[6] = "confirm.jsp";
	for(var i = 0 ; i < array.length; i++){
		if(i == index){
			array[i].className = "label_on";
			array[i].src = arraySrc[i];
		}else{
			array[i].className = "label_off";
		}
		if(array[i].disabled){
			array[i].disabled = false;
		}
	}
}

function disabledAllTitle(){
	var array = (document.parentWindow || document.defaultView).parent.document.getElementsByTagName("a");
	for(var i = 0 ; i < array.length; i++){
		array[i].disabled = true;
	}
}
//校验必填项
function checkNull(){
	var allEle = document.all;
	if(!allEle){
		allEle = document.getElementsByTagName("*");
	}
	var att = null;
	for(var i = 0; i < allEle.length; i++){
		att = allEle[i].attributes;
		if(allEle[i].tagName == "INPUT"){
			for(var j = 0; j < att.length; j++){
				if(att[j].name == "required" && allEle[i].value == ""){
					allEle[i].style.backgroundColor = "yellow";
					alert("该项不能为空!");
					allEle[i].focus();
					return false;
				}else if(att[j].name == "required" && allEle[i].value != ""){
					allEle[i].style.backgroundColor = "";
				}
			}
		}else if(allEle[i].tagName == "SELECT"){
			for(var j = 0; j < att.length; j++){
				if(att[j].name == "required" && allEle[i].value == ""){
					alert("该项不能为空!");
					allEle[i].focus();
					return false;
				}
			}
		}
	}
	return true;
}

//校验数据类型（整数或小数）
function checkDataFormat(){
	var allEle = document.all;
	if(!allEle){
		allEle = document.getElementsByTagName("*");
	}
	var att = null;
	for(var i = 0; i < allEle.length; i++){
		att = allEle[i].attributes;
		if(allEle[i].tagName == "INPUT"){
			for(var j = 0; j < att.length; j++){
				if(att[j].name == "integer" && allEle[i].value != ""&&!checkInt(allEle[i].value)){
					allEle[i].value == "";
					allEle[i].style.backgroundColor = "yellow";
					allEle[i].focus();
					alert("该项只能为整数!");
					return false;
				}else if(att[j].name == "float" && allEle[i].value != ""&&!checkFloat(allEle[i].value)){
					allEle[i].value == "";
					allEle[i].style.backgroundColor = "yellow";
					allEle[i].focus();
					alert("该项只能为小数!");
					return false;
				}else{
					allEle[i].style.backgroundColor = "";
				}
			}
		}
	}
	return true;
}

/**
*校验浮点数
*/
function checkFloat(value){
	var re = /^\d+(\.\d+)?$/;
	return re.test(value);
}
/**
*校验非负整数
*/
function checkInt(value){
	var re = /^\d+$/;
	return re.test(value);
}

/**
*年龄单位下拉列表
*/
function getUnitOptionsHTML(id,name){
	var html = "<select required";
	if(name != ""){
		html += " name='"+name+"'";
	}
	if(id != ""){
		html += " id='"+id+"'";
	}
	html += ">";
 	html += "<option value='D'>天</option>";
 	html += "<option value='M'>月</option>";
 	html += "<option value='Y'>周岁</option>";
 	html += "</select>";
	return html;
}

/**
*性别下拉列表
*/
function getSexOptionHTML(name){
	var html = "<select name='"+name+"'>";
		html += "<option value='M'>男</option>";
		html += "<option value='F'>女</option>";
		html += "</select>";
	return html;
}

/**
*文本框
*/
function getInputHTML(id,name,att){
	var html = "<input type='text' maxlength='5' size='3' ";
	if(id != null){
		html += "id='"+id+"'";
	}
	if(name != ""){
		html += "name='"+name+"'";
	}
	html += att+">";
	return html;
}

/**
*删除责任
*@param	id 
*/
function deleteRateInfo(id){
	rateInfoTable.deleteRow(document.getElementById(id).rowIndex);
}
/**
*checkbox一直被选中
*@param	obj 
*/
function checkAllTrue(obj){
	obj.checked = true;
}

/**
*启用checkbox
*@param	obj 
*/
function showRequeriedIterm(obj){
	if(obj.checked){
		document.getElementById(obj.id + "Value").disabled = false;
	}else{
		document.getElementById(obj.id + "Value").disabled = true;
	}	
}
/**
*全选/否选
*@param	obj 
*@param	name 
*/
function checkAll(obj,name){
	var children = document.getElementsByName(name);
	for(var i = 0; i < children.length; i++){
		children[i].checked = obj.checked;
	}
}

function initValue(id){
	var array = document.getElementsByName(id+"_");
	var value = "";
	for(var i = 0; i < array.length; i++){
		if(array[i].checked){
			value += array[i].value + ",";
		}
	}
	if(value.length > 1){
		value = value.substr(0, value.length -1);
	}
	document.getElementById(id).value = value;
}
function getImagePath(obj) { 
	if(obj){
		if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
	          return obj.value;
		 }else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
		      if (obj.files) {
		          return obj.files.item(0).getAsDataURL();
		      }
		      return obj.value;
		 }
	     return obj.value;
	}
}
function contains(array){
	var hash = {}; 
	for(var i = 0; i < array.length; i++){ 
		if(hash[array[i].value]){ 
			array[i].focus();
			return true; 
		}
		hash[array[i].value] = true; 
	} 
	return false;
}
/**
 * 获取浏览器类型版本
 * @return
 */
function getExplorerVersion(){
	var explorerVersion;
    var ua = navigator.userAgent.toLowerCase();
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? explorerVersion = "IE:" + s[1]:
    (s = ua.match(/firefox\/([\d.]+)/)) ? explorerVersion = "Firefox:" + s[1]:
    (s = ua.match(/chrome\/([\d.]+)/)) ? explorerVersion = "Chrome:" + s[1]:
    (s = ua.match(/opera.([\d.]+)/)) ? explorerVersion = "Opera:" + s[1]:
    (s = ua.match(/version\/([\d.]+).*safari/)) ? explorerVersion = "Safari:" + s[1]: null;
    if(explorerVersion){
    	return explorerVersion;
    }
}