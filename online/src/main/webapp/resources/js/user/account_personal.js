var curDate = new Date();
var curYear = curDate.getYear() + 1900;
$(document).ready(function(){
	$('.head_mid .main_title').html('��Ա����');
	$('.member_menu .item').eq(5).addClass('select');
	$('.member_menu .item').eq(5).siblings().removeClass('select');
	$('.member_menu .item .account_item').eq(0).addClass('item_select');
	$('.member_menu .item .account_item').eq(0).siblings().removeClass('item_select');
	
	//�����Ч
	$('.click_btn').mousedown(function(){
		$(this).css({
			'top':'1px',
			'left':'1px'
		});
	}).mouseup(function(){
		$(this).css({
			'top':'0',
			'left':'0'
		});
	});
	
	$('.input_row .input_sex').click(function(){
		if(!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
			$('#appSex').val($(this).attr('val'));
		}
		checkIdCard('app','','personalInfoForm');
	});
	
	var year_opts = getYearOfnum(80,1),
		month_opts = [
				         {name:'01',value:'01'},
				         {name:'02',value:'02'},
				         {name:'03',value:'03'},
				         {name:'04',value:'04'},
				         {name:'05',value:'05'},
				         {name:'06',value:'06'},
				         {name:'07',value:'07'},
				         {name:'08',value:'08'},
				         {name:'09',value:'09'},
				         {name:'10',value:'10'},
				         {name:'11',value:'11'},
				         {name:'12',value:'12'}
				],
		day_opts = [
		            ],
		blood_type_opts = [
						{name:'O��',value:'1'},
						{name:'A��',value:'2'},
						{name:'B��',value:'3'},
						{name:'AB��',value:'4'},
						{name:'����',value:'5'}
		                   ];
		
	function updateDaySelector(year,month,day){
		var year_str = year.data('jSelect').getValue();
		var month_str = month.data('jSelect').getValue();
		if(year_str && month_str){
			var date = DayNumOfMonth(year_str,month_str);
			var days = [];
			for(var i=0 ; i< date ; i++){
				var dayStr = i+1;
				if(dayStr < 10){
					dayStr = "0"+dayStr;
				}
				days.push({
					name:dayStr,
					value:dayStr
				});
			}
			day.data('jSelect').update(days);
		}
	}
	
	function setBirthday() {
		var year = $('#app_year').val();
		var month = $('#app_month').val();
		var day = $('#app_day').val();
		if (year != "" && month != "" && day != "") {
			$("#appBirthday").attr("value", year + "-" + month + "-" + day);
		}
	}

	$('#app_year').jSelect({
		options:year_opts,
		onSelect:function(){
			updateDaySelector($('#app_year'),$('#app_month'),$('#app_day'));
			setBirthday();
		}
	});
	
	$('#app_month').jSelect({
		options:month_opts,
		onSelect:function(){
			updateDaySelector($('#app_year'),$('#app_month'),$('#app_day'));
			setBirthday();
		}
	});
	
	$('#app_day').jSelect({
		options:day_opts,
		onSelect:function() {
			setBirthday();
		}
	});
	
    var IdTypeYear_opts = getYearOfnum(50,2);
	
	$('#app_validity_year').jSelect({
		options:IdTypeYear_opts,
		onSelect:function(){
			updateDaySelector($('#app_validity_year'),$('#app_validity_month'),$('#app_validity_day'));
			setValidityDate();
		}
	});
	
	$('#app_validity_month').jSelect({
		options:month_opts,
		onSelect:function(){
			updateDaySelector($('#app_validity_year'),$('#app_validity_month'),$('#app_validity_day'));
			setValidityDate();
		}
	});
	
	$('#app_validity_day').jSelect({
		options:day_opts,
		onSelect:function(){
			setValidityDate();
		}
	});
	
	
	function setValidityDate() {
		var year = $('#app_validity_year').val();
		var month = $('#app_validity_month').val();
		var day = $('#app_validity_day').val();
		if (year != "" && month != "" && day != "") {
			$("#appIdentifyEffectiveDate").attr("value", year + "-" + month + "-" + day);
		}
	}
	
	$('#appIdType').jSelect({
		options:eval(s_identifyTypes),
		onSelect:function(){
			checkIdCard('app','','personalInfoForm');
		}
	});
	
	$('.blood_type .input_text').jSelect({
		options:blood_type_opts
	});
	
});

//��ʼ�������֤
function initCheck(flag,prefix,prefixTag,index,isExsitBnf){
// 	console.log("initCheck(flag: "+flag+", prefix: "+prefix+", prefixTag: "+prefixTag+", index: "+index+")...");
	var relationToApp = "RelationToApp";
	var name = 'Name';
	var idType = "IdType";
	var idNo = "IdNo";
	var _year = "_year";
	var _month = "_month";
	var _day = "_day";
	var birthday = "Birthday";
	var sex = "Sex";
	var email = "Email";
	var mobilePhone = "MobilePhone";
	var address = "Address";
	var zipCode = "ZipCode";
	var comPhone = "ComPhone";
	var homePhone = "HomePhone";
	var occupation = "Occupation";
	var province = "Province";
	var city = "City";
	var area = "Area";
	var _validity_year = "_validity_year";
	var _validity_month = "_validity_month";
	var _validity_day = "_validity_day";
	
	//����
	if($("#"+prefixTag+" #"+prefix+name+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+name+"").formValidator().regexValidator({regExp:regexEnum.chinese,onError:"����������"});
	}
	//��������
	if($("#"+prefixTag+" #"+prefix+birthday+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+birthday+"").formValidator().regexValidator({regExp:regexEnum.date,onError:"��ѡ���������"});	
	}
	//֤������
	if($("#"+prefixTag+" #"+prefix+idType+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+idType+"").formValidator().regexValidator({regExp:regexEnum.num1,onError: "��ѡ��֤������"});
	}
	//֤������
	if($("#"+prefixTag+" #"+prefix+idNo+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+idNo+"").formValidator().inputValidator({min:1,max:18,onError:"��������ȷ��֤������"}).functionValidator({fun:function(sId){
			if($("#"+prefixTag+" #"+prefix+idType+"").val() == '0'){
				return isCardID(sId);
			}
		}});
	}
	//��������
	if($("#"+prefixTag+" #"+prefix+email+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+email+"").formValidator().inputValidator({min:6,max:100,onError:"��������ȷ�������ַ"}).regexValidator({regExp:regexEnum.email,onError:"�����ʽ����ȷ"});
	}else{
		$("#"+prefixTag+" #"+prefix+email+"").formValidator().functionValidator({fun:function(emailStr){
			var emailTest = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
			if($("#"+prefixTag+" #"+prefix+email+"").val() != '' && !emailTest.test(emailStr)){
				return "��������ȷ�������ַ";
			}
		}});
	}
	//��ϵ��ַ
	if($("#"+prefixTag+" #"+prefix+address+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+address+"").formValidator().inputValidator({min:2,max:100,onError:"�����ַ����Ȳ���ȷ,��ȷ��"});
	}
	//ʡ��
	if($("#"+prefixTag+" #"+prefix+province+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+province+"").formValidator().regexValidator({regExp:regexEnum.num1,onError: "��ѡ��ʡ��"});
	}
	//��������
	if($("#"+prefixTag+" #"+prefix+zipCode+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+zipCode+"").formValidator().regexValidator({regExp:"zipcode",dataType:"enum",onError:"�ʱ��ʽ����ȷ,���磺100000������ȷ����"});
	}
	//��˾�绰
	if($("#"+prefixTag+" #"+prefix+comPhone+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+comPhone+"").formValidator().regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"��ʽ����ȷ,���磺021-88888888"});
	}
	//��ͥ�绰
	if($("#"+prefixTag+" #"+prefix+homePhone+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+homePhone+"").formValidator().regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"��ʽ����ȷ,���磺021-88888888"});
	}
	//�ƶ��绰
	if($("#"+prefixTag+" #"+prefix+mobilePhone+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").formValidator().inputValidator({min:11,max:11,onError:"��������ȷ���ֻ�����"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"��������ȷ���ֻ�����"})
	}else{
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").formValidator().functionValidator({fun:function(mobile){
			var mobileTest = /^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$/;
			if($("#"+prefixTag+" #"+prefix+mobilePhone+"").val() != '' && !mobileTest.test(mobile)){
				return "��������ȷ���ֻ�����";
			}
		}});
	}
	//�����˹�ϵ
	if($("#"+prefixTag+" #"+prefix+relationToApp+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+relationToApp+"").formValidator().inputValidator({min:1,regExp:regexEnum.num1,onError: "��ѡ����Ͷ���˹�ϵ"}).functionValidator({fun:function(relationToAppStr){
			if(relationToAppStr == null || relationToAppStr == '' || relationToAppStr == '-1'){
				return "��ѡ����Ͷ���˹�ϵ";
			}
		}});
	}
}

//���������֤�����봦���Ա𣬳�������start...
function checkIdCard(prefix,index,prefixTag){
// 	console.log("checkIdCard(prefix: "+prefix+", index: "+index+", prefixTag: "+prefixTag+")...");
	var _idType = "IdType";
	var _idNo = "IdNo";
	var _year = "_year";
	var _month = "_month";
	var _day = "_day";
	var birthday = "Birthday";
	var sex = "Sex";
	var idType = $("#"+prefixTag+" #"+prefix+_idType+index).val();
	var idNo = $("#"+prefixTag+" #"+prefix+_idNo+index).val();
	if(idType!= null && idType == '0' && idNo != ''){
		if(idNo.length == 18 && isCardID(idNo) == true){
			//�����Ա𡢳�������Ϊ���ɱ༭
			$("#"+prefixTag+" #"+prefix+_year+index).data('jSelect').disable();
			$("#"+prefixTag+" #"+prefix+_month+index).data('jSelect').disable();
			$("#"+prefixTag+" #"+prefix+_day+index).data('jSelect').disable(); 
			$("#"+prefixTag+" #male"+index).attr({disabled:"disabled"});
			$("#"+prefixTag+" #female"+index).attr({disabled:"disabled"});
			var returnStr = doCardIDGetBirthdayAndSex(idNo);
// 			console.log(returnStr);
			//true,����,1986-5-23,M
			//console.log(returnStr.split(',')[0]);
			if(returnStr.indexOf(',',0) > -1 && returnStr.split(',')[0]=='true'){
				var birthday_1 = returnStr.split(',')[2];
				var y = birthday_1.split("-")[0];
				var m = birthday_1.split("-")[1];
				var d = birthday_1.split("-")[2];
				if(m > 0 && m < 10){
					m = "0" + m;
				}
				if(d > 0 && d < 10){
					d = "0" + d;
				}
				$("#"+prefixTag+" #"+prefix+birthday+index).val(y+"-"+m+"-"+d);
				$("#"+prefixTag+" #"+prefix+_year+index).data('jSelect').setValue(y);
				$("#"+prefixTag+" #"+prefix+_month+index).data('jSelect').setValue(m);
				$("#"+prefixTag+" #"+prefix+_day+index).data('jSelect').setValue(d);
				if(returnStr.split(',')[3] == "M"){
					if(!$("#"+prefixTag+" #male"+index).hasClass('selected')){
						$("#"+prefixTag+" #male"+index).addClass('selected');
						$("#"+prefixTag+" #male"+index).siblings().removeClass('selected');
						$("#"+prefixTag+" #male"+index).parent().find("input[type='hidden']").attr('value','0');
					}
					$("#"+prefixTag+" #"+prefix+sex+index).val('0');
				}else if(returnStr.split(',')[3] == "F"){
					if(!$("#"+prefixTag+" #female"+index).hasClass('selected')){
						$("#"+prefixTag+" #female"+index).addClass('selected');
						$("#"+prefixTag+" #female"+index).siblings().removeClass('selected');
						$("#"+prefixTag+" #female"+index).parent().find("input[type='hidden']").attr('value','1');
					}
					$("#"+prefixTag+" #"+prefix+sex+index).val('1');
				}
			}
		}else{
			//console.log(isCardID(idNo));
			//�����Ա𡢳�������Ϊ�ɱ༭
			$("#"+prefixTag+" #"+prefix+idNo+index).removeAttr("disabled");
			$("#"+prefixTag+" #"+prefix+_year+index).data('jSelect').enable();
			$("#"+prefixTag+" #"+prefix+_month+index).data('jSelect').enable();
			$("#"+prefixTag+" #"+prefix+_day+index).data('jSelect').enable();
			$("#"+prefixTag+" #male"+index).removeAttr("disabled");
			$("#"+prefixTag+" #female"+index).removeAttr("disabled");
		}
	}else{
		$("#"+prefixTag+" #"+prefix+idNo+index).removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+_year+index).data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_month+index).data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_day+index).data('jSelect').enable();
		$("#"+prefixTag+" #male"+index).removeAttr("disabled");
		$("#"+prefixTag+" #female"+index).removeAttr("disabled");
	}
}

function fillDate() {
	//�Ա��ʼ��
	if(s_sex == null || s_sex == "") {
		$("#appSex").attr("value", "0");
	} else {
		$("#appSex").attr("value", s_sex);
	}
	
	Sinosoft.initCitySelector($('#personalInfoForm #appProvince'),$('#personalInfoForm #appCity'),$('#personalInfoForm #appArea'));
	
	//֤�����ͣ���ʼ��
	if (s_idType != "") {
		$('#appIdType').data('jSelect').setValue(s_idType);
	} else {
		$('#appIdType').data('jSelect').setValue(0);
	}
	
	if (s_birthday != null && s_birthday != "") {
		s_birthday = new Date(s_birthday).format('yyyy-MM-dd');
		if(s_birthday.indexOf('-') > 0){
			$('#app_year').data('jSelect').setValue(s_birthday.split('-')[0]);
			$('#app_month').data('jSelect').setValue(s_birthday.split('-')[1]);
			$('#app_day').data('jSelect').setValue(s_birthday.split('-')[2]);
		}
		$("#appBirthday").attr("value", s_birthday);
	}
	
	if (s_idEffDate != null && s_idEffDate != "") {
		s_idEffDate = new Date(s_idEffDate).format('yyyy-MM-dd');
		if(s_idEffDate.indexOf('-') > 0){
			$('#app_validity_year').data('jSelect').setValue(s_idEffDate.split('-')[0]);
			$('#app_validity_month').data('jSelect').setValue(s_idEffDate.split('-')[1]);
			$('#app_validity_day').data('jSelect').setValue(s_idEffDate.split('-')[2]);
		}
		$("#appIdentifyEffectiveDate").attr("value", s_idEffDate);
	}
	
	$('#bloodType').data('jSelect').setValue(s_bloodType);
	
	$('#appProvince').data('jSelect').setValue(s_province);
	$('#appCity').data('jSelect').setValue(s_city);
	$('#appArea').data('jSelect').setValue(s_county);
	
	checkIdCard('app','','personalInfoForm');
	if(s_bind == 'true'){
		$('#appName,#appIdNo,#sex').attr("disabled","true");
 		$("#appIdType").data('jSelect').disable();
	}
}

function validatePersonalInfo(){
	var result = jQuery.formValidator.pageIsValid('1');
	console.log(result);
	if(result){
		if (!checkUserPersonal()) {
			Sinosoft.alert('���Ѱ���Ӧ����,�����޸���ʵ�������Ա𣬳������ڣ�֤��������֤������');
			return false;
		}
		//����һ���Ի���
		new Sinosoft.InteractiveDialog({
			layout : loadCalSubSccess(),
			width:410,//�Զ��������-�������������
			cancelBtnShow:false,//�Ƿ���ʾ�رհ�ť
			okStr : 'ȷ��',
			cancelStr : 'ȡ��',
			okFunc:function(){
				$("#personalInfoForm").submit();
				return true;
			},
			cancelFunc:function(){
				$("#personalInfoForm").submit();
				return true;
			}
		}).open();
	}
}