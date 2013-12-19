<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>��̩����������Ʊ�</title>
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
			<h1>��̩����������Ʊ�</h1>
		</div><!-- /header -->
		<div data-role="content" data-theme="c">
			<div style="">
				<img style="width: 100%" src="${ctx }/web/mobile/product/loon/images/baolilaia_main.png" />
		    </div>
			<table class="mytable">
				<tr >
					<td class="dyfirst" style="width: 55px; padding:0;">�� ����:</td>
					<td style="padding:0;">Ԥ���껯������5.3%</td>
				</tr>
				<tr >
					<td class="dyfirst" style="padding:0;">�� �ż�:</td>
					<td>1000Ԫ����</td>
				</tr>
				<tr >
					<td class="dyfirst">�� �ɱ�:</td>
					<td>���ʼ��,�㱣�������,����ձ��շ�</td>
				</tr>
				<tr >
					<td class="dyfirst">�� ����:</td>
					<td>�����껯����2.5%</td>
				</tr>
				<tr >
					<td class="dyfirst">�� ��ʧ:</td>
					<td>��ԥ������10Ԫ�����ѣ��ʽ�����ʧ</td>
				</tr>
				
			</table>
			
<!-- 			<a data-role="button" class="bt_red" href="loonDeclares.do"  data-theme="a"> -->
			<a data-role="button" class="bt_red" href="#" onclick="alert('�����У������ڴ���');" data-theme="a">
		            ����Ͷ��
		    </a>
			
			<ul data-role="listview" data-inset="true" data-divider-theme="c">
		    <li data-role="list-divider">�˽����...</li>
		    <li><a href="loonProductInfo.do" data-transition="slide">��Ʒ����</a></li>
		    <li><a href="loonServices.do" data-transition="slide">�ۺ����</a></li>
			<li><a href="loonQuestions.do" data-transition="slide">��������</a></li>
			</ul>
			
		</div><!-- /content -->
		<div data-role="footer"  data-theme="c">
		<h4>��ICP��07021747��</h4>
		</div><!-- /header -->
		</div><!-- /page -->
	</body>
</html>