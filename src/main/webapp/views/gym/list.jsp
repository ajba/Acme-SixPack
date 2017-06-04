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

<display:table name="gyms" id="rowGyms" requestURI="${requestURI}"
	pagesize="5" class="table table-striped table-hover">

	<acme:displayImageColumnMD code="gym.picture" var="picture"
		picture="${rowGyms.picture}" />
	<acme:displayColumnMD code="gym.name" var="name" />
	<acme:displayColumnMD code="gym.description" var="description" />
	<acme:displayColumnMD code="gym.address" var="address" address="${rowGyms.address}"/>
	<acme:displayColumnMD code="gym.phone" var="phone" />
	<acme:displayColumnMD code="gym.fee" var="fee" />

	<spring:message code="gym.customerBooked" var="customerBooked" />
	<display:column title="${customerBooked}" sortable="true">
		<jstl:out value="${customerHaveBooked.get(rowGyms_rowNum - 1)}" />
	</display:column>
	
	<spring:message code="gym.services" var="services" />
	<display:column title="${services}">
		<acme:buttonMD code="gym.services" href="serviceGym/list.do?gymId=${rowGyms.id}" type="info"/>
	</display:column>
	
	<security:authorize access="hasRole('CUSTOMER') || hasRole('ADMIN')">
	
	<spring:message code="gym.message.new" var="newMessage"></spring:message>
	<display:column title="${newMessage}">
		
	
	<form action="comment/create.do" method="post">
		<input type="hidden" value="${rowGyms.id}" name="id" id="id">
		<input type="hidden" value="gym" name="obj" id="obj">
		<button style=" background-color: Transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;outline:none;">
		<i class="material-icons">comment</i>
		</button>
	
	</form>
	
	</display:column>
	</security:authorize>
	
	<spring:message code="gym.message.view" var="viewMessage"></spring:message>
	<display:column title="${viewMessage}">
		
	
	<form action="comment/list.do" method="post">
		<input type="hidden" value="${rowGyms.id}" name="id" id="id">
		<input type="hidden" value="gym" name="obj" id="obj">
		<button style=" background-color: Transparent;background-repeat:no-repeat;border: none;cursor:pointer;overflow: hidden;outline:none;">
			<i class="material-icons">mode_comment</i>
		</button>
	
	</form>
	
	</display:column>

</display:table>