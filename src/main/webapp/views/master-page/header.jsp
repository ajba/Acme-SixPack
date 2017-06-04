<%--
 * header.jsp
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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="bs-docs-section clearfix">
	<div class="row">
		<div class="col-md-12">

			<div class="bs-component">
				<div class="navbar navbar-default">
					<div class="container-fluid">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".navbar-responsive-collapse">
								<span class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">Acme</a>
						</div>
						<div class="navbar-collapse collapse navbar-responsive-collapse">

							<ul class="nav navbar-nav">
								<li class="dropdown"><a href="javascript:void(0)"
									data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
											code="master.page.gym" /> <b class="caret"></b> </a>
									<ul class="dropdown-menu">
										<li><a href="gym/list.do"><spring:message
													code="master.page.listgym" /></a></li>
										<security:authorize access="hasRole('ADMIN')">
											<li class="divider"></li>
											<li><a href="gym/administrator/list.do"><spring:message
														code="master.page.admingym" /></a></li>
											<li><a href="gym/administrator/create.do"><spring:message
														code="master.page.creategym" /></a></li>
										</security:authorize>
									</ul></li>


								<li class="dropdown"><a href="javascript:void(0)"
									data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
											code="master.page.service" /> <b class="caret"></b> </a>
									<ul class="dropdown-menu">
										<li><a href="service/list.do"><spring:message
													code="master.page.listservice" /></a></li>
									</ul></li>

							</ul>

							<security:authorize access="hasRole('ADMIN')">

							</security:authorize>

							<security:authorize
								access="hasRole('CUSTOMER')|| hasRole('ADMIN')">
								<ul class="nav navbar-nav">

									<li class="dropdown"><a href="javascript:void(0)"
										data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
												code="master.page.fees" /> <b class="caret"></b> </a>


										<ul class="dropdown-menu">
											<security:authorize access="hasRole('CUSTOMER')">
												<li><a href="gym/customer/list.do"><spring:message
															code="master.page.activegymfees" /></a></li>
												<li class="divider"></li>
												<li><a href="feePayment/customer/create.do"><spring:message
															code="master.page.payfee" /></a></li>

											</security:authorize>
											<security:authorize access="hasRole('ADMIN')">
												<li><a href="feePayment/administrator/list.do"><spring:message
															code="master.page.managefee" /></a></li>
											</security:authorize>


										</ul></li>

									<li class="dropdown"><a href="javascript:void(0)"
										data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
												code="master.page.books" /> <b class="caret"></b> </a>
										<ul class="dropdown-menu">
											<security:authorize access="hasRole('ADMIN')">
												<li><a href="book/administrator/list.do"><spring:message
															code="master.page.manageBook" /></a></li>
											</security:authorize>
											<security:authorize access="hasRole('CUSTOMER')">
												<li><a href="book/customer/list.do"><spring:message
															code="master.page.mybooks" /></a></li>
												<li class="divider"></li>
												<li><a href="book/customer/create.do"><spring:message
															code="master.page.bookservice" /></a></li>
											</security:authorize>
										</ul></li>
								</ul>
							</security:authorize>

							<form:form class="navbar-form navbar-left" action="gym/search.do"
								method="GET">
								<div class="form-group">
									<input type="text" onchange="this.form.submit();"
										name="keyWord" class="form-control col-md-8"
										placeholder="<spring:message
									code="master.page.search" />">
								</div>
							</form:form>

							<ul class="nav navbar-nav navbar-right">

								<security:authorize access="hasRole('ADMIN')">
									<li><a href="dashboard/administrator/list.do"> <spring:message
												code="master.page.dashboard" /> <i class="material-icons">dashboard</i></a></li>
								</security:authorize>
								
								<li class="dropdown"><a href="javascript:void(0)"
									data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
											code="master.page.chooselanguage" /> <i
										class="material-icons">language</i><b class="caret"></b> </a>
									<ul class="dropdown-menu">
										<li><a href="?language=en"><spring:message
													code="master.page.english" /></a></li>
										<li><a href="?language=es"><spring:message
													code="master.page.spanish" /></a></li>
									</ul></li>

								<security:authorize access="hasRole('CUSTOMER')">

									<li><a href="message/actor/list.do"> <spring:message
												code="master.page.messages" /></a></li>
								</security:authorize>

								<security:authorize access="hasRole('ADMIN')">

									<li class="dropdown"><a href="javascript:void(0)"
										data-target="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message
												code="master.page.messages" /> <i class="material-icons">message</i><b
											class="caret"></b> </a>
										<ul class="dropdown-menu">
											<li><a href="message/actor/list.do"><spring:message
														code="master.page.messages" /></a></li>
											<li><a href="spamWords/administrator/list.do"><spring:message
														code="master.page.spamWords" /></a></li>
										</ul></li>
								</security:authorize>

								<security:authorize access="isAnonymous()">

									<li><a href="security/login.do"><spring:message
												code="master.page.login" /></a></li>
									<li><a href="register/create.do"><spring:message
												code="master.page.register" /></a></li>

								</security:authorize>

								<security:authorize access="isAuthenticated()">

									<li class="dropdown"><a href="javascript:void(0)"
										class="dropdown-toggle" data-toggle="dropdown"><spring:message
												code="master.page.profile" /><b class="caret"></b> </a>
										<ul class="dropdown-menu">
											<li><a href="profile/updateprofile.do"><spring:message
														code="master.page.myprofile" /> <i class="material-icons">person</i></a></li>
											<li class="divider"></li>
											<li><a href="javascript:void(0)"> (<security:authentication
														property="principal.username" />)
											</a></li>
											<li><a href="j_spring_security_logout"><spring:message
														code="master.page.logout" /> </a></li>
										</ul></li>

								</security:authorize>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>