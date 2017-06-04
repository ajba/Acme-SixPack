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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%-- Attributes --%>

<%@ attribute name="code" required="true"%>
<%@ attribute name="path" required="true"%>
<%@ attribute name="itemLabel" required="true"%>
<%@ attribute name="items" required="true" type="java.util.Collection"%>


<%-- Definition --%>


<div class="form-group">

	<form:label path="${path}" class="col-md-2 control-label">
		<spring:message code="${code}" />:</form:label>
	<div class="col-md-10">
		<form:select path="${path}" class="form-control">
			<form:options itemLabel="${itemLabel}" itemValue="id"
				items="${items}"></form:options>
		</form:select>
	</div>
	<form:errors cssClass="error" path="${path}" />

</div>


