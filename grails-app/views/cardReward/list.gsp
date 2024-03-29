
<%@ page import="com.cardsmart.CardReward" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cardReward.label', default: 'CardReward')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cardReward" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cardReward" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'cardReward.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="startMonth" title="${message(code: 'cardReward.startMonth.label', default: 'Start Month')}" />
					
						<g:sortableColumn property="endMonth" title="${message(code: 'cardReward.endMonth.label', default: 'End Month')}" />
					
						<th><g:message code="cardReward.card.label" default="Card" /></th>
					
						<th><g:message code="cardReward.category.label" default="Category" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cardRewardInstanceList}" status="i" var="cardRewardInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cardRewardInstance.id}">${fieldValue(bean: cardRewardInstance, field: "description")}</g:link></td>
					
						<td>${fieldValue(bean: cardRewardInstance, field: "startMonth")}</td>
					
						<td>${fieldValue(bean: cardRewardInstance, field: "endMonth")}</td>
					
						<td>${fieldValue(bean: cardRewardInstance, field: "card")}</td>
					
						<td>${fieldValue(bean: cardRewardInstance, field: "category")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cardRewardInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
