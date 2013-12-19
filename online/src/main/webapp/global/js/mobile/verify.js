/**************************************************************************
 * 程序名称: VerifyInput.js
 * 程序功能: 通用校验函数Ver2
 *           比Ver1增加科学记数法校验，校验类型间可进行“与（&）”、“或（|）”运算
 * 创建人  : 胡 博
 * 最近更新日期: 2002-10-22
**************************************************************************/
var arrVerifyErrInfo = new Array();   //记录一个字段的校验错误信息             

function verifyClass() {
	this.verifyInput         = verifyInput;
	this.verifyElement       = verifyElement;
	this.verifyType          = verifyType;
	this.verifyMustNull      = verifyMustNull;
	this.verifyMobile        = verifyMobile;
	this.verifyPhone         = verifyPhone;
	this.verifyEMail         = verifyEMail;
	this.verifyPassword      = verifyPassword;
	this.verifyNotNull       = verifyNotNull;
	this.verifyNumber        = verifyNumber;
	this.verifyDate          = verifyDate;
	this.verifyTime          = verifyTime; // 增加时间格式的检验
	this.verifyIdcard        = verifyIdcard;
	this.verifyInteger       = verifyInteger;
	this.verifyLength        = verifyLength;
	this.verifyValue         = verifyValue;
	this.verifyCode          = verifyCode;
}    

//校验元素，strInfo为元素校验信息，strValue为元素值
function verifyElement(strInfo, strValue, element) {
  strValue = strValue.trim();     //清空空格   
  var passFlag = true;               //校验通过标志，true表示通过
  var vName;                         //校验字段名称
  var vType;                         //要进行的校验类型
  var intIndex;                      //运算符索引
  var typeStack = new Array();       //一个字段的校验结果堆栈
  var operStack = new Array();       //一个字段的校验计算符号堆栈，仅限于“与”，“或”计算
  
  while (arrVerifyErrInfo != "") {   //清空前一个字段的校验错误信息
    arrVerifyErrInfo.pop();
  }
  
  //分离出字段名称，兼容前一版本，故仍用“|”分隔
  vName = strInfo.substring(0, strInfo.indexOf("|"));    
  strInfo = strInfo.substring(strInfo.indexOf("|") + 1); 
  
  //拆分出校验类型，并进行校验，返回校验结果（通过TRUE，否FALSE），并入堆栈
  while (strInfo != "") {
  	if (strInfo.indexOf("|") != -1 && strInfo.indexOf("&") != -1) {         //存在两种运算
      intIndex = strInfo.indexOf("|")>strInfo.indexOf("&")?strInfo.indexOf("&"):strInfo.indexOf("|");
      vType = strInfo.substring(0, intIndex);
      typeStack.push(verifyType(vName, vType, strValue, element));
      operStack.push(strInfo.substring(intIndex, intIndex + 1));
      strInfo = strInfo.substring(intIndex + 1);      
    } else if (strInfo.indexOf("|") != -1 || strInfo.indexOf("&") != -1) {  //只有一种运算
    	intIndex = strInfo.indexOf("|")>strInfo.indexOf("&")?strInfo.indexOf("|"):strInfo.indexOf("&");
      vType = strInfo.substring(0, intIndex);
      typeStack.push(verifyType(vName, vType, strValue, element));
      operStack.push(strInfo.substring(intIndex, intIndex + 1));
      strInfo = strInfo.substring(intIndex + 1);       
    } else {                                                                //无运算                                                       	
    	vType = strInfo;
      strInfo = ""; 
      typeStack.push(verifyType(vName, vType, strValue, element));
    }    
  }
    
  //alert("vName:"+vName+"\nstack:"+typeStack+"\n"+operStack);
  passFlag = typeStack[0];                                         //只有一个校验类型时
  for (var k=0; k<operStack.length; k++) {                         //有多个校验类型，进行运算         
    //alert("stack:"+typeStack[k]+"\n"+operStack[k]);
    if (operStack[k] == "|") {
      typeStack[k + 1] = typeStack[k] | typeStack[k + 1];
    } else if (operStack[k] == "&"){
    	typeStack[k + 1] = typeStack[k] & typeStack[k + 1];  	
    } else {
    	alert("校验参数设置有误");
    }
    passFlag = typeStack[k + 1];
  }

  var strVerifyErrInfo = "输入有误，可能是如下错误：\n";
  if (!passFlag) {
  	while (arrVerifyErrInfo != "") {
  	  strVerifyErrInfo = strVerifyErrInfo + arrVerifyErrInfo.pop();
  	}
  	alert(strVerifyErrInfo);
  }
  
  return passFlag;
}

function verifyType(vName, vType, strValue, element) {
  var passFlag = true;  
    
  if (vType.toUpperCase() == "NULL") 
    passFlag = verifyMustNull(vName, strValue);
  
  if (vType.toUpperCase() == "NOTNULL") 
    passFlag = verifyNotNull(vName, strValue);
    
  if (vType.toUpperCase() == "NUM") 
    //passFlag = (verifyNotNull(vName, strValue)&&verifyNumber(vName, strValue));
    passFlag = verifyNumber(vName, strValue);
  
  if (vType.toUpperCase() == "DATE") {
    //passFlag = (verifyNotNull(vName, strValue)&&verifyDate(vName, strValue));
    passFlag = verifyDate(vName, strValue);
  }
  
  if (vType.toUpperCase() == "TIME") { // 增加时间格式的检验
    passFlag = verifyTime(vName, strValue); 
  }
  
  if (vType.toUpperCase() == "IDCARD") { // 增加身份斑点的检验
    passFlag = verifyIdcard(vName, strValue); 
  }
  
  if (vType.toUpperCase() == "URL") {
  	passFlag = verifyUrl(vName, strValue); 
  }
  
  if (vType.toUpperCase() == "MOBILE") {
    passFlag = verifyMobile(vName, strValue);
  }
  
  if (vType.toUpperCase() == "PHONE") {
    passFlag = verifyPhone(vName, strValue);
  }
  
  if (vType.toUpperCase() == "EMAIL") {
    passFlag = verifyEMail(vName, strValue);
  }
  
  if (vType.toUpperCase() == "PASSWORD") {
    passFlag = verifyPassword(vName, strValue);
  }
  
  if (vType.toUpperCase() == "INT") 
    //passFlag = (verifyNotNull(vName, strValue)&&verifyInteger(vName, strValue));
    passFlag = verifyInteger(vName, strValue);
      
  if (vType.toUpperCase().indexOf("LEN") == 0)   //不能随意修改约定“LEN”
    //passFlag = (verifyNotNull(vName, strValue)&&verifyLength(vName, strValue, vType));
    passFlag = verifyLength(vName, strValue, vType);
        
  if (vType.toUpperCase().indexOf("VALUE") == 0) //不能随意修改约定“VALUE”
    //passFlag = (verifyNotNull(vName, strValue)&&verifyNumber(vName, strValue)&&verifyValue(vName, strValue, vType));
    passFlag = (verifyNumber(vName, strValue) && verifyValue(vName, strValue, vType));
    
  if (vType.toUpperCase().indexOf("CODE") == 0) //checkArray checkURL
    //passFlag = (verifyNotNull(vName, strValue)&&verifyCode(vName, strValue, vType));
    passFlag = verifyCode(vName, strValue, element);

  if (vType.toUpperCase() == "ZIPCODE") {//不能随意修改约定“CODE:”
    passFlag = (verifyLength(vName, strValue, "len=6") && verifyNumber(vName, strValue));
  }
  
  if (vType.toUpperCase() == "EXCELCOL") 
	passFlag = verifyExcelColLetter(vName, strValue);
  
  if (vType.toUpperCase() == "YEARMONTHBEFORECURMONTH") { // 增加统计月份小于当前月的校验
    passFlag = verifyYearMonthBeforeCurMonth(vName, strValue);
  }
  
  if (vType.toUpperCase() == "YEARONLYBEFORECURYEAR") { // 增加统计年份小于当前年的校验
    passFlag = verifyYearOnlyBeforeCurYear(vName, strValue);
  }
  
  return passFlag;
}

//返回true表示通过校验，返回false表示不通过，数据违法
//必须为空校验
function verifyMustNull(vName, strValue) {
  if (strValue != "") {
    //alert(vName + "必须为空!");
    arrVerifyErrInfo.push(vName + "必须为空！\n");
    return false;
  }
  return true;
}

//不能为空校验
function verifyNotNull(vName, strValue) {
  if (strValue == "") {
    //alert(vName + "不能为空!");
    arrVerifyErrInfo.push(vName + "不能为空！\n");
    return false;
  }
  return true;
}

//数字类型校验
function verifyNumber(vName, strValue) { 
  if (strValue != "" && !isNumeric(strValue)) {  //屏蔽了科学计数法
  //if (strValue != "" && isNaN(parseFloat(strValue)) && !isNumeric(strValue)) {
    //alert(vName + "不是有效的数字!");
    arrVerifyErrInfo.push(vName + "不是有效的数字！\n");
    return false;
  }
  return true;
}

//日期类型校验
function verifyDate(vName, strValue) { 
	if( "" == strValue ) {
		return true;
	}
	
	if( 'false' == isDate(strValue) ) {
		arrVerifyErrInfo.push(vName + "不是有效的日期格式(YYYY-MM-DD)！\n");
		return false;
	} else {
		return true;
	}
}

/** 检验时间格式 */
function verifyTime(vName, strValue) {

	if("" == strValue ) {
		return true;
	}
	
	if('false' == isTime(strValue) ) {
		arrVerifyErrInfo.push(vName + "不是有效的时间格式(HH:MM:SS)！\n");
		return false;
	} else {
		return true;
	}
}

/** 检验身份证 */
function verifyIdcard(vName, strValue) {

	if ("" == strValue ) {
		return true;
	}
	
	if (!isIdcard(strValue)) {
		arrVerifyErrInfo.push(vName + "不是有效的身份证！\n");
		return false;
	} else {
		return true;
	}
	
}

/** 检验手机号码 */
function verifyMobile(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isMobile(strValue)) {
		arrVerifyErrInfo.push(vName + "不是有效的手机号码！\n");
		return false;
	} else {
		return true;
	}
}

/** 检验联系电话 */
function verifyPhone(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isPhone(strValue)) {
		arrVerifyErrInfo.push(vName + "不是有效的联系电话！\n");
		return false;
	} else {
		return true;
	}
}

/** 检验电子邮箱 */
function verifyEMail(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isEMail(strValue)) {
		arrVerifyErrInfo.push(vName + "不是有效的电子邮箱！\n");
		return false;
	} else {
		return true;
	}
}

/** 检验密码有效性 */
function verifyPassword(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isPassword(strValue)) {
		arrVerifyErrInfo.push(vName + "不是有效的密码格式！\n密码正确格式：6-16位字符，可包含字母、数字。");
		return false;
	} else {
		return true;
	}
}

/** 检验URL */
function verifyUrl(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	var filter = /^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?(\/((\.)?(\?)?=?&?[a-zA-Z0-9_-](\?)?)*)*$/i;
	if (!filter.test(strValue)) {
		arrVerifyErrInfo.push(vName + "不是有效的网址！\n");
		return false;
	}
	return true;
}

//整数类型校验
function verifyInteger(vName, strValue) { 
  if (strValue != "" && !isInteger(strValue)) {
    //alert(vName + "不是有效的整数!");
    arrVerifyErrInfo.push(vName + "不是有效的整数！\n");
    return false;
  }
  return true;
}

//输入长度校验
function verifyLength(vName, strValue, vType) { 
  var oper;
  var len;
  var strOperLen = vType.substring(3);          //截取出操作符和值
  
  if (strValue == "") return true;

  if (isNaN(parseInt(strOperLen.substring(1)))) {
    oper = strOperLen.substring(0, 2);          //当为">=", "<="时
    len = strOperLen.substring(2);
  } else {
    oper = strOperLen.substring(0, 1);          //当为"=", "<", ">"时
    len = strOperLen.substring(1);
  }

  //alert("vName:"+vName+"strValue:"+strValue+"oper:"+oper+"len:"+len);
  switch (oper) {
    case "=" : 
      if (strValue.length != parseInt(len)) {
        //alert(vName + "不符合规定的输入长度!长度需要等于"+len);
        arrVerifyErrInfo.push(vName + "不符合规定的输入长度!长度需要等于" + len + "\n");
        return false;
      }
      break;
    case ">" : 
      if (strValue.length <= parseInt(len)) {
        //alert(vName + "不符合规定的输入长度!长度需要大于"+len);
        arrVerifyErrInfo.push(vName + "不符合规定的输入长度!长度需要大于" + len + "\n");
        return false;
      }
      break;
    case "<" : 
      if (strValue.length >= parseInt(len)) {
        //alert(vName + "不符合规定的输入长度!长度需要小于"+len);
        arrVerifyErrInfo.push(vName + "不符合规定的输入长度!长度需要小于" + len + "\n");
        return false;
      }
      break;
    case ">=" :
      if (strValue.length < parseInt(len)) {
        //alert(vName + "不符合规定的输入长度!长度需要大于等于"+len);
        arrVerifyErrInfo.push(vName + "不符合规定的输入长度!长度需要大于等于" + len + "\n");
        return false;
      }
      break;
    case "<=" :
      if (strValue.length > parseInt(len)) {
        //alert(vName + "不符合规定的输入长度!长度需要小于等于"+len);
        arrVerifyErrInfo.push(vName + "不符合规定的输入长度!长度需要小于等于" + len + "\n");
        return false;
      }
      break;
  }
  
  return true;
}

//输入值校验
function verifyValue(vName, strValue, vType) { 
  var oper;
  var Val;
  var strOperVal = vType.substring(5);            //截取出操作符和值
  
  if (strValue == "") return true;

  if (isNaN(parseFloat(strOperVal.substring(1)))) {
    oper = strOperVal.substring(0, 2);            //当为">=", "<="时
    Val = strOperVal.substring(2);
  } else {
    oper = strOperVal.substring(0, 1);            //当为"=", "<", ">"时
    Val = strOperVal.substring(1);
  }

  //alert("vName:"+vName+"strValue:"+strValue+"oper:"+oper+"Val:"+Val);
  switch (oper) {
    case "=" : 
      if (parseFloat(strValue) != parseFloat(Val)) {
        //alert(vName + "不符合规定的取值!输入值需要等于"+Val);
        arrVerifyErrInfo.push(vName + "不符合规定的取值!输入值需要等于" + Val + "\n");
        return false;
      }
      break;
    case ">" : 
      if (parseFloat(strValue) <= parseFloat(Val)) {
        //alert(vName + "不符合规定的取值!输入值需要大于"+Val);
        arrVerifyErrInfo.push(vName + "不符合规定的取值!输入值需要大于" + Val + "\n");
        return false;
      }
      break;
    case "<" : 
      if (parseFloat(strValue) >= parseFloat(Val)) {
        //alert(vName + "不符合规定的取值!输入值需要小于"+Val);
        arrVerifyErrInfo.push(vName + "不符合规定的取值!输入值需要小于" + Val + "\n");
        return false;
      }
      break;
    case ">=" :
      if (parseFloat(strValue) < parseFloat(Val)) {
        //alert(vName + "不符合规定的取值!输入值需要大于等于"+Val);
        arrVerifyErrInfo.push(vName + "不符合规定的取值!输入值需要大于等于" + Val + "\n");
        return false;
      }
      break;
    case "<=" :
      if (parseFloat(strValue) > parseFloat(Val)) {
        //alert(vName + "不符合规定的取值!输入值需要小于等于"+Val);
        arrVerifyErrInfo.push(vName + "不符合规定的取值!输入值需要小于等于" + Val + "\n");
        return false;
      }
      break;
  }
  
  return true;
}

//代码类型校验
function verifyCode(vName, strValue, element) { 
  var passFlag = true;
  if (strValue == "") return true;
  
  if (element.attr("checkArray") !== undefined){
	  if(jQuery.inArray(strValue, element.attr("checkArray").split(",")) === -1){
		  passFlag = false;
	  }
  }
  
  if (element.attr("checkURL") !== undefined){
	  $.ajax({
			type : "POST",
			async : false,
			url : element.attr("checkURL"),
			dataType : "json",
			cache : false,
			success : function(jsondata) {
		  		var checkArray = new Array();
		  		$.each(jsondata,function(k,v){
					checkArray.push(v.label);
				});
		  		if(jQuery.inArray(strValue, checkArray) === -1){
		  			passFlag = false;
		  		}
	  		}
	  	});
  }

  if (!passFlag) {
  	arrVerifyErrInfo.push(vName + "输入不符合数据库规定的取值范围！请查阅或双击输入框选择！\n");
  }
  return passFlag;
}

//业务程序调用接口，如果通过校验返回true，否则返回false
//modify by xw 2010-06-25
function verifyInput(jQuerySelector){
	var passFlag = true;
	$("input[verify][verify!='']",jQuerySelector).each(function(){
		var t = $(this);
		if (!verifyElementWrap2(t.attr("verify"), this.value, t)){
			passFlag = false;
			return false;
		}
	});
	return passFlag;
}

//modify by xw 2010-06-25
function verifyElementWrap2(strInfo, strValue, _verifyElement){
	if (!verifyElement(strInfo, strValue, _verifyElement))
	{
		_verifyElement.focus().val("").addClass("ui-state-highlight");
		return false;
	}
	_verifyElement.removeClass("ui-state-highlight");
	return true;
}

//jQuerySelector,必须输入一个表示input元素集合的jQuery对象
//add by xw 2010-06-25
function verifyInputField(jQuerySelector){
	var passFlag = true;
	jQuerySelector.filter("[verify][verify!='']").each(function(){
		if (!verifyElementWrap2($(this).attr("verify"), $(this).val(), $(this))){
			passFlag = false;
			return false;
		}
	});
	return passFlag;
}