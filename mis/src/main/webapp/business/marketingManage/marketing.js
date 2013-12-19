//marketingManage\create\frmCreate.jsp start********************************************************************************************
	var ttRange =  new tt.NRV().set(1, 99);//数字范围只到在1到99
//	var intRange = new tt.RV().set(new RegExp("^[0-9]+(\.[0-9]+)?$"), "请输入合法的数字");
	var intRange = new tt.RV().set(new RegExp("^[+-]?(([1-9]\\d*([.]\\d{1,2})?|0([.][1-9]\\d{0,1})?)|0([.]0[1-9])?)$"), "请输入合法数字，小数点后两位");
//	function isDigit(s) { 	
//		var patrn=/^[0-9]+(\.[0-9]+)?$/; 
////		var patrn=/^\d+(\.\d)?$/; 
//		if (!patrn.exec(s)) return false ;
//		return true ;
//	}
	(function($){
	    $.getUrlParam = function(name)
	    {
	        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if (r!=null) return unescape(r[2]); return null; 
	    }
	})(jQuery);
	var ttvf = tt.vf.req;//非空
	  //根据业务领域选择省
	function selectProvince(){
		var actId=$.getUrlParam('activityId');
		document.getElementById("cityId").value="";//重置市选项
		document.getElementById("cityId").style.display="none";//重置市选项
		var resultBusinessArea = "";//业务领域
		var BusinessAreas = document.getElementsByName("BusinessArea");
		for(var i=0;i<BusinessAreas.length;i++){
			if(BusinessAreas[i].checked){//选中的那个
				resultBusinessArea =BusinessAreas[i].value; 
			}
		}
		var infoOne;
		if(resultBusinessArea=="1"){//集团
			$("#authorityid").html("<option value='1'>全部</option>");
			$("#cityId").hide();
			$("#cityId").html("<option value='1'>全部</option>");
		}else{//寿财养老
			//调用ajax
			$.ajax({
		  		  type: "post",
		  		  async:false,
		  		  url: contextRootPath+'/marketing/selectProvince.do',
		  		  data:"json",
		  		  data: {'BusinessArea':resultBusinessArea,'actId':actId},
		  		  success: function(data){
		  			  var  activtyNaN=data.geDepartmentListUpdept;
		  			  var deptBusinessArea=data.deptBusinessArea;
		  			  data  = data.backdata;
		  			  for ( var k = 0; k < activtyNaN.length; k++) {
			  				infoOne=activtyNaN[k][0];
			  			  }
		  			  if(deptBusinessArea!=null&&deptBusinessArea!=""){
		  				infoOne=deptBusinessArea;
		  			  }
		  			  if(data!=null&&data.length>0){
		  				  var info = "<option value=''>--请选择--</option>";
		  				  for(var i=0;i<data.length;i++){
		  					info = info+"<option  id ="+data[i][0]+" value="+data[i][0]+">"+data[i][1]+"</option>";
		  				  }
		  				  $("#authorityid").html(info);
		  			  }
		  			  $("#"+infoOne).attr("selected",true);
		  		  }
			});//end $.ajax
		}
		selectCity();
	}
	/**
	 * 通过省选择市
	 */
	function selectCity(){
		var actId=$.getUrlParam('activityId');
		$("#cityId").val("");//重置市选项
		var province = $("#authorityid").val();
		if(province==""||province=="1"||province=="2"||province=="3"||province=="4"){//请选择 或 集团全部
			$("#suv").hide();//隐藏市
			$("#cityId").hide();//隐藏市
			$("#cityId").html("<option value='"+province+"'>全113部</option>");
		}else{
			$("#suv").show();//隐藏市
			$("#cityId").show();//显示 市
			$.ajax({
		  		  type: "post",
		  		  async:false,
		  		  url: contextRootPath+'/marketing/selectCity.do',
		  		  data:"json",
		  		  data: {'ProvinceId':province,'actId':actId},
		  		  success: function(data){
		  			 var authorityDept=data.authorityidDept;
		  			  var municipalityFlag = data.municipalityFlag;
		  			  data  = data.backdata;
		  			  
		  			  if(data!=null&&data.length>0){
		  				  var info = municipalityFlag ? "" : "<option value=''>--请选择--</option>";
		  				  for(var i=0;i<data.length;i++){
		  					info = info+"<option id="+"a"+data[i][0]+" value="+data[i][0]+">"+data[i][1]+"</option>";
		  				  }
		  				  $("#cityId").html(info);
		  			  }
		  			  $("#a"+authorityDept).attr("selected","selected");
		  		  }
			});//end ajax
		}
	}
	//选择打折规则，显示折扣id输入框
	function showDiscountID(){
		//取打折活动方式,看是否有打折活动
		var resetNode;
		var activityPatterns=$("select[name='activityPattern']");
		var ap;
		if($(".resetNode").length>0&&$(".clazz").length=='0'){
			for ( var k = 0; k < $("tr[class='resetNode']").length; k++) {
				if(k<=9){
				ap="<td align='right' class='clazz'><span style=\"font-weight: bold;padding-left: 200px\"> 折扣ID(选填)：</span><input  class=\"activityPatternDiscountID\" id=\"activityDiscountID"+k+"\" name=\"geAddServiceActivity.geActivitiesProducts["+k+"].discountID\" style=\"width: 170px;\" onkeyup=\"value=value.replace(/[\\\W]/g,'')\" type=\"text\" maxLength=\"100\"/></td>";
				if($("#resetNode"+k).val()==undefined){
					resetNode=$("#productType"+k);
				}else{
					resetNode=$("#resetNode"+k);//.find("td:last");
				}
				
				resetNode.append(ap);
				}else{
					ap="<td align='right' class='clazz'><span style=\"font-weight: bold;padding-left: 190px\"> 折扣ID(选填)：</span><input  class=\"activityPatternDiscountID\" id=\"activityDiscountID"+k+"\" name=\"geAddServiceActivity.geActivitiesProducts["+k+"].discountID\" style=\"width: 170px;\" onkeyup=\"value=value.replace(/[\\\W]/g,'')\" type=\"text\" maxLength=\"100\"/></td>";
					if($("#resetNode"+k).val()==undefined){
						resetNode=$("#productType"+k);
					}else{
						resetNode=$("#resetNode"+k);//.find("td:last");
					}
					
					resetNode.append(ap);
				}
			}
		}
		//看产品处是否有产品，有产品，给每个产品添加折扣ID
		//没有产品，不做任何操作,等待添加产品时带出折扣 id
		//活动方式没有打折活动方式，取消折扣id输入框和验证
	}
	//取消产品打折id输入
	function  remDiscountId(){
		var flag=0;
		var discountArray = new Array();
		var activityPatterns=$("select[name='activityPattern']"); 
		for ( var i = 0; i < activityPatterns.length; i++) {
			discountArray[i]=activityPatterns[i].value;
			if(activityPatterns[i].value=='4'){
				flag++;	
			}
		}
		if(flag>0){
			return true;
		}
		if(flag=='0'){
			$(".clazz").remove();
		}
	}
	
	/**
	 * 插入一个表格
	 */	
	var num = 1;
	function insertTable(){
		var ab = $("#tableInfoHidden").find("tr:first>td");
		var a = "<tr id='" + num + "'  style=\"height:30px;\"><td>" + $(ab).html()+ "</td></tr>";//为重置按钮提供name='classNonoNum"+num+"' class='classNonoNum1'
		$("#tableInfo").append(a);	
		//input
		$("#" + num).find("input").each(function(index, value){//input域
			$(this).attr("id",$(this).attr("name") + num);
			if($(value).attr("type")!="button"&&$(value).attr("type")!="hidden"){//排除button ,隐藏域
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				
				if($(value).attr("name")=="itemName"){//当是商品的时候，需要设置商品的名字的序号,在删除的时候也需要重新设置
					var itemNames = document.getElementsByName("itemName");
					$(value).attr("nameCount",(itemNames.length-1));
				}
				$(this).attr("value","");//清空其它值
			}
		});
		
		//textArea 的输入域的
		$("#" + num).find("textArea").each(function(index, value){
			$(this).attr("id",$(this).attr("name") + num);
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			$(this).attr("id",$(this).attr("name") + num);
		});
		
		//将select值还原
		$("#" + num).find("select").each(function(index, value){
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			$(this).attr("id",$(this).attr("name") + num);
			if($(this).attr("name")!="discountType"){
				ttvf.addId($(this).attr("id"));
			}
			//
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		
		num++;
		
		setCountNum();
	}
	
	//公共部分,设置序号
	function setCountNum(){//填写序号
		var spans = $("span");
		var j = 0;
		for(var i = 0;i < spans.length; i++){
			if(spans[i].id == "count"){
				$(spans[i]).text("规则"+j);
				j ++;
			}
		}
	}
	//*********************************多行中的每一行的显示与隐藏start*******************
	function cancelItemName(field){//商品名称 
		field.style.display="none";//显示
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id);
			}
		});
		       
	}
	
	function cancelDiscountType(field){//打折类型
		field.style.display="none";//显示
		$(field).find("select").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //去掉验证
			}
		});
	}
	
	function cancelNvalueInit(field){
		field.style.display="none";//显示
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				intRange.rmId(valueObj.id); //去掉验证
			}
		});
	}
	
	function cancelNvalue(field){//n的值
		field.style.display="none";//显示
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //去掉验证
			}
		});
	}
	
	function cancelDiscountID(field){//折扣ID
		field.style.display="none";//显示
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //去掉验证
			}
		});
	}
	
	function cancelDiscountRemarkText(field){//打折因子描述
		field.style.display="none";//显示
		$(field).find("textArea").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //去掉验证
			}
		});
	}

	//隐藏本表格的所有加购产品 
	function displayShoppingProductFlag(displayFlag,innerTable){
		if(displayFlag==""||displayFlag=="1"||displayFlag=="2"||displayFlag=="3"||displayFlag=="4"){
			innerTable.find("input").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){//即加购产品名称那个输入域
					//将ID去除掉
					//ttvf.rmId($(value).attr("id"));//去掉 
					$(value).parent().parent().remove();//隐藏
				}
				if($(value).attr("typeProduct")=="addShoppingProductCode"){//即加购产品名称那个输入域
					//将ID去除掉
					//ttvf.rmId($(value).attr("id"));
					$(value).parent().parent().remove();//隐藏
				}
			});
		}
	}
	//*********************************多行中的每一行的显示与隐藏end*******************
	//*********************************多行中的每一行的显示与显示start*******************
	function addValidateItemName(field){//商品名称
		field.style.display ="";
		var id;
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);//增加验证
				id = valueObj.id;
				return;
			}
		});
		return id;
	}
	function addValidateDiscountType(field){//打折类型
		field.style.display ="";
		var id;
		$(field).find("select").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);
				id = valueObj.id;
				return;
			}
		});
		return id;
	}

	//intRange 只能输入正整数的处理
	function addValidateNvalueInit(field){
		field.style.display ="";
		var id;
		var type=$(field).attr('at');
		//alert($(field).html());
		if(type=='2'){
			$(field).find('.dazhe').text('前N的值：');
		}else if(type=='3'){
			$(field).find('.dazhe').text('概率N%的值：');
		}
		else if(type=='4'){
			$(field).find('.dazhe').text('打折率(%)/打折价(元)：');
		}
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				intRange.addId(valueObj.id);//增加验证  正整数的处理
				id = valueObj.id;
				return;
			}
		});
		return id;
	}

	function addValidateNvalue(field){//n的值
		field.style.display ="";
		var id;
		var type=$(field).attr('at');
		if(type=='2'){
			$(field).find('.dazhe').text('前N的值：');
		}else if(type=='3'){
			$(field).find('.dazhe').text('概率N%的值：');
		}
		else if(type=='4'){
			$(field).find('.dazhe').text('打折率(%)/打折价(元)：');
		}
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);//增加验证
				id = valueObj.id;
				return;
			}
		});
		return id;
	}
	function addValidateDiscountID(field){//折扣ID
		field.style.display ="";
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//折扣ID不校验
				//ttvf.addId(valueObj.id);//增加验证
			}
		});
	}
	function addDiscountRemarkText(field){//打折因子描述
		field.style.display ="";
		var id;
		$(field).find("textArea").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);//增加验证
				id =  valueObj.id;
				return;
			}
		});
		return id;
	}
	function addShoppingProductShow(field){//加购产品 
		field.style.display ="";
	}
	//*********************************多行中的每一行的显示与显示start*******************

	//设置n的值是否显示
	var discountFlag = true;
	function currentAddCheck(id,array){
		this.id = id;
		this.checkIdsarray = array;
	}
	var array = new Array();
	
	function getNvalueCheck(id){
		for(var i=0;i<array.length;i++){
			if(array[i].id == id){
				return array[i];
			}
		}
		return null;
		
	}

	function setNValueDisplay(field){
		var currentArray = new Array();
		var displayFlag = field.value;
		
		var obj ;
		if(getNvalueCheck(field.id) != null){
			obj = getNvalueCheck(field.id)
			
		}else{
			obj =new currentAddCheck(field.id,currentArray);
			array.push(obj);
		}
		
		var oldarray = obj.checkIdsarray;
		for(var i=0;i<oldarray.length;i++){
			if(oldarray[i] != undefined&&oldarray[i]!=''){
				ttvf.rmId(oldarray[i]);
				intRange.rmId(oldarray[i]);
			}
		}
		
		var innerTable = $(field).parent().parent().parent();
		$(innerTable).find("tr").each(function(index,value){
			if(displayFlag==""){//请选择
				if(index==4){//商品名称 隐藏 取消验证规则
					var itemID = cancelItemName(value);
				}
				if(index==5){//打折类型 隐藏 取消验证规则
					cancelDiscountType(value);
				}
				
				if(index==6){//n的值  隐藏 取消验证规则
					remDiscountId();
					$(value).attr('at',displayFlag);
					cancelNvalue(value);//取消非空的验证
					cancelNvalueInit(value)//取消正整数的验证
					
				}
				
				if(index==7){//折扣ID  隐藏 取消验证规则  不要添验证
					cancelDiscountID(value);
				}
				
				if(index==8){//打折因子描述  隐藏 取消验证规则
					cancelDiscountRemarkText(value);
				}
				
				if(index==9){//加购产品  隐藏 取消验证规则
					value.style.display="none";//不显示
					displayShoppingProductFlag(displayFlag,innerTable);//去掉验证
				}
			}
			
			if(displayFlag=="1"){//面对所有客户
				if(index==4){//商品名称 显示 增加验证规则
					var itemID = addValidateItemName(value);
					currentArray.push(itemID);
				}
				if(index==5){//打折类型 隐藏 取消验证规则
					cancelDiscountType(value);
				}
				
				if(index==6){//n的值  隐藏 取消验证规则
					remDiscountId();
					$(value).attr('at',displayFlag);
					cancelNvalue(value);//取消非空的验证
					cancelNvalueInit(value)//取消正整数的验证
				}
				
				if(index==7){//折扣ID  隐藏 取消验证规则  取消掉  
					cancelDiscountID(value);
				}
				
				if(index==8){//打折因子描述  隐藏 取消验证规则
					cancelDiscountRemarkText(value);
				}
				
				if(index==9){//加购产品  隐藏 取消验证规则
					value.style.display="none";//不显示
					displayShoppingProductFlag(displayFlag,innerTable);//去掉验证
				}
			}// end if(displayFlag=="1"){
			
			if(displayFlag=="2"){//面向前N名客户  这个N为正整数
				if(index==4){//显示 商品名称
					var itemID = addValidateItemName(value);
					currentArray.push(itemID);
				}
				if(index==5){//隐藏 打折类型
					cancelDiscountType(value);
				}
				if(index==6){//显示 n的值
					remDiscountId();
					$(value).attr('at',displayFlag);
					currentArray.push(addValidateNvalue(value));
					currentArray.push(addValidateNvalueInit(value));
				}
				if(index==7){//隐藏 折扣ID  
					cancelDiscountID(value);
				}
				if(index==8){//隐藏 打折因子描述

					cancelDiscountRemarkText(value);
				}
				if(index==9){//隐藏 加购产品 
					value.style.display="none";//不显示
					displayShoppingProductFlag(displayFlag,innerTable);//去掉验证
				}
			}
			
			if(displayFlag=="3"){//面向%N名客户
				if(index==4){//显示 商品名称
					currentArray.push(addValidateItemName(value));
				}
				if(index==5){//隐藏 打折类型
					cancelDiscountType(value);
				}
				if(index==6){//显示 n的值
					remDiscountId();
					//td显示N的值	
					$(value).attr('at',displayFlag);
					currentArray.push(addValidateNvalue(value));
					currentArray.push(addValidateNvalueInit(value));
				}
				if(index==7){//隐藏 折扣ID
					cancelDiscountID(value);
				}
				if(index==8){//隐藏 打折因子描述
					cancelDiscountRemarkText(value);
				}
				if(index==9){//隐藏 加购产品 
					value.style.display="none";//不显示
					displayShoppingProductFlag(displayFlag,innerTable);//去掉验证
				}
			}
			
			if(displayFlag=="4"){//打折的处理
				if(index==4){//隐藏商品名称
					cancelItemName(value);
				}
				if(index==5){//显示 打折类型
					currentArray.push(addValidateDiscountType(value));
				}
				if(index==6){//显示 n的值
					showDiscountID();
					//td显示根据打折类型 来确定对应的值
					$(value).attr('at',displayFlag);
					cancelNvalue(value);
					currentArray.push(addValidateNvalueInit(value));
				}
				if(index==7){//显示 折扣ID
					currentArray.push(addValidateDiscountID(value));
				}
				if(index==8){//显示 打折因子描述
					currentArray.push(addDiscountRemarkText(value));//出错了 
				}
				if(index==9){//隐藏 加购产品
					value.style.display="none";//不显示
					displayShoppingProductFlag(displayFlag,innerTable);//去掉验证
				}
			}
			
			if(displayFlag=="5"){//加购产品
				if(index==4){//隐藏商品名称
					cancelItemName(value);
				}
				if(index==5){//隐藏 打折类型
					cancelDiscountType(value);
				}
				if(index==6){//隐藏 n的值
					remDiscountId();
					//td显示根据打折类型 来确定对应的值
					$(value).attr('at',displayFlag);
					cancelNvalue(value);
					cancelNvalueInit(value);
				}
				if(index==7){//隐藏 折扣ID
					cancelDiscountID(value);
				}
				if(index==8){//隐藏 打折因子描述
					cancelDiscountRemarkText(value);
				}
				if(index==9){//显示 加购产品 
					value.style.display="";//显示
					//displayShoppingProductFlag(displayFlag,innerTable);//去掉验证
				}
			}
			
		});
	}
	
	function cancelProductNameValidate(value){
		$(value).find("input").each(function(indexInput,valueInput){
			if(indexInput==1){//商品名称 
				ttvf.rmId(valueInput.id);
			}
		});
	}
	function getNValueId(field){
		var id="";
		$(field).find("INPUT").each(function(index,value){
			id = value.id;
			ttvf.rmId(id);
		});
		return id;
	}
	//入参是对应的行    给商品名称添加验证
	function addProductNameValidate(value){
		$(value).find("input").each(function(indexInput,valueInput){
			if(indexInput==1){//商品名称 
				ttvf.addId($(valueInput).attr("id"));
			}
		});
	}
	
	//入参是对应的行 给N的值添加验证
	function addNValueValidate(value){
		$(value).find("input").each(function(indexInput,valueInput){
			if(indexInput==0){//N的值 
				ttvf.addId($(valueInput).attr("id"));
			}
		});
	} 
	
	//提供给 活动方式  :1--请选择-- 2.加购    将相应的验证取消掉
	function cancelValidateForSelect(index,value){
		if(index==4||index==5){//商品名称   N的值  
			$(value).find("input").each(function(indexInput,valueInput){
				if(indexInput==0){//
					ttvf.rmId($(valueInput).attr("id"));
				}
			});
		}
		if(index=6){
			$(value).find("textarea").each(function(indexInput,valueInput){
				if(indexInput==0){//
					ttvf.rmId($(valueInput).attr("id"));
				}
			});
		}
	}
	//取消  innerTable 表格中的N的值验证  
	function cancelValidateForSelect1(innerTable){
		$(innerTable).find("tr").each(function(index,value){
			if(index==5){//N的值 的那行
				value.style.display="none";
				$(value).find("input").each(function(indexInput,valueInput){
					ttvf.rmId($(valueInput).attr("id"));  //将N的值的验证给取消掉
				});
			}
			if(index==6){//打折因子 的那行
				value.style.display="none";
				$(value).find("textArea").each(function(indexInput,valueInput){
					ttvf.rmId($(valueInput).attr("id"));  //将打折因子的值的验证给取消掉
				});
			}
		});
	}
	
	//打折因子去掉验证
	function cancelDiscountValidate(innerTable){
		
	}
	//加购产品 
	function addShoppingProduct(field){
		var deptId = document.getElementById("authorityid").value;
		if(deptId==""){
			alert("请先选择活动区");
		}else{
			var xRule = "";//第几条归则
			var yAddShopping=0 ;//第几条加购产品
			var yReal = 0;
			//先求纵坐标
			var obj = $(field).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
			xRule = obj.attr("id");
			//alert(xRule);
			//求横坐标
			var objAddShopping = $(field).parent().parent().parent().parent();
			objAddShopping.find("table").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){
					yAddShopping++;
					if(field.id==$(value).attr("id")){
						yReal = yAddShopping;
					}
				}
			});
			//选择要弹出的产品
			 var eids = getEids();//","间隔 "G300024";//"G300024,G200058,G300008";//从页面上获取的
			window.open(contextRootPath+"/marketing/prepareSelectAddShopping.do?operation=1&deptId="+deptId+"&eids="+eids+"&xRule="+(parseInt(num))+"&yAddShopping=0","加够产品");
		}
		
	}
	
	//增加加购产品
	var addShoppingCount = 0; 
	function addShopping(field){
		var trObj  = $(field).parent().parent().parent();
		var obj = $("#tableAddShoppingHidden");
		trObj.html("");
		trObj.append(obj.html());
		trObj.find("table").each(function(index,value){
			if($(this).attr("name")=="addshoppingProductCallBack"){////加购产品名称
				//设置input的ID
				$(this).attr("id",$(this).attr("name")+num+"0");
				addShoppingProduct(this);
				addShoppingCount++;
			}
		});
	}
	//insertTr  插入一个TR上传一个图片
	function insertTr(field){
		var trObj = $(field).parent().parent();
		$("#displayText").html(trObj.html());
		//var tr.append
		
		trObj.append($("#tableDelHidden").html());
	}
	
	//双击域获取供应商信息
	function getGeThirdParterInfo(field){
		var  deptId = document.getElementById("authorityid").value;
		if(deptId==""){
			alert("请选择活动地域");
		}else{
			var temp= $("select[name='premumOperator']");
			var itemNames= $("input[name='itemName']");
			var flag=0;
			for ( var i = 0; i <itemNames.size(); i++) {
				if(itemNames[i]==field){
					break;
				}else{
					flag=flag+1;
				}
			}
			var nameCount = flag;//$(field).attr("nameCount");
			//window.open(contextRootPath+"/business/marketingManage/create/selectGeThirdParterInfo/index.jsp?nameCount="+nameCount,"查询供应商" ,"top=100, left=100, width=900,height=600,toolbar=no");
//			window.open(contextRootPath+"/business/marketingManage/create/selectGeThirdParterService/index.jsp?deptId="+deptId+"&nameCount="+nameCount,"查询商品" ,"top=100, left=100, width=900,height=600,toolbar=no");
		window.open(contextRootPath+'/business/marketingManage/create/selectProduct/selectProductMore/importMoreProduct.jsp?deptId='+deptId+'&opertion=userMarketing&nameCount='+nameCount+'',"查询商品");
		}
	}
	//增加
	var productCount=1;
	var countTemp =0;
	function addProduct(type){
		var obj = $("#tableProductHidden");
		$("#productModel").append(obj.html());
		//开始加验证
		var coreProductNames = document.getElementsByName("coreProductName");
		var lastProductName = coreProductNames[coreProductNames.length-1];
		//给lastProductName赋值
		var addTemp = coreProductNames.length-1;
		$(lastProductName).bind("dblclick",function(){
			eval("selectProduct('"+addTemp+"')");
		});//设置ondblclick
		//设置ID
		$(lastProductName).removeAttr("ttTalentReqStarId");
		$(lastProductName).removeAttr("ttTalentMsgId");
		$(lastProductName).parent().find("span").remove();
		$(lastProductName).removeClass("talentErrInput");
		if(type=="update"){//更新
			$("#productModel").find("input").each(function(index,domObj){
				if($(domObj).attr("typeProduct")=="productName"){
					countTemp++;
				}
			});
			$(lastProductName).attr("id",$(lastProductName).attr("name")+countTemp);
			ttvf.addId($(lastProductName).attr("id"));
		}else{//创建
			$(lastProductName).attr("id",$(lastProductName).attr("name")+productCount);
			ttvf.addId($(lastProductName).attr("id"));
			productCount++;
		}
	}
	
	//删除本行
	function deleteOnself(field){
		//删除ID,取消验证
		var tdObj = $(field).parent();
		tdObj.find("input").each(function(index,value){
			if(index==0){
				ttvf.rmId($(this).attr("id"));
			}
		});
		$(field).parent().parent().remove();
		
	}
	function doCreate(){//提交表单
	//	showDiscountID();
		if(!tt.validate()){
			return false;
		}
//		if(!isPeremiu()){
//			return false;
//		}
		//如果有图片,判断图片的类型
		if(!judgePicture()){//判断图片
			return false;
		}
		if(!checkActivityPatternsRepeat()){
			return false;
		}
		if(!isProductAdd()){
			return false;
		}
		if(isAddShoppingNull()){//判断是否加购产品
			return false;
		}
		if(isPremiumRangeError()){//如果保费的值的范围错误，则提示
			return false;
		}
		if(!isDiscountIDNull()){
			return false;
		}
		if(!isProductAddServiceExist()){//看看其它产品是否存在该活动类型
			setNames();//设置表单各元素的name属性，符合struts2的自动装配
			//$("form:first").submit();
			document.getElementById("frmInput").submit();
		}else{
			setNames();//设置表单各元素的name属性，符合struts2的自动装配
			//$("form:first").submit();
			document.getElementById("frmInput").submit();
		}
	}//end doCreate();
	
	function   isPeremiu(){
		var activityPatterns=$("input[name='premiumRange2']");
		for ( var i = 1; i <= activityPatterns.length; i++) {
			//alert($("#premiumRange2a"+i+"").val());
			if($("#premiumRange2a"+i+"").val()==""){
			alert("活动规则，保费第二个值为空，请输入保费值！");	
			return false;
			}
		}
		return true;
	}
	
	//判断是否存在打折活动
	function isDiscountIDNull(){
		var flag =0;
		var discount=new Array();
		var activityPatterns=$("select:visible[name='activityPattern']");
		for(var i=0;i<activityPatterns.length;i++){
			discount[i]=activityPatterns[i].value;
			if(activityPatterns[i].value=="4"){
				for ( var j = 0; j < $(".activityPatternDiscountID").length; j++) {
					if($("#activityDiscountID"+j+"").val()!=""){
						//alert($("#activityDiscountID"+j+"").val());
						flag++;
					}
				}
			}
		}
		if(flag>0){
			return true;
		}else{
			for ( var l = 0; l < discount.length; l++) {
				if(discount[l]=='4'){
					alert("当前活动中存在打折规则，请至少添加一个折扣ID！");
					return false;
				}
			}
			for ( var j = 0; j < $(".activityPatternDiscountID").length; j++) {
				if($("#activityDiscountID"+j+"").val()!=""){
					alert("当前活动不存在打折规则，请删除产品折扣ID！");
					return false;
				}
			}
			return true;
		}
	}
	//加购
	function isAddShoppingNull(){
		var activityPatterns = document.getElementsByName("activityPattern");
		var haveAdd = false;
		var isAddShoppingExistFlag = false;
		for(var i=1;i<activityPatterns.length;i++){
			if(activityPatterns[i].value=="5"){//5为加购
				haveAdd = true;
				var addShoppingExistFlag = true;
				var tbodyObj = $(activityPatterns[i]).parent().parent().parent();
				tbodyObj.find("tbody").each(function(index,value){
					if(index==0){
						$(value).find("input").each(function(indexInput,valueInput){
							if(indexInput==1){
								addShoppingExistFlag = false;
							}
						});
						if(addShoppingExistFlag){
							alert("请选择加购产品");
							isAddShoppingExistFlag = true;
							return false;
						}
					}
				});
			}
		}//end for
		if(!haveAdd){//没有增值
			return false;
		}else{//有加购
			return isAddShoppingExistFlag;
		}
	}
	//产品为空验证
	function isProductAdd(){
		if($("input[name='coreProductCode']").length==0){
			alert("请选择产品");
			return false;
		}
		return true;
	}
	//活动修改产品为空验证
	function isProductAddForUpdate(){
		if($("input[class='coreProductCode']").length==0){
			alert("请选择产品");
			return false;
		}
		return true;
	}
	//判断该活动是否存在 
	function isProductAddServiceExist(){
		var flag = false;
		
		var coreProductNamesValue = "";
		var activityPatternsValue = "";
		var coreProductNames = document.getElementsByName("coreProductCode");//取产品
		var activityPatterns = document.getElementsByName("activityPattern");//活动方式
		var deptID = $("#authorityid").val();//地域代码
		var actStatus="";
		
		var startDate = document.getElementsByName("geAddServiceActivity.activityStartDate")[0].value;;//取开始日期
		var endDate = document.getElementsByName("geAddServiceActivity.activityEndDate")[0].value;//取结束日期
		//给数组赋值
		for(var i=0;i<coreProductNames.length;i++){
			coreProductNamesValue = coreProductNamesValue+coreProductNames[i].value +",";
		}
		for(var i=1;i<activityPatterns.length;i++){
			activityPatternsValue = activityPatternsValue+activityPatterns[i].value+",";
		}
		//去掉","
		coreProductNamesValue = coreProductNamesValue.substring(0,(coreProductNamesValue.length-1))
		activityPatternsValue = activityPatternsValue.substring(0,(activityPatternsValue.length-1))
		$.ajax({
	  		  type: "GET",
	  		  async:false,
	  		  url: contextRootPath+'/marketing/isProductAddServiceExist.do',
	  		  data:"json",
	  		  data: {'startDate':startDate,'endDate':endDate,'coreProductNames':coreProductNamesValue,'activityPatterns':activityPatternsValue,'deptID':deptID},
	  		  success: function(data){
	  			  data  = data.geCustomAddServiceActivityList;
	  			  if(data!=null){
	  				  var error = "";
	  				  
	  				  for(var i=0;i<data.length;i++){
	  					  if(data[i].status=='3'){
	  						actStatus="状      态：【已发布】\r";
	  					  }else{
	  						actStatus="";
	  					  }
	  					error = error+" \r"+"活动名称："+data[i].activityName+"\r"+"产品类型："+data[i].productName+"\r"+"活动日期：("+data[i].startDate+"-"+data[i].endDate+")上存在交集\r"+actStatus;
	  				  }
	  				  if(data.length==0){
	  					flag= true;
	  				  }else{
	  					 alert("当前活动与："+error);
	  				  }
	  			  }else{
	  				flag= true;
	  			  }
	  		  }
		});//end $.ajax
		
		return flag;
	}
	
	//设置各对象的名字
	function setNames(){//设置表单各元素的name属性，符合struts2的自动装配
		//设置图片的上传
		var uploadPictures = document.getElementsByName("uploadPicture");
		var uploadSerialNos= "";
		for(var i=0;i<uploadPictures.length;i++){
			if(uploadPictures[i].value!=""){
				uploadSerialNos = uploadSerialNos+(i+1)+",";
			}
		}
		uploadSerialNos = uploadSerialNos.substring(0,(uploadSerialNos.length-1));
		document.getElementsByName("uploadPictureSerialNos")[0].value=uploadSerialNos;
		//设置规则
		setPremiums();//设置保费类型
		setActivityPattern();//设置活动方式  及活动方式对应的值
	}
	/**
	 * 修改产品
	 */
	function upProductTest(){
		var productCallBack=$("#productCallBack");
		var activityPatterns=$("select[name='activityPattern']");
		var discount="";
		if(activityPatterns!=null&&activityPatterns!=undefined){
			for(var i=1;i<activityPatterns.length;i++){
				if(activityPatterns[i].value=="4"){
					discount=activityPatterns[i].value;
				}
			}
		}
		productCallBack.html("");
		 var deptId = (   $("#cityId").val()==""||$("#cityId").val()==null  )  ?    (  $("#authorityid").val() )  : $("#cityId").val();
		 if(deptId==""){
			 alert("请选择活动地区");
		 }else{
			 var eids = getEidsUpDate();//","间隔 "G300024";//"G300024,G200058,G300008";//从页面上获取的
			 window.open(contextRootPath+"/marketing/prepareSelectAddShopping1.do?deptId="+deptId+"&eids="+eids+"&discount="+discount,"增加产品");
			 
		 }
	}
	//得到eids以","间隔
	function getEidsUpDate(){
		var results = "";
		var eids = document.getElementsByName("upDateProductCode");
		for(var i=0;i<eids.length;i++){
			results = results+eids[i].value+",";
		}
		if(results==""){
			return "";
		}else{
			return results.substring(0,results.length-1);
		}
		
	}
	/**
	 * 增加产品
	 */
	function addProductTest(){
		var productCallBack=$("#productCallBack");
		var productCallBacktwo=$("#productCallBacktwo");
		var activityPatterns=$("select[name='activityPattern']");
		var discount="";
		if(activityPatterns!=null&&activityPatterns!=undefined){
			for(var i=1;i<activityPatterns.length;i++){
				if(activityPatterns[i].value=="4"){
					discount=activityPatterns[i].value;
					///alert("打折"+discount);
				}
			}
		}
		
		productCallBack.html("");
		productCallBacktwo.html("");
		$("#showInfo").hide();
		$("#productInfo").hide();
		 var deptId = (   $("#cityId").val()==""||$("#cityId").val()==null  )  ?    (  $("#authorityid").val() )  : $("#cityId").val();
		 if(deptId==""){
			 alert("请选择活动地区");
		 }else{
			 var eids = getEids();//","间隔 "G300024";//"G300024,G200058,G300008";//从页面上获取的
			 window.open(contextRootPath+"/marketing/prepareSelectAddShopping.do?deptId="+deptId+"&eids="+eids+"&discount="+discount,"增加产品");
			 
		 }
		
	}
	//得到eids以","间隔
	function getEids(){
		var results = "";
		var eids = document.getElementsByName("coreProductCode");
		for(var i=0;i<eids.length;i++){
			results = results+eids[i].value+",";
		}
		if(results==""){
			return "";
		}else{
			return results.substring(0,results.length-1);
		}
		
	}
	//设置保费类型  设置保费的值
	function setPremiums(){
		var _premumOperators=$("select[name='premumOperator']");//保费类型
		for(var i=1;i<_premumOperators.length;i++){
			var tbodyObj = $(_premumOperators[i]).parent().parent().parent();
			if(_premumOperators[i].value=="2"||_premumOperators[i].value=="3"||_premumOperators[i].value=="4"||_premumOperators[i].value=="5"){//premumOperators[i].value=="1"||
				//1不限  2小于 3大于 4两者之前
				tbodyObj.find("input").each(function(index,value){
					if(index==0){//保费的值
						$(value).attr("name","geAddServiceActivity.geActivitiesRules["+(i-1)+"].peremiumValue");
					}
				});
			}
			
			if(_premumOperators[i].value=="6"){//6两者之前
				var range1  ="";//保费的值 小于
				var range2 = "";//保费的值 大于
				tbodyObj.find("input").each(function(index,value){
					
					if(index==0){//保费的值小于
						range1 = $(value).attr("value");
					}
					if(index==1){//保费的值 大于
						range2 = $(value).attr("value");
					}
				});
				$("#frmInput").append("<input type=\"hidden\" name=\"geAddServiceActivity.geActivitiesRules["+(i-1)+"].peremiumValue\" value='"+range1+"@"+range2+"'/>");	
				
			}
			_premumOperators[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].premiumType"; 
		}
	}
	
	//设置活动方式  及活动方式对应的值
	function setActivityPattern(){
		var activityPatterns=$("select[name='activityPattern']");
		var itemIDs = $("input[name='itemID']");//商品ID
		var nvalues = $("input[name='nvalue']");//N的值
		var discountTypes = document.getElementsByName("discountType");//打折类型
		var discountRemarkTexts = document.getElementsByName("discountRemarkText");//打折因子
		
		for(var i=1;i<activityPatterns.length;i++){
			if(activityPatterns[i].value=="1"){//面向所有客户 
				itemIDs[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].itemID";//商品名称 
			}
			if(activityPatterns[i].value=="2"||activityPatterns[i].value=="3"){//面向前N名客户  面向概率为N的客户 or 打折
				itemIDs[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].itemID";//商品名称 
				nvalues[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].nvalue";//N的值
			}
	
			if(activityPatterns[i].value=="4"){//打折因子
				nvalues[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].nvalue";//N的值
				discountRemarkTexts[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].discountRemarkText";
				discountTypes[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].discountType";
			}
			
			if(activityPatterns[i].value=="5"){//面向加购产品 
				var tbodyObj = $(activityPatterns[i]).parent().parent().parent();
				$(tbodyObj).find("table[typeProduct='addShopping']").each(function(index,value){
					var countList = 0;
					$(value).find("input").each(function(indexInput,valueInput){
						if($(this).attr("typeProduct")=="addShoppingProductCode"){
							
							$(valueInput).attr("name","geAddServiceActivity.geActivitiesRules["+(i-1)+"].geActivitiesShoppingProducts["+(countList)+"].eid");
							countList++;
						}
					});
				});
			}
			activityPatterns[i].name= "geAddServiceActivity.geActivitiesRules["+(i-1)+"].activityPattern";
		}// end for();
	}
	//检查规则是否有相同的
	function checkActivityPatternsRepeat(){
		var a = new Array();//面向所有客户
		var b = new Array();//向向前N名客户
		var c = new Array();//面向前%n名客户
		var d = new Array();//打折
		var e = new Array();//加购
		
		var activityPatterns = document.getElementsByName("activityPattern");//活动方式
		for(var i=1;i<activityPatterns.length;i++){//从1天始排除隐藏域
			if(activityPatterns[i].value=="1"){
				a[a.length] = i;
			}
			if(activityPatterns[i].value=="2"){
				b[b.length] = i;
			}
			if(activityPatterns[i].value=="3"){
				c[c.length] = i;
			}
			if(activityPatterns[i].value=="4"){
				d[d.length] = i;
			}
			if(activityPatterns[i].value=="5"){
				e[e.length] = i;
			}
		}
		if(a.length>1){
			var temp="";
			for(var i=0;i<a.length;i++){
				temp = temp+"规则"+a[i]+"，";
			}
			var count = temp.lastIndexOf("，")
			temp = temp.substring(0,count)
			alert(temp+"的活动方式不能相同");
			return false;
		}
		if(b.length>1){
			var temp="";
			for(var i=0;i<b.length;i++){
				temp = temp+"规则"+b[i]+"，";
			}
			var count = temp.lastIndexOf("，")
			temp = temp.substring(0,count)
			alert(temp+"的活动方式不能相同");
			return false;
		}
		if(c.length>1){
			var temp="";
			for(var i=0;i<c.length;i++){
				temp = temp+"规则"+c[i]+"，";
			}
			var count = temp.lastIndexOf("，")
			temp = temp.substring(0,count)
			alert(temp+"的活动方式不能相同");
			return false;
		}
		if(d.length>1){
			var temp="";
			for(var i=0;i<d.length;i++){
				temp = temp+"规则"+d[i]+"，";
			}
			var count = temp.lastIndexOf("，")
			temp = temp.substring(0,count)
			alert(temp+"的活动方式不能相同");
			return false;
		}
		if(e.length>1){
			var temp="";
			for(var i=0;i<e.length;i++){
				temp = temp+"规则"+e[i]+"，";
			}
			var count = temp.lastIndexOf("，")
			temp = temp.substring(0,count)
			alert(temp+"的活动方式不能相同");
			return false;
		}
		return true;
	}
	//得到临时值
	function setDateTempId(count){
		var tempDate = document.getElementsByName("activityStartDate")[count].value;
		document.getElementById("dateTempId").value=document.getElementsByName("activityStartDate")[count].value;
	}
	//判断图片的类型
	function judgePicture(){
	var uploadPictures=	$("input[name='uploadPicture']");
		if(uploadPictures.length==1){//只是一个隐藏字段的,即没有要上传的图片
			return true;
		}else{//有要上传的图片
			
			for(var i=0;i<uploadPictures.length;i++){
				var filepath = uploadPictures[i].value;
				if(filepath!=""){
					var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
					extname = extname.toLowerCase();//处理了大小写
				    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
				    	alert("上传图片"+(i+1)+" 只能上传bmp,jpg,gif格式的图片！");
				     	return false;
				    }
				}// end if
			}
			return true;
		}
	}
	//根据
	function showPremum(field){
		var premiumType = field.value;
		var obj = $(field).parent().parent().parent();
		obj.find("tr").each(function(index,value){
			if(index==2){
				if(premiumType==""||premiumType=="1"){//请选择或不限
					value.style.display="none";//隐藏
					cancelValidate(value);//取消验证
				}
				if(premiumType=="2"||premiumType=="3"||premiumType=="4"||premiumType=="5"||premiumType=="6"){//小于 小于等于 大于 大于等于  两者之间
					value.style.display="";//显示 
					cancelValidate(value);
					setPremiumRange(premiumType,value);//保费的值 大于
				}
			}
		});
	}
	//取消验证
	function cancelValidate(field){
		$(field).find("input").each(function(index,value){
			ttvf.rmId($(this).attr("id"));
			intRange.rmId($(this).attr("id"));
		});
	}
	
	function setPremiumRange(premiumType,field){
		var htmlValue= "";
		
		if(premiumType=="2"){
			htmlValue = "保费的值小于：";
		}
		if(premiumType=="3"){
			htmlValue = "保费的值小于等于：";
		}
		if(premiumType=="4"){
			htmlValue = "保费的值大于：";
		}
		if(premiumType=="5"){
			htmlValue = "保费的值大于等于：";
		}
		if(premiumType=="6"){
			htmlValue = "保费的值两者之间：";
		}
		$(field).find("td").each(function(indexTD,valueTD){
			
			if(indexTD==0){//
				$(valueTD).html(htmlValue);
			}
			if(indexTD==1&&premiumType!="6"){
				$(valueTD).html("<input type=\"text\" name=\"premiumRange\" id=\"premiumRange"+num+"\" maxlength=10/> ");
			}else if(indexTD==1&&premiumType=="6"){
				$(valueTD).html("<input type=\"text\" size=\"15\" name=\"premiumRange1\" id=\"premiumRange1a"+num+"\" maxlength=10 /> <=保费的值<= <input type=\"text\" size=\"15\" name=\"premiumRange2\" id=\"premiumRange2a"+num+"\" maxlength=10 />");
				//加JS验证
				
				var id1 = "premiumRange1a"+num;
				ttvf.addId(id1);
				var id2 = "premiumRange2a"+num;
				intRange.addId(id1);
				intRange.addId(id2);
			}
			
		});
		
		//如果有INPUT,则增加校验
		$(field).find("input").each(function(index,value){
			
			ttvf.addId($(this).attr("id"));
			intRange.addId($(this).attr("id"));//设置正整数的验证规则
		});
	}//end setPremiumRange
	
	
	
	//marketingManage\create\frmCreate.jsp end********************************************************************************************
	
	//business\marketingManage\update\index.jsp start***************************************************************************************
	var updateNum=1;
	//ttRange
	//页面加载时
	function setUpdate(){

		
		var i=0;
		$("#tableInfo").find("table").each(function(index,value){
			if($(value).attr("updateType")=="update"){
				updateNum++;
				i++;
			}
		});
		//set default
		ttvf.addId("activityName");//活动名称
		ttvf.addId("activityContent");//活动内容介绍
		ttvf.addId("activityStartDateId");//活动起始时间
		ttvf.addId("activityEndDateId");//活动结束时间
//		tt.vf.req.add("geAddServiceActivity.province");////活动地域
//		tt.vf.req.add("geAddServiceActivity.deptID");////活动地域
		new tt.LV().set(0,2000).add("geAddServiceActivity.activityContent");//活动方式字数限制
		//产品名称
		$("#productModel").find("tr").each(function(index,domObj){
			if(index==0){//第一行
				$(domObj).find("input").each(function(indexInput,domInputObj){
					if(indexInput==1){
						ttvf.addId($(domInputObj).attr("id"));
					}
				});
			}else{//非第一行
				$(domObj).find("input").each(function(indexInput,domInputObj){
					if(indexInput==0){
						ttvf.addId($(domInputObj).attr("id"));
					}
				});
			}
		});
		for(var j=0;j<i;j++){
			ttvf.addId("premumOperator"+j);//保费
			ttvf.addId("activityPattern"+j);//活动方式
		}
		var actId=$.getUrlParam('activityId');
		selectProvince();
		addPreviewFaceInitForUpdate();//设置图片的预览
		addValidatePeremiumValue();//设置归则里保费的值添加验证
		addValidateItemID();//设置归则里的商品名称添加验证
		discountShowOrHide();//折扣ID是否显示
		remDiscountId();//页面加载时，如果活动方式没有打折，remove()掉所有的.clazz属性	
		
	}
	//折扣ID是否显示
	function discountShowOrHide(){
		var activityPatterns=$("select[name='activityPattern']");
		if(activityPatterns!=null&&activityPatterns!=undefined){
			for(var i=1;i<activityPatterns.length;i++){
				if(activityPatterns[i].value=="4"){
					$(".disShowOrHide").show();
				}
			}
		}
	}

	
	//设置图片的预览
	function addPreviewFaceInitForUpdate(){
		var hiddenImages = document.getElementsByName("hiddenImage");
		for(var i=0;i<hiddenImages.length;i++){
			if(hiddenImages[i].value!=""){
				ImagePreview.MODE = "simple";//让走simplle模式
				
				var facePic = new ImagePreview( $$(hiddenImages[i].id), $$("uploadPicture"+(i+1)+"Preview"), {maxWidth: 230, maxHeight: 160});
				document.getElementById("uploadPicture"+(i+1)+"TR").style.display="";
				//facePic.preview();
			}
		}
	}
	
	//包含了 保费的值  商品名称 的校验
	function addValidateItemID(){
		
		$("#tableInfo").find("table").each(function(index,domObj){
			
			if($(domObj).attr("updateType")==="update"){
				
				$(domObj).find("select").each(function(indexSelect,domSelectObj){
					
					if(indexSelect==0){//第一个选择域
						if($(domSelectObj).attr("value")=="2"||$(domSelectObj).attr("value")=="3"){//设置保费的值
							$(domObj).find("input").each(function(indexInput,domInputObj){
								if(indexInput==0){
									ttvf.addId(domInputObj.id);//设置保费的值
								}
							});
						}//end if
						
						if($(domSelectObj).attr("value")=="4"){//设置保费的值  两者之间
							var  obj=$(domObj).find("input:visible");
							$(domObj).find("input:visible").each(function(indexInput,domInputObj){
								if(indexInput==0||indexInput==1){
									ttvf.addId(domInputObj.id);//设置保费的值
								}
							});
						}
						
					}//end if(indexSelect==0){ 
					
					if(indexSelect==1){//第二个选择域 活动方式 
						if($(domSelectObj).attr("value")=="1"){//面对所有客户    需要添加商品的校验
							ttvf.addId(getId(domObj,"itemObj"));//设置商品的值
						}
						if($(domSelectObj).attr("value")=="2"||$(domSelectObj).attr("value")=="3"){//面向前N名客户  or N% 
							ttvf.addId(getId(domObj,"itemObj"));//设置商品的值
							
							ttvf.addId(getId(domObj,"nValue"));///设置N的值
							intRange.addId(getId(domObj,"nValue"));///设置N的值为正整数
						}
						
						if($(domSelectObj).attr("value")=="4"){//打折
							ttvf.addId(getId(domObj,"discountType"));//设置打折类型
							ttvf.addId(getId(domObj,"discountRemarkText"));//打折因子
							intRange.addId(getId(domObj,"nValue"));///设置N的值为正整数
						}
						
						if($(domSelectObj).attr("value")=="5"){//加购
							var addshoppingIds = getId(domObj,"addShopping");
							addshoppingIds = addshoppingIds.substring(0,(addshoppingIds.length-1));
							var idtemp = addshoppingIds.split(",");
							for(var i=0;i<idtemp.length;i++){
								//ttvf.addId(idtemp[i]);  //
							}
						}
						
					}
					
				});//end $(domObj).find("select")
			}
			
		});
		
	}
	//在给保费的值添加验证的方法
	function addValidatePeremiumValue(){
		//第一个值为隐藏域 
		//从第二个开始
		var premumOperatorTypes = document.getElementsByName("premumOperator");
		for(var i=1;i<premumOperatorTypes.length;i++){
			var premiumType = premumOperatorTypes[i].value;
			var obj = $(premumOperatorTypes[i]).parent().parent().parent();
			obj.find("tr").each(function(index,value){
				if(index==2){
					if(premiumType==""||premiumType=="1"){//请选择或不限
						value.style.display="none";//隐藏
					}
					if(premiumType=="2"||premiumType=="3"||premiumType=="4"||premiumType=="5"||premiumType=="6"){//小于 小于等于 大于 大于等于  两者之间
						//加验证归则
						$(value).find("input").each(function(indexInput,InputObj){
							ttvf.addId(InputObj.id);//必录项
							intRange.addId(InputObj.id);//必录项
						});
					}//
				}
			});
		}
	}
	
	function getId(domObj,type){
		var idString = "";
		$(domObj).find("tr").each(function(index,trObj){
			if(type=="itemObj"&&index==4){//商品
				$(trObj).find("input").each(function(indexInput,domOjb){
					if(indexInput==0){
						idString =  domOjb.id;
					}
				});
			}
			
			if(type=="discountType"&&index==5){//打折类型
				$(trObj).find("select").each(function(indexSelect,domObj){
					if(indexSelect==0){
						idString =domObj.id;
					}
				});
			}
			
			if(type=="nValue"&&index==6){//N的值
				$(trObj).find("input").each(function(indexInput,domOjb){
					if(indexInput==0){
						idString =  domOjb.id;
					}
				});
			}
			if(type=="discountRemarkText"&&index==8){//打折因子的值
				$(trObj).find("textarea").each(function(indexSelect,domObj){
					if(indexSelect==0){
						idString =domObj.id;
					}
				});
			}
			if(type=="addShopping"&&index==9){//加购
				$(trObj).find("input").each(function(indexInput,domObj){
					if($(domObj).attr("typeProduct")=="addShopping"){
						idString = idString+domObj.id+",";
					}
				});
			}
			
		});
		return idString;
	}
	function insertTableForUpdate(){
		var ab = $("#tableInfoHiddenForUpdate").find("tr:first>td");
		var a = "<tr id='" + updateNum + "'><td>" + $(ab).html()+ "</td></tr>";
		$("#tableInfo").append(a);
		$("#" + updateNum).find("input").each(function(index, value){
			$(this).attr("id",$(this).attr("name") + updateNum);
			if($(value).attr("type")!="button"&&$(value).attr("type")!="hidden"){//排除button按钮  排除隐藏域
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				
				//ttvf.addId($(this).attr("id"));
				if($(value).attr("name")=="itemName"){//当是供应商的时候，需要设置供应商的名字的序号,在删除的时候也需要重新设置
					var itemNames = document.getElementsByName("itemName");
					$(value).attr("nameCount",(itemNames.length-1));
				}
				//	if(index!=2){//供应商公司代码  不清空 
					$(this).attr("value","");//清空其它值
				//				}
			}
		});
		//textArea添加id
		$("#" + updateNum).find("textArea").each(function(index, value){
			$(this).attr("id",$(this).attr("name") + updateNum);
			if(index==0){
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				//ttvf.addId($(this).attr("id"));
			}
		});		
		
		//将select值还原
		$("#" + updateNum).find("select").each(function(index, value){
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
		document.getElementById("dateTempId").value="";
		updateNum++;
		setCountNum();//填写序号
	}
	
	function valid(){
		if(!checkActivityPatternsRepeat()){
			
			return false;
		}
	}
	//修改按钮
	function doUpdate(){
		if(!tt.validate()){
			return false;
		}
		//校验图片类型
		if(!checkPicture()){
			return false;
		}
		if(!checkActivityPatternsRepeat()){
			return false;
		}
		if(isAddShoppingNull()){//判断是否加购产品
			return false;
		}
		if(!isProductAddForUpdate()){
			return false;
		}
		if(isPremiumRangeError()){//如果保费的值的范围错误，则提示
			return false;
		}
		if(!isDiscountIDNull()){
			return false;
		}
		if(!isProductAddServiceExistForUpdate()){//看看其它产品是否存在该活动类型  新的产品  原：isProductAddServiceExistForUpdate()
			setNames();
			document.getElementById("frmInput").submit();
		}else{
			setNames();
			document.getElementById("frmInput").submit();
		}
	}
	function isPremiumRangeError(){
		var isFlag = false;
		var premumOperators = document.getElementsByName("premumOperator");//保费类型
		for(var i=1;i<premumOperators.length;i++){
			if(premumOperators[i].value=="6"){//6两者之前
				var tbodyObj = $(premumOperators[i]).parent().parent().parent();
				var range1  ="";//保费的值 小于
				var range2 = "";//保费的值 大于
				tbodyObj.find("input").each(function(index,value){
					if(index==0){//保费的值小于
						range1 = $(value).attr("value");
					}
					if(index==1){//保费的值 大于
						range2 = $(value).attr("value");
					}
				});
				if(parseInt(range1)>parseInt(range2)){
					alert("保费1的值大于保费2的值");
					isFlag=true;
				}
			}//end
		}
		return isFlag;

	}
	function isProductAddServiceExistForUpdate(){
		var returnFlag =false;
		var newProduct = "";
		var actStatus="";
		var oldProduct = document.getElementsByName("oldProduct")[0].value;
		var coreProductCodes =$(".coreProductCode");/// document.getElementsByName("coreProductCode");
		for(var i =0;i<coreProductCodes.length;i++){
			newProduct = newProduct+coreProductCodes[i].value+",";
		}
		if(newProduct!=""){
			newProduct = newProduct.substring(0,newProduct.length-1);
			var startDate =  document.getElementsByName("geAddServiceActivity.activityStartDate")[0].value;
			var endDate =    document.getElementsByName("geAddServiceActivity.activityEndDate")[0].value;
			var activityId = $("input[name='geAddServiceActivity.activityId']").val();
			var updatePage = "update";
			var activityPatterns = document.getElementsByName("activityPattern");
			var activityPatternsValue = "";
			for(var i=1;i<activityPatterns.length;i++){
				activityPatternsValue=activityPatternsValue+ activityPatterns[i].value+",";
			}
			var deptID = $("#authorityid").val();
			activityPatternsValue = activityPatternsValue.substring(0,activityPatternsValue.length-1);
			//调用ajax
			$.ajax({
		  		  type: "post",
		  		  async:false,
		  		  url: contextRootPath+'/marketing/isProductAddServiceExist.do',
		  		  data:"json",
		  		  data: {'startDate':startDate,'endDate':endDate,'coreProductNames':newProduct,'activityPatterns':activityPatternsValue,'deptID':deptID,'activityId':activityId,'updatePage':updatePage},
		  		  success: function(data){
		  			  data  = data.geCustomAddServiceActivityList;
		  			  if(data!=null){
		  				  var error = "";
		  				  for(var i=0;i<data.length;i++){
		  					  if(data[i].status=='3'){
			  						actStatus="状      态：【已发布】\r";
			  					 }else{
			  						actStatus="";
			  					 }
		  					error = error+" \r"+"活动名称："+data[i].activityName+"\r"+"产品类型："+data[i].productName+"\r"+"活动日期：("+data[i].startDate+"-"+data[i].endDate+")\r"+actStatus;
		  				  }
		  				  if(data.length==0){
		  					returnFlag= true;
		  				  }else{
		  					error = error+" \r"+"以上已经存在 请不要选择   ";
			  				alert(error);
			  				
			  			//	$("#BgDiv").css({ display:"block",height:$(document).height()});
			  				/*window.showModalDialog(contextRootPath+"/business/marketingManage/winOpen.jsp?startDate="+startDate+"&endDate="+endDate+"&coreProductNames="+newProduct+
			  						"&activityPatterns="+activityPatternsValue+"&deptID="+deptID+"&activityId="+activityId+"&updatePage="+updatePage,
			  						"查询商品" ,"dialogWidth=780px;dialogheight=600px");*/
			  				
		  				  }
		  			  }else{
		  				returnFlag= true;
		  			  }
		  		  }
			});//end $.ajax
		}else{
			returnFlag = true;
		}
		return returnFlag;
	}
	//判断图片的类型
	function checkPicture(){
		var uploadPictures=	$("input[name='uploadPicture']");
		for(var i=0;i<uploadPictures.length;i++){
			var filepath = uploadPictures[i].value;
			if(filepath!=""){//如果文件名存在
				var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
				extname = extname.toLowerCase();//处理了大小写
			    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
			    	alert("上传图片"+(i+1)+" 只能上传bmp,jpg,gif格式的图片！");
			     	return false;
			    }
			}
		}
		return true;
	}
	//设置对象名字
	function setNameUpdate(){
		var premumOperators = document.getElementsByName("premumOperator");//保费
		var activityPatterns = document.getElementsByName("activityPattern");//活动方式
		for(var i=1;i<premumOperators.length;i++){
			premumOperators[i].name="geAddServiceActivity.geActivitiesRules["+i+"].premumOperator";//保费的值
			activityPatterns[i].name="geAddServiceActivity.geActivitiesRules["+i+"].activityPattern";//活动方式
			
		}
		//根据保费设置 保费的值
		var itemIDs = document.getElementsByName("itemID");
		
		var nvalues = document.getElementsByName("nvalue");
		
		
		for(var i=0;i<itemIDs.length;i++){
			if((i+1)!=itemIDs.length){
				itemIDs[i+1].name="geAddServiceActivity.geActivitiesRules["+i+"].itemID";
				activityPatterns[i+1].name = "geAddServiceActivity.geActivitiesRules["+i+"].activityPattern";
				nvalues[i+1].name = "geAddServiceActivity.geActivitiesRules["+i+"].nvalue";
			}
		}
		//设置上传图片的名字
		var uploadPictures = document.getElementsByName("uploadPicture");
		var inputHmtlStart = "<input type='hidden' name='updateSerial' value='";
		var inputHmtlEnd ="'/>";
		var htmlString   ="";
		for(var i=0;i<uploadPictures.length;i++){
			htmlString = htmlString+inputHmtlStart+uploadPictures[i].value+inputHmtlEnd;
		}
		$("#productType").append(htmlString);
	}
	
	//双击域获取供应商信息
	function getGeThirdParterInfoForUpdate(field,eid){
		deptId = $("#authorityid").val();	
		var nameCount = $(field).attr("nameCount");
		 window.open(contextRootPath+"/business/marketingManage/create/selectProduct/selectProductMore/importMoreProduct.jsp?deptId="+deptId+"&nameCount="+nameCount+'&itemId='+eid,"查询商品");
		//window.open(contextRootPath+'/party/prepareAddGeThirdParterService.do?deptId='+deptId+'&opertion=updateUserMarketing&nameCount='+nameCount+'&itemId='+eid,"查询商品");
	}

//business\marketingManage\update\index.jsp end***************************************************************************************

	
//common sart*************************************************************************************************************************
/**
	 * 删除一个表格
	 * @returns
	 */
	function deleteTable(feld){
		var tableObj = $(feld).parent().parent().parent().parent().parent();
		var id;
		tableObj.find("select[name='activityPattern']").each(function(){
			id = $(this).attr("id");
			return;
		});
		for(var i=array.length-1;i >-1;i--){
		    if(array[i].id ==id){
		    	var checkIds = array[i].checkIdsarray;
		    	for(var j=0;j<checkIds.length;j++){
		    		if(checkIds[j] != undefined&&checkIds[j]!=''){
			    		ttvf.rmId(checkIds[j]);
			    		intRange.rmId(checkIds[j]);
		    		}
		    	}
		    	array.splice(i,1);
		    }
		}
		var itemName = $("input[name='itemName']");
		if(itemName.length!=2){//非最后一个
			(tableObj).find("input").each(function(index,value){//删除input域的样式
				if(($(this).attr("type")!="button")
						&&($(this).attr("name")=="nvalue"||$(this).attr("name")=="discountID")){//nValue 折扣ID不用考虑
					$(this).removeAttr("ttTalentReqStarId");
					$(this).removeAttr("ttTalentMsgId");
					$(this).parent().find("span").remove();
					$(this).removeClass("talentErrInput");
					ttvf.rmId($(this).attr("id"));
				}
			});
			//删除textArea输入域
			$(tableObj).find("textArea").each(function(index,value){//删除input域的样式
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.rmId($(this).attr("id"));
			});
			//删除select域的样式
			$(tableObj).find("select").each(function(index,value){//删除input域的样式
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.rmId($(this).attr("id"));
			});
			//删除表格
			var obj = $(feld).parent().parent().parent().parent().parent().parent();
			//在删除之前如果该表格里有input域将所有删除
			$(tableObj).find("table").each(function(addShoppingIndex,addShoppingTable){
				if(addShoppingIndex==1){
					$(addShoppingTable).find("input").each(function(inputIndex,domInput){
						if($(domInput).attr("typeProduct")=="addShopping"){
							ttvf.rmId(domInput.id);
						}
					});
				}
			});
			$(obj).remove();
			//设置序号
			var spans = $("span");
			var j=0;
			for(var i=0;i<spans.length;i++){
				if(spans[i].id=="count"){
//					spans[i].innerText = "规则"+(j);
					$(spans[i]).text("规则"+j);
					//alert($(spans[i]).text());
					j++;
				}
			}
			//设置nameCount的序号nameCount
			var thirdParterIDs =$("select[name='activityPattern']");
			for(var i=0;i<thirdParterIDs.length;i++){
				$(thirdParterIDs[i]).attr("nameCount",i);
			}
			//清空临时变量的值
			document.getElementById("dateTempId").value="";
		}else{//最后一个    //if(itemName.length!=2){
			alert("至少一个规则");
		}
		remDiscountId();
	}
//common end*************************************************************************************************************************
	//得到eids以","间隔
	function getEids1(){
		var results = "";
		var eids = document.getElementsByName("shoppingCoreProductCode");
		for(var i=0;i<eids.length;i++){
			results = results+eids[i].value+",";
		}
		if(results==""){
			return "";
		}else{
			return results.substring(0,results.length-1);
		}
		
	}
	//加购产品 
	function upaddShoppingProduct(field){
		var deptId = document.getElementById("authorityid").value;
		if(deptId==""){
			alert("请先选择活动区");
		}else{
			var xRule = "";//第几条归则
			var yAddShopping=0 ;//第几条加购产品
			var yReal = 0;
			//先求纵坐标
			var obj = $(field).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
			xRule = obj.attr("id");
			//求横坐标
			var objAddShopping = $(field).parent().parent().parent().parent();
			objAddShopping.find("table").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){
					yAddShopping++;
					if(field.id==$(value).attr("id")){
						yReal = yAddShopping;
					}
				}
			});
			//选择要弹出的产品
			 var eids = getEids1();//","间隔 "G300024";//"G300024,G200058,G300008";//从页面上获取的
			window.open(contextRootPath+"/marketing/prepareSelectAddShopping1.do?operation=0&deptId="+deptId+"&eids="+eids+"&xRule="+(parseInt(xRule)-1)+"&yAddShopping=0","加够产品");
		}
		
	}
	
	//增加加购产品
	var upaddShoppingCount = 0; 
	function upaddShopping(field){
		var xRule = "";//第几条归则
		var obj = $(field).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
		xRule = obj.attr("id");
		var trObj  = $(field).parent().parent().parent();
		var obj = $("#tableAddShoppingHidden");
		trObj.html("");
		trObj.append(obj.html());
		trObj.find("table").each(function(index,value){
			if($(this).attr("name")=="addshoppingProductCallBack"){////加购产品名称
				//设置input的ID
				$(this).attr("id",$(this).attr("name")+(parseInt(xRule)-1)+"0");
				upaddShoppingProduct(this);
				addShoppingCount++;
			}
		});
	}
	//字符个数计算
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
	//活动内容长度校验
	function count() {
	    var value = document.getElementById("geAddServiceActivity.activityContent").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=2000) {
	        document.getElementById("geAddServiceActivity.activityContent").value = leftUTFString(document.getElementById("geAddServiceActivity.activityContent").value,2000);
	    }
	}
	//活动名称长度校验
	function countActivityName() {
	    var value = document.getElementById("activityName").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=55) {
	        document.getElementById("activityName").value = leftUTFString(document.getElementById("activityName").value,60);
	    }
	}
	//---------end------
	

	function a(){
		var sel=document.getElementById("authorityid");
		sel.multiple="multiple";

		}
