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
	</head>
	<body>
		<div data-role="page" data-theme="c">
		<div data-role="header" data-theme="c">
		    <h1>投保须知</h1>
		</div><!-- /header -->
		<div data-role="content">
			<form>
			<div data-role="fieldcontain">
				<p>  
					一.投保前请您仔细阅读<a href="${ctx}/web/mobile/product/loon/pdf/00115600.pdf" target="_blank">保险产品条款</a>和<a target="_blank" href="${ctx}/web/common/insuranceTipBook/index.jsp">投保提示书</a>，请重点关注保险责任、免除保险人责任的条款、犹豫期、等待期、退保等关键信息，确保已完全理解。
				</p>  
				<p>
					二.进行网上投保，请务必准确录入投保事项、投保人、被保险人和身故受益人信息，并如实告知健康状况，如有不实告知， 本公司有权依法解除保险合同，并对合同解除前发生的保险事故不承担保险责任。 
				</p>
				   
				<p>
					三.请根据您的交费能力，选择合适交费金额，以确保本次投保计划符合您的保险需求。
				</p>
				<p>   
					四.您选择网上投保的保险合同生效之日起十日内为犹豫期，保险合同生效日载明于保险单，您在犹豫期内解除保险合同的， 本公司向您无息退还您所支付保险费。若您在犹豫期后解除保险合同的，本公司退还保险合同的现金价值，故如果在犹豫期后解除合同，您会遭受一定损失。 
				</p>   
				<p>
					五.本公司在对投保人、被保险人个人信息严格保密的基础上，因业务需要对投保人或被保险人之个人资料， 有搜集、计算机处理、传递的权利。 
				</p>
				<p>
					六.自本公司收到您支付的首期保险费并确认完成电子投保手续之日起， 至本公司同意承保并签发保险单或发出拒保通知书并退还保险费期间（最长不超过30天）， 本公司仅承担投保人申请投保险种中被保险人的意外身故保险责任（因免责条款约定的情形导致身故的除外），且每单有效投保单的最高赔付金额不超过人民币十万元。 
				</p>
				<p>
					七.您本次投保的是万能保险产品
					<ul style="list-style:none;padding:5px;margin-top:-5px;">
					<li>（1）请您仔细阅读万能保险条款以便全面了解您享有的保障范围、本公司承担的保险责任、本公司不承担保险责任的情形、以及当约定的保险责任发生时您可获得的给付金额的计算方法；</li>
					<li>（2）请您仔细阅读您所投保的万能保险条款以便全面了解您需支付的各项费用的具体扣除情形及您的个人账户价值的计算方法。特别提醒您注意您所交纳的保险费并非全部计入您的个人账户，而是要扣除部分保费用于保险保障和保险公司经营管理；</li>			
					<li>（3）请勿以您已交纳的全部保费为基础来简单套算保证收益；</li>
					<li>（4）万能保险产品仅对个人账户价值的增长提供一个最低保证，实际结算利率高于最低保证利率的部分本公司是不予保证的；</li>
					<li>（5）请您随时关注您的保险合同状况，并及时交纳保费，避免出现因保险合同现金价值不足而影响合同的效力的情形。</li>
					</ul>
				</p>
				<p>
					 
				<p>
			</div>
		
			<div data-role="">
				<h4>声明与授权:</h4>
				<p>一.本人声明就贵公司提出的询问据实告知且所填写的各项内容均属实，可作为贵公司签发保险单的依据，并作为保险合同组成部分。如有隐瞒或告知不实，贵公司有权解除本保险合同。</p>
				<p>二.本人同意贵公司通过电话、手机（包括手机短信）、E-mail、QQ、微博、微信、信件提供保险信息服务。</p>
				<p>三.本人不从事下列工种：易燃易爆物品制造；腐蚀性产品生产；土石方工程施工；高压线路安检；高空、海上、水下或井下作业；特种兵；防暴警察等意外险风险较高的职业或工种。</p>		
			</div>
			
			<input id="checkbox2" type="checkbox" />
			<label for="checkbox2">
				本人已认真阅读和理解投保须知、保险产品条款、 投保提示书，同意上述声明与授权，并同意本保险合同的生效日为签收日，并以此开始计算犹豫期。
			</label>
			<table style="width:100%;"><tr><td>
			<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)">
		           返回
		    </a></td><td>
			<a id="nextBtn" data-role="button" class="bt_red"  onclick="var type = document.getElementById('checkbox2').checked;if (type) {window.location.href = './loonCustomInfoInput.do'} else {alert('请确认页面下方声明内容！');};" data-transition="slide" >
		            下一步
		    </a></td></tr></table>
			</form>
		</div><!-- /content -->
		
		<div data-role="footer"  data-theme="c">
		<h4>信泰人寿保险股份有限公司</h4>
		</div>
		</div><!-- /page -->
	</body>
</html>