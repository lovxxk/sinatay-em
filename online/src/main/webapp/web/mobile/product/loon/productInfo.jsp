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
			.ui-table-reflow td .ui-table-cell-label,
			.ui-table-reflow th .ui-table-cell-label { 
				width:120px;
				display: block;
				padding: .4em; 
				min-width: 30%; 
				display: inline-block;
				margin: -.4em 1em -.4em -.4em;
			}
		</style>
	</head>
	<body>
		<div data-role="page">
		<div data-role="header" data-theme="c">
		    <h1>��Ʒ����</h1>
		</div>
		<div data-role="content" >
		<div data-role="navbar">
		            <ul>                
		                <li>
		                    <a href="#" onclick="$('#content-1').show();$('#content-2').hide();$('#content-3').hide();" data-transition="fade" class="ui-btn-active ui-state-persist">
		                        ������֪
		                    </a>
		                </li>
		                <li>
		                    <a href="#" onclick="$('#content-2').show();$('#content-1').hide();$('#content-3').hide();" data-transition="fade" >
		                        ������ʾ
		                    </a>
		                </li>
		                <li>
		                    <a href="#" onclick="$('#content-3').show();$('#content-1').hide();$('#content-2').hide();" data-transition="fade">
		                        ������Ⱥ
		                    </a>
		                </li>
		            </ul>
		    </div>
			<div id="content-1" style="display:''">
			<br>
			<table>
				<tr style="padding:0px">
					<td style="width: 90px;">Ͷ����ʽ:</td>
					<td>����Ͷ��</td>
				</tr>
				<tr >
					<td>Ͷ������:</td>
					<td>18-64����</td>
				</tr>
				<tr >
					<td>Ͷ����Χ:</td>
					<td>���ޱ���Ͷ��</td>
				</tr>
				<tr >
					<td>��ʼ����:</td>
					<td>ȫ��</td>
				</tr>
				<tr >
					<td>�˱�������:</td>
					<td>һ���ڣ��۳�2%������һ�꣬�˱�����Ϊ0</td>
				</tr>
				<tr >
					<td>��Ч����:</td>
					<td>Ͷ���յĴ�����ʱ�������Ա�������Ϊ׼</td>
				</tr>
				<tr >
					<td>�����޶�:</td>
					<td>�����޹�С��20��Ԫ/�ˣ��ۼ��޹�С�ڵ���200��Ԫ/��</td>
				</tr>
				<tr >
					<td>�ɹ������:</td>
					<td>�㽭�����ա��������ӱ������������ϡ�ɽ�������������������Ϻ����������������㶫</td>
				</tr>
				<tr >
					<td><font style="color:#c1272d">������ʾ:</font></td>
					<td><font style="color:#c1272d">����ƷΪ���ܱ��գ��������ʳ�����ͱ�֤���ʵĲ����ǲ�ȷ����</font></td>
				</tr>
			</table>	
			</div>
			<div id="content-2" style="display:none">
				
				 <div data-role="content" style="padding:0px">
				 <p>��Ͷ��10��Ԫ�ò�ƷΪ������Ԥ���껯������5.3%����</p>
				*�ֽ��ֵ���������ͻ���ʵ�ʽ��
		    <table data-role="table" id="movie-table" data-mode="reflow" class="ui-responsive table-stroke" data-theme="c">
		      <thead>
		        <tr>
		          <th data-priority="1">��ȡʱ��</th>
		          <th data-priority="persist">�����˻���ֵ</th>
		          <th data-priority="2">�ֽ��ֵ</th>
		          <th data-priority="3">һ����ʱ��ս�</th>
		          <th data-priority="4">������ʱ��ս�</th>
				  <th data-priority="5">Ԥ���껯���� </th>
		        </tr>
		      </thead>
		      <tbody>
		        <tr>
		          <th style="margin-top:20px">��1��ĩ</th>
		          <td><span style="text-align:right">105,300</font></td>
		          <td>103,904</td>
		          <td>110,565</td>
		          <td>210,600</td>
				  <td>3.2%</td>
		        </tr>
		        <tr>
		          <th style="margin-top:20px">1����1��</th>
		          <td>105,300</td>
		          <td>105,300</td>
		          <td>110,565</td>
		          <td>210,600</td>
				  <td>5.3%</td>
		        </tr>
		        <tr>
		          <th style="margin-top:20px">��2��ĩ</th>
		          <td>110,881</td>
		          <td>110,881</td>
		          <td>116,425</td>
		          <td>221,762</td>
				  <td>5.3%</td>
		        </tr>
				<tr>
		          <th style="margin-top:20px">��10��ĩ</th>
		          <td>171,794</td>
		          <td>171,194</td>
		          <td>180,384</td>
		          <td>343,588</td>
				  <td>5.3%</td>
		        </tr>		
		      </tbody>
		    </table>
		 
				</div>
			</div>
			<div id="content-3" style="display:none">		
				<div data-role="fieldcontain">
				<p><img src="${ctx }/web/mobile/product/loon/images/baolilaia_1.png" style="FLOAT: left;width:120px;height:120px" /><font style="font-size:120%;font-weight:bold;">���쾫Ӣ</font>:Jack����н10000������С���죬ƽʱע������Ʒ�ʡ�
				</p>
				<p style="text-indent: 2em">��ȥ�ճ�����������5000Ԫ�Ĵ��ۺϿ���֮��ѡ����������Ʊ�������10000Ԫ��ÿ�����¶���һ�Σ�����һ���������ܻ�ò�������档
				</p>
				</div>
		        
				<div data-role="fieldcontain">	   
				<p><img src="${ctx }/web/mobile/product/loon/images/baolilaia_2.png" style="FLOAT: left;width:120px;height:120px" /><font style="font-size:120%;font-weight:bold;">ȫְ̫̫</font>:��̫���Ϲ���ְҵ�����ˣ���ͥ������50�򣬳�̫�����ͥ��ƺͽ������ӡ�
				</p>
				<p style="text-indent: 2em">��ȥ������֧��һ�㶼��10�����ֽ�10���Ǯ��Ͷ���ʽ�̫�٣�������û�з�����������Ʋ�Ʒ��ֱ��������������Ʊ���������������ͷ����Ǯȫ������Ͷ�ʡ�ͨ������������Ʊ���ÿ��������5300Ԫ��
				</p>
				</div>
				
				<div data-role="fieldcontain">	   
				<p><img src="${ctx }/web/mobile/product/loon/images/baolilaia_3.png" style="FLOAT: left;width:120px;height:120px" /><font style="font-size:120%;font-weight:bold;">ְ������</font>:С���ҵ���꣬��н3000�������޲����Ⱥá�
				</p>
				<p style="text-indent: 2em">��ȥ����ˮ����ճ���֧��ÿ����1000���ҽ��ࡣ������Ƽ���һ�ϲ�ͨ������Ը��Ǯ�����У���Ѱ������������Ʋ�Ʒ������ѡ����������Ʊ�����������ߣ��������ʽ�Ƚϵͣ�ֻ��Ҫ1000Ԫ������С��ÿ��1000��Ľ������׷��Ͷ�뵽������Ʊ��У��ż���������ߣ�����һ���������ܻ�ò�������档
				</p>
				</div>
				
			</div>
		
		<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)">
			   ����
		</a>
		</div><!-- /content -->
		
		
		<div data-role="footer"  data-theme="c">
		<h4>��̩���ٱ��չɷ����޹�˾</h4>
		</div>
		
		</div><!-- /page -->
		</body>
</html>