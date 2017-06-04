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

<%-- Attributes --%>

<%@ attribute name="delete" required="true"%>
<%@ attribute name="route" required="true"%>




<%-- Definition --%>

<div class="form-group">
	<div class="col-md-12">
	
		<button type="submit" name="save"
			class="btn btn-raised btn-primary">
			<spring:message code="button.accept" />
		</button>
		
			<input type="button" name="cancel" class="btn btn-raised btn-primary"
				value="<spring:message code="button.cancel" />"
				onclick="javascript:relativeRedir('${route}/list.do');" />
		
		<jstl:if test="${delete}">
			<input type="button" name="delete" class="btn btn-raised btn-primary"
				value="<spring:message code="button.delete" />"
				onclick="hiddenOrShow()" />&nbsp;	
		</jstl:if>
	</div>
</div>