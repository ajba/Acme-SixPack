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

	<jstl:if test="${!noAvailableGyms}">
		<div class="col-md-6 col-centered">
			<div class="well bs-component">
				<form:form action="book/customer/edit.do" modelAttribute="book"
					class="form-horizontal">
					<fieldset>
						<legend>
							<spring:message code="book.bookNewService" />
						</legend>

						<form:hidden path="id" />
						<form:hidden path="version" />
						<form:hidden path="placementMoment" />
						<form:hidden path="approved" />
						<form:hidden path="customer" />

						<jstl:if test="${!correct}">
							<div class="alert alert-dismissible alert-info">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<spring:message code="book.noservicegym" />
							</div>
						</jstl:if>

						<acme:inputTextMD code="book.activateDate" path="activateDate" placeholder="dd/MM/yyyy HH:mm"/>

						<acme:inputTextMD code="book.duration" path="duration" />

						<acme:inputSelectMD items="${gyms}" itemLabel="name"
							code="book.gym.name" path="gym" />

						<acme:inputSelectMD items="${services}" itemLabel="name"
							code="book.service" path="service" />

						<acme:submitMD name="save" code="book.bookNow"
							withButtonCancel="true" codeButtonCancel="customer.cancel" />
					</fieldset>
				</form:form>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${noAvailableGyms}">
		<div class="alert alert-dismissible alert-warning">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<spring:message code="book.nofeegym" />
		</div>
	</jstl:if>
</security:authorize>