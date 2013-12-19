<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/global/ui/taglibs_data.jsp"%>
<input type="hidden" id="isSupportBeneficiary" name="isSupportBeneficiary" value="${geSaleProduct.isSupportBeneficiary }">
<input type="hidden" id="minPInsuredNum" name="minPInsuredNum" value="${geSaleProduct.minPInsuredNum }">
<input type="hidden" id="minBeneficiaryNum" name="minBeneficiaryNum" value="${geSaleProduct.minBeneficiaryNum }">
<input type="hidden" id="maxBeneficiaryNum" name="maxBeneficiaryNum" value="${geSaleProduct.maxBeneficiaryNum }">
<input type="hidden" id="minInsuredNum" name="minInsuredNum" value="${geSaleProduct.minInsuredNum }">
<input type="hidden" id="maxInsuredNum" name="maxInsuredNum" value="${geSaleProduct.maxInsuredNum }">
<input type="hidden" id="isSupportPIns" name="isSupportPIns" value="${geSaleProduct.isSupportPIns }">

<input type="hidden" id="appIdType_opts" name="appIdType_opts" value="${appIdType_opts }">
<input type="hidden" id="insRelationToApp_opts" name="insRelationToApp_opts" value="${insRelationToApp_opts }">
<input type="hidden" id="insIdType_opts" name="insIdType_opts" value="${insIdType_opts }">
<input type="hidden" id="benIdType_opts" name="benIdType_opts" value="${benIdType_opts }">
<input type="hidden" id="benRelaToInss_opts" name="benRelaToInss_opts" value="${benRelaToInss_opts }">

<input type="hidden" id="topInsuredNum" name="topInsuredNum" value="${topInsuredNum }">

<input type="hidden" id="effectDateT" name="effectDateT" value="${geSaleProduct.effectDateType}">
<input type="hidden" id="inceptionDate" name="inceptionDate" value="${inceptionDate }">

<input type="hidden" id="userId" name="userId" value="${geUserPersonal.userID }">
<input type="hidden" id="geInformBookNum" name="geInformBookNum" value="${geInformBookNum }">

<input type="hidden" id="quoteNo" name="quoteNo" value="${quoteNo }">
<input type="hidden" id="proposalSID" name="proposalSID" value="${proposalSID }">

<input type="hidden" id="appAgeDesc" name="appAgeDesc" value="${appAgeDesc }">
<input type="hidden" id="isLimitAppAge" name="isLimitAppAge" value="${isLimitAppAge }">
<input type="hidden" id="appMinBirthday" name="appMinBirthday" value="${appMinBirthday }">
<input type="hidden" id="appMaxBirthday" name="appMaxBirthday" value="${appMaxBirthday }">

<input type="hidden" id="insuredAgeDesc" name="insuredAgeDesc" value="${insuredAgeDesc }">
<input type="hidden" id="isLimitInsuredAge" name="isLimitInsuredAge" value="${isLimitInsuredAge }">
<input type="hidden" id="insuredMinBirthday" name="insuredMinBirthday" value="${insuredMinBirthday }">
<input type="hidden" id="insuredMaxBirthday" name="insuredMaxBirthday" value="${insuredMaxBirthday }">

<input type="hidden" id="isMobileEx" name="isMobileEx" value="1">
<input type="hidden" id="isMobileExDesc" name="isMobileExDesc" value="">
<input type="hidden" id="isCardIDEx" name="isCardIDEx" value="1">
<input type="hidden" id="isCardIDExDesc" name="isCardIDExDesc" value="">
<input type="hidden" id="checkAppAgeByQuoteDesc" name="checkAppAgeByQuoteDesc" value="">

<input type="hidden" id="isBind" name="isBind" value="${isBind }">

<div style="display: none" id="productRisks">${productRisks}</div>

