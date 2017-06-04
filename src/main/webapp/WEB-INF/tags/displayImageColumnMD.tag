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
<%@ attribute name="picture" required="false"%>

<%-- Definition --%>
<spring:message code="${code}" var="content" />
<display:column title="${content}">
	<jstl:choose>
		<jstl:when test="${picture == ''}">
			<a class="fancybox"
				href="http://c1.staticflickr.com/7/6100/6222732938_8e6694729d.jpg"><img
				class="mini-circle"
				src="http://c1.staticflickr.com/7/6100/6222732938_8e6694729d.jpg" /></a>
		</jstl:when>
		<jstl:otherwise>
			<a class="fancybox" href="${picture}"><img class="mini-circle"
				src="${picture}" /></a>
		</jstl:otherwise>
	</jstl:choose>
</display:column>