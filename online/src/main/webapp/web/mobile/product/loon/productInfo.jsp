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
		    <h1>产品详情</h1>
		</div>
		<div data-role="content" >
		<div data-role="navbar">
		            <ul>                
		                <li>
		                    <a href="#" onclick="$('#content-1').show();$('#content-2').hide();$('#content-3').hide();" data-transition="fade" class="ui-btn-active ui-state-persist">
		                        购买须知
		                    </a>
		                </li>
		                <li>
		                    <a href="#" onclick="$('#content-2').show();$('#content-1').hide();$('#content-3').hide();" data-transition="fade" >
		                        收益演示
		                    </a>
		                </li>
		                <li>
		                    <a href="#" onclick="$('#content-3').show();$('#content-1').hide();$('#content-2').hide();" data-transition="fade">
		                        适用人群
		                    </a>
		                </li>
		            </ul>
		    </div>
			<div id="content-1" style="display:''">
			<br>
			<table>
				<tr style="padding:0px">
					<td style="width: 90px;">投保方式:</td>
					<td>网络投保</td>
				</tr>
				<tr >
					<td>投保年龄:</td>
					<td>18-64周岁</td>
				</tr>
				<tr >
					<td>投保范围:</td>
					<td>仅限本人投保</td>
				</tr>
				<tr >
					<td>初始费用:</td>
					<td>全免</td>
				</tr>
				<tr >
					<td>退保手续费:</td>
					<td>一年内，扣除2%，超过一年，退保费用为0</td>
				</tr>
				<tr >
					<td>生效日期:</td>
					<td>投保日的次日零时，具体以保单载明为准</td>
				</tr>
				<tr >
					<td>购买限额:</td>
					<td>单笔限购小于20万元/人，累计限购小于等于200万元/人</td>
				</tr>
				<tr >
					<td>可购买地区:</td>
					<td>浙江、江苏、北京、河北、附件、河南、山东、黑龙江、辽宁、上海、湖北、江西、广东</td>
				</tr>
				<tr >
					<td><font style="color:#c1272d">风险提示:</font></td>
					<td><font style="color:#c1272d">本产品为万能保险，结算利率超过最低保证利率的部分是不确定的</font></td>
				</tr>
			</table>	
			</div>
			<div id="content-2" style="display:none">
				
				 <div data-role="content" style="padding:0px">
				 <p>以投资10万元该产品为例，按预期年化收益率5.3%测算</p>
				*现金价值：返还给客户的实际金额
		    <table data-role="table" id="movie-table" data-mode="reflow" class="ui-responsive table-stroke" data-theme="c">
		      <thead>
		        <tr>
		          <th data-priority="1">领取时间</th>
		          <th data-priority="persist">个人账户价值</th>
		          <th data-priority="2">现金价值</th>
		          <th data-priority="3">一般身故保险金</th>
		          <th data-priority="4">意外身故保险金</th>
				  <th data-priority="5">预期年化收益 </th>
		        </tr>
		      </thead>
		      <tbody>
		        <tr>
		          <th style="margin-top:20px">第1年末</th>
		          <td><span style="text-align:right">105,300</font></td>
		          <td>103,904</td>
		          <td>110,565</td>
		          <td>210,600</td>
				  <td>3.2%</td>
		        </tr>
		        <tr>
		          <th style="margin-top:20px">1年零1天</th>
		          <td>105,300</td>
		          <td>105,300</td>
		          <td>110,565</td>
		          <td>210,600</td>
				  <td>5.3%</td>
		        </tr>
		        <tr>
		          <th style="margin-top:20px">第2年末</th>
		          <td>110,881</td>
		          <td>110,881</td>
		          <td>116,425</td>
		          <td>221,762</td>
				  <td>5.3%</td>
		        </tr>
				<tr>
		          <th style="margin-top:20px">第10年末</th>
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
				<p><img src="${ctx }/web/mobile/product/loon/images/baolilaia_1.png" style="FLOAT: left;width:120px;height:120px" /><font style="font-size:120%;font-weight:bold;">白领精英</font>:Jack，月薪10000，外企小白领，平时注重生活品质。
				</p>
				<p style="text-indent: 2em">除去日常开销，还有5000元的存款，综合考虑之后选择了懒人理财宝，花费10000元，每两个月都买一次，这样一年下来就能获得不错的收益。
				</p>
				</div>
		        
				<div data-role="fieldcontain">	   
				<p><img src="${ctx }/web/mobile/product/loon/images/baolilaia_2.png" style="FLOAT: left;width:120px;height:120px" /><font style="font-size:120%;font-weight:bold;">全职太太</font>:陈太，老公是职业经理人，家庭年收入50万，陈太负责家庭理财和教育孩子。
				</p>
				<p style="text-indent: 2em">除去正常开支，一般都有10余万现金，10万块钱做投资资金太少，银行又没有符合期望的理财产品，直到发现了懒人理财宝，立即决定把手头的闲钱全部拿来投资。通过购买懒人理财宝，每年获得收益5300元。
				</p>
				</div>
				
				<div data-role="fieldcontain">	   
				<p><img src="${ctx }/web/mobile/product/loon/images/baolilaia_3.png" style="FLOAT: left;width:120px;height:120px" /><font style="font-size:120%;font-weight:bold;">职场新人</font>:小李，毕业半年，月薪3000，单身，无不良嗜好。
				</p>
				<p style="text-indent: 2em">除去房租水电等日常开支，每月有1000左右结余。他对理财几乎一窍不通，但不愿把钱存银行，想寻求更高收益的理财产品，于是选择了懒人理财宝，不仅收益高，而且起购资金比较低，只需要1000元。这样小李每月1000多的结余可以追加投入到懒人理财宝中，门槛低且收益高，这样一年下来就能获得不错的收益。
				</p>
				</div>
				
			</div>
		
		<a data-role="button" class="bt_yellow" href="javascript:void(0)" onclick="history.go(-1)">
			   返回
		</a>
		</div><!-- /content -->
		
		
		<div data-role="footer"  data-theme="c">
		<h4>信泰人寿保险股份有限公司</h4>
		</div>
		
		</div><!-- /page -->
		</body>
</html>