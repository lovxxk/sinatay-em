/**
* <p>程序名称: Common.js</p>
* <p>程序功能: 公用函数变量定义 </p>
* <p>注意：所有的变量类型为VAR，在JAVA中表示为STRING<p>
*/
//公用变量
/** 日期分隔符,初始值=":" */
var DATEVALUEDELIMITER=":";
/** 域名与域值的分隔符,初始值=":" */
var NAMEVALUEDELIMITER=":";
/** 初始值=":" */
var SBCCASECOLON="：";
/** 域之间的分隔符,初始值="|" */
var FIELDDELIMITER="|";
/** 初始值="｜" */
var SBCCASEVERTICAL="｜";
/** 记录之间的分隔符,初始值="^" */
var RECORDDELIMITER="^";

/** 认为相当于0的数 */
var ZERO_EQUAL_VALUE = 1E-6;

/**
* 更换图片
* <p><b>Example: </b><p>
* <p>function changeImage(image,gif)<p>
* @param image 存放图片的对象或框架或页面
* @param gif 图片的全路径
*/
function changeImage(image,gif){
    Image.src=gif;
}
/**
* 替换字符串函数
* <p><b>Example: </b><p>
* <p>replace("Minim123Minim", "123", "Minim") returns "MinimMinimMinim"<p>
* @param strExpression 字符串表达式
* @param strFind 被替换的子字符串
* @param strReplaceWith 替换的目标字符串，即用strReplaceWith字符串替换掉strFind
* @return 返回替换后的字符串表达式
*/
function replace(strExpression,strFind,strReplaceWith){
    var strReturn;
    var intIndex;
    strReturn = (strExpression==null?"":strExpression);

    while((intIndex=strReturn.indexOf(strFind))>-1){
        strReturn = strReturn.substring(0,intIndex) + strReplaceWith + strReturn.substring(intIndex+strFind.length,strReturn.length);
    }
    return strReturn;
}

/**
* 替换字符串函数
* @param initStr 字符串表达式
* @param initExp 被替换的子字符串
* @param repExp 替换的目标字符串，即用repExp字符串替换掉initExp
* @return 返回替换后的字符串表达式
*/
function replaceReg(initStr, initExp, repExp)
{
    var reg=new RegExp(initExp,"g"); //创建正则RegExp对象

    return initStr.replace(reg, repExp);
}
/**
* 去掉字符串头尾空格
* <p><b>Example: </b><p>
* <p>trim(" Minim ") returns "Minim"<p>
* @param strValue 字符串表达式
* @return 头尾无空格的字符串表达式
*/
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
* 对输入域是否是整数的校验
* VerifyInput.js 引用
* <p><b>Example: </b><p>
* <p>isInteger("Minim") returns false<p>
* <p>isInteger("123") returns true<p>
* @param strValue 输入数值表达式或字符串表达式
* @return 布尔值（true--是整数, false--不是整数）
*/
function isInteger(strValue){
    var NUM="0123456789";
    var i;
    if(strValue==null || strValue=="") return false;
    for(i=0;i<strValue.length;i++){
        if(NUM.indexOf(strValue.charAt(i))<0) return false;
    }
    return true;
}
/**
* 
* 对输入域是否是数字的校验
* <p><b>Example: </b><p>
* <p>isNumeric("Minim") returns false<p>
* <p>isNumeric("123.1") returns true<p>
* @param strValue 输入数值表达式或字符串表达式
* @return 布尔值（true--是数字, false--不是数字）
*/
function isNumeric(strValue){

    if(strValue.charAt(0)=='-')
        strValue = strValue.substr(1);
    var NUM="0123456789.";
    var i;
    if(strValue==null ||strValue=="") return false;
    for(i=0;i<strValue.length;i++){
        if(NUM.indexOf(strValue.charAt(i))<0) return false;
    }
    if(strValue.indexOf(".")!=strValue.lastIndexOf(".")) return false;
    return true;
}
/**
* 对输入域是否是百分数的校验
* <p><b>Example: </b><p>
* <p>isRat("123.1") returns false<p>
* @param strValue 输入数值表达式或字符串表达式
* @return 布尔值（true--是数字, false--不是数字）
*/
function isRat(strValue){

    if ( isNumeric(strValue) )
      if ( eval(strValue)>=0 && eval(strValue)<=100 )
         return true;
    return false;
}
/**
* 日期的合法判断
* <p><b>Example: </b><p>
* <p>isLegalDate("2002", "10", "03") returns true<p>
* <p>isLegalDate("Minim", "10", "03") returns false<p>
* @param year 年份字符串
* @param month 月份字符串
* @param day 日期字符串
* @return 布尔值（true--合法日期, false--非法日期）
*/
function isLegalDate(y,m,d){
    if(isNaN(parseInt(y,10)) || isNaN(parseInt(m,10)) || isNaN(parseInt(d,10)))
        return false;
    var dt = new Date(parseInt(y,10),parseInt(m,10)-1,parseInt(d,10));
    if(dt.getFullYear()==parseInt(y,10) &&  dt.getMonth()==parseInt(m,10)-1 &&  dt.getDate()==parseInt(d,10))
        return true;
    else
        return false;
}
/**
* 对输入域是否是日期的校验
* VerifyInput.js 引用
* <p><b>Example: </b><p>
* <p>isDate("2002-10-03") returns true<p>
* <p>isDate("2002/10/03") returns true<p>
* <p>isDate("20021003") returns true<p>
* @param date 日期字符串,格式可以为“yyyy-mm-dd”,也可以为“yyyy/mm/dd”，也可以为“yyyymmdd”
* @return String（"yyyy-mm-dd"--合法日期, "false"--非法日期）
*/
function isDate(vDate){
    var strValue;
    var dateValue;
    var strReturn = "false";
    var strDate = vDate.trim();

    if(strDate!=null && strDate!='')
    {
    if(strDate.indexOf("-")>=0){
        strValue=strDate.split("-");
        if(strValue.length!=3) return strReturn;
        dateValue=strValue[0]+strValue[1]+strValue[2];
    }
    else{
    	//return strReturn;
        if(strDate.indexOf("/")>=0){
            strValue=strDate.split("/");
            if(strValue.length!=3) return strReturn;
            dateValue=strValue[0]+strValue[1]+strValue[2];
        }
        else{
            if(strDate.length!=8) return strReturn;
            dateValue=strDate;
            strValue = new Array();
            strValue[0]=strDate.substring(0,4);
            strValue[1]=strDate.substring(4,6);
            strValue[2]=strDate.substring(6,strDate.length);
        }
    }

    if(!isInteger(dateValue)) return strReturn;
    //var intYear=dateValue.substring(0,4);
    //var intMonth=dateValue.substring(4,6);
    //var intDay=dateValue.substring(6,dateValue.length);
    var intYear=strValue[0];
    var intMonth=strValue[1];
    var intDay=strValue[2];
    if(intYear.length > 4||intMonth.length > 2||intDay.length > 2){
    	return strReturn;
    }
    if(!isValidDate(new Number(intYear), new Number(intMonth), new Number(intDay))) return strReturn;
    strReturn=intYear+"-"+intMonth+"-"+intDay;
    return strReturn;
    }else{
        return strReturn;
    }
}

/**
* 是否为有效日期的校验
* <p><b>Example: </b><p>
* <p>isDate(2002,9,30) returns true<p>
* <p>isDate(2002,9,31) returns false<p>
* @param year 年数值, month 月数值, day 日数值
* @return boolean（true - 合法日期, false - 非法日期）
*/
function isValidDate(year, month, day) {
    if (month < 1 || month > 12) {
        return false;
    }
    if (day < 1 || day > 31) {
        return false;
    }
    if ((month == 4 || month == 6 || month == 9 || month == 11) &&
        (day == 31)) {
        return false;
    }
    if (month == 2) {
        var leap = (year % 4 == 0 &&
           (year % 100 != 0 || year % 400 == 0));
        if (day > 29 || (day == 29 && !leap)) {
            return false;
        }
    }
    return true;
}

/*
 * 校验时间是否有效，分隔符冒号，24小时制（小时00-23） 
 * VerifyInput.js 引用
 */
function isTime(str) {

	var a = str.match(/^(\d{2}):(\d{2}):(\d{2})$/);
	if (a == null) {
		return 'false';
	}
	if (a[1] >= 24 || a[2] >= 60 || a[3] >= 60) {
		return 'false';
	}
	
	return '';
	
}

/**
 * 校验电话是否有效，区号-电话号-分机号，长度最大为18位
 * VerifyInput.js 引用
 */
function isPhone(str) {

	if (str.length > 18) {
		return false;
	}
	
	var a = str.split("-");
	if (a.length > 3) {
		return false;
	}
	
	for (var i = 0; i < a.length; i=i+1) {
		if (a[i].length == 0 || isNaN(a[i]) ) {
			return false;
		}
	}
	
	return true;

}

/**
 * 核验身份证是否有效
 * VerifyInput.js 引用
 */
function isIdcard(idcard) {
	var a = idcard.match(/^(\d{14}|\d{17})(\d|[xX])$/);
	if (a == null) {
		return false;
	}
	var birth;
	if (idcard.length == 15) {
		birth  = '19' + idcard.substr(6, 6); 
	} else {
		birth = idcard.substr(6, 8); 
	}
	return isValidDate(new Number(birth.substr(0, 4)),new Number(birth.substr(4, 2)),new Number(birth.substr(6, 2)));
}

function getInfoById(idcard) {
	var info = new Array();
	var a = idcard.match(/^(\d{14}|\d{17})(\d|[xX])$/);
	if (a == null) {
		info[0] = "false";
		info[1] = "false";
		
		return info;
	}
	
	var birth;
	var sex;
	if (idcard.length == 15) {
		birth  = '19' + idcard.substr(6, 6); 
		sex = idcard.substr(14, 1);
	} else {
		birth = idcard.substr(6, 8);
		sex = idcard.substr(16, 1);
	}
	birth = isDate(birth);
	info[0] = birth;
	sex = (new Number(sex) + 1) % 2;
	info[1] = sex;
	
	return info;
}

function getBirthByIdcard(idcard) {
	var a = idcard.match(/^(\d{14}|\d{17})(\d|[xX])$/);
	if (a == null) {
		return false;
	}
	var birth;
	if (idcard.length == 15) {
		birth  = '19' + idcard.substr(6, 6); 
	} else {
		birth = idcard.substr(6, 8); 
	}
	return isValidDate(new Number(birth.substr(0, 4)),new Number(birth.substr(4, 2)),new Number(birth.substr(6, 2)));
}

/**
  * 核验身份证是否有效
  * VerifyInput.js 引用
  */
function isIdcard2(idcard){ 
	var area = {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",
				22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",
				35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",
				44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",
				53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
				65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
	var idcard,Y,JYM; 
	var S,M; 
	var idcard_array = idcard.split(""); 
	if (area[parseInt(idcard.substr(0,2))] == null) {
		return false;
	}
	switch (idcard.length) {
		case 15:
			if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 
				|| ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 
				&& (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 )){ 
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
			} else { 
				ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; 
			} 
			if (ereg.test(idcard)) {
				return true;
			} else {
				return false;
			}
		break; 
		case 18: 
			if (parseInt(idcard.substr(6, 4)) % 4 == 0 
				|| (parseInt(idcard.substr(6, 4)) % 100 == 0 
				&& parseInt(idcard.substr(6, 4))%4 == 0)) {
				// 闰年出生日期的合法性正则表达式
				ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;
			} else {
				// 平年出生日期的合法性正则表达式
				ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;
			}
			if (ereg.test(idcard)) {
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 
				  + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 
				  + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 
				  + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 
				  + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 
				  + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 
				  + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 
				  + parseInt(idcard_array[7]) * 1 
				  + parseInt(idcard_array[8]) * 6 
				  + parseInt(idcard_array[9]) * 3 ; 
				Y = S % 11;
				M = "F"; 
				JYM = "10X98765432"; 
				M = JYM.substr(Y,1);
				if (M == idcard_array[17]) {
					return true; 
				} else {
					return false;
				}
			} else {
				return false;
			}
			break;
		default: 
			return false; 
			break; 
	}
}

function isMobile(mobile){
	var a = mobile.match(/^(1[0-9]{10})$/);
	if (a == null) {
		return false;
	}
	
	return true;
}

function isEMail(email){
	var a = email.match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i);
	if (a == null) {
		return false;
	}
	
	return true;
}

function isPassword(password){
	var a = password.match(/^[A-Za-z0-9]{6,16}$/);
	if (a == null) {
		return false;
	}
	
	return true;
}

function isDateObject(object){
    var strReturn=isDate(object.value);
    if(strReturn=="false"){
        object.value="";
        return false;
    }
    else{
        object.value=strReturn;
        return true;
    }
}
/**
* 把传入的其他类型的日期格式转换为"2003-03-01"的格式
* 目前支持"2003/03/01"和"20030301"的格式转换
* <p><b>Example: </b><p>
* <p>isDate("2003/03/01") returns "2003-03-01"<p>
* <p>isDate("20030301") returns "2003-03-01"<p>
* @param date 日期字符串,格式可以为“yyyy/mm/dd”，也可以为“yyyymmdd”
* @return String "2003-03-01"
*/
function convertDate(vDate){
    var strValue;
    var dateValue;
    var strDate=vDate.value;
    if(strDate.indexOf("/")>=0){
        strValue=strDate.split("/");
        dateValue=strValue[0]+"-"+strValue[1]+"-"+strValue[2];
    }
    else
        dateValue=strDate.substring(0,4)+"-"+strDate.substring(4,6)+"-"+strDate.substring(6,8);
    vDate.value=dateValue;
}
/**
* 把比较两个日期字符串
* <p><b>Example: </b><p>
* <p>compareDate("2002-10-03", "2002-10-03") returns 0<p>
* <p>compareDate("2002-10-03", "2001-10-03") returns 1<p>
* @param date1 日期字符串,格式必须为“yyyy-mm-dd”
* @param date2 日期字符串,格式必须为“yyyy-mm-dd”
* @return date1=date2则返回0 , date1>date2则返回1 , date1<date2则返回2
*/
function compareDate(date1,date2){
    var strValue=date1.split("-");
    var date1Temp=new Date(strValue[0],strValue[1],strValue[2]);
    strValue=date2.split("-");
    var date2Temp=new Date(strValue[0],strValue[1],strValue[2]);
    if(date1Temp.getTime()==date2Temp.getTime())
        return 0;
    else{
        if(date1Temp.getTime()>date2Temp.getTime())
            return 1;
        else
            return 2;
    }
}
/**
* 对格式字符串进行解析,返回一个关联数组
* <p><b>Example: </b><p>
* <p>splitField("Minim:123|Hzm:456|") returns arrayReturn[Minim]=123;arrayReturn[Hzm]=456<p>
* @param record 格式字符串 FieldName:FieldValue|
* @return 关联数组 array[FieldName]=FieldValue
*/
function splitField(record){
    var arrayField=record.split(FIELDDELIMITER);
    var arrayReturn=new Array();
    var i;
    for(i=0;i<arrayField.length-1;i++){
        var arrayNameValuePair=arrayField[i].split(NAMEVALUEDELIMITER); //分割出一对域名和域值
        arrayReturn[arrayNameValuePair[0]]=arrayNameValuePair[1];
    }
    return arrayReturn;
}

/**
* 打开一个窗口
* <p><b>Example: </b><p>
* <p>openWindow("www.163.com", null)<p>
* @param strURL 新窗口的完整路径（URL）或相对路径
* @param strName 指定窗口名，可以为空
* @return 返回新建窗口的句柄
*/
function openWindow(strURL,strName){
    var strWidth  = screen.width-screen.width*0.2;
    var strHeight = screen.height-screen.height*0.3;
    var strTop = screen.height*0.1;
    var strLeft = screen.width*0.1;
    var newWindow = window.open(strURL,strName,'width='+strWidth+',height='+strHeight+',top='+strTop+',left='+strLeft+',toolbar=0,location=0,directories=0,menubar=0,scrollbars=1,resizable=1,status=0');
    newWindow.focus();
    return newWindow;
}
/**
* 分割代码并放在select域里
* <p><b>Example: </b><p>
* <p>setOption("name", "1=Minim&2=Hzm");将在下拉框中看到可选项Minim和Hzm<p>
* @param selectName HTML的select对象名
* @param strValue 包含select对象显示内容的字符串，串的格式必须为: value1=text1&value2=text2，以“&"号分隔
*/
function setOption(selectName,strValue){
    var arrayField=strValue.split("&");
    var i=0;
    fm.all(selectName).length = 0;
    while(i<arrayField.length){
        var option=document.createElement("option");
        var arrayTemp=arrayField[i].split("=");
        var strFieldName=arrayTemp[0];
        var strFieldValue=unescape(arrayTemp[1]);
        option.value=strFieldName;
        option.text=strFieldValue;
        fm.all(selectName).add(option);
        i++;
    }
}

/**
* trans the date style of string to style of date 
* maker:houzw
* time: 20050407	
*/
function getDateByStr(dateStr)
{
	if(dateStr == null || dateStr == "")
		return "";
		
	var str = dateStr;
	var tarr = str.split('-');
	
	var tDate = new Date(tarr[0],tarr[1]-1,tarr[2]);
	return tDate;
}


/**
* 计算两个日期的差,返回差的月数(M)或天数(D) (其中天数除2.29这一天)
* <p><b>Example: </b><p>
* <p>dateDiff("2002-10-1", "2002-10-3", "D") returns "2"<p>
* <p>dateDiff("2002-1-1", "2002-10-3", "M") returns "9"<p>
* @param dateStart 减日期
* @param dateEnd 被减日期
* @param MD 标记，“M”为要求返回差的月数；“D”为要求返回差的天数
* @return 返回两个日期差的月数(M)或天数(D)
*/
function dateDiff(dateStart,dateEnd,MD){
    var i;
    dateStart = getDateByStr(dateStart); // add by houzw 20050407
    dateEnd = getDateByStr(dateEnd);     // add by houzw 20050407
    //按天计算差
    if(MD=="D"){
        var endTm = dateEnd.getTime();
        var startTm = dateStart.getTime();
        var diffDay = (endTm - startTm)/86400000 + 1;
        var dateL;
        for(i=dateStart.getFullYear();i<=dateEnd.getFullYear();i++){
            dateL = new Date(i,1,29); //试着构造一个该年的2月29日
            //判断构造成功否，判断该天介于起始日期和终止日期之间
            if(dateL.getDate()==29 && dateL.getTime()>=startTm && dateL.getTime()<=endTm)
                diffDay--;
        }
        return diffDay;
    }
    else{
        //按月计算差
        var endD = dateEnd.getDate();
        var endM = dateEnd.getMonth();
        var endY = dateEnd.getFullYear();
        var startD = dateStart.getDate();
        var startM = dateStart.getMonth();
        var startY = dateStart.getFullYear();
        if(endD>=startD){
            return (endY-startY)*12 + (endM-startM) + 1;
        }
        else{
            return (endY-startY)*12 + (endM-startM);
        }
    }
}
/**
* 将保存的数值在指定的坐标（ex,ey）中，通过span显示出来
* @param oldValue 保存的数值
* @param ex X坐标
* @param ey Y坐标
*/
function showOldValue(oldValue,ex,ey){
    spanOldValue.innerHTML = oldValue;
    spanOldValue.style.left=ex;
    spanOldValue.style.top=ey;
    spanOldValue.style.display ='';
}
/**
* 通过将span设置为不可见“NONE”隐藏数值
*/
function hideOldValue(){
    spanOldValue.style.display ='none';
}
/**
* 将空值输入域设为给定值，isMulti表示该域是否为多个输入域
* <p><b>Example: </b><p>
* <p>setEmpty([name1,name2], [Minim, Hzm], 2)<p>
* @param FieldName HTML页面的对象名称
* @param FieldValue 要负给对象的值
* @param isMulti 标志传入的是单个对象还是对象数组
*/
function setEmpty(FieldName,FieldValue,isMulti){
    var i = 0;
    if (!isMulti){
        if (fm.all(FieldName).value == "")
            fm.all(FieldName).value = FieldValue;
    }
    else{
        for(i = 0; i< fm.all(FieldName).length; i++){
            theField = fm.all(FieldName)[i];
            if (theField.value.trim() == "" || eval(theField.value) == 0)
                theField.value = FieldValue;
        }
    }
}
/**
* 对小数点后第三位四舍五入
* <p><b>Example: </b><p>
* <p>mathRound(123.456) returns 123.46<p>
* @param intValue 整型数值
* @return 对小数点后第三位四舍五入后的整型数值
*/
function mathRound(x){
    var v = Math.round(x * 100) ;
    v = v/100;
    return v;
}
/**
* 返回一个格式化日期字符串
* <p><b>Example: </b><p>
* <p>dateToString("2002-10-4") returns "2002/10/4"<p>
* @param date 日期型变量
* @return “YYYY/MM/DD”格式化日期字符串
*/
function dateToString(d){
    return  d.getFullYear() +"/"+(d.getMonth()<9?("0"+(d.getMonth()+1)):(d.getMonth()+1)) +"/"+(d.getDate()<10?("0"+d.getDate()):d.getDate());
}
/**
* 在浏览器中弹出一个alert框显示错误信息
* @param strErrMsg 要显示的错误信息字符串
*/
function errorMessage(strErrMsg){
    alert(strErrMsg);
}
/**
* 显示打印窗口，主要是统一打印窗口的样式
* <p><b>Example: </b><p>
* <p>printWindow("../print.jsp", null)<p>
* @param strURL 新窗口的完整路径（URL）或相对路径
* @param strWindowName 指定窗口名，可以为空
* @return 返回新建窗口的句柄
*/
function printWindow(strURL,strWindowName){
    var pageWidth=screen.availWidth-10;
    var pageHeight=screen.availHeight-30;
    if (pageWidth<100){
        pageWidth = 100;
    }
    if (pageHeight<100){
        pageHeight = 100;
    }
    var newWindow = window.open(strURL,strWindowName,'width='+pageWidth+',height='+pageHeight+',top=0,left=0,toolbar=0,location=0,directories=0,menubar=0,scrollbars=1.resizable=1,status=0');
    newWindow.focus();
    return newWindow;
}
/**
* 对输入域是否是日期的校验(日期格式xxxx/xx/xx)，建议修改，与isDate函数合并
* <p><b>Example: </b><p>
* <p>isDateI("2004/10/4") returns true<p>
* <p>isDateI("2004-10-4") returns false<p>
* @param date 格式必须为“YYYY/MM/DD”的日期字符串
* @return 布尔值（true--合法日期, false--非法日期）
*/
function isDateI(date){
    var strValue;
    strValue=date.split("/");
    if(strValue.length!=3) return false;
    if(!isInteger(strValue[0]) || !isInteger(strValue[1]) || !isInteger(strValue[2])) return false;
    var intYear=eval(strValue[0]);
    var intMonth=eval(strValue[1]);
    var intDay=eval(strValue[2]);
    if(intYear<0 || intYear>9999 || intMonth<0 || intMonth>12 || intDay<0 || intDay>31) return false;
    return true;
}
/**
* 比较两个日期字符串(日期格式xxxx/xx/xx)
* <p><b>Example: </b><p>
* <p>compareDateI("2002/10/03", "2002/10/03") returns 0<p>
* <p>compareDateI("2002/10/03", "2001/10/03") returns 1<p>
* @param date1 日期字符串,格式必须为“yyyy/mm/dd”
* @param date2 日期字符串,格式必须为“yyyy/mm/dd”
* @return date1=date2则返回0 , date1>date2则返回1 , date1<date2则返回2
*/
function compareDateI(date1,date2){
    var strValue=date1.split("/");
    var date1Temp=new Date(strValue[0],strValue[1],strValue[2]);
    strValue=date2.split("/");
    var date2Temp=new Date(strValue[0],strValue[1],strValue[2]);
    if(date1Temp.getTime()==date2Temp.getTime())
        return 0;
    else if(date1Temp.getTime()>date2Temp.getTime())
            return 1;
        else
            return 2;
}
/**
*得到当前的系统时间：
*splitOp 为分割符，Example：
*splitOp='-' 则日期格式为 年-月-日
*splitOp='/' 则日期格式为 年/月/日
*splitOp如果为空，则默认是：'-'
*/
function getCurrentDate(splitOp){
    if(splitOp==null) splitOp='-';
    if(splitOp.trim()=='') splitOp='-';
    var SystemDate=new Date();
    var year=SystemDate.getFullYear();
    var month=SystemDate.getMonth()+1;
    if ( month<10 )
     month = "0"+month;
    var day=SystemDate.getDate();
    if (day<10)
      day="0"+day;
    var CurrentDate=year+splitOp+month+splitOp+day;
    return CurrentDate;
}
/**
*得到当前的系统时间：
*splitOp 为分割符，Example：
*splitOp='-' 则日期格式为 年-月
*splitOp='/' 则日期格式为 年/月
*splitOp如果为空，则默认是：'-'
*/
function getCrntYearMonth(splitOp){
    if(splitOp==null) splitOp='';
    if(splitOp.trim()=='') splitOp='';
    var SystemDate=new Date();
    var year=SystemDate.getFullYear();
    var month=SystemDate.getMonth()+1;
    if ( month<10 )
     month = "0"+month;
    var CurrentDate=year+splitOp+month;
    return CurrentDate;
}
/**
* 对图片的显示、隐藏
* @param imgID HTML中可显示图片的对象的ID
* @param stl 控制显示或隐藏的标志，“”为显示，“none”为隐藏
*/
function showImg(imgID,stl){
    document.all(imgID).style.display = stl;
}
/**
* 给新***代码赋值 --代码维护模块专用onblur=setNewCode(this)
* @param field HTML页面的对象名称
*/
function setNewCode(field){
    if(fm.all("new"+field.name).value.trim()==""){
        fm.all("new"+field.name).value = field.value;
    }
}
//对输入域是否是日期的校验，不同的连接字段-，/
function isCodeDate(date){
    var strValue;
    strValue=date.split("/");
    if(strValue.length!=3) return false;
    if(!isInteger(strValue[0]) || !isInteger(strValue[1]) || !isInteger(strValue[2])) return false;
    var intYear=eval(strValue[0]);
    var intMonth=eval(strValue[1]);
    var intDay=eval(strValue[2]);
    if(intYear<0 || intYear>9999 || intMonth<0 || intMonth>12 || intDay<0 || intDay>31) return false;
    return true;
}
/**
* 获取字符串的部分子串，该函数得到c_Str中的第c_i个以c_Split分割的字符串
* <p><b>Example: </b><p>
* <p>getStr("Minim|Hzm|Yt|", "2", "|") returns "Hzm"<p>
* @param c_Str 有分隔规则的字符串
* @param c_i 取第几个分隔子串
* @param c_Split 分隔符
* @return 返回第c_i个分隔子串
*/
function getStr(c_Str , c_i ,c_Split){
    var t_Str1, t_Str2 , t_strOld;
    var i, i_Start, j_End;
    t_Str1 = c_Str;
    t_Str2 = c_Split;
    i = 0;
    try{
        while (i < c_i){
            i_Start = t_Str1.indexOf(t_Str2,0);
            if (i_Start >= 0){
                i = i + 1;
                t_strOld = t_Str1;
                t_Str1 = t_Str1.substring(i_Start+t_Str2.length,t_Str1.length);
            }
            else{
                if (i != c_i - 1){
                    t_Str1="";
                }
                break;
            }
        }
        if (i_Start >= 0)
            t_Str1=t_strOld.substring(0,i_Start);
    }
    catch(ex){
        t_Str1="";
    }
    return t_Str1;
}
/**
* 将字符串解析成为一个数组，该字符串的头部有返回信息
* <p><b>Example: 未测试，请确认例子的正确性</b><p>
* <p>decodeString("Minim|1^Hzm|2^Yt|3") returns "3，Minim,1,Hzm,2,Yt,3"<p>
* @param strValue 需要解析的字符串,通常是查询数据库返回的结果字符串
* @return 如果执行成功，则返回以记录为行，字段为列的二唯数组，如果执行不成功，则返回false
*/
function decodeString(strValue){
    var i,i1,j,j1;
    var strValue;   //存放服务器端返回的代码数据
    var arrField;
    var arrRecord;
    var arrCode = new Array();  //存放初始化变量时用
    var t_Str;
    try{
        arrRecord = strValue.split(RECORDDELIMITER);    //拆分字符串，形成返回的数组
        t_Str = getStr(arrRecord[0],1,FIELDDELIMITER);
        //如果不为0表示服务器端执行发生错误
        if (t_Str!="0"){
            return false;
        }
        i1=arrRecord.length;
        for (i=1;i<i1;i++){
            arrField  = arrRecord[i].split(FIELDDELIMITER); //拆分字符串,将每个纪录拆分为一个数组
            j1=arrField.length;
            arrCode[i-1] = new Array();
            for (j=0;j<j1;j++){
                arrCode[i-1][j] = arrField[j];
            }
        }
    }
    catch(ex){
        return false;
    }
    return arrCode;
}
/**
* 将字符串解析成为一个数组，该字符串的头部没有有返回信息
* <p><b>Example: 未测试，请确认例子的正确性</b><p>
* <p>decodeStringNoHead("Minim|1^Hzm|2^Yt|3") returns "Minim,1,Hzm,2,Yt,3"<p>
* @param strValue 需要解析的字符串,通常是查询数据库返回的结果字符串
* @return 如果执行成功，则返回以记录为行，字段为列的二唯数组，如果执行不成功，则返回false
*/
function decodeStringNoHead(strValue){
    var i,i1,j,j1;
    var strValue;   //存放服务器端返回的代码数据
    var arrField;
    var arrRecord;
    var arrCode = new Array();  //存放初始化变量时用
    var t_Str;
    if(strValue==null || strValue=="")
    return false;
    try{
        arrRecord = strValue.split(RECORDDELIMITER);    //拆分字符串，形成返回的数组
        i1=arrRecord.length;
        for (i=0;i<i1;i++){
            arrField  = arrRecord[i].split(FIELDDELIMITER); //拆分字符串,将每个纪录拆分为一个数组
            j1=arrField.length;
            arrCode[i] = new Array();
            for (j=0;j<j1;j++){
                arrCode[i][j] = arrField[j];
            }
        }
    }
    catch(ex){
        return false;
    }
    return arrCode;
}
/**
* 清空界面上的所有输入栏
* <p><b>Example: </b><p>
* <p>EmptyFormElements()<p>
*/
function emptyFormElements(){
    var formsNum = 0;   //窗口中的FORM数
    var elementsNum = 0;    //FORM中的元素数
    //遍历所有FORM
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //遍历FORM中的所有ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){
            if (window.document.forms[formsNum].elements[elementsNum].type == "text"){
                window.document.forms[formsNum].elements[elementsNum].value = "";
            }
        }
    }
}
/**
* 将界面上的所有输入栏中为"undefined"清空
* <p><b>Example: </b><p>
* <p>EmptyFormElements()<p>
*/
function emptyUndefined(){
    var formsNum = 0;   //窗口中的FORM数
    var elementsNum = 0;    //FORM中的元素数
    //遍历所有FORM
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //遍历FORM中的所有ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){
            if ((window.document.forms[formsNum].elements[elementsNum].value == "undefined" || window.document.forms[formsNum].elements[elementsNum].value == "null") && (window.document.forms[formsNum].elements[elementsNum].type == "text" || window.document.forms[formsNum].elements[elementsNum].type == "textarea")){
                window.document.forms[formsNum].elements[elementsNum].value = "";
            }
        }
    }
}
/**
* 使用一维数组中存放的索引来过滤二维数组
* <p><b>Example: </b><p>
* <p>chooseArray({{1，2}，{3，4}},{0}) returns{{1}，{3}}<p>
* @param dataArray 存放数据的二维数组
* @param filterArray 存放有索引的一维数组
* @return 按一维数组中的索引过滤过的二维数组
*/
function chooseArray(dataArray, filterArray){
    var arrResult = new Array();
    var recordNum, filterNum;
    try{
        for (recordNum=0; recordNum<dataArray.length; recordNum++){
            arrResult[recordNum] = new Array();
            for (filterNum=0; filterNum<filterArray.length; filterNum++){
                arrResult[recordNum].push(dataArray[recordNum][filterArray[filterNum]]);
            }
        }
    }
    catch(ex){
        alert("chooseArray处理出现错误！");
    }
    return arrResult;
}
/**
* 把js文件中的字符转换成特殊字符
*/
function Conversion(strIn){
    var strOut;
    strOut=replace(strIn,"@@Enter","\r\n");
    strIn=strOut;
    strOut=replace(strIn,"@@DouQuot","\"");
    strIn=strOut;
    strOut=replace(strIn,"@@SinQuot","\'");
    return strOut;
}
/**
* 根据代码选择的代码查找并显示名称
*/
function showCodeName(){
}

/**
* 查询指定值在数据库中存不存在
* tableName:表名
* fieldName:字段名
* fieldValue:查询条件
* 如果存在返回true,反之返回false
*/
function checkValueIsExsit(tableName,fieldName,fieldValue){
    var strSQL = "";
    strSQL = "select "+fieldName+" from "+tableName+" where "+fieldName+" = '"+fieldValue+"'";
    return true;
}

/**
* 查询指定字段的值
* tableName:表名
* fieldName:字段名
* fieldValue:查询条件
* returnField:返回字段名
* 如果存在返回名称,反之返回""
*/
function getNameByCode(returnField,tableName,fieldName,fieldValue){
    var strSQL = "";
    if(fieldValue!='' && fieldValue!=null){
    }
    else{
        return("");
    }
}

/**
* 查询指定字段的值
* tSQL:查询条件
* arrReturn:返回值数组
* 如果存在返回名称,反之返回""
*/
function getValuesBySQL(tSQL){
    var strSQL = "";
    if(tSQL!='' && tSQL!=null){
    }
    else{
        return("");
    }
}

/**
* 查询指定字段的值
* tSQL:查询条件
* arrReturn:返回值
* 如果存在返回名称,反之返回""
*/
function getValueBySQL(tSQL){
    var strSQL = "";
    if(tSQL!='' && tSQL!=null){
    }
    else{
        return("");
    }
}

/**
* 从LDCode取得代码对应的名称
* CodeType:字段类型
* Code:代码
* 如果存在返回名称,反之返回""
*/
function getNameFromLDCode(CodeType,Code){
    var tCodeType=CodeType.toLowerCase();
    var tCode=Code;
    if(tCode!='' && tCode!=null){
    }
    else{
        return("");
    }
}

/**
* 检测页面所有数值,百分数，日期,型输入是否合法.
*页面元素id命名遵从规则：数字用Amt或Num结尾，百分数用Rat或Wgh结尾，日期用Date或Day结尾
*不区分大小写
*要求非空加前缀'!'
* <p><b>Example: </b><p>
* @return 布尔值（true--是数字, false--不是数字）
*/
function verifyCheck(){
    var formsNum = 0;   //窗口中的FORM数
    var elementsNum = 0;    //FORM中的元素数
    var elementName;
    var elementId;
    var NullBlank;
    var curElement;
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //遍历FORM中的所有ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){

            curElement=window.document.forms[formsNum].elements[elementsNum];
            elementName=curElement.name.toUpperCase() ;
            elementId= curElement.id.toUpperCase();

            //判断元素是否非空
            NullBlank=NullOrBlank(curElement.value);
            //是否可空
            if (  elementId.indexOf("!")!=-1 )
            {
                if (NullBlank)
                    {
                        alert(curElement.title+"不能为空");
                        curElement.focus();
                        return false;
                    }
                 }
            ////数字
            if (elementId.lastIndexOf("AMT")>=0||elementId.lastIndexOf("NUM")>=0 )
            {
                if (!NullBlank && !isNumeric(curElement.value) )
                {
                       alert(curElement.title+"输入数字有误！");
                        curElement.focus();
                        return false;

                }
            }
            else if (elementId.lastIndexOf("RAT")>=0 ||elementId.lastIndexOf("WGH")>=0){
            //百分数
            if (!NullBlank && !isRat(curElement.value) ){
                        alert(curElement.title+ "输入不是小于100的数字！");
                        curElement.focus();
                        return false;
                }
            }
        else if (elementId.lastIndexOf("DATE")>=0 || elementId.lastIndexOf("DAY")>=0){
            //日期
            if (!NullBlank && isDate(curElement.value)=="false" ){

                        alert(curElement.title+ "输入有误！");
                        curElement.focus();
                        return false;

                }
                }
            }
        }

    return true;
}

function NullOrBlank(sVal){
    if (sVal.trim()=='' || (sVal.trim()==null)) return true;
    return false;
}

//用来判定画面中的所有有值的codeselect框中的内容是否存在
function checkcode(){
    var formsNum = 0;   //窗口中的FORM数
    var elementsNum = 0;    //FORM中的元素数
    var strEvent = "";  //保存onDoubleClick事件代码
    var urlStr = "";
    var sFeatures = "";
    var strCode = "";   //代码选择
    var aCode = ""; //代码
    var bCode = ""; //查询条件值
    var cCode = ""; //查询条件对象
    var strQueryResult = "";    //代码选择的查询结果集
    var arrCode = null; //拆分数组
    var strCodeValue = "";  //代码值
    var cacheFlag = false;  //数据校验标志
    var strCodeSelect = "";
    //寻找主窗口
    var win = searchMainWindow(this);
    if (win == false){ win = this; }
    //遍历所有FORM
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //遍历FORM中的所有ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){
            //寻找代码选择元素
            if ((window.document.forms[formsNum].elements[elementsNum].className == "code")||(window.document.forms[formsNum].elements[elementsNum].className == "codeno")){
                //取出代码值
                strCodeValue = window.document.forms[formsNum].elements[elementsNum].value;
                //空值则不处理
                if (strCodeValue == "") continue;
                //虚拟数据源处理，同样是和数组进行一一匹配，找到符合的就通过，如果全部遍历后仍无匹配则报错
                if (window.document.forms[formsNum].elements[elementsNum].CodeData != null){
                    strQueryResult = window.document.forms[formsNum].elements[elementsNum].CodeData;
                    cacheFlag = false;
                    arrCode = decodeEasyQueryResult(strQueryResult);
                    for(var m=0;m<arrCode.length;m++){
                        if (strCodeValue==arrCode[m][0]){
                            cacheFlag = true;
                            continue;
                        }
                    }
                    if (cacheFlag==false){
                        alert(window.document.forms[formsNum].elements[elementsNum].title+"代码选择错误！");
                        window.document.forms[formsNum].elements[elementsNum].focus();
                        return false;
                        break;
                    }
                }
                else{
                    //得到ondblclick的函数值
                    strEvent = window.document.forms[formsNum].elements[elementsNum].ondblclick;
                    if (strEvent == null) continue;
                    strCode = new String(strEvent);
                    strCode = strCode.substring(strCode.indexOf("showCodeList") + 14);
                    aCode = strCode.substring(0, strCode.indexOf("'")); //得到查询类型
                    //如果有null出现，则表示将会有查询条件（但是还不确定一定有，因为有可能只有强制刷新）
                    if (strCode.indexOf("null")!=-1){
                        strCode = strCode.substring(strCode.indexOf("null") + 5);
                        bCode = strCode.substring(0, strCode.indexOf(","));
                        strCode = strCode.substring(strCode.indexOf(",")+2);
                        cCode = strCode.substring(0, strCode.indexOf("'")); //得到查询条件所要查询的字段
                        if (bCode.indexOf("'")!=-1){
                            bCode = bCode.substring(1,bCode.length-1);  //得到查询条件字段所匹配的值
                        }
                        else{
                            bCode = eval(bCode);
                        }
                        if (bCode == "null") bCode = "";    //出现null的情况
                        if (cCode == "null") cCode = "";
                    }
                    else{
                        bCode = ""; //没有查询条件的情况
                        cCode = "";
                    }
                    strCode = aCode + bCode + cCode;    //拼写查询条件，缓存的存储方式为类型＋匹配字段值+匹配字段
                    //如果内容中有数据，匹配数据和实际数据，无则跳过，可能是因为别的双击带出的
                    if (win.parent.VD.gVCode.getVar(strCode) != false){
                        cacheFlag = false;
                        arrCode = win.parent.VD.gVCode.getVar(strCode);
                        for(var m=0;m<arrCode.length;m++){
                            if (strCodeValue==arrCode[m][0]){
                                cacheFlag = true;
                                continue;
                                //如果发现有匹配的则立即跳出
                            }
                        }
                        if (cacheFlag==false){
                            alert(window.document.forms[formsNum].elements[elementsNum].title+"代码选择错误！");
                            window.document.forms[formsNum].elements[elementsNum].focus();
                            return false;
                            break;
                        }
                    }
                    else{
                        continue;   //无所谓的一句话
                    }
                }
            }
        }
    }
    return true;
    //alert(win.parent.VD.gVCode.getVar('OrganCode010104a.AccountOrgan'));  //得到代码
    //alert(win.parent.VD.gVCode.getVar('OrganCode010104a.AccountOrganSelect'));    //得到html代码
}

//替换字符（回车、单引号、双引号），有待考证
function escapes(ls_string){
    ls_string = replace(ls_string,"\r\n","@E!");
    ls_string = replace(ls_string,"\'","@SQ!");
    ls_string = replace(ls_string,"\"","@DQ!");
    return ls_string;
}

//恢复字符（判定是否用escape转换的，如果不是则用自行的替换函数）
function unescapes(ls_string){
    if((ls_string.indexOf("%u") == -1)&&(ls_string.indexOf("%20") == -1)&&(ls_string.indexOf("%0D%0A") == -1)){
        //估计不需要我做什么操作了，如果使用民生的编码体系，使用民生的体系，好像还是会有一定的问题，可能是没有编译类
        ls_string = replace(ls_string,"@E!","\r\n");
        ls_string = replace(ls_string,"@SQ!","\'");
        ls_string = replace(ls_string,"@DQ!","\"");
    }
    else{
        ls_string = unescape(ls_string);
    }
    return ls_string;
}

//判断指定的字符串是否长度是否为0或者为undefined或者为null
function isEmpty(value){
    return (value == undefined || value == null || value == "");
}

//判断一个浮点数是否接近0
function equalZero(floatValue){
    try {
        var overValue = Math.abs(floatValue - 0);
        if (overValue < ZERO_EQUAL_VALUE){
            return true;
        }
    } catch (ex){
        alert("判断浮点数是否为0失败：" + ex + " 指定的浮点数为：" + floatValue);
    }
    return false;
}

//判断一个指定的数是否大于0
function greatThanZero(floatValue){
    try {
        if (floatValue > 0 && !equalZero(floatValue)){
            return true;
        }
    } catch (ex){
        alert("判断浮点数：" + floatValue + "是否大于0失败，" + ex);
    }
    return false;
}

//格式化浮点数
function formatValue(sValue)
{
  if (sValue.length == 0) return "";
    //Parse the value into a float number
    var iValue = parseFloat(sValue);
    iValue = (Math.round(iValue * 100)) / 100;
    //If the value is not a number, return an error
    if (isNaN(iValue)) {
        return "";
    }
    //Return the value to a string to apply formatting
    sValue = iValue.toString();
    //Fill in zeros (if necessary) to show two digits to the right
    //of the decimal
  if (sValue.indexOf(".") == -1) {
        sValue = sValue + ".00";
    } else {
        if (sValue.indexOf(".") == sValue.length - 1) {
            sValue = sValue + "00";
        } else if (sValue.indexOf(".") == sValue.length - 2) {
            sValue = sValue + "0";
        }
    }

    //Add commas if necessary
    //if (sValue.indexOf(".") > 3) {
    //  sValue = sValue.substring(0,sValue.indexOf(".") - 3) + ","
    //        + sValue.substring(sValue.indexOf(".") - 3,sValue.length);
    //}

    return sValue;
}

//parseInt 10进制
function myParseInt(param)
{
    return parseInt(param,10);
}


//侯志伟
//会计月度加num
function getYearMonthAdd(tYearMonth,tAddNum)
{
    var finalMonth = 12;
    var iniMonth ;
    var iniYear = tYearMonth.substring(0,4);
    var strTmp = tYearMonth.substring(4);
    if(strTmp == "JS")
    {
        iniMonth = myParseInt(finalMonth) + 1;
    }else
    {
        iniMonth = myParseInt(strTmp);
    }

    iniMonth = myParseInt(iniMonth) + myParseInt(tAddNum);

    var iniYearAdd = myParseInt(iniMonth)/(myParseInt(finalMonth)+1);
    var iniYear = myParseInt(iniYear) + myParseInt(iniYearAdd);

    iniMonth = myParseInt(iniMonth) - myParseInt(iniYearAdd) *(myParseInt(finalMonth)+1);

    if(iniMonth <= 0 || (iniMonth > 0 && finalMonth != "12"))
    {
        iniYear = myParseInt(iniYear) - 1;
        iniMonth = myParseInt(iniMonth) + myParseInt(finalMonth) + 1;
    }
    var sNextYearMonth = iniYear + "";
    var sNextMonth = iniMonth + "";
    if(sNextMonth.length<=1)
    {
        sNextMonth = "0" + sNextMonth;
    }
    if(iniMonth == (myParseInt(finalMonth) + 1))
        sNextMonth = "JS";

    sNextYearMonth = sNextYearMonth + "" + sNextMonth;
    return sNextYearMonth;
}

//penpen    格式化数字（财务格式）###
function formatAccount(sValue)
{
    try {
        if (sValue.length == 0) return "";
        var rValue=formatValue(sValue);
        var tLength=rValue.length;
        var rSign="";  //正负号
        var rInt=rValue.substring(0,tLength-3);  //整数部分
        var rDecimal=rValue.substring(tLength-3); //小数部分

        if(rInt<0){  //如果是负，则把负号保留起来
            rSign=rInt.substring(0,1);
            rInt=rInt.substring(1);
        }

        if(rInt.length<=3) return rValue;  //如果整数部分长度小于3，则直接返回

        var tThree=rInt.length/3;
        var tReturn="";
        for(var i=1;i<=tThree;i++){
            tReturn=","+rInt.substring(rInt.length-3*i,rInt.length-3*(i-1))+tReturn;
        }

        if(parseInt(tThree)==tThree)
            tReturn=tReturn.substring(1);
        else{
            tReturn=""+rInt.substring(0,rInt.length-(i-1)*3)+tReturn;
        }
        tReturn=rSign+tReturn+rDecimal;

    } catch (ex){
        alert("转换：" + sValue + "为会计格式失败：" + ex);
    }
    return tReturn;
}

/**
 * 在框架中打开查询页面
 * @param queryURI 查询页面的URI，可以带参数
 */
function showQueryPage(queryURI){
    var encodedURI = encodeURIComponent(queryURI);
    var uri = "QueryMain.jsp?queryURI=" + encodedURI;
    openWindow(uri);
}

/**
 *取某单位的初始化会计月度
 */
function getInitYearMonth(tCenterCode,tAccBookType,tAccBookCode)
{
    var sql="select yearmonth from accmonthtrace "
       +" where centercode='"+tCenterCode+"' "
       +" and accbooktype='"+tAccBookType+"' "
       +" and accbookcode='"+tAccBookCode+"' "
       +" and accmonthstat='2' ";
    var res = easyQueryVer3(sql,1,0,1);
    var resArr = decodeEasyQueryResult(res);
    if(resArr != null){
        alert("已经初始化确认,请继续其他功能!");
        window.location="about:blank";
        return "";
    }else{
        sql="select yearmonth from accmonthtrace "
            +" where centercode='"+tCenterCode+"' "
            +" and accbooktype='"+tAccBookType+"' "
            +" and accbookcode='"+tAccBookCode+"' "
            +" and accmonthstat='1' ";
        res = easyQueryVer3(sql,1,0,1);
        resArr = decodeEasyQueryResult(res);
        if(resArr==null){
            alert("尚未指定初始化月度,请输入初始化月度!");
            return "";
        }else{
            return resArr[0][0];
        }
    }
}

/*
校验输入的email地址是否合法
*/
function emailCheck (emailStr) {
/* The following pattern is used to check if the entered e-mail address
   fits the user@domain format.  It also is used to separate the username
   from the domain. */
var emailPat=/^(.+)@(.+)$/;
/* The following string represents the pattern for matching all special
   characters.  We don't want to allow special characters in the address. 
   These characters include ( ) < > @ , ; : \ " . [ ]    */
var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
/* The following string represents the range of characters allowed in a 
   username or domainname.  It really states which chars aren't allowed. */
var validChars="\[^\\s" + specialChars + "\]";
/* The following pattern applies if the "user" is a quoted string (in
   which case, there are no rules about which characters are allowed
   and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
   is a legal e-mail address. */
var quotedUser="(\"[^\"]*\")";
/* The following pattern applies for domains that are IP addresses,
   rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
   e-mail address. NOTE: The square brackets are required. */
var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
/* The following string represents an atom (basically a series of
   non-special characters.) */
var atom=validChars + '+';
/* The following string represents one word in the typical username.
   For example, in john.doe@somewhere.com, john and doe are words.
   Basically, a word is either an atom or quoted string. */
var word="(" + atom + "|" + quotedUser + ")";
// The following pattern describes the structure of the user
var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
/* The following pattern describes the structure of a normal symbolic
   domain, as opposed to ipDomainPat, shown above. */
var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");

/* Finally, let's start trying to figure out if the supplied address is
   valid. */
/* Begin with the coarse pattern to simply break up user@domain into
   different pieces that are easy to analyze. */
var matchArray=emailStr.match(emailPat);
if (matchArray==null) {
  /* Too many/few @'s or something; basically, this address doesn't
     even fit the general mould of a valid e-mail address. */
	alert("email地址不合法！");
	return false;
}
var user=matchArray[1];
var domain=matchArray[2];

// See if "user" is valid 
if (user.match(userPat)==null) {
    // user is not valid
    alert("email用户名不合法！");
    return false;
}

/* if the e-mail address is at an IP address (as opposed to a symbolic
   host name) make sure the IP address is valid. */
var IPArray=domain.match(ipDomainPat);
if (IPArray!=null) {
    // this is an IP address
	  for (var i=1;i<=4;i++) {
	    if (IPArray[i]>255) {
	        alert("email IP地址不合法！");
		return false;
	    }
    }
    return true;
}

// Domain is symbolic name
var domainArray=domain.match(domainPat);
if (domainArray==null) {
	alert("email主机名不合法！");
    return false;
}

/* Now we need to break up the domain to get a count of how many atoms
   it consists of. */
var atomPat=new RegExp(atom,"g");
var domArr=domain.match(atomPat);
var len=domArr.length;
if (domArr[domArr.length-1].length<2 || 
    domArr[domArr.length-1].length>3) {
   // the address must end in a two letter or three letter word.
   alert("email地址必须以3字母的域或者2字母的国家结尾！");
   return false;
}

// Make sure there's a host name preceding the domain.
if (len<2) {
   var errStr="emai地址缺少域名！";
   alert(errStr);
   return false;
}

// If we've gotten this far, everything's valid!
return true;
}

//新增试用功能 简单查询
//add by dengqiao
function getValueBySimpQry(fieldName,code){
	var sFeatures = "status:no;help:0;close:0;dialogWidth:0px;dialogHeight:0px;resizable=1";
	var urlStr = "../common/simpleQuery/SimpleQueryWindow.jsp?fieldName="+fieldName+"&code=" + code;
	var strQueryResult = window.showModalDialog(urlStr, "", sFeatures);
	if(strQueryResult==false)return "";
	else return strQueryResult;
}


/*新增与日期相关的函数************************************************/

//取上一天日期
//@param dDate: Date对象
function getDateOfYesterday(dDate){
	var diffrence = 1000*60*60*24;//一天的毫秒数
	var today = Date.parse(dDate);
	var yesterday = today-diffrence;
	return new Date(yesterday);
}

//取下一天日期
//@param dDate: Date对象
function getDateOfTomorrow(dDate){
	var diffrence = 1000*60*60*24;//一天的毫秒数
	var today = Date.parse(dDate);
	var yesterday = today+diffrence;
	return new Date(yesterday);
}

//取得一个月的天数
//@month : number of the month;0-11
//@year : number of the year;such as 2005
function getDaysOfMonth(month,year){
	var monarr = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monarr[1] = "29";
	return monarr[month];
}

//取上月的日期
//@param dDate: Date对象
function getDateOfLastMonth(dDate){
	var tMonth = dDate.getMonth();
	var tYear = dDate.getFullYear();
	tMonth--;
	if(tMonth<0){
		tMonth+=12;
		tYear-=1;
	}
	dDate.setMonth(tMonth);
	dDate.setYear(tYear);
	return dDate;
}

//取下月的日期
//@param dDate: Date对象
function getDateOfNextMonth(dDate){
	var tMonth = dDate.getMonth();
	var tYear = dDate.getFullYear();
	tMonth++;
	if(tMonth>11){
		tMonth-=12;
		tYear+=1;
	}
	dDate.setMonth(tMonth);
	dDate.setYear(tYear);
	return dDate;
}

//取上一季的日期
function getDateOfLastQuater(dDate){
	var tMonth = dDate.getMonth();
	var tYear = dDate.getFullYear();
	tMonth-=3;
	if(tMonth<0){
		tMonth+=12;
		tYear-=1;
	}
	dDate.setMonth(tMonth);
	dDate.setYear(tYear);
	return dDate;
}
//取下一季的日期
function getDateOfNextQuater(dDate){
	var tMonth = dDate.getMonth();
	var tYear = dDate.getFullYear();
	tMonth+=3;
	if(tMonth>11){
		tMonth-=12;
		tYear+=1;
	}
	dDate.setMonth(tMonth);
	dDate.setYear(tYear);
	return dDate;
}

//取上一年的日期
//@param dDate: Date对象
function getDateOfLastYear(dDate){
	var tYear = dDate.getFullYear();
	tYear--;
	dDate.setYear(tYear);
	return dDate;
}

//取下一年的日期
//@param dDate: Date对象
function getDateOfNextYear(dDate){
	var tYear = dDate.getFullYear();
	tYear++;
	dDate.setYear(tYear);
	return dDate;
}

//取得可拼接的日期
//@param dDate: Date对象
function getConcatableDate(dDate){
	var tDate = dDate.getDate();
	return tDate<10?"0"+tDate:tDate;
}
//取得可拼接的月份
//@param dDate: Date对象
function getConcatableMonth(dDate){
	var tMonth = dDate.getMonth()+1;
	return tMonth<10?"0"+tMonth:tMonth;
}
//取得可拼接的年份
//@param dDate: Date对象
function getConcatableYear(dDate){
	return dDate.getFullYear();
}
//取得格式化的日期，无连接符
//@param dDate: Date对象
function getFormattedDate(dDate){
	var s = "";
	return s.concat(getConcatableYear(dDate),getConcatableMonth(dDate),getConcatableDate(dDate));
}

/*以上为新增与日期相关的函数******************************************/

/***************
 * 使用DOM加载JS
 **************/
function addJavaScript(file){
	var head = document.getElementsByTagName('head').item(0);
	var script = document.createElement('script');
	script.src = file;
	script.type = 'text/javascript';
	head.appendChild(script);
}
/****************
 * 使用DOM加载CSS
 ***************/
function addCascadingStyleSheet(file){
	var head = document.getElementsByTagName('head').item(0);
	var link = document.createElement('link');
	link.href = file;
	link.rel = "stylesheet";
	link.type = 'text/css';
	head.appendChild(link);
}

/*****************
*补零操作*
*****************/
function addZero(inputObj,size){ //inputObj内容 size长度
	var no=inputObj;
	if(no.length<size&&no.length>0){
		var i=0; 
		var j=size-no.length;
		for(i=0;i<j;i++){
			no="0"+no;
		}
	}
	return no;
}

function verifyTextAreaMaxLength(textarea, maxlength) {
	if (textarea.value.length > maxlength){
        $().openDialog("alert", true, true, "多行文本输入框中字数超过规定的最大长度！", "提示信息");
        textarea.value = textarea.value.substring(0, maxlength);
    }
}

function changeImg(imgObj) {
	var imgSrc;
	if (typeof imgObj == "undefined") {
		imgSrc = $("#imgObj");
	} else {
		imgSrc = $("#" + imgObj);
	}
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgurl(src));
}  
//时间戳  
//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳  
function chgurl(url){  
    var timestamp = (new Date()).valueOf();
    url = url.substring(0, url.indexOf(".action") + 7);
    if ((url.indexOf("&") >= 0)) {  
        url = url + "&timestamp=" + timestamp;  
    } else {
        url = url + "?timestamp=" + timestamp;  
    }
    return url;  
}

function removeHighlight(jObj) {
	jObj.removeClass("ui-state-highlight");
}

function addHighlight(jObj) {
	jObj.val("").addClass("ui-state-highlight").focus();
}

function showProgress(jBar, jMsg, msg) {
	if (msg != null && msg != "") {
		jMsg.text(msg);
	}
	jBar.show();
}

function hideProgress(jBar) {
	jBar.hide();
}

//进行Iframe的自动撑开,让所有父页面的Iframe都自动适应包含页高度   
function autoHeight1(frame, height){
	try {
		var doc = document,   
		p = window;
		if (typeof height == "undefined") {
			height = 0;
		}
		while (p = p.parent) {
			if (p == top){
	            break;   
	        }   
		}
		p = $(p.document).find("#tdContent");
		p.height(500);
		frame.height(200);
		var thisheight = $(doc).height();
		if (height + thisheight > 500) {
			p.height(height + thisheight);
		}
		if (thisheight > 200) {
			frame.height(thisheight);
		}
	} catch (e) {
	}
}

//进行Iframe的自动撑开,让所有父页面的Iframe都自动适应包含页高度   
function autoHeight(height){
	try {
		var doc = document,   
		p = window;
		if (typeof height == "undefined") {
			height = 0;
		}
		while (p = p.parent) {
			if (p == top){
	            break;   
	        }   
		}
		p = $(p.document).find("#tdContent");
		p.height(500);
		var thisheight = $(doc).height();
		if (height + thisheight > 500) {
			p.height(height + thisheight);
		}
	} catch (e) {
	}
}

/*
 *功能:打开模态窗口
 *参数:
 */
function showModel(url, width, height){
	// 如果showModalDialog的url已经带参数了，那么只需把refreshRandomSerialNo="+Math.random()追加到已有参数后面
	if((url.match(".action\\?") || url.match(".jsp\\?")) != null){
		url = url+"&refreshRandomSerialNo="+Math.random();
	}else{
		url = url+"?refreshRandomSerialNo="+Math.random();
	}
	
	var style = "help=0;center=1;status:yes;scroll=1;";
	if(width != undefined && height != undefined){
		style += "dialogWidth:" + width + "px;";
		style += "dialogHeight:" + height + "px;";
	} else {
		style += "dialogTop:0;dialogWidth:" + (window.screen.availWidth) + "px;";
		style += "dialogHeight=" + (window.screen.availHeight) + "px;";
	}
	
	return window.showModalDialog(url, window, style);  
}

function changeWords(id,num) {
	var len = document.getElementById(id).value.length;
	var wordsId = id+"Words";
	if (len > num) {
		var value = document.getElementById(id).value;
		document.getElementById(id).value = value.substring(0,num);
		document.getElementById(wordsId).innerHTML = num;
		alert("超过限定字数！");
	} else {
		document.getElementById(wordsId).innerHTML = len;
	}
}

// 上移
function upOption(obj) {
	//最上面的一个不需要移动，所以直接从i=1开始
	for (var i = 1; i < obj.length; i++) {
		if (obj.options[i].selected) {
			if (!obj.options.item(i-1).selected) {
				var selText = obj.options[i].text;
				var selValue = obj.options[i].value;
				obj.options[i].text = obj.options[i-1].text;
				obj.options[i].value = obj.options[i-1].value;
				obj.options[i].selected = false;
				obj.options[i-1].text = selText;
				obj.options[i-1].value = selValue;
				obj.options[i-1].selected=true;
			}
		}
	}
}

// 下移
function downOption(obj) {
	// 向下移动，最后一个不需要处理，所以直接从倒数第二个开始
	for (var i = obj.length - 2 ; i >= 0; i--) {
		if (obj.options[i].selected) {
			if (!obj.options[i+1].selected) {
				var selText = obj.options[i].text;
				var selValue = obj.options[i].value;
		    	obj.options[i].text = obj.options[i+1].text;
		    	obj.options[i].value = obj.options[i+1].value;
		    	obj.options[i].selected = false;
		    	obj.options[i+1].text = selText;
		    	obj.options[i+1].value = selValue;
		    	obj.options[i+1].selected=true;
		    }
		}
	}
}

// 删除
function deleteOption(obj) {
	for (var i = 0; i < obj.length; i++) {
		if (obj.options[i].selected) {
			obj.options[i] = null ; 
		}
	}
}