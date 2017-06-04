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

<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>

<display:table name="services" id="rowServiceGym"
	requestURI="${requestURI}" pagesize="5"
	class="table table-striped table-hover">

	<acme:displayColumnMD code="service.name" var="name" />
	<acme:displayColumnMD code="service.description" var="description" />

	<spring:message code="service.picture" var="pictures" />
	<display:column title="${pictures}">

		<jstl:forEach items="${rowServiceGym.pictures}" var="i">
			<a class="fancybox" href="${i}" rel="gallery${rowServiceGym_rowNum}"><img class="mini-circle"
				src="${i}" /></a>
		</jstl:forEach>

	</display:column>

</display:table>