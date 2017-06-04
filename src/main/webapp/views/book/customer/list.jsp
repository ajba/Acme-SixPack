<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('CUSTOMER')">

	<jstl:if test="${empty books}">
		<div class="alert alert-dismissible alert-info">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<spring:message code="book.noBooksYet" />
		</div>
	</jstl:if>
	
	<jstl:if test="${not empty books}">

		<jstl:if test="${not empty cancelError && cancelError}">
			<div class="alert alert-dismissible alert-danger">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<spring:message code="book.cancelBookingError" />
			</div>
		</jstl:if>

		<display:table name="books" id="rowCustomerBooks"
			requestURI="${requestURI}" pagesize="5"
			class="table table-striped table-hover">
			<acme:displayColumnMD code="book.gym.name" var="gym.name" />

			<spring:message code="book.service" var="service"></spring:message>
			<display:column title="${service}" sortable="true">
				<jstl:out value="${services.get(rowCustomerBooks_rowNum - 1).name}"></jstl:out>
			</display:column>

			<acme:displayColumnMD code="book.placementMoment"
				var="placementMoment" />

			<acme:displayColumnMD code="book.activateDate" var="activateDate" />

			<acme:displayColumnMD code="book.duration" var="duration" />

			<jstl:if test="${rowCustomerBooks.approved}">
				<display:column style="color:#4CAF50">
					<spring:message code="book.approved"></spring:message>
				</display:column>
			</jstl:if>
			<jstl:if
				test="${!rowCustomerBooks.approved && not empty rowCustomerBooks.administrator}">
				<display:column style="color:#FF0000">
					<spring:message code="book.denied"></spring:message>
				</display:column>
			</jstl:if>
			<jstl:if
				test="${!rowCustomerBooks.approved && empty rowCustomerBooks.administrator}">
				<display:column style="color:#004CFF">
					<spring:message code="book.pending"></spring:message>
				</display:column>
			</jstl:if>

			<jstl:choose>
				<jstl:when test="${expired.get(rowCustomerBooks_rowNum - 1)}">
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

					<display:column>
						<jstl:if
							test="${!rowCustomerBooks.approved && empty rowCustomerBooks.administrator}">
							<acme:buttonMD
								href="book/customer/cancelBooking.do?bookId=${rowCustomerBooks.id}"
								type="warning btn-raised" code="book.cancelBooking" />
						</jstl:if>
					</display:column>

				</jstl:otherwise>
			</jstl:choose>
		</display:table>
	</jstl:if>
</security:authorize>