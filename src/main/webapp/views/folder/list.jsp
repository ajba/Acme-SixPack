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

<table>
	
<jstl:forEach items="${map.keySet()}" var="j"
				varStatus="loopCounter">
				<tr>
				<td>
					<a href="message/actor/list.do?folderId=${j.id}"> ${j.getName()}</a>
				</td>
				<td>
		<jstl:if test="${j.getMessages().size() == 0}">
			No hay mensajes en la carpeta ${j.getName()}
		</jstl:if>
		<jstl:if test="${j.getMessages().size() != 0}">
			<jstl:forEach items="${j.getMessages()}" var="i"	>
			${j.getName()}->${i.getBody()}<br/>
			</jstl:forEach>
			</jstl:if>
				</td>
				</tr>
</jstl:forEach>
</table>

<div> <a href="message/actor/create.do">Nuevo mensaje</a></div>
