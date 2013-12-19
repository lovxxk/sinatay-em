/**
* <p>��������: Common.js</p>
* <p>������: ���ú����������� </p>
* <p>ע�⣺���еı�������ΪVAR����JAVA�б�ʾΪSTRING<p>
*/
//���ñ���
/** ���ڷָ���,��ʼֵ=":" */
var DATEVALUEDELIMITER=":";
/** ��������ֵ�ķָ���,��ʼֵ=":" */
var NAMEVALUEDELIMITER=":";
/** ��ʼֵ=":" */
var SBCCASECOLON="��";
/** ��֮��ķָ���,��ʼֵ="|" */
var FIELDDELIMITER="|";
/** ��ʼֵ="��" */
var SBCCASEVERTICAL="��";
/** ��¼֮��ķָ���,��ʼֵ="^" */
var RECORDDELIMITER="^";

/** ��Ϊ�൱��0���� */
var ZERO_EQUAL_VALUE = 1E-6;

/**
* ����ͼƬ
* <p><b>Example: </b><p>
* <p>function changeImage(image,gif)<p>
* @param image ���ͼƬ�Ķ�����ܻ�ҳ��
* @param gif ͼƬ��ȫ·��
*/
function changeImage(image,gif){
    Image.src=gif;
}
/**
* �滻�ַ�������
* <p><b>Example: </b><p>
* <p>replace("Minim123Minim", "123", "Minim") returns "MinimMinimMinim"<p>
* @param strExpression �ַ������ʽ
* @param strFind ���滻�����ַ���
* @param strReplaceWith �滻��Ŀ���ַ���������strReplaceWith�ַ����滻��strFind
* @return �����滻����ַ������ʽ
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
* �滻�ַ�������
* @param initStr �ַ������ʽ
* @param initExp ���滻�����ַ���
* @param repExp �滻��Ŀ���ַ���������repExp�ַ����滻��initExp
* @return �����滻����ַ������ʽ
*/
function replaceReg(initStr, initExp, repExp)
{
    var reg=new RegExp(initExp,"g"); //��������RegExp����

    return initStr.replace(reg, repExp);
}
/**
* ȥ���ַ���ͷβ�ո�
* <p><b>Example: </b><p>
* <p>trim(" Minim ") returns "Minim"<p>
* @param strValue �ַ������ʽ
* @return ͷβ�޿ո���ַ������ʽ
*/
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
/**
* ���������Ƿ���������У��
* VerifyInput.js ����
* <p><b>Example: </b><p>
* <p>isInteger("Minim") returns false<p>
* <p>isInteger("123") returns true<p>
* @param strValue ������ֵ���ʽ���ַ������ʽ
* @return ����ֵ��true--������, false--����������
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
* ���������Ƿ������ֵ�У��
* <p><b>Example: </b><p>
* <p>isNumeric("Minim") returns false<p>
* <p>isNumeric("123.1") returns true<p>
* @param strValue ������ֵ���ʽ���ַ������ʽ
* @return ����ֵ��true--������, false--�������֣�
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
* ���������Ƿ��ǰٷ�����У��
* <p><b>Example: </b><p>
* <p>isRat("123.1") returns false<p>
* @param strValue ������ֵ���ʽ���ַ������ʽ
* @return ����ֵ��true--������, false--�������֣�
*/
function isRat(strValue){

    if ( isNumeric(strValue) )
      if ( eval(strValue)>=0 && eval(strValue)<=100 )
         return true;
    return false;
}
/**
* ���ڵĺϷ��ж�
* <p><b>Example: </b><p>
* <p>isLegalDate("2002", "10", "03") returns true<p>
* <p>isLegalDate("Minim", "10", "03") returns false<p>
* @param year ����ַ���
* @param month �·��ַ���
* @param day �����ַ���
* @return ����ֵ��true--�Ϸ�����, false--�Ƿ����ڣ�
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
* ���������Ƿ������ڵ�У��
* VerifyInput.js ����
* <p><b>Example: </b><p>
* <p>isDate("2002-10-03") returns true<p>
* <p>isDate("2002/10/03") returns true<p>
* <p>isDate("20021003") returns true<p>
* @param date �����ַ���,��ʽ����Ϊ��yyyy-mm-dd��,Ҳ����Ϊ��yyyy/mm/dd����Ҳ����Ϊ��yyyymmdd��
* @return String��"yyyy-mm-dd"--�Ϸ�����, "false"--�Ƿ����ڣ�
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
* �Ƿ�Ϊ��Ч���ڵ�У��
* <p><b>Example: </b><p>
* <p>isDate(2002,9,30) returns true<p>
* <p>isDate(2002,9,31) returns false<p>
* @param year ����ֵ, month ����ֵ, day ����ֵ
* @return boolean��true - �Ϸ�����, false - �Ƿ����ڣ�
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
 * У��ʱ���Ƿ���Ч���ָ���ð�ţ�24Сʱ�ƣ�Сʱ00-23�� 
 * VerifyInput.js ����
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
 * У��绰�Ƿ���Ч������-�绰��-�ֻ��ţ��������Ϊ18λ
 * VerifyInput.js ����
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
 * �������֤�Ƿ���Ч
 * VerifyInput.js ����
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
  * �������֤�Ƿ���Ч
  * VerifyInput.js ����
  */
function isIdcard2(idcard){ 
	var area = {11:"����",12:"���",13:"�ӱ�",14:"ɽ��",15:"���ɹ�",21:"����",
				22:"����",23:"������",31:"�Ϻ�",32:"����",33:"�㽭",34:"����",
				35:"����",36:"����",37:"ɽ��",41:"����",42:"����",43:"����",
				44:"�㶫",45:"����",46:"����",50:"����",51:"�Ĵ�",52:"����",
				53:"����",54:"����",61:"����",62:"����",63:"�ຣ",64:"����",
				65:"�½�",71:"̨��",81:"���",82:"����",91:"����"};
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
				// ����������ڵĺϷ���������ʽ
				ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;
			} else {
				// ƽ��������ڵĺϷ���������ʽ
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
* �Ѵ�����������͵����ڸ�ʽת��Ϊ"2003-03-01"�ĸ�ʽ
* Ŀǰ֧��"2003/03/01"��"20030301"�ĸ�ʽת��
* <p><b>Example: </b><p>
* <p>isDate("2003/03/01") returns "2003-03-01"<p>
* <p>isDate("20030301") returns "2003-03-01"<p>
* @param date �����ַ���,��ʽ����Ϊ��yyyy/mm/dd����Ҳ����Ϊ��yyyymmdd��
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
* �ѱȽ����������ַ���
* <p><b>Example: </b><p>
* <p>compareDate("2002-10-03", "2002-10-03") returns 0<p>
* <p>compareDate("2002-10-03", "2001-10-03") returns 1<p>
* @param date1 �����ַ���,��ʽ����Ϊ��yyyy-mm-dd��
* @param date2 �����ַ���,��ʽ����Ϊ��yyyy-mm-dd��
* @return date1=date2�򷵻�0 , date1>date2�򷵻�1 , date1<date2�򷵻�2
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
* �Ը�ʽ�ַ������н���,����һ����������
* <p><b>Example: </b><p>
* <p>splitField("Minim:123|Hzm:456|") returns arrayReturn[Minim]=123;arrayReturn[Hzm]=456<p>
* @param record ��ʽ�ַ��� FieldName:FieldValue|
* @return �������� array[FieldName]=FieldValue
*/
function splitField(record){
    var arrayField=record.split(FIELDDELIMITER);
    var arrayReturn=new Array();
    var i;
    for(i=0;i<arrayField.length-1;i++){
        var arrayNameValuePair=arrayField[i].split(NAMEVALUEDELIMITER); //�ָ��һ����������ֵ
        arrayReturn[arrayNameValuePair[0]]=arrayNameValuePair[1];
    }
    return arrayReturn;
}

/**
* ��һ������
* <p><b>Example: </b><p>
* <p>openWindow("www.163.com", null)<p>
* @param strURL �´��ڵ�����·����URL�������·��
* @param strName ָ��������������Ϊ��
* @return �����½����ڵľ��
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
* �ָ���벢����select����
* <p><b>Example: </b><p>
* <p>setOption("name", "1=Minim&2=Hzm");�����������п�����ѡ��Minim��Hzm<p>
* @param selectName HTML��select������
* @param strValue ����select������ʾ���ݵ��ַ��������ĸ�ʽ����Ϊ: value1=text1&value2=text2���ԡ�&"�ŷָ�
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
* �����������ڵĲ�,���ز������(M)������(D) (����������2.29��һ��)
* <p><b>Example: </b><p>
* <p>dateDiff("2002-10-1", "2002-10-3", "D") returns "2"<p>
* <p>dateDiff("2002-1-1", "2002-10-3", "M") returns "9"<p>
* @param dateStart ������
* @param dateEnd ��������
* @param MD ��ǣ���M��ΪҪ�󷵻ز����������D��ΪҪ�󷵻ز������
* @return �����������ڲ������(M)������(D)
*/
function dateDiff(dateStart,dateEnd,MD){
    var i;
    dateStart = getDateByStr(dateStart); // add by houzw 20050407
    dateEnd = getDateByStr(dateEnd);     // add by houzw 20050407
    //��������
    if(MD=="D"){
        var endTm = dateEnd.getTime();
        var startTm = dateStart.getTime();
        var diffDay = (endTm - startTm)/86400000 + 1;
        var dateL;
        for(i=dateStart.getFullYear();i<=dateEnd.getFullYear();i++){
            dateL = new Date(i,1,29); //���Ź���һ�������2��29��
            //�жϹ���ɹ����жϸ��������ʼ���ں���ֹ����֮��
            if(dateL.getDate()==29 && dateL.getTime()>=startTm && dateL.getTime()<=endTm)
                diffDay--;
        }
        return diffDay;
    }
    else{
        //���¼����
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
* ���������ֵ��ָ�������꣨ex,ey���У�ͨ��span��ʾ����
* @param oldValue �������ֵ
* @param ex X����
* @param ey Y����
*/
function showOldValue(oldValue,ex,ey){
    spanOldValue.innerHTML = oldValue;
    spanOldValue.style.left=ex;
    spanOldValue.style.top=ey;
    spanOldValue.style.display ='';
}
/**
* ͨ����span����Ϊ���ɼ���NONE��������ֵ
*/
function hideOldValue(){
    spanOldValue.style.display ='none';
}
/**
* ����ֵ��������Ϊ����ֵ��isMulti��ʾ�����Ƿ�Ϊ���������
* <p><b>Example: </b><p>
* <p>setEmpty([name1,name2], [Minim, Hzm], 2)<p>
* @param FieldName HTMLҳ��Ķ�������
* @param FieldValue Ҫ���������ֵ
* @param isMulti ��־������ǵ��������Ƕ�������
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
* ��С��������λ��������
* <p><b>Example: </b><p>
* <p>mathRound(123.456) returns 123.46<p>
* @param intValue ������ֵ
* @return ��С��������λ����������������ֵ
*/
function mathRound(x){
    var v = Math.round(x * 100) ;
    v = v/100;
    return v;
}
/**
* ����һ����ʽ�������ַ���
* <p><b>Example: </b><p>
* <p>dateToString("2002-10-4") returns "2002/10/4"<p>
* @param date �����ͱ���
* @return ��YYYY/MM/DD����ʽ�������ַ���
*/
function dateToString(d){
    return  d.getFullYear() +"/"+(d.getMonth()<9?("0"+(d.getMonth()+1)):(d.getMonth()+1)) +"/"+(d.getDate()<10?("0"+d.getDate()):d.getDate());
}
/**
* ��������е���һ��alert����ʾ������Ϣ
* @param strErrMsg Ҫ��ʾ�Ĵ�����Ϣ�ַ���
*/
function errorMessage(strErrMsg){
    alert(strErrMsg);
}
/**
* ��ʾ��ӡ���ڣ���Ҫ��ͳһ��ӡ���ڵ���ʽ
* <p><b>Example: </b><p>
* <p>printWindow("../print.jsp", null)<p>
* @param strURL �´��ڵ�����·����URL�������·��
* @param strWindowName ָ��������������Ϊ��
* @return �����½����ڵľ��
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
* ���������Ƿ������ڵ�У��(���ڸ�ʽxxxx/xx/xx)�������޸ģ���isDate�����ϲ�
* <p><b>Example: </b><p>
* <p>isDateI("2004/10/4") returns true<p>
* <p>isDateI("2004-10-4") returns false<p>
* @param date ��ʽ����Ϊ��YYYY/MM/DD���������ַ���
* @return ����ֵ��true--�Ϸ�����, false--�Ƿ����ڣ�
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
* �Ƚ����������ַ���(���ڸ�ʽxxxx/xx/xx)
* <p><b>Example: </b><p>
* <p>compareDateI("2002/10/03", "2002/10/03") returns 0<p>
* <p>compareDateI("2002/10/03", "2001/10/03") returns 1<p>
* @param date1 �����ַ���,��ʽ����Ϊ��yyyy/mm/dd��
* @param date2 �����ַ���,��ʽ����Ϊ��yyyy/mm/dd��
* @return date1=date2�򷵻�0 , date1>date2�򷵻�1 , date1<date2�򷵻�2
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
*�õ���ǰ��ϵͳʱ�䣺
*splitOp Ϊ�ָ����Example��
*splitOp='-' �����ڸ�ʽΪ ��-��-��
*splitOp='/' �����ڸ�ʽΪ ��/��/��
*splitOp���Ϊ�գ���Ĭ���ǣ�'-'
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
*�õ���ǰ��ϵͳʱ�䣺
*splitOp Ϊ�ָ����Example��
*splitOp='-' �����ڸ�ʽΪ ��-��
*splitOp='/' �����ڸ�ʽΪ ��/��
*splitOp���Ϊ�գ���Ĭ���ǣ�'-'
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
* ��ͼƬ����ʾ������
* @param imgID HTML�п���ʾͼƬ�Ķ����ID
* @param stl ������ʾ�����صı�־������Ϊ��ʾ����none��Ϊ����
*/
function showImg(imgID,stl){
    document.all(imgID).style.display = stl;
}
/**
* ����***���븳ֵ --����ά��ģ��ר��onblur=setNewCode(this)
* @param field HTMLҳ��Ķ�������
*/
function setNewCode(field){
    if(fm.all("new"+field.name).value.trim()==""){
        fm.all("new"+field.name).value = field.value;
    }
}
//���������Ƿ������ڵ�У�飬��ͬ�������ֶ�-��/
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
* ��ȡ�ַ����Ĳ����Ӵ����ú����õ�c_Str�еĵ�c_i����c_Split�ָ���ַ���
* <p><b>Example: </b><p>
* <p>getStr("Minim|Hzm|Yt|", "2", "|") returns "Hzm"<p>
* @param c_Str �зָ�������ַ���
* @param c_i ȡ�ڼ����ָ��Ӵ�
* @param c_Split �ָ���
* @return ���ص�c_i���ָ��Ӵ�
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
* ���ַ���������Ϊһ�����飬���ַ�����ͷ���з�����Ϣ
* <p><b>Example: δ���ԣ���ȷ�����ӵ���ȷ��</b><p>
* <p>decodeString("Minim|1^Hzm|2^Yt|3") returns "3��Minim,1,Hzm,2,Yt,3"<p>
* @param strValue ��Ҫ�������ַ���,ͨ���ǲ�ѯ���ݿⷵ�صĽ���ַ���
* @return ���ִ�гɹ����򷵻��Լ�¼Ϊ�У��ֶ�Ϊ�еĶ�Ψ���飬���ִ�в��ɹ����򷵻�false
*/
function decodeString(strValue){
    var i,i1,j,j1;
    var strValue;   //��ŷ������˷��صĴ�������
    var arrField;
    var arrRecord;
    var arrCode = new Array();  //��ų�ʼ������ʱ��
    var t_Str;
    try{
        arrRecord = strValue.split(RECORDDELIMITER);    //����ַ������γɷ��ص�����
        t_Str = getStr(arrRecord[0],1,FIELDDELIMITER);
        //�����Ϊ0��ʾ��������ִ�з�������
        if (t_Str!="0"){
            return false;
        }
        i1=arrRecord.length;
        for (i=1;i<i1;i++){
            arrField  = arrRecord[i].split(FIELDDELIMITER); //����ַ���,��ÿ����¼���Ϊһ������
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
* ���ַ���������Ϊһ�����飬���ַ�����ͷ��û���з�����Ϣ
* <p><b>Example: δ���ԣ���ȷ�����ӵ���ȷ��</b><p>
* <p>decodeStringNoHead("Minim|1^Hzm|2^Yt|3") returns "Minim,1,Hzm,2,Yt,3"<p>
* @param strValue ��Ҫ�������ַ���,ͨ���ǲ�ѯ���ݿⷵ�صĽ���ַ���
* @return ���ִ�гɹ����򷵻��Լ�¼Ϊ�У��ֶ�Ϊ�еĶ�Ψ���飬���ִ�в��ɹ����򷵻�false
*/
function decodeStringNoHead(strValue){
    var i,i1,j,j1;
    var strValue;   //��ŷ������˷��صĴ�������
    var arrField;
    var arrRecord;
    var arrCode = new Array();  //��ų�ʼ������ʱ��
    var t_Str;
    if(strValue==null || strValue=="")
    return false;
    try{
        arrRecord = strValue.split(RECORDDELIMITER);    //����ַ������γɷ��ص�����
        i1=arrRecord.length;
        for (i=0;i<i1;i++){
            arrField  = arrRecord[i].split(FIELDDELIMITER); //����ַ���,��ÿ����¼���Ϊһ������
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
* ��ս����ϵ�����������
* <p><b>Example: </b><p>
* <p>EmptyFormElements()<p>
*/
function emptyFormElements(){
    var formsNum = 0;   //�����е�FORM��
    var elementsNum = 0;    //FORM�е�Ԫ����
    //��������FORM
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //����FORM�е�����ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){
            if (window.document.forms[formsNum].elements[elementsNum].type == "text"){
                window.document.forms[formsNum].elements[elementsNum].value = "";
            }
        }
    }
}
/**
* �������ϵ�������������Ϊ"undefined"���
* <p><b>Example: </b><p>
* <p>EmptyFormElements()<p>
*/
function emptyUndefined(){
    var formsNum = 0;   //�����е�FORM��
    var elementsNum = 0;    //FORM�е�Ԫ����
    //��������FORM
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //����FORM�е�����ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){
            if ((window.document.forms[formsNum].elements[elementsNum].value == "undefined" || window.document.forms[formsNum].elements[elementsNum].value == "null") && (window.document.forms[formsNum].elements[elementsNum].type == "text" || window.document.forms[formsNum].elements[elementsNum].type == "textarea")){
                window.document.forms[formsNum].elements[elementsNum].value = "";
            }
        }
    }
}
/**
* ʹ��һά�����д�ŵ����������˶�ά����
* <p><b>Example: </b><p>
* <p>chooseArray({{1��2}��{3��4}},{0}) returns{{1}��{3}}<p>
* @param dataArray ������ݵĶ�ά����
* @param filterArray �����������һά����
* @return ��һά�����е��������˹��Ķ�ά����
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
        alert("chooseArray������ִ���");
    }
    return arrResult;
}
/**
* ��js�ļ��е��ַ�ת���������ַ�
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
* ���ݴ���ѡ��Ĵ�����Ҳ���ʾ����
*/
function showCodeName(){
}

/**
* ��ѯָ��ֵ�����ݿ��д治����
* tableName:����
* fieldName:�ֶ���
* fieldValue:��ѯ����
* ������ڷ���true,��֮����false
*/
function checkValueIsExsit(tableName,fieldName,fieldValue){
    var strSQL = "";
    strSQL = "select "+fieldName+" from "+tableName+" where "+fieldName+" = '"+fieldValue+"'";
    return true;
}

/**
* ��ѯָ���ֶε�ֵ
* tableName:����
* fieldName:�ֶ���
* fieldValue:��ѯ����
* returnField:�����ֶ���
* ������ڷ�������,��֮����""
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
* ��ѯָ���ֶε�ֵ
* tSQL:��ѯ����
* arrReturn:����ֵ����
* ������ڷ�������,��֮����""
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
* ��ѯָ���ֶε�ֵ
* tSQL:��ѯ����
* arrReturn:����ֵ
* ������ڷ�������,��֮����""
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
* ��LDCodeȡ�ô����Ӧ������
* CodeType:�ֶ�����
* Code:����
* ������ڷ�������,��֮����""
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
* ���ҳ��������ֵ,�ٷ���������,�������Ƿ�Ϸ�.
*ҳ��Ԫ��id������ӹ���������Amt��Num��β���ٷ�����Rat��Wgh��β��������Date��Day��β
*�����ִ�Сд
*Ҫ��ǿռ�ǰ׺'!'
* <p><b>Example: </b><p>
* @return ����ֵ��true--������, false--�������֣�
*/
function verifyCheck(){
    var formsNum = 0;   //�����е�FORM��
    var elementsNum = 0;    //FORM�е�Ԫ����
    var elementName;
    var elementId;
    var NullBlank;
    var curElement;
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //����FORM�е�����ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){

            curElement=window.document.forms[formsNum].elements[elementsNum];
            elementName=curElement.name.toUpperCase() ;
            elementId= curElement.id.toUpperCase();

            //�ж�Ԫ���Ƿ�ǿ�
            NullBlank=NullOrBlank(curElement.value);
            //�Ƿ�ɿ�
            if (  elementId.indexOf("!")!=-1 )
            {
                if (NullBlank)
                    {
                        alert(curElement.title+"����Ϊ��");
                        curElement.focus();
                        return false;
                    }
                 }
            ////����
            if (elementId.lastIndexOf("AMT")>=0||elementId.lastIndexOf("NUM")>=0 )
            {
                if (!NullBlank && !isNumeric(curElement.value) )
                {
                       alert(curElement.title+"������������");
                        curElement.focus();
                        return false;

                }
            }
            else if (elementId.lastIndexOf("RAT")>=0 ||elementId.lastIndexOf("WGH")>=0){
            //�ٷ���
            if (!NullBlank && !isRat(curElement.value) ){
                        alert(curElement.title+ "���벻��С��100�����֣�");
                        curElement.focus();
                        return false;
                }
            }
        else if (elementId.lastIndexOf("DATE")>=0 || elementId.lastIndexOf("DAY")>=0){
            //����
            if (!NullBlank && isDate(curElement.value)=="false" ){

                        alert(curElement.title+ "��������");
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

//�����ж������е�������ֵ��codeselect���е������Ƿ����
function checkcode(){
    var formsNum = 0;   //�����е�FORM��
    var elementsNum = 0;    //FORM�е�Ԫ����
    var strEvent = "";  //����onDoubleClick�¼�����
    var urlStr = "";
    var sFeatures = "";
    var strCode = "";   //����ѡ��
    var aCode = ""; //����
    var bCode = ""; //��ѯ����ֵ
    var cCode = ""; //��ѯ��������
    var strQueryResult = "";    //����ѡ��Ĳ�ѯ�����
    var arrCode = null; //�������
    var strCodeValue = "";  //����ֵ
    var cacheFlag = false;  //����У���־
    var strCodeSelect = "";
    //Ѱ��������
    var win = searchMainWindow(this);
    if (win == false){ win = this; }
    //��������FORM
    for (formsNum=0; formsNum<window.document.forms.length; formsNum++){
        //����FORM�е�����ELEMENT
        for (elementsNum=0; elementsNum<window.document.forms[formsNum].elements.length; elementsNum++){
            //Ѱ�Ҵ���ѡ��Ԫ��
            if ((window.document.forms[formsNum].elements[elementsNum].className == "code")||(window.document.forms[formsNum].elements[elementsNum].className == "codeno")){
                //ȡ������ֵ
                strCodeValue = window.document.forms[formsNum].elements[elementsNum].value;
                //��ֵ�򲻴���
                if (strCodeValue == "") continue;
                //��������Դ����ͬ���Ǻ��������һһƥ�䣬�ҵ����ϵľ�ͨ�������ȫ������������ƥ���򱨴�
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
                        alert(window.document.forms[formsNum].elements[elementsNum].title+"����ѡ�����");
                        window.document.forms[formsNum].elements[elementsNum].focus();
                        return false;
                        break;
                    }
                }
                else{
                    //�õ�ondblclick�ĺ���ֵ
                    strEvent = window.document.forms[formsNum].elements[elementsNum].ondblclick;
                    if (strEvent == null) continue;
                    strCode = new String(strEvent);
                    strCode = strCode.substring(strCode.indexOf("showCodeList") + 14);
                    aCode = strCode.substring(0, strCode.indexOf("'")); //�õ���ѯ����
                    //�����null���֣����ʾ�����в�ѯ���������ǻ���ȷ��һ���У���Ϊ�п���ֻ��ǿ��ˢ�£�
                    if (strCode.indexOf("null")!=-1){
                        strCode = strCode.substring(strCode.indexOf("null") + 5);
                        bCode = strCode.substring(0, strCode.indexOf(","));
                        strCode = strCode.substring(strCode.indexOf(",")+2);
                        cCode = strCode.substring(0, strCode.indexOf("'")); //�õ���ѯ������Ҫ��ѯ���ֶ�
                        if (bCode.indexOf("'")!=-1){
                            bCode = bCode.substring(1,bCode.length-1);  //�õ���ѯ�����ֶ���ƥ���ֵ
                        }
                        else{
                            bCode = eval(bCode);
                        }
                        if (bCode == "null") bCode = "";    //����null�����
                        if (cCode == "null") cCode = "";
                    }
                    else{
                        bCode = ""; //û�в�ѯ���������
                        cCode = "";
                    }
                    strCode = aCode + bCode + cCode;    //ƴд��ѯ����������Ĵ洢��ʽΪ���ͣ�ƥ���ֶ�ֵ+ƥ���ֶ�
                    //��������������ݣ�ƥ�����ݺ�ʵ�����ݣ�������������������Ϊ���˫��������
                    if (win.parent.VD.gVCode.getVar(strCode) != false){
                        cacheFlag = false;
                        arrCode = win.parent.VD.gVCode.getVar(strCode);
                        for(var m=0;m<arrCode.length;m++){
                            if (strCodeValue==arrCode[m][0]){
                                cacheFlag = true;
                                continue;
                                //���������ƥ�������������
                            }
                        }
                        if (cacheFlag==false){
                            alert(window.document.forms[formsNum].elements[elementsNum].title+"����ѡ�����");
                            window.document.forms[formsNum].elements[elementsNum].focus();
                            return false;
                            break;
                        }
                    }
                    else{
                        continue;   //����ν��һ�仰
                    }
                }
            }
        }
    }
    return true;
    //alert(win.parent.VD.gVCode.getVar('OrganCode010104a.AccountOrgan'));  //�õ�����
    //alert(win.parent.VD.gVCode.getVar('OrganCode010104a.AccountOrganSelect'));    //�õ�html����
}

//�滻�ַ����س��������š�˫���ţ����д���֤
function escapes(ls_string){
    ls_string = replace(ls_string,"\r\n","@E!");
    ls_string = replace(ls_string,"\'","@SQ!");
    ls_string = replace(ls_string,"\"","@DQ!");
    return ls_string;
}

//�ָ��ַ����ж��Ƿ���escapeת���ģ���������������е��滻������
function unescapes(ls_string){
    if((ls_string.indexOf("%u") == -1)&&(ls_string.indexOf("%20") == -1)&&(ls_string.indexOf("%0D%0A") == -1)){
        //���Ʋ���Ҫ����ʲô�����ˣ����ʹ�������ı�����ϵ��ʹ����������ϵ�������ǻ���һ�������⣬������û�б�����
        ls_string = replace(ls_string,"@E!","\r\n");
        ls_string = replace(ls_string,"@SQ!","\'");
        ls_string = replace(ls_string,"@DQ!","\"");
    }
    else{
        ls_string = unescape(ls_string);
    }
    return ls_string;
}

//�ж�ָ�����ַ����Ƿ񳤶��Ƿ�Ϊ0����Ϊundefined����Ϊnull
function isEmpty(value){
    return (value == undefined || value == null || value == "");
}

//�ж�һ���������Ƿ�ӽ�0
function equalZero(floatValue){
    try {
        var overValue = Math.abs(floatValue - 0);
        if (overValue < ZERO_EQUAL_VALUE){
            return true;
        }
    } catch (ex){
        alert("�жϸ������Ƿ�Ϊ0ʧ�ܣ�" + ex + " ָ���ĸ�����Ϊ��" + floatValue);
    }
    return false;
}

//�ж�һ��ָ�������Ƿ����0
function greatThanZero(floatValue){
    try {
        if (floatValue > 0 && !equalZero(floatValue)){
            return true;
        }
    } catch (ex){
        alert("�жϸ�������" + floatValue + "�Ƿ����0ʧ�ܣ�" + ex);
    }
    return false;
}

//��ʽ��������
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

//parseInt 10����
function myParseInt(param)
{
    return parseInt(param,10);
}


//��־ΰ
//����¶ȼ�num
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

//penpen    ��ʽ�����֣������ʽ��###
function formatAccount(sValue)
{
    try {
        if (sValue.length == 0) return "";
        var rValue=formatValue(sValue);
        var tLength=rValue.length;
        var rSign="";  //������
        var rInt=rValue.substring(0,tLength-3);  //��������
        var rDecimal=rValue.substring(tLength-3); //С������

        if(rInt<0){  //����Ǹ�����Ѹ��ű�������
            rSign=rInt.substring(0,1);
            rInt=rInt.substring(1);
        }

        if(rInt.length<=3) return rValue;  //����������ֳ���С��3����ֱ�ӷ���

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
        alert("ת����" + sValue + "Ϊ��Ƹ�ʽʧ�ܣ�" + ex);
    }
    return tReturn;
}

/**
 * �ڿ���д򿪲�ѯҳ��
 * @param queryURI ��ѯҳ���URI�����Դ�����
 */
function showQueryPage(queryURI){
    var encodedURI = encodeURIComponent(queryURI);
    var uri = "QueryMain.jsp?queryURI=" + encodedURI;
    openWindow(uri);
}

/**
 *ȡĳ��λ�ĳ�ʼ������¶�
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
        alert("�Ѿ���ʼ��ȷ��,�������������!");
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
            alert("��δָ����ʼ���¶�,�������ʼ���¶�!");
            return "";
        }else{
            return resArr[0][0];
        }
    }
}

/*
У�������email��ַ�Ƿ�Ϸ�
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
	alert("email��ַ���Ϸ���");
	return false;
}
var user=matchArray[1];
var domain=matchArray[2];

// See if "user" is valid 
if (user.match(userPat)==null) {
    // user is not valid
    alert("email�û������Ϸ���");
    return false;
}

/* if the e-mail address is at an IP address (as opposed to a symbolic
   host name) make sure the IP address is valid. */
var IPArray=domain.match(ipDomainPat);
if (IPArray!=null) {
    // this is an IP address
	  for (var i=1;i<=4;i++) {
	    if (IPArray[i]>255) {
	        alert("email IP��ַ���Ϸ���");
		return false;
	    }
    }
    return true;
}

// Domain is symbolic name
var domainArray=domain.match(domainPat);
if (domainArray==null) {
	alert("email���������Ϸ���");
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
   alert("email��ַ������3��ĸ�������2��ĸ�Ĺ��ҽ�β��");
   return false;
}

// Make sure there's a host name preceding the domain.
if (len<2) {
   var errStr="emai��ַȱ��������";
   alert(errStr);
   return false;
}

// If we've gotten this far, everything's valid!
return true;
}

//�������ù��� �򵥲�ѯ
//add by dengqiao
function getValueBySimpQry(fieldName,code){
	var sFeatures = "status:no;help:0;close:0;dialogWidth:0px;dialogHeight:0px;resizable=1";
	var urlStr = "../common/simpleQuery/SimpleQueryWindow.jsp?fieldName="+fieldName+"&code=" + code;
	var strQueryResult = window.showModalDialog(urlStr, "", sFeatures);
	if(strQueryResult==false)return "";
	else return strQueryResult;
}


/*������������صĺ���************************************************/

//ȡ��һ������
//@param dDate: Date����
function getDateOfYesterday(dDate){
	var diffrence = 1000*60*60*24;//һ��ĺ�����
	var today = Date.parse(dDate);
	var yesterday = today-diffrence;
	return new Date(yesterday);
}

//ȡ��һ������
//@param dDate: Date����
function getDateOfTomorrow(dDate){
	var diffrence = 1000*60*60*24;//һ��ĺ�����
	var today = Date.parse(dDate);
	var yesterday = today+diffrence;
	return new Date(yesterday);
}

//ȡ��һ���µ�����
//@month : number of the month;0-11
//@year : number of the year;such as 2005
function getDaysOfMonth(month,year){
	var monarr = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monarr[1] = "29";
	return monarr[month];
}

//ȡ���µ�����
//@param dDate: Date����
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

//ȡ���µ�����
//@param dDate: Date����
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

//ȡ��һ��������
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
//ȡ��һ��������
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

//ȡ��һ�������
//@param dDate: Date����
function getDateOfLastYear(dDate){
	var tYear = dDate.getFullYear();
	tYear--;
	dDate.setYear(tYear);
	return dDate;
}

//ȡ��һ�������
//@param dDate: Date����
function getDateOfNextYear(dDate){
	var tYear = dDate.getFullYear();
	tYear++;
	dDate.setYear(tYear);
	return dDate;
}

//ȡ�ÿ�ƴ�ӵ�����
//@param dDate: Date����
function getConcatableDate(dDate){
	var tDate = dDate.getDate();
	return tDate<10?"0"+tDate:tDate;
}
//ȡ�ÿ�ƴ�ӵ��·�
//@param dDate: Date����
function getConcatableMonth(dDate){
	var tMonth = dDate.getMonth()+1;
	return tMonth<10?"0"+tMonth:tMonth;
}
//ȡ�ÿ�ƴ�ӵ����
//@param dDate: Date����
function getConcatableYear(dDate){
	return dDate.getFullYear();
}
//ȡ�ø�ʽ�������ڣ������ӷ�
//@param dDate: Date����
function getFormattedDate(dDate){
	var s = "";
	return s.concat(getConcatableYear(dDate),getConcatableMonth(dDate),getConcatableDate(dDate));
}

/*����Ϊ������������صĺ���******************************************/

/***************
 * ʹ��DOM����JS
 **************/
function addJavaScript(file){
	var head = document.getElementsByTagName('head').item(0);
	var script = document.createElement('script');
	script.src = file;
	script.type = 'text/javascript';
	head.appendChild(script);
}
/****************
 * ʹ��DOM����CSS
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
*�������*
*****************/
function addZero(inputObj,size){ //inputObj���� size����
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
        $().openDialog("alert", true, true, "�����ı�����������������涨����󳤶ȣ�", "��ʾ��Ϣ");
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
//ʱ���  
//Ϊ��ʹÿ������ͼƬ��һ�£�����������������棬������Ҫ����ʱ���  
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

//����Iframe���Զ��ſ�,�����и�ҳ���Iframe���Զ���Ӧ����ҳ�߶�   
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

//����Iframe���Զ��ſ�,�����и�ҳ���Iframe���Զ���Ӧ����ҳ�߶�   
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
 *����:��ģ̬����
 *����:
 */
function showModel(url, width, height){
	// ���showModalDialog��url�Ѿ��������ˣ���ôֻ���refreshRandomSerialNo="+Math.random()׷�ӵ����в�������
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
		alert("�����޶�������");
	} else {
		document.getElementById(wordsId).innerHTML = len;
	}
}

// ����
function upOption(obj) {
	//�������һ������Ҫ�ƶ�������ֱ�Ӵ�i=1��ʼ
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

// ����
function downOption(obj) {
	// �����ƶ������һ������Ҫ��������ֱ�Ӵӵ����ڶ�����ʼ
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

// ɾ��
function deleteOption(obj) {
	for (var i = 0; i < obj.length; i++) {
		if (obj.options[i].selected) {
			obj.options[i] = null ; 
		}
	}
}