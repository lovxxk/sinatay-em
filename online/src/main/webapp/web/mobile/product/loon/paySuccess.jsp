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
		<link rel="stylesheet" type="text/css" href="${ctx}/global/css/mobile/colorbox.css" charset="gbk" />
		
		<script src="${ctx}/global/js/mobile/jquery-1.9.1.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.mobile-1.3.0.min.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/common.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/verify.js" charset="gbk"></script>
		<script src="${ctx}/global/js/mobile/jquery.colorbox.js" charset="gbk"></script>
		
		<script language="javascript">
			var ctx = "${ctx}";
			function checkSubmit() {
				window.opener=null;
				window.open('','_self');
				window.close();
			}
		</script>
	</head>

	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>֧�����</h1>
		</div><!-- /header -->
		<div style="text-align:center;width:100%;">
		<p style="color:gray;">��.��д��Ϣ...��.ȷ����Ϣ...<font style="color:#c1272d">��.֧������</font></p>
		</div>
		<hr/>
		<div data-role="content">
			
			<div style="">
		            <img src="${ctx }/web/mobile/product/loon/images/success.png"/>
		    </div>
			
			<p>�𾴵� ${orderForm.insurancePolicy.insuranceApplicant.fullName}<br>
				���ı����Ѿ�֧���ɹ�����Ϣ���£�
			</p>
			
			<table data-role="table" id="movie-table" data-mode="reflow" class="ui-responsive table-stroke" data-theme="c">
		      <thead>
		        <tr>
		          <th data-priority="1">��Ʒ����</th>
		          <th data-priority="persist">Ͷ������</th>
		          <th data-priority="2">������������</th>
		          <th data-priority="3">���</th>
		          
		        </tr>
		      </thead>
		      <tbody>
		        <tr>
		          <th style="margin-top:20px">${orderForm.productName}</th>
		          <td><span style="text-align:right">${orderForm.insurancePolicy.applicationNumber}</font></td>
		          <td>${orderForm.insurancePolicy.insureds[0].fullName}</td>
		          <td>
		          	��<fmt:formatNumber value="${orderForm.insurancePolicy.grossPremium}" pattern="0.00"></fmt:formatNumber>
		          </td>
		        </tr>
			   </tbody>
			</table>
			<p>���ӱ����Ժ��͵�${orderForm.insurancePolicy.insuranceApplicant.email}������ע����գ���Ҳ���Ե�¼��Ա���Ĳ鿴�����ص��ӱ�����</p>
			
			<a data-role="button" class="bt_red" onclick="javascript:checkSubmit()" data-transition="slide">
				ȷ��
		    </a>
		</div><!-- /content -->
		
		<div data-role="footer"  data-theme="c">
		<h4>��̩���ٱ��չɷ����޹�˾</h4>
		</div>
		
		</div><!-- /page -->
	</body>
</html>