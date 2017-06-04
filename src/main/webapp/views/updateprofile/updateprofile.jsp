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


<jstl:if test="${show}">
	<div class="alert alert-dismissible alert-success">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<spring:message code="welcome.message.updated" />
		<p>
			<acme:buttonMD href="#" type="primary btn-raised"
				code="register.backtoindex" />
		</p>
	</div>
</jstl:if>

<jstl:if test="${!isAdmin && isCustomer}">
	<jstl:if test="${empty show || !show}">
		<div class="col-md-6 col-centered">
			<div class="well bs-component">
				<form:form action="profile/edit/customer.do"
					modelAttribute="customerForm" class="form-horizontal" name="f1"
					onsubmit="check();">
					<fieldset>
						<legend>
							<spring:message code="customer.submit" />
						</legend>

						<form:hidden path="id" />

						<acme:inputTextMD code="customer.name" path="name" />
						<acme:inputTextMD code="customer.surname" path="surname" />
						<input type="hidden" id="isActived" name="isActived">

						<div class="creditCard" style="background-color: #F2F2F2;">
							<fieldset>
								<legend>
									<spring:message code="consumer.creditCardData" />
								</legend>
								<acme:inputTextMD code="consumer.creditCard.holderName"
									path="creditCard.holderName" id="holderName" />
								<acme:inputTextMD code="consumer.creditCard.brand"
									path="creditCard.brand" id="brand" />
								<acme:inputTextMD code="consumer.creditCard.numero"
									path="creditCard.number" id="number" />
								<acme:inputTextMD code="consumer.creditCard.CVV"
									path="creditCard.CVV" id="cvv" />
								<acme:inputTextMD code="consumer.creditCard.expirationMonth"
									path="creditCard.expirationMonth" id="expirationMonth" />
								<acme:inputTextMD code="consumer.creditCard.expirationYear"
									path="creditCard.expirationYear" id="expirationYear" />
							</fieldset>
						</div>

						<acme:inputTextMD code="customer.userName" readonly="true"
							path="username" />

						<acme:inputPassMD code="customer.userPass" path="password" />

						<acme:inputPassMD code="customer.repeatPass" path="repeatPassword" />
						<jstl:if test="${passwordError}">
							<p class="error">
								<spring:message code="customer.errorPassword" />
							</p>
						</jstl:if>

						<acme:inputTextMD code="customer.phone" path="phone"
							placeholder="6XX XXX XXX || 7XX XXX XXX" />

						<form:checkbox path="acceptTerms" />
						<form:label path="acceptTerms">
							<spring:message code="register.acceptTerms" />
							<a target="_blank" href="terms/show.do"><spring:message
									code="register.acceptTerms.link" /></a>
						</form:label>
						<jstl:if test="${termsError}">
							<p class="error">
								<spring:message code="customer.errorTerms" />
							</p>
						</jstl:if>

						<acme:submitMD name="save" code="customer.save"
							withButtonCancel="true" codeButtonCancel="customer.cancel" />
					</fieldset>
				</form:form>
			</div>
		</div>
	</jstl:if>
</jstl:if>

<jstl:if test="${isAdmin && !isCustomer}">
	<jstl:if test="${empty show || !show}">
		<div class="col-md-6 col-centered">
			<div class="well bs-component">
				<form:form action="profile//edit/administrator.do"
					modelAttribute="customerForm" class="form-horizontal" name="f1"
					onsubmit="check();">
					<fieldset>
						<legend>
							<spring:message code="customer.submit" />
						</legend>

						<form:hidden path="id" />

						<acme:inputTextMD code="customer.name" path="name" />
						<acme:inputTextMD code="customer.surname" path="surname" />
						<input type="hidden" id="isActived" name="isActived">

						<acme:inputTextMD code="customer.userName" readonly="true"
							path="username" />

						<acme:inputPassMD code="customer.userPass" path="password" />

						<acme:inputPassMD code="customer.repeatPass" path="repeatPassword" />

						<jstl:if test="${passwordError}">
							<p class="error">
								<spring:message code="customer.errorPassword" />
							</p>
						</jstl:if>

						<acme:inputTextMD code="customer.phone" path="phone"
							placeholder="6XX XXX XXX || 7XX XXX XXX" />

						<form:checkbox path="acceptTerms" />
						<form:label path="acceptTerms">
							<spring:message code="register.acceptTerms" />
							<a target="_blank" href="terms/show.do"><spring:message
									code="register.acceptTerms.link" /></a>
						</form:label>
						<jstl:if test="${termsError}">
							<p class="error">
								<spring:message code="customer.errorTerms" />
							</p>
						</jstl:if>

						<acme:submitMD name="save" code="customer.save"
							withButtonCancel="true" codeButtonCancel="customer.cancel" />
					</fieldset>
				</form:form>
			</div>
		</div>
	</jstl:if>
</jstl:if>