<%--
 * submit.tag
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty"%>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%-- Attributes --%>

<%@ attribute name="code" required="true"%>
<%@ attribute name="var" required="true"%>
<%@ attribute name="address" required="false"%>

<%-- Definition --%>
<jstl:if test="${not empty address}">
	<spring:message code="${code}" var="content" />
	<display:column title="${content}" sortable="true">
		<a target="_blank" href="https://www.google.es/maps/place/${address}"><jstl:out value="${address}" /></a> 
	</display:column>
</jstl:if>

<jstl:if test="${empty address}">
	<spring:message code="${code}" var="content" />
	<display:column property="${var}" title="${content}" sortable="true" />
</jstl:if>