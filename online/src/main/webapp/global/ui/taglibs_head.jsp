<link href="${ctx}/global/css/global.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/resources/css/frame.css" rel="stylesheet" type="text/css"/>
		<script src="${ctx}/global/js/jquery/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/jquery/jquery-gbk-ajax.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/common/main.js" type="text/javascript"></script>
		<script src="${ctx }/global/js/util.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/frame.js" type="text/javascript"></script>
		<script src="${ctx}/global/js/json/insureflow.formwork.json.js" type="text/javascript"></script>
		
		<script>
			var contextRootPath = "${ctx}";
			
			if(typeof JSON == 'undefined'){
				Sinosoft.loader.script("${ctx}/global/js/json/json2.js");
			}
			
			function banBackSpace(e){      
			    var ev = e || window.event;
			    var obj = ev.target || ev.srcElement;
			       
			    var t = obj.type || obj.getAttribute('type');
			       
			    var vReadOnly = obj.getAttribute('readonly');   
			    var vEnabled = obj.getAttribute('enabled');   
			    vReadOnly = (vReadOnly == null) ? false : vReadOnly;   
			    vEnabled = (vEnabled == null) ? true : vEnabled;
			       
			    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")    
			                && (vReadOnly==true || vEnabled!=true))?true:false;   
			      
			    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")   
			                ?true:false;           
			       
			    if(flag2){   
			        return false;   
			    }   
			    if(flag1){      
			        return false;      
			    }      
			}   
			  
			document.onkeypress=banBackSpace;   
			document.onkeydown=banBackSpace;   
		</script>
		<%@ include file="/global/google/inner.jsp"%>
		<%@ include file="/global/baidu/inner.jsp"%>