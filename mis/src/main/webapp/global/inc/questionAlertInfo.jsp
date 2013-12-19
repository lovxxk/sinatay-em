<%@ page language="java" contentType="text/html;charset=GBK" %>
<%@ include file="/global/ui/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/global/js/jquery-1.6.2.min.js" charset="gbk"></script>

<c:choose>
<c:when test="${type=='create'}"><!-- 新建的时候提示信息 -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			var MainModelLength=top.opener.document.getElementById("hidden_mainmodel2").rows.length;
			//alert("MainModelLength333:"+MainModelLength);
			var logo = "${logo}";
			var image1 = "${image1}";
			//alert(logo);
			//alert(image1);
			var strLogo="<input class='logo' type='hidden' name='geSurveyMainModels["+MainModelLength+"].logo' value='"+logo+"'>";
			var strImage1="<input class='image1' type='hidden' name='geSurveyMainModels["+MainModelLength+"].image1' value='"+image1+"'>";
			$("#hidden_mainmodel2",window.opener.document).append("<tr><td><span id='modelspanB"+MainModelLength+"'></span>"+strLogo+strImage1+"</td></tr>");
			//$("#geSurveyMainModel_logo",window.opener.document).html("<input type='hidden' name='geSurveyMainModel["+MainModelLength+"].logo' value='"+logo+"'>");
			//$("#geSurveyMainModel_image1",window.opener.document).html("<input type='hidden' name='geSurveyMainModel["+MainModelLength+"].image1' value='"+image1+"'>");
			
			alert("保存成功!");
			window.parent.close();
			//window.close();
		</script>
	</c:when>
	<c:otherwise>
		<script language="javascript">
			alert("${message}");
		</script>
	</c:otherwise>
	</c:choose>
</c:when>
<c:when test="${type=='update'}"><!-- 修改时提示信息 -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
		var MainModelLength=top.opener.document.getElementById("hidden_mainmodel2").rows.length;
		var logo = "${logo}";
		var image1 = "${image1}";

		var flag_logo="${flag_logo}";
		var flag_image1="${flag_image1}";
		
		if(null == flag_logo || "" == flag_logo){
			logo = $("#modelspanB${rownum}",window.opener.document).next().val();
		}
		if(null == flag_image1 || "" == flag_image1){
			image1 = $("#modelspanB${rownum}",window.opener.document).next().next().val();
		}
		var strLogo="<input class='logo' type='hidden' name='geSurveyMainModels["+(MainModelLength-1)+"].logo' value='"+logo+"'>";
		var strImage1="<input class='image1' type='hidden' name='geSurveyMainModels["+(MainModelLength-1)+"].image1' value='"+image1+"'>";
		$("#modelspanB${rownum}",window.opener.document).parent().parent().html("<td><span id='modelspanB"+(MainModelLength-1)+"'></span>"+strLogo+strImage1+"</td>");
		alert("修改成功!");
		window.close();
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert('${message}');
		</script>
	</c:otherwise>
	</c:choose>
</c:when>
<c:otherwise><!-- 类似删除的提示信息 -->
	<c:choose>
	<c:when test="${flag eq 1}">
		<script language="javascript">
			alert("twesdsdf");
			window.parent.close();
			window.parent.opener.parent.frames[0].doSearch();
		</script>
	</c:when>
	<c:otherwise>
		<script language="javascript">
			alert("1111111");
		</script>
	</c:otherwise>
	</c:choose>
</c:otherwise>
</c:choose>
