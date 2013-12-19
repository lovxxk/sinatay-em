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
		
		<script src="${ctx}/global/js/mobile/jquery-1.9.1.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.mobile-1.3.0.min.js" charset="gbk"></script>
		
		<style type="text/css">
			.mytable{
				font-size:12.5px;
			}
			
			.mytable td{
			   padding:0;
			}
			
			.dyfirst:first-letter {
				font-size:120%;
				font-weight:bold;
				color:#000;
				#float:left;
			}
		</style>
		
	</head>
	<body>
		<div data-role="page">
		<div data-role="header" data-theme="c" id="homeHeader">
			<h1>信泰保险懒人理财宝</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="c">
			<div style="">
				<img style="width: 100%" src="${ctx }/web/mobile/product/loon/images/baolilaia_main.png" />
		    </div>
			<table class="mytable">
				<tr >
					<td class="dyfirst" style="width: 55px; padding:0;">高 收益:</td>
					<td style="padding:0;">预期年化收益率5.3%</td>
				</tr>
				<tr >
					<td class="dyfirst" style="padding:0;">低 门槛:</td>
					<td>1000元起售</td>
				</tr>
				<tr >
					<td class="dyfirst">零 成本:</td>
					<td>零初始费,零保单管理费,零风险保险费</td>
				</tr>
				<tr >
					<td class="dyfirst">低 风险:</td>
					<td>保底年化收益2.5%</td>
				</tr>
				<tr >
					<td class="dyfirst">无 损失:</td>
					<td>犹豫期内免10元工本费，资金无损失</td>
				</tr>
				
			</table>
			
<!-- 			<a data-role="button" class="bt_red" href="loonDeclares.do"  data-theme="a"> -->
			<a data-role="button" class="bt_red" href="#" onclick="alert('建设中，敬请期待！');" data-theme="a">
		            马上投保
		    </a>
			
			<ul data-role="listview" data-inset="true" data-divider-theme="c">
		    <li data-role="list-divider">了解更多...</li>
		    <li><a href="loonProductInfo.do" data-transition="slide">产品详情</a></li>
		    <li><a href="loonServices.do" data-transition="slide">售后服务</a></li>
			<li><a href="loonQuestions.do" data-transition="slide">常见问题</a></li>
			</ul>
			
		</div><!-- /content -->
		<div data-role="footer"  data-theme="c">
		<h4>浙ICP备07021747号</h4>
		</div><!-- /header -->
		</div><!-- /page -->
	</body>
</html>