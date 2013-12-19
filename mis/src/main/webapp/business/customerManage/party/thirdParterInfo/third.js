//\create\frmCreate.jsp start********************************************************************************************
	/**
	 * 插入一个表格
	 */
	var num = 1;
	(function($){
	    $.getUrlParam = function(name)
	    {
	        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if (r!=null) return unescape(r[2]); return null; 
	    }
	})(jQuery);
	function insertTable(){
		var ab = $("#tableInfoHidden").find("tr:first>td");
		var a = "<tr id='" + num + "'><td>" + $(ab).html()+ "</td></tr>";
		$("#tableInfo").append(a);	
		$("#" + num).find("input").each(function(index, value){
			if($(this).attr("type")!=="button"){
				$(this).attr("value","");//清空值
				$(this).attr("id",$(this).attr("name") + num);
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.addId($(this).attr("id"));
			//	alert(index+"--------------"+$(this).attr("id"));
				if(index==3){//联系电话
					//tttel.addId($(this).attr("id"));
					ttMobile.addId($(this).attr("id"));
				}
				if(index==4){
					ttvfemail.addId($(this).attr("id"));
				}
			}
		});
		$("#"+num).find("textarea").each(function(index,value){
			$(this).attr("value","");//清空值
			$(this).attr("id",$(this).attr("name") + num);
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			ttvf.addId($(this).attr("id"));
		});
		$("#" + num).find("select").each(function(index, value){//将select值还原
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		num++;
		setCountNum();//填写序号
	}
	//公共部分:\create\frmCreate.jsp需要使用;thirdParterInfo\update\index.jsp需要使用
	function setCountNum(){//填写序号
		var spans = $("span");
		var j = 0;
		for(var i=0;i<spans.length;i++){
			if(spans[i].id=="count"){
				//spans[i].innerText = "联系人"+(j);
				$(spans[i]).text("联系人"+j);
				j ++;
			}
		}
	}

	//检查业务领域
	function checkBusiness(){
		var businessAreas = document.getElementsByName("BusinessArea");
		var flag = false;
		for(var i=0;i<businessAreas.length;i++){
			if(businessAreas[i].checked){
				flag=true;
			}
		}
		return flag;
	}
	
	function doCreate(){//提交表单
		if(!checkBusiness()){
			alert("请选择业务领域");
			return false;
		}
		//check 业务领域
		checkIDNO();//当选择身份证时，验证证件号码
		if(!tt.validate()){
			//alert("验证框架出错");
			return false;
		}
		setNames();//设置表单各元素的name属性，符合struts2的自动装配
		document.getElementById("frmInput").submit();
	}
	//设置各对象的名字
	function setNames(){//设置表单各元素的name属性，符合struts2的自动装配
		var userNames = document.getElementsByName("userName");
		var identifyTypes = document.getElementsByName("identifyType");
		var identifyNumbers = document.getElementsByName("identifyNumber");
		var sexs = document.getElementsByName("sex");
		var birthdays = document.getElementsByName("birthday");
		var telePhones = document.getElementsByName("telePhone");
		var contactAddresses = document.getElementsByName("contactAddress");
		var emails = document.getElementsByName("email");
		for(var i=0;i<userNames.length;i++){
			if((i+1)!=userNames.length){
				userNames[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].userName";
				identifyTypes[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].identifyType";
				identifyNumbers[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].identifyNumber";
				sexs[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].sex";
				birthdays[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].birthday";
				telePhones[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].telePhone";
				contactAddresses[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].contactAddress";
				emails[i+1].name="geThirdParterInfo.geThirdParterContacts["+i+"].email";
			}
		}
	}
	//检查身份证号码 \create\frmCreate.jsp需要使用,thirdParterInfo\update\index.jsp需要使用
	function checkIDNO(){
		var identifyNumbers = document.getElementsByName("identifyNumber");
		for(var i=0;i<identifyNumbers.length;i++){
			if((i+1)!=identifyNumbers.length){
				validateIDNO(identifyNumbers[i+1]);
			}
		}
		
	}
	//验证身份证的核心内容   \create\frmCreate.jsp需要使用,thirdParterInfo\update\index.jsp需要使用   
	function validateIDNO(field){//对于证件号码是身份证的判断
		var tableObj = $(field).parent().parent().parent();
		//遍历表格找到该证件号码对应的证件类型
		var IDTypeValue;//证件类型
		$(tableObj).find("select").each(function(index,value){
			if(index==0){
				IDTypeValue = $(this).val();//给证件类型赋值
			}
			
		});
		if(IDTypeValue != "" && IDTypeValue == "01"){
			if(field.value != ""){
				$(field).removeAttr("ttTalentReqStarId");
				$(field).removeAttr("ttTalentMsgId");
				$(field).parent().find("span").remove();
				$(field).removeClass("talentErrInput");
				ttidCard.addId(field.id);//添加指定的证件号码 需要
			}
		}else{
			$(field).removeAttr("ttTalentReqStarId");
			$(field).removeAttr("ttTalentMsgId");
			$(field).parent().find("span").remove();
			$(field).removeClass("talentErrInput");
			ttidCard.rmId(field.id);
		}
	}
//frmCreate.jsp end********************************************************************************************	
	
	
	
//thirdParterInfo\update\index.jsp  start**********************************************************************
	var updateNum=1;
	var ttvf = tt.vf.req;
	var ttvfemail=tt.vf.email;
	var ttidCard = tt.vf.idcard;
	var tttel = tt.vf.tel;
	var ttMobile = new tt.RV().set(new RegExp("^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$"), "手机号不正确！");
	var ttCompanyPhone = new tt.RV().set(new RegExp("^[0-9]{1}[0-9-]+$"), "公司电话不正确！")
	//页面加载时
	function setUpdate(){
		selectProvince();
//		selectCity();
		var i=0;
		$("#tableInfo").find("table").each(function(index,value){
			updateNum++;
			i++;
		});
		//set default
		ttvf.addId("thirdParterName");//公司名称
		ttvf.addId("companyPhone");//公司电话
		ttvf.addId("address");//公司地址
		//ttvf.addId("areaSubordinate");//公司下属地区
		ttvf.addId("infoUrl");//公司网址
		ttvf.addId("companyEmail");//公司电子邮箱
		ttvf.addId("companyType");//公司类型
		ttvf.addId("mechanismArea");//活动地域
		ttvf.addId("cityId");//活动地域
		ttCompanyPhone.addId("companyPhone");
		new tt.LV().set(0,200).add("geThirdParterInfo.address");//活动方式字数限制
		
		for(var j=0;j<i;j++){
			ttvf.addId("userName"+j);//联系人姓名
			ttvf.addId("identifyNumber"+j);//证件号码
			ttvf.addId("birthday"+j);//出生日期
			ttvf.addId("telePhone"+j);//联系电话
			ttvf.addId("contactAddress"+j);//联系地址
			ttvf.addId("email"+j);//联系人邮箱
			ttvfemail.addId("email"+j);//联系人邮箱
			//tttel.addId("telePhone"+j);//联系电话
			ttMobile.addId("telePhone"+j);//联系电话
		}
		//email
		ttvfemail.addId("companyEmail");//公司电子邮箱
		
		
		//设置页面的checkBox的选中
		var businessAreas = document.getElementById("businessAreaId").value;
		var businessAreaDoms = document.getElementsByName("geThirdParterInfo.businessArea");
		var businessAreasTemp =businessAreas.split(",");
		for(var i=0;i<businessAreasTemp.length;i++){
			for(var j=0;j<businessAreaDoms.length;j++){
				if(businessAreasTemp[i]==businessAreaDoms[j].value){
					businessAreaDoms[j].checked=true;
				}
			}
		}
		
	}
	//修改操作时插入表格
	/*function insertTableForUpdate(){
		var ab = $("#tableInfoHiddenForUpdate").find("tr:first>td");
		if(!isNaN($("tr[id!='']").length)&&$("tr[id!='']").length>0){//判断取到的<tr>值是否为数字
			updateNum=$("tr[id!='']").length;
		}
		var a = "<tr id='" + updateNum + "'><td>" + $(ab).html()+ "</td></tr>";
		$("#tableInfo").append(a);	
		$("#" + updateNum).find("input").each(function(index, value){
			if($(value).attr("type")!="button"){//如果是按钮不清空
				$(this).attr("id",$(this).attr("name") + updateNum);
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.addId($(this).attr("id"));
				if(index==3){
					//tttel.addId($(this).attr("id"));
					ttMobile.addId($(this).attr("id"));
				}
				if(index==5){
					ttvfemail.addId($(this).attr("id"));
				}
				$(this).attr("value","");//清空值
			}
		});
		//将select值还原
		$("#" + updateNum).find("select").each(function(index, value){
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		updateNum++;
		setCountNum();//填写序号
	}*/
	/**
	 * 编辑时的插入table
	 */
	function insertTableForUpdate(){
		var ab = $("#tableInfoHiddenForUpdate").find("tr:first>td");
		var a = "<tr id='tableInfoHiddenForUpdate_tr" + updateNum + "'><td>" + $(ab).html()+ "</td></tr>";
		$("#tableInfo").append(a);
		$("#tableInfoHiddenForUpdate_tr" + updateNum).find("input").each(function(index, value){
			
			$(this).attr("id",$(this).attr("name") + updateNum);
			if($(value).attr("type")!="button"&&$(value).attr("type")!="hidden"){//排除button按钮  排除隐藏域
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				
				ttvf.addId($(this).attr("id"));
				if(index==3){
					//tttel.addId($(this).attr("id"));
					ttMobile.addId($(this).attr("id"));
				}
				if(index==4){
					ttvfemail.addId($(this).attr("id"));
				}
				if($(value).attr("name")=="itemName"){//当是供应商的时候，需要设置供应商的名字的序号,在删除的时候也需要重新设置
					var itemNames = document.getElementsByName("itemName");
					$(value).attr("nameCount",(itemNames.length-1));
				}
//				if(index!=2){//供应商公司代码  不清空 
					$(this).attr("value","");//清空其它值
//				}
			}
		});
		$("#tableInfoHiddenForUpdate_tr" + updateNum).find("textarea").each(function(index, value){
			   $(this).attr("id",$(this).attr("name") + updateNum);
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				
				ttvf.addId($(this).attr("id"));
			
		});
		//textArea添加id
		/*$("#" + updateNum).find("textArea").each(function(index, value){
			$(this).attr("id",$(this).attr("name") + updateNum);
			if(index==0){
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				//alert($(this).attr("id"));
				//ttvf.addId($(this).attr("id"));
			}
		});	*/	
		
		//将select值还原
		$("#tableInfoHiddenForUpdate_tr" + updateNum).find("select").each(function(index, value){
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			$(this).attr("id",$(this).attr("name") + updateNum);
			if($(this).attr("name")!="discountType"){//打折类型的不加验证域
				ttvf.addId($(this).attr("id"));
			}
			//
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		
		//清空临时变量的值
	//	document.getElementById("dateTempId").value="";
		updateNum++;
		setCountNum();//填写序号
	}
	//修改按钮
	function doUpdate(){
		
		if(!checkBusiness()){
			alert("请选择业务领域");
			return false;
		}
		//身份证号码的校验
		checkIDNO();
		if(!tt.validate()){
			//alert("验证框架出错");
			return false;
		}
		setNameUpdate();
		document.getElementById("frmInput").submit();
	}
	//设置对象名字
	function setNameUpdate(){
		//var contactIDs = document.getElementsByName("contactID");
		var userNames = document.getElementsByName("userName");
		var identifyTypes = document.getElementsByName("identifyType");
		var identifyNumbers = document.getElementsByName("identifyNumber");
		var sexs = document.getElementsByName("sex");
		var birthdays = document.getElementsByName("birthday");
		var telePhones = document.getElementsByName("telePhone");
		var contactAddresses = document.getElementsByName("contactAddress");
		var emails = document.getElementsByName("email");
		for(var i=0;i<userNames.length;i++){
			//contactIDs[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].contactID";
			if((i+1)!=userNames.length){
				userNames[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].userName";
				identifyTypes[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].identifyType";
				identifyNumbers[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].identifyNumber";
				sexs[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].sex";
				birthdays[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].birthday";
				telePhones[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].telePhone";
				contactAddresses[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].contactAddress";
				emails[i+1].name = "geThirdParterInfo.geThirdParterContacts["+i+"].email";	
			}
		}
	}
//thirdParterInfo\update\index.jsp  end**********************************************************************

/**
	 * 删除一个表格
	 * @returns
	 */
	function deleteTable(feld){
		var tableObj = $(feld).parent().parent().parent().html();
		$(tableObj).find("input").each(function(index, value){
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			ttvf.rmId($(this).attr("id"));
			ttidCard.rmId($(this).attr("id"));
			//alert($(this).attr("id"));
			//alert(index+"--------------"+$(this).attr("id"));
			
			if(index==3){
				ttvf.rmId($(this).attr("id"));
				ttMobile.rmId($(this).attr("id"));
			}
			if(index==4){
				ttMobile.rmId($(this).attr("id"));
				ttvfemail.rmId($(this).attr("id"));
			}
			
			});
			/**
			 *  textarea 删除校验
			 */
			$(tableObj).find("textarea").each(function(index,value){
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				//$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.rmId($(this).attr("id"));
				ttidCard.rmId($(this).attr("id"));
				
			});
			
		//删除select域的样式
		$(tableObj).find("select").each(function(index,value){//删除input域的样式
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			ttvf.rmId($(this).attr("id"));
		});
		//
	//$(feld).parent().parent().parent().parent().parent().parent().remove();
		//序号           
		//var obj = $(feld).parent().parent().parent().parent().parent().parent();
		var tableObjOne = $(feld).parent().parent().parent().parent().parent().parent();
		//alert($(tableObjOne).html());
		$(tableObjOne).remove();

		var spans = $("span");
		var j=0;
		for(var i=0;i<spans.length;i++){
			if(spans[i].id=="count"){
				$(spans[i]).text("联系人"+j);
				j++;
			}
		}
	}
	/**
	 * 删除一个表格，里面包括与后台交互删除记录
	 * @param feld
	 * @param contactID
	 * @returns
	 */
	function deleteTableAjax(feld,contactID){
		$.ajax({
	  		  type: "POST",
	  		  async:false,
	  		  url: contextRootPath+'/party/deleteGeThirdParterContact.do',
	  		  dataType: 'text',
	  		  data: {'contactID':contactID},
	  		  success: function(backData){
	  			  if(backData == 'success'){//删除结点
	  				$(feld).parent().parent().parent().parent().remove();//通过父结点来找到表格
	  				//序号
	  				var spans = document.getElementsByTagName('span');
	  				for(var i=0;i<spans.length;i++){
	  					spans[i].innerText = "联系人"+(i+1);
	  				}
	  			  }
	  		  }
	  		});
	}
	/**
	 * 提交增加功能
	 * @returns
	 */
	function submitAddThirdParterInfoAndThirdParterContact(){
		verify();
		if(!tt.validate()){
			return false;
		}
		var contactNames = document.getElementsByName("contactName");
		var identifyTypes = document.getElementsByName("identifyType");
		var identifyNumbers = document.getElementsByName("identifyNumber");
		var sexs = document.getElementsByName("sex");
		var birthdays = document.getElementsByName("birthday");
		var telePhones = document.getElementsByName("telePhone");
		var address = document.getElementsByName("address");
		var emails = document.getElementsByName("email");
		
		for(var i=0;i<contactNames.length;i++){
			contactNames[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].userName";
			identifyTypes[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].identifyType";
			identifyNumbers[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].identifyNumber";
			sexs[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].sex";
			birthdays[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].birthday";
			telePhones[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].telePhone";
			address[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].contactAddress";
			emails[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].email";
			
		}
		$("form:first").submit();
	}
	/**
	 * 提交修改功能
	 * @returns
	 */
	function submitUpdateThirdParterInfoAndThirdParterContact(){
//		if(!tt.validate()){
//			return false;
//		}
		
		var userNames = document.getElementsByName("userName");
		var identifyTypes = document.getElementsByName("identifyType");
		var identifyNumbers = document.getElementsByName("identifyNumber");
		var sexs = document.getElementsByName("sex");
		var birthdays = document.getElementsByName("birthday");
		var telePhones = document.getElementsByName("telePhone");
		var address = document.getElementsByName("contactAddress");
		var emails = document.getElementsByName("email");
		var contactIds = document.getElementsByName("contactID");
		for(var i=0;i<userNames.length;i++){
			
			userNames[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].userName";
			identifyTypes[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].identifyType";
			identifyNumbers[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].identifyNumber";
			sexs[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].sex";
			birthdays[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].birthday";
			telePhones[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].telePhone";
			address[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].contactAddress";
			emails[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].email";
			//alert(contactNames[i].name);
		}
		//
		for(var i=0;i<contactIds.length;i++){
			contactIds[i].name = "geThirdParterInfo.geThirdParterContacts["+i+"].contactID";
		}
		$("form:first").submit();
	}
	//根据业务领域选择省
	function selectProvince(){
		var thirdParterID=$.getUrlParam('geThirdParterInfo.thirdParterID');
		document.getElementById("cityId").value="";//重置市选项
		document.getElementById("suv").style.display="none";//重置市选项
		//document.getElementById("cityId").style.display="none";//重置市选项
		var resultBusinessArea = "";//业务领域
		var infoOne;
		var BusinessAreas = document.getElementsByName("BusinessArea");
		for(var i=0;i<BusinessAreas.length;i++){
			if(BusinessAreas[i].checked){//选中的那个
				resultBusinessArea =BusinessAreas[i].value; 
			}
		}
		if(resultBusinessArea=="1"){//集团
			document.getElementById("cityId").style.display = "none";
			$("#authorityid").html("<option value='1'>全部</option>");
		}else{//寿财养老
			//document.getElementById("cityId").style.display = "";
			//调用ajax
			$.ajax({
		  		  type: "post",
		  		  async:false,
		  		  url: contextRootPath+'/party/selectProvince.do',
		  		  dataType:"json",
		  		  data: {'BusinessArea':resultBusinessArea,'thirdParter':thirdParterID},
		  		  success: function(data){
		  			  var backdataup=data.backdataup;
		  			  var deptBusinessArea=data.deptBusinessArea;
		  			  data  = data.backdata;
		  			  for ( var k = 0; k < backdataup.length; k++) {
		  				infoOne=backdataup[k][0];
		  			  }
		  			  if(deptBusinessArea!=null&&deptBusinessArea!=""){
		  				infoOne=deptBusinessArea;
		  			  }
		  			  //alert(infoOne);
		  			  if(data!=null&&data.length>0){
		  				  var info = "<option value=''>--请选择--</option>";
		  				  for(var i=0;i<data.length;i++){
		  					info = info+"<option id="+data[i][0]+" value="+data[i][0]+">"+data[i][1]+"</option>";
		  				  }
		  				
		  				  $("#mechanismArea").html(info);
		  				$("#"+infoOne).attr("selected",true);
		  				  
		  			  }
		  		  }
			});//end $.ajax
		}
		selectCity();
	}

	//根据省选择市
	function selectCity(){
		var authorityidDept;
		var thirdParterID=$.getUrlParam('geThirdParterInfo.thirdParterID');
		var ProvinceId = $("#mechanismArea").val();
		var province = $("#mechanismArea").val();
		if(province==""||province=="1"||province=="2"||province=="3"||province=="4"){//请选择 或 集团全部
			$("#suv").hide();//隐藏市
			$("#cityId").hide();//隐藏市
			$("#cityId").html("<option value='"+province+"'>全部</option>");
		}else{
		$("#suv").show();
		$("#cityId").show();
		var ProvinceId = $("#mechanismArea").val();
		document.getElementById("suv").style.display="block";//重置市选项
		//document.getElementById("cityId").style.display="block";//重置市选项
			$.ajax({
		  		  type: "post",
		  		  async:false,
		  		  url: contextRootPath+'/party/selectCity.do',
		  		  dataType:"json",
		  		  data: {'ProvinceId':ProvinceId,'thirdParterCity':thirdParterID},
		  		  success: function(data){
		  			 var municipalityFlag = data.municipalityFlag;
		  			 var authorityidDept=data.authorityidDeptId;
		  			
		  			  data  = data.backdata;
		  			  if(data!=null&&data.length>0){
		  				  var info = municipalityFlag ? "" : "<option value=''>--请选择--</option>";
		  				//  var info = "<option value=''>--请选择--</option>";
		  				  for(var i=0;i<data.length;i++){
		  					info = info+"<option id="+"a"+data[i][0]+" value="+data[i][0]+">"+data[i][1]+"</option>";
		  				  }
		  				  $("#cityId").html(info);
		  				
		  			  }
		  			$("#a"+authorityidDept).attr("selected","selected");
		  		  }
			});//end $.ajax
		}
		}


	function getStringUTFLength(str) {
	    var value = str.replace(/[^\x00-\xff]/g,"  ");
	    return value.length;
	}
	//字符长度校验
	function leftUTFString(str,len) {
	    if(getStringUTFLength(str)<=len)
	        return str;
	    var value = str.substring(0,len);

	    while(getStringUTFLength(value)>len) {
	        value = value.substring(0,value.length-1);
	    }
	    return value;
	}
	//公司地址校验
	function countAddress() {
	    var value = document.getElementById("address").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=200) {
	        document.getElementById("address").value = leftUTFString(document.getElementById("address").value,200);
	    }
	}
	//公司名称校验
	function countThirdParterName() {
	    var value = document.getElementById("thirdParterName").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=200) {
	        document.getElementById("thirdParterName").value = leftUTFString(document.getElementById("thirdParterName").value,200);
	    }
	}
	//超出字符控制
	function toBreakWord(){
		//公司地址超出控制换行
		if(document.getElementById("address")!=null){
		    var obj=document.getElementById("address");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\，/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
		//联系人地址
		/*
		if(document.getElementById("ctAddress")!=null){
		    var obj=document.getElementById("ctAddress");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\，/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
		*/
		//联系人地址
		if(document.getElementById("tharterName")!=null){
		    var obj=document.getElementById("tharterName");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\，/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
		//联系人地址
		if(document.getElementById("urlIndo")!=null){
		    var obj=document.getElementById("urlIndo");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\，/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
	}
	
	/**
	 * 校验字数长度
	 * @param field
	 * @returns {Boolean}
	 */
	function textAreaMaxLen(field){
		 var iMaxLen = parseInt(field.getAttribute('maxlength'));
		    var iCurLen = field.value.length;
		    if ( field.getAttribute && iCurLen > iMaxLen ){
		    	field.value = field.value.substring(0, iMaxLen);
		    	alert("最多输入150个字");
		    	return false ;
		    }
	}