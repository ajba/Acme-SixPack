<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
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


	<display:table name="books" id="rowAdminBooks"
		requestURI="${requestURI}" pagesize="5"
		class="table table-striped table-hover">

		<acme:displayColumnMD code="book.gym.name" var="gym.name" />


		<acme:displayColumnMD code="book.placementMoment"
			var="placementMoment" />

		<acme:displayColumnMD code="book.activateDate" var="activateDate" />

		<acme:displayColumnMD code="book.duration" var="duration" />




		<jstl:choose>
			<jstl:when test="${expired.get(rowAdminBooks_rowNum - 1)}">
				<display:column style="color:#FF0000">
					<spring:message code="book.expired"></spring:message>
				</display:column>
				<display:column>
				</display:column>
			</jstl:when>
			<jstl:otherwise>
				<display:column style="color:#4CAF50">
					<spring:message code="book.notexpired"></spring:message>
				</display:column>
				<jstl:if test="${rowAdminBooks.approved}">
					<display:column style="color:#4CAF50">
						<spring:message code="book.approved"></spring:message>
					</display:column>
				</jstl:if>
				<jstl:if
					test="${!rowAdminBooks.approved && not empty rowAdminBooks.administrator}">
					<display:column style="color:#FF0000">
						<spring:message code="book.denied"></spring:message>
					</display:column>
				</jstl:if>
				<jstl:if
					test="${!rowAdminBooks.approved && empty rowAdminBooks.administrator}">
					<display:column>
						<acme:buttonMD
							href="book/administrator/deny.do?bookId=${rowAdminBooks.id}"
							type="warning btn-raised" code="book.denyBooking" />
						<acme:buttonMD
							href="book/administrator/aprove.do?bookId=${rowAdminBooks.id}"
							type="btn btn-raised btn-success" code="book.aproveBooking" />
					</display:column>
				</jstl:if>



			</jstl:otherwise>
		</jstl:choose>

	</display:table>

</security:authorize>