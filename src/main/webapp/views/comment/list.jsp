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




<display:table name="comments" id="rowCommentService"
	requestURI="${requestURI}" pagesize="5"
	class="table table-striped table-hover">

	<acme:displayColumnMD var="actor.name" code="comment.actor" />
	<acme:displayColumnMD var="placementMoment" code="comment.date" />
	<acme:displayColumnMD var="rank" code="comment.rank" />
	<acme:displayColumnMD var="text" code="comment.text" />

	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<form action="comment/edit.do" method="post">
				<input type="hidden" value="delete" name="delete">

				<jstl:if test="${service != null}">
					<input type="hidden" value="${service.id}" name="service_id">
				</jstl:if>
				<jstl:if test="${gym != null}">
					<input type="hidden" value="${gym.id}" name="gym_id">
				</jstl:if>
				<input type="hidden" value="${rowCommentService.id}"
					name="comment_id">



				<button
					style="background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden; outline: none;">
					<i class="material-icons">delete</i>
				</button>

			</form>

		</display:column>
	</security:authorize>




</display:table>



