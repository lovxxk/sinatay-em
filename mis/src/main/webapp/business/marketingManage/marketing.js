//marketingManage\create\frmCreate.jsp start********************************************************************************************
	var ttRange =  new tt.NRV().set(1, 99);//���ַ�Χֻ����1��99
//	var intRange = new tt.RV().set(new RegExp("^[0-9]+(\.[0-9]+)?$"), "������Ϸ�������");
	var intRange = new tt.RV().set(new RegExp("^[+-]?(([1-9]\\d*([.]\\d{1,2})?|0([.][1-9]\\d{0,1})?)|0([.]0[1-9])?)$"), "������Ϸ����֣�С�������λ");
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
	var ttvf = tt.vf.req;//�ǿ�
	  //����ҵ������ѡ��ʡ
	function selectProvince(){
		var actId=$.getUrlParam('activityId');
		document.getElementById("cityId").value="";//������ѡ��
		document.getElementById("cityId").style.display="none";//������ѡ��
		var resultBusinessArea = "";//ҵ������
		var BusinessAreas = document.getElementsByName("BusinessArea");
		for(var i=0;i<BusinessAreas.length;i++){
			if(BusinessAreas[i].checked){//ѡ�е��Ǹ�
				resultBusinessArea =BusinessAreas[i].value; 
			}
		}
		var infoOne;
		if(resultBusinessArea=="1"){//����
			$("#authorityid").html("<option value='1'>ȫ��</option>");
			$("#cityId").hide();
			$("#cityId").html("<option value='1'>ȫ��</option>");
		}else{//�ٲ�����
			//����ajax
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
		  				  var info = "<option value=''>--��ѡ��--</option>";
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
	 * ͨ��ʡѡ����
	 */
	function selectCity(){
		var actId=$.getUrlParam('activityId');
		$("#cityId").val("");//������ѡ��
		var province = $("#authorityid").val();
		if(province==""||province=="1"||province=="2"||province=="3"||province=="4"){//��ѡ�� �� ����ȫ��
			$("#suv").hide();//������
			$("#cityId").hide();//������
			$("#cityId").html("<option value='"+province+"'>ȫ113��</option>");
		}else{
			$("#suv").show();//������
			$("#cityId").show();//��ʾ ��
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
		  				  var info = municipalityFlag ? "" : "<option value=''>--��ѡ��--</option>";
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
	//ѡ����۹�����ʾ�ۿ�id�����
	function showDiscountID(){
		//ȡ���ۻ��ʽ,���Ƿ��д��ۻ
		var resetNode;
		var activityPatterns=$("select[name='activityPattern']");
		var ap;
		if($(".resetNode").length>0&&$(".clazz").length=='0'){
			for ( var k = 0; k < $("tr[class='resetNode']").length; k++) {
				if(k<=9){
				ap="<td align='right' class='clazz'><span style=\"font-weight: bold;padding-left: 200px\"> �ۿ�ID(ѡ��)��</span><input  class=\"activityPatternDiscountID\" id=\"activityDiscountID"+k+"\" name=\"geAddServiceActivity.geActivitiesProducts["+k+"].discountID\" style=\"width: 170px;\" onkeyup=\"value=value.replace(/[\\\W]/g,'')\" type=\"text\" maxLength=\"100\"/></td>";
				if($("#resetNode"+k).val()==undefined){
					resetNode=$("#productType"+k);
				}else{
					resetNode=$("#resetNode"+k);//.find("td:last");
				}
				
				resetNode.append(ap);
				}else{
					ap="<td align='right' class='clazz'><span style=\"font-weight: bold;padding-left: 190px\"> �ۿ�ID(ѡ��)��</span><input  class=\"activityPatternDiscountID\" id=\"activityDiscountID"+k+"\" name=\"geAddServiceActivity.geActivitiesProducts["+k+"].discountID\" style=\"width: 170px;\" onkeyup=\"value=value.replace(/[\\\W]/g,'')\" type=\"text\" maxLength=\"100\"/></td>";
					if($("#resetNode"+k).val()==undefined){
						resetNode=$("#productType"+k);
					}else{
						resetNode=$("#resetNode"+k);//.find("td:last");
					}
					
					resetNode.append(ap);
				}
			}
		}
		//����Ʒ���Ƿ��в�Ʒ���в�Ʒ����ÿ����Ʒ����ۿ�ID
		//û�в�Ʒ�������κβ���,�ȴ���Ӳ�Ʒʱ�����ۿ� id
		//���ʽû�д��ۻ��ʽ��ȡ���ۿ�id��������֤
	}
	//ȡ����Ʒ����id����
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
	 * ����һ�����
	 */	
	var num = 1;
	function insertTable(){
		var ab = $("#tableInfoHidden").find("tr:first>td");
		var a = "<tr id='" + num + "'  style=\"height:30px;\"><td>" + $(ab).html()+ "</td></tr>";//Ϊ���ð�ť�ṩname='classNonoNum"+num+"' class='classNonoNum1'
		$("#tableInfo").append(a);	
		//input
		$("#" + num).find("input").each(function(index, value){//input��
			$(this).attr("id",$(this).attr("name") + num);
			if($(value).attr("type")!="button"&&$(value).attr("type")!="hidden"){//�ų�button ,������
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				
				if($(value).attr("name")=="itemName"){//������Ʒ��ʱ����Ҫ������Ʒ�����ֵ����,��ɾ����ʱ��Ҳ��Ҫ��������
					var itemNames = document.getElementsByName("itemName");
					$(value).attr("nameCount",(itemNames.length-1));
				}
				$(this).attr("value","");//�������ֵ
			}
		});
		
		//textArea ���������
		$("#" + num).find("textArea").each(function(index, value){
			$(this).attr("id",$(this).attr("name") + num);
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			$(this).attr("id",$(this).attr("name") + num);
		});
		
		//��selectֵ��ԭ
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
	
	//��������,�������
	function setCountNum(){//��д���
		var spans = $("span");
		var j = 0;
		for(var i = 0;i < spans.length; i++){
			if(spans[i].id == "count"){
				$(spans[i]).text("����"+j);
				j ++;
			}
		}
	}
	//*********************************�����е�ÿһ�е���ʾ������start*******************
	function cancelItemName(field){//��Ʒ���� 
		field.style.display="none";//��ʾ
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id);
			}
		});
		       
	}
	
	function cancelDiscountType(field){//��������
		field.style.display="none";//��ʾ
		$(field).find("select").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //ȥ����֤
			}
		});
	}
	
	function cancelNvalueInit(field){
		field.style.display="none";//��ʾ
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				intRange.rmId(valueObj.id); //ȥ����֤
			}
		});
	}
	
	function cancelNvalue(field){//n��ֵ
		field.style.display="none";//��ʾ
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //ȥ����֤
			}
		});
	}
	
	function cancelDiscountID(field){//�ۿ�ID
		field.style.display="none";//��ʾ
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //ȥ����֤
			}
		});
	}
	
	function cancelDiscountRemarkText(field){//������������
		field.style.display="none";//��ʾ
		$(field).find("textArea").each(function(index,valueObj){
			if(index==0){
				//ttvf.rmId(valueObj.id); //ȥ����֤
			}
		});
	}

	//���ر��������мӹ���Ʒ 
	function displayShoppingProductFlag(displayFlag,innerTable){
		if(displayFlag==""||displayFlag=="1"||displayFlag=="2"||displayFlag=="3"||displayFlag=="4"){
			innerTable.find("input").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){//���ӹ���Ʒ�����Ǹ�������
					//��IDȥ����
					//ttvf.rmId($(value).attr("id"));//ȥ�� 
					$(value).parent().parent().remove();//����
				}
				if($(value).attr("typeProduct")=="addShoppingProductCode"){//���ӹ���Ʒ�����Ǹ�������
					//��IDȥ����
					//ttvf.rmId($(value).attr("id"));
					$(value).parent().parent().remove();//����
				}
			});
		}
	}
	//*********************************�����е�ÿһ�е���ʾ������end*******************
	//*********************************�����е�ÿһ�е���ʾ����ʾstart*******************
	function addValidateItemName(field){//��Ʒ����
		field.style.display ="";
		var id;
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);//������֤
				id = valueObj.id;
				return;
			}
		});
		return id;
	}
	function addValidateDiscountType(field){//��������
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

	//intRange ֻ�������������Ĵ���
	function addValidateNvalueInit(field){
		field.style.display ="";
		var id;
		var type=$(field).attr('at');
		//alert($(field).html());
		if(type=='2'){
			$(field).find('.dazhe').text('ǰN��ֵ��');
		}else if(type=='3'){
			$(field).find('.dazhe').text('����N%��ֵ��');
		}
		else if(type=='4'){
			$(field).find('.dazhe').text('������(%)/���ۼ�(Ԫ)��');
		}
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				intRange.addId(valueObj.id);//������֤  �������Ĵ���
				id = valueObj.id;
				return;
			}
		});
		return id;
	}

	function addValidateNvalue(field){//n��ֵ
		field.style.display ="";
		var id;
		var type=$(field).attr('at');
		if(type=='2'){
			$(field).find('.dazhe').text('ǰN��ֵ��');
		}else if(type=='3'){
			$(field).find('.dazhe').text('����N%��ֵ��');
		}
		else if(type=='4'){
			$(field).find('.dazhe').text('������(%)/���ۼ�(Ԫ)��');
		}
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);//������֤
				id = valueObj.id;
				return;
			}
		});
		return id;
	}
	function addValidateDiscountID(field){//�ۿ�ID
		field.style.display ="";
		$(field).find("input").each(function(index,valueObj){
			if(index==0){
				//�ۿ�ID��У��
				//ttvf.addId(valueObj.id);//������֤
			}
		});
	}
	function addDiscountRemarkText(field){//������������
		field.style.display ="";
		var id;
		$(field).find("textArea").each(function(index,valueObj){
			if(index==0){
				ttvf.addId(valueObj.id);//������֤
				id =  valueObj.id;
				return;
			}
		});
		return id;
	}
	function addShoppingProductShow(field){//�ӹ���Ʒ 
		field.style.display ="";
	}
	//*********************************�����е�ÿһ�е���ʾ����ʾstart*******************

	//����n��ֵ�Ƿ���ʾ
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
			if(displayFlag==""){//��ѡ��
				if(index==4){//��Ʒ���� ���� ȡ����֤����
					var itemID = cancelItemName(value);
				}
				if(index==5){//�������� ���� ȡ����֤����
					cancelDiscountType(value);
				}
				
				if(index==6){//n��ֵ  ���� ȡ����֤����
					remDiscountId();
					$(value).attr('at',displayFlag);
					cancelNvalue(value);//ȡ���ǿյ���֤
					cancelNvalueInit(value)//ȡ������������֤
					
				}
				
				if(index==7){//�ۿ�ID  ���� ȡ����֤����  ��Ҫ����֤
					cancelDiscountID(value);
				}
				
				if(index==8){//������������  ���� ȡ����֤����
					cancelDiscountRemarkText(value);
				}
				
				if(index==9){//�ӹ���Ʒ  ���� ȡ����֤����
					value.style.display="none";//����ʾ
					displayShoppingProductFlag(displayFlag,innerTable);//ȥ����֤
				}
			}
			
			if(displayFlag=="1"){//������пͻ�
				if(index==4){//��Ʒ���� ��ʾ ������֤����
					var itemID = addValidateItemName(value);
					currentArray.push(itemID);
				}
				if(index==5){//�������� ���� ȡ����֤����
					cancelDiscountType(value);
				}
				
				if(index==6){//n��ֵ  ���� ȡ����֤����
					remDiscountId();
					$(value).attr('at',displayFlag);
					cancelNvalue(value);//ȡ���ǿյ���֤
					cancelNvalueInit(value)//ȡ������������֤
				}
				
				if(index==7){//�ۿ�ID  ���� ȡ����֤����  ȡ����  
					cancelDiscountID(value);
				}
				
				if(index==8){//������������  ���� ȡ����֤����
					cancelDiscountRemarkText(value);
				}
				
				if(index==9){//�ӹ���Ʒ  ���� ȡ����֤����
					value.style.display="none";//����ʾ
					displayShoppingProductFlag(displayFlag,innerTable);//ȥ����֤
				}
			}// end if(displayFlag=="1"){
			
			if(displayFlag=="2"){//����ǰN���ͻ�  ���NΪ������
				if(index==4){//��ʾ ��Ʒ����
					var itemID = addValidateItemName(value);
					currentArray.push(itemID);
				}
				if(index==5){//���� ��������
					cancelDiscountType(value);
				}
				if(index==6){//��ʾ n��ֵ
					remDiscountId();
					$(value).attr('at',displayFlag);
					currentArray.push(addValidateNvalue(value));
					currentArray.push(addValidateNvalueInit(value));
				}
				if(index==7){//���� �ۿ�ID  
					cancelDiscountID(value);
				}
				if(index==8){//���� ������������

					cancelDiscountRemarkText(value);
				}
				if(index==9){//���� �ӹ���Ʒ 
					value.style.display="none";//����ʾ
					displayShoppingProductFlag(displayFlag,innerTable);//ȥ����֤
				}
			}
			
			if(displayFlag=="3"){//����%N���ͻ�
				if(index==4){//��ʾ ��Ʒ����
					currentArray.push(addValidateItemName(value));
				}
				if(index==5){//���� ��������
					cancelDiscountType(value);
				}
				if(index==6){//��ʾ n��ֵ
					remDiscountId();
					//td��ʾN��ֵ	
					$(value).attr('at',displayFlag);
					currentArray.push(addValidateNvalue(value));
					currentArray.push(addValidateNvalueInit(value));
				}
				if(index==7){//���� �ۿ�ID
					cancelDiscountID(value);
				}
				if(index==8){//���� ������������
					cancelDiscountRemarkText(value);
				}
				if(index==9){//���� �ӹ���Ʒ 
					value.style.display="none";//����ʾ
					displayShoppingProductFlag(displayFlag,innerTable);//ȥ����֤
				}
			}
			
			if(displayFlag=="4"){//���۵Ĵ���
				if(index==4){//������Ʒ����
					cancelItemName(value);
				}
				if(index==5){//��ʾ ��������
					currentArray.push(addValidateDiscountType(value));
				}
				if(index==6){//��ʾ n��ֵ
					showDiscountID();
					//td��ʾ���ݴ������� ��ȷ����Ӧ��ֵ
					$(value).attr('at',displayFlag);
					cancelNvalue(value);
					currentArray.push(addValidateNvalueInit(value));
				}
				if(index==7){//��ʾ �ۿ�ID
					currentArray.push(addValidateDiscountID(value));
				}
				if(index==8){//��ʾ ������������
					currentArray.push(addDiscountRemarkText(value));//������ 
				}
				if(index==9){//���� �ӹ���Ʒ
					value.style.display="none";//����ʾ
					displayShoppingProductFlag(displayFlag,innerTable);//ȥ����֤
				}
			}
			
			if(displayFlag=="5"){//�ӹ���Ʒ
				if(index==4){//������Ʒ����
					cancelItemName(value);
				}
				if(index==5){//���� ��������
					cancelDiscountType(value);
				}
				if(index==6){//���� n��ֵ
					remDiscountId();
					//td��ʾ���ݴ������� ��ȷ����Ӧ��ֵ
					$(value).attr('at',displayFlag);
					cancelNvalue(value);
					cancelNvalueInit(value);
				}
				if(index==7){//���� �ۿ�ID
					cancelDiscountID(value);
				}
				if(index==8){//���� ������������
					cancelDiscountRemarkText(value);
				}
				if(index==9){//��ʾ �ӹ���Ʒ 
					value.style.display="";//��ʾ
					//displayShoppingProductFlag(displayFlag,innerTable);//ȥ����֤
				}
			}
			
		});
	}
	
	function cancelProductNameValidate(value){
		$(value).find("input").each(function(indexInput,valueInput){
			if(indexInput==1){//��Ʒ���� 
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
	//����Ƕ�Ӧ����    ����Ʒ���������֤
	function addProductNameValidate(value){
		$(value).find("input").each(function(indexInput,valueInput){
			if(indexInput==1){//��Ʒ���� 
				ttvf.addId($(valueInput).attr("id"));
			}
		});
	}
	
	//����Ƕ�Ӧ���� ��N��ֵ�����֤
	function addNValueValidate(value){
		$(value).find("input").each(function(indexInput,valueInput){
			if(indexInput==0){//N��ֵ 
				ttvf.addId($(valueInput).attr("id"));
			}
		});
	} 
	
	//�ṩ�� ���ʽ  :1--��ѡ��-- 2.�ӹ�    ����Ӧ����֤ȡ����
	function cancelValidateForSelect(index,value){
		if(index==4||index==5){//��Ʒ����   N��ֵ  
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
	//ȡ��  innerTable ����е�N��ֵ��֤  
	function cancelValidateForSelect1(innerTable){
		$(innerTable).find("tr").each(function(index,value){
			if(index==5){//N��ֵ ������
				value.style.display="none";
				$(value).find("input").each(function(indexInput,valueInput){
					ttvf.rmId($(valueInput).attr("id"));  //��N��ֵ����֤��ȡ����
				});
			}
			if(index==6){//�������� ������
				value.style.display="none";
				$(value).find("textArea").each(function(indexInput,valueInput){
					ttvf.rmId($(valueInput).attr("id"));  //���������ӵ�ֵ����֤��ȡ����
				});
			}
		});
	}
	
	//��������ȥ����֤
	function cancelDiscountValidate(innerTable){
		
	}
	//�ӹ���Ʒ 
	function addShoppingProduct(field){
		var deptId = document.getElementById("authorityid").value;
		if(deptId==""){
			alert("����ѡ����");
		}else{
			var xRule = "";//�ڼ�������
			var yAddShopping=0 ;//�ڼ����ӹ���Ʒ
			var yReal = 0;
			//����������
			var obj = $(field).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
			xRule = obj.attr("id");
			//alert(xRule);
			//�������
			var objAddShopping = $(field).parent().parent().parent().parent();
			objAddShopping.find("table").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){
					yAddShopping++;
					if(field.id==$(value).attr("id")){
						yReal = yAddShopping;
					}
				}
			});
			//ѡ��Ҫ�����Ĳ�Ʒ
			 var eids = getEids();//","��� "G300024";//"G300024,G200058,G300008";//��ҳ���ϻ�ȡ��
			window.open(contextRootPath+"/marketing/prepareSelectAddShopping.do?operation=1&deptId="+deptId+"&eids="+eids+"&xRule="+(parseInt(num))+"&yAddShopping=0","�ӹ���Ʒ");
		}
		
	}
	
	//���Ӽӹ���Ʒ
	var addShoppingCount = 0; 
	function addShopping(field){
		var trObj  = $(field).parent().parent().parent();
		var obj = $("#tableAddShoppingHidden");
		trObj.html("");
		trObj.append(obj.html());
		trObj.find("table").each(function(index,value){
			if($(this).attr("name")=="addshoppingProductCallBack"){////�ӹ���Ʒ����
				//����input��ID
				$(this).attr("id",$(this).attr("name")+num+"0");
				addShoppingProduct(this);
				addShoppingCount++;
			}
		});
	}
	//insertTr  ����һ��TR�ϴ�һ��ͼƬ
	function insertTr(field){
		var trObj = $(field).parent().parent();
		$("#displayText").html(trObj.html());
		//var tr.append
		
		trObj.append($("#tableDelHidden").html());
	}
	
	//˫�����ȡ��Ӧ����Ϣ
	function getGeThirdParterInfo(field){
		var  deptId = document.getElementById("authorityid").value;
		if(deptId==""){
			alert("��ѡ������");
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
			//window.open(contextRootPath+"/business/marketingManage/create/selectGeThirdParterInfo/index.jsp?nameCount="+nameCount,"��ѯ��Ӧ��" ,"top=100, left=100, width=900,height=600,toolbar=no");
//			window.open(contextRootPath+"/business/marketingManage/create/selectGeThirdParterService/index.jsp?deptId="+deptId+"&nameCount="+nameCount,"��ѯ��Ʒ" ,"top=100, left=100, width=900,height=600,toolbar=no");
		window.open(contextRootPath+'/business/marketingManage/create/selectProduct/selectProductMore/importMoreProduct.jsp?deptId='+deptId+'&opertion=userMarketing&nameCount='+nameCount+'',"��ѯ��Ʒ");
		}
	}
	//����
	var productCount=1;
	var countTemp =0;
	function addProduct(type){
		var obj = $("#tableProductHidden");
		$("#productModel").append(obj.html());
		//��ʼ����֤
		var coreProductNames = document.getElementsByName("coreProductName");
		var lastProductName = coreProductNames[coreProductNames.length-1];
		//��lastProductName��ֵ
		var addTemp = coreProductNames.length-1;
		$(lastProductName).bind("dblclick",function(){
			eval("selectProduct('"+addTemp+"')");
		});//����ondblclick
		//����ID
		$(lastProductName).removeAttr("ttTalentReqStarId");
		$(lastProductName).removeAttr("ttTalentMsgId");
		$(lastProductName).parent().find("span").remove();
		$(lastProductName).removeClass("talentErrInput");
		if(type=="update"){//����
			$("#productModel").find("input").each(function(index,domObj){
				if($(domObj).attr("typeProduct")=="productName"){
					countTemp++;
				}
			});
			$(lastProductName).attr("id",$(lastProductName).attr("name")+countTemp);
			ttvf.addId($(lastProductName).attr("id"));
		}else{//����
			$(lastProductName).attr("id",$(lastProductName).attr("name")+productCount);
			ttvf.addId($(lastProductName).attr("id"));
			productCount++;
		}
	}
	
	//ɾ������
	function deleteOnself(field){
		//ɾ��ID,ȡ����֤
		var tdObj = $(field).parent();
		tdObj.find("input").each(function(index,value){
			if(index==0){
				ttvf.rmId($(this).attr("id"));
			}
		});
		$(field).parent().parent().remove();
		
	}
	function doCreate(){//�ύ��
	//	showDiscountID();
		if(!tt.validate()){
			return false;
		}
//		if(!isPeremiu()){
//			return false;
//		}
		//�����ͼƬ,�ж�ͼƬ������
		if(!judgePicture()){//�ж�ͼƬ
			return false;
		}
		if(!checkActivityPatternsRepeat()){
			return false;
		}
		if(!isProductAdd()){
			return false;
		}
		if(isAddShoppingNull()){//�ж��Ƿ�ӹ���Ʒ
			return false;
		}
		if(isPremiumRangeError()){//������ѵ�ֵ�ķ�Χ��������ʾ
			return false;
		}
		if(!isDiscountIDNull()){
			return false;
		}
		if(!isProductAddServiceExist()){//����������Ʒ�Ƿ���ڸû����
			setNames();//���ñ���Ԫ�ص�name���ԣ�����struts2���Զ�װ��
			//$("form:first").submit();
			document.getElementById("frmInput").submit();
		}else{
			setNames();//���ñ���Ԫ�ص�name���ԣ�����struts2���Զ�װ��
			//$("form:first").submit();
			document.getElementById("frmInput").submit();
		}
	}//end doCreate();
	
	function   isPeremiu(){
		var activityPatterns=$("input[name='premiumRange2']");
		for ( var i = 1; i <= activityPatterns.length; i++) {
			//alert($("#premiumRange2a"+i+"").val());
			if($("#premiumRange2a"+i+"").val()==""){
			alert("����򣬱��ѵڶ���ֵΪ�գ������뱣��ֵ��");	
			return false;
			}
		}
		return true;
	}
	
	//�ж��Ƿ���ڴ��ۻ
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
					alert("��ǰ��д��ڴ��۹������������һ���ۿ�ID��");
					return false;
				}
			}
			for ( var j = 0; j < $(".activityPatternDiscountID").length; j++) {
				if($("#activityDiscountID"+j+"").val()!=""){
					alert("��ǰ������ڴ��۹�����ɾ����Ʒ�ۿ�ID��");
					return false;
				}
			}
			return true;
		}
	}
	//�ӹ�
	function isAddShoppingNull(){
		var activityPatterns = document.getElementsByName("activityPattern");
		var haveAdd = false;
		var isAddShoppingExistFlag = false;
		for(var i=1;i<activityPatterns.length;i++){
			if(activityPatterns[i].value=="5"){//5Ϊ�ӹ�
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
							alert("��ѡ��ӹ���Ʒ");
							isAddShoppingExistFlag = true;
							return false;
						}
					}
				});
			}
		}//end for
		if(!haveAdd){//û����ֵ
			return false;
		}else{//�мӹ�
			return isAddShoppingExistFlag;
		}
	}
	//��ƷΪ����֤
	function isProductAdd(){
		if($("input[name='coreProductCode']").length==0){
			alert("��ѡ���Ʒ");
			return false;
		}
		return true;
	}
	//��޸Ĳ�ƷΪ����֤
	function isProductAddForUpdate(){
		if($("input[class='coreProductCode']").length==0){
			alert("��ѡ���Ʒ");
			return false;
		}
		return true;
	}
	//�жϸû�Ƿ���� 
	function isProductAddServiceExist(){
		var flag = false;
		
		var coreProductNamesValue = "";
		var activityPatternsValue = "";
		var coreProductNames = document.getElementsByName("coreProductCode");//ȡ��Ʒ
		var activityPatterns = document.getElementsByName("activityPattern");//���ʽ
		var deptID = $("#authorityid").val();//�������
		var actStatus="";
		
		var startDate = document.getElementsByName("geAddServiceActivity.activityStartDate")[0].value;;//ȡ��ʼ����
		var endDate = document.getElementsByName("geAddServiceActivity.activityEndDate")[0].value;//ȡ��������
		//�����鸳ֵ
		for(var i=0;i<coreProductNames.length;i++){
			coreProductNamesValue = coreProductNamesValue+coreProductNames[i].value +",";
		}
		for(var i=1;i<activityPatterns.length;i++){
			activityPatternsValue = activityPatternsValue+activityPatterns[i].value+",";
		}
		//ȥ��","
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
	  						actStatus="״      ̬�����ѷ�����\r";
	  					  }else{
	  						actStatus="";
	  					  }
	  					error = error+" \r"+"����ƣ�"+data[i].activityName+"\r"+"��Ʒ���ͣ�"+data[i].productName+"\r"+"����ڣ�("+data[i].startDate+"-"+data[i].endDate+")�ϴ��ڽ���\r"+actStatus;
	  				  }
	  				  if(data.length==0){
	  					flag= true;
	  				  }else{
	  					 alert("��ǰ��룺"+error);
	  				  }
	  			  }else{
	  				flag= true;
	  			  }
	  		  }
		});//end $.ajax
		
		return flag;
	}
	
	//���ø����������
	function setNames(){//���ñ���Ԫ�ص�name���ԣ�����struts2���Զ�װ��
		//����ͼƬ���ϴ�
		var uploadPictures = document.getElementsByName("uploadPicture");
		var uploadSerialNos= "";
		for(var i=0;i<uploadPictures.length;i++){
			if(uploadPictures[i].value!=""){
				uploadSerialNos = uploadSerialNos+(i+1)+",";
			}
		}
		uploadSerialNos = uploadSerialNos.substring(0,(uploadSerialNos.length-1));
		document.getElementsByName("uploadPictureSerialNos")[0].value=uploadSerialNos;
		//���ù���
		setPremiums();//���ñ�������
		setActivityPattern();//���û��ʽ  �����ʽ��Ӧ��ֵ
	}
	/**
	 * �޸Ĳ�Ʒ
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
			 alert("��ѡ������");
		 }else{
			 var eids = getEidsUpDate();//","��� "G300024";//"G300024,G200058,G300008";//��ҳ���ϻ�ȡ��
			 window.open(contextRootPath+"/marketing/prepareSelectAddShopping1.do?deptId="+deptId+"&eids="+eids+"&discount="+discount,"���Ӳ�Ʒ");
			 
		 }
	}
	//�õ�eids��","���
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
	 * ���Ӳ�Ʒ
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
					///alert("����"+discount);
				}
			}
		}
		
		productCallBack.html("");
		productCallBacktwo.html("");
		$("#showInfo").hide();
		$("#productInfo").hide();
		 var deptId = (   $("#cityId").val()==""||$("#cityId").val()==null  )  ?    (  $("#authorityid").val() )  : $("#cityId").val();
		 if(deptId==""){
			 alert("��ѡ������");
		 }else{
			 var eids = getEids();//","��� "G300024";//"G300024,G200058,G300008";//��ҳ���ϻ�ȡ��
			 window.open(contextRootPath+"/marketing/prepareSelectAddShopping.do?deptId="+deptId+"&eids="+eids+"&discount="+discount,"���Ӳ�Ʒ");
			 
		 }
		
	}
	//�õ�eids��","���
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
	//���ñ�������  ���ñ��ѵ�ֵ
	function setPremiums(){
		var _premumOperators=$("select[name='premumOperator']");//��������
		for(var i=1;i<_premumOperators.length;i++){
			var tbodyObj = $(_premumOperators[i]).parent().parent().parent();
			if(_premumOperators[i].value=="2"||_premumOperators[i].value=="3"||_premumOperators[i].value=="4"||_premumOperators[i].value=="5"){//premumOperators[i].value=="1"||
				//1����  2С�� 3���� 4����֮ǰ
				tbodyObj.find("input").each(function(index,value){
					if(index==0){//���ѵ�ֵ
						$(value).attr("name","geAddServiceActivity.geActivitiesRules["+(i-1)+"].peremiumValue");
					}
				});
			}
			
			if(_premumOperators[i].value=="6"){//6����֮ǰ
				var range1  ="";//���ѵ�ֵ С��
				var range2 = "";//���ѵ�ֵ ����
				tbodyObj.find("input").each(function(index,value){
					
					if(index==0){//���ѵ�ֵС��
						range1 = $(value).attr("value");
					}
					if(index==1){//���ѵ�ֵ ����
						range2 = $(value).attr("value");
					}
				});
				$("#frmInput").append("<input type=\"hidden\" name=\"geAddServiceActivity.geActivitiesRules["+(i-1)+"].peremiumValue\" value='"+range1+"@"+range2+"'/>");	
				
			}
			_premumOperators[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].premiumType"; 
		}
	}
	
	//���û��ʽ  �����ʽ��Ӧ��ֵ
	function setActivityPattern(){
		var activityPatterns=$("select[name='activityPattern']");
		var itemIDs = $("input[name='itemID']");//��ƷID
		var nvalues = $("input[name='nvalue']");//N��ֵ
		var discountTypes = document.getElementsByName("discountType");//��������
		var discountRemarkTexts = document.getElementsByName("discountRemarkText");//��������
		
		for(var i=1;i<activityPatterns.length;i++){
			if(activityPatterns[i].value=="1"){//�������пͻ� 
				itemIDs[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].itemID";//��Ʒ���� 
			}
			if(activityPatterns[i].value=="2"||activityPatterns[i].value=="3"){//����ǰN���ͻ�  �������ΪN�Ŀͻ� or ����
				itemIDs[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].itemID";//��Ʒ���� 
				nvalues[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].nvalue";//N��ֵ
			}
	
			if(activityPatterns[i].value=="4"){//��������
				nvalues[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].nvalue";//N��ֵ
				discountRemarkTexts[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].discountRemarkText";
				discountTypes[i].name = "geAddServiceActivity.geActivitiesRules["+(i-1)+"].discountType";
			}
			
			if(activityPatterns[i].value=="5"){//����ӹ���Ʒ 
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
	//�������Ƿ�����ͬ��
	function checkActivityPatternsRepeat(){
		var a = new Array();//�������пͻ�
		var b = new Array();//����ǰN���ͻ�
		var c = new Array();//����ǰ%n���ͻ�
		var d = new Array();//����
		var e = new Array();//�ӹ�
		
		var activityPatterns = document.getElementsByName("activityPattern");//���ʽ
		for(var i=1;i<activityPatterns.length;i++){//��1��ʼ�ų�������
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
				temp = temp+"����"+a[i]+"��";
			}
			var count = temp.lastIndexOf("��")
			temp = temp.substring(0,count)
			alert(temp+"�Ļ��ʽ������ͬ");
			return false;
		}
		if(b.length>1){
			var temp="";
			for(var i=0;i<b.length;i++){
				temp = temp+"����"+b[i]+"��";
			}
			var count = temp.lastIndexOf("��")
			temp = temp.substring(0,count)
			alert(temp+"�Ļ��ʽ������ͬ");
			return false;
		}
		if(c.length>1){
			var temp="";
			for(var i=0;i<c.length;i++){
				temp = temp+"����"+c[i]+"��";
			}
			var count = temp.lastIndexOf("��")
			temp = temp.substring(0,count)
			alert(temp+"�Ļ��ʽ������ͬ");
			return false;
		}
		if(d.length>1){
			var temp="";
			for(var i=0;i<d.length;i++){
				temp = temp+"����"+d[i]+"��";
			}
			var count = temp.lastIndexOf("��")
			temp = temp.substring(0,count)
			alert(temp+"�Ļ��ʽ������ͬ");
			return false;
		}
		if(e.length>1){
			var temp="";
			for(var i=0;i<e.length;i++){
				temp = temp+"����"+e[i]+"��";
			}
			var count = temp.lastIndexOf("��")
			temp = temp.substring(0,count)
			alert(temp+"�Ļ��ʽ������ͬ");
			return false;
		}
		return true;
	}
	//�õ���ʱֵ
	function setDateTempId(count){
		var tempDate = document.getElementsByName("activityStartDate")[count].value;
		document.getElementById("dateTempId").value=document.getElementsByName("activityStartDate")[count].value;
	}
	//�ж�ͼƬ������
	function judgePicture(){
	var uploadPictures=	$("input[name='uploadPicture']");
		if(uploadPictures.length==1){//ֻ��һ�������ֶε�,��û��Ҫ�ϴ���ͼƬ
			return true;
		}else{//��Ҫ�ϴ���ͼƬ
			
			for(var i=0;i<uploadPictures.length;i++){
				var filepath = uploadPictures[i].value;
				if(filepath!=""){
					var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
					extname = extname.toLowerCase();//�����˴�Сд
				    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
				    	alert("�ϴ�ͼƬ"+(i+1)+" ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��");
				     	return false;
				    }
				}// end if
			}
			return true;
		}
	}
	//����
	function showPremum(field){
		var premiumType = field.value;
		var obj = $(field).parent().parent().parent();
		obj.find("tr").each(function(index,value){
			if(index==2){
				if(premiumType==""||premiumType=="1"){//��ѡ�����
					value.style.display="none";//����
					cancelValidate(value);//ȡ����֤
				}
				if(premiumType=="2"||premiumType=="3"||premiumType=="4"||premiumType=="5"||premiumType=="6"){//С�� С�ڵ��� ���� ���ڵ���  ����֮��
					value.style.display="";//��ʾ 
					cancelValidate(value);
					setPremiumRange(premiumType,value);//���ѵ�ֵ ����
				}
			}
		});
	}
	//ȡ����֤
	function cancelValidate(field){
		$(field).find("input").each(function(index,value){
			ttvf.rmId($(this).attr("id"));
			intRange.rmId($(this).attr("id"));
		});
	}
	
	function setPremiumRange(premiumType,field){
		var htmlValue= "";
		
		if(premiumType=="2"){
			htmlValue = "���ѵ�ֵС�ڣ�";
		}
		if(premiumType=="3"){
			htmlValue = "���ѵ�ֵС�ڵ��ڣ�";
		}
		if(premiumType=="4"){
			htmlValue = "���ѵ�ֵ���ڣ�";
		}
		if(premiumType=="5"){
			htmlValue = "���ѵ�ֵ���ڵ��ڣ�";
		}
		if(premiumType=="6"){
			htmlValue = "���ѵ�ֵ����֮�䣺";
		}
		$(field).find("td").each(function(indexTD,valueTD){
			
			if(indexTD==0){//
				$(valueTD).html(htmlValue);
			}
			if(indexTD==1&&premiumType!="6"){
				$(valueTD).html("<input type=\"text\" name=\"premiumRange\" id=\"premiumRange"+num+"\" maxlength=10/> ");
			}else if(indexTD==1&&premiumType=="6"){
				$(valueTD).html("<input type=\"text\" size=\"15\" name=\"premiumRange1\" id=\"premiumRange1a"+num+"\" maxlength=10 /> <=���ѵ�ֵ<= <input type=\"text\" size=\"15\" name=\"premiumRange2\" id=\"premiumRange2a"+num+"\" maxlength=10 />");
				//��JS��֤
				
				var id1 = "premiumRange1a"+num;
				ttvf.addId(id1);
				var id2 = "premiumRange2a"+num;
				intRange.addId(id1);
				intRange.addId(id2);
			}
			
		});
		
		//�����INPUT,������У��
		$(field).find("input").each(function(index,value){
			
			ttvf.addId($(this).attr("id"));
			intRange.addId($(this).attr("id"));//��������������֤����
		});
	}//end setPremiumRange
	
	
	
	//marketingManage\create\frmCreate.jsp end********************************************************************************************
	
	//business\marketingManage\update\index.jsp start***************************************************************************************
	var updateNum=1;
	//ttRange
	//ҳ�����ʱ
	function setUpdate(){

		
		var i=0;
		$("#tableInfo").find("table").each(function(index,value){
			if($(value).attr("updateType")=="update"){
				updateNum++;
				i++;
			}
		});
		//set default
		ttvf.addId("activityName");//�����
		ttvf.addId("activityContent");//����ݽ���
		ttvf.addId("activityStartDateId");//���ʼʱ��
		ttvf.addId("activityEndDateId");//�����ʱ��
//		tt.vf.req.add("geAddServiceActivity.province");////�����
//		tt.vf.req.add("geAddServiceActivity.deptID");////�����
		new tt.LV().set(0,2000).add("geAddServiceActivity.activityContent");//���ʽ��������
		//��Ʒ����
		$("#productModel").find("tr").each(function(index,domObj){
			if(index==0){//��һ��
				$(domObj).find("input").each(function(indexInput,domInputObj){
					if(indexInput==1){
						ttvf.addId($(domInputObj).attr("id"));
					}
				});
			}else{//�ǵ�һ��
				$(domObj).find("input").each(function(indexInput,domInputObj){
					if(indexInput==0){
						ttvf.addId($(domInputObj).attr("id"));
					}
				});
			}
		});
		for(var j=0;j<i;j++){
			ttvf.addId("premumOperator"+j);//����
			ttvf.addId("activityPattern"+j);//���ʽ
		}
		var actId=$.getUrlParam('activityId');
		selectProvince();
		addPreviewFaceInitForUpdate();//����ͼƬ��Ԥ��
		addValidatePeremiumValue();//���ù����ﱣ�ѵ�ֵ�����֤
		addValidateItemID();//���ù��������Ʒ���������֤
		discountShowOrHide();//�ۿ�ID�Ƿ���ʾ
		remDiscountId();//ҳ�����ʱ��������ʽû�д��ۣ�remove()�����е�.clazz����	
		
	}
	//�ۿ�ID�Ƿ���ʾ
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

	
	//����ͼƬ��Ԥ��
	function addPreviewFaceInitForUpdate(){
		var hiddenImages = document.getElementsByName("hiddenImage");
		for(var i=0;i<hiddenImages.length;i++){
			if(hiddenImages[i].value!=""){
				ImagePreview.MODE = "simple";//����simplleģʽ
				
				var facePic = new ImagePreview( $$(hiddenImages[i].id), $$("uploadPicture"+(i+1)+"Preview"), {maxWidth: 230, maxHeight: 160});
				document.getElementById("uploadPicture"+(i+1)+"TR").style.display="";
				//facePic.preview();
			}
		}
	}
	
	//������ ���ѵ�ֵ  ��Ʒ���� ��У��
	function addValidateItemID(){
		
		$("#tableInfo").find("table").each(function(index,domObj){
			
			if($(domObj).attr("updateType")==="update"){
				
				$(domObj).find("select").each(function(indexSelect,domSelectObj){
					
					if(indexSelect==0){//��һ��ѡ����
						if($(domSelectObj).attr("value")=="2"||$(domSelectObj).attr("value")=="3"){//���ñ��ѵ�ֵ
							$(domObj).find("input").each(function(indexInput,domInputObj){
								if(indexInput==0){
									ttvf.addId(domInputObj.id);//���ñ��ѵ�ֵ
								}
							});
						}//end if
						
						if($(domSelectObj).attr("value")=="4"){//���ñ��ѵ�ֵ  ����֮��
							var  obj=$(domObj).find("input:visible");
							$(domObj).find("input:visible").each(function(indexInput,domInputObj){
								if(indexInput==0||indexInput==1){
									ttvf.addId(domInputObj.id);//���ñ��ѵ�ֵ
								}
							});
						}
						
					}//end if(indexSelect==0){ 
					
					if(indexSelect==1){//�ڶ���ѡ���� ���ʽ 
						if($(domSelectObj).attr("value")=="1"){//������пͻ�    ��Ҫ�����Ʒ��У��
							ttvf.addId(getId(domObj,"itemObj"));//������Ʒ��ֵ
						}
						if($(domSelectObj).attr("value")=="2"||$(domSelectObj).attr("value")=="3"){//����ǰN���ͻ�  or N% 
							ttvf.addId(getId(domObj,"itemObj"));//������Ʒ��ֵ
							
							ttvf.addId(getId(domObj,"nValue"));///����N��ֵ
							intRange.addId(getId(domObj,"nValue"));///����N��ֵΪ������
						}
						
						if($(domSelectObj).attr("value")=="4"){//����
							ttvf.addId(getId(domObj,"discountType"));//���ô�������
							ttvf.addId(getId(domObj,"discountRemarkText"));//��������
							intRange.addId(getId(domObj,"nValue"));///����N��ֵΪ������
						}
						
						if($(domSelectObj).attr("value")=="5"){//�ӹ�
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
	//�ڸ����ѵ�ֵ�����֤�ķ���
	function addValidatePeremiumValue(){
		//��һ��ֵΪ������ 
		//�ӵڶ�����ʼ
		var premumOperatorTypes = document.getElementsByName("premumOperator");
		for(var i=1;i<premumOperatorTypes.length;i++){
			var premiumType = premumOperatorTypes[i].value;
			var obj = $(premumOperatorTypes[i]).parent().parent().parent();
			obj.find("tr").each(function(index,value){
				if(index==2){
					if(premiumType==""||premiumType=="1"){//��ѡ�����
						value.style.display="none";//����
					}
					if(premiumType=="2"||premiumType=="3"||premiumType=="4"||premiumType=="5"||premiumType=="6"){//С�� С�ڵ��� ���� ���ڵ���  ����֮��
						//����֤����
						$(value).find("input").each(function(indexInput,InputObj){
							ttvf.addId(InputObj.id);//��¼��
							intRange.addId(InputObj.id);//��¼��
						});
					}//
				}
			});
		}
	}
	
	function getId(domObj,type){
		var idString = "";
		$(domObj).find("tr").each(function(index,trObj){
			if(type=="itemObj"&&index==4){//��Ʒ
				$(trObj).find("input").each(function(indexInput,domOjb){
					if(indexInput==0){
						idString =  domOjb.id;
					}
				});
			}
			
			if(type=="discountType"&&index==5){//��������
				$(trObj).find("select").each(function(indexSelect,domObj){
					if(indexSelect==0){
						idString =domObj.id;
					}
				});
			}
			
			if(type=="nValue"&&index==6){//N��ֵ
				$(trObj).find("input").each(function(indexInput,domOjb){
					if(indexInput==0){
						idString =  domOjb.id;
					}
				});
			}
			if(type=="discountRemarkText"&&index==8){//�������ӵ�ֵ
				$(trObj).find("textarea").each(function(indexSelect,domObj){
					if(indexSelect==0){
						idString =domObj.id;
					}
				});
			}
			if(type=="addShopping"&&index==9){//�ӹ�
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
			if($(value).attr("type")!="button"&&$(value).attr("type")!="hidden"){//�ų�button��ť  �ų�������
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				
				//ttvf.addId($(this).attr("id"));
				if($(value).attr("name")=="itemName"){//���ǹ�Ӧ�̵�ʱ����Ҫ���ù�Ӧ�̵����ֵ����,��ɾ����ʱ��Ҳ��Ҫ��������
					var itemNames = document.getElementsByName("itemName");
					$(value).attr("nameCount",(itemNames.length-1));
				}
				//	if(index!=2){//��Ӧ�̹�˾����  ����� 
					$(this).attr("value","");//�������ֵ
				//				}
			}
		});
		//textArea���id
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
		
		//��selectֵ��ԭ
		$("#" + updateNum).find("select").each(function(index, value){
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			$(this).attr("id",$(this).attr("name") + updateNum);
			if($(this).attr("name")!="discountType"){//�������͵Ĳ�����֤��
				ttvf.addId($(this).attr("id"));
			}
			//
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		
		//�����ʱ������ֵ
		document.getElementById("dateTempId").value="";
		updateNum++;
		setCountNum();//��д���
	}
	
	function valid(){
		if(!checkActivityPatternsRepeat()){
			
			return false;
		}
	}
	//�޸İ�ť
	function doUpdate(){
		if(!tt.validate()){
			return false;
		}
		//У��ͼƬ����
		if(!checkPicture()){
			return false;
		}
		if(!checkActivityPatternsRepeat()){
			return false;
		}
		if(isAddShoppingNull()){//�ж��Ƿ�ӹ���Ʒ
			return false;
		}
		if(!isProductAddForUpdate()){
			return false;
		}
		if(isPremiumRangeError()){//������ѵ�ֵ�ķ�Χ��������ʾ
			return false;
		}
		if(!isDiscountIDNull()){
			return false;
		}
		if(!isProductAddServiceExistForUpdate()){//����������Ʒ�Ƿ���ڸû����  �µĲ�Ʒ  ԭ��isProductAddServiceExistForUpdate()
			setNames();
			document.getElementById("frmInput").submit();
		}else{
			setNames();
			document.getElementById("frmInput").submit();
		}
	}
	function isPremiumRangeError(){
		var isFlag = false;
		var premumOperators = document.getElementsByName("premumOperator");//��������
		for(var i=1;i<premumOperators.length;i++){
			if(premumOperators[i].value=="6"){//6����֮ǰ
				var tbodyObj = $(premumOperators[i]).parent().parent().parent();
				var range1  ="";//���ѵ�ֵ С��
				var range2 = "";//���ѵ�ֵ ����
				tbodyObj.find("input").each(function(index,value){
					if(index==0){//���ѵ�ֵС��
						range1 = $(value).attr("value");
					}
					if(index==1){//���ѵ�ֵ ����
						range2 = $(value).attr("value");
					}
				});
				if(parseInt(range1)>parseInt(range2)){
					alert("����1��ֵ���ڱ���2��ֵ");
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
			//����ajax
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
			  						actStatus="״      ̬�����ѷ�����\r";
			  					 }else{
			  						actStatus="";
			  					 }
		  					error = error+" \r"+"����ƣ�"+data[i].activityName+"\r"+"��Ʒ���ͣ�"+data[i].productName+"\r"+"����ڣ�("+data[i].startDate+"-"+data[i].endDate+")\r"+actStatus;
		  				  }
		  				  if(data.length==0){
		  					returnFlag= true;
		  				  }else{
		  					error = error+" \r"+"�����Ѿ����� �벻Ҫѡ��   ";
			  				alert(error);
			  				
			  			//	$("#BgDiv").css({ display:"block",height:$(document).height()});
			  				/*window.showModalDialog(contextRootPath+"/business/marketingManage/winOpen.jsp?startDate="+startDate+"&endDate="+endDate+"&coreProductNames="+newProduct+
			  						"&activityPatterns="+activityPatternsValue+"&deptID="+deptID+"&activityId="+activityId+"&updatePage="+updatePage,
			  						"��ѯ��Ʒ" ,"dialogWidth=780px;dialogheight=600px");*/
			  				
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
	//�ж�ͼƬ������
	function checkPicture(){
		var uploadPictures=	$("input[name='uploadPicture']");
		for(var i=0;i<uploadPictures.length;i++){
			var filepath = uploadPictures[i].value;
			if(filepath!=""){//����ļ�������
				var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
				extname = extname.toLowerCase();//�����˴�Сд
			    if(extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
			    	alert("�ϴ�ͼƬ"+(i+1)+" ֻ���ϴ�bmp,jpg,gif��ʽ��ͼƬ��");
			     	return false;
			    }
			}
		}
		return true;
	}
	//���ö�������
	function setNameUpdate(){
		var premumOperators = document.getElementsByName("premumOperator");//����
		var activityPatterns = document.getElementsByName("activityPattern");//���ʽ
		for(var i=1;i<premumOperators.length;i++){
			premumOperators[i].name="geAddServiceActivity.geActivitiesRules["+i+"].premumOperator";//���ѵ�ֵ
			activityPatterns[i].name="geAddServiceActivity.geActivitiesRules["+i+"].activityPattern";//���ʽ
			
		}
		//���ݱ������� ���ѵ�ֵ
		var itemIDs = document.getElementsByName("itemID");
		
		var nvalues = document.getElementsByName("nvalue");
		
		
		for(var i=0;i<itemIDs.length;i++){
			if((i+1)!=itemIDs.length){
				itemIDs[i+1].name="geAddServiceActivity.geActivitiesRules["+i+"].itemID";
				activityPatterns[i+1].name = "geAddServiceActivity.geActivitiesRules["+i+"].activityPattern";
				nvalues[i+1].name = "geAddServiceActivity.geActivitiesRules["+i+"].nvalue";
			}
		}
		//�����ϴ�ͼƬ������
		var uploadPictures = document.getElementsByName("uploadPicture");
		var inputHmtlStart = "<input type='hidden' name='updateSerial' value='";
		var inputHmtlEnd ="'/>";
		var htmlString   ="";
		for(var i=0;i<uploadPictures.length;i++){
			htmlString = htmlString+inputHmtlStart+uploadPictures[i].value+inputHmtlEnd;
		}
		$("#productType").append(htmlString);
	}
	
	//˫�����ȡ��Ӧ����Ϣ
	function getGeThirdParterInfoForUpdate(field,eid){
		deptId = $("#authorityid").val();	
		var nameCount = $(field).attr("nameCount");
		 window.open(contextRootPath+"/business/marketingManage/create/selectProduct/selectProductMore/importMoreProduct.jsp?deptId="+deptId+"&nameCount="+nameCount+'&itemId='+eid,"��ѯ��Ʒ");
		//window.open(contextRootPath+'/party/prepareAddGeThirdParterService.do?deptId='+deptId+'&opertion=updateUserMarketing&nameCount='+nameCount+'&itemId='+eid,"��ѯ��Ʒ");
	}

//business\marketingManage\update\index.jsp end***************************************************************************************

	
//common sart*************************************************************************************************************************
/**
	 * ɾ��һ�����
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
		if(itemName.length!=2){//�����һ��
			(tableObj).find("input").each(function(index,value){//ɾ��input�����ʽ
				if(($(this).attr("type")!="button")
						&&($(this).attr("name")=="nvalue"||$(this).attr("name")=="discountID")){//nValue �ۿ�ID���ÿ���
					$(this).removeAttr("ttTalentReqStarId");
					$(this).removeAttr("ttTalentMsgId");
					$(this).parent().find("span").remove();
					$(this).removeClass("talentErrInput");
					ttvf.rmId($(this).attr("id"));
				}
			});
			//ɾ��textArea������
			$(tableObj).find("textArea").each(function(index,value){//ɾ��input�����ʽ
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.rmId($(this).attr("id"));
			});
			//ɾ��select�����ʽ
			$(tableObj).find("select").each(function(index,value){//ɾ��input�����ʽ
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.rmId($(this).attr("id"));
			});
			//ɾ�����
			var obj = $(feld).parent().parent().parent().parent().parent().parent();
			//��ɾ��֮ǰ����ñ������input������ɾ��
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
			//�������
			var spans = $("span");
			var j=0;
			for(var i=0;i<spans.length;i++){
				if(spans[i].id=="count"){
//					spans[i].innerText = "����"+(j);
					$(spans[i]).text("����"+j);
					//alert($(spans[i]).text());
					j++;
				}
			}
			//����nameCount�����nameCount
			var thirdParterIDs =$("select[name='activityPattern']");
			for(var i=0;i<thirdParterIDs.length;i++){
				$(thirdParterIDs[i]).attr("nameCount",i);
			}
			//�����ʱ������ֵ
			document.getElementById("dateTempId").value="";
		}else{//���һ��    //if(itemName.length!=2){
			alert("����һ������");
		}
		remDiscountId();
	}
//common end*************************************************************************************************************************
	//�õ�eids��","���
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
	//�ӹ���Ʒ 
	function upaddShoppingProduct(field){
		var deptId = document.getElementById("authorityid").value;
		if(deptId==""){
			alert("����ѡ����");
		}else{
			var xRule = "";//�ڼ�������
			var yAddShopping=0 ;//�ڼ����ӹ���Ʒ
			var yReal = 0;
			//����������
			var obj = $(field).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
			xRule = obj.attr("id");
			//�������
			var objAddShopping = $(field).parent().parent().parent().parent();
			objAddShopping.find("table").each(function(index,value){
				if($(value).attr("typeProduct")=="addShopping"){
					yAddShopping++;
					if(field.id==$(value).attr("id")){
						yReal = yAddShopping;
					}
				}
			});
			//ѡ��Ҫ�����Ĳ�Ʒ
			 var eids = getEids1();//","��� "G300024";//"G300024,G200058,G300008";//��ҳ���ϻ�ȡ��
			window.open(contextRootPath+"/marketing/prepareSelectAddShopping1.do?operation=0&deptId="+deptId+"&eids="+eids+"&xRule="+(parseInt(xRule)-1)+"&yAddShopping=0","�ӹ���Ʒ");
		}
		
	}
	
	//���Ӽӹ���Ʒ
	var upaddShoppingCount = 0; 
	function upaddShopping(field){
		var xRule = "";//�ڼ�������
		var obj = $(field).parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
		xRule = obj.attr("id");
		var trObj  = $(field).parent().parent().parent();
		var obj = $("#tableAddShoppingHidden");
		trObj.html("");
		trObj.append(obj.html());
		trObj.find("table").each(function(index,value){
			if($(this).attr("name")=="addshoppingProductCallBack"){////�ӹ���Ʒ����
				//����input��ID
				$(this).attr("id",$(this).attr("name")+(parseInt(xRule)-1)+"0");
				upaddShoppingProduct(this);
				addShoppingCount++;
			}
		});
	}
	//�ַ���������
	function getStringUTFLength(str) {
	    var value = str.replace(/[^\x00-\xff]/g,"  ");
	    return value.length;
	}
	//�ַ�����У��
	function leftUTFString(str,len) {
	    if(getStringUTFLength(str)<=len)
	        return str;
	    var value = str.substring(0,len);

	    while(getStringUTFLength(value)>len) {
	        value = value.substring(0,value.length-1);
	    }
	    return value;
	}
	//����ݳ���У��
	function count() {
	    var value = document.getElementById("geAddServiceActivity.activityContent").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=2000) {
	        document.getElementById("geAddServiceActivity.activityContent").value = leftUTFString(document.getElementById("geAddServiceActivity.activityContent").value,2000);
	    }
	}
	//����Ƴ���У��
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
