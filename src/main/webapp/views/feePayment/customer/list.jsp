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

<security:authorize access="hasRole('CUSTOMER')">

	<display:table name="feePayments" id="rowFeePayments"
		requestURI="${requestURI}" pagesize="5"
		class="table table-striped table-hover">

		<acme:displayColumnMD code="feePayment.gym" var="gym.name" />
		<acme:displayColumnMD code="feePayment.amount" var="amount" />
		<acme:displayColumnMD code="feePayment.placementMoment"
			var="placementMoment" />
		<acme:displayColumnMD code="feePayment.activeMoment" var="activeDate" />
		<acme:displayColumnMD code="feePayment.inactiveMoment"
			var="inactiveDate" />
		<acme:displayColumnMD code="feePayment.ccnumber"
			var="creditCard.number" />

	</display:table>

</security:authorize>