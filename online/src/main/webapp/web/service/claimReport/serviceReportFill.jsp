<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">首页</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">服务中心</a><span> &gt;</span></li>
				<li class="at">理赔报案</li>
			</ul>
		</div>
		<div class="report_main">
		<form id="fm">
			<div class="main_title">理赔报案</div>
			<div class="report_area claim_reporter">
				<input type="text" style="display:none" id="ctx" value="${ctx}" />
				<div class="title">报案人信息</div>
				<div class="input_area">
					<div class="input_row reportName">
						<label class="input_label" for="reportName">
							<span class="required">*</span><span class="name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
						</label>
						<input id="reportName" class="input_text" name="reportName" type="text" maxlength="10" />
					</div>
					<div class="input_row reportPhone">
						<label class="input_label" for="reportPhone">
							<span class="required">*</span><span class="name">手机号码：</span>
						</label>
						<input id="reportPhone" class="input_text" name="reportPhone" type="text" maxlength="11" />
					</div>
					<div class="input_row reportRelation">
						<label class="input_label" for="reportRelation">
							<span class="required">*</span><span class="name">与出险人关系：</span>
						</label>
						<input id="reportRelation" class="input_text" name="reportRelation" type="text" />
					</div>
				</div>
			</div>
			<div class="report_area accident_info">
				<div class="title">出险信息</div>
				<div class="input_area">
					<div class="input_row accidentName">
						<label class="input_label" for="accidentName">
							<span class="required">*</span><span class="name">出险人姓名：</span>
						</label>
							<input id="accidentName" class="input_text" name="accidentName" type="text" maxlength="10" />
					</div>
					<div class="input_row accidentIdType">
						<label class="input_label" for="accidentIdType">
							<span class="required">*</span><span class="name">出险人证件类型：</span>
						</label>
						<input id="accidentIdType" class="input_text" name="accidentIdType" type="text" />
					</div>
					<div class="input_row accidentId">
						<label class="input_label" for="accidentId">
							<span class="required">*</span><span class="name">出险人证件号码：</span>
						</label>
						<input id="accidentId" class="input_text" name="accidentId" type="text" maxlength="30" style="width:280px" />
					</div>
					<div class="input_row sex accidentSex">
						<label class="input_label" for="accidentSex">
							<span class="required">*</span><span class="name">出险人性别：</span>
						</label>
						<div class="input_sex selected" id="male" val="0"></div>
						<label class="tip_label label_sex">男</label>
						<div class="input_sex" id="female" val="1"></div>
						<label class="tip_label label_sex">女</label>
						<!-- <label class="input_label" for="accidentSex">
							<span class="required">*</span><span class="name">出险人性别：</span> -->
						
							<input id="accidentSex" class="input_text" name="accidentSex" type="hidden" />
					</div>
					<div class="input_row accidentBirth">
						<label class="input_label" for="">
							<span class="required">*</span><span class="name">出险人出生日期：</span>
						</label>
						<input id="accidentBirthYear" class="input_text year" name="accidentBirthYear" type="text" />
						<label class="tip_label" for="accidentBirthYear">年</label>
						<input id="accidentBirthMonth" class="input_text month" name="accidentBirthMonth" type="text" />
						<label class="tip_label" for="accidentBirthMonth">月</label>
						<input id="accidentBirthDay" class="input_text day" name="accidentBirthDay" type="text" />
						<label class="tip_label" for="accidentBirthDay">日</label>
					</div>
					<div class="input_row accidentDate">
						<label class="input_label" for="">
							<span class="required">*</span><span class="name">出险时间：</span>
						</label>
						<input id="accidentDateYear" class="input_text year" name="accidentDateYear" type="text" />
						<label class="tip_label" for="accidentDateYear">年</label>
						<input id="accidentDateMonth" class="input_text month" name="accidentDateMonth" type="text" />
						<label class="tip_label" for="accidentDateMonth">月</label>
						<input id="accidentDateDay" class="input_text day" name="accidentDateDay" type="text" />
						<label class="tip_label" for="accidentDateDay">日</label>
					</div>
					<div class="input_row place">
						<label class="input_label">
							<span class="required">*</span><span class="name">出险地点：</span>
						</label>
						<input id="province" class="input_text" name="province" type="text" />
						<label class="tip_label">省</label>
						<input id="city" class="input_text" name="city" type="text" />
						<label class="tip_label">市</label>
						<input id="county" class="input_text" name="county" type="text" />
						<label class="tip_label">县</label>
					</div>
					<div class="input_row place">
						<label class="input_label">
							<span class="required">*</span><span class="name">详细地址：</span>
						</label>
						<input id="other" class="input_text" name="other" type="text"  maxlength="20" />
						<label class="warn">请勿重复输入省市区</label>
					</div>
					<div class="input_row accidentReason">
						<label class="input_label" for="accidentReason">
							<span class="required">*</span><span class="name">出险原因：</span>
						</label>
						<input class="input_text" name="accidentReason" type="text" id="accidentReason" />
					</div>
					<div class="input_row accidentStatus">
						<label class="input_label" for="accidentStatus">
							<span class="required">*</span><span class="name">出险人现状：</span>
						</label>
						<input class="input_text" name="accidentStatus" type="text" id="accidentStatus" />
					</div>
					<div class="input_row accidentDo">
						<label class="input_label" for="accidentDo">
							<span class="required">*</span><span class="name">出险时从事工作：</span>
						</label>
						<input class="input_text" name="accidentDo" type="text" id="accidentDo" maxlength="50"/>
					</div>
					<div class="input_row hospital">
						<label class="input_label" for="hospital">
							<span class="required">*</span><span class="name">就诊医院名称：</span>
						</label>
						<div class="select_job" tag="insured_info">
							<jsp:include page="/web/common/searchHospital.jsp"></jsp:include>
						</div>
						<input style="display:none" id="hospital" name="hospital" type="text" value="" />
					</div>
					<div class="input_row diagnose">
						<label class="input_label" for="diagnose">
							<span class="required">*</span><span class="name">医院诊断：</span>
						</label>
						<input class="input_text" name="diagnose" type="text" id="diagnose" maxlength="50" />
					</div>
					<div class="input_row claimType">
						<label class="input_label" for="claimType">
							<span class="required">*</span><span class="name">理赔类型：</span>
						</label>
						<input class="claimType" type="checkbox" id="t01" name="claimType" value="00"/>
						<label class="claimType" for="t01">身故&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t02" name="claimType" value="01"/>
						<label class="claimType" for="t02">全残&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t03" name="claimType" value="02"/>
						<label class="claimType" for="t03">重大疾病&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t04" name="claimType" value="03"/>
						<label class="claimType" for="t04">伤残&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t05" name="claimType" value="04"/>
						<label class="claimType" for="t05">豁免&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t06" name="claimType" value="05"/>
						<label class="claimType" for="t06">医疗&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t07" name="claimType" value="06"/>
						<label class="claimType" for="t07">特种疾病&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t08" name="claimType" value="07"/>
						<label class="claimType" for="t08">失业失能&nbsp;&nbsp;</label>
					</div>
				</div>
			</div>
			</form>
			<div class="action">
				<button id="submitButton" class="report click_btn">提交报案</button>
			</div>
		</div>
	</div>
</div>