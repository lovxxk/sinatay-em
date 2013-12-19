<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<form id="confirmProposalSIDForm" action="${ctx }/sale/toPayConfirmInfo.do" method="post">
	<input type="hidden" id="eId" name="eId" value="${eId }">
	<input type="hidden" id="confirmProposalSID" name="proposalSID" value="${proposalSID }">
</form>