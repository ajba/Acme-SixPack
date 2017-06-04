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

<script>
	var idMensaje;
	var folderDestino;
	var folderOrigen;
	
	$(document).ready(function() {
		
		$(".form").hide();
		$(".formEnlace").hide();
		
	});

	function allowDrop(ev) {
		ev.preventDefault();
	}

	function drag(ev1, ev2) {
		idMensaje = ev1;
		folderOrigen = ev2;
	}

	function drop(ev) {
		folderDestino = ev;
		window.location = "message/actor/move.do?folderOrigen=" + folderOrigen
				+ "&folderDestino=" + folderDestino + "&mens=" + idMensaje;
	}
	function newFolder(){
	
	$(".folderForm").attr("type", "text");
	}
	
	function foldeName(id){
		
		$(".form"+id).show();
		$(".enlaceShow"+id).show();
		$(".enlace"+id).hide();
		}
	function edit(id){
		
		window.location = "folder/actor/edit.do?folderId="+id+"&name="+$(".form"+id).val();	
	}
</script>

<div class="col-md-2">
	<table class="table table-bordered" id="carpetas">


		<jstl:forEach items="${actor.getAdditionalFolders()}" var="j"
			varStatus="loopCounter">
			<tr>
				<td><jstl:choose>
						<jstl:when test="${carpeta.equals(j)}">
							<b>${j.getName()}</b>
						</jstl:when>
						<jstl:otherwise>
							<jstl:choose>
								<jstl:when
									test="${j.equals(actor.getTrashbox())||j.equals(actor.getOutbox())||j.equals(actor.getInbox())||j.equals(actor.getSpambox())}">
									<a href="message/actor/list.do?folderId=${j.id}">${j.getName()}</a>
								</jstl:when>
								<jstl:otherwise>
							
									<input  class="form${j.getId()} form" value="${j.getName()}"  style="width: 100px;"/>
					
									<a href="message/actor/list.do?folderId=${j.id}"
										ondrop="drop(${j.id})" ondragover="allowDrop(event)" class="enlace${j.getId()}">${j.getName()} </a>
									
									<a href="folder/actor/delete.do?folderId=${j.id}" class="enlace${j.getId()}"  
									onclick = "return confirm('<spring:message code="folder.delete" />')">
									<i class="material-icons">clear</i></a>
									
									<a onclick="foldeName(${j.id})" class="enlace${j.getId()}" >
									<i class="material-icons">create</i></a>	
									<a  onclick="edit(${j.id})" class="enlaceShow${j.getId()} formEnlace" >
									<i class="material-icons">create</i>
									</a>
								
								</jstl:otherwise>
							</jstl:choose>
						</jstl:otherwise>
					</jstl:choose></td>
			</tr>
		</jstl:forEach>
		<tr>
		<td>
		<form:form  action="folder/actor/create.do" modelAttribute="folder">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:input class="folderForm" type="hidden" path="name" style="width: 100px;"/>
		</form:form>
		</td>
		</tr>
		<tr>
		<td>
		<a  onclick="newFolder()">
		<spring:message code="folder.newfolder" />
		</a>
		</td>
		</tr>
	</table>
</div>
<div class="col-md-10">

	<display:table name="${carpeta.getMessages()}"
		requestURI="${requestURI}" pagesize="8" htmlId="formatCols"
		class="table table-striped" id="row_message">

		<div draggable="true"
			ondragstart="drag(${row_message.id},${carpeta.id})">
			<jstl:choose>
					<jstl:when test="${!carpeta.equals(actor.getOutbox())}">
			<display:column>
				<input type="button" draggable="true" value="Drag&Drop"
					ondragstart="drag(${row_message.id},${carpeta.id})">
			</display:column>
			</jstl:when>
			</jstl:choose>
			

			<acme:displayColumnMD var="sender.name" code="message.sender" />
			<acme:displayColumnMD var="recipient.name" code="message.recipient" />
			<acme:displayColumnMD var="subject" code="message.subject" />
			<acme:displayColumnMD var="momentSent" code="message.date" />
			
	
		

			<display:column>
				<jstl:choose>
					<jstl:when test="${!carpeta.equals(actor.getTrashbox())}">

						<a
							href="message/actor/move.do?folderOrigen=${carpeta.id}&folderDestino=${actor.getTrashbox().getId()}&mens=${row_message.id}"
							class="btn btn-danger" onclick="return confirm('<spring:message code="message.confirm.delete1" />')"
							> <spring:message code="message.delete" />
						</a>

					</jstl:when>
					<jstl:otherwise>

						<a
							href="message/actor/delete.do?mens=${row_message.id}"
							class="btn btn-danger" onclick="return confirm('<spring:message code="message.confirm.delete2" />')"
							> <spring:message code="message.delete" />
						</a>

					</jstl:otherwise>
				</jstl:choose>
			</display:column>
		</div>
	</display:table>

	<br>
	<a href="message/actor/create.do"><spring:message
			code="message.new" /></a>
</div>

