/**************************************************************************
 * ��������: VerifyInput.js
 * ������: ͨ��У�麯��Ver2
 *           ��Ver1���ӿ�ѧ������У�飬У�����ͼ�ɽ��С��루&����������|��������
 * ������  : �� ��
 * �����������: 2002-10-22
**************************************************************************/
var arrVerifyErrInfo = new Array();   //��¼һ���ֶε�У�������Ϣ             

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
	this.verifyTime          = verifyTime; // ����ʱ���ʽ�ļ���
	this.verifyIdcard        = verifyIdcard;
	this.verifyInteger       = verifyInteger;
	this.verifyLength        = verifyLength;
	this.verifyValue         = verifyValue;
	this.verifyCode          = verifyCode;
}    

//У��Ԫ�أ�strInfoΪԪ��У����Ϣ��strValueΪԪ��ֵ
function verifyElement(strInfo, strValue, element) {
  strValue = strValue.trim();     //��տո�   
  var passFlag = true;               //У��ͨ����־��true��ʾͨ��
  var vName;                         //У���ֶ�����
  var vType;                         //Ҫ���е�У������
  var intIndex;                      //���������
  var typeStack = new Array();       //һ���ֶε�У������ջ
  var operStack = new Array();       //һ���ֶε�У�������Ŷ�ջ�������ڡ��롱�����򡱼���
  
  while (arrVerifyErrInfo != "") {   //���ǰһ���ֶε�У�������Ϣ
    arrVerifyErrInfo.pop();
  }
  
  //������ֶ����ƣ�����ǰһ�汾�������á�|���ָ�
  vName = strInfo.substring(0, strInfo.indexOf("|"));    
  strInfo = strInfo.substring(strInfo.indexOf("|") + 1); 
  
  //��ֳ�У�����ͣ�������У�飬����У������ͨ��TRUE����FALSE���������ջ
  while (strInfo != "") {
  	if (strInfo.indexOf("|") != -1 && strInfo.indexOf("&") != -1) {         //������������
      intIndex = strInfo.indexOf("|")>strInfo.indexOf("&")?strInfo.indexOf("&"):strInfo.indexOf("|");
      vType = strInfo.substring(0, intIndex);
      typeStack.push(verifyType(vName, vType, strValue, element));
      operStack.push(strInfo.substring(intIndex, intIndex + 1));
      strInfo = strInfo.substring(intIndex + 1);      
    } else if (strInfo.indexOf("|") != -1 || strInfo.indexOf("&") != -1) {  //ֻ��һ������
    	intIndex = strInfo.indexOf("|")>strInfo.indexOf("&")?strInfo.indexOf("|"):strInfo.indexOf("&");
      vType = strInfo.substring(0, intIndex);
      typeStack.push(verifyType(vName, vType, strValue, element));
      operStack.push(strInfo.substring(intIndex, intIndex + 1));
      strInfo = strInfo.substring(intIndex + 1);       
    } else {                                                                //������                                                       	
    	vType = strInfo;
      strInfo = ""; 
      typeStack.push(verifyType(vName, vType, strValue, element));
    }    
  }
    
  //alert("vName:"+vName+"\nstack:"+typeStack+"\n"+operStack);
  passFlag = typeStack[0];                                         //ֻ��һ��У������ʱ
  for (var k=0; k<operStack.length; k++) {                         //�ж��У�����ͣ���������         
    //alert("stack:"+typeStack[k]+"\n"+operStack[k]);
    if (operStack[k] == "|") {
      typeStack[k + 1] = typeStack[k] | typeStack[k + 1];
    } else if (operStack[k] == "&"){
    	typeStack[k + 1] = typeStack[k] & typeStack[k + 1];  	
    } else {
    	alert("У�������������");
    }
    passFlag = typeStack[k + 1];
  }

  var strVerifyErrInfo = "�������󣬿��������´���\n";
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
  
  if (vType.toUpperCase() == "TIME") { // ����ʱ���ʽ�ļ���
    passFlag = verifyTime(vName, strValue); 
  }
  
  if (vType.toUpperCase() == "IDCARD") { // ������ݰߵ�ļ���
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
      
  if (vType.toUpperCase().indexOf("LEN") == 0)   //���������޸�Լ����LEN��
    //passFlag = (verifyNotNull(vName, strValue)&&verifyLength(vName, strValue, vType));
    passFlag = verifyLength(vName, strValue, vType);
        
  if (vType.toUpperCase().indexOf("VALUE") == 0) //���������޸�Լ����VALUE��
    //passFlag = (verifyNotNull(vName, strValue)&&verifyNumber(vName, strValue)&&verifyValue(vName, strValue, vType));
    passFlag = (verifyNumber(vName, strValue) && verifyValue(vName, strValue, vType));
    
  if (vType.toUpperCase().indexOf("CODE") == 0) //checkArray checkURL
    //passFlag = (verifyNotNull(vName, strValue)&&verifyCode(vName, strValue, vType));
    passFlag = verifyCode(vName, strValue, element);

  if (vType.toUpperCase() == "ZIPCODE") {//���������޸�Լ����CODE:��
    passFlag = (verifyLength(vName, strValue, "len=6") && verifyNumber(vName, strValue));
  }
  
  if (vType.toUpperCase() == "EXCELCOL") 
	passFlag = verifyExcelColLetter(vName, strValue);
  
  if (vType.toUpperCase() == "YEARMONTHBEFORECURMONTH") { // ����ͳ���·�С�ڵ�ǰ�µ�У��
    passFlag = verifyYearMonthBeforeCurMonth(vName, strValue);
  }
  
  if (vType.toUpperCase() == "YEARONLYBEFORECURYEAR") { // ����ͳ�����С�ڵ�ǰ���У��
    passFlag = verifyYearOnlyBeforeCurYear(vName, strValue);
  }
  
  return passFlag;
}

//����true��ʾͨ��У�飬����false��ʾ��ͨ��������Υ��
//����Ϊ��У��
function verifyMustNull(vName, strValue) {
  if (strValue != "") {
    //alert(vName + "����Ϊ��!");
    arrVerifyErrInfo.push(vName + "����Ϊ�գ�\n");
    return false;
  }
  return true;
}

//����Ϊ��У��
function verifyNotNull(vName, strValue) {
  if (strValue == "") {
    //alert(vName + "����Ϊ��!");
    arrVerifyErrInfo.push(vName + "����Ϊ�գ�\n");
    return false;
  }
  return true;
}

//��������У��
function verifyNumber(vName, strValue) { 
  if (strValue != "" && !isNumeric(strValue)) {  //�����˿�ѧ������
  //if (strValue != "" && isNaN(parseFloat(strValue)) && !isNumeric(strValue)) {
    //alert(vName + "������Ч������!");
    arrVerifyErrInfo.push(vName + "������Ч�����֣�\n");
    return false;
  }
  return true;
}

//��������У��
function verifyDate(vName, strValue) { 
	if( "" == strValue ) {
		return true;
	}
	
	if( 'false' == isDate(strValue) ) {
		arrVerifyErrInfo.push(vName + "������Ч�����ڸ�ʽ(YYYY-MM-DD)��\n");
		return false;
	} else {
		return true;
	}
}

/** ����ʱ���ʽ */
function verifyTime(vName, strValue) {

	if("" == strValue ) {
		return true;
	}
	
	if('false' == isTime(strValue) ) {
		arrVerifyErrInfo.push(vName + "������Ч��ʱ���ʽ(HH:MM:SS)��\n");
		return false;
	} else {
		return true;
	}
}

/** �������֤ */
function verifyIdcard(vName, strValue) {

	if ("" == strValue ) {
		return true;
	}
	
	if (!isIdcard(strValue)) {
		arrVerifyErrInfo.push(vName + "������Ч�����֤��\n");
		return false;
	} else {
		return true;
	}
	
}

/** �����ֻ����� */
function verifyMobile(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isMobile(strValue)) {
		arrVerifyErrInfo.push(vName + "������Ч���ֻ����룡\n");
		return false;
	} else {
		return true;
	}
}

/** ������ϵ�绰 */
function verifyPhone(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isPhone(strValue)) {
		arrVerifyErrInfo.push(vName + "������Ч����ϵ�绰��\n");
		return false;
	} else {
		return true;
	}
}

/** ����������� */
function verifyEMail(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isEMail(strValue)) {
		arrVerifyErrInfo.push(vName + "������Ч�ĵ������䣡\n");
		return false;
	} else {
		return true;
	}
}

/** ����������Ч�� */
function verifyPassword(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	
	if (!isPassword(strValue)) {
		arrVerifyErrInfo.push(vName + "������Ч�������ʽ��\n������ȷ��ʽ��6-16λ�ַ����ɰ�����ĸ�����֡�");
		return false;
	} else {
		return true;
	}
}

/** ����URL */
function verifyUrl(vName, strValue) {
	if ("" == strValue ) {
		return true;
	}
	var filter = /^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?(\/((\.)?(\?)?=?&?[a-zA-Z0-9_-](\?)?)*)*$/i;
	if (!filter.test(strValue)) {
		arrVerifyErrInfo.push(vName + "������Ч����ַ��\n");
		return false;
	}
	return true;
}

//��������У��
function verifyInteger(vName, strValue) { 
  if (strValue != "" && !isInteger(strValue)) {
    //alert(vName + "������Ч������!");
    arrVerifyErrInfo.push(vName + "������Ч��������\n");
    return false;
  }
  return true;
}

//���볤��У��
function verifyLength(vName, strValue, vType) { 
  var oper;
  var len;
  var strOperLen = vType.substring(3);          //��ȡ����������ֵ
  
  if (strValue == "") return true;

  if (isNaN(parseInt(strOperLen.substring(1)))) {
    oper = strOperLen.substring(0, 2);          //��Ϊ">=", "<="ʱ
    len = strOperLen.substring(2);
  } else {
    oper = strOperLen.substring(0, 1);          //��Ϊ"=", "<", ">"ʱ
    len = strOperLen.substring(1);
  }

  //alert("vName:"+vName+"strValue:"+strValue+"oper:"+oper+"len:"+len);
  switch (oper) {
    case "=" : 
      if (strValue.length != parseInt(len)) {
        //alert(vName + "�����Ϲ涨�����볤��!������Ҫ����"+len);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨�����볤��!������Ҫ����" + len + "\n");
        return false;
      }
      break;
    case ">" : 
      if (strValue.length <= parseInt(len)) {
        //alert(vName + "�����Ϲ涨�����볤��!������Ҫ����"+len);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨�����볤��!������Ҫ����" + len + "\n");
        return false;
      }
      break;
    case "<" : 
      if (strValue.length >= parseInt(len)) {
        //alert(vName + "�����Ϲ涨�����볤��!������ҪС��"+len);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨�����볤��!������ҪС��" + len + "\n");
        return false;
      }
      break;
    case ">=" :
      if (strValue.length < parseInt(len)) {
        //alert(vName + "�����Ϲ涨�����볤��!������Ҫ���ڵ���"+len);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨�����볤��!������Ҫ���ڵ���" + len + "\n");
        return false;
      }
      break;
    case "<=" :
      if (strValue.length > parseInt(len)) {
        //alert(vName + "�����Ϲ涨�����볤��!������ҪС�ڵ���"+len);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨�����볤��!������ҪС�ڵ���" + len + "\n");
        return false;
      }
      break;
  }
  
  return true;
}

//����ֵУ��
function verifyValue(vName, strValue, vType) { 
  var oper;
  var Val;
  var strOperVal = vType.substring(5);            //��ȡ����������ֵ
  
  if (strValue == "") return true;

  if (isNaN(parseFloat(strOperVal.substring(1)))) {
    oper = strOperVal.substring(0, 2);            //��Ϊ">=", "<="ʱ
    Val = strOperVal.substring(2);
  } else {
    oper = strOperVal.substring(0, 1);            //��Ϊ"=", "<", ">"ʱ
    Val = strOperVal.substring(1);
  }

  //alert("vName:"+vName+"strValue:"+strValue+"oper:"+oper+"Val:"+Val);
  switch (oper) {
    case "=" : 
      if (parseFloat(strValue) != parseFloat(Val)) {
        //alert(vName + "�����Ϲ涨��ȡֵ!����ֵ��Ҫ����"+Val);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨��ȡֵ!����ֵ��Ҫ����" + Val + "\n");
        return false;
      }
      break;
    case ">" : 
      if (parseFloat(strValue) <= parseFloat(Val)) {
        //alert(vName + "�����Ϲ涨��ȡֵ!����ֵ��Ҫ����"+Val);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨��ȡֵ!����ֵ��Ҫ����" + Val + "\n");
        return false;
      }
      break;
    case "<" : 
      if (parseFloat(strValue) >= parseFloat(Val)) {
        //alert(vName + "�����Ϲ涨��ȡֵ!����ֵ��ҪС��"+Val);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨��ȡֵ!����ֵ��ҪС��" + Val + "\n");
        return false;
      }
      break;
    case ">=" :
      if (parseFloat(strValue) < parseFloat(Val)) {
        //alert(vName + "�����Ϲ涨��ȡֵ!����ֵ��Ҫ���ڵ���"+Val);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨��ȡֵ!����ֵ��Ҫ���ڵ���" + Val + "\n");
        return false;
      }
      break;
    case "<=" :
      if (parseFloat(strValue) > parseFloat(Val)) {
        //alert(vName + "�����Ϲ涨��ȡֵ!����ֵ��ҪС�ڵ���"+Val);
        arrVerifyErrInfo.push(vName + "�����Ϲ涨��ȡֵ!����ֵ��ҪС�ڵ���" + Val + "\n");
        return false;
      }
      break;
  }
  
  return true;
}

//��������У��
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
  	arrVerifyErrInfo.push(vName + "���벻�������ݿ�涨��ȡֵ��Χ������Ļ�˫�������ѡ��\n");
  }
  return passFlag;
}

//ҵ�������ýӿڣ����ͨ��У�鷵��true�����򷵻�false
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

//jQuerySelector,��������һ����ʾinputԪ�ؼ��ϵ�jQuery����
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