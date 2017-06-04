<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMIN')">

	<script>
		function addurl() {
			$('.linkaddword').attr(
					"href",
					"spamWords/administrator/addWord.do?word="
							+ $("#focusedInput1").val());
		}
	</script>
	<div class="row">
		<h3>
			<spring:message code="keyWords.list" />
		</h3>
		<jstl:if test="${fn:length(spamWords) == 0}">
			<p>
				<span class="label label-primary"><spring:message
						code="keyWords.listempty" /></span>
			</p>
		</jstl:if>

		<div class="col-md-4"></div>

		<div class="col-md-4">
			<div class="col-md-6 text-left">
				<jstl:set var="aux" value="0"></jstl:set>
				<jstl:forEach items="${spamWords}" var="sw">
					<p>
						<a style="text-decoration: none;"> <i class="material-icons">label_outline</i>
							<jstl:out value="${sw}" />
						</a>
					</p>
				</jstl:forEach>
			</div>
			<div class="col-md-6 text-right">
				<jstl:set var="aux" value="0"></jstl:set>
				<jstl:forEach items="${spamWords}" var="sw">

					<p>
						<a href="spamWords/administrator/deleteWord.do?id=${aux}"><i
							class="material-icons">delete</i></a>
					</p>

					<jstl:set var="aux" value="${aux+1}"></jstl:set>
				</jstl:forEach>
			</div>
		</div>
		<div class="col-md-4"></div>
	</div>

	<div class="row">
		<div class="col-md-4 col-centered">
			<div class="form-group has-success">
				<label class="control-label" for="inputSuccess"><spring:message
						code="keyWords.addNewWord"></spring:message></label> <input
					oninput="addurl()" class="form-control" id="focusedInput1"
					type="text">
			</div>
			<acme:buttonMD href="spamWords/administrator/addWord.do?word="
				type="primary linkaddword" code="keyWords.add" />
		</div>
	</div>
</security:authorize>