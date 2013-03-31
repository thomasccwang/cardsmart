
<%@ page import="com.cardsmart.CardReward" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cardReward.label', default: 'CardReward')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cardReward" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cardReward" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cardReward">
			
				<g:if test="${cardRewardInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="cardReward.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${cardRewardInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardRewardInstance?.startMonth}">
				<li class="fieldcontain">
					<span id="startMonth-label" class="property-label"><g:message code="cardReward.startMonth.label" default="Start Month" /></span>
					
						<span class="property-value" aria-labelledby="startMonth-label"><g:fieldValue bean="${cardRewardInstance}" field="startMonth"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardRewardInstance?.endMonth}">
				<li class="fieldcontain">
					<span id="endMonth-label" class="property-label"><g:message code="cardReward.endMonth.label" default="End Month" /></span>
					
						<span class="property-value" aria-labelledby="endMonth-label"><g:fieldValue bean="${cardRewardInstance}" field="endMonth"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardRewardInstance?.card}">
				<li class="fieldcontain">
					<span id="card-label" class="property-label"><g:message code="cardReward.card.label" default="Card" /></span>
					
						<span class="property-value" aria-labelledby="card-label"><g:link controller="card" action="show" id="${cardRewardInstance?.card?.id}">${cardRewardInstance?.card?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardRewardInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="cardReward.category.label" default="Category" /></span>
					
						<span class="property-value" aria-labelledby="category-label"><g:link controller="rewardCategory" action="show" id="${cardRewardInstance?.category?.id}">${cardRewardInstance?.category?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cardRewardInstance?.id}" />
					<g:link class="edit" action="edit" id="${cardRewardInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
