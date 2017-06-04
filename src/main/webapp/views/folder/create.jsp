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

<form:form action="message/actor/create.do" modelAttribute="mens">

		<form:hidden path="id" />
		<form:hidden path="version" />


		<form:label path="recipient"><spring:message code="message.recipient" />:</form:label>
		<form:select path="recipient">
			<form:options itemLabel="name" items="${actors}"></form:options>
		</form:select>
		<form:errors cssClass="error" path="recipient" />
		<br />
		
		

		


	</form:form>

