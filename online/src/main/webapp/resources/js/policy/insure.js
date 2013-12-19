/**
 * 当关系改变时执行
 * @param flag 复制or清空
 * @param proxy 源组件关键字
 * @param prefix 目标组件关键字
 * @param proxyTag 源组件定位关键字
 * @param prefixTag 目标组件定位关键字
 */
function initParameter(flag,proxy,prefix,proxyTag,prefixTag){
//	console.log("initParameter(flag: "+flag+", proxy: "+proxy+", prefix: "+prefix+", proxyTag: "+proxyTag+", prefixTag: "+prefixTag+")...");
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
	var homePhone = "Address";
	var province = "Province";
	var city = "City";
	var area = "Area";
	var _validity_year = "_validity_year";
	var _validity_month = "_validity_month";
	var _validity_day = "_validity_day";
	if(flag == 1){
		$("#"+prefixTag+" #"+prefix+name+"").val($("#"+proxyTag+" #"+proxy+name+"").val());
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+idType+"").val());
		$("#"+prefixTag+" #"+prefix+idNo+"").val($("#"+proxyTag+" #"+proxy+idNo+"").val());
		$("#"+prefixTag+" #"+prefix+birthday+"").val($("#"+proxyTag+" #"+proxy+birthday+"").val());
		$("#"+prefixTag+" #"+prefix+province+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+province+"").val());
		$("#"+prefixTag+" #"+prefix+city+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+city+"").val());
		$("#"+prefixTag+" #"+prefix+area+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+area+"").val());
		$("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+_year+"").val());
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+_month+"").val());
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+_day+"").val());
		$("#"+prefixTag+" #"+prefix+_validity_year+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+_validity_year+"").val());
		$("#"+prefixTag+" #"+prefix+_validity_month+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+_validity_month+"").val());
		$("#"+prefixTag+" #"+prefix+_validity_day+"").data('jSelect').setValue($("#"+proxyTag+" #"+proxy+_validity_day+"").val());
		if($("#"+proxyTag+" #"+proxy+sex+"").val() == 0){
			if(!$("#"+prefixTag+" #male").hasClass('selected')){
				$("#"+prefixTag+" #male").addClass('selected');
				$("#"+prefixTag+" #male").siblings().removeClass('selected');
				$("#"+prefixTag+" #male").parent().find("input[type='hidden']").attr('value',$("#"+proxyTag+" #"+proxy+sex+"").val());
			}
		}else if($("#"+proxyTag+" #"+proxy+sex+"").val() == 1){
			if(!$("#"+prefixTag+" #female").hasClass('selected')){
				$("#"+prefixTag+" #female").addClass('selected');
				$("#"+prefixTag+" #female").siblings().removeClass('selected');
				$("#"+prefixTag+" #female").parent().find("input[type='hidden']").attr('value',$("#"+proxyTag+" #"+proxy+sex+"").val());
			}
		}
		$("#"+prefixTag+" #"+prefix+sex+"").val($("#"+proxyTag+" #"+proxy+sex+"").val());
		$("#"+prefixTag+" #"+prefix+email+"").val($("#"+proxyTag+" #"+proxy+email+"").val());
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").val($("#"+proxyTag+" #"+proxy+mobilePhone+"").val());
		$("#"+prefixTag+" #"+prefix+comPhone+"").val($("#"+proxyTag+" #"+proxy+comPhone+"").val());
		$("#"+prefixTag+" #"+prefix+homePhone+"").val($("#"+proxyTag+" #"+proxy+homePhone+"").val());
		$("#"+prefixTag+" #"+prefix+address+"").val($("#"+proxyTag+" #"+proxy+address+"").val());
		$("#"+prefixTag+" #"+prefix+zipCode+"").val($("#"+proxyTag+" #"+proxy+zipCode+"").val());
	}else if(flag == 0){
		$("#"+prefixTag+" #"+prefix+name+"").val("");
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').setValue("请选择");
        $("#"+prefixTag+" #"+prefix+idNo+"").val("");
        $("#"+prefixTag+" #"+prefix+birthday+"").val("");
        $("#"+prefixTag+" #"+prefix+province+"").val("");
		$("#"+prefixTag+" #"+prefix+city+"").val("");
		$("#"+prefixTag+" #"+prefix+area+"").val("");
        $("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #male").addClass('selected');
		$("#"+prefixTag+" #male").siblings().removeClass('selected');
		$("#"+prefixTag+" #male").parent().find("input[type='hidden']").attr('value','0');
        $("#"+prefixTag+" #"+prefix+sex+"").val("0");
        $("#"+prefixTag+" #"+prefix+email+"").val("");
        $("#"+prefixTag+" #"+prefix+mobilePhone+"").val("");
        $("#"+prefixTag+" #"+prefix+comPhone+"").val("");
        $("#"+prefixTag+" #"+prefix+homePhone+"").val("");
        $("#"+prefixTag+" #"+prefix+address+"").val("");
        $("#"+prefixTag+" #"+prefix+zipCode+"").val("");
	}
	initModuleEditable(flag,prefix,prefixTag);
}

/**
 * 设置组件是否可编辑
 * @param flag
 * @param prefix
 * @param prefixTag
 */
function initModuleEditable(flag,prefix,prefixTag){
//	console.log("initModuleEditable(flag: "+flag+", prefix: "+prefix+", prefixTag: "+prefixTag+")...");
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
	if(flag == 1){
		$("#"+prefixTag+" #"+prefix+name+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+idNo+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+birthday+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+province+"").data('jSelect').disable();
		$("#"+prefixTag+" #"+prefix+city+"").data('jSelect').disable();
		$("#"+prefixTag+" #"+prefix+area+"").data('jSelect').disable();
		$("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+_validity_year+"").data('jSelect').disable();
		$("#"+prefixTag+" #"+prefix+_validity_month+"").data('jSelect').disable();
		$("#"+prefixTag+" #"+prefix+_validity_day+"").data('jSelect').disable();
		$("#"+prefixTag+" #male").attr({disabled:"disabled"});
		$("#"+prefixTag+" #female").attr({disabled:"disabled"});
		for(var i=0;i<$("#"+prefixTag+" .label_sex").length;i++){
			$("#"+prefixTag+" .label_sex").eq(i).attr({disabled:"disabled"});
		}
		$("#"+prefixTag+" #"+prefix+sex+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+email+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+comPhone+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+homePhone+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+address+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+zipCode+"").attr({disabled:"disabled"});
	}else if(flag == 0){
		$("#"+prefixTag+" #"+prefix+name+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+idNo+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+birthday+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+province+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+city+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+area+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_validity_year+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_validity_month+"").data('jSelect').enable(); 
		$("#"+prefixTag+" #"+prefix+_validity_day+"").data('jSelect').enable(); 
		$("#"+prefixTag+" #male").removeAttr("disabled");
		$("#"+prefixTag+" #female").removeAttr("disabled");
		for(var i=0;i<$("#"+prefixTag+" .label_sex").length;i++){
			$("#"+prefixTag+" .label_sex").eq(i).removeAttr("disabled");
		}
		$("#"+prefixTag+" #"+prefix+sex+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+email+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+comPhone+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+homePhone+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+address+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+zipCode+"").removeAttr("disabled");
	}
}

function initAppModuleEditable(flag,prefix,prefixTag){
//	console.log("initAppModuleEditable(flag: "+flag+", prefix: "+prefix+", prefixTag: "+prefixTag+")...");
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
	if(flag == 1){
		$("#"+prefixTag+" #"+prefix+name+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+idNo+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+birthday+"").attr({disabled:"disabled"});
		$("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').disable(); 
		$("#"+prefixTag+" #male").attr({disabled:"disabled"});
		$("#"+prefixTag+" #female").attr({disabled:"disabled"});
		for(var i=0;i<$("#"+prefixTag+" .label_sex").length;i++){
			$("#"+prefixTag+" .label_sex").eq(i).attr({disabled:"disabled"});
		}
		$("#"+prefixTag+" #"+prefix+sex+"").attr({disabled:"disabled"});
	}else if(flag == 0){
		$("#"+prefixTag+" #"+prefix+name+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+idNo+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+birthday+"").removeAttr("disabled");
		$("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').enable();
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').enable();
		$("#"+prefixTag+" #male").removeAttr("disabled");
		$("#"+prefixTag+" #female").removeAttr("disabled");
		for(var i=0;i<$("#"+prefixTag+" .label_sex").length;i++){
			$("#"+prefixTag+" .label_sex").eq(i).removeAttr("disabled");
		}
		$("#"+prefixTag+" #"+prefix+sex+"").removeAttr("disabled");
	}
}

/**
 * 点击常用被保人快速填充被保人数据
 * @param flag
 * @param proxy
 * @param prefix
 * @param proxyTag
 * @param prefixTag
 */
function initInsDateByTopIns(flag,topId,prefix,prefixTag){
//	console.log("initInsDateByTopIns(flag: "+flag+", topId: "+topId+", prefix: "+prefix+", prefixTag: "+prefixTag+")...");
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
	var homePhone = "Address";
	var province = "Province";
	var city = "City";
	var area = "Area";
	var _validity_year = "_validity_year";
	var _validity_month = "_validity_month";
	var _validity_day = "_validity_day";
	
	if(flag == 1){ //填充
		$.ajax({
			type: "POST",
			url:contextRootPath+"/sale/obtainTopInsured.do",
			dataType : 'json',
			data:{"topId":topId},
			success: function(data){
				if(data.result != 'success'){
					alret(data.result,"");
				}else{
					$("#"+prefixTag+" #topinsId").val(data.topinsId);
					$("#"+prefixTag+" #"+prefix+relationToApp+"").data('jSelect').setValue(data.relationToApp);
					$("#"+prefixTag+" #"+prefix+name+"").val(data.name);
					$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').setValue(data.idType);
					$("#"+prefixTag+" #"+prefix+idNo+"").val(data.idNo);
					$("#"+prefixTag+" #"+prefix+birthday+"").val(data.birthday);
					$("#"+prefixTag+" #"+prefix+province+"").data('jSelect').setValue(data.province);
					$("#"+prefixTag+" #"+prefix+city+"").data('jSelect').setValue(data.city);
					$("#"+prefixTag+" #"+prefix+area+"").data('jSelect').setValue(data.county);
					var birthdayStr = data.birthday;
					var idExpDateStr = data.idExpDate;
					if(birthdayStr.indexOf("-") > 0){
						$("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').setValue(data.birthday.split("-")[0]);
						$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').setValue(data.birthday.split("-")[1]);
						$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').setValue(data.birthday.split("-")[2]);
					}
					if(idExpDateStr.indexOf("-") > 0){
						$("#"+prefixTag+" #"+prefix+_validity_year+"").data('jSelect').setValue(data.idExpDate.split("-")[0]);
						$("#"+prefixTag+" #"+prefix+_validity_month+"").data('jSelect').setValue(data.idExpDate.split("-")[1]);
						$("#"+prefixTag+" #"+prefix+_validity_day+"").data('jSelect').setValue(data.idExpDate.split("-")[2]);
					}
					if(data.sex == 0){
						if(!$("#"+prefixTag+" #male").hasClass('selected')){
							$("#"+prefixTag+" #male").addClass('selected');
							$("#"+prefixTag+" #male").siblings().removeClass('selected');
							$("#"+prefixTag+" #male").parent().find("input[type='hidden']").attr('value',data.sex);
						}
					}else if(data.sex == 1){
						if(!$("#"+prefixTag+" #female").hasClass('selected')){
							$("#"+prefixTag+" #female").addClass('selected');
							$("#"+prefixTag+" #female").siblings().removeClass('selected');
							$("#"+prefixTag+" #female").parent().find("input[type='hidden']").attr('value',data.sex);
						}
					}
					$("#"+prefixTag+" #"+prefix+sex+"").val(data.sex);
					$("#"+prefixTag+" #"+prefix+email+"").val(data.email);
					$("#"+prefixTag+" #"+prefix+mobilePhone+"").val(data.mobilePhone);
					$("#"+prefixTag+" #"+prefix+comPhone+"").val(data.comPhone);
					$("#"+prefixTag+" #"+prefix+homePhone+"").val(data.homePhone);
					$("#"+prefixTag+" #"+prefix+address+"").val(data.address);
					$("#"+prefixTag+" #"+prefix+zipCode+"").val(data.zipCode);
					checkIdCard(prefix,'',prefixTag);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log("操作异常，请稍候再试！");
			}
		}); 
	}else{	//清空
		$("#"+prefixTag+" #"+prefix+name+"").val("");
		$("#"+prefixTag+" #"+prefix+idType+"").data('jSelect').setValue("请选择");
        $("#"+prefixTag+" #"+prefix+idNo+"").val("");
        $("#"+prefixTag+" #"+prefix+birthday+"").val("");
        $("#"+prefixTag+" #"+prefix+province+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #"+prefix+city+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #"+prefix+area+"").data('jSelect').setValue("");
        $("#"+prefixTag+" #"+prefix+_year+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #"+prefix+_month+"").data('jSelect').setValue("");
		$("#"+prefixTag+" #"+prefix+_day+"").data('jSelect').setValue("");
        if(!$("#"+prefixTag+" #male").hasClass('selected')){
			$("#"+prefixTag+" #male").addClass('selected');
			$("#"+prefixTag+" #male").siblings().removeClass('selected');
			$("#"+prefixTag+" #male").parent().find("input[type='hidden']").attr('value','0');
		}
        $("#"+prefixTag+" #"+prefix+sex+"").val("0");
        $("#"+prefixTag+" #"+prefix+email+"").val("");
        $("#"+prefixTag+" #"+prefix+mobilePhone+"").val("");
        $("#"+prefixTag+" #"+prefix+comPhone+"").val("");
        $("#"+prefixTag+" #"+prefix+homePhone+"").val("");
        $("#"+prefixTag+" #"+prefix+address+"").val("");
        $("#"+prefixTag+" #"+prefix+zipCode+"").val("");
	}
}

/**
 * 验证投被保人信息是否相同
 * @param flag
 * @param proxy
 * @param prefix
 * @param proxyTag
 * @param prefixTag
 * @returns {Boolean}
 */
function checkInsisApp(flag,proxy,prefix,proxyTag,prefixTag){
//	console.log("checkInsisApp(flag: "+flag+", proxy: "+proxy+", prefix: "+prefix+", proxyTag: "+proxyTag+", prefixTag: "+prefixTag+")...");
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
	var homePhone = "Address";
	var province = "Province";
	var city = "City";
	var area = "Area";
	var _validity_year = "_validity_year";
	var _validity_month = "_validity_month";
	var _validity_day = "_validity_day";
	var result = true;
	
	if($("#"+prefixTag+" #"+prefix+name+"").val() != $("#"+proxyTag+" #"+proxy+name+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+idType+"").val() != $("#"+proxyTag+" #"+proxy+idType+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+idNo+"").val() != $("#"+proxyTag+" #"+proxy+idNo+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+birthday+"").val() != $("#"+proxyTag+" #"+proxy+birthday+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+province+"").val() != $("#"+proxyTag+" #"+proxy+province+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+area+"").val() != $("#"+proxyTag+" #"+proxy+area+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+_year+"").val() != $("#"+proxyTag+" #"+proxy+_year+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+_month+"").val() != $("#"+proxyTag+" #"+proxy+_month+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+_day+"").val() != $("#"+proxyTag+" #"+proxy+_day+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+_validity_year+"").val() != $("#"+proxyTag+" #"+proxy+_validity_year+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+_validity_month+"").val() != $("#"+proxyTag+" #"+proxy+_validity_month+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+_validity_day+"").val() != $("#"+proxyTag+" #"+proxy+_validity_day+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+sex+"").val() != $("#"+proxyTag+" #"+proxy+sex+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+email+"").val() != $("#"+proxyTag+" #"+proxy+email+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+mobilePhone+"").val() != $("#"+proxyTag+" #"+proxy+mobilePhone+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+comPhone+"").val() != $("#"+proxyTag+" #"+proxy+comPhone+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+homePhone+"").val() != $("#"+proxyTag+" #"+proxy+homePhone+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+address+"").val() != $("#"+proxyTag+" #"+proxy+address+"").val()){
		result = false;
	}
	if($("#"+prefixTag+" #"+prefix+zipCode+"").val() != $("#"+proxyTag+" #"+proxy+zipCode+"").val()){
		result = false;
	}
	return result;
}


//初始化投被保人组件验证
function initCheck(flag,prefix,prefixTag,index,isExsitBnf){
//	console.log("initCheck(flag: "+flag+", prefix: "+prefix+", prefixTag: "+prefixTag+", index: "+index+")...");
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
	
	//姓名
	if($("#"+prefixTag+" #"+prefix+name+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+name+"").formValidator().regexValidator({regExp:regexEnum.chinese,onError:"请输入中文"});
	}
	//性别
//	if($("#"+prefixTag+" #female").parent().find("span[class='required']").parent().find("span[class='required']").length > 0){
//		$("#"+prefixTag+" #female").parent().find("span[class='required']").formValidator({tipID:""+prefix+sex+"Tip"}).inputValidator({min:1,max:1,onError:"请选择性别"});		
//	}
	//出生日期
	if($("#"+prefixTag+" #"+prefix+birthday+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+birthday+"").formValidator().inputValidator({regExp:regexEnum.date,onError: "请选择出生日期"}).functionValidator({fun:function(birthdayStr){
			if(birthdayStr == null || birthdayStr == ''){
				return "请选择出生日期";
			}
		}});	
	}
	//证件类型
	if($("#"+prefixTag+" #"+prefix+idType+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+idType+"").formValidator().regexValidator({regExp:regexEnum.num1,onError: "请选择证件类型"}).functionValidator({fun:function(idTypeStr){
			if(idTypeStr == null || idTypeStr == '' || idTypeStr == '-1'){
				return "请选择证件类型";
			}
		}});
	}
	//证件号码
	if($("#"+prefixTag+" #"+prefix+idNo+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+idNo+"").formValidator().inputValidator({min:1,max:18,onError:"请输入正确的证件号码"}).functionValidator({fun:function(sId){
			if($("#"+prefixTag+" #"+prefix+idType+"").val() == '0'){
				return isCardID(sId);
			}
		}});
	}
	//电子邮箱
	if($("#"+prefixTag+" #"+prefix+email+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+email+"").formValidator().inputValidator({min:1,max:100,onError:"请输入正确的邮箱地址"}).regexValidator({regExp:regexEnum.email,onError:"邮箱格式不正确"});
	}else{
		$("#"+prefixTag+" #"+prefix+email+"").formValidator().functionValidator({fun:function(emailStr){
			if($("#"+prefixTag+" #"+prefix+email+"").val() != ''){
				var emailTest = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
				return emailTest.test(emailStr);
			}
		}});
	}
	
	//联系地址
	if($("#"+prefixTag+" #"+prefix+address+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+address+"").formValidator().inputValidator({min:2,max:100,onError:"输入字符长度不正确,请确认"});
	}
	
	//省份
	if($("#"+prefixTag+" #"+prefix+province+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+province+"").formValidator().regexValidator({regExp:regexEnum.num1,onError: "请选择省份"});
	}
	
	//邮政编码
	if($("#"+prefixTag+" #"+prefix+zipCode+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+zipCode+"").formValidator().regexValidator({regExp:"zipcode",dataType:"enum",onError:"邮编格式不正确,例如：100000，请正确输入"});
	}
	//公司电话
	if($("#"+prefixTag+" #"+prefix+comPhone+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+comPhone+"").formValidator().regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"格式不正确,例如：021-88888888"});
	}
	//家庭电话
	if($("#"+prefixTag+" #"+prefix+homePhone+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+homePhone+"").formValidator().regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"格式不正确,例如：021-88888888"});
	}
	//移动电话
	if($("#"+prefixTag+" #"+prefix+mobilePhone+"").parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").formValidator().inputValidator({min:11,max:11,onError:"请输入正确的手机号码"}).regexValidator({regExp:regexEnum.mobile,onError:"请输入正确的手机号码"});
	}else{
		$("#"+prefixTag+" #"+prefix+mobilePhone+"").formValidator().functionValidator({fun:function(mobile){
			var mobileTest = /^13[0-9]{9}|15[0-9]{9}|18[0-9]{9}|147[0-9]{8}$/;
			if(mobile != '' && !mobileTest.test(emailStr)){
				return "请输入正确的手机号码";
			}
		}});
	}
	if(flag == 2){
		//被保人关系
		if($("#"+prefixTag+" #"+prefix+relationToApp+"").parent().find("span[class='required']").length > 0){
			$("#"+prefixTag+" #"+prefix+relationToApp+"").formValidator().inputValidator({min:1,regExp:regexEnum.num1,onError: "请选择与投保人关系"}).functionValidator({fun:function(relationToAppStr){
				if(relationToAppStr == null || relationToAppStr == '' || relationToAppStr == '-1'){
					return "请选择与投保人关系";
				}
			}});
		}
		//职业类别
//		if($("#"+prefixTag+" #"+prefix+occupation+"").parent().find("span[class='required']").length > 0){
//			$("#"+prefixTag+" #"+prefix+occupation+"").formValidator().functionValidator({fun:function(occupationStr){
//				if(occupationStr == null || occupationStr == ''){
//					return "请选择职业";
//				}
//			}});
//		}
	}
}

//初始化受益人组件验证
function initCheckBnf(flag,prefix,prefixTag,index,isExsitBnf){
//	console.log("initCheckBnf(flag: "+flag+", prefix: "+prefix+", prefixTag: "+prefixTag+", index: "+index+")...");
	var relationToPIns = "RelationToPIns";
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
	var order = "Order";
	var rate = "Rate";
	var _validity_year = "_validity_year";
	var _validity_month = "_validity_month";
	var _validity_day = "_validity_day";
	//受益人关系
	if($("#"+prefixTag+" #"+prefix+relationToPIns+index).parent().parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+relationToPIns+index).formValidator({validatorGroup:10+index+""}).inputValidator({min:1,regExp:regexEnum.num1,onError: "请选择与被保人关系"}).functionValidator({fun:function(relationToPInsStr){
			if(relationToPInsStr == null || relationToPInsStr == '' || relationToPInsStr == '-1'){
				return "请选择与被保人关系";
			}
		}});
	}
	//姓名
	if($("#"+prefixTag+" #"+prefix+name+index).parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+name+index).formValidator({validatorGroup:10+index+""}).regexValidator({regExp:regexEnum.chinese,onError:"请输入中文"});
	}
	//出生日期
	if($("#"+prefixTag+" #"+prefix+birthday+index).parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+birthday+index).formValidator({validatorGroup:10+index+""}).inputValidator({regExp:regexEnum.date,onError: "请选择出生日期"}).functionValidator({fun:function(birthdayStr){
			if(birthdayStr == null || birthdayStr == ''){
				return "请选择出生日期";
			}
		}});
	}
	
	//证件类型
	if($("#"+prefixTag+" #"+prefix+idType+index).parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+idType+index).formValidator({validatorGroup:10+index+""}).inputValidator({min:1,onError: "请选择证件类型"}).functionValidator({fun:function(idTypeStr){
			if(idTypeStr == null || idTypeStr == '' || idTypeStr == '-1'){
				return "请选择证件类型";
			}
		}});
	}
	//证件号码
	if($("#"+prefixTag+" #"+prefix+idNo+index).parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+idNo+index).formValidator({validatorGroup:10+index+""}).inputValidator({min:1,max:18,onError:"请输入正确的证件号码"}).functionValidator({fun:function(sId){
			if($("#"+prefixTag+" #"+prefix+idType+index).val() == '0'){
				return isCardID(sId);
			}else{
				return true;
			}
		}});
	}
	//性别
//	if($("#"+prefixTag+" #"+prefix+"Gender"+index).parent().find("input[type='hidden']").parent().find("span[class='required']").length > 0){
//		$("#"+prefixTag+" #"+prefix+"Gender"+index).parent().find("input[type='hidden']").formValidator({validatorGroup:10+index+""}).inputValidator({min:1,max:1,onError:"请选择性别"});		
//	}
	//受益顺序
	if($("#"+prefixTag+" #"+prefix+order+index).parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+order+index).formValidator({validatorGroup:10+index+""}).regexValidator({regExp:"num",dataType:"enum",onError:"格式不正确，必须输入数字"});
	}
	//受益比例
	if($("#"+prefixTag+" #"+prefix+rate+index).parent().find("span[class='required']").length > 0){
		$("#"+prefixTag+" #"+prefix+rate+index).formValidator({validatorGroup:10+index+""}).inputValidator({regExp:regexEnum.intege1,onError: "受益比例输入错误"}).functionValidator({fun:function(rateStr){
			if(rateStr != null || rateStr != ''){
				var sumRate = 0;
				$(".beneficiary_info").find("div[tag='inputBnf']").each(function(i){
					if($("#"+prefixTag+" #"+prefix+order+i).val() == $("#"+prefixTag+" #"+prefix+order+index).val()){
						sumRate += parseInt($("#"+prefixTag+" #"+prefix+rate+i).val());
					}
				});
				if(sumRate != 100){
					return "同一受益顺序下受益比例之和必须为100%";
				}
			}
		}});
	}
}

//根据输入的证件号码处理性别，出生日期start...
function checkIdCard(prefix,index,prefixTag){
//	console.log("checkIdCard(prefix: "+prefix+", index: "+index+", prefixTag: "+prefixTag+")...");
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
			//设置性别、出生日期为不可编辑
			$("#"+prefixTag+" #"+prefix+_year+index).data('jSelect').disable();
			$("#"+prefixTag+" #"+prefix+_month+index).data('jSelect').disable();
			$("#"+prefixTag+" #"+prefix+_day+index).data('jSelect').disable(); 
			$("#"+prefixTag+" #male"+index).attr({disabled:"disabled"});
			$("#"+prefixTag+" #female"+index).attr({disabled:"disabled"});
			var returnStr = doCardIDGetBirthdayAndSex(idNo);
//			console.log(returnStr);
			//true,北京,1986-5-23,M
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
				autoSave();
			}
		}else{
			//console.log(isCardID(idNo));
			//设置性别、出生日期为可编辑
			if(prefix == 'app'){
				$("#"+prefixTag+" #"+prefix+idNo+index).removeAttr("disabled");
				$("#"+prefixTag+" #"+prefix+_year+index).data('jSelect').enable();
				$("#"+prefixTag+" #"+prefix+_month+index).data('jSelect').enable();
				$("#"+prefixTag+" #"+prefix+_day+index).data('jSelect').enable();
				$("#"+prefixTag+" #male"+index).removeAttr("disabled");
				$("#"+prefixTag+" #female"+index).removeAttr("disabled");
			}
		}
	}
}
//根据输入的证件号码处理性别，出生日期end...

//选择日期更新隐藏域中的值
function setHiddenInput(year,month,day){
	var year_str = year.data('jSelect').getValue();
	var month_str = month.data('jSelect').getValue();
	var day_str = day.data('jSelect').getValue();
	year.parent().find("input[type='hidden']").attr('value',year_str+"-"+month_str+"-"+day_str);
}