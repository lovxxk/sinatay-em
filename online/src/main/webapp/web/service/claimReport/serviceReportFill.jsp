<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<div class="middle">
	<div class="h_layout">
		<div class="nav_index">
			<ul>
				<li><a href="${ctx }/index.jsp">��ҳ</a><span> &gt;</span></li>
				<li><a href="${ctx }/web/service/index.jsp">��������</a><span> &gt;</span></li>
				<li class="at">���ⱨ��</li>
			</ul>
		</div>
		<div class="report_main">
		<form id="fm">
			<div class="main_title">���ⱨ��</div>
			<div class="report_area claim_reporter">
				<input type="text" style="display:none" id="ctx" value="${ctx}" />
				<div class="title">��������Ϣ</div>
				<div class="input_area">
					<div class="input_row reportName">
						<label class="input_label" for="reportName">
							<span class="required">*</span><span class="name">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����</span>
						</label>
						<input id="reportName" class="input_text" name="reportName" type="text" maxlength="10" />
					</div>
					<div class="input_row reportPhone">
						<label class="input_label" for="reportPhone">
							<span class="required">*</span><span class="name">�ֻ����룺</span>
						</label>
						<input id="reportPhone" class="input_text" name="reportPhone" type="text" maxlength="11" />
					</div>
					<div class="input_row reportRelation">
						<label class="input_label" for="reportRelation">
							<span class="required">*</span><span class="name">������˹�ϵ��</span>
						</label>
						<input id="reportRelation" class="input_text" name="reportRelation" type="text" />
					</div>
				</div>
			</div>
			<div class="report_area accident_info">
				<div class="title">������Ϣ</div>
				<div class="input_area">
					<div class="input_row accidentName">
						<label class="input_label" for="accidentName">
							<span class="required">*</span><span class="name">������������</span>
						</label>
							<input id="accidentName" class="input_text" name="accidentName" type="text" maxlength="10" />
					</div>
					<div class="input_row accidentIdType">
						<label class="input_label" for="accidentIdType">
							<span class="required">*</span><span class="name">������֤�����ͣ�</span>
						</label>
						<input id="accidentIdType" class="input_text" name="accidentIdType" type="text" />
					</div>
					<div class="input_row accidentId">
						<label class="input_label" for="accidentId">
							<span class="required">*</span><span class="name">������֤�����룺</span>
						</label>
						<input id="accidentId" class="input_text" name="accidentId" type="text" maxlength="30" style="width:280px" />
					</div>
					<div class="input_row sex accidentSex">
						<label class="input_label" for="accidentSex">
							<span class="required">*</span><span class="name">�������Ա�</span>
						</label>
						<div class="input_sex selected" id="male" val="0"></div>
						<label class="tip_label label_sex">��</label>
						<div class="input_sex" id="female" val="1"></div>
						<label class="tip_label label_sex">Ů</label>
						<!-- <label class="input_label" for="accidentSex">
							<span class="required">*</span><span class="name">�������Ա�</span> -->
						
							<input id="accidentSex" class="input_text" name="accidentSex" type="hidden" />
					</div>
					<div class="input_row accidentBirth">
						<label class="input_label" for="">
							<span class="required">*</span><span class="name">�����˳������ڣ�</span>
						</label>
						<input id="accidentBirthYear" class="input_text year" name="accidentBirthYear" type="text" />
						<label class="tip_label" for="accidentBirthYear">��</label>
						<input id="accidentBirthMonth" class="input_text month" name="accidentBirthMonth" type="text" />
						<label class="tip_label" for="accidentBirthMonth">��</label>
						<input id="accidentBirthDay" class="input_text day" name="accidentBirthDay" type="text" />
						<label class="tip_label" for="accidentBirthDay">��</label>
					</div>
					<div class="input_row accidentDate">
						<label class="input_label" for="">
							<span class="required">*</span><span class="name">����ʱ�䣺</span>
						</label>
						<input id="accidentDateYear" class="input_text year" name="accidentDateYear" type="text" />
						<label class="tip_label" for="accidentDateYear">��</label>
						<input id="accidentDateMonth" class="input_text month" name="accidentDateMonth" type="text" />
						<label class="tip_label" for="accidentDateMonth">��</label>
						<input id="accidentDateDay" class="input_text day" name="accidentDateDay" type="text" />
						<label class="tip_label" for="accidentDateDay">��</label>
					</div>
					<div class="input_row place">
						<label class="input_label">
							<span class="required">*</span><span class="name">���յص㣺</span>
						</label>
						<input id="province" class="input_text" name="province" type="text" />
						<label class="tip_label">ʡ</label>
						<input id="city" class="input_text" name="city" type="text" />
						<label class="tip_label">��</label>
						<input id="county" class="input_text" name="county" type="text" />
						<label class="tip_label">��</label>
					</div>
					<div class="input_row place">
						<label class="input_label">
							<span class="required">*</span><span class="name">��ϸ��ַ��</span>
						</label>
						<input id="other" class="input_text" name="other" type="text"  maxlength="20" />
						<label class="warn">�����ظ�����ʡ����</label>
					</div>
					<div class="input_row accidentReason">
						<label class="input_label" for="accidentReason">
							<span class="required">*</span><span class="name">����ԭ��</span>
						</label>
						<input class="input_text" name="accidentReason" type="text" id="accidentReason" />
					</div>
					<div class="input_row accidentStatus">
						<label class="input_label" for="accidentStatus">
							<span class="required">*</span><span class="name">��������״��</span>
						</label>
						<input class="input_text" name="accidentStatus" type="text" id="accidentStatus" />
					</div>
					<div class="input_row accidentDo">
						<label class="input_label" for="accidentDo">
							<span class="required">*</span><span class="name">����ʱ���¹�����</span>
						</label>
						<input class="input_text" name="accidentDo" type="text" id="accidentDo" maxlength="50"/>
					</div>
					<div class="input_row hospital">
						<label class="input_label" for="hospital">
							<span class="required">*</span><span class="name">����ҽԺ���ƣ�</span>
						</label>
						<div class="select_job" tag="insured_info">
							<jsp:include page="/web/common/searchHospital.jsp"></jsp:include>
						</div>
						<input style="display:none" id="hospital" name="hospital" type="text" value="" />
					</div>
					<div class="input_row diagnose">
						<label class="input_label" for="diagnose">
							<span class="required">*</span><span class="name">ҽԺ��ϣ�</span>
						</label>
						<input class="input_text" name="diagnose" type="text" id="diagnose" maxlength="50" />
					</div>
					<div class="input_row claimType">
						<label class="input_label" for="claimType">
							<span class="required">*</span><span class="name">�������ͣ�</span>
						</label>
						<input class="claimType" type="checkbox" id="t01" name="claimType" value="00"/>
						<label class="claimType" for="t01">���&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t02" name="claimType" value="01"/>
						<label class="claimType" for="t02">ȫ��&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t03" name="claimType" value="02"/>
						<label class="claimType" for="t03">�ش󼲲�&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t04" name="claimType" value="03"/>
						<label class="claimType" for="t04">�˲�&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t05" name="claimType" value="04"/>
						<label class="claimType" for="t05">����&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t06" name="claimType" value="05"/>
						<label class="claimType" for="t06">ҽ��&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t07" name="claimType" value="06"/>
						<label class="claimType" for="t07">���ּ���&nbsp;&nbsp;</label>
						<input class="claimType" type="checkbox" id="t08" name="claimType" value="07"/>
						<label class="claimType" for="t08">ʧҵʧ��&nbsp;&nbsp;</label>
					</div>
				</div>
			</div>
			</form>
			<div class="action">
				<button id="submitButton" class="report click_btn">�ύ����</button>
			</div>
		</div>
	</div>
</div>