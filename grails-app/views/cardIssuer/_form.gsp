<%@ page import="com.cardsmart.CardIssuer" %>



<div class="fieldcontain ${hasErrors(bean: cardIssuerInstance, field: 'issuername', 'error')} required">
	<label for="issuername">
		<g:message code="cardIssuer.issuername.label" default="Issuername" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="issuername" required="" value="${cardIssuerInstance?.issuername}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cardIssuerInstance, field: 'cards', 'error')} ">
	<label for="cards">
		<g:message code="cardIssuer.cards.label" default="Cards" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${cardIssuerInstance?.cards?}" var="c">
    <li><g:link controller="card" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="card" action="create" params="['cardIssuer.id': cardIssuerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'card.label', default: 'Card')])}</g:link>
</li>
</ul>

</div>

