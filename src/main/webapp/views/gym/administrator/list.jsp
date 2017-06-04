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

	<script type="text/javascript">
		$(document).ready(function() {
			$(".fancybox").fancybox();
		});
	</script>

	<display:table name="gyms" id="rowGymsAdmin" requestURI="${requestURI}"
		pagesize="5" class="table table-striped table-hover">

		<acme:displayImageColumnMD code="gym.picture" var="picture"
			picture="${rowGymsAdmin.picture}" />
		<acme:displayColumnMD code="gym.name" var="name" />
		<acme:displayColumnMD code="gym.description" var="description" />
		<acme:displayColumnMD code="gym.address" var="address"
			address="${rowGymsAdmin.address}" />
		<acme:displayColumnMD code="gym.phone" var="phone" />


		<spring:message code="gym.customerBooked" var="customerBooked" />
		<display:column title="${customerBooked}" sortable="true">
			<jstl:out value="${customerHaveBooked.get(rowGymsAdmin_rowNum - 1)}" />
		</display:column>

		<spring:message code="gym.services" var="services" />
		<display:column title="${services}">
			<acme:buttonMD code="gym.services"
				href="serviceGym/list.do?gymId=${rowGymsAdmin.id}" type="info" />
		</display:column>

		<display:column>
			<acme:editMD
				href="gym/administrator/edit.do?gymId=${rowGymsAdmin.id}" />
		</display:column>

	</display:table>

</security:authorize>