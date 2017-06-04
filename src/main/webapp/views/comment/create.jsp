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




<security:authorize access="hasRole('CUSTOMER') || hasRole('ADMIN')">

<div class="col-md-6 col-centered">
		<div class="well bs-component">
			<form:form action="comment/edit.do" modelAttribute="comment" class="form-horizontal">
				<fieldset>
					<legend>
					<jstl:if test="${nuevo}">
						<jstl:if test="${service != null}">
							<spring:message code="comment.newCommentService" /><jstl:out value=" ${service.name}"></jstl:out>
						</jstl:if>
						<jstl:if test="${gym != null}">
							<spring:message code="comment.newCommentGym" /><jstl:out value=" ${gym.name}"></jstl:out>
						</jstl:if>
					</jstl:if>
					
						
					</legend>

					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="actor" />
					
					<jstl:if test="${service != nyll}">
						<input type="hidden" name="service_id" value="${service.id}">
					</jstl:if>
					
					<jstl:if test="${gym != nyll}">
						<input type="hidden" name="gym_id" value="${gym.id}">
					</jstl:if>
					
					
					<acme:textareaMD code="comment.text" path="text"/>
					<acme:inputTextMD code="comment.rank" path="rank" />
					<acme:submitMD name="save" code="comment.submit"
							withButtonCancel="true" codeButtonCancel="comment.cancel" />
					
				</fieldset>
			</form:form>
		</div>
	</div>
</security:authorize>



