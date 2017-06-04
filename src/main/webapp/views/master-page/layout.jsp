<%--
 * layout.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon" href="favicon.ico" />

<!-- Bootstrap -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.12.1.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="lib/bootstrap/js/bootstrap.min.js"></script>

<!-- Material Design fonts -->
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/icon?family=Material+Icons">

<!-- Bootstrap Material Design -->
<link rel="stylesheet" type="text/css"
	href="lib/material/css/bootstrap-material-design.css">
<link rel="stylesheet" type="text/css"
	href="lib/material/css/ripples.min.css">

<!-- Material Design for Bootstrap -->
<script src="lib/material/js/material.js"></script>
<script src="lib/material/js/ripples.min.js"></script>

<!-- FancyBox -->
<link rel="stylesheet"
	href="lib/fancybox/source/jquery.fancybox.css?v=2.1.5" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="lib/fancybox/source/jquery.fancybox.pack.js?v=2.1.5"></script>

<!-- Optionally add helpers - button, thumbnail and/or media -->
<link rel="stylesheet"
	href="lib/fancybox/source/helpers/jquery.fancybox-buttons.css?v=1.0.5"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="lib/fancybox/source/helpers/jquery.fancybox-buttons.js?v=1.0.5"></script>
<script type="text/javascript"
	src="lib/fancybox/source/helpers/jquery.fancybox-media.js?v=1.0.6"></script>
<link rel="stylesheet"
	href="lib/fancybox/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="lib/fancybox/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>

<!-- jQuery UI -->
<link rel="stylesheet" href="lib/jquery-ui/jquery-ui.css"
	type="text/css" />
<script type="text/javascript" src="lib/jquery-ui/jquery-ui.js"></script>

<!-- Foggy -->
<script type="text/javascript" src="lib/foggy/jquery.foggy.min.js"></script>

<!-- CookieBar -->
<link rel="stylesheet" href="lib/jquery-cookieBar/jquery.cookiebar.css"
	type="text/css" />
<script type="text/javascript"
	src="lib/jquery-cookieBar/jquery.cookiebar.js"></script>

<script>
	$(document).ready(function() {
		$.cookieBar({
			message : '<spring:message code="master.page.cookie" />',
			acceptText : '<spring:message code="master.page.acceptcookie" />'
		});
	});
</script>

<!-- PaperCollapse -->
<link rel="stylesheet" href="lib/paper-collapse/paper-collapse.css"
	type="text/css" />
<script type="text/javascript"
	src="lib/paper-collapse/paper-collapse.js"></script>

<!-- Retoques -->
<link rel="stylesheet" type="text/css" href="css/style.css">

<script>
	$(function() {
		$.material.init();
	});
</script>

<script type="text/javascript">
	function relativeRedir(loc) {
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
			if (b[0].href.substr(b[0].href.length - 1) == '/'
					&& loc.charAt(0) == '/')
				loc = loc.substr(1);
			loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}

	function hiddenOrShow() {
		var elemento = document.getElementById("alerta");
		if (elemento.style.display == "none") {
			elemento.style.display = "block";
		} else {
			elemento.style.display = "none";
		}
	}
</script>

<title><tiles:insertAttribute name="title" ignore="true" /></title>

</head>

<body>

	<div class="container">

		<div class="page-header" id="banner">
			<div class="row">
				<div class="col-sm-3">
					<a href="#"><img width="100%" src="images/logo.png"
						alt="Acme - Six Pack" /></a>
				</div>
				<div class="col-sm-9 text-right">
					<h1>
						<a style="text-decoration: none;" href="#">Acme - Six Pack</a>
					</h1>
					<h2>
						<tiles:insertAttribute name="title" />
					</h2>
				</div>
			</div>
		</div>


		<tiles:insertAttribute name="header" />


		<div class="bs-docs-section clearfix">
			<div class="row row-centered">
				<tiles:insertAttribute name="body" />
				<jstl:if test="${message != null}">
					<div class="alert alert-dismissible alert-danger">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<spring:message code='${message}' />
					</div>
				</jstl:if>
			</div>
		</div>
	</div>

	<footer>
		<div class="container">
			<div id="footer-content" class="pull-left">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</footer>

</body>
</html>