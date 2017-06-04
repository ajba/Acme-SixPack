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

	<script>
	 $(function() {
	              
	        $(".datepicker").datepicker({ dateFormat: 'yy-mm-dd'  });  
	    });
	 
		function editar(id){
			document.getElementById(id).disabled = false
			 var date = $("#"+id).datepicker('getDate');
			  $("#"+id).datepicker("option", "minDate", date);
			  $("#"+id).datepicker("show");
			
			  $("#aceptar"+id).show();
			  $(".calendario").hide();
		}
		
	</script>

	<display:table name="fees" id="rowFee"
		requestURI="${requestURI}" pagesize="5"
		class="table table-striped table-hover text-left">

		<acme:displayColumnMD code="feePayment.gym" var="gym.name" />
		<acme:displayColumnMD code="feePayment.amount" var="amount" />
		<acme:displayColumnMD code="feePayment.placementMoment"
			var="placementMoment" />

		<acme:displayColumnMD code="feePayment.activeMoment" var="activeDate" />




		<spring:message code="feePayment.inactiveMoment" var="inactiveDate" />
		<display:column title="${inactiveDate}" sortable="true"
			style="display">
			<form id="form${rowFee.id}" action="feePayment/administrator/edit.do" >
			<input type="text" name="feeId" value="${rowFee.id}" hidden="hidden">
			<input type="text" name="date" id="${rowFee.id}" class="datepicker"  value="${rowFee.inactiveDate}" disabled="disabled" style="border: none;background-color: transparent; width: 80px;">
			<a onclick="editar(${rowFee.id})" id="calendario${rowFee.id}" style="cursor: pointer;" class="calendario">
			<i class="material-icons">date_range</i>
			</a>
			<a onclick="document.getElementById('form${rowFee.id}').submit();" style="cursor: pointer;"  hidden="hidden" id="aceptar${rowFee.id}">
			<i class="material-icons">send</i>
			</a>
			</form>
		</display:column>

		<acme:displayColumnMD code="feePayment.ccnumber"
			var="creditCard.number" />

	</display:table>
	

</security:authorize>