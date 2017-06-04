<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('CUSTOMER')">

	<jstl:if test="${expired}">
		<div class="alert alert-dismissible alert-danger">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<spring:message code="feePayment.creditcard.expired" />
		</div>
	</jstl:if>

	<div class="col-md-6 col-centered">
		<div class="well bs-component">
			<form:form action="feePayment/customer/edit.do"
				modelAttribute="feePayment" class="form-horizontal">
				<fieldset>
					<legend>
						<spring:message code="feePayment.payNewFeE" />
					</legend>

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="placementMoment" />
					<form:hidden path="inactiveDate" />
					<form:hidden path="amount" />
					
					<jstl:if test="${!expired && !requiredCreditCard}">
						<form:hidden path="creditCard.holderName" />
						<form:hidden path="creditCard.brand" />
						<form:hidden path="creditCard.number" />
						<form:hidden path="creditCard.CVV" />
						<form:hidden path="creditCard.expirationMonth" />
						<form:hidden path="creditCard.expirationYear" />
					</jstl:if>

					<acme:inputTextMD code="feePayment.activeMoment" path="activeDate" placeholder="dd/MM/yyyy"/>

					<acme:inputSelectMD items="${gyms}" itemLabel="name"
						code="feePayment.gym" path="gym" />

					<jstl:if test="${expired || requiredCreditCard}">
						<hr>
						<h3>
							<spring:message code="feePayment.creditcard" />
						</h3>

						<acme:inputTextMD code="feePayment.creditcard.holder"
							path="creditCard.holderName" />
						<acme:inputTextMD code="feePayment.creditcard.brand"
							path="creditCard.brand" />
						<acme:inputTextMD code="feePayment.creditcard.number"
							path="creditCard.number" />
						<acme:inputTextMD code="feePayment.creditcard.cvv"
							path="creditCard.CVV" />
						<acme:inputTextMD code="feePayment.creditcard.month"
							path="creditCard.expirationMonth" />
						<acme:inputTextMD code="feePayment.creditcard.year"
							path="creditCard.expirationYear" />

					</jstl:if>


					<acme:submitMD name="save" code="feePayment.pay"
						withButtonCancel="true" codeButtonCancel="customer.cancel" />
				</fieldset>
			</form:form>
		</div>
	</div>

</security:authorize>