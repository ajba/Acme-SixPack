
<%--
 * login.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div class="col-md-6 col-centered">
	<div class="well bs-component">
		<form:form action="j_spring_security_check"
			modelAttribute="credentials" cssClass="form-horizontal">
			<fieldset>
				<legend>
					<spring:message code="security.login.title" />
				</legend>

				<div class="form-group">
					<form:label class="col-md-2 control-label" path="username">
						<spring:message code="security.username" />
					</form:label>

					<div class="col-md-10">
						<form:input cssClass="form-control" path="username" />
					</div>

					<form:errors class="error" path="username" />
				</div>

				<div class="form-group">
					<form:label class="col-md-2 control-label" path="password">
						<spring:message code="security.password" />
					</form:label>

					<div class="col-md-10">
						<form:password cssClass="form-control" path="password" />
					</div>

					<form:errors class="error" path="username" />
				</div>

				<div class="form-group">
					<jstl:if test="${showError == true}">
						<div class="error">
							<spring:message code="security.login.failed" />
						</div>
					</jstl:if>
				</div>

				<div class="form-group">
					<div class="col-md-12">
						<button type="submit" class="btn btn-primary">
							<spring:message code="security.login" />
						</button>
						<a href="register/create.do" class="btn btn-primary"><spring:message
								code="security.register" /></a>
					</div>
				</div>

			</fieldset>
		</form:form>
	</div>
</div>