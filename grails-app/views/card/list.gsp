
<%@ page import="com.cardsmart.Card" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'card.label', default: 'Card')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-card" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-card" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="cardname" title="${message(code: 'card.cardname.label', default: 'Cardname')}" />
					
						<th><g:message code="card.issuer.label" default="Issuer" /></th>
					
						<th><g:message code="card.network.label" default="Network" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cardInstanceList}" status="i" var="cardInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cardInstance.id}">${fieldValue(bean: cardInstance, field: "cardname")}</g:link></td>
					
						<td>${fieldValue(bean: cardInstance, field: "issuer")}</td>
					
						<td>${fieldValue(bean: cardInstance, field: "network")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cardInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
