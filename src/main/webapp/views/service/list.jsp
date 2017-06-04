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

<display:table name="services" id="rowService"
	requestURI="${requestURI}" pagesize="5"
	class="table table-striped table-hover">

	<acme:displayColumnMD code="service.name" var="name" />
	<acme:displayColumnMD code="service.description" var="description" />

	<spring:message code="service.picture" var="pictures" />
	<display:column title="${pictures}">

		<jstl:forEach items="${rowService.pictures}" var="i">
			<a class="fancybox" href="${i}" rel="gallery${rowService_rowNum}"><img
				class="mini-circle" src="${i}" /></a>
		</jstl:forEach>

	</display:column>

	<spring:message code="service.watch" var="watch"></spring:message>
	<display:column title="${watch}">
		<a href="service/watchOfferService.do?idGym=${rowService.id}"><img
			src="images/watch.png" style="height: 30px; width: 30px;"
			alt=<spring:message code="service.watch"></spring:message>></a>
	</display:column>



	<spring:message code="service.tc" var="tc"></spring:message>
	<display:column title="${tc}">
		<jstl:out value="${totalCustomer[count]}"></jstl:out>



	</display:column>

	<security:authorize access="hasRole('CUSTOMER') || hasRole('ADMIN')">

		<spring:message code="service.message.new" var="newMessage"></spring:message>
		<display:column title="${newMessage}">


			<form action="comment/create.do" method="post">
				<input type="hidden" value="${rowService.id}" name="id" id="id">
				<input type="hidden" value="service" name="obj" id="obj">
				<!--  button
					style="background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden; outline: none;"
					value="Crear">
					<i class="material-icons">comment</i>
				</button>-->

			</form>

			<form:form action="comment/create.do" modelAttribute="commentform"
				method="post">
				
				<input type="hidden" value="${rowService.id}" name="id" id="id">
				<input type="hidden" value="service" name="obj" id="obj">
			<button
					style="background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden; outline: none;" value="Crear">
					<i class="material-icons">comment</i>
				</button>


			</form:form>

		</display:column>
	</security:authorize>


	<spring:message code="service.message.view" var="viewMessage"></spring:message>
	<display:column title="${viewMessage}">


		<form action="comment/list.do" method="post">
			<input type="hidden" value="${rowService.id}" name="id" id="id">
			<input type="hidden" value="service" name="obj" id="obj">
			<button
				style="background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden; outline: none;">
				<i class="material-icons">mode_comment</i>
			</button>

		</form>


	</display:column>



	<jstl:set var="count" value="${count + 1}" />



</display:table>




