$(document).ready(function() {

	$('.nav_menu .nav_item').eq(2).addClass('select');
	$('.nav_menu .nav_item').eq(2).siblings().removeClass('select');

	// 点击特效
	$('.click_btn').mousedown(function() {
		$(this).css({
			'top' : '1px',
			'left' : '1px'
		});
	}).mouseup(function() {
		$(this).css({
			'top' : '0',
			'left' : '0'
		});
	});

	$('.input_row .input_sex').click(function(){
		if(!$(this).hasClass('selected')){
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
			$('#accidentSex').val($(this).attr('val'));
		}
		checkIdCard();
	});
	
	$('#accidentId').change(function(){
		checkIdCard();
	});
	
	initReportRelationSelector();
	//initAccidentSexSelector();
	initAccidentBirthSelector();
	initAccidentIdTypeSelector();
	initAccidentDateSelector();
	initPlaceSelector();
	initAccidentReasonSelector();
	initAccidentStatusSelector();
	
	$('#submitButton').click(function(){
		checkIdCard();
		
		if($('#reportName').val() == ''
		|| $('#reportPhone').val() == ''
		|| $('#reportRelation').val() == ''
		|| $('#accidentName').val() == ''
		|| $('#accidentBirthYear').val() == ''
		|| $('#accidentBirthMonth').val() == ''
		|| $('#accidentBirthDay').val() == ''
		|| $('#accidentIdType').val() == ''
		|| $('#accidentId').val() == ''
		|| $('#accidentDateYear').val() == ''
		|| $('#accidentDateMonth').val() == ''
		|| $('#accidentDateDay').val() == ''
		|| $('#province').val() == ''
		|| $('#city').val() == ''
		|| $('#county').val() == ''
		|| $('#other').val() == ''
		|| $('#accidentReason').val() == ''
		|| $('#accidentStatus').val() == ''
		|| $('#accidentDo').val() == ''
		|| $('#hospital').val() == ''
		|| $('#diagnose').val() == ''){
			a_alert('请检查是否遗漏输入！');
			return false;
		}
		
		if($('#accidentIdType').val() == '0'){
			var returnStr = doCardIDGetBirthdayAndSex($('#accidentId').val());
			
			if(!(returnStr.indexOf(',',0) > -1 && returnStr.split(',')[0]=='true')){
				a_alert(returnStr);
				return false;
			}
		}
		
		var reg = /^\d{11}$/;
		if (!reg.test($('#reportPhone').val())) {
			a_alert('请检查报案人手机号码是否正确！');
			return;
		}
		
		reg = /^\w+$/;
		if (!reg.test($('#accidentId').val())) {
			a_alert('请检查出险人证件号码是否正确！');
			return;
		}
		
		//判断是否有选中理赔类型
		var checked = false;
		$("[name='claimType']").each(function(){
			if($(this).attr('checked')){
				checked = true;
			}
		});
		if(!checked){
			a_alert('请至少选择一项理赔类型！');
		}
		var params = $('#fm').serialize();
		$.ajax({
			type : "POST",
			async : true,
			//url : $('#ctx').val()+"/toolJson/initArea.do",
			url : $('#ctx').val()+"/claimReportJson/reportClaim.do",
			dataType : 'json',
			data : params,
			success : function(data) {
				if(data.flag == "1"){
					location.href =$('#ctx').val() + "/web/service/claimReportSuccess/index.jsp";
				}else{
					a_alert(data.desc);
				}
			},
			error: function(data) {
				a_alert('网络错误');
			}
		});	
	});
});

function initReportRelationSelector(){
	var opts = [
	                	{
	                		name : '本人',
	                		value : 'GX01'
	                	},
	                	{
	                		name : '保单服务人员',
	                		value : 'GX02'
	                	},
	                	{
	                		name : '同事	',
	                		value : 'GX03'
	                	},
	                	{
	                		name : '朋友',
	                		value : 'GX04'
	                	},
	                	{
	                		name : '亲戚',
	                		value : 'GX05'
	                	},
	                	{
	                		name : '妻子',
	                		value : 'GX06'
	                	},
	                	{
	                		name : '丈夫',
	                		value : 'GX07'
	                	},
	                	{
	                		name : '儿女',
	                		value : 'GX08'
	                	},
	                	{
	                		name : '父亲',
	                		value : 'GX09'
	                	},
	                	{
	                		name : '母亲',
	                		value : 'GX10'
	                	},
	                	{
	                		name : '其他',
	                		value : 'GX11'
	                	},
	                	{
	                		name : '身故受益人',
	                		value : '12'
	                	}
		             ];
		$('#reportRelation').jSelect({
			options : opts,
			onSelect : function() {
			}
		});
}

function initAccidentSexSelector(){
	var opts = [
            	{
            		name : '男',
            		value : '0'
            	},
            	{
            		name : '女',
            		value : '1'
            	}
             ];
	$('#accidentSex').jSelect({
		options : opts,
		onSelect : function() {
		}
	});
}

function initAccidentBirthSelector() {
	var year_opts = [],
	month_opts = [ {
		name : '1',
		value : '1'
	}, {
		name : '2',
		value : '2'
	}, {
		name : '3',
		value : '3'
	}, {
		name : '4',
		value : '4'
	}, {
		name : '5',
		value : '5'
	}, {
		name : '6',
		value : '6'
	}, {
		name : '7',
		value : '7'
	}, {
		name : '8',
		value : '8'
	}, {
		name : '9',
		value : '9'
	}, {
		name : '10',
		value : '10'
	}, {
		name : '11',
		value : '11'
	}, {
		name : '12',
		value : '12'
	} ],
	day_opts = [];

	for ( var i = 1930; i < 2021; i++) {
		year_opts.push({
			name : i,
			value : i
		});
	}

	$('#accidentBirthYear').jSelect(
			{
				options : year_opts,
				onSelect : function() {
					updateDaySelector($('#accidentBirthYear'),
							$('#accidentBirthMonth'), $('#accidentBirthDay'));
				}
			});

	$('#accidentBirthMonth').jSelect(
			{
				options : month_opts,
				onSelect : function() {
					updateDaySelector($('#accidentBirthYear'),
							$('#accidentBirthMonth'), $('#accidentBirthDay'));
				}
			});

	$('#accidentBirthDay').jSelect({
		options : day_opts
	});
}

function initAccidentIdTypeSelector(){
	var opts = [
            	{
            		name : '身份证',
            		value : '0'
            	},
            	{
            		name : '护照',
            		value : '1'
            	},
            	{
            		name : '军官证',
            		value : '2'
            	},
            	{
            		name : '驾照',
            		value : '3'
            	},
            	{
            		name : '出生证明',
            		value : '4'
            	},
            	{
            		name : '户口簿',
            		value : '5'
            	},
            	{
            		name : '其他',
            		value : '8'
            	},
            	{
            		name : '数据转换证件',
            		value : '9'
            	}
             ];
$('#accidentIdType').jSelect({
	options : opts,
	onSelect : function() {
		checkIdCard();
	}
});
}

function initAccidentDateSelector(){
	var year_opts = [],
	month_opts = [ {
		name : '1',
		value : '1'
	}, {
		name : '2',
		value : '2'
	}, {
		name : '3',
		value : '3'
	}, {
		name : '4',
		value : '4'
	}, {
		name : '5',
		value : '5'
	}, {
		name : '6',
		value : '6'
	}, {
		name : '7',
		value : '7'
	}, {
		name : '8',
		value : '8'
	}, {
		name : '9',
		value : '9'
	}, {
		name : '10',
		value : '10'
	}, {
		name : '11',
		value : '11'
	}, {
		name : '12',
		value : '12'
	} ],
	day_opts = [];

	for ( var i = 2010; i < 2021; i++) {
		year_opts.push({
			name : i,
			value : i
		});
	}

	$('#accidentDateYear').jSelect(
			{
				options : year_opts,
				onSelect : function() {
					updateDaySelector($('#accidentDateYear'),
							$('#accidentDateMonth'), $('#accidentDateDay'));
				}
			});

	$('#accidentDateMonth').jSelect(
			{
				options : month_opts,
				onSelect : function() {
					updateDaySelector($('#accidentDateYear'),
							$('#accidentDateMonth'), $('#accidentDateDay'));
				}
			});

	$('#accidentDateDay').jSelect({
		options : day_opts
	});
}

function initPlaceSelector(){
	$('#province').jSelect({
		options : [],
		onSelect : function() {
			$.ajax({
				type : "POST",
				async : false,
				url : $('#ctx').val()+"/toolJson/initArea.do",
				dataType : 'json',
				data : {'upCode':$('#province').val()},
				success : function(data) {
					console.log("area data:" +data)
					var opts = [];
					for(var i = 0; i < data.listArea.length; i++){
						var opt={};
						opt['name'] = data.listArea[i].name;
						opt['value'] = data.listArea[i].code;
						opts.push(opt);
					}
					$('.place .selector:eq(1) .text div').html('');
					$('.place .selector:eq(2) .text div').html('');
					$('#city').data('jSelect').update(opts);
				},
				error: function(data) {
					a_alert('网络错误');
				}
			});	
		}
	});
	$('#city').jSelect({
		options : [],
		onSelect : function() {
			$.ajax({
				type : "POST",
				async : false,
				url : $('#ctx').val()+"/toolJson/initArea.do",
				dataType : 'json',
				data : {'upCode':$('#city').val()},
				success : function(data) {
					var opts = [];
					for(var i = 0; i < data.listArea.length; i++){
						var opt={};
						opt['name'] = data.listArea[i].name;
						opt['value'] = data.listArea[i].code;
						opts.push(opt);
					}
					$('.place .selector:eq(2) .text div').html('');
					$('#county').data('jSelect').update(opts);
				},
				error: function(data) {
					a_alert('网络错误');
				}
			});	
		}
	});
	$('#county').jSelect({
		options : [],
		onSelect : function() {
		}
	});
	$.ajax({
		type : "POST",
		async : false,
		url : $('#ctx').val()+"/toolJson/initArea.do",
		dataType : 'json',
		data : {'upCode':''},
		success : function(data) {
			var opts = [];
			for(var i = 0; i < data.listArea.length; i++){
				var opt={};
				opt['name'] = data.listArea[i].name;
				opt['value'] = data.listArea[i].code;
				opts.push(opt);
			}
			$('#province').data('jSelect').update(opts);
		},
		error: function(data) {
			a_alert('网络错误');
		}
	});	
}

function initAccidentReasonSelector(){
	var opts = [
	                	{
	                		name : '意外',
	                		value : '1'
	                	},
	                	{
	                		name : '疾病',
	                		value : '2'
	                	}
		             ];
		$('#accidentReason').jSelect({
			options : opts,
			onSelect : function() {
			}
		});
}

function initAccidentStatusSelector(){
	var opts = [
            	{
            		name : '门诊',
            		value : '01'
            	},
            	{
            		name : '住院',
            		value : '02'
            	},
            	{
            		name : '综合',
            		value : '03'
            	}
             ];
	$('#accidentStatus').jSelect({
		options : opts,
		onSelect : function() {
		}
	});
}

function updateDaySelector(year, month, day) {
	var year_str = year.data('jSelect').getValue();
	var month_str = month.data('jSelect').getValue();
	if (year_str && month_str) {
		var date = DayNumOfMonth(year_str, month_str);
		var days = [];
		for ( var i = 0; i < date; i++) {
			days.push({
				name : i + 1,
				value : i + 1
			});
		}

		day.data('jSelect').update(days);
	}
}

function checkIdCard(){
// 	console.log("checkIdCard(prefix: "+prefix+", index: "+index+", prefixTag: "+prefixTag+")...");
//	var _idType = "IdType";
//	var _idNo = "IdNo";
//	var _year = "_year";
//	var _month = "_month";
//	var _day = "_day";
//	var birthday = "Birthday";
//	var sex = "Sex";
	var idType = $("#accidentIdType").val();
	var idNo = $("#accidentId").val();
	if(idType!= null && idType == '0' && idNo != ''){
		if(idNo.length == 18  && isCardID(idNo) == true){
			//设置性别、出生日期为不可编辑
			$("#accidentBirthYear").data('jSelect').disable();
			$("#accidentBirthMonth").data('jSelect').disable();
			$("#accidentBirthDay").data('jSelect').disable(); 
			$("#male").attr({disabled:"disabled"});
			$("#female").attr({disabled:"disabled"});
			var returnStr = doCardIDGetBirthdayAndSex(idNo);
// 			console.log(returnStr);
			//true,北京,1986-5-23,M
			//console.log(returnStr.split(',')[0]);
			if(returnStr.indexOf(',',0) > -1 && returnStr.split(',')[0]=='true'){
				var birthday_1 = returnStr.split(',')[1];
				var y = birthday_1.split("-")[0];
				var m = birthday_1.split("-")[1];
				var d = birthday_1.split("-")[2];
				$("#accidentBirthYear").data('jSelect').setValue(y);
				$("#accidentBirthMonth").data('jSelect').setValue(m);
				$("#accidentBirthDay").data('jSelect').setValue(d);
				if(returnStr.split(',')[2] == "M"){
					if(!$("#male").hasClass('selected')){
						$("#male").addClass('selected');
						$("#male").siblings().removeClass('selected');
					}
					$("#accidentSex").val('0');
				}else if(returnStr.split(',')[2] == "F"){
					if(!$("#female").hasClass('selected')){
						$("#female").addClass('selected');
						$("#female").siblings().removeClass('selected');
					}
					$("#accidentSex").val('1');
				}
			}
		}else{
			//console.log(isCardID(idNo));
			//设置性别、出生日期为可编辑
			$("#accidentBirthYear").data('jSelect').enable();
			$("#accidentBirthMonth").data('jSelect').enable();
			$("#accidentBirthDay").data('jSelect').enable();
			$("#male").removeAttr("disabled");
			$("#female").removeAttr("disabled");
		}
	}else{
		$("#accidentBirthYear").data('jSelect').enable();
		$("#accidentBirthMonth").data('jSelect').enable();
		$("#accidentBirthDay").data('jSelect').enable();
		$("#male").removeAttr("disabled");
		$("#female").removeAttr("disabled");
	}
}

function isCardID(sId){ 
	var iSum=0 ;
	var info="" ;
	if((!/^\d{17}(\d|x)$/i.test(sId)) && (!/^\d{14}(\d|x)$/i.test(sId))) return "你输入的身份证长度或格式错误";
	if(sId.length == 18){ 
		sId=sId.replace(/x$/i,"a"); 
		sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
		var d=new Date(sBirthday.replace(/-/g,"/")) ;
		if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法"; 
		for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
		if(iSum%11!=1) return "你输入的身份证号非法";
		return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女") 
	}else if(sId.length == 15){
		return true;
	}
}

function doCardIDGetBirthdayAndSex(sId){ 
	var iSum=0 ;
	var info="" ;
	if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误"; 
	sId=sId.replace(/x$/i,"a"); 
	sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2)); 
	var d=new Date(sBirthday.replace(/-/g,"/")) ;
	if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法"; 
	for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
	if(iSum%11!=1) return "你输入的身份证号非法"; 
	return "true,"+sBirthday+","+(sId.substr(16,1)%2?"M":"F") 
}