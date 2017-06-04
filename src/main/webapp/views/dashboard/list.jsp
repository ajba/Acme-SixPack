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

<security:authorize access="hasRole('ADMIN')">

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.mostPopularGym" />
			</h4>
		</div>
		<div class="body">
			<display:table name="mpg" id="dashboard1" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="name" code="gym.name" />
				<acme:displayColumnMD var="address" code="gym.address"
					address="${dashboard1.address}" />
				<acme:displayColumnMD var="description" code="gym.description" />
				<acme:displayColumnMD var="phone" code="gym.phone" />
				<acme:displayColumnMD var="fee" code="gym.fee" />
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.lessPopularGym" />
			</h4>
		</div>
		<div class="body">
			<display:table name="lpg" id="dashboard2" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="name" code="gym.name" />
				<acme:displayColumnMD var="address" code="gym.address"
					address="${dashboard2.address}" />
				<acme:displayColumnMD var="description" code="gym.description" />
				<acme:displayColumnMD var="phone" code="gym.phone" />
				<acme:displayColumnMD var="fee" code="gym.fee" />
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.mostPopularService" />
			</h4>
		</div>
		<div class="body">
			<display:table name="mps" id="dashboard3" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="name" code="service.name" />
				<acme:displayColumnMD var="description" code="service.description" />

				<spring:message code="service.picture" var="pictures" />
				<display:column title="${pictures}">

					<jstl:forEach items="${dashboard3.pictures}" var="i">
						<a class="fancybox" href="${i}"
							rel="dashboard3${dashboard3_rowNum}"><img class="mini-circle"
							src="${i}" /></a>
					</jstl:forEach>

				</display:column>
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.lessPopularService" />
			</h4>
		</div>
		<div class="body">
			<display:table name="lps" id="dashboard4" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="name" code="service.name" />
				<acme:displayColumnMD var="description" code="service.description" />

				<spring:message code="service.picture" var="pictures" />
				<display:column title="${pictures}">

					<jstl:forEach items="${dashboard4.pictures}" var="i">
						<a class="fancybox" href="${i}"
							rel="dashboard4${dashboard4_rowNum}"><img class="mini-circle"
							src="${i}" /></a>
					</jstl:forEach>

				</display:column>
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.morefee" />
			</h4>
		</div>
		<div class="body">
			<display:table name="pmf" id="dashboard5" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="surname" code="customer.surname" />
				<acme:displayColumnMD var="name" code="customer.name" />
				<acme:displayColumnMD var="phone" code="customer.phone" />
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.lessfee" />
			</h4>
		</div>
		<div class="body">
			<display:table name="plf" id="dashboard6" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="surname" code="customer.surname" />
				<acme:displayColumnMD var="name" code="customer.name" />
				<acme:displayColumnMD var="phone" code="customer.phone" />
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.morespam" />
			</h4>
		</div>
		<div class="body">
			<display:table name="ams" id="dashboard7" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="surname" code="customer.surname" />
				<acme:displayColumnMD var="name" code="customer.name" />
				<acme:displayColumnMD var="phone" code="customer.phone" />
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.averagemessages" />
			</h4>
		</div>
		<div class="body">
			<p>
				<jstl:out value="${averageMessages}" />
			</p>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.gymHaveMoreComments" />
			</h4>
		</div>
		<div class="body">
			<display:table name="ghmc" id="dashboard8" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="name" code="gym.name" />
				<acme:displayColumnMD var="address" code="gym.address"
					address="${dashboard8.address}" />
				<acme:displayColumnMD var="description" code="gym.description" />
				<acme:displayColumnMD var="phone" code="gym.phone" />
				<acme:displayColumnMD var="fee" code="gym.fee" />
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.serviceHaveMoreComments" />
			</h4>
		</div>
		<div class="body">
			<display:table name="shmc" id="dashboard9" requestURI="${requestURI}"
				pagesize="5" class="table table-striped table-hover">
				<acme:displayColumnMD var="name" code="service.name" />
				<acme:displayColumnMD var="description" code="service.description" />

				<spring:message code="service.picture" var="pictures" />
				<display:column title="${pictures}">

					<jstl:forEach items="${dashboard9.pictures}" var="i">
						<a class="fancybox" href="${i}"
							rel="dashboard9${dashboard9_rowNum}"><img class="mini-circle"
							src="${i}" /></a>
					</jstl:forEach>

				</display:column>
			</display:table>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.averagecomments" />
			</h4>
		</div>
		<div class="body">
			<p>
				<spring:message code="dashboard.average" />
				:
				<jstl:out value="${averageComments}" />
			</p>
			<p>
				<spring:message code="dashboard.deviation" />
				:
				<jstl:out value="${deviation}" />
			</p>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.averagecommentspergym" />
			</h4>
		</div>
		<div class="body">
			<p>
				<jstl:out value="${averageCommentsPerGym}" />
			</p>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.averagecommentsperservice" />
			</h4>
		</div>
		<div class="body">
			<p>
				<jstl:out value="${averageCommentsPerService}" />
			</p>
		</div>
	</div>

	<div class="collapse-card">
		<div class="title">
			<h4>
				<spring:message code="dashboard.customerRemoveComments" />
			</h4>
		</div>
		<div class="body">
			<display:table name="crmc" id="dashboard10"
				requestURI="${requestURI}" pagesize="5"
				class="table table-striped table-hover">
				<acme:displayColumnMD var="surname" code="customer.surname" />
				<acme:displayColumnMD var="name" code="customer.name" />
				<acme:displayColumnMD var="phone" code="customer.phone" />
			</display:table>
		</div>
	</div>
</security:authorize>