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
<table class="table table-striped table-hover" >

<tr>

<spring:message code="search.name" var="name"></spring:message>
<spring:message code="search.description" var="description"></spring:message>
<spring:message code="search.picture" var="picture"></spring:message>
<spring:message code="search.gym" var="gym"></spring:message>
<spring:message code="search.nothing" var="nothing"></spring:message>


<spring:message code="search.word" var="word"></spring:message>
<jstl:if test="${mostrar}">
	<div class="alert alert-dismissible alert-success">
  		<strong>${word}</strong> ${wordSearch}
	</div>
	<br/>
	<br/>
</jstl:if>
	<jstl:if test="${mostrar}">
		<th style="text-align: center;">${name}</th>
		<th style="text-align: center;">${description}</th>
		<th style="text-align: center;">${picture}</th>
		<th style="text-align: center;">${gym}</th>
	</jstl:if>
	<jstl:if test="${!mostrar}">
		<div class="alert alert-dismissible alert-danger">
  			<strong>${nothing}</strong>
		</div>
	</jstl:if>
	</tr>
<jstl:forEach items="${search}" var="entry">
	<tr>
	<td>${entry.key.name}</td>
	<td>${entry.key.description}</td>
	<td><jstl:forEach items="${entry.key.pictures}" var="picture">
		<img class="mini-circle" src="${picture}" />
		</jstl:forEach>
	</td>
	<td>
    <jstl:forEach items="${entry.value}" var="g">
	   <jstl:out value="||"></jstl:out> ${g.name}
    </jstl:forEach>
    </td>
    </tr>
</jstl:forEach>
</table>	



