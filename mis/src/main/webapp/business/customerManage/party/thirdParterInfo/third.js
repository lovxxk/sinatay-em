//\create\frmCreate.jsp start********************************************************************************************
	/**
	 * ����һ�����
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
				$(this).attr("value","");//���ֵ
				$(this).attr("id",$(this).attr("name") + num);
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.addId($(this).attr("id"));
			//	alert(index+"--------------"+$(this).attr("id"));
				if(index==3){//��ϵ�绰
					//tttel.addId($(this).attr("id"));
					ttMobile.addId($(this).attr("id"));
				}
				if(index==4){
					ttvfemail.addId($(this).attr("id"));
				}
			}
		});
		$("#"+num).find("textarea").each(function(index,value){
			$(this).attr("value","");//���ֵ
			$(this).attr("id",$(this).attr("name") + num);
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			ttvf.addId($(this).attr("id"));
		});
		$("#" + num).find("select").each(function(index, value){//��selectֵ��ԭ
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		num++;
		setCountNum();//��д���
	}
	//��������:\create\frmCreate.jsp��Ҫʹ��;thirdParterInfo\update\index.jsp��Ҫʹ��
	function setCountNum(){//��д���
		var spans = $("span");
		var j = 0;
		for(var i=0;i<spans.length;i++){
			if(spans[i].id=="count"){
				//spans[i].innerText = "��ϵ��"+(j);
				$(spans[i]).text("��ϵ��"+j);
				j ++;
			}
		}
	}

	//���ҵ������
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
	
	function doCreate(){//�ύ��
		if(!checkBusiness()){
			alert("��ѡ��ҵ������");
			return false;
		}
		//check ҵ������
		checkIDNO();//��ѡ�����֤ʱ����֤֤������
		if(!tt.validate()){
			//alert("��֤��ܳ���");
			return false;
		}
		setNames();//���ñ���Ԫ�ص�name���ԣ�����struts2���Զ�װ��
		document.getElementById("frmInput").submit();
	}
	//���ø����������
	function setNames(){//���ñ���Ԫ�ص�name���ԣ�����struts2���Զ�װ��
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
	//������֤���� \create\frmCreate.jsp��Ҫʹ��,thirdParterInfo\update\index.jsp��Ҫʹ��
	function checkIDNO(){
		var identifyNumbers = document.getElementsByName("identifyNumber");
		for(var i=0;i<identifyNumbers.length;i++){
			if((i+1)!=identifyNumbers.length){
				validateIDNO(identifyNumbers[i+1]);
			}
		}
		
	}
	//��֤���֤�ĺ�������   \create\frmCreate.jsp��Ҫʹ��,thirdParterInfo\update\index.jsp��Ҫʹ��   
	function validateIDNO(field){//����֤�����������֤���ж�
		var tableObj = $(field).parent().parent().parent();
		//��������ҵ���֤�������Ӧ��֤������
		var IDTypeValue;//֤������
		$(tableObj).find("select").each(function(index,value){
			if(index==0){
				IDTypeValue = $(this).val();//��֤�����͸�ֵ
			}
			
		});
		if(IDTypeValue != "" && IDTypeValue == "01"){
			if(field.value != ""){
				$(field).removeAttr("ttTalentReqStarId");
				$(field).removeAttr("ttTalentMsgId");
				$(field).parent().find("span").remove();
				$(field).removeClass("talentErrInput");
				ttidCard.addId(field.id);//���ָ����֤������ ��Ҫ
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
	var ttMobile = new tt.RV().set(new RegExp("^(13[0-9]{9})|(15[0-9]{9})|(18[0-9]{9})$"), "�ֻ��Ų���ȷ��");
	var ttCompanyPhone = new tt.RV().set(new RegExp("^[0-9]{1}[0-9-]+$"), "��˾�绰����ȷ��")
	//ҳ�����ʱ
	function setUpdate(){
		selectProvince();
//		selectCity();
		var i=0;
		$("#tableInfo").find("table").each(function(index,value){
			updateNum++;
			i++;
		});
		//set default
		ttvf.addId("thirdParterName");//��˾����
		ttvf.addId("companyPhone");//��˾�绰
		ttvf.addId("address");//��˾��ַ
		//ttvf.addId("areaSubordinate");//��˾��������
		ttvf.addId("infoUrl");//��˾��ַ
		ttvf.addId("companyEmail");//��˾��������
		ttvf.addId("companyType");//��˾����
		ttvf.addId("mechanismArea");//�����
		ttvf.addId("cityId");//�����
		ttCompanyPhone.addId("companyPhone");
		new tt.LV().set(0,200).add("geThirdParterInfo.address");//���ʽ��������
		
		for(var j=0;j<i;j++){
			ttvf.addId("userName"+j);//��ϵ������
			ttvf.addId("identifyNumber"+j);//֤������
			ttvf.addId("birthday"+j);//��������
			ttvf.addId("telePhone"+j);//��ϵ�绰
			ttvf.addId("contactAddress"+j);//��ϵ��ַ
			ttvf.addId("email"+j);//��ϵ������
			ttvfemail.addId("email"+j);//��ϵ������
			//tttel.addId("telePhone"+j);//��ϵ�绰
			ttMobile.addId("telePhone"+j);//��ϵ�绰
		}
		//email
		ttvfemail.addId("companyEmail");//��˾��������
		
		
		//����ҳ���checkBox��ѡ��
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
	//�޸Ĳ���ʱ������
	/*function insertTableForUpdate(){
		var ab = $("#tableInfoHiddenForUpdate").find("tr:first>td");
		if(!isNaN($("tr[id!='']").length)&&$("tr[id!='']").length>0){//�ж�ȡ����<tr>ֵ�Ƿ�Ϊ����
			updateNum=$("tr[id!='']").length;
		}
		var a = "<tr id='" + updateNum + "'><td>" + $(ab).html()+ "</td></tr>";
		$("#tableInfo").append(a);	
		$("#" + updateNum).find("input").each(function(index, value){
			if($(value).attr("type")!="button"){//����ǰ�ť�����
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
				$(this).attr("value","");//���ֵ
			}
		});
		//��selectֵ��ԭ
		$("#" + updateNum).find("select").each(function(index, value){
			$(value).find("option:first").each(function(optionIndex,optionValue){
				$(optionValue).attr("selected","selected");
			});
		});
		updateNum++;
		setCountNum();//��д���
	}*/
	/**
	 * �༭ʱ�Ĳ���table
	 */
	function insertTableForUpdate(){
		var ab = $("#tableInfoHiddenForUpdate").find("tr:first>td");
		var a = "<tr id='tableInfoHiddenForUpdate_tr" + updateNum + "'><td>" + $(ab).html()+ "</td></tr>";
		$("#tableInfo").append(a);
		$("#tableInfoHiddenForUpdate_tr" + updateNum).find("input").each(function(index, value){
			
			$(this).attr("id",$(this).attr("name") + updateNum);
			if($(value).attr("type")!="button"&&$(value).attr("type")!="hidden"){//�ų�button��ť  �ų�������
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
				if($(value).attr("name")=="itemName"){//���ǹ�Ӧ�̵�ʱ����Ҫ���ù�Ӧ�̵����ֵ����,��ɾ����ʱ��Ҳ��Ҫ��������
					var itemNames = document.getElementsByName("itemName");
					$(value).attr("nameCount",(itemNames.length-1));
				}
//				if(index!=2){//��Ӧ�̹�˾����  ����� 
					$(this).attr("value","");//�������ֵ
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
		//textArea���id
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
		
		//��selectֵ��ԭ
		$("#tableInfoHiddenForUpdate_tr" + updateNum).find("select").each(function(index, value){
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
	//	document.getElementById("dateTempId").value="";
		updateNum++;
		setCountNum();//��д���
	}
	//�޸İ�ť
	function doUpdate(){
		
		if(!checkBusiness()){
			alert("��ѡ��ҵ������");
			return false;
		}
		//���֤�����У��
		checkIDNO();
		if(!tt.validate()){
			//alert("��֤��ܳ���");
			return false;
		}
		setNameUpdate();
		document.getElementById("frmInput").submit();
	}
	//���ö�������
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
	 * ɾ��һ�����
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
			 *  textarea ɾ��У��
			 */
			$(tableObj).find("textarea").each(function(index,value){
				$(this).removeAttr("ttTalentReqStarId");
				$(this).removeAttr("ttTalentMsgId");
				//$(this).parent().find("span").remove();
				$(this).removeClass("talentErrInput");
				ttvf.rmId($(this).attr("id"));
				ttidCard.rmId($(this).attr("id"));
				
			});
			
		//ɾ��select�����ʽ
		$(tableObj).find("select").each(function(index,value){//ɾ��input�����ʽ
			$(this).removeAttr("ttTalentReqStarId");
			$(this).removeAttr("ttTalentMsgId");
			$(this).parent().find("span").remove();
			$(this).removeClass("talentErrInput");
			ttvf.rmId($(this).attr("id"));
		});
		//
	//$(feld).parent().parent().parent().parent().parent().parent().remove();
		//���           
		//var obj = $(feld).parent().parent().parent().parent().parent().parent();
		var tableObjOne = $(feld).parent().parent().parent().parent().parent().parent();
		//alert($(tableObjOne).html());
		$(tableObjOne).remove();

		var spans = $("span");
		var j=0;
		for(var i=0;i<spans.length;i++){
			if(spans[i].id=="count"){
				$(spans[i]).text("��ϵ��"+j);
				j++;
			}
		}
	}
	/**
	 * ɾ��һ���������������̨����ɾ����¼
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
	  			  if(backData == 'success'){//ɾ�����
	  				$(feld).parent().parent().parent().parent().remove();//ͨ����������ҵ����
	  				//���
	  				var spans = document.getElementsByTagName('span');
	  				for(var i=0;i<spans.length;i++){
	  					spans[i].innerText = "��ϵ��"+(i+1);
	  				}
	  			  }
	  		  }
	  		});
	}
	/**
	 * �ύ���ӹ���
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
	 * �ύ�޸Ĺ���
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
	//����ҵ������ѡ��ʡ
	function selectProvince(){
		var thirdParterID=$.getUrlParam('geThirdParterInfo.thirdParterID');
		document.getElementById("cityId").value="";//������ѡ��
		document.getElementById("suv").style.display="none";//������ѡ��
		//document.getElementById("cityId").style.display="none";//������ѡ��
		var resultBusinessArea = "";//ҵ������
		var infoOne;
		var BusinessAreas = document.getElementsByName("BusinessArea");
		for(var i=0;i<BusinessAreas.length;i++){
			if(BusinessAreas[i].checked){//ѡ�е��Ǹ�
				resultBusinessArea =BusinessAreas[i].value; 
			}
		}
		if(resultBusinessArea=="1"){//����
			document.getElementById("cityId").style.display = "none";
			$("#authorityid").html("<option value='1'>ȫ��</option>");
		}else{//�ٲ�����
			//document.getElementById("cityId").style.display = "";
			//����ajax
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
		  				  var info = "<option value=''>--��ѡ��--</option>";
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

	//����ʡѡ����
	function selectCity(){
		var authorityidDept;
		var thirdParterID=$.getUrlParam('geThirdParterInfo.thirdParterID');
		var ProvinceId = $("#mechanismArea").val();
		var province = $("#mechanismArea").val();
		if(province==""||province=="1"||province=="2"||province=="3"||province=="4"){//��ѡ�� �� ����ȫ��
			$("#suv").hide();//������
			$("#cityId").hide();//������
			$("#cityId").html("<option value='"+province+"'>ȫ��</option>");
		}else{
		$("#suv").show();
		$("#cityId").show();
		var ProvinceId = $("#mechanismArea").val();
		document.getElementById("suv").style.display="block";//������ѡ��
		//document.getElementById("cityId").style.display="block";//������ѡ��
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
		  				  var info = municipalityFlag ? "" : "<option value=''>--��ѡ��--</option>";
		  				//  var info = "<option value=''>--��ѡ��--</option>";
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
	//��˾��ַУ��
	function countAddress() {
	    var value = document.getElementById("address").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=200) {
	        document.getElementById("address").value = leftUTFString(document.getElementById("address").value,200);
	    }
	}
	//��˾����У��
	function countThirdParterName() {
	    var value = document.getElementById("thirdParterName").value;
	    value = value.replace(/[\u4e00-\u9fa5]/g,"  ");
	    if(value.length>=200) {
	        document.getElementById("thirdParterName").value = leftUTFString(document.getElementById("thirdParterName").value,200);
	    }
	}
	//�����ַ�����
	function toBreakWord(){
		//��˾��ַ�������ƻ���
		if(document.getElementById("address")!=null){
		    var obj=document.getElementById("address");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\��/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
		//��ϵ�˵�ַ
		/*
		if(document.getElementById("ctAddress")!=null){
		    var obj=document.getElementById("ctAddress");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\��/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
		*/
		//��ϵ�˵�ַ
		if(document.getElementById("tharterName")!=null){
		    var obj=document.getElementById("tharterName");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\��/g,',');
		    
		    var strTemp="";
		    while(strContent.length>10){
		          strTemp+=strContent.substr(0,41)+"<br />"; 
		          strContent=strContent.substr(41,strContent.length); 
		    }
		    strTemp+=" "+strContent;
		    obj.innerHTML=strTemp;
		}
		//��ϵ�˵�ַ
		if(document.getElementById("urlIndo")!=null){
		    var obj=document.getElementById("urlIndo");
		    var contentStr=obj.innerHTML;
		    
		    var strContent=contentStr.replace(/\��/g,',');
		    
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
	 * У����������
	 * @param field
	 * @returns {Boolean}
	 */
	function textAreaMaxLen(field){
		 var iMaxLen = parseInt(field.getAttribute('maxlength'));
		    var iCurLen = field.value.length;
		    if ( field.getAttribute && iCurLen > iMaxLen ){
		    	field.value = field.value.substring(0, iMaxLen);
		    	alert("�������150����");
		    	return false ;
		    }
	}