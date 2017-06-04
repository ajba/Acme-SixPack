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

<security:authorize access="hasRole('ADMIN')">



	<div class="col-md-6 col-centered">
		<div class="well bs-component">
			<form:form action="gym/administrator/edit.do" modelAttribute="gym"
				class="form-horizontal">
				<fieldset>
					<legend>
						<spring:message code="feePayment.payNewFeE" />
					</legend>

					<form:hidden path="id" />
					<form:hidden path="version" />
					
					<acme:inputTextMD code="gym.name" path="name" />
					<acme:inputTextMD code="gym.description" path="description" />
					<acme:inputTextMD code="gym.address" path="address" />
					<acme:inputTextMD code="gym.phone" path="phone" />
					<acme:inputTextMD code="gym.fee" path="fee" />
					<acme:inputTextMD code="gym.picture" path="picture" />
					
					
					<acme:manageButtonsMD delete="${gym.id!=0}" route="gym/administrator" />
					
				</fieldset>
			</form:form>
		</div>
	</div>

</security:authorize>