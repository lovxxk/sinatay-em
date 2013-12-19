<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>信泰保险懒人理财宝</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		
<%-- 		<%@ include file="/global/ui/taglibs_head.jsp"%> --%>
		
		<link rel="stylesheet" href="${ctx}/global/css/mobile/jquery.mobile.structure-1.3.0.css" charset="gbk" />
		<link rel="stylesheet" href="${ctx}/global/css/mobile/themes/my-custom-theme.css" charset="gbk" />
		<link rel="stylesheet" href="${ctx}/global/css/mobile/styles.css" charset="gbk" />
		<link rel="stylesheet" type="text/css" href="${ctx}/global/css/mobile/colorbox.css" charset="gbk" />
		
		<script src="${ctx}/global/js/mobile/jquery-1.9.1.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.mobile-1.3.0.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/common.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/verify.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.colorbox.js" charset="gbk"></script>
		<s:action name="provincesCode" namespace="/mobile" id="bean1" />
		<s:action name="banksCode" namespace="/mobile" id="bean2" />
		
		<script language="javascript">
			alert("订单支付错误！");
			var ctx = "${ctx}";
			location.href = ctx + "/mobile/loonIndex.do";
		</script>
	</head>
		
	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>订单支付</h1>
		</div><!-- /header -->
		<div style="text-align:center;width:100%;">
		<p style="color:gray;">①.填写信息...②.确认信息...<font style="color:#c1272d">③.支付订单</font></p>
		</div>
		<hr/>
		
		<div data-role="footer"  data-theme="c">
		<h4>信泰人寿保险股份有限公司</h4>
		</div>
		
		</div><!-- /page -->
	</body>
</html>