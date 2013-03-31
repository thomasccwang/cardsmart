
<%@ page import="com.cardsmart.Card" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'card.label', default: 'Card')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-card" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-card" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list card">
			
				<g:if test="${cardInstance?.cardname}">
				<li class="fieldcontain">
					<span id="cardname-label" class="property-label"><g:message code="card.cardname.label" default="Cardname" /></span>
					
						<span class="property-value" aria-labelledby="cardname-label"><g:fieldValue bean="${cardInstance}" field="cardname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardInstance?.issuer}">
				<li class="fieldcontain">
					<span id="issuer-label" class="property-label"><g:message code="card.issuer.label" default="Issuer" /></span>
					
						<span class="property-value" aria-labelledby="issuer-label"><g:link controller="cardIssuer" action="show" id="${cardInstance?.issuer?.id}">${cardInstance?.issuer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardInstance?.network}">
				<li class="fieldcontain">
					<span id="network-label" class="property-label"><g:message code="card.network.label" default="Network" /></span>
					
						<span class="property-value" aria-labelledby="network-label"><g:link controller="cardNetwork" action="show" id="${cardInstance?.network?.id}">${cardInstance?.network?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cardInstance?.rewards}">
				<li class="fieldcontain">
					<span id="rewards-label" class="property-label"><g:message code="card.rewards.label" default="Rewards" /></span>
					
						<g:each in="${cardInstance.rewards}" var="r">
						<span class="property-value" aria-labelledby="rewards-label"><g:link controller="cardReward" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${cardInstance?.id}" />
					<g:link class="edit" action="edit" id="${cardInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
